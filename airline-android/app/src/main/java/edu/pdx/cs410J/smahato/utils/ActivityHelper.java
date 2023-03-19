package edu.pdx.cs410J.smahato.utils;

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.ARRIVAL;
import static edu.pdx.cs410J.smahato.constants.AirlineConstants.DEPARTURE;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.DEPARTURE_BEFORE_ARRIVAL;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.SOURCE_AND_DESTINATION_CANNOT_BE_SAME;
import static edu.pdx.cs410J.smahato.constants.Messages.MISSING_SRC_DEST;
import static edu.pdx.cs410J.smahato.utils.DateTimeUtils.getDateFromString;

import android.widget.EditText;

public class ActivityHelper {

    public static final String THIS_FIELD_IS_REQUIRED = "This field is required";

    public static boolean isEmpty(EditText editText) {
        boolean isLengthZero = isInputLengthZero(editText);
        if (isLengthZero) setRequiredError(editText);
        return isLengthZero;
    }

    public static boolean isInputLengthZero(EditText editText) {
        return editText.length() == 0;
    }

    public static void setRequiredError(EditText editText) {
        editText.setError(THIS_FIELD_IS_REQUIRED);
    }

    public static boolean validateAirportCode(EditText source, EditText destination, String airportType) {
        if (isInputLengthZero(source)) {
            setRequiredError(source);
            return false;
        } else {
            return validateNonEmptyAirportCode(source, destination, airportType);
        }
    }

    public static boolean validateAirportForSearch(EditText source, EditText destination, String airportType) {
        if (isInputLengthZero(source) && isInputLengthZero(destination)) {
            return true;
        } else if (!isInputLengthZero(source)) {
            return validateNonEmptyAirportCode(source, destination, airportType);
        } else {
            source.setError(MISSING_SRC_DEST);
            return false;
        }
    }

    private static boolean validateNonEmptyAirportCode(EditText source, EditText destination, String airportType) {
        try {
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

    public static boolean validateDeparture(EditText arrival, EditText departure) {
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

    public static boolean validateArrival(EditText arrival, EditText departure) {
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

    public static boolean isDepartureBeforeArrival(EditText departure, EditText arrival) {
        if (departure.length() > 0 && arrival.length() > 0)
            return departure.getText().toString().compareTo(arrival.getText().toString()) > -1;
        return false;
    }
}
