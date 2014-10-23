package TransferFunctionLogic;

import android.util.Log;

import com.example.freqcharts.Glyph;

public class ComplexDiv implements ComplexFunction, Glyph {

	private ComplexFunction mLHS;
	private ComplexFunction mRHS;
	
	public ComplexDiv(ComplexFunction aLHS, ComplexFunction aRHS){
		mLHS = aLHS;
		mRHS = aRHS;
	}
	
	public ComplexDiv(){
		mLHS = new ComplexNull(this, ComplexNull.LEFT);
		mRHS = new ComplexNull(this, ComplexNull.RIGHT);
	}
	
	@Override
	public Complex getResult(float aOmega) {
		return mLHS.getResult(aOmega).divides(mRHS.getResult(aOmega));
	}
	
	public void setLHS(ComplexFunction aLHS){
		this.mLHS = aLHS;
	}
	public void setRHS(ComplexFunction aRHS){
		this.mRHS = aRHS;
	}
	public ComplexFunction getLHS(){
		return this.mLHS;
	}
	public ComplexFunction getRHS(){
		return this.mRHS;
	}
	
	public String toString(){
		return "div("+mLHS.toString()+","+mRHS.toString()+")";
		
	}

	@Override
	public int getWidth() {
		int rW;
		int lW;
		if(mLHS==null){
			lW = 30;
		}else{
			lW = ((Glyph)mLHS).getWidth();
		}
		if(mRHS==null){
			rW = 30;
		}else{
			rW = ((Glyph)mRHS).getWidth();
		}		
		return Math.max(lW, rW);
	}

	@Override
	public int getHeight() {
		int rH;
		int lH;
		if(mLHS==null){
			lH = 45;
		}else{
			lH = ((Glyph)mLHS).getHeight();
		}
		if(mRHS==null){
			rH = 45;
		}else{
			rH = ((Glyph)mRHS).getHeight();
		}		
		Log.d("czyta","DIV: " + lH+"+"+rH+"+"+3);
		return lH+rH+3;
	}

}
