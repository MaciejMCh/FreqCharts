package com.example.freqcharts;

import TransferFunctionLogic.ComplexConstant;
import TransferFunctionLogic.ComplexDiv;
import TransferFunctionLogic.ComplexES;
import TransferFunctionLogic.ComplexFunction;
import TransferFunctionLogic.ComplexRound;
import TransferFunctionLogic.ComplexSquare;
import TransferFunctionLogic.ComplexSum;
import android.util.Log;

public class TransferFunctionFactory {
	private static ComplexFunction mFunction;
	
	public static ComplexFunction getTransferFunction(){
		return mFunction;
	}
	
	public static void setTransferFunction(ComplexFunction aFunction){
		mFunction = aFunction;
	}
}
