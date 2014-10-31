package com.example.API19Test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class NewsActivity extends Activity {
    static List<News> newsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
        AsynTask asynTask = new AsynTask();
        asynTask.execute();

    }

    public void setAdapterData(List<News> list) {
        ListView listView1 = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<News> adapter = new ArrayAdapter<News>(this,
                android.R.layout.simple_list_item_1, list);

        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                News news = newsList.get(position);
                AlertDialog.Builder newsAlert = new AlertDialog.Builder(NewsActivity.this);
                newsAlert.setTitle(news.getHeader());
                newsAlert.setMessage(news.getDescription());
                newsAlert.setNegativeButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                newsAlert.show();

            }
        });

    }

    public class AsynTask extends AsyncTask<String, Void, String> {
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
                newsList = new ArrayList<News>();
                for (JsonElement obj : jArray) {
                    News news = new News();
                    news.setHeader(obj.getAsJsonObject().get("header").toString());
                    news.setDescription(obj.getAsJsonObject().get("description").toString());
                    newsList.add(news);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setAdapterData(newsList);
                    }
                });
                assert is != null;
                is.close();
            } catch (Exception e) {
                Log.e("log_tag", "Error converting result " + e.toString());
            }
            return null;
        }
    }
}
