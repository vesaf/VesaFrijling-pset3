package com.example.vesaf.vesafrijling_pset3;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DetailsActivity extends AppCompatActivity {

    String title;
    Button btSave;
    Button btDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);


        Bundle extras = getIntent().getExtras();
        title = extras.getString("choice");
        tvTitle.setText(title);

        Log.d("OK", "1");
        SharedPreferences pref = getSharedPreferences("Saved", Context.MODE_PRIVATE);;
        Set<String> s = new HashSet<String>(pref.getStringSet("Saved", new HashSet<String>()));
        Log.d("OK", "2");
        if (s.contains(title)) {
            btDelete = (Button) findViewById(R.id.btDelete);
            btDelete.setVisibility(View.VISIBLE);
        }
        else {
            btSave = (Button) findViewById(R.id.btSave);
            btSave.setVisibility(View.VISIBLE);
        }
    }

    public void saveTitle(View view) {
        Set<String> moviesSet;
        SharedPreferences.Editor sPEditor = getSharedPreferences("Saved", Context.MODE_PRIVATE).edit();
        SharedPreferences pref = getSharedPreferences("Saved", Context.MODE_PRIVATE);
        //moviesSet = pref.getStringSet("Saved", new HashSet<String>());
        Set<String> s = new HashSet<String>(pref.getStringSet("Saved", new HashSet<String>()));
        s.add(title);
        Log.d("Set!!!!!!!!!!", s.toString());
        sPEditor.putStringSet("Saved", s);
        sPEditor.commit();
        Toast.makeText(DetailsActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void deleteTitle(View view) {
        Set<String> moviesSet;
        SharedPreferences.Editor sPEditor = getSharedPreferences("Saved", Context.MODE_PRIVATE).edit();
        SharedPreferences pref = getSharedPreferences("Saved", Context.MODE_PRIVATE);
        Set<String> s = new HashSet<String>(pref.getStringSet("Saved", new HashSet<String>()));
        s.remove(title);
        Log.d("Set!!!!!!!!!!", s.toString());
        sPEditor.putStringSet("Saved", s);
        sPEditor.commit();
        Toast.makeText(DetailsActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
        Log.d("deleted", "now change visibility");
        finish();
    }
}
