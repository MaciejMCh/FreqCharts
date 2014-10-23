package com.example.freqcharts;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class ResourceToBitmapMask {
	
	public static Bitmap getBitmap(Context aContext, int aID){
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;	// No pre-scaling
		options.inPreferredConfig = Bitmap.Config.ALPHA_8;

		// Read in the resource
		final Bitmap bitmap = BitmapFactory.decodeResource(aContext.getResources(), aID, options);
		return bitmap;
	}
}
