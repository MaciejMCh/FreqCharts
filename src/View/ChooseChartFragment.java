package View;

import Aplication.Application;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.freqcharts.FunctionBuilder;
import com.example.freqcharts.R;
import com.example.freqcharts.ResourceToBitmap;

public class ChooseChartFragment extends Fragment{
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frament_choose_chart, container, false);
        
        Bitmap back = ResourceToBitmap.getBitmap(getActivity(), R.drawable.back3);
        Bitmap background;
        int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
		int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
		background = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas cc = new Canvas(background);
		Paint pp = new Paint();
		for(int x = 0; x<=width; x+=89){
			for( int y = 0; y<=height; y+=89){
				cc.drawBitmap(back, x, y, pp);
			}
		}
		((RelativeLayout)view).setBackground(new BitmapDrawable(background));
        
        ((ImageButton)view.findViewById(R.id.imageButton1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Application.getInstance().drawMagnitude(null);
			}
		});
        ((ImageButton)view.findViewById(R.id.imageButton2)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Application.getInstance().drawPhase(null);
			}
		});
        ((ImageButton)view.findViewById(R.id.imageButton3)).setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				Application.getInstance().drawNyquist(null);
			}
        });
        
        
        return view;
	}

	private void prepareBackground(Bitmap background) {
		
		
	}

}
