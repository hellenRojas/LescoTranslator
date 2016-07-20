package com.example.alexiscr.lescotranslator.Logic.Core;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by AlexisCR on 19/07/2016.
 */
public class LescoObject extends RealmObject {
    @PrimaryKey
    private String word;
    private byte[] image;
    @Ignore
    private boolean chunk;

    public LescoObject(){}

    public LescoObject(String word, byte[] image) {
        this.word = word;
        this.image = image;
    }

    public String getWord() {
        return word;
    }

    public byte[] getImage(){
        return image;
    }

    public boolean isChunk() { return chunk; }

    public void setChunk(boolean chunkState) { this.chunk = chunkState; }
}
