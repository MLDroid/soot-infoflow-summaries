package soot.jimple.infoflow.methodSummary.data;

public class Tuple<T, R> {
	public final T _1;
	public final R _2;

	public Tuple(T _1, R _2) {
		this._1 = _1;
		this._2 = _2;
	}
	@Override
	public String toString(){
		return "(" + _1 + "," +_2 + ")";
	}
}