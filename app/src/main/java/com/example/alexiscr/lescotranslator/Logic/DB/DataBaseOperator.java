package com.example.alexiscr.lescotranslator.Logic.DB;

import com.example.alexiscr.lescotranslator.Logic.Core.LescoObject;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by AlexisCR on 20/07/2016.
 */
public class DataBaseOperator {

    public static LescoObject getLescoObjectByWord(String word){
        return Realm.getDefaultInstance().where(LescoObject.class).equalTo("word", word.toLowerCase()).findFirst();
    }

    public static ArrayList<LescoObject> wordSpell(String word){
        ArrayList<LescoObject> letters = new ArrayList();
        LescoObject actualLescoObject;
        int length = word.length() - 2;
        for(int i = 0; i < length; i++) {
            actualLescoObject = getLescoObjectByWord(String.valueOf(word.charAt(i)));
            actualLescoObject.setChunk(true);
            letters.add(actualLescoObject);
        }
        actualLescoObject = getLescoObjectByWord(String.valueOf(word.charAt(length)));
        actualLescoObject.setChunk(false);
        letters.add(actualLescoObject);
        return letters;
    }

    public static ArrayList<LescoObject> stringToLescoObjectArrayList(String string){
        String[] splitedString = string.split(" ");
        ArrayList<LescoObject> lescoObjects = new ArrayList();
        for(String word : splitedString){
            LescoObject temporalLescoObject = getLescoObjectByWord(word);
            if(temporalLescoObject == null)
                lescoObjects.addAll(wordSpell(word));
            else
                temporalLescoObject.setChunk(false);
        }
        return lescoObjects;
    }
}
