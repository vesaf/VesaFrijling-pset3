package com.example.vesaf.vesafrijling_pset3;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by vesaf on 2/27/2017.
 */

public class HttpRequestHelper {
    protected static synchronized String downloadFromServer(String... params) throws MalformedURLException {
        String result = "";
        String chosenTag = params[0];

        // creeer een geldige url met je API en de gegeven zoekterm van de gebruiker
        URL url = new URL("http://www.omdbapi.com/?s=" + chosenTag + "&r=json");

        HttpURLConnection connect;

        if (url != null) {
            Log.d("not null", url.toString());
            try {
                connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("GET");

                Integer responseCode = 0;
                responseCode = connect.getResponseCode();

                if (responseCode >= 200 && responseCode < 300) {
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                    String line;
                    while ((line = bReader.readLine()) != null) {
                        result += line;
                    }
                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
