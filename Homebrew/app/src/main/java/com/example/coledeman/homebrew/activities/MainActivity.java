package com.example.coledeman.homebrew.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coledeman.homebrew.controllers.AbvCalcLogic;
import com.example.coledeman.homebrew.R;

public class MainActivity extends AppCompatActivity {

    Button calculateButton;
    TextView result_view;
    EditText in, fin;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateButton = (Button) findViewById(R.id.calculate);
        in = (EditText) findViewById(R.id.intial_gravity);
        fin = (EditText) findViewById(R.id.final_gravity);
        result_view = (TextView) findViewById(R.id.result_text);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Performs calculation
                String tmp = "";
                Double a, b;
                tmp += in.getText().toString();
                if (tmp.equals("")) tmp = "0";
                a = Double.parseDouble(tmp);
                tmp = "";
                tmp += fin.getText().toString();
                if (tmp.equals("")) tmp = "0";
                b = Double.parseDouble(tmp);
                if((a != b) && (a != 0) && (b != 0) && (a >= b)) {
                    result = AbvCalcLogic.calculateWGravity(a, b);
                    result_view.setText(result.toString());
                }else{
                    error("invalid values");
                }

            }
        });


    }

    protected void error(String error) {
        //display in long period of time
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }


}
