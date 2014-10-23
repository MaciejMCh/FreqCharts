package com.example.freqcharts;

import java.util.ArrayList;

import TransferFunctionLogic.ComplexConstant;
import TransferFunctionLogic.ComplexDiff;
import TransferFunctionLogic.ComplexDiv;
import TransferFunctionLogic.ComplexES;
import TransferFunctionLogic.ComplexFunction;
import TransferFunctionLogic.ComplexMult;
import TransferFunctionLogic.ComplexNull;
import TransferFunctionLogic.ComplexRound;
import TransferFunctionLogic.ComplexSum;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.util.Log;

public class FunctionRenderer {

	private static FunctionRenderer mInstance;
	
	public static FunctionRenderer getInstance(){
		if(mInstance == null){
			mInstance = new FunctionRenderer();
		}
		return mInstance;
	}




	private Paint mDivLine;
	private Paint mTextPaint;
	private Paint mRoundPaint;
	private Paint mNullRect;
	private Paint mBmapPaint;
	
	private ArrayList<NullSpace> mNullList;
	private Paint mBackPaint;
	
	public FunctionRenderer(){
		mNullList = new ArrayList<NullSpace>();
		
		mDivLine = new Paint(Paint.ANTI_ALIAS_FLAG);
		mDivLine.setColor(Color.WHITE);
		
		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);		 
		mTextPaint.setTextSize(20);
		mTextPaint.setColor(Color.WHITE);
		
//		mRoundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mRoundPaint = PaintFactory.getInstance().getRoundPaint();
		mRoundPaint.setColor(Color.WHITE);
		
		mNullRect = new Paint(Paint.ANTI_ALIAS_FLAG);		
		mNullRect.setColor(Color.GRAY);
		
		mBmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		
		mBackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBackPaint.setColor(Color.WHITE);
	}
	
	public Bitmap renderFunction(ComplexFunction aFunction){
		int width = ((Glyph)aFunction).getWidth();
		int height = ((Glyph)aFunction).getHeight();
		Bitmap bitmap = Bitmap.createBitmap(100, height*2, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);		
		paint.setColor(Color.WHITE);
//		canvas.drawRect(0, 0, width, height*2, paint);
		this.DrawFunc(canvas, aFunction, width/2,  height);
		
		
//		canvas.drawRect(0, 0, width, height, paint);
		
		int py = 0;
		boolean found = false;
		while(!found){
			for(int i = 0; i<=bitmap.getWidth()-1;i++){
				if(bitmap.getPixel(i, py)==Color.WHITE){
					found = true;
				}
			}
			py++;
		}
		
		int ky = bitmap.getHeight()-1;
		found = false;
		while(!found){
			for(int i = 0; i<=bitmap.getWidth()-1;i++){
				if(bitmap.getPixel(i, ky)==Color.WHITE){
					found = true;
				}
			}
			ky--;
		}
		
		
		int px = 0;
		found = false;
		while(!found){
			for(int i = 0; i<=bitmap.getHeight()-1;i++){
				if(bitmap.getPixel(px, i)==Color.WHITE){
					found = true;
				}
			}
			px++;
		}
		
		int kx = bitmap.getWidth()-1;
		found = false;
		while(!found){
			for(int i = 0; i<=bitmap.getHeight()-1;i++){
				if(bitmap.getPixel(kx, i)==Color.WHITE){
					found = true;
				}
			}
			kx--;
		}
		
		
		Log.d("asd","py: "+py+", ky: "+ky);
		Log.d("asd","px: "+px+", kx: "+kx);
		
		
		int finalHeight = 80;
		Bitmap bb = Bitmap.createBitmap(100, finalHeight, Bitmap.Config.ARGB_8888);
		Canvas cc = new Canvas(bb);
		int bWidth = kx-px;
		int bHeight = ky-py;
		
		cc.drawBitmap(bitmap, 50-bWidth/2-px, 40-bHeight/2-py, new Paint());
		
		
		
		return bb;
		
//		Bitmap bmap = Bitmap.createBitmap(bitmap, 0, py-1, width, ky-py+2);
//		bitmap.recycle();
//		
//		if(bmap.getHeight()>90){
//			bmap = Bitmap.createScaledBitmap(bmap, width, 90, true);
//			height = 90;
//		}
//		
//		if(bmap.getWidth()>90){
//			bmap = Bitmap.createScaledBitmap(bmap, 90, height, true);
//			width = 90;
//		}
//		
//		
//		Bitmap bmap2 = Bitmap.createBitmap(100, 100, Config.RGB_565);
//		Canvas canv = new Canvas(bmap2);
//		canv.drawRect(0, 0, 100, 100, mBackPaint);
//		canv.drawBitmap(bmap, 50-width/2, 50-height/2, mBmapPaint);
//		
//		return bmap2;
	}
	
	
	
	
	private void DrawFunc(Canvas aCanvas, ComplexFunction aF, int aX, int aY){

		if(aF.getClass().equals(ComplexDiv.class)){
			int width = ((Glyph)aF).getWidth();
			
			
			
			int height =((Glyph)((ComplexDiv)aF).getLHS()).getHeight();
			
			aY = aY - ((Glyph)aF).getHeight()/2 + height;
			
			
			DrawFunc(aCanvas, ((ComplexDiv)aF).getLHS(), aX, aY-height/2);
			height =((Glyph)((ComplexDiv)aF).getRHS()).getHeight(); 
			DrawFunc(aCanvas, ((ComplexDiv)aF).getRHS(), aX, aY+height/2);
			
			aCanvas.drawLine(aX-width/2, aY-7, aX+width/2, aY-7, mDivLine);
		}
		
		if(aF.getClass().equals(ComplexSum.class)){
			int plusWidth = (int) mTextPaint.measureText(" + ");
			int width = ((Glyph)aF).getWidth();
			int beg = aX-width/2 + ((Glyph)((ComplexSum)aF).getLHS()).getWidth()/2;
			int plus = aX-width/2 + ((Glyph)((ComplexSum)aF).getLHS()).getWidth();
			DrawFunc(aCanvas, ((ComplexSum)aF).getLHS(), beg, aY);
			
			aCanvas.drawText(" + ", plus, aY, mTextPaint);
			beg = aX+width/2 - ((Glyph)((ComplexSum)aF).getRHS()).getWidth()/2;		
			DrawFunc(aCanvas, ((ComplexSum)aF).getRHS(), beg, aY);
		}
		
		if(aF.getClass().equals(ComplexES.class)){
			String mText = ((ComplexES)aF).getMultiplier()+"s";
			int tH = Math.round(mTextPaint.measureText(mText));
			aCanvas.drawText(((ComplexES)aF).getMultiplier()+"s", aX-tH/2, aY, mTextPaint);
		}
		
		if(aF.getClass().equals(ComplexDiff.class)){
			int plusWidth = (int) mTextPaint.measureText(" - ");
			int width = ((Glyph)aF).getWidth();
			int beg = aX-width/2 + ((Glyph)((ComplexDiff)aF).getLHS()).getWidth()/2;
			int plus = aX-width/2 + ((Glyph)((ComplexDiff)aF).getLHS()).getWidth();
			DrawFunc(aCanvas, ((ComplexDiff)aF).getLHS(), beg, aY);
			
			aCanvas.drawText(" - ", plus, aY, mTextPaint);
			beg = aX+width/2 - ((Glyph)((ComplexDiff)aF).getRHS()).getWidth()/2;		
			DrawFunc(aCanvas, ((ComplexDiff)aF).getRHS(), beg, aY);
		}
		
		if(aF.getClass().equals(ComplexMult.class)){
			int width = ((Glyph)aF).getWidth();
			int beg = aX-width/2 + ((Glyph)((ComplexMult)aF).getLHS()).getWidth()/2;
			int plus = aX-width/2 + ((Glyph)((ComplexMult)aF).getLHS()).getWidth();
			DrawFunc(aCanvas, ((ComplexMult)aF).getLHS(), beg, aY);
			
			
			beg = aX+width/2 - ((Glyph)((ComplexMult)aF).getRHS()).getWidth()/2;		
			DrawFunc(aCanvas, ((ComplexMult)aF).getRHS(), beg, aY);
		}
		
		if(aF.getClass().equals(ComplexConstant.class)){
			String mText = ((ComplexConstant)aF).getConstant()+"";
			int tH = Math.round(mTextPaint.measureText(mText));
			aCanvas.drawText(((ComplexConstant)aF).getConstant()+"", aX-tH/2, aY, mTextPaint);
		}
		
		
		if(aF.getClass().equals(ComplexNull.class)){
			aCanvas.drawRect(aX-15, aY-23, aX+15, aY+10, mNullRect);
			this.mNullList.add(new NullSpace(aF, aX-15, aY-23, aX+15, aY+10));
//			Log.d("czyta","nuli: "+mNullList.size());
		}
		
		if(aF.getClass().equals(ComplexRound.class)){
			DrawFunc(aCanvas, ((ComplexRound)aF).getLHS(), aX, aY);	
			mRoundPaint.setColor(Color.WHITE);
			mRoundPaint.setTextSize(((Glyph) ((ComplexRound)aF).getLHS()).getHeight());
			int width = (int) (((Glyph) ((ComplexRound)aF).getLHS()).getWidth());
			int widthR = (int) mRoundPaint.measureText("(");
			int fY = (int) (aY + mRoundPaint.getTextSize()*mRoundPaint.getTextSize()*0.0018);
			aCanvas.drawText("(", aX-width/2-widthR, fY, mRoundPaint);
			aCanvas.drawText(")", aX+width/2, fY, mRoundPaint);
		}
		
	}
	
	
	
}
