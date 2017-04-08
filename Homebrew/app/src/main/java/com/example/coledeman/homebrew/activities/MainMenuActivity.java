package com.example.coledeman.homebrew.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

import com.example.coledeman.homebrew.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        abvCalculatorButtonSetup();
        myBrewsButtonSetup();
        addBrewsButtonSetup();
    }

    @Override
    public void onResume() {
        super.onResume();
        abvCalculatorButtonSetup();
        myBrewsButtonSetup();
        addBrewsButtonSetup();
        Log.i("BACK BUTTON PRESS", "ON RESUME WAS CALLED ");
    }

    public void abvCalculatorButtonSetup() {
        TableRow abvCalc = (TableRow) findViewById(R.id.abvCalcRow);
        abvCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //redirect to calculator
                Intent intent = new Intent(MainMenuActivity.this, ABVCalculatorActivity.class);
                startActivity(intent);
            }

        });
    }

    public void myBrewsButtonSetup() {
        TableRow abvCalc = (TableRow) findViewById(R.id.my_brews_row);
        abvCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //redirect to calculator
                Intent intent = new Intent(MainMenuActivity.this, BrewListActivity.class);
                startActivity(intent);
            }

        });
    }

    public void addBrewsButtonSetup() {
        Button addbrew = (Button) findViewById(R.id.add_brew_button);
        addbrew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //redirect to calculator
                /*Intent intent = new Intent(MainMenuActivity.this, BrewListActivity.class);
                startActivity(intent);*/
            }

        });
    }
}


