package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.smahato.Airline;

import java.util.Arrays;
import java.util.List;

/**
 * Interface InputUtils contains abstract methods to get values (airline and flight information) from arguments
 * and check if the number of arguments are correct
 * <p>
 * This interface is implemented by {@link P1InputUtils}
 */
public interface InputUtils {
  /**
   * Returns the value at the given index in the input list
   *
   * @param input      List of arguments
   * @param startIndex Index of the first argument where airline name starts
   * @param indexArray Array of indices of arguments which needs to be concatenated like date and time
   * @return String by coming all given index values with space as separator
   */
  default String getValueAtIndex(List<String> input, int startIndex, int... indexArray) {
    int inputSize = input.size();
    return Arrays.stream(indexArray).filter(idx -> (startIndex + idx) < inputSize).mapToObj(index -> input.get(startIndex + index)).reduce("", (a, b) -> a + " " + b).trim();
  }

  /**
   * checks if the input list contains the option
   * @param input
   * @param option
   * @return true if the input list contains the option
   */
  default boolean doesInputContainsOption(List<String> input, String option) {
    return input.contains(option);
  }

  /**
   * Checks if the number of arguments are correct
   *
   * @param actualNumberOfArgs   Number of arguments in the input list
   * @param expectedNumberOfArgs Expected number of arguments according to Project ex 9 in Project 1 and 11 in Project 2
   * @return 1, 0,-1 if the number of arguments are less, equal or greater than expected
   */
  default int compare(int actualNumberOfArgs, int expectedNumberOfArgs) {
    if (actualNumberOfArgs > expectedNumberOfArgs) {
      return 1;
    }
    if (actualNumberOfArgs != expectedNumberOfArgs) {
      return -1;
    }
    return 0;
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
  String getFlightNumber();

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
