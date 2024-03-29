package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.smahato.AirlineRestClient;
import edu.pdx.cs410J.smahato.constants.Option;

import java.util.Arrays;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.*;

/**
 * This class is used to get the input from the command line and parse it
 */
public class CommonInputUtils implements InputUtils {
  private final int inputSize;
  protected final List<String> input;
  /**
   * Expected number of arguments in the input list
   */
  protected int expectedNumberOfArgs;
  /**
   * Index of the first argument where airline name starts
   */
  protected int startIndex;
  /**
   * Airline Rest Client
   */
  protected final AirlineRestClient client;

  /**
   * Constructor to initialize the input list and expected number of arguments in the input list and index of the first argument where airline name starts
   *
   * @param input                List of input arguments
   * @param startIndex           Index of the first argument where airline name starts
   * @param expectedNumberOfArgs Expected number of arguments in the input list
   */
  protected CommonInputUtils(List<String> input, int startIndex, int expectedNumberOfArgs) {
    this.input = input;
    this.inputSize = input.size();
    String host = getOptionValue(Option.HOST.getOption());
    try {
      int port = Integer.parseInt(getOptionValue(Option.PORT.getOption()));
      this.client = new AirlineRestClient(host, port);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(PORT_NUMBER_SHOULD_BE_AN_INTEGER);
    }
    this.startIndex = startIndex;
    this.expectedNumberOfArgs = expectedNumberOfArgs;
  }

  /**
   * @return the start index of the airline name in the input list
   */
  protected int getStartIndex() {
    return startIndex;
  }

  /**
   * @return the expected number of arguments in the input list
   */
  protected int getExpectedNumberOfArgs() {
    return expectedNumberOfArgs;
  }


  /**
   * Returns the value at the given index in the input list
   *
   * @return String by coming all given index values with space as separator
   */
  protected String getValueAtIndexAfterStartIndex(int... indexArray) {
    return getValueAtIndex(startIndex, indexArray);
  }

  protected String getOptionValue(String option) {
    int index = input.indexOf(option);
    if (index == -1) {
      throw new IllegalArgumentException(option + OPTION_MISSING);
    }
    if (index + 1 >= this.inputSize) {
      throw new IllegalArgumentException(option + OPTION_VALUE_MISSING);
    }
    return this.input.get(index + 1);
  }

  private String getValueAtIndex(int startIndex, int... indexArray) {
    int inputSize = this.inputSize;
    return Arrays.stream(indexArray).filter(idx -> (startIndex + idx) < inputSize).mapToObj(index -> input.get(startIndex + index)).reduce("", (a, b) -> a + " " + b).trim();
  }


  /**
   * Returns the airline name from the input list
   *
   * @return Airline name
   */
  @Override
  public String getAirlineName() {
    return getValueAtIndexAfterStartIndex(0);
  }

  /**
   * Returns the flight number from the input list acc to Project 1 ex  at index 1
   *
   * @return Flight number
   */
  @Override
  public String getFlightNumber() {
    return getValueAtIndexAfterStartIndex(1);
  }

  /**
   * Returns the source airport code from the input list acc to Project 1 ex  at index 2
   *
   * @return Source airport code
   */
  @Override
  public String getSource() {
    return getValueAtIndexAfterStartIndex(2);
  }

  /**
   * Returns the departure date and time from the input list acc to Project 1 ex  at index 3 and 4
   *
   * @return Departure date and time
   */
  @Override
  public String getDepartureString() {
    return getValueAtIndexAfterStartIndex(3, 4, 5);
  }

  /**
   * Returns the destination airport code from the input list acc to Project 1 ex  at index 5
   *
   * @return Destination airport code
   */
  @Override
  public String getDestination() {
    return getValueAtIndexAfterStartIndex(6);
  }

  /**
   * Returns the arrival date and time from the input list acc to Project 1 ex  at index 6 and 7
   *
   * @return Arrival date and time
   */
  @Override
  public String getArrivalString() {
    return getValueAtIndexAfterStartIndex(7, 8, 9);
  }

  /**
   * checks if the input list contains the option
   *
   * @param option Option to be checked
   * @return true if the input list contains the option
   */
  protected boolean doesInputContainsOption(String option) {
    return input.contains(option);
  }

  /**
   * Checks if the number of arguments are correct
   *
   * @param actualNumberOfArgs   Number of arguments in the input list
   * @param expectedNumberOfArgs Expected number of arguments according to Project ex 9 in Project 1 and 11 in Project 2
   * @return 1, 0,-1 if the number of arguments are less, equal or greater than expected
   */
  private int compare(int actualNumberOfArgs, int expectedNumberOfArgs) {
    if (actualNumberOfArgs > expectedNumberOfArgs) {
      return 1;
    }
    if (actualNumberOfArgs != expectedNumberOfArgs) {
      return -1;
    }
    return 0;
  }

  /**
   * checks if the actual number of arguments is same or less than expected number of arguments
   *
   * @return true if actual number of arguments is same or less than expected number of arguments else false and prints the error message accordingly
   */
  protected boolean isActualNumberOfArgsSameOrLessThanExpected() {
    int compare = compare(this.inputSize, this.expectedNumberOfArgs);
    if (compare > 0) {
      if (input.stream().filter(val -> val.startsWith("-")).anyMatch(val -> !Option.exists(val))) {
        System.err.print(UNKNOWN_OPTION);
      } else {
        System.err.print(EXTRA_COMMAND_LINE_ARGS);
      }
      return false;
    } else {
      return true;
    }
  }
}
