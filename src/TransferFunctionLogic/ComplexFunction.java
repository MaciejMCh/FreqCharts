package TransferFunctionLogic;

public interface ComplexFunction {
	public Complex getResult(float aOmega);	
	public void setRHS(ComplexFunction aFunction);
	public void setLHS(ComplexFunction aFunction);
	
}
