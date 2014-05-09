package soot.jimple.infoflow.methodSummary.sourceSinkManager;

import heros.InterproceduralCFG;
import heros.solver.IDESolver;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import soot.Local;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.Unit;
import soot.Value;
import soot.jimple.DefinitionStmt;
import soot.jimple.InstanceFieldRef;
import soot.jimple.ParameterRef;
import soot.jimple.ReturnStmt;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.Stmt;
import soot.jimple.ThisRef;
import soot.jimple.infoflow.methodSummary.data.MethodSummaries;
import soot.jimple.infoflow.methodSummary.sourceSinkFactory.SourceSinkFactory;
import soot.jimple.infoflow.source.ISourceSinkManager;
import soot.jimple.infoflow.source.SourceInfo;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * SourceSinkManager for computing library summaries
 * 
 * @author Malte Viering
 * @author Steven Arzt
 */
public class SummarySourceSinkManager implements ISourceSinkManager {
	
	

	protected final LoadingCache<SootClass, Collection<SootField>> classToFields =
		IDESolver.DEFAULT_CACHE_BUILDER.build(new CacheLoader<SootClass, Collection<SootField>>() {
			@Override
			public Collection<SootField> load(SootClass sc) throws Exception {
				List<SootField> res = new LinkedList<SootField>();
				List<SootClass> impler = Scene.v().getActiveHierarchy().getSuperclassesOfIncluding(method.getDeclaringClass());
				for(SootClass c : impler)
					res.addAll(c.getFields());
				return res;
			}
		});
	
	private boolean forceTaintSubFields = false;
	
	

	private final Logger logger = LoggerFactory.getLogger(SummarySourceSinkManager.class);
	private final String methodSig;
	
	private SootMethod method = null;
	private final int summaryAccessPathLength;
	private SourceModel sModel = null;
	
	public SummarySourceSinkManager(String mSig, int apl) {
		this.methodSig = mSig;
		summaryAccessPathLength = apl;
		
	}

	public SummarySourceSinkManager(String method, MethodSummaries flows,int  apl) {
		this.methodSig = method;
		summaryAccessPathLength = apl;
	}


	
	@Override
	public SourceInfo getSourceInfo(Stmt sCallSite, InterproceduralCFG<Unit, SootMethod> cfg) {
		if(method == null && sModel == null){
			method = Scene.v().getMethod(methodSig);
			sModel = new SourceModel(method, summaryAccessPathLength, getClassFields());
			System.out.println(sModel.toString());
		}
		
		SootMethod currentMethod = cfg.getMethodOf(sCallSite);
		if(currentMethod.toString().contains("get(") ){
			int a ;
			a = 3;
		}
			 
		
		// If this is the dummy main method, we skip it
		if (currentMethod.toString().contains("<dummyMainClass: void dummyMainMethod()>"))
			return null;

		//check if we have a source with apl > 0
		if (sCallSite instanceof DefinitionStmt) {
			DefinitionStmt jstmt = (DefinitionStmt) sCallSite;
			Value rightOp = jstmt.getRightOp();
			if(rightOp instanceof InstanceFieldRef){
				SourceData si = sModel.isSource((Local) ((InstanceFieldRef) rightOp).getBase(),((InstanceFieldRef) rightOp).getField());
				if(si!=null){
					if(jstmt.toString().contains("soot.jimple.infoflow.test.methodSummary.FieldToPara: java.lang.Object[] arrayField")){
						System.out.println();
					}
					System.out.println("source: " + sCallSite + " " + currentMethod.getSignature());
					return new SourceInfo(si.isTaintSubFields()|| forceTaintSubFields, si.getSourceInfo());
				}
					
			}
		}
	
		//check if we have  asource with apl = 0 (this or paramter source)
		if (sCallSite instanceof DefinitionStmt) {
			DefinitionStmt jstmt = (DefinitionStmt) sCallSite;
			Value rightOp = jstmt.getRightOp();
			// Check for direct parameter accesses
			if (currentMethod == method && rightOp instanceof ParameterRef) {
				ParameterRef pref = (ParameterRef) rightOp;
				logger.debug("source: " + sCallSite + " " + currentMethod.getSignature());
				System.out.println("source: " + sCallSite + " " + currentMethod.getSignature());
				return new SourceInfo(false || forceTaintSubFields, SourceSinkFactory.createParamterSource(method, pref.getIndex(), null));
			}
			else if (currentMethod == method && rightOp instanceof ThisRef) {
				System.out.println("source: (this)" + sCallSite + " " + currentMethod.getSignature());				
				return new SourceInfo(false|| forceTaintSubFields, SourceSinkFactory.createThisSource());
			}
		}
		return null;
	}
	
	@Override
	public boolean isSink(Stmt sCallSite, InterproceduralCFG<Unit, SootMethod> cfg) {
		SootMethod m = cfg.getMethodOf(sCallSite);
		if (m.getSignature().contains("dummyMainClass: void dummyMainMethod()"))
			return false;

		if (isReturnSink(sCallSite, cfg)) {
			logger.debug("sink: " + sCallSite + " method: " + m.getSignature());
			System.out.println("sink: " + sCallSite + " method: " + m.getSignature());
			return true;
		}

		return false;
	}

	private boolean isReturnSink(Stmt sCallSite, InterproceduralCFG<Unit, SootMethod> cfg) {
		if (sCallSite instanceof ReturnStmt || sCallSite instanceof ReturnVoidStmt) {
			if (methodSig != null && methodSig.contains(cfg.getMethodOf(sCallSite).getSignature()))
				return true;
		}
		return false;
	}
	
	private Collection<SootField> getClassFields(){
		return classToFields.getUnchecked(method.getDeclaringClass());
	}
	public void setForceTaintSubFields(boolean forceTaintSubFields) {
		this.forceTaintSubFields = forceTaintSubFields;
	}
}
