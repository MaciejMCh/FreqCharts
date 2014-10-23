package GraphLogic;

import org.achartengine.ChartFactory;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.example.freqcharts.FrequencyChart;

import TransferFunctionLogic.Complex;
import TransferFunctionLogic.ComplexFunction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

public class Nyquist implements FrequencyChart {
	
	private ComplexFunction mFunction;
	
	public Nyquist(ComplexFunction aFunction) {
		mFunction = aFunction;
	}

	public Intent getIntent(Context context){
		
		int x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int y[] = {1, 6, 9, 8, 5, 1, 2, 3 ,0, 15};
		
		TimeSeries timeSeries = new TimeSeries("line1");
		
//		int n = 0;
//		int il = 100;
//		
//		
//		float diff = 10.0f;
//		float step = 0.0000001f;
//		float infinity = step;
//		
//		while(Math.abs(diff)>step){
//			diff = (float) (mFunction.getResult(infinity*1000000).re() - mFunction.getResult(infinity).re());
//			Log.d("baza","INFS: "+diff);
//			infinity*=10;
//		}
//		Log.d("dostawa","ZERO: "+mFunction.getResult(0).re() + 
//				"\nINFINITY: " + mFunction.getResult(infinity).re() +
//				"\nSTEP: " + (mFunction.getResult(0).re()-mFunction.getResult(infinity).re())/il
//				);
//		
//		
//		
//		
//		
//		
		float darg = 0.0001f;
		float arg = darg;
//		float freq = 0.01f;
//		float dfreq = (float) ((mFunction.getResult(0).re()-mFunction.getResult(infinity).re())/il);
//		float prevx = (float) mFunction.getResult(0).re();
//
////		timeSeries.add(mFunction.getResult(0).re(), mFunction.getResult(0).im());
//		
//		
//		
//		
//		
		Complex complex = mFunction.getResult(arg);
		
		int pointCnt = 100;
//		float bX = (float) mFunction.getResult(0).re();
//		float eX = (float) mFunction.getResult(infinity).re();
//		float dX = (float) (eX - bX)/pointCnt;
		
		float argFreq = 1;
		float dFreq = 1.1f;
		
		float n = 0;
//		float pX = bX;
		
		
		argFreq = (float) (1/Math.pow(dFreq, pointCnt/2));
		Log.d("rozmiar", "PC: "+argFreq);
		while(n<pointCnt){
			complex = mFunction.getResult(argFreq);
			timeSeries.add(complex.re(), complex.im());
			argFreq = argFreq * dFreq;
			n++;
		}
		
		

		
//		while(n<=il-2){
//			Log.d("produkt", "arg: " + arg);
//		complex = mFunction.getResult(arg);
//			if(Math.abs(complex.re() - prevx)>dfreq){
//				timeSeries.add(mFunction.getResult(arg).re(), mFunction.getResult(arg).im());
//				prevx = (float) complex.re();
//				n++;
//				Log.d("rozmiar", n+": "+complex.toString());
//			}
//			arg+=darg;
//		}
//		timeSeries.add(mFunction.getResult(infinity).re(), mFunction.getResult(infinity).im());
		
			
			
//			if(Math.abs(complex.re()-prevx)>freq*2){
//				darg*=2;
//			}
			
//			if(Math.abs(complex.re()-prevx)<freq)darg*=2;
//			prevx = arg;
			
//			timeSeries.add(complex.re(), complex.im());
		
		
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(timeSeries);
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		
		XYSeriesRenderer renderer = new XYSeriesRenderer();

		mRenderer.addSeriesRenderer(renderer);
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer,"Nyquist");
		
		
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setAxesColor(Color.BLACK);
		mRenderer.setGridColor(Color.GRAY);
		mRenderer.setMarginsColor(Color.GRAY);
		mRenderer.setShowAxes(true);
		mRenderer.setShowGrid(true);
		
		mRenderer.setLabelsTextSize(30);
		
		
		return intent;
	}

}
