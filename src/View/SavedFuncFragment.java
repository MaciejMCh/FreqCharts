package View;

import java.util.ArrayList;


import com.example.freqcharts.ComplexFunctionParser;
import com.example.freqcharts.DBAdapter;
import com.example.freqcharts.DBRecord;
import com.example.freqcharts.FuncListAdapter;
import com.example.freqcharts.R;
import com.example.freqcharts.ResourceToBitmap;

import Aplication.Application;
import TransferFunctionLogic.ComplexConstant;
import TransferFunctionLogic.ComplexDiv;
import TransferFunctionLogic.ComplexES;
import TransferFunctionLogic.ComplexFunction;
import TransferFunctionLogic.ComplexSum;
import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class SavedFuncFragment extends Fragment{
	private View view;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        view =  inflater.inflate(R.layout.fragment_saved, container, false);
        
		
		
		view = new LinearLayout(getActivity());
		
		
		
		
		MyGridView gv = new MyGridView(getActivity());
		
      ArrayList<DBRecord> list = new ArrayList<DBRecord>();		
		DBAdapter db = new DBAdapter(view.getContext());
		db.open();
		Cursor cursor = db.getAllTodos();
		cursor.moveToFirst();
		
		for(int i=0;i<=cursor.getCount()-1;i++){
			list.add(new DBRecord(ComplexFunctionParser.parseFunction(cursor.getString(DBAdapter.FUNCTION_COLUMN)),
					cursor.getString(DBAdapter.NAME_COLUMN), 
					cursor.getInt(DBAdapter.ID_COLUMN)
					));
			cursor.moveToNext();
		}
		gv.setAdapter(new FuncListAdapter(view.getContext(), list));
		db.close();
        
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT,android.view.ViewGroup.LayoutParams.FILL_PARENT);		
		int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
		lp = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT,height);
        ((LinearLayout)view).addView(gv,lp);
        
		
		
		
		
		
		
		
		
		
		
		
        
//        GridView gv;
//        gv = (GridView)view.findViewById(R.id.gridView1); 
//        
//        Log.d("pacz",gv.toString());
//        
////        
//        ArrayList<DBRecord> list = new ArrayList<DBRecord>();		
//		DBAdapter db = new DBAdapter(view.getContext());
//		db.open();
//		Cursor cursor = db.getAllTodos();
//		cursor.moveToFirst();
//		
//		for(int i=0;i<=cursor.getCount()-1;i++){
//			list.add(new DBRecord(ComplexFunctionParser.parseFunction(cursor.getString(DBAdapter.FUNCTION_COLUMN)),
//					cursor.getString(DBAdapter.NAME_COLUMN), 
//					cursor.getInt(DBAdapter.ID_COLUMN)
//					));
//			cursor.moveToNext();
//		}		
//		gv.setAdapter(new FuncListAdapter(view.getContext(), list));        
//		db.close();
		gv.setOnItemClickListener(Application.getInstance());
		gv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Application.getInstance().confirmDelete(((DBRecord)parent.getItemAtPosition(position)).getID());
				return true;
			}
		});
		
		
		
		
		
		
		
        return view;
    }
	
	

	

}
