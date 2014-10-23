package Aplication;

import java.sql.PreparedStatement;

import TransferFunctionLogic.ComplexFunction;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.Log;

import com.example.freqcharts.ApplyAlphaMask;
import com.example.freqcharts.BitmapWithAlpha;
import com.example.freqcharts.FunctionBuilder;
import com.example.freqcharts.R;
import com.example.freqcharts.ResourceToBitmap;
import com.example.freqcharts.ResourceToBitmapMask;

import android.graphics.PorterDuff.Mode;

@SuppressLint("NewApi")
public class MyFuncBuilder extends FunctionBuilder implements FunctionProvider {

	private Bitmap mBackBitmap, mToolbarBitmap, mToolbatAlphaMask;
	private Paint mBackPaint;
//	private int mW;
	private Bitmap mToolBarResult;
	private Paint mToolBarPaint;
	private Paint mTestPaint;
	private Bitmap bb;
	private Paint mMaskPaint;
	private Bitmap mToolBar;
	private Paint maskPaint;
	private Paint imagePaint;
	private Bitmap result;
	private Bitmap destBitmap;
	private Bitmap NavBarBitmap;
	

	public MyFuncBuilder(Context context) {
		super(context);
		mBackBitmap = ResourceToBitmap.getBitmap(context, R.drawable.back3);
		
		mToolbarBitmap = ResourceToBitmap.getBitmap(context, R.drawable.toolbar_bitmap);
		mToolbatAlphaMask = ResourceToBitmapMask.getBitmap(context, R.drawable.toolbar_mask);
		
		
		Bitmap rgbDrawable = mToolbarBitmap;
		Bitmap alphaDrawable = mToolbatAlphaMask;
		
		int width = rgbDrawable.getWidth();
		  int height = rgbDrawable.getHeight();

		  

		  destBitmap = Bitmap.createBitmap(width, height,
		      Bitmap.Config.ARGB_8888);

		  int[] pixels = new int[width];
		  int[] alpha = new int[width];

		  for (int y = 0; y < height; y++)
		  {
		      rgbDrawable.getPixels(pixels, 0, width, 0, y, width, 1);
		      alphaDrawable.getPixels(alpha, 0, width, 0, y, width, 1);

		      for (int x = 0; x < width; x++) 
		      {
		          // Replace the alpha channel with the r value from the bitmap.
		          pixels[x] = (pixels[x] & 0x00FFFFFF) | ((alpha[x] << 8) & 0xFF000000);
		      }

		    destBitmap.setPixels(pixels, 0, width, 0, y, width, 1);
		  }

		  alphaDrawable.recycle();
		  rgbDrawable.recycle();
		  
		  
		  Bitmap bbb = ResourceToBitmap.getBitmap(context, R.drawable.nav_bitmap);
		  Bitmap aaa = ResourceToBitmap.getBitmap(context, R.drawable.nav_mask);
		  NavBarBitmap = BitmapWithAlpha.addAlpha(bbb, aaa);
//		  NavBarBitmap = aaa;
		  

		  
		  

		  
		

	}
	
	@Override
	protected void onSizeChanged (int w, int h, int oldw, int oldh){
		super.onSizeChanged(w, h, oldw, oldh);
		
		mW = w;
		mH = h;
	}

	@Override
	public ComplexFunction getFunction() {
		return super.getFunc();
	}
	
	
	@Override
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
	
	@Override
	protected void drawToolbar(Canvas canvas){
		
		canvas.drawBitmap(destBitmap, 0, mH/2-160, new Paint());
	    
	}
	
	@Override
	protected void drawNavigationbar(Canvas canvas){
		canvas.drawBitmap(NavBarBitmap, mW-100, mH/2-160, new Paint());
		canvas.drawBitmap(mNavBarBitmaps.get(0), mW-91, mH/2-151, new Paint());
		canvas.drawBitmap(mNavBarBitmaps.get(1), mW-91, mH/2-58, new Paint());
		canvas.drawBitmap(mNavBarBitmaps.get(2), mW-91, mH/2+35, new Paint());
		canvas.drawBitmap(mNavBarBitmaps.get(3), mW-91, mH/2+128, new Paint());
	}
	
	@Override
	protected int[]  getToolbarCoords(){
		int[] aC = new int[16];
		
		aC[0] = 10;
		aC[1] = 70;
		aC[2] = 10;
		aC[3] = 70;
		aC[4] = 10;
		aC[5] = 70;
		aC[6] = 10;
		aC[7] = 70;
		
		aC[8] = mY-120;
		aC[9] = mY-120;
		aC[10] = mY-60;
		aC[11] = mY-60;
		aC[12] = mY;
		aC[13] = mY;
		aC[14] = mY+60;
		aC[15] = mY+60;
		return aC;
	}
	
	@Override
	protected int[] getNavbarCoords(){
		int aC[] = new int[8];
		aC[0] = mW-91;
		aC[1] = mW-91;
		aC[2] = mW-91;
		aC[3] = mW-91;
		aC[4] = mH/2-151;
		aC[5] = mH/2-58;
		aC[6] = mH/2+35;
		aC[7] = mH/2+128;
		return aC;		
	}

	
	
	
	
	

}
