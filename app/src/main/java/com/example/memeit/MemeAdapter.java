package com.example.memeit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MemeAdapter extends ArrayAdapter<Meme> {

        private static final String LOG_TAG = MemeAdapter.class.getSimpleName();
        public MemeAdapter(Context context, ArrayList<Meme> allOfMyMemes) {
            super(context, 0, allOfMyMemes);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;
            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
            }

            Meme currentPost = getItem(position);

            TextView postTitleView = (TextView) listItemView.findViewById(R.id.post_title);
            postTitleView.setText(currentPost.getTitle());

            ImageView postIMGView = (ImageView) listItemView.findViewById(R.id.post_img);
            String postImgUrl = currentPost.getPostImg();

            Picasso.get().load(postImgUrl).into(postIMGView);

            return listItemView;
        }
}
