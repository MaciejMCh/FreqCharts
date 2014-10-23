package TransferFunctionLogic;

import com.example.freqcharts.Glyph;
import com.example.freqcharts.PaintFactory;

public class ComplexRound implements ComplexFunction, Glyph {

	private ComplexFunction mLHS;
	
	public ComplexRound(){
		mLHS = new ComplexNull(this, ComplexNull.LEFT);
	}
	
	public ComplexRound(ComplexFunction aFunction){
		this.mLHS = aFunction;
	}
	
	@Override
	public int getWidth() {
//		return ((Glyph)mLHS).getWidth() + PaintFactory.getInstance().getRoundWidth();
		return (int) (((Glyph)mLHS).getWidth() + this.getHeight()*0.4);
	}

	@Override
	public int getHeight() {
		return ((Glyph)mLHS).getHeight();
	}

	@Override
	public Complex getResult(float aOmega) {
		return ((ComplexFunction)mLHS).getResult(aOmega);
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
		return "round("+this.mLHS.toString()+")";
	}

}
