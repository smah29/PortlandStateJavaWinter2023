package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.smahato.Airline;
import edu.pdx.cs410J.smahato.PrettyPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.Options.*;

/**
 * P3InputUtils extends {@link P2InputUtils} has additional logic of pretty printing
 */
public class P3InputUtils extends P2InputUtils {
  /**
   * Constant field Expected number of arguments in the input list ex 15 in Project 3
   */
  public static final int EXPECTED_INPUT_SIZE = 15;
  /**
   * Constant field Index of the first argument where airline name starts ex 5 in Project 3
   */
  public static final int START_INDEX = 5;

  /**
   * Constructor for P3InputUtils, set the expected number of arguments and start index to Project 3 specific values
   *
   * @param input List of arguments
   */
  public P3InputUtils(List<String> input) {
    super(input, EXPECTED_INPUT_SIZE, START_INDEX);
    checkForTextFileOption();
  }

  /**
   * Constructor for P3InputUtils used by {@link P4InputUtils}
   *
   * @param input                List of arguments
   * @param expectedNumberOfArgs Expected number of arguments according to Project ex 15 in Project 3
   * @param startIndex           Index of the first argument where airline name starts ex 5 in Project 3
   */
  public P3InputUtils(List<String> input, int expectedNumberOfArgs, int startIndex) {
    super(input, expectedNumberOfArgs, startIndex);
    checkForTextFileOption();
  }

  private void checkForTextFileOption() {
    if (!doesInputContainsTextFileOption()) {
      this.expectedNumberOfArgs = this.getExpectedNumberOfArgs() - 2;
      this.startIndex = this.getStartIndex() - 2;
    }
  }

  /**
   * @param airline which will be dumped earlier is std out or file
   * @return true/ false based on whether the newly added file has to be printed after this
   */
  public boolean prettyPrintAirline(Airline airline) {
    Airline airlineFromTextFile = null;
    if (doesInputContainsTextFileOption()) {
      airlineFromTextFile = saveAirlineToFile(airline);
    } else {
      airlineFromTextFile = airline;
    }
    if (airlineFromTextFile != null) {
      String prettyFilePath = getFilePath(PRETTY.getOption());
      if (prettyFilePath.equals("-")) {
        if (this.input.contains(PRINT.getOption()))
          System.out.println(new ArrayList<>(airline.getFlights()).get(0));
        new PrettyPrinter(new PrintWriter(System.out)).dump(airlineFromTextFile);
        return false;
      } else {
        File prettyFile = new File(prettyFilePath);
        try {
          new PrettyPrinter(new FileWriter(prettyFile)).dump(airlineFromTextFile);
          return true;
        } catch (IOException e) {
          System.err.println("Error creating file: " + prettyFilePath);
        }
      }
    }
    return false;
  }

  private boolean doesInputContainsTextFileOption() {
    return this.input.contains(TEXT_FILE.getOption());
  }
}