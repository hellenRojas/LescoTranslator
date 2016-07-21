package com.example.alexiscr.lescotranslator.UI;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.alexiscr.lescotranslator.Logic.Core.LescoObject;
import com.example.alexiscr.lescotranslator.Logic.DB.DataBaseInitializer;
import com.example.alexiscr.lescotranslator.Logic.DB.DataBaseOperator;
import com.example.alexiscr.lescotranslator.R;

import java.util.ArrayList;
import java.util.Locale;

public class RecorderActivity extends AppCompatActivity {
    private EditText textViewResult;
    private Button btnTranslate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);
        textViewResult = (EditText) findViewById(R.id.textViewResult);
        ImageButton imageButtonMicrophone;
        imageButtonMicrophone = (ImageButton) findViewById(R.id.imageButtonMicrophone);
        btnTranslate = (Button) findViewById(R.id.btnTranslate);
        assert imageButtonMicrophone != null;
        new DataBaseInitializer().initializeDatabase(getApplicationContext());
    }

    public void onClickButton(View v) {
        switch (v.getId()){
            case R.id.imageButtonMicrophone:
                startSpeechInput();
                break;
        }
    }

    public void onClickTranslate(View v) {
       /// ArrayList<LescoObject> lescoObjectArrayList = DataBaseOperator.stringToLescoObjectArrayList(textViewResult.getText().toString());
        Intent in = new Intent( RecorderActivity.this, TranslatorActivity.class);
    //   mainIntent.putExtra("lescoObjectArrayList", lescoObjectArrayList);
        startActivity(in);
    }

    public void translate(View v) {
        Intent in = new Intent( RecorderActivity.this, TranslatorActivity.class);
        startActivity(in);
    }

        private void startSpeechInput(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, R.string.order);
        try {
            startActivityForResult(intent, 100);
        }
        catch(ActivityNotFoundException ex){
            Toast.makeText(RecorderActivity.this, R.string.notification, Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode){
            case 100:
                if(resultCode == RESULT_OK && intent != null){
                    ArrayList<String> resultArray = intent.getStringArrayListExtra(
                            RecognizerIntent.EXTRA_RESULTS);
                    textViewResult.setText(resultArray.get(0));
                }
                break;
        }
    }

}
