package appcamp.hemang.ntsv2;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Pack200;

/**
 * Created by Hemang on 04/07/16.
 */
public class JSONSerializer {

    String fileName;
    Context context;

    public JSONSerializer(String fileName, Context context) {
        this.fileName = fileName;
        this.context = context;
    }

    public void save(List<Note> notes) throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray();
        Writer writer = null;


        for(Note n: notes){
            jsonArray.put(n.convertToJSON());
        }
        try {
            OutputStream outputStream = context.openFileOutput(fileName, context.MODE_PRIVATE);
            writer = new OutputStreamWriter(outputStream);
            writer.write(jsonArray.toString());
        }finally {
            if(writer!=null){
                writer.close();
            }
        }

    }

    public ArrayList<Note> load() throws IOException, JSONException {

        ArrayList<Note> noteArrayList = new ArrayList<>();
        BufferedReader reader = null;

        try{
            InputStream in = context.openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            for(int i =0; i<jsonArray.length();i++){
                noteArrayList.add(new Note(jsonArray.getJSONObject(i)));
            }

        }catch (FileNotFoundException r){
            r.printStackTrace();
        }finally {
            if(reader!=null) reader.close();

        }
        return noteArrayList;
    }
}
