package TransferFunctionLogic;

public class ComplexSquare implements ComplexFunction {

	private ComplexFunction mBase;
	
	public ComplexSquare(ComplexFunction aBase){
		mBase = aBase;
	}
	
	public ComplexSquare(){
		
	}
	
	@Override
	public Complex getResult(float aOmega) {
		return mBase.getResult(aOmega).times(mBase.getResult(aOmega));
	}
	
	public String toString(){
		return "square("+mBase.toString()+")";
	}
	
	public void setBase(ComplexFunction aBase){
		this.mBase = aBase;
	}

	@Override
	public void setRHS(ComplexFunction aFunction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLHS(ComplexFunction aFunction) {
		// TODO Auto-generated method stub
		
	}

}
