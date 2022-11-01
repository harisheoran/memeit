package com.example.memeit;

import android.content.Context;
import android.media.metrics.Event;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
  //  private static final String REDDIT_API_URL = "https://www.reddit.com/r/memes/.json";
    private static final String REDDIT_API_URL = "https://www.reddit.com/r/ProgrammerHumor/hot/.json";
    private MemeAdapter memeAdapter;
    private TextView mEmptyTextView;
    private ProgressBar mLoadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.meme_listView);
        // when there is no data in list, it display empty view
        mEmptyTextView = findViewById(R.id.empty_TextView);
        listView.setEmptyView(mEmptyTextView);

        memeAdapter = new MemeAdapter(this, new ArrayList<Meme>());

        listView.setAdapter(memeAdapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the AsyncTask, in order to interact with loaders.
            MemeAsyncTask memeAsyncTask = new MemeAsyncTask();
            memeAsyncTask.execute(REDDIT_API_URL);

        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_Bar);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyTextView.setText(R.string.no_internet_connection);
        }

    }

    public class MemeAsyncTask extends AsyncTask<String, Integer, ArrayList<Meme>>{
        protected void onPreExecute (){

        }
        @Override
        protected ArrayList<Meme> doInBackground(String... urls){
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<Meme> result = MemeData.grabMemeData(urls[0]);
            return result;
        }


        @Override
        protected void onPostExecute(ArrayList<Meme> data) {
            // Clear the adapter of previous earthquake data
            memeAdapter.clear();

            // setting the text after the load is finished
            mEmptyTextView.setText(R.string.no_memes);

            //hide the loading bar as data loading process is done here
            View loadingIndicator = findViewById(R.id.loading_Bar);
            loadingIndicator.setVisibility(View.GONE);

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                memeAdapter.addAll(data);
            }

        }
    }

}