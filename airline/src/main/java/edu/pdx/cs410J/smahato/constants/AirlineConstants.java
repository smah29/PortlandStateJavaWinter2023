package edu.pdx.cs410J.smahato.constants;

/**
 * This class contains constants used in the airline project
 */
public interface AirlineConstants {
  /**
   * Airport code regex
   */
  String ALPHABETS = "[a-zA-Z]+";
  /**
   * Airport code length
   */
  int AIRPORT_CODE_LENGTH = 3;
  String SOURCE = "Source";
  String DESTINATION = "Destination";
  String DEPARTURE = "Departure";
  String ARRIVAL = "Arrival";

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
  String AIRLINE_NAME_MISMATCH = "Airline name doesn't match with the one in the file!";
  /**
   * Flight number must be an integer!
   */
  String FLIGHT_NUMBER_MUST_BE_AN_INTEGER = "Flight number must be an integer!";
  /**
   * Airport code must be a valid airport code, it is not present in names map of {@link edu.pdx.cs410J.AirportNames} class
   */
  String MUST_BE_A_VALID_AIRPORT_CODE = " must be a valid airport code";
}
