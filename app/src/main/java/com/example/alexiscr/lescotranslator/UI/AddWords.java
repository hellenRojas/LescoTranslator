package com.example.alexiscr.lescotranslator.UI;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alexiscr.lescotranslator.Logic.Core.ImageConverter;
import com.example.alexiscr.lescotranslator.Logic.Core.LescoObject;
import com.example.alexiscr.lescotranslator.R;

import java.io.IOException;

import io.realm.Realm;

public class AddWords extends AppCompatActivity implements View.OnClickListener{
    private ImageButton addImage;
    private ImageView imgVPhoto;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST;
    private EditText wordToadd;
    private Button addWord;

    Realm realm = Realm.getDefaultInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);
        addImage = (ImageButton)findViewById(R.id.imageButtonAddWord);
        addImage.setOnClickListener(this);
        addWord = (Button)findViewById(R.id.buttonAddDatabaseWord);
        imgVPhoto = (ImageView)findViewById(R.id.imageViewNewPhoto);
        wordToadd = (EditText)findViewById(R.id.editTextWritteWord);
        addWord.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String photoName;
        switch (view.getId()){
            case R.id.imageButtonAddWord:
                showFileChooser();
                break;
            case R.id.buttonAddDatabaseWord:
                photoName= wordToadd.getText().toString();
                if(photoName.equals("")){
                    Toast.makeText(this,"Ingrese un nombre válido",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        LescoObject newLescoObject = new LescoObject(photoName, ImageConverter.bitmapToByteArray(bitmap));
                        realm.beginTransaction();
                        LescoObject newRealmLescoObject = realm.copyToRealm(newLescoObject);
                        realm.commitTransaction();
                    }
                    catch(Exception ex){
                        Log.e("Error", ex.getLocalizedMessage());
                        realm.cancelTransaction();
                    }
                    wordToadd.setText("");
                    imgVPhoto.setImageDrawable(null);
                    imgVPhoto.setImageDrawable(null);
                    Toast.makeText(this,"Nueva palabra agregada",Toast.LENGTH_SHORT).show();

                }

        }
    }

    public void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        int RESULT_OK = 200;
       // if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
           Uri filePath = data.getData();
           // try {
                //Getting the Bitmap from Gallery
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
            imgVPhoto.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Setting the Bitmap to ImageView

           // } catch (IOException e) {
                //e.printStackTrace();
           // }
        //}
        super.onActivityResult(requestCode, resultCode, data);
    }
}
