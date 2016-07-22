package com.example.alexiscr.lescotranslator.UI;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.alexiscr.lescotranslator.R;

import java.io.IOException;

public class AddWords extends AppCompatActivity implements View.OnClickListener{
    private ImageButton addImage;
    private ImageView imgVPhoto;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);
        addImage = (ImageButton)findViewById(R.id.imageButtonAddWord);
        addImage.setOnClickListener(this);
        imgVPhoto = (ImageView)findViewById(R.id.imageViewNewPhoto);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButtonAddWord:
                showFileChooser();
                break;
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
