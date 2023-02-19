package edu.pdx.cs410J.smahato.utils;

import java.util.List;

import static edu.pdx.cs410J.smahato.constants.Options.PRETTY;

/**
 * P4InputUtils extends {@link P3InputUtils} has additional logic of xml file
 */
public class P4InputUtils extends P3InputUtils {
  /**
   * Constant field Expected number of arguments in the input list ex 17 in Project 4
   */
  public static final int EXPECTED_INPUT_SIZE = 17;
  /**
   * Constant field Index of the first argument where airline name starts ex 7 in Project 4
   */
  public static final int START_INDEX = 7;

  /**
   * Constructor for P4InputUtils, set the expected number of arguments and start index to Project 4 specific values
   *
   * @param input List of arguments
   */
  public P4InputUtils(List<String> input) {
    super(input, EXPECTED_INPUT_SIZE, START_INDEX);
    checkForPrettyOption();
  }

  private void checkForPrettyOption() {
    if (!doesInputContainsPrettyOption()) {
      this.expectedNumberOfArgs = this.getExpectedNumberOfArgs() - 2;
      this.startIndex = this.getStartIndex() - 2;
    }
  }

  private boolean doesInputContainsPrettyOption() {
    return this.input.contains(PRETTY.getOption());
  }
}
