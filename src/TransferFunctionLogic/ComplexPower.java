package TransferFunctionLogic;

import com.example.freqcharts.Glyph;
import com.example.freqcharts.PaintFactory;

public class ComplexPower implements ComplexFunction, Glyph {

	private ComplexFunction mLHS;
	private int mExp;
	
	public ComplexPower(){
		mLHS = new ComplexNull(this, ComplexNull.LEFT);
	}
	public ComplexPower(int aExp){
		mLHS = new ComplexNull(this, ComplexNull.LEFT);
		mExp = aExp;
	}
	
	public ComplexPower(ComplexFunction aFunction){
		this.mLHS = aFunction;
	}
	
	@Override
	public int getWidth() {
		return ((Glyph)mLHS).getWidth() + PaintFactory.getInstance().getExponentWidth(mExp);
	}

	@Override
	public int getHeight() {
		return (int) (((Glyph)mLHS).getHeight()+PaintFactory.getInstance().getTextHeight()/1.5);
	}

	@Override
	public Complex getResult(float aOmega) {
		Complex base =  ((ComplexFunction)mLHS).getResult(aOmega);
		Complex result = new Complex(base.re(), base.im());
		
		for(int i = 2; i<=mExp;i++){
			result = result.times(base);
		}
		return result;
	}

	@Override
	public void setRHS(ComplexFunction aFunction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLHS(ComplexFunction aFunction) {
		this.mLHS = aFunction;
	}

	public ComplexFunction getLHS() {
		return this.mLHS;
	}
	
	public String toString(){
		return "power("+this.mLHS.toString()+")";
	}
	public int getExp() {
		return this.mExp;
	}
	public void setExponent(int aExp) {
		this.mExp = aExp;
	}

}
