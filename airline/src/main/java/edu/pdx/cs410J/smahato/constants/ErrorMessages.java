package edu.pdx.cs410J.smahato.constants;

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.ARRIVAL;
import static edu.pdx.cs410J.smahato.constants.AirlineConstants.DEPARTURE;

/**
 * This class contains error messages used in the airline project
 */
public interface ErrorMessages {
  /**
   * Error message for invalid command line arguments, Redirection to use -README option
   */
  String README_MESSAGE = " Use options -README to get help" + "\n";
  /**
   * Missing command line arguments!
   */
  String MISSING_COMMAND_LINE_ARGS = "Missing command line arguments!" + README_MESSAGE;
  /**
   * Extra command line arguments!
   */
  String EXTRA_COMMAND_LINE_ARGS = "Extra command line arguments!" + README_MESSAGE;

  /**
   * must be exactly 3 characters long
   */
  String MUST_BE_EXACTLY_3_CHARACTERS_LONG = " must be exactly 3 characters long";
  /**
   * Source and destination cannot be same
   */
  String SOURCE_AND_DESTINATION_CANNOT_BE_SAME = "Source and destination cannot be same";
  /**
   * must contain only alphabets
   */
  String MUST_CONTAIN_ONLY_ALPHABETS = " must contain only alphabets";
  /**
   * cannot be null or empty
   */
  String CANNOT_BE_NULL_OR_EMPTY = " cannot be null or empty";

  /**
   * Airline name doesn't match with the one in the file!
   */
  String AIRLINE_NAME_MISMATCH = "Airline name doesn't match with the one in the text file!";
  /**
   * Flight number must be an integer!
   */
  String FLIGHT_NUMBER_MUST_BE_AN_INTEGER = "Flight number must be an integer!";
  /**
   * Airport code must be a valid airport code, it is not present in names map of {@link edu.pdx.cs410J.AirportNames} class
   */
  String MUST_BE_A_VALID_AIRPORT_CODE = " must be a valid airport code";

  String DEPARTURE_BEFORE_ARRIVAL = DEPARTURE + " should be before " + ARRIVAL;
}
