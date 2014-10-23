package View;

import com.example.freqcharts.R;
import com.example.freqcharts.ResourceToBitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.GridView;

public class MyGridView extends GridView {

	private Bitmap mBackBitmap;
	private int mW;
	private int mH;
	private Paint mBackPaint;

	public MyGridView(Context context) {
		super(context);
		mBackBitmap = ResourceToBitmap.getBitmap(context, R.drawable.back3);
		mBackPaint = new Paint();
		
		this.setNumColumns(AUTO_FIT);
		this.setColumnWidth(120);
		//this.setVerticalSpacing(15);
		
		
	}

	@Override
	protected void onDraw(Canvas canvas){
		drawBackGround(canvas);
	}
	
	
	@Override
	protected void onSizeChanged (int w, int h, int oldw, int oldh){
		super.onSizeChanged(w, h, oldw, oldh);		
		mW = w;
		mH = h;
		
		
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