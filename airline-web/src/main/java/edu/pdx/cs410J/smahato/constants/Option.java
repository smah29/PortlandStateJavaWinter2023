package edu.pdx.cs410J.smahato.constants;

import java.util.Arrays;

/**
 * This enum contains all the options that can be used in the airline project
 */
public enum Option {
  /**
   * Prints only the README.txt file
   */
  README("-README"),
  /**
   * If search option is present, then flights are searched for the given airline
   */
  SEARCH("-search"),
  /**
   * host of application ex localhost
   */
  HOST("-host"),
  /**
   * port of application ex 8080
   */
  PORT("-port"),
  /**
   * If print option is not present, then added flight information is not printed to the console
   */
  PRINT("-print");
  /**
   * Option value
   */
  final String option;

  /**
   * Constructor
   *
   * @param option Option value
   */
  Option(String option) {
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
