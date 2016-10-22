package com.example.alexiscr.lescotranslator.Logic.DB;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.alexiscr.lescotranslator.Logic.Core.ImageConverter;
import com.example.alexiscr.lescotranslator.Logic.Core.LescoObject;
import com.example.alexiscr.lescotranslator.R;

import java.lang.reflect.Field;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmConfiguration.Builder;


/**
 * Created by AlexisCR on 19/07/2016.
 */
public class DataBaseInitializer extends AppCompatActivity {

    public void initializeDatabase(Context context){
        final R.drawable drawableResources = new R.drawable();
        final Class<R.drawable> rClass = R.drawable.class;
        final Field[] fields = rClass.getDeclaredFields();
        int resourceID;
        String temporalName;
        Bitmap temporalImage;
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
        Realm realm = Realm.getDefaultInstance();
        for(int i = 0; i < fields.length; i++){
            try{
                resourceID = fields[i].getInt(drawableResources);
                temporalName = context.getResources().getString(resourceID);
                temporalName = temporalName.substring(temporalName.lastIndexOf("/") + 1);
                temporalImage = BitmapFactory.decodeResource(context.getResources(), resourceID);
                if(temporalName.length() > 6 && "ltimg".equals(temporalName.substring(0, 5))){
                    LescoObject newLescoObject = new LescoObject(temporalName, ImageConverter.bitmapToByteArray(temporalImage));
                    realm.beginTransaction();
                    LescoObject newRealmLescoObject = realm.copyToRealm(newLescoObject);
                    realm.commitTransaction();
                }
            }
            catch(Exception ex){
                Log.e("Error", ex.getLocalizedMessage());
            }
        }
    }
}
