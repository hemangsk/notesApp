package appcamp.hemang.ntsv2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    RadioGroup radioGroup;

    static final int FAST = 1;
    static final int SLOW = 2;
    static final int NONE = 3;

    CheckBox isSound;

    boolean checkSound;
    int animationOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = getSharedPreferences("nts", MODE_PRIVATE);
        editor = prefs.edit();

        isSound = (CheckBox) findViewById(R.id.checkBox4);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        checkSound = prefs.getBoolean("SOUND", true);

        /*
           if(mSound){
            checkBoxSound.setChecked(true);
           }
            else{
            checkBoxSound.setChecked(false);

            }*/
        isSound.setChecked(checkSound);

        isSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkSound = !checkSound;
                editor.putBoolean("SOUND", checkSound);

            }
        });


        animationOption = prefs.getInt("ANIMATION", FAST);
        radioGroup.clearCheck();

        switch(animationOption){
            case FAST: radioGroup.check(R.id.radioButton);
                break;
            case SLOW: radioGroup.check(R.id.radioButton2);
                break;
            case NONE: radioGroup.check(R.id.radioButton3);
                break;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    switch (rb.getId()) {
                        case R.id.radioButton:
                            animationOption = FAST;
                            break;
                        case R.id.radioButton2:
                            animationOption = SLOW;
                            break;
                        case R.id.radioButton3:
                            animationOption = NONE;
                            break;
                    }
                }
                editor.putInt("ANIMATION", animationOption);
            }

        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        editor.commit();
    }
}
