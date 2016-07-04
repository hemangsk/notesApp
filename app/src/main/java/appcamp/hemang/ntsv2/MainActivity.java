package appcamp.hemang.ntsv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public NoteAdapter noteAdapter;

    private boolean mSound;
    private int mAnimOption;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        noteAdapter = new NoteAdapter();

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(noteAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note tempNote = noteAdapter.getItem(position);

                DialogShowNote dialogShowNote = new DialogShowNote();
                dialogShowNote.sendNoteShow(tempNote);
                dialogShowNote.show(getFragmentManager(), "2");

            }
        });

    }

    public void createANote(Note receiveNote){
        noteAdapter.addNote(receiveNote);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent();
            intent.setClass(this, SettingsActivity.class);
            startActivity(intent);

        }

        if(id == R.id.add_item){
            DialogAddNote dialogAddNote = new DialogAddNote();
            dialogAddNote.show(getFragmentManager(), "1");
        }

        return super.onOptionsItemSelected(item);
    }

    public class NoteAdapter extends BaseAdapter{

        JSONSerializer jsonSerializer;
        List<Note> myNoteList = new ArrayList<Note>();

        public NoteAdapter() {
            this.jsonSerializer = new JSONSerializer("nts.json", MainActivity.this.getApplicationContext());

            try{
                myNoteList = jsonSerializer.load();
            } catch (JSONException e) {
                myNoteList = new ArrayList<>();
                e.printStackTrace();
            } catch (IOException e) {
                myNoteList = new ArrayList<>();
                e.printStackTrace();
            }
        }

        @Override
        public int getCount() {
            return myNoteList.size();
        }

        @Override
        public Note getItem(int position) {
            return myNoteList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            /*
            *
            * The view object reference is, in fact,
            * an instance of the list item that is necessary to be displayed as evaluated by BaseAdapter,
            * and whichItem is the position in ArrayList of the Note object that needs to be displayed in it.
            *
            * It seems like BaseAdapter must have read our minds.
            *
            *
            */

            View view = convertView;

            if(view == null){

                /* One more new way to use LayoutInflator */
                LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                view = layoutInflater.inflate(R.layout.list_item, parent, false);

            }

            TextView title = (TextView) view.findViewById(R.id.textViewTitle);
            TextView desc = (TextView) view.findViewById(R.id.textViewDesc);

            ImageView iv1,iv2,iv3;

            iv1 = (ImageView) view.findViewById(R.id.imageViewId);
            iv2 = (ImageView) view.findViewById(R.id.imageViewTo);
            iv3 = (ImageView) view.findViewById(R.id.imageViewIm);


            //Fetch the arrayList object corresponding to address 'position'

            Note noteAdapt = myNoteList.get(position);

            title.setText(noteAdapt.getTitle());
            desc.setText(noteAdapt.getDes());


            if(noteAdapt.isIdea()) {
                iv1.setVisibility(View.VISIBLE);
            }else if(noteAdapt.isIdea()==false){
                iv1.setVisibility(View.GONE);
            }
            if(noteAdapt.isTodo()) {
                iv2.setVisibility(View.VISIBLE);
            }else if(noteAdapt.isTodo()==false){
                iv2.setVisibility(View.GONE);
            }
            if(noteAdapt.isImp()) {
                iv3.setVisibility(View.VISIBLE);
            }else if(noteAdapt.isImp()==false){
                iv3.setVisibility(View.GONE);
            }



            return view;
        }

        public void addNote(Note n){
            myNoteList.add(n);
            notifyDataSetChanged();
        }

        public void saveNotes(){
            try{
                jsonSerializer.save(myNoteList);
            }catch(Exception e){
                Log.e("Error Saving Notes","", e);
            } }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPrefs = getSharedPreferences("nts", MODE_PRIVATE);
        mSound  = mPrefs.getBoolean("SOUND", true);
        mAnimOption = mPrefs.getInt("ANIMATION", SettingsActivity.FAST);

    }

    @Override
    protected void onPause() {
        super.onPause();
        noteAdapter.saveNotes();
    }
}
