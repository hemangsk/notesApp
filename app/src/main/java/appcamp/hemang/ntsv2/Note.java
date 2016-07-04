package appcamp.hemang.ntsv2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hemang on 03/07/16.
 */
public class Note {

    String title ;
    String des ;
    boolean imp ;
    boolean todo ;
    boolean idea ;

    private static final String JSON_TITLE = "title";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_IDEA = "idea" ;
    private static final String JSON_TODO = "todo";
    private static final String JSON_IMPORTANT = "important";

    public Note(JSONObject jsonObject) throws JSONException {
        this.title = jsonObject.getString(JSON_TITLE);
        this.des = jsonObject.getString(JSON_DESCRIPTION);
        this.imp = jsonObject.getBoolean(JSON_IMPORTANT);
        this.todo = jsonObject.getBoolean(JSON_TODO);
        this.idea = jsonObject.getBoolean(JSON_IDEA);
    }

    public Note() {
    }

    public JSONObject convertToJSON()throws JSONException{
        JSONObject jo = new JSONObject();

        jo.put(JSON_TITLE, this.title);
        jo.put(JSON_DESCRIPTION, this.des);
        jo.put(JSON_IDEA, this.isIdea());
        jo.put(JSON_TODO, this.isTodo());
        jo.put(JSON_IMPORTANT, this.isImp());

        return jo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isImp() {
        return imp;
    }

    public void setImp(boolean imp) {
        this.imp = imp;
    }

    public boolean isTodo() {
        return todo;
    }

    public void setTodo(boolean todo) {
        this.todo = todo;
    }

    public boolean isIdea() {
        return idea;
    }

    public void setIdea(boolean idea) {
        this.idea = idea;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Boolean getImp() {
        return imp;
    }

    public void setImp(Boolean imp) {
        this.imp = imp;
    }

    public Boolean getTodo() {
        return todo;
    }

    public void setTodo(Boolean todo) {
        this.todo = todo;
    }

    public Boolean getIdea() {
        return idea;
    }

    public void setIdea(Boolean idea) {
        this.idea = idea;
    }
}
