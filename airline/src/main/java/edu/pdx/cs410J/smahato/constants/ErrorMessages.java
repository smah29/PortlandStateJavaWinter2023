package edu.pdx.cs410J.smahato.constants;

/**
 * This class contains error messages used in the airline project
 */
public interface ErrorMessages {
  /**
   * Error message for invalid command line arguments, Redirection to use -README option
   */
  String README_MESSAGE = " Use options -README to get help" + "\n";
  /**
   * Missing command line arguments!
   */
  String MISSING_COMMAND_LINE_ARGS = "Missing command line arguments!" + README_MESSAGE;
  /**
   * Extra command line arguments!
   */
  String EXTRA_COMMAND_LINE_ARGS = "Extra command line arguments!" + README_MESSAGE;
}
