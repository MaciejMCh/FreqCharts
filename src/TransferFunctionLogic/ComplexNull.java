package TransferFunctionLogic;

import com.example.freqcharts.Glyph;

public class ComplexNull implements ComplexFunction, Glyph {

	public final static int LEFT = 0;
	public final static int RIGHT = 1;
	
	private ComplexFunction mParent;
	private int mSide;
	
	public ComplexNull(ComplexFunction aParent, int aSide){
		mParent = aParent;
		mSide = aSide;
	}
	
	public void fill(ComplexFunction aFunction){
		if(mSide == ComplexNull.LEFT){
			mParent.setLHS(aFunction);
		}else{
			mParent.setRHS(aFunction);
		}
	}
	
	@Override
	public int getWidth() {
		return 30;
	}

	@Override
	public int getHeight() {
		return 40;
	}

	@Override
	public Complex getResult(float aOmega) {
		return new Complex(0, 0);
	}

	@Override
	public void setRHS(ComplexFunction aFunction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLHS(ComplexFunction aFunction) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString(){
		return "null()";
	}

}
