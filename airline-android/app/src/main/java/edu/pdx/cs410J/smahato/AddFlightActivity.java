package edu.pdx.cs410J.smahato;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddFlightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_flight);
    }

    public void addFlight(View view) {
        EditText airlineName = findViewById(R.id.airlineNameValue);
        EditText flightNumber = findViewById(R.id.flightNumberValue);
        EditText departure = findViewById(R.id.departureValue);
        EditText source = findViewById(R.id.sourceValue);
        EditText destination = findViewById(R.id.destinationVal);
        EditText arrival = findViewById(R.id.arrivalValue);
        String str = airlineName.getText() + " " + flightNumber.getText() + " " + source.getText() + " "
                + departure.getText() + " " + destination.getText() + " " + arrival.getText();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
