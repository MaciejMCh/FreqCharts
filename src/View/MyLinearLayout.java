package View;

import com.example.freqcharts.R;
import com.example.freqcharts.ResourceToBitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

	private int mW;
	private int mH;
	private Bitmap mBackBitmap;
	private Paint mBackPaint;

	public MyLinearLayout(Context context) {
		super(context);
		mBackBitmap = ResourceToBitmap.getBitmap(context, R.drawable.back3);
		mBackPaint = new Paint();
	}
	
	
	
	
	@Override
	protected void onSizeChanged (int w, int h, int oldw, int oldh){
		super.onSizeChanged(w, h, oldw, oldh);		
		mW = w;
		mH = h;
		
		
	}
	
	@Override
	protected void onDraw(Canvas canvas){
//		drawBackGround(canvas);
	}
	
	protected void drawBackGround(Canvas canvas){
		int x = 0;
		int y = 0;
		while(y<mH){
			while(x<mW){
				canvas.drawBitmap(mBackBitmap, x, y, mBackPaint);
				x+=89;
			}
			x=0;
			y+=89;
		}
	}

}
