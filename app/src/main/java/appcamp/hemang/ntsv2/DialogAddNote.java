package appcamp.hemang.ntsv2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by Hemang on 03/07/16.
 */
public class DialogAddNote extends DialogFragment implements View.OnClickListener {

    EditText title, des;
    Button ok, cancel;
    CheckBox idea, imp, todo;
    Note note;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        View dialogFrag = layoutInflater.inflate(R.layout.add_dialog, null);

        title = (EditText) dialogFrag.findViewById(R.id.editText);
        des = (EditText) dialogFrag.findViewById(R.id.editText2);

        ok = (Button) dialogFrag.findViewById(R.id.button3);
        cancel = (Button) dialogFrag.findViewById(R.id.button2);

        idea= (CheckBox) dialogFrag.findViewById(R.id.checkBox);
        imp= (CheckBox) dialogFrag.findViewById(R.id.checkBox2);
        todo= (CheckBox) dialogFrag.findViewById(R.id.checkBox3);

        ok.setOnClickListener(this);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



        builder.setView(dialogFrag).setMessage("Add a new note!");

        return builder.create();
    }


    @Override
    public void onClick(View v) {
        note = new Note();
        note.setTitle(title.getText().toString());
        note.setDes(des.getText().toString());
        note.setIdea(idea.isChecked());
        note.setTodo(todo.isChecked());
        note.setImp(imp.isChecked());
        Log.e("SHOW TODO!!!!", String.valueOf(todo.isChecked()));
        Log.e("SHOW IMP!!!!", String.valueOf(imp.isChecked()));
        Log.e("SHOW IDEA!!!!", String.valueOf(idea.isChecked()));

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.createANote(note);
        dismiss();

    }

    public Note getNote(){
        return note;
    }
}
