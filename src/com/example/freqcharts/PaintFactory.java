package com.example.freqcharts;

import android.graphics.Paint;

public class PaintFactory {
	
	private static PaintFactory mInstance = null;
	
	private int mTextHeight;
	private Paint mTextPaint;
	private Paint mRoundPaint;
	private Paint mExponentPaint;
	
	public PaintFactory(){
		mTextHeight = 20;
		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setTextSize(mTextHeight);
		
		mRoundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		
		mExponentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mExponentPaint.setTextSize((float) (mTextHeight/1.5));
	}
	
	public static PaintFactory getInstance(){
		if(mInstance == null){
			mInstance = new PaintFactory();
		}
		return mInstance;
	}
	
	public int getTextHeight(){
		return this.mTextHeight;
	}
	
	public int getTextWidth(String aText){
		return Math.round(mTextPaint.measureText(aText));
	}
	
	public Paint getTextPaint(){
		return this.mTextPaint;
	}

	public Paint getRoundPaint() {
		return mRoundPaint;
	}

	public int getRoundWidth() {
		return (int) mRoundPaint.measureText("(")*2;
	}

	public int getExponentWidth(int mExp) {
		return (int) mExponentPaint.measureText(""+mExp);
	}

	public Paint getExpPaint() {
		return this.mExponentPaint;
	}

}
