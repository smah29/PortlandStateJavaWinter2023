package edu.pdx.cs410J.smahato;

/**
 * This class contains constants used in the airline project
 */
public interface AirlineConstants {
  String ALPHABETS = "[a-zA-Z]+";
  int AIRPORT_CODE_LENGTH = 3;
  String TARGET = "{0}";
  String CANNOT_BE_NULL_OR_EMPTY = TARGET + " cannot be null or empty";
  String MUST_BE_EXACTLY_3_CHARACTERS_LONG = TARGET + " must be exactly 3 characters long";
  String MUST_CONTAIN_ONLY_ALPHABETS = TARGET + " must contain only alphabets";
  String SOURCE = "Source";
  String DESTINATION = "Destination";
}
