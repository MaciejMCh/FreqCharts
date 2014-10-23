package View;

import com.example.freqcharts.FunctionBuilder;

import Aplication.Application;
import TransferFunctionLogic.ComplexPower;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class PickExponentDialogFragment extends DialogFragment {
	
	ComplexPower mPower;
	private View V;
	private NumberPicker mNumberPicker;

	
	public PickExponentDialogFragment setPower(ComplexPower aPower){
		mPower = aPower;
		return this;
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());   

        V=this.getActivity().getLayoutInflater().inflate(com.example.freqcharts.R.layout.dialogfragment_exp, null);
        
        mNumberPicker = (NumberPicker)V.findViewById(com.example.freqcharts.R.id.numberPicker1);
		mNumberPicker.setMinValue(2);
		mNumberPicker.setMaxValue(9);
		mNumberPicker.setWrapSelectorWheel(false);
        
        builder.setView(V);
        builder.setMessage("Pick exponent value")
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   mPower.setExponent(mNumberPicker.getValue());
                	   Application.getInstance().refreshBuilder();
                   }
               });
        
        return builder.create();
    }

}
