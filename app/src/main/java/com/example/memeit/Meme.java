package com.example.memeit;

import android.graphics.drawable.Drawable;

public class Meme {
    private String title;
    private String postImg;

    public Meme(String myTitle){
        this.title = myTitle;
    }
    public Meme(String myPostImg, String myTitle){
        this.title = myTitle;
        this.postImg = myPostImg;
    }

    public String getPostImg() {
        return postImg;
    }

    public String getTitle() {
        return title;
    }
}
