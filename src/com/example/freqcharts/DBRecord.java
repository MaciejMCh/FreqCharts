package com.example.freqcharts;

import TransferFunctionLogic.ComplexFunction;

public class DBRecord {
	private ComplexFunction mFunction;
	private String mName;
	private int mID;
	
	public DBRecord(ComplexFunction aFunction, String aName, int aID){
		mFunction = aFunction;
		mName = aName;
		mID = aID;
	}
	
	public ComplexFunction getFunction() {
		return mFunction;
	}
	public void setFunction(ComplexFunction mFunction) {
		this.mFunction = mFunction;
	}
	public String getName() {
		return mName;
	}
	public void setName(String mName) {
		this.mName = mName;
	}
	public int getID() {
		return mID;
	}
	public void setID(int mID) {
		this.mID = mID;
	}
}
