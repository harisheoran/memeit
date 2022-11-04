package com.example.memeit;

import android.graphics.drawable.Drawable;

public class Meme {
    private String title;
    private String postImg;
    private String memeUrl;

    public Meme(String myTitle){
        this.title = myTitle;
     //   this.memeUrl = memeUrl;
    }
    public Meme(String myPostImg, String myTitle, String memeUrl){
        this.title = myTitle;
        this.postImg = myPostImg;
        this.memeUrl = memeUrl;
    }

    public String getPostImg() {
        return postImg;
    }

    public String getTitle() {
        return title;
    }

    public String getMemeUrl() {
        return memeUrl;
    }
}
