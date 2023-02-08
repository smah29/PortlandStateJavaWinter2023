package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.smahato.Airline;
import edu.pdx.cs410J.smahato.PrettyPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.OptionConstants.*;

public class P3InputUtils extends P2InputUtils {
  /**
   * Constant field Expected number of arguments in the input list ex 11 in Project 2
   */
  public static final int EXPECTED_INPUT_SIZE = 15;
  /**
   * Constant field Index of the first argument where airline name starts ex 3 in Project 2
   */
  public static final int START_INDEX = 5;

  /**
   * Constructor for P2InputUtils, set the expected number of arguments and start index to Project 2 specific values
   *
   * @param input List of arguments
   */
  public P3InputUtils(List<String> input) {
    super(input, EXPECTED_INPUT_SIZE, START_INDEX);
    if (!this.input.contains(TEXT_FILE)) {
      this.expectedNumberOfArgs = this.getExpectedNumberOfArgs() - 2;
      this.startIndex = this.getStartIndex() - 2;
    }
  }

  public boolean prettyPrintAirline(Airline airline) {
    Airline airlineFromTextFile = null;
    if (this.input.contains(TEXT_FILE)) {
      airlineFromTextFile = saveAirlineToFile(airline);
    } else {
      airlineFromTextFile = airline;
    }
    if (airlineFromTextFile != null) {
      String prettyFilePath = getFilePath(PRETTY);
      if (prettyFilePath.equals("-")) {
        if (this.input.contains(PRINT))
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
}