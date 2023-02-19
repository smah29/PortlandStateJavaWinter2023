package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.smahato.utils.P1InputUtils;
import edu.pdx.cs410J.smahato.utils.P2InputUtils;
import edu.pdx.cs410J.smahato.utils.P3InputUtils;
import edu.pdx.cs410J.smahato.utils.P4InputUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.MISSING_COMMAND_LINE_ARGS;
import static edu.pdx.cs410J.smahato.constants.Options.*;

/**
 * The main class for the CS410J airline Project 2
 */
public class Project4 {
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
      System.err.println(MISSING_COMMAND_LINE_ARGS);
      printFile("usage.txt");
    } else if (input.contains(README.getOption())) {
      printFile("README.txt");
    } else if (input.contains(XML_FILE.getOption())) {
      P4InputUtils p4InputUtils = new P4InputUtils(input);
      airline = p4InputUtils.getAirline();
      if (airline != null) {
        boolean noConsoleOutputOrError = p4InputUtils.prettyPrintAirline(airline);
        if (noConsoleOutputOrError && input.contains(PRINT.getOption())) printAddedFlight(airline);
      }
    } else if (input.contains(PRETTY.getOption())) {
      P3InputUtils p3InputUtils = new P3InputUtils(input);
      airline = p3InputUtils.getAirline();
      if (airline != null) {
        boolean noConsoleOutputOrError = p3InputUtils.prettyPrintAirline(airline);
        if (noConsoleOutputOrError && input.contains(PRINT.getOption())) printAddedFlight(airline);
      }
    } else if (input.contains(TEXT_FILE.getOption())) {
      P2InputUtils filePathInputUtils = new P2InputUtils(input);
      airline = filePathInputUtils.getAirline();
      if (airline != null) {
        Airline airlineFromFile = filePathInputUtils.saveAirlineToFile(airline);
        if (airlineFromFile != null && input.contains(PRINT.getOption())) printAddedFlight(airline);
      }
    } else {
      P1InputUtils inputUtils = new P1InputUtils(input);
      airline = inputUtils.getAirline();
      if (airline != null && input.contains(PRINT.getOption())) printAddedFlight(airline);
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
   * Prints the file
   *
   * @param fileName Name of the file
   */
  private static void printFile(String fileName) {
    try {
      InputStream resource = Project4.class.getResourceAsStream(fileName);
      BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.err.println("Error reading " + fileName);
    }
  }
}