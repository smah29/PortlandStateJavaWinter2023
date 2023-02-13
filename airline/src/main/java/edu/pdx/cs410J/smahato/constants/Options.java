package edu.pdx.cs410J.smahato.constants;

import java.util.Arrays;

/**
 * This enum contains all the options that can be used in the airline project
 */
public enum Options {
  /**
   * Prints only the README.txt file
   */
  README("-README"),
  /**
   * If print option is not present, then flights information is not printed
   */
  PRINT("-print"),
  /**
   * If textFile option is not present, then flights information is not dumped to a file
   */
  TEXT_FILE("-textFile"),
  /**
   * If pretty option is not present, then flights information is not be pretty printed
   */
  PRETTY("-pretty");
  /**
   * Option value
   */
  final String option;

  /**
   * Constructor
   *
   * @param option Option value
   */
  Options(String option) {
    this.option = option;
  }

  /**
   * Returns the option value
   *
   * @return Option value
   */
  public String getOption() {
    return option;
  }

  /**
   * Checks if the option is present in the input list
   *
   * @param val Option value
   * @return true if the option is present in the input list, false otherwise
   */
  public static boolean exists(String val) {
    return Arrays.stream(values()).anyMatch(option -> option.getOption().equals(val));
  }
}
