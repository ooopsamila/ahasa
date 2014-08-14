package com.example.API19Test;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AsynTask asynTask = new AsynTask();
        asynTask.execute();

    }

    public void setAdapterData(List<String> list) {
        setListAdapter(new ArrayAdapter<String>(this, R.layout.news_layout, R.id.label, list));
    }

    private class AsynTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            InputStream is = null;
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://192.168.0.118/login.php");
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection" + e.toString());
            }

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                String line = reader.readLine();
                JsonParser parser = new JsonParser();
                JsonArray jArray = parser.parse(line).getAsJsonArray();
                final List<String> newsList = new ArrayList<String>();
                for (JsonElement obj : jArray) {
                    newsList.add(obj.getAsJsonObject().get("description").toString());
                }
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        setAdapterData(newsList);

                    }
                });
                is.close();
            } catch (Exception e) {
                Log.e("log_tag", "Error converting result " + e.toString());
            }
            return null;
        }
    }
}
