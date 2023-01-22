package edu.pdx.cs410J.smahato;

/**
 * This class contains constants used in the airline project
 */
public interface AirlineConstants {
  String ALPHABETS = "[a-zA-Z]+";
  int AIRPORT_CODE_LENGTH = 3;
  String SOURCE = "Source";
  String DESTINATION = "Destination";
  String DEPARTURE = "Departure";
  String ARRIVAL = "Arrival";

  String MUST_BE_EXACTLY_3_CHARACTERS_LONG = " must be exactly 3 characters long";
  String SOURCE_AND_DESTINATION_CANNOT_BE_SAME = "Source and destination cannot be same";
  String MUST_CONTAIN_ONLY_ALPHABETS = " must contain only alphabets";

  String CANNOT_BE_NULL_OR_EMPTY = " cannot be null or empty";
}
