package edu.pdx.cs410J.smahato;

/**
 * This exception is thrown when the airport code is not valid, it must be 3-letter code, source and destination cannot be same
 */
public class AirportCodeException extends RuntimeException {
  public AirportCodeException(String message) {
    super(message);
  }
}
