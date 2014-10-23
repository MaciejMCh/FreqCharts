package Aplication;

import com.example.freqcharts.ApplyAlphaMask;
import com.example.freqcharts.R;
import com.example.freqcharts.ResourceToBitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class MyFuncMini extends View {

	private static Bitmap mBackground;
	private Paint mPaint;
	
	public MyFuncMini(Context context) {
		super(context);
		
		if(mBackground == null) prepareBackground();
		mPaint = new Paint();
		
	}

	private void prepareBackground() {
		Bitmap bm = ResourceToBitmap.getBitmap(getContext(), R.drawable.saved_bitmap);
		Bitmap am = ResourceToBitmap.getBitmap(getContext(), R.drawable.saved_mask);
		mBackground = ApplyAlphaMask.addMask(bm, am);
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		Log.d("liscik", this.getWidth()+","+this.getHeight());
		canvas.drawBitmap(mBackground, 0, 0, mPaint);
		canvas.drawText("ddsdsd", 10, 10, new Paint());
	}

}
