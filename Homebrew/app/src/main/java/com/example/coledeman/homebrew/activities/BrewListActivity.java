package com.example.coledeman.homebrew.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.coledeman.homebrew.R;
import com.example.coledeman.homebrew.database.DatabaseHelper;
import com.example.coledeman.homebrew.objects.Brew;

import java.util.ArrayList;

public class BrewListActivity extends AppCompatActivity {

    DatabaseHelper dbhelper;
    ArrayList<Brew> brews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbhelper = DatabaseHelper.getInstance(this.getApplicationContext());
        brews = dbhelper.getAllBrews();
        setContentView(R.layout.activity_brew_list);
        populateList();
        addBrewsButtonSetup();
    }

    @Override
    public void onResume() {
        super.onResume();
        populateList();
        Log.i("BACK BUTTON PRESS", "ON RESUME WAS CALLED ");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BrewListActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    private void populateList() {
        ListView listView = (ListView) findViewById(R.id.brew_list_list);
        ArrayAdapter<Brew> adapter =
                new ArrayAdapter<Brew>(this, android.R.layout.simple_list_item_1, brews);
        listView.setAdapter(adapter);
    }

    public void addBrewsButtonSetup() {
        Button addBrew = (Button) findViewById(R.id.add_brew_button);
        addBrew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BrewListActivity.this, AddBrewActivity.class);
                startActivity(intent);
            }

        });
    }
}
