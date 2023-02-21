package edu.pdx.cs410J.smahato.exception;

/**
 * This class is used to throw XML related exceptions.
 */
public class XMLException extends RuntimeException {
  /**
   * Creates a new <code>XMLException</code> object.
   *
   * @param message The message to be displayed
   */
  public XMLException(String message) {
    super(message);
  }
}
