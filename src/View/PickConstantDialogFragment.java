package View;

import com.example.freqcharts.R;

import Aplication.Application;
import TransferFunctionLogic.ComplexConstant;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

public class PickConstantDialogFragment extends DialogFragment {
	
	
	
	private ComplexConstant mConstant;
	private View V;
	private EditText mEditText;

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());   

        V=this.getActivity().getLayoutInflater().inflate(com.example.freqcharts.R.layout.dialogfragment_const, null);
        
        mEditText = (EditText)V.findViewById(R.id.editText1);
        
        builder.setView(V);
        builder.setMessage("Pick constant value")
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   mConstant.setConstant(Float.parseFloat(mEditText.getText().toString()));
                	   Application.getInstance().refreshBuilder();
                   }
               });
        
        return builder.create();
    }

	public PickConstantDialogFragment setConstant(ComplexConstant aConstant) {
		this.mConstant = aConstant;
		return this;
	}

}
