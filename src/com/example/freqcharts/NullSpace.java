package com.example.freqcharts;

import android.util.Log;
import TransferFunctionLogic.ComplexES;
import TransferFunctionLogic.ComplexFunction;
import TransferFunctionLogic.ComplexNull;
import TransferFunctionLogic.ComplexSum;

public class NullSpace {
	
	private ComplexFunction mFunc;
	private int mL, mT, mR, mB;
	
	public NullSpace(ComplexFunction aFunc, int aL, int aT, int aR, int aB){
		this.mFunc = aFunc;
		this.mL = aL;
		this.mT = aT;
		this.mR = aR;
		this.mB = aB;
	}
	
	public boolean intersects(int aX, int aY){
//		Log.d("czyta","int");
		if((aX>mL)&&(aX<mR)&&(aY>mT)&&(aY<mB))return true;
		return false;
	}
	
	public void setFunc(ComplexFunction aFunc){
		((ComplexNull)mFunc).fill(aFunc);
	}
	
}
