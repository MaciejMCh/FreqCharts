package View;

import com.example.freqcharts.FunctionBuilder;
import com.example.freqcharts.R;

import Aplication.Application;
import Aplication.MyFuncBuilder;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FuncBuilderFragment extends Fragment{

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState) {
		 View view = inflater.inflate(R.layout.fragment_new, container, false);
		 
		 MyFuncBuilder builder = new MyFuncBuilder(view.getContext());
		 builder.addFuncDoneListener(Application.getInstance());
		 builder.addGraphFuncListener(Application.getInstance());
		 builder.setConstantProvider(Application.getInstance());
		 ((LinearLayout)view.findViewById(R.id.fragment_new_content)).addView(builder);
		 Application.getInstance().setBuilder(builder);
		 return view;
    }


}
