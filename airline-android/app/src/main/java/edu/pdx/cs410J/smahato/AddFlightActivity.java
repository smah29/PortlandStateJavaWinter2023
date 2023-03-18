package edu.pdx.cs410J.smahato;

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.ARRIVAL;
import static edu.pdx.cs410J.smahato.constants.AirlineConstants.DEPARTURE;
import static edu.pdx.cs410J.smahato.constants.AirlineConstants.DESTINATION;
import static edu.pdx.cs410J.smahato.constants.AirlineConstants.SOURCE;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.DEPARTURE_BEFORE_ARRIVAL;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.SOURCE_AND_DESTINATION_CANNOT_BE_SAME;
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

import java.time.DateTimeException;

import edu.pdx.cs410J.smahato.exception.AirportCodeException;
import edu.pdx.cs410J.smahato.utils.AirlineValidationUtils;
import edu.pdx.cs410J.smahato.utils.OnTextChangeWatcher;

public class AddFlightActivity extends AppCompatActivity {

    public static final String THIS_FIELD_IS_REQUIRED = "This field is required";

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
                Toast.makeText(getBaseContext(), "Flight Number contains only numbers", Toast.LENGTH_SHORT).show();
        });
        flightNumber.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                if ((keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) || keyCode == KeyEvent.KEYCODE_TAB) {
                    // numeric key pressed, do nothing
                } else {
                    // non-numeric key pressed, show a Toast message
                    Toast.makeText(getBaseContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
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

        TextWatcher enableAddFLightButton = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
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

    private boolean isEmpty(EditText editText) {
        boolean isLengthZero = isInputLengthZero(editText);
        if (isLengthZero) setRequiredError(editText);
        return isLengthZero;
    }

    private boolean isInputLengthZero(EditText editText) {
        return editText.length() == 0;
    }

    private void setRequiredError(EditText editText) {
        editText.setError(THIS_FIELD_IS_REQUIRED);
    }

    private boolean validateAirportCode(EditText source, EditText destination, String airportType) {
        if (isInputLengthZero(source)) {
            setRequiredError(source);
            return false;
        } else try {
            AirlineValidationUtils.validateAirportCode(source.getText().toString(), airportType);
            if (source.getText().toString().equalsIgnoreCase(destination.getText().toString())) {
                source.setError(SOURCE_AND_DESTINATION_CANNOT_BE_SAME);
                return false;
            }
        } catch (Exception e) {
            source.setError(e.getMessage());
            return false;
        }
        if (destination.getError() != null && destination.getError().toString().equals(SOURCE_AND_DESTINATION_CANNOT_BE_SAME)) {
            destination.setError(null);
        }
        source.setError(null);
        return true;
    }

    private boolean validateDeparture(EditText arrival, EditText departure) {
        if (isInputLengthZero(departure)) {
            setRequiredError(departure);
            return false;
        } else try {
            getDateFromString(departure.getText().toString(), DEPARTURE);
            if (isDepartureBeforeArrival(departure, arrival)) {
                departure.setError(DEPARTURE_BEFORE_ARRIVAL);
                return false;
            }
        } catch (Exception e) {
            departure.setError(e.getMessage());
            return false;
        }
        if (arrival.getError() != null && arrival.getError().toString().equals(DEPARTURE_BEFORE_ARRIVAL)) {
            arrival.setError(null);
        }
        departure.setError(null);
        return true;
    }

    private boolean validateArrival(EditText arrival, EditText departure) {
        if (isInputLengthZero(arrival)) {
            setRequiredError(arrival);
            return false;
        } else try {
            getDateFromString(arrival.getText().toString(), ARRIVAL);
            if (isDepartureBeforeArrival(departure, arrival)) {
                arrival.setError(DEPARTURE_BEFORE_ARRIVAL);
                return false;
            }
        } catch (Exception e) {
            arrival.setError(e.getMessage());
            return false;
        }
        if (departure.getError() != null && departure.getError().toString().equals(DEPARTURE_BEFORE_ARRIVAL)) {
            departure.setError(null);
        }
        arrival.setError(null);
        return true;
    }

    private boolean isDepartureBeforeArrival(EditText departure, EditText arrival) {
        if (departure.length() > 0 && arrival.length() > 0)
            return departure.getText().toString().compareTo(arrival.getText().toString()) > -1;
        return false;
    }


    public void addFlight(View view) {
        Button addFlightButton = findViewById(R.id.addFlightButton);
        EditText airlineName = findViewById(R.id.airlineNameValue);
        EditText flightNumber = findViewById(R.id.flightNumberValue);
        EditText departure = findViewById(R.id.departureValue);
        EditText source = findViewById(R.id.sourceValue);
        EditText destination = findViewById(R.id.destinationVal);
        EditText arrival = findViewById(R.id.arrivalValue);
        try {
            Flight flight = new Flight(flightNumber.getText().toString(), source.getText().toString(), destination.getText().toString(), departure.getText().toString(), arrival.getText().toString());
        } catch (DateTimeException | AirportCodeException | NullPointerException e) {
            //response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
        String str = airlineName.getText() + " " + flightNumber.getText() + " " + source.getText() + " " + departure.getText() + " " + destination.getText() + " " + arrival.getText();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
