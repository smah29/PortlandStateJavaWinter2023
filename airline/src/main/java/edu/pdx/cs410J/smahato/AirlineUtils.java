package edu.pdx.cs410J.smahato;

import java.util.HashSet;
import java.util.Set;

import static edu.pdx.cs410J.smahato.AirlineConstants.*;

/**
 * This class contains utility methods for validating airline data
 */
public class AirlineUtils {

  /**
   * Validate the airport codes
   *
   * @param airportCodes <p>array of airport codes and their types ex source/destination</p>
   */
  public static void validateAirportCodes(String[][] airportCodes) {
    Set<String> uniqueAirportCodes = new HashSet<>();
    for (String[] airportCode : airportCodes) {
      // HasSet returns true if this set did not already contain the specified element
      if (!uniqueAirportCodes.add(airportCode[0])) {
        throw new AirportCodeException("Source and destination cannot be same");
      }
      airportCodeValidation(airportCode[0], airportCode[1]);
    }
  }

  /**
   * Validate the airport code
   * <p>airport code cannot be null or empty</p>
   * <p>airport code must be exactly 3 characters long</p>
   * <p>airport code must contain only alphabets</p>
   *
   * @param airportCode <p>airport code given as input by the user</p>
   * @param airportType <p>airport type such as source or destination</p>
   */
  private static void airportCodeValidation(String airportCode, String airportType) {
    airportCodeNullCheck(airportCode, airportType);
    airportCodeLengthCheck(airportCode, airportType);
    airportCodeAlphabeticCheck(airportCode, airportType);
  }

  /**
   * Validate the airport code
   * <p>airport code must contain only alphabets</p>
   *
   * @param airportCode <p>airport code given as input by the user</p>
   * @param airportType <p>airport type such as source or destination</p>
   */
  private static void airportCodeAlphabeticCheck(String airportCode, String airportType) {
    if (!airportCode.matches(ALPHABETS)) {
      throw new AirportCodeException(airportType + " must contain only alphabets");
    }
  }

  /**
   * Validate the airport code
   * <p>airport code must be exactly 3 characters long</p>
   *
   * @param airportCode <p>airport code given as input by the user</p>
   * @param airportType <p>airport type such as source or destination</p>
   */
  private static void airportCodeLengthCheck(String airportCode, String airportType) {
    if (airportCode.length() != AIRPORT_CODE_LENGTH) {
      throw new AirportCodeException(airportType + " must be exactly 3 characters long");
    }
  }

  /**
   * Validate the airport code
   * <p>airport code cannot be null or empty</p>
   *
   * @param airportCode <p>airport code given as input by the user</p>
   * @param airportType <p>airport type such as source or destination</p>
   */
  private static void airportCodeNullCheck(String airportCode, String airportType) {
    if (airportCode == null || airportCode.isEmpty())
      throw new NullPointerException(airportType + " cannot be null or empty");
  }
}
