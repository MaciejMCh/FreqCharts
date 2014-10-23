package com.example.freqcharts;

import java.util.ArrayList;
import java.util.zip.Inflater;

import Aplication.MyFuncMini;
import TransferFunctionLogic.ComplexFunction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FuncListAdapter extends BaseAdapter {

	private static Bitmap mBackground;
	private static Paint mTextPaint;
	
	private ArrayList<DBRecord> mFuncList;
	private Context mContext;
	private Bitmap mFrontground;
	
	public FuncListAdapter(Context aContext, ArrayList<DBRecord> list) {
		super();
		this.mContext = aContext;
		this.mFuncList = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFuncList.size();
	}

	@Override
	public Object getItem(int position) {
		return mFuncList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
//			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			convertView = inflater.inflate(R.layout.grid_item, null);
//			((ImageView) convertView.findViewById(R.id.imageView1)).setImageBitmap(FunctionRenderer.getInstance().renderFunction(mFuncList.get(position).getFunction()));
//			((TextView)convertView.findViewById(R.id.textView1)).setText(mFuncList.get(position).getName());
//			convertView = new LinearLayout(mContext);
//			((LinearLayout)convertView).addView(new MyFuncMini(mContext));
//			TextView te = new TextView(mContext);
//			te.setText("huuj");
//			((LinearLayout)convertView).addView(te);
					
			convertView = new ImageView(mContext);
			
			if(mBackground == null) prepareBackground();
			if(mFrontground == null) prepareFrontGround();
			if(mTextPaint == null) preparePaint();
			
			Bitmap bb = Bitmap.createBitmap(mBackground.getWidth(), mBackground.getHeight(), Bitmap.Config.ARGB_8888);
			Canvas cc = new Canvas(bb);
			cc.drawBitmap(mBackground, 0, 0, new Paint());
			
			String ss = mFuncList.get(position).getName();
			float tp = mTextPaint.measureText("...");
			if(mTextPaint.measureText(ss)>90){
				while(mTextPaint.measureText(ss)+tp>90){
					ss = ss.substring(0,ss.length()-1);
				}
				ss+="...";
			}
			
			
			float mid = 50-mTextPaint.measureText(ss)/2;
			
			cc.drawText(ss, mid, 90, mTextPaint);
			
			Bitmap bbb = FunctionRenderer.getInstance().renderFunction(mFuncList.get(position).getFunction());
			

//			Log.d("ddd", bbb.getWidth()+","+bbb.getHeight()+"  "+aa.getWidth()+"+"+aa.getHeight());
//			Bitmap bbbb = ApplyAlphaMask.addMask(bbb, aa);
			cc.drawBitmap(bbb, 0, 0, new Paint());
			
			
			BitmapFactory.Options options = new BitmapFactory.Options();  
			options.inPreferredConfig = Bitmap.Config.ARGB_8888; 
			
			
			Bitmap bbp = Bitmap.createBitmap(110, 125, Config.ARGB_8888);
			Canvas c = new Canvas(bbp);
			c.drawBitmap(bb, 0, 15, new Paint());
			c.drawBitmap(mFrontground, 0, 15, new Paint());
			
			((ImageView)convertView).setImageBitmap(bbp);
		}
		
		return convertView;
	}

	private void prepareFrontGround() {
		mFrontground = ResourceToBitmap.getBitmap(mContext, R.drawable.saved_item_front);
	}

	private void preparePaint() {
		mTextPaint = new Paint();
		mTextPaint.setTextSize(16);
		mTextPaint.setColor(Color.WHITE);
	}

	private void prepareBackground() {
		Bitmap bm = ResourceToBitmap.getBitmap(mContext, R.drawable.saved_bitmap);
		Bitmap am = ResourceToBitmap.getBitmap(mContext, R.drawable.saved_mask);
//		mBackground = ApplyAlphaMask.addMask(bm, am);
		BitmapFactory.Options options = new BitmapFactory.Options();  
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		mBackground = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.saved_item, options);
	}

}
