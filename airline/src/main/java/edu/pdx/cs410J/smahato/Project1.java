package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.smahato.utils.P1InputUtils;
import edu.pdx.cs410J.smahato.utils.P2InputUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.MISSING_COMMAND_LINE_ARGS;
import static edu.pdx.cs410J.smahato.constants.OptionConstants.*;

/**
 * The main class for the CS410J airline Project 2
 */
public class Project1 {
  /**
   * Main method for Project 2,
   * it has the logic to check the command line arguments and print the README or flight or save the airline to a file
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    List<String> input = Arrays.asList(args);
    int inputSize = input.size();
    Airline airline;
    if (inputSize == 0) {
      System.err.print(MISSING_COMMAND_LINE_ARGS);
    } else if (input.contains(README)) {
      printREADME();
    } else if (!input.contains(TEXT_FILE)) {
      P1InputUtils inputUtils = new P1InputUtils(input);
      if (!input.contains(PRINT)) {
        inputUtils.setExpectedNumberOfArgs(inputUtils.getExpectedNumberOfArgs() - 1);
        inputUtils.setStartIndex(inputUtils.getStartIndex() - 1);
      }
      airline = inputUtils.getAirline();
      if (airline != null && input.contains(PRINT)) printAddedFlight(airline);
    } else {
      P2InputUtils filePathInputUtils = new P2InputUtils(input);
      if (!input.contains(PRINT)) {
        filePathInputUtils.setExpectedNumberOfArgs(filePathInputUtils.getExpectedNumberOfArgs() - 1);
        filePathInputUtils.setStartIndex(filePathInputUtils.getStartIndex() - 1);
      }
      airline = filePathInputUtils.getAirline();
      if (airline != null) {
        boolean saved = filePathInputUtils.saveAirlineToFile(airline);
        if (saved && input.contains(PRINT)) printAddedFlight(airline);
      }
    }
  }

  /**
   * Prints the flight that was added to the airline
   *
   * @param airline Airline object
   */
  private static void printAddedFlight(Airline airline) {
    System.out.println(new ArrayList<>(airline.getFlights()).get(0));
  }

  /**
   * Prints the README file
   */
  private static void printREADME() {
    try {
      InputStream readme = Project1.class.getResourceAsStream("README.txt");
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.err.println("Error reading README.txt");
    }
  }
}