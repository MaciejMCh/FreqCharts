package TransferFunctionLogic;

import com.example.freqcharts.Glyph;
import com.example.freqcharts.PaintFactory;

public class ComplexConstant implements ComplexFunction, Glyph {

	String mDisplay;
	float mConstant;
	
	public ComplexConstant(float aConstant){
		mConstant = aConstant;
		this.setDisplay();
	}
	
	public ComplexConstant(){
		
	}
	
	@Override
	public Complex getResult(float aOmega) {
		return new Complex(mConstant, 0);
	}
	
	public String toString(){
		return "const("+mConstant+")";
	}
	
	public void setConstant(float aConstant){
		this.mConstant = aConstant;
		this.setDisplay();
	}
	
	public float getConstant(){
		return this.mConstant;
	}

	@Override
	public int getWidth() {
		return PaintFactory.getInstance().getTextWidth(mDisplay);
	}

	@Override
	public int getHeight() {
		return PaintFactory.getInstance().getTextHeight();
	}

	@Override
	public void setRHS(ComplexFunction aFunction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLHS(ComplexFunction aFunction) {
		// TODO Auto-generated method stub
		
	}
	
	public String getDisplay(){
		return mDisplay;
	}
	
	private void setDisplay(){
		Float rest = (float) (mConstant - Math.floor(mConstant));
		if(rest == 0){
			mDisplay = Math.round(mConstant)+""; 
		}else{
			mDisplay = mConstant+"";
		}
	}

}
