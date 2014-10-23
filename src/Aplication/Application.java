package Aplication;


import com.example.freqcharts.DBAdapter;
import com.example.freqcharts.DBRecord;
import com.example.freqcharts.FuncDoneListener;
import com.example.freqcharts.FunctionBuilder;
import com.example.freqcharts.GraphFuncListener;
import com.example.freqcharts.MainActivity;

import GraphLogic.Impulse;
import GraphLogic.Magnitude;
import GraphLogic.Nyquist;
import GraphLogic.Phase;
import TransferFunctionLogic.ComplexConstant;
import TransferFunctionLogic.ComplexES;
import TransferFunctionLogic.ComplexFunction;
import TransferFunctionLogic.ComplexPower;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;



public class Application implements FuncDoneListener, OnItemClickListener, GraphFuncListener, FuncConstantsProvider, getExponentListener{
	
	private static Application mInstance;
	
	private FunctionProvider mFuncProvider;
	private MainActivity mActivity;
	private DBRecord mRecord;
	private int mDeleteID;

	private int mExponent = -1;

	private FunctionBuilder mBuilder;
	
	public static Application getInstance(){
		if(mInstance == null){
			mInstance = new Application();
		}
		return mInstance;
	}
	
	
	
	
	
	public void drawNyquist(ComplexFunction aFunction){
//		Nyquist nyq = new Nyquist(mFuncProvider.getFunction());	
//		this.startActivity(nyq.getIntent(mActivity));
		
		Impulse impulse = new Impulse(mFuncProvider.getFunction());
		this.startActivity(impulse.getIntent(mActivity));
	}
	public void drawMagnitude(ComplexFunction aFunction){
		Magnitude mag = new Magnitude(mFuncProvider.getFunction());
		this.startActivity(mag.getIntent(mActivity));
	}
	public void drawPhase(ComplexFunction aFunction){
		Phase pha = new Phase(mFuncProvider.getFunction());
		this.startActivity(pha.getIntent(mActivity));
	}
	
	private void startActivity(Intent aIntent){
		mActivity.startActivity(aIntent);
	}
	
	public void setFuncProvider(FunctionProvider mFuncProvider) {
		this.mFuncProvider = mFuncProvider;
	}
	
	public void setActivity(MainActivity aActivity){
		mActivity = aActivity;
	}
	
	@Override
	public void onFuncDone(ComplexFunction aFunction){
		mRecord = new DBRecord(aFunction, null, 0);
		mActivity.pickName();
	}
	
	public void onNamePicked(String aName){
		mRecord.setName(aName);
		DBAdapter db = new DBAdapter(mActivity);
		db.open();
		db.insertTodo(mRecord);
		db.close();
	}





	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		this.mFuncProvider = new SimpleFuncProvider(((DBRecord)parent.getItemAtPosition(position)).getFunction());
		Log.d("pacz",parent.getItemAtPosition(position).toString());
		mActivity.ChooseChart();		
	}





	@Override
	public void onGraphFunc(ComplexFunction aFunction) {
		this.mFuncProvider = new SimpleFuncProvider(aFunction);
		mActivity.ChooseChart();
	}
	
	public void confirmDelete(int id) {
		mDeleteID = id;
		mActivity.confirmDelete();
	}
	
	public void deleteSaved() {
		DBAdapter db = new DBAdapter(mActivity);
		db.open();
		db.deleteTodo(mDeleteID);
		db.close();
		mActivity.refreshSaved();
	}








	@Override
	public void onPickedExponent(int aExp) {
		this.mExponent  = aExp;
	}
	
	public void setExponent(int aExp){
		this.mExponent = aExp;
	}





	@Override
	public void init(ComplexFunction mNewFunc) {
		if(mNewFunc.getClass().equals(ComplexPower.class)){
			mActivity.pickExponent((ComplexPower)mNewFunc);
		}
		if(mNewFunc.getClass().equals(ComplexConstant.class)){
			mActivity.pickConstant((ComplexConstant)mNewFunc);
		}
		if(mNewFunc.getClass().equals(ComplexES.class)){
			mActivity.pickMultiplier((ComplexES)mNewFunc);
		}
	}
	
	public void setBuilder(FunctionBuilder aBuilder){
		mBuilder = aBuilder;
	}
	
	public void refreshBuilder() {
		this.mBuilder.refresh();
	}

}
