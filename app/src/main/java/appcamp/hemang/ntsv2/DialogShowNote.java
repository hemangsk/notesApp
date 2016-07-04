package appcamp.hemang.ntsv2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Hemang on 03/07/16.
 */
public class DialogShowNote extends DialogFragment implements View.OnClickListener {

    ImageView iv1, iv2, iv3;
    TextView title, desc;
    Button ok;

    Note noteShow;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        /*The reason why LayoutInflator object is constructed like this is because
        Layout Inflator is used to create a 'sub' layout and not a whole activity covering layout.*/
        LayoutInflater layoutInflater =
                LayoutInflater.from(getActivity());


       /* Whole activity covering layouts are made using setContentView, which converts XML to View Objects
        LayoutInflator is used for Toasts, Dialogs etc
        And since it is used for sub layout. That means it is stacked on some super layout/activity.
        Thus we need the context of that activity to use LayoutManager. */
        View dialogShow = layoutInflater.inflate(R.layout.show_dialog, null);


        //Connecting View Objects and DialogShowNote Class objects
        iv1 = (ImageView) dialogShow.findViewById(R.id.imageViewId);
        iv2 = (ImageView) dialogShow.findViewById(R.id.imageViewTo);
        iv3 = (ImageView) dialogShow.findViewById(R.id.imageView3);

        iv1.setVisibility(View.GONE);
        iv2.setVisibility(View.GONE);
        iv3.setVisibility(View.GONE);

        title = (TextView) dialogShow.findViewById(R.id.textViewDesc);
        desc = (TextView) dialogShow.findViewById(R.id.textView3);

        ok = (Button) dialogShow.findViewById(R.id.button4);




        if(noteShow.getIdea()){
            iv1.setVisibility(View.VISIBLE);
        }

        if(noteShow.getTodo()){
            iv2.setVisibility(View.VISIBLE);
        }

        if(noteShow.getImp()){
            iv3.setVisibility(View.VISIBLE);
        }

        title.setText(noteShow.getTitle());
        desc.setText(noteShow.getDes());
        ok.setOnClickListener(this);


        //Setting the builder message.
        //Note new method setView is also included.
        //Since we are using a custom view for builder
        //Else we would have gone with builder.setMessage();
        builder.setView(dialogShow).setMessage("Your Note");

        return builder.create();


    }

    @Override
    public void onClick(View v) {
       dismiss();
    }

    public void sendNoteShow(Note receiveNote){
       noteShow = receiveNote;
    }
}
