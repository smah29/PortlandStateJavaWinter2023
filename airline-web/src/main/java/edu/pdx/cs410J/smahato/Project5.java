package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.smahato.utils.SaveFlightUtils;
import edu.pdx.cs410J.smahato.utils.SearchFlightsUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.MISSING_COMMAND_LINE_ARGS;
import static edu.pdx.cs410J.smahato.constants.Option.*;

/**
 * The main class that parses the command line and communicates with the
 * Airline server using REST.
 */
public class Project5 {

  /**
   * Main method that parses the command line and communicates with the
   * Airline server using REST.
   *
   * @param args The command line arguments
   */
  public static void main(String... args) {
    List<String> input = Arrays.asList(args);
    if (input.size() == 0) {
      System.err.println(MISSING_COMMAND_LINE_ARGS);
      printFile("usage.txt");
    } else if (input.contains(README.getOption())) {
      printFile("README.txt");
    } else {
      try {
        if (input.contains(SEARCH.getOption())) {
          new SearchFlightsUtils(input).searchFlights();
        } else {
          new SaveFlightUtils(input).saveFlight();
        }
      } catch (Exception e) {
        System.err.println("Error : " + e.getMessage());
      }
    }
  }

  private static void printFile(String fileName) {
    try {
      InputStream resource = Project5.class.getResourceAsStream(fileName);
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