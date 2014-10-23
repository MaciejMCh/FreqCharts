package com.example.freqcharts;


import java.util.ArrayList;

import com.example.freqcharts.R;
import com.example.freqcharts.R.id;
import com.example.freqcharts.R.layout;
import com.example.freqcharts.R.menu;

import Aplication.Application;

import Aplication.MyFuncBuilder;
import TransferFunctionLogic.ComplexConstant;
import TransferFunctionLogic.ComplexDiv;
import TransferFunctionLogic.ComplexES;
import TransferFunctionLogic.ComplexFunction;
import TransferFunctionLogic.ComplexPower;
import TransferFunctionLogic.ComplexSum;
import View.ChooseChartFragment;
import View.ConfirmDeleteDialogFragment;
import View.FuncBuilderFragment;
import View.PickConstantDialogFragment;
import View.PickExponentDialogFragment;
import View.PickMultiplerDialogFragment;
import View.PickNameDialogFragment;
import View.SavedFuncFragment;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.os.Build;

import android.view.View.*;
import android.app.ActionBar;
public class MainActivity extends Activity {

	Application mApplication;
	private MyFuncBuilder mFunctionBuilder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		prepareActionBar();


		String c = Integer.toBinaryString(Color.rgb(50, 100, 200));
		
		int r = Integer.parseInt(c.substring(8,15), 2);
		int g = Integer.parseInt(c.substring(16,23), 2);
		int b = Integer.parseInt(c.substring(24,32), 2);
		
		
		Log.d("flapi",c+" "+ r+" "+g+" "+b);
		
		mApplication = Application.getInstance();
		Application.getInstance().setActivity(this);

//		prepareViews();
		
//		test();
		
		
		
	}

	private void prepareActionBar() {
		ActionBar actionBar = this.getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionBar.addTab(actionBar.newTab()
                .setText("New")
                .setTabListener(new ActionBar.TabListener() {
					
					@Override
					public void onTabUnselected(Tab tab, FragmentTransaction ft) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onTabSelected(Tab tab, FragmentTransaction ft) {
						ft.replace(R.id.main_container, new FuncBuilderFragment());						
					}
					
					@Override
					public void onTabReselected(Tab tab, FragmentTransaction ft) {
						// TODO Auto-generated method stub
						
					}
				}));
		actionBar.addTab(actionBar.newTab()
				.setText("Saved")
				.setTabListener(new ActionBar.TabListener() {
			
					@Override
					public void onTabUnselected(Tab tab, FragmentTransaction ft) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onTabSelected(Tab tab, FragmentTransaction ft) {
						ft.replace(R.id.main_container, new SavedFuncFragment());						
					}
					
					@Override
					public void onTabReselected(Tab tab, FragmentTransaction ft) {
						// TODO Auto-generated method stub
						
					}
		}));
	}

	private void test() {
		DBAdapter ad = new DBAdapter(this);
		ad.open();
		
		ad.insertTodo(new DBRecord(new ComplexSum(), "inercja", 0));
		
		Cursor cursor = ad.getAllTodos();
		Log.d("baza","ile: " + cursor.getCount());
		
		ad.close();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		
		return false;		
	}

	public void pickName() {
		new PickNameDialogFragment().show(getFragmentManager(), "s");
	}

	public void ChooseChart() {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.setCustomAnimations(R.anim.gla_there_come, R.anim.gla_there_gone);
		fragmentTransaction.replace(R.id.main_container, new ChooseChartFragment());
		fragmentTransaction.addToBackStack("stringname");
		fragmentTransaction.commit();
	}

	public void confirmDelete() {
		new ConfirmDeleteDialogFragment().show(getFragmentManager(), "tag");
	}

	public void refreshSaved() {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.main_container, new SavedFuncFragment());
		fragmentTransaction.commit();		
	}

	public void pickExponent(ComplexPower aPower) {
		new PickExponentDialogFragment().setPower(aPower).show(getFragmentManager(), "s");
	}

	public void pickConstant(ComplexConstant aConstant) {
		new PickConstantDialogFragment().setConstant(aConstant).show(getFragmentManager(), "s");
	}

	public void pickMultiplier(ComplexES mNewFunc) {
		new PickMultiplerDialogFragment().setES(mNewFunc).show(getFragmentManager(), "s");		
	}

	
	
	



}
