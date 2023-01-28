package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.smahato.Airline;

import java.util.Arrays;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.EXTRA_COMMAND_LINE_ARGS;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.MISSING_COMMAND_LINE_ARGS;

/**
 * Interface InputUtils contains abstract methods to get values (airline and flight information) from arguments
 * and check if the number of arguments are correct
 * <p>
 * This interface is implemented by {@link P1InputUtils} and {@link P2InputUtils}
 */
public interface InputUtils {
  /**
   * Returns the value at the given index in the input list
   *
   * @param input      List of arguments
   * @param startIndex Index of the first argument where airline name starts
   * @param indexArray Array of indices of arguments which needs to be concatenated like date and time
   * @return
   */
  default String getValueAtIndex(List<String> input, int startIndex, int... indexArray) {
    return Arrays.stream(indexArray).mapToObj(index -> input.get(startIndex + index)).reduce("", (a, b) -> a + " " + b).trim();
  }

  /**
   * Checks if the number of arguments are correct
   *
   * @param actualNumberOfArgs   Number of arguments in the input list
   * @param expectedNumberOfArgs Expected number of arguments according to Project ex 9 in Project 1 and 11 in Project 2
   * @return true if the number of arguments are correct, false otherwise, and prints the error message accordingly
   */
  default boolean isActualNumberOfArgsSameAsExpected(int actualNumberOfArgs, int expectedNumberOfArgs) {
    if (actualNumberOfArgs > expectedNumberOfArgs) {
      System.err.print(EXTRA_COMMAND_LINE_ARGS);
      return false;
    }
    if (actualNumberOfArgs != expectedNumberOfArgs) {
      System.err.print(MISSING_COMMAND_LINE_ARGS);
      return false;
    }
    return true;
  }

  /**
   * Returns the airline name from the input list
   *
   * @return Airline object
   */
  String getAirlineName();

  /**
   * Returns the flight number from the input list
   *
   * @return Flight number
   */
  int getFlightNumber();

  /**
   * Returns the source from the input list
   *
   * @return Source
   */
  String getSource();

  /**
   * Returns the departure time from the input list
   *
   * @return Departure time
   */
  String getDepartureString();

  /**
   * Returns the destination from the input list
   *
   * @return Destination
   */
  String getDestination();

  /**
   * Returns the arrival time from the input list
   *
   * @return Arrival time
   */
  String getArrivalString();

  /**
   * Returns the airline object
   *
   * @return Airline object
   */
  Airline getAirline();
}
