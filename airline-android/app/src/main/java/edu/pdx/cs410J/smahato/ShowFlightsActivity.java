package edu.pdx.cs410J.smahato;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class ShowFlightsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_flights);
        Intent intent = getIntent();
        String airline = intent.getStringExtra(getString(R.string.pretty_flights));
        ListView listView = (ListView) findViewById(R.id.prettyFlights);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList(airline, airline));
        listView.setAdapter(arrayAdapter);
    }
}