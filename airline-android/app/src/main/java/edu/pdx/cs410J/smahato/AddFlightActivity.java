package edu.pdx.cs410J.smahato;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.DateTimeException;

import edu.pdx.cs410J.smahato.exception.AirportCodeException;
import edu.pdx.cs410J.smahato.utils.AfterTextChangeWatcher;
import edu.pdx.cs410J.smahato.utils.OnTextChangeWatcher;

public class AddFlightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_flight);
        Button addFlightButton = findViewById(R.id.addFlightButton);
        addFlightButton.setEnabled(false);
        EditText airlineName = findViewById(R.id.airlineNameValue);
        EditText flightNumber = findViewById(R.id.flightNumberValue);
        EditText source = findViewById(R.id.sourceValue);
        EditText departure = findViewById(R.id.departureValue);
        EditText destination = findViewById(R.id.destinationVal);
        EditText arrival = findViewById(R.id.arrivalValue);
        emptyCheck(airlineName);
        emptyCheck(flightNumber);
        emptyCheck(source);
        emptyCheck(departure);
        emptyCheck(destination);
        emptyCheck(arrival);
        TextWatcher enableSubmitButtonOnlyIfAllFieldsAreFilled = new OnTextChangeWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean enableSubmitButton = airlineName.getText().length() > 0 &&
                        flightNumber.getText().length() > 0 &&
                        source.getText().length() > 0 &&
                        departure.getText().length() > 0 &&
                        destination.getText().length() > 0 &&
                        arrival.getText().length() > 0;
                addFlightButton.setEnabled(enableSubmitButton);
            }
        };
        airlineName.addTextChangedListener(enableSubmitButtonOnlyIfAllFieldsAreFilled);
        flightNumber.addTextChangedListener(enableSubmitButtonOnlyIfAllFieldsAreFilled);
        source.addTextChangedListener(enableSubmitButtonOnlyIfAllFieldsAreFilled);
        departure.addTextChangedListener(enableSubmitButtonOnlyIfAllFieldsAreFilled);
        destination.addTextChangedListener(enableSubmitButtonOnlyIfAllFieldsAreFilled);
        arrival.addTextChangedListener(enableSubmitButtonOnlyIfAllFieldsAreFilled);

    }

    private void emptyCheck(EditText editText) {
        editText.addTextChangedListener(new AfterTextChangeWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0)
                    editText.setError("This field is required");
            }
        });
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
            Flight flight = new Flight(flightNumber.getText().toString(), source.getText().toString(),
                    destination.getText().toString(), departure.getText().toString(), arrival.getText().toString());
        } catch (DateTimeException | AirportCodeException | NullPointerException e) {
            //response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
        String str = airlineName.getText() + " " + flightNumber.getText() + " " + source.getText() + " "
                + departure.getText() + " " + destination.getText() + " " + arrival.getText();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
