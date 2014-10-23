package TransferFunctionLogic;

import com.example.freqcharts.Glyph;
import com.example.freqcharts.PaintFactory;

public class ComplexES implements ComplexFunction, Glyph{

	private float mMultiplier;
	private String mDisplay;
	
	public ComplexES(float aMultiplier){
		mMultiplier = aMultiplier;
		this.setDisplay();
	}
	
	public ComplexES(){
		
	}
	
	@Override
	public Complex getResult(float aOmega) {
		return new Complex(0, mMultiplier * aOmega);
	}
	
	public String toString(){
		return "es("+mMultiplier+")";
	}
	
	public void setMultiplier(float aMultiplier){
		this.mMultiplier = aMultiplier;
		this.setDisplay();
	}
	
	public float getMultiplier(){
		return this.mMultiplier;
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
		if(mMultiplier == 1){
			mDisplay = "s";
		}else{
			Float rest = (float) (mMultiplier - Math.floor(mMultiplier));
			if(rest == 0){
				mDisplay = Math.round(mMultiplier)+"s"; 
			}else{
				mDisplay = mMultiplier+"s";
			}
		}
	}

}
