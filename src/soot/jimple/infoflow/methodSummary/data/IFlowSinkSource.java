package soot.jimple.infoflow.methodSummary.data;

import java.util.Map;

import soot.jimple.infoflow.data.Abstraction;
import soot.jimple.infoflow.methodSummary.data.impl.AccessPath;

public interface IFlowSinkSource {
	
	public boolean isParamter();

	public int getParamterIndex();

	public boolean isField();

	public boolean isThis();

	public String getField();

	public String getParaType();

	public boolean hasAccessPath();
	
	public AccessPath getAccessPath();
	
	public Map<String, String> xmlAttributes();
	
}
