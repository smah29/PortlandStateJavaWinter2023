package edu.pdx.cs410J.smahato;

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.DESTINATION;
import static edu.pdx.cs410J.smahato.constants.AirlineConstants.SOURCE;
import static edu.pdx.cs410J.smahato.utils.ActivityHelper.isEmpty;
import static edu.pdx.cs410J.smahato.utils.ActivityHelper.isInputLengthZero;
import static edu.pdx.cs410J.smahato.utils.ActivityHelper.validateAirportCode;
import static edu.pdx.cs410J.smahato.utils.ActivityHelper.validateAirportForSearch;
import static edu.pdx.cs410J.smahato.utils.ActivityHelper.validateArrival;
import static edu.pdx.cs410J.smahato.utils.ActivityHelper.validateDeparture;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.pdx.cs410J.smahato.utils.OnTextChangeWatcher;

public class SearchFlightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_flight);
        Button searchFlightButton = findViewById(R.id.searchFlightButton);
        EditText airlineName = findViewById(R.id.airlineNameValue);
        EditText source = findViewById(R.id.sourceValue);
        EditText destination = findViewById(R.id.destinationVal);
        airlineName.addTextChangedListener(new OnTextChangeWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isEmpty(airlineName);
            }
        });
        airlineName.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus)
                isEmpty(airlineName);
        });

        source.addTextChangedListener(new OnTextChangeWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateAirportForSearch(source, destination, SOURCE);
            }
        });
        source.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus)
                validateAirportForSearch(source, destination, SOURCE);
        });
        destination.addTextChangedListener(new OnTextChangeWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateAirportForSearch(destination, source, DESTINATION);
            }
        });
        destination.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus)
                validateAirportForSearch(destination, source, DESTINATION);
        });
        OnTextChangeWatcher enableAddFLightButton = new OnTextChangeWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (airlineName.getError() == null && !isInputLengthZero(airlineName)
                        && ((isInputLengthZero(source) && isInputLengthZero(destination)) ||
                        (source.getError() == null && !isInputLengthZero(source) && destination.getError() == null && !isInputLengthZero(destination))))
                    searchFlightButton.setEnabled(true);
                else
                    searchFlightButton.setEnabled(false);
            }
        };
        airlineName.addTextChangedListener(enableAddFLightButton);
        source.addTextChangedListener(enableAddFLightButton);
        destination.addTextChangedListener(enableAddFLightButton);
    }

    public void searchFlight(View view) {
        EditText airlineName = findViewById(R.id.airlineNameValue);
        EditText source = findViewById(R.id.sourceValue);
        EditText destination = findViewById(R.id.destinationVal);
        String str = airlineName.getText() + " " + source.getText() + " "
                + destination.getText();
        //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ShowFlightsActivity.class);
        intent.putExtra("flight1", str);
        startActivity(intent);
    }
}
