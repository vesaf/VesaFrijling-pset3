package com.example.vesaf.vesafrijling_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMovie = (EditText) findViewById(R.id.etMovie);
        assert etMovie != null;
    }

    public void movieSearch(View view) {
        String movieSearch = etMovie.getText().toString();
        MovieAsyncTask asyncTask = new MovieAsyncTask(this);
        asyncTask.execute(movieSearch);

        etMovie.getText().clear();
    }

    public void movieStartIntent(ArrayList<String> movieData) {
        Intent dataIntent = new Intent(this, SearchResults.class);
        dataIntent.putExtra("data", movieData);
        this.startActivity(dataIntent);
    }

    public void goToSaved(View view) {
        Intent savedIntent = new Intent(this, SavedActivity.class);
        this.startActivity(savedIntent);
    }
}
