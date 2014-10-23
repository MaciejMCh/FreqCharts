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

public class NyqTest implements FrequencyChart {
	
	private ComplexFunction mFunction;
	
	public NyqTest(ComplexFunction aFunction) {
		mFunction = aFunction;
	}

	public Intent getIntent(Context context){
		
		
		
		TimeSeries timeSeries = new TimeSeries("line1");
		
		
		Complex compZero = mFunction.getResult(0);
		float zDX = 0.0001f;
		float dX = 0.1f;
		boolean done = false;
		float diff;
		
		Complex comp = mFunction.getResult(dX);
		while(!done){
			comp = mFunction.getResult(dX);
			diff = (float) (compZero.re()-comp.re());
			Log.d("baza",""+diff);
			if((Math.abs(diff)<zDX*5)&&(Math.abs(diff)>zDX/5)) break;		
			if(Math.abs(diff)>zDX){
				dX/=2;
				continue;
			}
			if(Math.abs(diff)<zDX){
				dX*=2;
				continue;
			}
				
		}
		
		//timeSeries.add(compZero.re(), compZero.im());
		//timeSeries.add(comp.re(), comp.im());
		
		for(int i = 0; i<=10000; i++){
			comp = mFunction.getResult(zDX*i*i*1.1f);
			timeSeries.add(comp.re(), comp.im());
		}
		
		
		
		
		
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
