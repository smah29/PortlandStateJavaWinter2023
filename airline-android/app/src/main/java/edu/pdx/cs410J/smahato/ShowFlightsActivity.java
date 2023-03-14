package edu.pdx.cs410J.smahato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowFlightsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_flights);
        Intent intent = getIntent();
        String airline = intent.getStringExtra(getString(R.string.pretty_flights));
        ((TextView) findViewById(R.id.prettyFlights)).setText(airline);
    }
}