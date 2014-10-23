package GraphLogic;

import org.achartengine.ChartFactory;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.example.freqcharts.FrequencyChart;

import TransferFunctionLogic.ComplexFunction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

public class Phase implements FrequencyChart{

	private ComplexFunction mFunction;
	
	public Phase(ComplexFunction aFunction){
		mFunction = aFunction;
	}
	
	public Intent getIntent(Context context){
		TimeSeries timeSeries = new TimeSeries("line1");
		
		
		
		for(int i = -20; i<=20; i++){
			timeSeries.add(i, 180 / Math.PI * mFunction.getResult((float) Math.pow(2, i)).phase());
		}
		
		
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(timeSeries);
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		
		XYSeriesRenderer renderer = new XYSeriesRenderer();

		mRenderer.addSeriesRenderer(renderer);
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer,"Phase");
		
		
		
		
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
