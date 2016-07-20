package com.example.alexiscr.lescotranslator.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.alexiscr.lescotranslator.R;



public class TranslatorActivity extends AppCompatActivity {
    private ImageSwitcher sw;
    private ImageButton b1,b2;
    Bitmap bitmap;
    private static final long IMAGE_DELAY = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_translator);


        b1 = (ImageButton) findViewById(R.id.btn_rew);
        b2 = (ImageButton) findViewById(R.id.btn_next);

        sw = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        sw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                myView.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.MATCH_PARENT, ImageSwitcher.LayoutParams.WRAP_CONTENT));
                return myView;
            }
        });


       // sw.setImageDrawable(new BitmapDrawable(this.getResources(), bitmap));
        sw.setImageResource(R.drawable.u);

        sw.postDelayed(new Runnable() {

            @Override
            public void run() {
                sw.setImageResource(R.drawable.g);
            }


        }, 5000);





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw.setImageResource(R.drawable.a);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sw.setImageResource(R.drawable.g);
            }
        });
    }


}
