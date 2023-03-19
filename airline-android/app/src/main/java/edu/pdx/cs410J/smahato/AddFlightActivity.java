package edu.pdx.cs410J.smahato;

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.ARRIVAL;
import static edu.pdx.cs410J.smahato.constants.AirlineConstants.DEPARTURE;
import static edu.pdx.cs410J.smahato.constants.AirlineConstants.DESTINATION;
import static edu.pdx.cs410J.smahato.constants.AirlineConstants.SOURCE;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.DEPARTURE_BEFORE_ARRIVAL;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.SOURCE_AND_DESTINATION_CANNOT_BE_SAME;
import static edu.pdx.cs410J.smahato.utils.ActivityHelper.isEmpty;
import static edu.pdx.cs410J.smahato.utils.ActivityHelper.isInputLengthZero;
import static edu.pdx.cs410J.smahato.utils.ActivityHelper.validateAirportCode;
import static edu.pdx.cs410J.smahato.utils.ActivityHelper.validateArrival;
import static edu.pdx.cs410J.smahato.utils.ActivityHelper.validateDeparture;
import static edu.pdx.cs410J.smahato.utils.DateTimeUtils.getDateFromString;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.smahato.exception.AirportCodeException;
import edu.pdx.cs410J.smahato.storage.TextDumper;
import edu.pdx.cs410J.smahato.storage.TextParser;
import edu.pdx.cs410J.smahato.utils.Airline;
import edu.pdx.cs410J.smahato.utils.AirlineValidationUtils;
import edu.pdx.cs410J.smahato.utils.OnTextChangeWatcher;

public class AddFlightActivity extends AppCompatActivity {
    public static final String SOME_ERROR_OCCURRED_WHILE_ADDING_FLIGHT = "Some error occurred while adding flight";
    public static final String FLIGHT_NUMBER_CONTAINS_ONLY_NUMBERS = "Flight Number contains only numbers";
    public static final String PLEASE_ENTER_A_NUMBER = "Please enter a number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_flight);
        Button addFlightButton = findViewById(R.id.addFlightButton);
        EditText airlineName = findViewById(R.id.airlineNameValue);
        EditText flightNumber = findViewById(R.id.flightNumberValue);
        EditText source = findViewById(R.id.sourceValue);
        EditText destination = findViewById(R.id.destinationVal);
        EditText departure = findViewById(R.id.departureValue);
        EditText arrival = findViewById(R.id.arrivalValue);
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
        flightNumber.addTextChangedListener(new OnTextChangeWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isEmpty(flightNumber);
            }
        });
        flightNumber.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus && isEmpty((EditText) view))
                Toast.makeText(getBaseContext(), FLIGHT_NUMBER_CONTAINS_ONLY_NUMBERS, Toast.LENGTH_SHORT).show();
        });
        flightNumber.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                if ((keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) || keyCode == KeyEvent.KEYCODE_TAB) {
                    // numeric key pressed, do nothing
                } else {
                    // non-numeric key pressed, show a Toast message
                    Toast.makeText(getBaseContext(), PLEASE_ENTER_A_NUMBER, Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        });
        source.addTextChangedListener(new OnTextChangeWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateAirportCode(source, destination, SOURCE);
            }
        });
        source.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus)
                validateAirportCode(source, destination, SOURCE);
        });
        destination.addTextChangedListener(new OnTextChangeWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateAirportCode(destination, source, DESTINATION);
            }
        });
        destination.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus)
                validateAirportCode(destination, source, DESTINATION);
        });
        departure.addTextChangedListener(new OnTextChangeWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateDeparture(arrival, departure);
            }
        });
        departure.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus)
                validateDeparture(arrival, departure);
        });

        arrival.addTextChangedListener(new OnTextChangeWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateArrival(arrival, departure);
            }
        });
        arrival.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus)
                validateArrival(arrival, departure);
        });

        OnTextChangeWatcher enableAddFLightButton = new OnTextChangeWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (airlineName.getError() == null && !isInputLengthZero(airlineName)
                        && flightNumber.getError() == null && !isInputLengthZero(flightNumber)
                        && source.getError() == null && !isInputLengthZero(source)
                        && destination.getError() == null && !isInputLengthZero(destination)
                        && departure.getError() == null && !isInputLengthZero(departure)
                        && arrival.getError() == null && !isInputLengthZero(arrival))
                    addFlightButton.setEnabled(true);
                else
                    addFlightButton.setEnabled(false);
            }
        };
        airlineName.addTextChangedListener(enableAddFLightButton);
        flightNumber.addTextChangedListener(enableAddFLightButton);
        source.addTextChangedListener(enableAddFLightButton);
        destination.addTextChangedListener(enableAddFLightButton);
        departure.addTextChangedListener(enableAddFLightButton);
        arrival.addTextChangedListener(enableAddFLightButton);
    }

    public void addFlight(View view) {
        EditText airlineName = findViewById(R.id.airlineNameValue);
        EditText flightNumber = findViewById(R.id.flightNumberValue);
        EditText departure = findViewById(R.id.departureValue);
        EditText source = findViewById(R.id.sourceValue);
        EditText destination = findViewById(R.id.destinationVal);
        EditText arrival = findViewById(R.id.arrivalValue);
        try {
            File file = new File(this.getDataDir(), airlineName.getText().toString());
            Airline airline = null;
            if (file.exists()) {
                airline = new TextParser(new FileReader(file)).parse();
            }
            if (airline == null) {
                airline = new Airline(airlineName.getText().toString());
            }
            Flight flight = new Flight(flightNumber.getText().toString(), source.getText().toString(), destination.getText().toString(), departure.getText().toString(), arrival.getText().toString());
            airline.addFlight(flight);
            new TextDumper(new FileWriter(file)).dump(airline);
            Toast.makeText(this, flight.toString(), Toast.LENGTH_SHORT).show();
        } catch (DateTimeException | AirportCodeException | NullPointerException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (IOException | ParserException e) {
            Toast.makeText(this, SOME_ERROR_OCCURRED_WHILE_ADDING_FLIGHT, Toast.LENGTH_LONG).show();
        }
    }
}
