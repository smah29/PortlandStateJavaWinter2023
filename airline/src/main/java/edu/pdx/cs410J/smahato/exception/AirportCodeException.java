package edu.pdx.cs410J.smahato.exception;

/**
 * This exception is thrown when the airport code is not valid, it must be 3-letter code, source and destination cannot be same
 */
public class AirportCodeException extends RuntimeException {
  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message The detail message.
   */
  public AirportCodeException(String message) {
    super(message);
  }
}
