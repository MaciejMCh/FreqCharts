package com.example.freqcharts;


import java.util.ArrayList;

import Aplication.FuncConstantsProvider;
import TransferFunctionLogic.ComplexConstant;
import TransferFunctionLogic.ComplexDiff;
import TransferFunctionLogic.ComplexDiv;
import TransferFunctionLogic.ComplexES;
import TransferFunctionLogic.ComplexFunction;
import TransferFunctionLogic.ComplexMult;
import TransferFunctionLogic.ComplexNull;
import TransferFunctionLogic.ComplexPower;
import TransferFunctionLogic.ComplexRound;
import TransferFunctionLogic.ComplexSum;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;




public class FunctionBuilder extends View implements ComplexFuncFactory {

	private ArrayList<FuncDoneListener> mListeners;
	private ArrayList<String> mOperationStack;
	private ComplexFunction mFunction;
	private Paint mRoundPaint;
	private Paint mTextPaint;
	private Paint mIcon;
	private Paint mNullRect;
	private Paint mDivLine;
	private int mTextHeight = 20;
	private Bitmap mSumBitmap, mDivBitmap, mMultBitmap, mESBitmap, mConstBitmap, mRoundBitmap;
	private Bitmap mDragBitmap, mSquareBitmap, mUndoBitmap, mDeleteBitmap, mSaveBitmap, mGraphBitmap;
	private boolean mDragging;
	private ArrayList<NullSpace> mNullList;
	private ComplexFunction mNewFunc;
	private int mIconSize = 40;
	private int mIconSpacing = 10;
	private int mTX, mTY;
	private ArrayList<Bitmap> mToolBarBitmaps;
	protected ArrayList<Bitmap> mNavBarBitmaps;
	protected ArrayList<Bitmap> mNavBarRegBM;
	protected ArrayList<Bitmap> mNavBarActBM;
	
	
	private ToolBarItem[] mToolBarItems;
	private ToolBarItem[] mNavBarItems;
	
	protected int mX = 100;
	protected int mY = 100;
	private boolean mEmpty;
	protected int mW;
	protected int mH;
	private ArrayList<GraphFuncListener> mGraphListeners;
	private int mButtonPressed;
	private FuncConstantsProvider mConstantsProvider;
	private Context mContext;
	
	public FunctionBuilder(Context context) {
		super(context);
		
		
		this.mContext = context;
		this.mButtonPressed = -1;
		this.mDragging = false;
		this.mEmpty = true;
		mNullList = new ArrayList<NullSpace>();	
		mOperationStack = new ArrayList<String>();
		mListeners = new ArrayList<FuncDoneListener>();
		mGraphListeners = new ArrayList<GraphFuncListener>();
		prepareBitmaps(context);
		this.Init();		
	}

	protected int[] getNavbarCoords() {
		int[] aC = new int[8];
		
		aC[0] = mW-40;
		aC[1] = mW-40;
		aC[2] = mW-40;
		aC[3] = mW-40;
		aC[4] = 0;
		aC[5] = 50;
		aC[6] = 100;
		aC[7] = 150;
		return aC;
	}

	protected int[] getToolbarCoords() {
		int aC[] = new int[16];
		
		aC[0] = 0;
		aC[1] = 0;
		aC[2] = 0;
		aC[3] = 0;
		aC[4] = 0;
		aC[5] = 0;
		aC[6] = 0;
		aC[7] = 0;
		
		aC[8] = 0;
		aC[9] = 40;
		aC[10] = 80;
		aC[11] = 120;
		aC[12] = 160;
		aC[13] = 200;
		aC[14] = 240;
		aC[15] = 280;
		
		return aC;
	}

	

	private void prepareBitmaps(Context context) {
//		mSumBitmap = ResourceToBitmap.getBitmap(context, R.drawable.sum2_icon);
		mDivBitmap = ResourceToBitmap.getBitmap(context, R.drawable.div_icon);
		mMultBitmap = ResourceToBitmap.getBitmap(context, R.drawable.mult_icon);
		mESBitmap = ResourceToBitmap.getBitmap(context, R.drawable.es_icon);
		mConstBitmap = ResourceToBitmap.getBitmap(context, R.drawable.const_icon);
		mRoundBitmap = ResourceToBitmap.getBitmap(context, R.drawable.round_icon);
		mDeleteBitmap = ResourceToBitmap.getBitmap(context, R.drawable.delete_icon);
		mUndoBitmap = ResourceToBitmap.getBitmap(context, R.drawable.undo_icon);
//		mSquareBitmap = ResourceToBitmap.getBitmap(context, R.drawable.square_icon);
		mSaveBitmap = ResourceToBitmap.getBitmap(context, R.drawable.save_icon);
		mGraphBitmap = ResourceToBitmap.getBitmap(context, R.drawable.graph_icon);
		this.mToolBarBitmaps = new ArrayList<Bitmap>();
		mToolBarBitmaps.add(ResourceToBitmap.getBitmap(context, R.drawable.const_icon));
		mToolBarBitmaps.add(ResourceToBitmap.getBitmap(context, R.drawable.es_icon));
		mToolBarBitmaps.add(ResourceToBitmap.getBitmap(context, R.drawable.plus_icon));
		mToolBarBitmaps.add(ResourceToBitmap.getBitmap(context, R.drawable.minus_icon));
		mToolBarBitmaps.add(ResourceToBitmap.getBitmap(context, R.drawable.mult_icon));
		mToolBarBitmaps.add(ResourceToBitmap.getBitmap(context, R.drawable.div_icon));
		mToolBarBitmaps.add(ResourceToBitmap.getBitmap(context, R.drawable.pow_icon));
		mToolBarBitmaps.add(ResourceToBitmap.getBitmap(context, R.drawable.round_icon));
		
		this.mNavBarBitmaps = new ArrayList<Bitmap>();
		this.mNavBarActBM = new ArrayList<Bitmap>();
		this.mNavBarRegBM = new ArrayList<Bitmap>();
		
		mNavBarActBM.add(ResourceToBitmap.getBitmap(context, R.drawable.chart_act));
		mNavBarActBM.add(ResourceToBitmap.getBitmap(context, R.drawable.save_act));
		mNavBarActBM.add(ResourceToBitmap.getBitmap(context, R.drawable.undo_act));
		mNavBarActBM.add(ResourceToBitmap.getBitmap(context, R.drawable.discard_act));
		
		mNavBarRegBM.add(ResourceToBitmap.getBitmap(context, R.drawable.chart_reg));
		mNavBarRegBM.add(ResourceToBitmap.getBitmap(context, R.drawable.save_reg));
		mNavBarRegBM.add(ResourceToBitmap.getBitmap(context, R.drawable.undo_reg));
		mNavBarRegBM.add(ResourceToBitmap.getBitmap(context, R.drawable.discard_reg));
		
		mNavBarBitmaps.add(mNavBarRegBM.get(0));
		mNavBarBitmaps.add(mNavBarRegBM.get(1));
		mNavBarBitmaps.add(mNavBarRegBM.get(2));
		mNavBarBitmaps.add(mNavBarRegBM.get(3));
		
		
	}
	
	private void Init() {
		
		mNullRect = new Paint(Paint.ANTI_ALIAS_FLAG);
		
		mNullRect.setColor(Color.GRAY);
		
		mDivLine = new Paint(Paint.ANTI_ALIAS_FLAG);
		mDivLine.setColor(Color.BLACK);
		
		 mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//		   mTextPaint.setColor(Color.BLACK);
//		   if (mTextHeight == 0) {
//		       mTextHeight = (int) mTextPaint.getTextSize();
//		   } else {
//		       mTextPaint.setTextSize(mTextHeight);
//		   }
		 
		 mTextPaint.setTextSize(mTextHeight);

		   Paint mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		   mPiePaint.setStyle(Paint.Style.FILL);
		   mPiePaint.setTextSize(mTextHeight);

		   Paint mShadowPaint = new Paint(0);
		   mShadowPaint.setColor(0xff101010);
		   mShadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
		   
		   mIcon = new Paint(Paint.ANTI_ALIAS_FLAG);
		   mRoundPaint = PaintFactory.getInstance().getRoundPaint();
//		   mRoundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		
	}

	protected void onSizeChanged (int w, int h, int oldw, int oldh){
		Log.d("liscik","rozmiar: "+w+" x "+h);
		mX = w/2;
		mY = h/2;
		mW = w;
		mH = h;
		

		int[] crd = this.getToolbarCoords();
		this.setToolbarItems(new int[]{crd[0],  crd[1], crd[2], crd[3], crd[4], crd[5], crd[6], crd[7]}, new int[]{crd[8], crd[9], crd[10], crd[11], crd[12], crd[13], crd[14], crd[15]});
		crd = this.getNavbarCoords();
		this.setNavbarItems(new int[]{crd[0], crd[1], crd[2], crd[3]}, new int[]{crd[4], crd[5], crd[6], crd[7]});
		
		
	}
	
	protected void onDraw(Canvas canvas){
//		canvas.drawText("www", 10, 10, mTextPaint);
		
		//canvas.drawRect(mX, mY, mX+30, mY+45, mNullRect);
		
		this.drawBackGround(canvas);
		mNullList = new ArrayList<NullSpace>();
		if(!mEmpty)this.DrawFunc(canvas, mX,mY);		
		this.drawToolbar(canvas);
		this.drawNavigationbar(canvas);
		if(mDragging) canvas.drawBitmap(mDragBitmap, mTX-mIconSize/2, mTY-mIconSize/2, mIcon);
		
		
		
		
		
	}

	protected void drawBackGround(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

	protected void drawNavigationbar(Canvas canvas) {
		canvas.drawBitmap(mDeleteBitmap, mW-mIconSize, 0, mIcon);
		canvas.drawBitmap(mUndoBitmap, mW-mIconSize, mIconSize+mIconSpacing, mIcon);
		canvas.drawBitmap(mSaveBitmap, mW-mIconSize, (mIconSize+mIconSpacing)*2, mIcon);
		canvas.drawBitmap(mGraphBitmap, mW-mIconSize, (mIconSize+mIconSpacing)*3, mIcon);
	}

	protected void drawToolbar(Canvas canvas) {
		canvas.drawBitmap(mConstBitmap, 0, 0, mIcon);
		canvas.drawBitmap(mESBitmap, 0, mIconSize+mIconSpacing, mIcon);
		canvas.drawBitmap(mDivBitmap, 0, (mIconSize+mIconSpacing)*2, mIcon);
		canvas.drawBitmap(mMultBitmap, 0, (mIconSize+mIconSpacing)*3, mIcon);
		canvas.drawBitmap(mSumBitmap, 0, (mIconSize+mIconSpacing)*4, mIcon);
		canvas.drawBitmap(mRoundBitmap, 0, (mIconSize+mIconSpacing)*5, mIcon);
	}

	private void DrawFunc(Canvas aCanvas, int aX, int aY) {
		DrawFunc(aCanvas, mFunction, aX, aY);
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
		
		if(aF.getClass().equals(ComplexMult.class)){
			int width = ((Glyph)aF).getWidth();
			int beg = aX-width/2 + ((Glyph)((ComplexMult)aF).getLHS()).getWidth()/2;
			int plus = aX-width/2 + ((Glyph)((ComplexMult)aF).getLHS()).getWidth();
			DrawFunc(aCanvas, ((ComplexMult)aF).getLHS(), beg, aY);
			
			
			beg = aX+width/2 - ((Glyph)((ComplexMult)aF).getRHS()).getWidth()/2;		
			DrawFunc(aCanvas, ((ComplexMult)aF).getRHS(), beg, aY);
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
		
		if(aF.getClass().equals(ComplexES.class)){
			if(((ComplexES)aF).getMultiplier()!=0){
//				String mText = ((ComplexES)aF).getMultiplier()+"s";
				String mText = ((ComplexES)aF).getDisplay(); 
				int tH = Math.round(mTextPaint.measureText(mText));
//				aCanvas.drawText(((ComplexES)aF).getMultiplier()+"s", aX-tH/2, aY, mTextPaint);
				aCanvas.drawText(mText, aX-tH/2, aY, mTextPaint);
			}
		}
		
		if(aF.getClass().equals(ComplexConstant.class)){
			if(((ComplexConstant)aF).getConstant()!=0){
				String mText;
				mText = ((ComplexConstant)aF).getDisplay();
				int tH = Math.round(mTextPaint.measureText(mText));
//				aCanvas.drawText(((ComplexConstant)aF).getConstant()+"", aX-tH/2, aY, mTextPaint);
				aCanvas.drawText(mText, aX-tH/2, aY, mTextPaint);
			}
		}
		
		
		if(aF.getClass().equals(ComplexNull.class)){
			aCanvas.drawRect(aX-15, aY-23, aX+15, aY+10, mNullRect);
			this.mNullList.add(new NullSpace(aF, aX-15, aY-23, aX+15, aY+10));
//			Log.d("czyta","nuli: "+mNullList.size());
		}
		
		if(aF.getClass().equals(ComplexRound.class)){
			DrawFunc(aCanvas, ((ComplexRound)aF).getLHS(), aX, aY);	
			mRoundPaint.setColor(Color.BLACK);
			mRoundPaint.setTextSize(((Glyph) ((ComplexRound)aF).getLHS()).getHeight());
			int width = (int) (((Glyph) ((ComplexRound)aF).getLHS()).getWidth());
			int widthR = (int) mRoundPaint.measureText("(");
			int fY = (int) (aY + mRoundPaint.getTextSize()*mRoundPaint.getTextSize()*0.0018);
			aCanvas.drawText("(", aX-width/2-widthR, fY, mRoundPaint);
			aCanvas.drawText(")", aX+width/2, fY, mRoundPaint);
		}
		
//		if(aF.getClass().equals(ComplexRound.class)){
//			//mRoundPaint.setTextSize(((Glyph) ((ComplexRound)aF).getLHS()).getHeight());
//			DrawFunc(aCanvas, ((ComplexRound)aF).getLHS(), aX, aY);
//		}
		
		if(aF.getClass().equals(ComplexPower.class)){
			if(((ComplexPower)aF).getExp()!=0){
				DrawFunc(aCanvas, ((ComplexPower)aF).getLHS(), aX, aY);
				int width = (int) (((Glyph) ((ComplexPower)aF).getLHS()).getWidth());
				int height = (int) (((Glyph) ((ComplexPower)aF).getLHS()).getHeight());
				aCanvas.drawText(((ComplexPower)aF).getExp()+"", aX+width/2, aY-height/2, PaintFactory.getInstance().getExpPaint());
			}
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {        	
		
		int action = e.getAction();
		
		if(action==MotionEvent.ACTION_MOVE){
			mTX = (int) e.getX();
			mTY = (int) e.getY();
			this.invalidate();
			
		}else if(action==MotionEvent.ACTION_DOWN){
			
			int x = (int) e.getX();
			int y = (int) e.getY();
			
			int c = -1;
			for(int i = 0; i<=7; i++){
				if(mToolBarItems[i].intersects(x, y, 40)){
					c=i;
					break;
				}
			}
			Log.d("baza", c+"");
				
			if(c!=-1){
				mDragBitmap = mToolBarBitmaps.get(c);
				if(c == 0){					
					mNewFunc = new ComplexConstant(0);
				}else if(c == 1){					
					mNewFunc = new ComplexES(0);			
				}else if(c == 2){
					mNewFunc = new ComplexSum();
				}else if(c == 3){
					mNewFunc = new ComplexDiff();
				}else if(c == 4){
					mNewFunc = new ComplexMult();
				}else if(c == 5){
					mNewFunc = new ComplexDiv();
				}else if(c == 6){
						mNewFunc = new ComplexPower(0);
				}else if(c == 7){
					mNewFunc = new ComplexRound();
				}
				mTX = x;
				mTY = y;
				mDragging = true;	
				}
				
				c=-1;
				for(int i = 0; i<=3; i++){
					if(mNavBarItems[i].intersects(x, y, 72)){
						c=i;
						break;
					}
				}
				if(c!=-1){
					mNavBarBitmaps.set(c, mNavBarActBM.get(c));
					mButtonPressed = c;
				}
			
			
		}else if(action==MotionEvent.ACTION_UP){
			int x = (int) e.getX();
			int y = (int) e.getY();
			
			if(mButtonPressed!=-1){
				mNavBarBitmaps.set(mButtonPressed, mNavBarRegBM.get(mButtonPressed));
			}
			if(mDragging){
				if(mEmpty){
					
					
					mConstantsProvider.init(mNewFunc);
						
					mEmpty = false;
					this.mFunction = mNewFunc;
					TransferFunctionFactory.setTransferFunction(mFunction);
					
				}
				for(int i=0; i<=mNullList.size()-1; i++){
					Log.d("czyta","int: "+ mNullList.get(i).intersects((int)e.getX(), (int)e.getY()));
					if(mNullList.get(i).intersects((int)e.getX(), (int)e.getY())){
						mOperationStack.add(mFunction.toString());
						mConstantsProvider.init(mNewFunc);
						mNullList.get(i).setFunc(mNewFunc);						
					}
				}
				Log.d("rozmiar", "func: "+mFunction.toString());
				Log.d("rozmiar", "pars: "+ComplexFunctionParser.parseFunction(mFunction.toString()).toString());
				mDragging = false;
				this.invalidate();
			}
			
			int c = -1;
			for(int i = 0; i<=3; i++){
				if(mNavBarItems[i].intersects(x, y, 72)){
					c=i;
					break;
				}
			}
			if(c==0){
				this.GraphFunc();
			}else if(c==1){
				this.FuncDone();
			}else if(c==2){
				if(mOperationStack.size() <= 0){
					mEmpty = true;
					this.invalidate();
					this.mOperationStack = new ArrayList<String>();
				}else{
					this.mFunction = ComplexFunctionParser.parseFunction(mOperationStack.get(mOperationStack.size()-1));
					this.mOperationStack.remove(mOperationStack.size()-1);
					this.invalidate();
				}
			}else if(c==3){
				mEmpty = true;
				this.invalidate();
				this.mOperationStack = new ArrayList<String>();
			}
		}
		
		return true;
    }
	
	private void GraphFunc() {
		if(mGraphListeners.size()!=0){
			for(int i = 0; i<=mGraphListeners.size()-1; i++){
				mGraphListeners.get(i).onGraphFunc(mFunction);
			}
		}
	}

	public Bitmap RenderFunction(ComplexFunction aFunction){
		int width = ((Glyph)aFunction).getWidth();
		int height = ((Glyph)aFunction).getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height*2, Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);		
		paint.setColor(Color.WHITE);
		canvas.drawRect(0, 0, width, height*2, paint);
		this.DrawFunc(canvas, aFunction, width/2,  height);
//		canvas.drawRect(0, 0, width, height, paint);
		
		int py = 0;
		boolean found = false;
		while(!found){
			for(int i = 0; i<=bitmap.getWidth()-1;i++){
				if(bitmap.getPixel(i, py)==Color.WHITE){
				}else{
					found = true;
				}
			}
			py++;
		}
		Log.d("asd","p: "+py);
		int ky = bitmap.getHeight()-1;
		found = false;
		while(!found){
			for(int i = 0; i<=bitmap.getWidth()-1;i++){
				if(bitmap.getPixel(i, ky)==Color.WHITE){
				}else{
					found = true;
				}
			}
			ky--;
		}
		Log.d("asd","k: "+ky);
		
		Bitmap bmap = Bitmap.createBitmap(bitmap, 0, py-1, width, ky-py+2);
		bitmap.recycle();
		return bmap;
	}

	@Override
	public ComplexFunction getFunc() {
		return mFunction;
	}
	
	public void FuncDone(){
		if(mListeners.size()>0){
			for(int i = 0; i<=mListeners.size()-1; i++){
				mListeners.get(i).onFuncDone(mFunction);
			}
		}
	}
	
	public void addFuncDoneListener(FuncDoneListener aListener){
		this.mListeners.add(aListener);
	}
	
	public void addGraphFuncListener(GraphFuncListener aListener){
		this.mGraphListeners.add(aListener);
	}
	
	
	protected void setToolbarItems(int[] aX, int[] aY){
		mToolBarItems = new ToolBarItem[8];
		for(int i = 0; i<=7; i++){
			mToolBarItems[i] = new ToolBarItem(aX[i], aY[i]);
		}		
	}
	
	protected void setNavbarItems(int[] aX, int[] aY){
		mNavBarItems = new ToolBarItem[4];
		for(int i = 0; i<=3; i++){
			mNavBarItems[i] = new ToolBarItem(aX[i], aY[i]);
		}		
	}
	
	
	private class ToolBarItem{
		private int mX, mY;
		public ToolBarItem(int aX, int aY){
			mX = aX;
			mY = aY;
		}
		public boolean intersects(int aX, int aY, int aSize){
			Log.d("baza", aX+","+aY+"    "+mX+","+mY);
			if((aX>mX)&&(aY>mY)&&(aX<mX+aSize)&&(aY<mY+aSize))return true;
			return false;
		}		
	}
	
	public void setConstantProvider(FuncConstantsProvider aProvider) {
		this.mConstantsProvider = aProvider;
	}

	public void initPower(int aExp) {
		mEmpty = false;
		this.mFunction = new ComplexPower(aExp);
		TransferFunctionFactory.setTransferFunction(mFunction);
		this.invalidate();
	}

	public void refresh() {
		this.invalidate();		
	}
	
	
	
}
