package com.ernestovaldez.androidapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import org.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Student student;
    List<Student> itemList;
    String jsonResponse;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        Toast.makeText(context, isConnected(context)?"Connected":"No Connected", Toast.LENGTH_LONG).show();

        if (isConnected(context)) {
            new HttpAsyncTask(jsonResponse).execute("http://172.28.11.16:49966/api/students");
        }


    }

    private static String GET(String url){
        HttpURLConnection urlConnection;
        try {

            URL urlString = new URL(url);
            urlConnection = (HttpURLConnection) urlString.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            if(reader != null) {
                return convertInputStreamToString(in);
            } else {
                return "Did not work!";}

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
            return e.getLocalizedMessage();
        }

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line;
        StringBuilder result = new StringBuilder();
        while((line = bufferedReader.readLine()) != null)
            result.append(line);
        inputStream.close();

        return result.toString();

    }

    private boolean isConnected(@NonNull Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        String Response;

        HttpAsyncTask(String Response) {
            this.Response = Response;
        }

        @Override
        protected String doInBackground(String... urls) {
            return GET(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            parseJson(result);
        }
    }

    private void parseJson(String data){

        try {

            JSONArray jsonArray = new JSONArray(data);
            Log.d("jsonArray", jsonArray.toString());

            Toast.makeText(context,"Data received from server", Toast.LENGTH_LONG).show();

            RecyclerView recycler = findViewById(R.id.rv1);
            recycler.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            recycler.setLayoutManager(layoutManager);
            itemList = new ArrayList<>();


            for (int i = 0; i < jsonArray.length(); i++) {

                student = new Student();
                student.Id = Integer.parseInt(jsonArray.getJSONObject(i).getString("id"));
                student.Name = jsonArray.getJSONObject(i).getString("name");
                student.Age = Integer.parseInt(jsonArray.getJSONObject(i).getString("age"));

                itemList.add(student);
            }


            RecyclerView.Adapter myAdapter = new ListAdapter(itemList);
            recycler.setAdapter(myAdapter);

        } catch (Throwable t) {
            Log.e("Error", "Could not parse malformed data: \"" + t.getLocalizedMessage() + "\"");
        }

    }
}
