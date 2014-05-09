package soot.jimple.infoflow.test.methodSummary.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static soot.jimple.infoflow.methodSummary.data.SourceSinkType.Field;
import static soot.jimple.infoflow.methodSummary.data.SourceSinkType.Parameter;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import soot.jimple.infoflow.methodSummary.SummaryGenerator;
import soot.jimple.infoflow.methodSummary.data.AbstractMethodFlow;
import soot.jimple.infoflow.test.methodSummary.ArbitraryAccessPath;
import soot.jimple.infoflow.test.methodSummary.FieldToPara;

public class FieldToParaTests extends TestHelper {

	static final String className = "soot.jimple.infoflow.test.methodSummary.FieldToPara";
	static final String OBJ_FIELD = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: java.lang.Object obField>";
	static final String OBJ_ARRAY = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: java.lang.Object[] arrayField>";
	static final String LIST_FIELD = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: java.util.List listField>";
	static final String DATA_FIELD = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: soot.jimple.infoflow.test.methodSummary.Data dataField>";
	static final String ALIST_DATA = "<java.util.ArrayList: java.lang.Object[] elementData>";
	static final String INT_ARRAY = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: int[] intArray>";

	@Test(timeout = 100000)
	public void fieldToPara1() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void dataParameter(soot.jimple.infoflow.test.methodSummary.Data)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { OBJ_FIELD }, Parameter, 0, new String[] { DATACLASS_OBJECT_FIELD }));
		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void fieldToPara2() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void dataParameter2(soot.jimple.infoflow.test.methodSummary.Data)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { OBJ_ARRAY }, Parameter, 0, new String[] { DATACLASS_OBJECT_FIELD }));

	}

	@Ignore
	@Test(timeout = 100000)
	public void fieldToPara2NoFalsePositive() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void dataParameter2(soot.jimple.infoflow.test.methodSummary.Data)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { OBJ_ARRAY }, Parameter, 0, new String[] { DATACLASS_OBJECT_FIELD }));
		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void fieldToPara3() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void dataParameter3(soot.jimple.infoflow.test.methodSummary.Data)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { LIST_FIELD, ALIST_DATA }, Parameter, 0, new String[] { DATACLASS_OBJECT_FIELD }));
	}

	@Ignore
	// bug in infoflow related to the sink -> source path construction
	@Test(timeout = 100000)
	public void fieldToPara3NoFalePositive() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void dataParameter3(soot.jimple.infoflow.test.methodSummary.Data)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { LIST_FIELD, ALIST_DATA }, Parameter, 0, new String[] { DATACLASS_OBJECT_FIELD }));
		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void fieldToPara4() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void dataParameter4(soot.jimple.infoflow.test.methodSummary.Data)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0,
				new String[] { DATACLASS_OBJECT_FIELD }));
	}

	@Ignore
	// bug in infoflow related to the sink -> source path construction
	@Test(timeout = 100000)
	public void fieldToPara4NoFalsePositive() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void dataParameter4(soot.jimple.infoflow.test.methodSummary.Data)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0,
				new String[] { DATACLASS_OBJECT_FIELD }));
		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void fieldToPara5() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void dataParameter5(soot.jimple.infoflow.test.methodSummary.Data)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0,
				new String[] { DATACLASS_OBJECT_FIELD }));

		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void fieldToParaRec1() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void dataParameterRec(soot.jimple.infoflow.test.methodSummary.Data,int)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { OBJ_FIELD }, Parameter, 0, new String[] { DATACLASS_OBJECT_FIELD }));
		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void fieldToArrayParameter() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void objArrayParameter(java.lang.Object[])>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { OBJ_FIELD }, Parameter, 0, new String[] {}));
		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void fieldToArrayParameter2() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void objArrayParameter2(java.lang.Object[])>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { OBJ_ARRAY }, Parameter, 0, new String[] {}));
		assertEquals(1, flow.size());
	}

	@Test(timeout = 250000)
	public void fieldToArrayParameter3() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void objArrayParameter3(java.lang.Object[])>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { LIST_FIELD, ALIST_DATA }, Parameter, 0, new String[] {}));
		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void fieldToArrayParameter4() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void objArrayParameter4(java.lang.Object[])>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0, new String[] {}));
		//assertTrue(containsFieldToParaFlow(flow,"<soot.jimple.infoflow.test.methodSummary.FieldToPara: soot.jimple.infoflow.test.methodSummary.Data dataField>",DATACLASS_OBJECT_FIELD,0,OBJECT_ARRAY,NO_ACCESS_PATH));
		//Assert.assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void fieldToArrayParameter5() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void objArrayParameter5(java.lang.Object[])>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0, new String[] {}));
		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void listFieldToParameter1() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void listParameter(java.util.List)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { LIST_FIELD, ALIST_DATA }, Parameter, 0, new String[] { ALIST_DATA }));
	}

	@Ignore
	@Test(timeout = 100000)
	public void listFieldToParameter1NoFalsePositive() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void listParameter(java.util.List)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { LIST_FIELD, ALIST_DATA }, Parameter, 0, new String[] { ALIST_DATA }));
		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void listFieldToParameter2() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void listParameter2(java.util.List)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0, new String[] { ALIST_DATA }));
	}

	@Ignore
	// bug in infoflow related to the sink -> source path construction
	@Test(timeout = 100000)
	public void listFieldToParameter2NoFalsePositive() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void listParameter2(java.util.List)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0, new String[] { ALIST_DATA }));
		assertEquals(1, flow.size());
		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void listFieldToParameter3() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void listParameter3(java.util.List)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0, new String[] { ALIST_DATA }));
	}

	@Test(timeout = 100000)
	public void listFieldToParameter3NoFalsePositive() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void listParameter3(java.util.List)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0, new String[] { ALIST_DATA }));
		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void listFieldToParameter4() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void listParameter4(java.util.List)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0, new String[] { ALIST_DATA }));

	}

	@Test(timeout = 100000)
	public void listFieldToParameter4NoFalsePositive() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void listParameter4(java.util.List)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0, new String[] { ALIST_DATA }));
	}

	@Test(timeout = 100000)
	public void listFieldToParameter5() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void listParameter5(java.util.List)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { INT_ARRAY }, Parameter, 0, new String[] { ALIST_DATA, "<java.lang.Integer: int value>" }));
	}

	@Ignore
	@Test(timeout = 100000)
	public void listFieldToParameter5NoFalsePositive() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void listParameter5(java.util.List)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { INT_ARRAY }, Parameter, 0, new String[] { ALIST_DATA, "<java.lang.Integer: int value>" }));
		assertEquals(1, flow.size());
	}

	@Test(timeout = 100000)
	public void listFieldToParameter6() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void listParameter6(java.util.List)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0, new String[] { ALIST_DATA }));

	}

	@Ignore
	@Test(timeout = 100000)
	public void listFieldToParameter6NoFalsePositive() {
		SummaryGenerator s = summeryGenerator();
		String mSig = "<soot.jimple.infoflow.test.methodSummary.FieldToPara: void listParameter6(java.util.List)>";
		Set<AbstractMethodFlow> flow = s.createMethodSummary(mSig, methods()).getFlowsForMethod(mSig);
		assertTrue(containsFlow(flow, Field, new String[] { DATA_FIELD, DATACLASS_OBJECT_FIELD }, Parameter, 0, new String[] { ALIST_DATA }));
		assertEquals(1, flow.size());
	}

	@Override
	Class getClazz() {
		return FieldToPara.class;
	}

	private SummaryGenerator summeryGenerator() {
		SummaryGenerator sg = new SummaryGenerator();

		List<String> sub = new LinkedList<String>();
		sub.add("java.util.ArrayList");
		sg.setSubstitutedWith(sub);
		return sg;
	}
}
