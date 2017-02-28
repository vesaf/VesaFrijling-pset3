package com.example.vesaf.vesafrijling_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by vesaf on 2/27/2017.
 */

public class MovieAsyncTask extends AsyncTask<String, Integer, String> {
    Context context;
    MainActivity mainAct;

    public MovieAsyncTask(MainActivity main) {
        this.mainAct = main;
        this.context = this.mainAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "searching for movies...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return HttpRequestHelper.downloadFromServer(params);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d("error", "before");
        JSONArray resultsObj = null;
        try {
            JSONObject movieStreamObj = new JSONObject(result);
            resultsObj = movieStreamObj.getJSONArray("Search");
            //JSONObject resultsObj = movieStreamObj.getJSONObject("Search"); // ga naar map in JSON

        } catch (JSONException e) {
            Log.d("error", "caught");
            e.printStackTrace();
        }
        Log.d("error", "after");
        ArrayList<String> titles = new ArrayList<String>();
//        ArrayList<ArrayList<String>> movies = new ArrayList<ArrayList<String>>();
//
//        Log.d("crash", "before");
//        for (int i = 0; i < resultsObj.length(); i++) {
//            try {
//                movies.add(new ArrayList<String>());
//                movies.get(i).add(resultsObj.getJSONObject(i).getString("Title"));
//                movies.get(i).add(resultsObj.getJSONObject(i).getString("Year"));
//                movies.get(i).add(resultsObj.getJSONObject(i).getString("imdbID"));
//                movies.get(i).add(resultsObj.getJSONObject(i).getString("Type"));
//                movies.get(i).add(resultsObj.getJSONObject(i).getString("Poster"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        Log.d("movies", movies.toString());

        for (int i = 0; i < resultsObj.length(); i++) {
            try {
                titles.add(resultsObj.getJSONObject(i).getString("Title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        this.mainAct.movieStartIntent(titles); //GEEF HIER IETS MEE AAN VOLGENDE ACT
    }
}
