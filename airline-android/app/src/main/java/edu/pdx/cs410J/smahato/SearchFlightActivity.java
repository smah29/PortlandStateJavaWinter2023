package edu.pdx.cs410J.smahato;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchFlightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_flight);
    }

    public void searchFlight(View view) {
        EditText airlineName = findViewById(R.id.airlineNameValue);
        EditText source = findViewById(R.id.sourceValue);
        EditText destination = findViewById(R.id.destinationVal);
        String str = airlineName.getText() + " " + source.getText() + " "
                + destination.getText();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
