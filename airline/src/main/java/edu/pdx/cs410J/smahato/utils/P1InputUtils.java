package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.smahato.Airline;
import edu.pdx.cs410J.smahato.exception.AirportCodeException;
import edu.pdx.cs410J.smahato.Flight;

import java.time.DateTimeException;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.FLIGHT_NUMBER_MUST_BE_AN_INTEGER;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.README_MESSAGE;

/**
 * P1InputUtils implements {@link InputUtils} is used to get the input from the command line arguments.
 * <p> It has Project 1 specific methods to get airline and flight information from the input list
 */
public class P1InputUtils implements InputUtils {
  /**
   * Constant field Expected number of arguments in the input list
   */
  public static final int EXPECTED_INPUT_SIZE = 9;
  /**
   * Constant field Index of the first argument where airline name starts
   */
  public static final int START_INDEX = 1;
  /**
   * List of arguments
   */
  public final List<String> input;
  /**
   * Expected number of arguments according to Project ex 9 in Project 1
   */
  public int expectedNumberOfArgs;
  /**
   * Index of the first argument where airline name starts ex 1 in Project 1
   */
  public int startIndex;

  /**
   * Constructor for P1InputUtils
   *
   * @param input List of arguments
   */
  public P1InputUtils(List<String> input) {
    this(input, EXPECTED_INPUT_SIZE, START_INDEX);
  }

  /**
   * Constructor for P1InputUtils
   *
   * @param input                List of arguments
   * @param expectedNumberOfArgs Expected number of arguments according to Project ex 9 in Project 1
   * @param startIndex           Index of the first argument where airline name starts ex 1 in Project 1
   */
  public P1InputUtils(List<String> input, int expectedNumberOfArgs, int startIndex) {
    this.input = input;
    this.expectedNumberOfArgs = expectedNumberOfArgs;
    this.startIndex = startIndex;
  }

  public int getExpectedNumberOfArgs() {
    return expectedNumberOfArgs;
  }

  public int getStartIndex() {
    return startIndex;
  }

  public void setExpectedNumberOfArgs(int expectedNumberOfArgs) {
    this.expectedNumberOfArgs = expectedNumberOfArgs;
  }

  public void setStartIndex(int startIndex) {
    this.startIndex = startIndex;
  }

  /**
   * Returns the airline name from the input list
   *
   * @return Airline name
   */
  @Override
  public String getAirlineName() {
    return getValueAtIndex(input, startIndex, 0);
  }

  /**
   * Returns the flight number from the input list acc to Project 1 ex  at index 1
   *
   * @return Flight number
   */
  @Override
  public int getFlightNumber() {
    return Integer.parseInt(getValueAtIndex(input, startIndex, 1));
  }

  /**
   * Returns the source airport code from the input list acc to Project 1 ex  at index 2
   *
   * @return Source airport code
   */
  @Override
  public String getSource() {
    return getValueAtIndex(input, startIndex, 2);
  }

  /**
   * Returns the departure date and time from the input list acc to Project 1 ex  at index 3 and 4
   *
   * @return Departure date and time
   */
  @Override
  public String getDepartureString() {
    return getValueAtIndex(input, startIndex, 3, 4);
  }

  /**
   * Returns the destination airport code from the input list acc to Project 1 ex  at index 5
   *
   * @return Destination airport code
   */
  @Override
  public String getDestination() {
    return getValueAtIndex(input, startIndex, 5);
  }

  /**
   * Returns the arrival date and time from the input list acc to Project 1 ex  at index 6 and 7
   *
   * @return Arrival date and time
   */
  @Override
  public String getArrivalString() {
    return getValueAtIndex(input, startIndex, 6, 7);
  }

  /**
   * checks if the actual number of arguments is same as expected number of arguments
   *
   * @return true if actual number of arguments is same as expected number of arguments else false
   */
  public boolean isActualNumberOfArgsSameAsExpected() {
    return isActualNumberOfArgsSameAsExpected(input.size(), this.expectedNumberOfArgs);
  }

  /**
   * creates an {@link Airline} object from the input list and add the flight to the airline
   *
   * @return {@link Airline} object
   */
  @Override
  public Airline getAirline() {
    if (isActualNumberOfArgsSameAsExpected()) {
      try {
        Airline airline = new Airline(getAirlineName());

        Flight flight = new Flight(getFlightNumber(), getSource(), getDestination(), getDepartureString(), getArrivalString());

        airline.addFlight(flight);

        return airline;
      } catch (NumberFormatException e) {
        System.err.println(FLIGHT_NUMBER_MUST_BE_AN_INTEGER + README_MESSAGE);
      } catch (DateTimeException | AirportCodeException | NullPointerException e) {
        System.err.println(e.getMessage() + README_MESSAGE);
      }
    }
    return null;
  }
}
