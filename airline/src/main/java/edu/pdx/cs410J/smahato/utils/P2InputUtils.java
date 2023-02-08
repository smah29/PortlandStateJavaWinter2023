package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.smahato.Airline;
import edu.pdx.cs410J.smahato.TextDumper;
import edu.pdx.cs410J.smahato.TextParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.AIRLINE_NAME_MISMATCH;
import static edu.pdx.cs410J.smahato.constants.OptionConstants.TEXT_FILE;

/**
 * P2InputUtils implements {@link FilePathInputUtils} and extends {@link P1InputUtils} is used to get the input from the command line arguments.
 * <p> It has Project 2 specific methods to get airline and flight information from the input list, and save the airline to a file
 */
public class P2InputUtils extends P1InputUtils implements FilePathInputUtils {
  /**
   * Constant field Expected number of arguments in the input list ex 11 in Project 2
   */
  public static final int EXPECTED_INPUT_SIZE = 13;
  /**
   * Constant field Index of the first argument where airline name starts ex 3 in Project 2
   */
  public static final int START_INDEX = 3;

  /**
   * Constructor for P2InputUtils, set the expected number of arguments and start index to Project 2 specific values
   *
   * @param input List of arguments
   */
  public P2InputUtils(List<String> input) {
    this(input, EXPECTED_INPUT_SIZE, START_INDEX);
  }

  public P2InputUtils(List<String> input, int expectedNumberOfArgs, int startIndex) {
    super(input, expectedNumberOfArgs, startIndex);
  }

  /**
   * Gets the index of the file path in the input list
   *
   * @return Index of the file path in the input list
   */
  private int getFilePathIndex(String option) {
    return input.indexOf(option) + 1;
  }

  /**
   * Gets the file path from the input list
   *
   * @return File path from the input list
   */
  @Override
  public String getFilePath(String option) {
    return input.get(getFilePathIndex(option));
  }

  /**
   * Saves the airline to a file
   *
   * @param airline Airline to be saved
   * @return Airline if airline is saved successfully, null otherwise
   */
  @Override
  public Airline saveAirlineToFile(Airline airline) {
    String filePath = getFilePath(TEXT_FILE);
    File file = new File(filePath);
    if (file.exists()) {
      Airline airlineFromFile = null;
      try {
        airlineFromFile = new TextParser(new FileReader(file)).parse();
      } catch (ParserException e) {
        System.err.println("Error parsing file: " + filePath);
      } catch (FileNotFoundException e) {
        System.err.println("Error reading file: " + filePath);
      }
      if (airlineFromFile == null) return null;
      if (airline.getName().equals(airlineFromFile.getName())) {
        airlineFromFile.addFlight(new ArrayList<>(airline.getFlights()).get(0));
        try {
          new TextDumper(new FileWriter(file)).dump(airlineFromFile);
        } catch (IOException e) {
          System.err.println("Error writing file: " + filePath);
        }
        return airlineFromFile;
      } else {
        System.err.println(AIRLINE_NAME_MISMATCH);
      }
    } else {
      try {
        new TextDumper(new FileWriter(file)).dump(airline);
        return airline;
      } catch (IOException e) {
        System.err.println("Error creating file: " + filePath);
      }
    }
    return null;
  }

}
