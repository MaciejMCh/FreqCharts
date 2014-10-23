package View;

import Aplication.Application;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public class ConfirmDeleteDialogFragment extends DialogFragment {
	
	
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());   

        builder.setMessage("Delete saved function?")
               .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Application.getInstance().deleteSaved();
                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                   }
               });
        return builder.create();
    }
	

}
