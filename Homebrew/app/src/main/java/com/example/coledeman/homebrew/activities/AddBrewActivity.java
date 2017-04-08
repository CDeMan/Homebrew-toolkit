package com.example.coledeman.homebrew.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.coledeman.homebrew.R;
import com.example.coledeman.homebrew.database.DatabaseHelper;
import com.example.coledeman.homebrew.objects.Brew;

import java.util.Calendar;
import java.util.Date;

public class AddBrewActivity extends AppCompatActivity {

    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbhelper = DatabaseHelper.getInstance(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_brew);
        setupAddButton();
    }

    public void setupAddButton() {
        Button addBrew = (Button) findViewById(R.id.add_brew);
        addBrew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Brew brew = null;

                EditText nameField = (EditText) findViewById(R.id.edit_name);
                EditText descriptionField = (EditText) findViewById(R.id.edit_description);
                DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

                String name, description;
                Date date = getDateFromDatePicker(datePicker);

                name = nameField.getText().toString();
                description = descriptionField.getText().toString();

                brew = new Brew(date, name, description);

                dbhelper.addBrew(brew);

                Intent intent = new Intent(AddBrewActivity.this, BrewListActivity.class);
                startActivity(intent);
            }

        });
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}

