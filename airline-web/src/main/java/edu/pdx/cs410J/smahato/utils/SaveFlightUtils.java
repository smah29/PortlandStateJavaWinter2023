package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.smahato.Flight;

import java.io.IOException;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.invalidHostOrPort;
import static edu.pdx.cs410J.smahato.constants.Option.*;

/**
 * This class is used to get the input from the command line and save the flight
 * extends {@link CommonInputUtils}
 */
public class SaveFlightUtils extends CommonInputUtils {
  /**
   * Constant field Expected number of arguments in the input list
   */
  public static final int EXPECTED_INPUT_SIZE = 15;
  /**
   * Constant field Index of the first argument where airline name starts
   */
  public static final int START_INDEX = 5;

  /**
   * Constructor for SaveFlightUtils
   *
   * @param input List of arguments
   */
  public SaveFlightUtils(List<String> input) {
    super(input, START_INDEX, EXPECTED_INPUT_SIZE);
    if (!doesInputContainsOption(PRINT.getOption())) {
      this.expectedNumberOfArgs = this.getExpectedNumberOfArgs() - 1;
      this.startIndex = this.getStartIndex() - 1;
    }
  }

  /**
   * saves the flight and prints it if the print option is present
   */
  public void saveFlight() {
    if (isActualNumberOfArgsSameOrLessThanExpected()) {
      try {
        this.client.saveFlight(getAirlineName(), getFlightNumber(), getSource(), getDestination(), getDepartureString(), getArrivalString());
        if (doesInputContainsOption(PRINT.getOption()))
          System.out.println(new Flight(getFlightNumber(), getSource(), getDestination(), getDepartureString(), getArrivalString()));
      } catch (IOException e) {
        System.err.println(invalidHostOrPort(getOptionValue(HOST.getOption()), getOptionValue(PORT.getOption())));
      }
    }
  }
}
