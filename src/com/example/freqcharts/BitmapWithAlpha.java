package com.example.freqcharts;

import android.graphics.Bitmap;

public class BitmapWithAlpha {

	public static Bitmap addAlpha(Bitmap aBitmap, Bitmap aAlphaMask){
		
		int width = aBitmap.getWidth();
		  int height = aBitmap.getHeight();

		  

		 Bitmap destBitmap = Bitmap.createBitmap(width, height,
		      Bitmap.Config.ARGB_8888);

		  int[] pixels = new int[width];
		  int[] alpha = new int[width];

		  for (int y = 0; y < height; y++)
		  {
		      aBitmap.getPixels(pixels, 0, width, 0, y, width, 1);
		      aAlphaMask.getPixels(alpha, 0, width, 0, y, width, 1);

		      for (int x = 0; x < width; x++) 
		      {
		          pixels[x] = (pixels[x] & 0x00FFFFFF) | ((alpha[x] << 8) & 0xFF000000);
		      }

		    destBitmap.setPixels(pixels, 0, width, 0, y, width, 1);
		  }

		  aAlphaMask.recycle();
		  aBitmap.recycle();
		  
		  return destBitmap;
		
	}
	
}
