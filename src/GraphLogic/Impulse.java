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

public class Impulse implements FrequencyChart {
	
	private ComplexFunction mFunction;
	
	public Impulse(ComplexFunction aFunction) {
		mFunction = aFunction;
	}

	public Intent getIntent(Context context){
		
		int x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int y[] = {1, 6, 9, 8, 5, 1, 2, 3 ,0, 15};
		
		TimeSeries timeSeries = new TimeSeries("line1");
		float darg = 0.0001f;
		float arg = darg;
		Complex complex = mFunction.getResult(arg);		
		int pointCnt = 100;		
		float argFreq = 1;
		float dFreq = 1.1f;
		argFreq = (float) (1/Math.pow(dFreq, pointCnt/2));
		Log.d("rozmiar", "PC: "+argFreq);
		
		
		int p=1000;
		float[] resp = new float[p];
		for(int i=0; i<=p-1;i++){
			resp[i]=0;
		}
		
		float n=0;
		while(n<=100){
			complex = mFunction.getResult(n);
			for(int i=0; i<=p-1; i++){
				resp[i]+=complex.abs()*Math.cos(n*(i+complex.phase()));
			}
//			timeSeries.add(complex.re(), complex.im());
			n+=0.1;
		}
		
		for(int i=0; i<=p-1; i++){
			timeSeries.add(i, resp[i]);
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
