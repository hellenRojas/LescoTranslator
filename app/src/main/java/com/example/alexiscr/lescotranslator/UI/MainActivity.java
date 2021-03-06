package com.example.alexiscr.lescotranslator.UI;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.alexiscr.lescotranslator.R;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );
        textViewResult = (EditText) findViewById(R.id.textViewResult);
        ImageButton imageButtonMicrophone;
        imageButtonMicrophone = (ImageButton) findViewById(R.id.imageButtonMicrophone);
        assert imageButtonMicrophone != null;
    }

    public void onClickButton(View v) {
        switch (v.getId()){
            case R.id.imageButtonMicrophone:
                startSpeechInput();
                break;
        }
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
            Toast.makeText(MainActivity.this, R.string.notification, Toast.LENGTH_SHORT).show();
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
