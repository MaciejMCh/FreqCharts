package TransferFunctionLogic;

import android.util.Log;

import com.example.freqcharts.Glyph;
import com.example.freqcharts.PaintFactory;

public class ComplexDiff implements ComplexFunction, Glyph {

	ComplexFunction mLHS;
	ComplexFunction mRHS;
	
	public ComplexDiff(ComplexFunction aLS, ComplexFunction aRS){
		mLHS = aLS;
		mRHS = aRS;
	}
	
	public ComplexDiff(){
		mLHS = new ComplexNull(this, ComplexNull.LEFT);
		mRHS = new ComplexNull(this, ComplexNull.RIGHT);
	}
	
	@Override
	public Complex getResult(float aOmega) {
		return mLHS.getResult(aOmega).minus(mRHS.getResult(aOmega));
	}
	
	public String toString(){
		return "diff("+mLHS.toString()+","+mRHS.toString()+")";
	}
	
	public void setLHS(ComplexFunction aLHS){
		this.mLHS = aLHS;
	}
	public void setRHS(ComplexFunction aRHS){
		this.mRHS = aRHS;
	}
	
	public ComplexFunction getRHS(){
		return this.mRHS;
	}
	
	public ComplexFunction getLHS(){
		return this.mLHS;
	}

	@Override
	public int getWidth() {
		int rw;
		int lw;
		
		try{
			rw = ((Glyph)mRHS).getWidth();
		}catch(NullPointerException e){
			rw = 30;
		}
		
		try{
			lw = ((Glyph)mLHS).getWidth();
		}catch(NullPointerException e){
			lw = 30;
		}
		
		return rw + lw + PaintFactory.getInstance().getTextWidth(" - ");
	}

	@Override
	public int getHeight() {
		
		int rh;
		int lh;
		
		try{
			rh = ((Glyph)mRHS).getHeight();
		}catch(NullPointerException e){
			rh = 45;
		}
		
		try{
			lh = ((Glyph)mLHS).getHeight();
		}catch(NullPointerException e){
			lh = 45;
		}
		Log.d("czyta","DIFF: " + Math.max(lh, rh));
		return Math.max(lh, rh);
	}

}
