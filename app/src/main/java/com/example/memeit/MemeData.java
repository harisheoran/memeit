package com.example.memeit;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MemeData {
    public static final String LOG_TAG = MemeData.class.getSimpleName();

        public static ArrayList<Meme> grabMemeData(String usgsUrl){
            // create the URL object
            URL url = createUrl(usgsUrl);
            // response
            String JSONresponse = "";
            try{
                JSONresponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error closing input stream", e);
            }

            ArrayList<Meme> memeslist = extractJsonFromResponse(JSONresponse);

            return memeslist;
        }
        private static URL createUrl(String usgsUrl) {
            URL url = null;
            try{
                url = new URL(usgsUrl);
            }catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Error with creating URL ", e);
            }
            return url;
        }
        private static String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";

            // If the URL is null, then return early.
            if (url == null) {
                return jsonResponse;
            }

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // If the request was successful (response code 200),
                // then read the input stream and parse the response.
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return jsonResponse;
        }
        private static String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }
        private static ArrayList<Meme> extractJsonFromResponse(String jsonresponse){
            // If the JSON string is empty or null, then return early.
            if (TextUtils.isEmpty(jsonresponse)) {
                return null;
            }
            // our list
            ArrayList<Meme> memeslist = new ArrayList<>();
            String postImg = "";
            try {
                JSONObject baseJsonResponse = new JSONObject(jsonresponse);
                JSONObject dataResponse2nd = baseJsonResponse.getJSONObject("data");

                JSONArray jsonChildrenArray = dataResponse2nd.getJSONArray("children");


                // If there are results in the features array
                if (jsonChildrenArray.length() > 0) {
                    for (int i = 0; i < jsonChildrenArray.length(); i++) {
                        JSONObject arrayChild = jsonChildrenArray.getJSONObject(i);
                        JSONObject dataChildofArrayChild = arrayChild.getJSONObject("data");

                        String title = dataChildofArrayChild.getString("title");

                        if(dataChildofArrayChild.has("url_overridden_by_dest")){
                            postImg = dataChildofArrayChild.getString("url_overridden_by_dest");
                            memeslist.add(new Meme(postImg, title));
                        }else{
                            memeslist.add(new Meme(title));
                        }
                    }
                    return memeslist;
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
            }
            return memeslist;
        }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}


