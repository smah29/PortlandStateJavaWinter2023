package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.smahato.utils.P1InputUtils;
import edu.pdx.cs410J.smahato.utils.P2InputUtils;
import edu.pdx.cs410J.smahato.utils.P3InputUtils;
import edu.pdx.cs410J.smahato.utils.P4InputUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.MISSING_COMMAND_LINE_ARGS;
import static edu.pdx.cs410J.smahato.constants.Option.*;

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
      P4InputUtils p4InputUtils;
      try {
        p4InputUtils = new P4InputUtils(input);
      } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
        return;
      }
      airline = p4InputUtils.getAirline();
      if (airline != null) {
        Airline airlineFromFile = p4InputUtils.saveAirlineToXmlFile(airline);
        if (airlineFromFile != null) {
          if (input.contains(PRINT.getOption())) printAddedFlight(airline);
          if (input.contains(PRETTY.getOption()))
            p4InputUtils.prettyPrintAirlineInConsole(airlineFromFile);
        }
      }
    } else if (input.contains(PRETTY.getOption())) {
      P3InputUtils p3InputUtils = new P3InputUtils(input);
      airline = p3InputUtils.getAirline();
      if (airline != null) {
        Airline airlineFromFile = p3InputUtils.prettyPrintAirlineInFile(airline);
        if (airlineFromFile != null) {
          if (input.contains(PRINT.getOption())) printAddedFlight(airline);
          p3InputUtils.prettyPrintAirlineInConsole(airlineFromFile);
        }
      }
    } else if (input.contains(TEXT_FILE.getOption())) {
      P2InputUtils p2InputUtils = new P2InputUtils(input);
      airline = p2InputUtils.getAirline();
      if (airline != null) {
        Airline airlineFromFile = p2InputUtils.saveAirlineToTextFile(airline);
        if (airlineFromFile != null && input.contains(PRINT.getOption())) printAddedFlight(airline);
      }
    } else {
      P1InputUtils p1InputUtils = new P1InputUtils(input);
      airline = p1InputUtils.getAirline();
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