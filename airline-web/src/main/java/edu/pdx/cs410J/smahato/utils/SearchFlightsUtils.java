package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.ParserException;

import java.io.IOException;
import java.util.List;

import static edu.pdx.cs410J.smahato.Messages.XML_PARSING_ERROR;
import static edu.pdx.cs410J.smahato.Messages.invalidHostOrPort;
import static edu.pdx.cs410J.smahato.constants.Option.*;

/**
 * SearchInputUtils extends {@link CommonInputUtils} is used to search flights
 */
public class SearchFlightsUtils extends CommonInputUtils {
  /**
   * Constant field Expected number of arguments in the input list
   */
  public static final int EXPECTED_INPUT_SIZE = 8;
  /**
   * Constant field Index of the first argument where airline name starts
   */
  public static final int START_INDEX = 5;


  /**
   * Constructor for SearchInputUtils
   *
   * @param input List of arguments
   */
  public SearchFlightsUtils(List<String> input) {
    super(input, START_INDEX, EXPECTED_INPUT_SIZE);
    if (doesInputContainsOption(PRINT.getOption())) {
      this.startIndex = this.getStartIndex() + 1;
      this.expectedNumberOfArgs = this.getExpectedNumberOfArgs() + 1;
    }
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
   * Returns the source airport code from the input list
   *
   * @return Source airport code
   */
  @Override
  public String getSource() {
    return getValueAtIndexAfterStartIndex(1);
  }


  /**
   * Returns the destination airport code from the input list
   *
   * @return Destination airport code
   */
  @Override
  public String getDestination() {
    return getValueAtIndexAfterStartIndex(2);
  }

  /**
   * make a rest call to the server, searches and pretty prints the flights
   */
  public void searchFlights() {
    if (isActualNumberOfArgsSameOrLessThanExpected()) {
      try {
        this.client.searchFlights(getAirlineName(), getSource(), getDestination());
      } catch (IOException e) {
        System.err.println(invalidHostOrPort(getOptionValue(HOST.getOption()), getOptionValue(PORT.getOption())));
      } catch (ParserException e) {
        System.err.println(XML_PARSING_ERROR);
      }
    }
  }
}
