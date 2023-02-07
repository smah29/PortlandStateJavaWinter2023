package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.AirportNames;
import edu.pdx.cs410J.smahato.exception.AirportCodeException;

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.AIRPORT_CODE_LENGTH;
import static edu.pdx.cs410J.smahato.constants.AirlineConstants.ALPHABETS;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.*;

/**
 * This class contains utility methods for validating airline data
 */
public class AirlineValidationUtils {

  /**
   * Validate the airport code
   * <p>airport code cannot be null or empty</p>
   * <p>airport code must be exactly 3 characters long</p>
   * <p>airport code must contain only alphabets</p>
   *
   * @param airportCode <p>airport code given as input by the user</p>
   * @param airportType <p>airport type such as source or destination</p>
   */
  public static void validateAirportCode(String airportCode, String airportType) {
    airportCodeNullCheck(airportCode, airportType);
    airportCodeLengthCheck(airportCode, airportType);
    airportCodeAlphabeticCheck(airportCode, airportType);
    airportCodeExistsInAirportNames(airportCode, airportType);
  }

  /**
   * Validate the airport code
   * <p>airport code must match the AirportNames map keys</p>
   *
   * @param airportCode <p>airport code given as input by the user</p>
   * @param airportType <p>airport type such as source or destination</p>
   */
  private static void airportCodeExistsInAirportNames(String airportCode, String airportType) {
    if (AirportNames.getName(airportCode.toUpperCase()) == null) {
      throw new AirportCodeException(airportType + MUST_BE_A_VALID_AIRPORT_CODE);
    }
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
      throw new AirportCodeException(airportType + MUST_CONTAIN_ONLY_ALPHABETS);
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
      throw new AirportCodeException(airportType + MUST_BE_EXACTLY_3_CHARACTERS_LONG);
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
      throw new NullPointerException(airportType + CANNOT_BE_NULL_OR_EMPTY);
  }
}
