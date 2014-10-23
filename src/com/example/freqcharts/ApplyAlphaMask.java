package com.example.freqcharts;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ApplyAlphaMask {

	public static Bitmap addMask(Bitmap aBitmap, Bitmap aMask){
		
		Bitmap bb = Bitmap.createBitmap(aBitmap.getWidth(), aBitmap.getHeight(), Bitmap.Config.ARGB_8888);
		
				Canvas cc = new Canvas(bb);
				
				Paint mTestPaint = new Paint();
				mTestPaint.setFilterBitmap(false);
				cc.drawBitmap(aBitmap, 0, 0, mTestPaint);
		

		
		for(int y=0; y<=bb.getHeight()-1; y++){
			for(int x=0; x<=bb.getWidth()-1; x++){
				String c = Integer.toBinaryString(aBitmap.getPixel(x, y));
				int r = Integer.parseInt(c.substring(8,15), 2);
				int g = Integer.parseInt(c.substring(16,23), 2);
				int b = Integer.parseInt(c.substring(24,31), 2);
				
				int a = aMask.getPixel(x, y)/256/256;
				
				bb.setPixel(x, y, Color.argb(a, r, g, b));
			}
		}
		
		return bb;
	}
	
}
