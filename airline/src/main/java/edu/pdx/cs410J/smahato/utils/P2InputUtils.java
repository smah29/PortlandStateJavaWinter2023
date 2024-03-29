package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.smahato.Airline;
import edu.pdx.cs410J.smahato.TextDumper;
import edu.pdx.cs410J.smahato.TextParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.AIRLINE_NAME_MISMATCH;
import static edu.pdx.cs410J.smahato.constants.Option.TEXT_FILE;

/**
 * P2InputUtils implements {@link FilePathInputUtils} and extends {@link P1InputUtils} is used to get the input from the command line arguments.
 * <p> It has Project 2 specific methods to get airline and flight information from the input list, and save the airline to a file
 */
public class P2InputUtils extends P1InputUtils implements FilePathInputUtils {
  /**
   * Constant field Expected number of arguments in the input list
   */
  public static final int EXPECTED_INPUT_SIZE = P1InputUtils.EXPECTED_INPUT_SIZE + 2;
  /**
   * Constant field Index of the first argument where airline name starts ex 3 in Project 2
   */
  public static final int START_INDEX = P1InputUtils.START_INDEX + 2;

  /**
   * Constructor for P2InputUtils, set the expected number of arguments and start index to Project 2 specific values
   *
   * @param input List of arguments
   */
  public P2InputUtils(List<String> input) {
    this(input, EXPECTED_INPUT_SIZE, START_INDEX);
  }

  /**
   * Constructor for P2InputUtils used by {@link P3InputUtils}
   *
   * @param input                List of arguments
   * @param expectedNumberOfArgs Expected number of arguments according to Project ex 11 in Project 2
   * @param startIndex           Index of the first argument where airline name starts ex 3 in Project 2
   */
  public P2InputUtils(List<String> input, int expectedNumberOfArgs, int startIndex) {
    super(input, expectedNumberOfArgs, startIndex);
  }

  /**
   * Gets the index of the file path in the input list
   *
   * @param option options allowed by airline application
   * @return Index of the file path in the input list
   */
  private int getFilePathIndex(String option) {
    return input.indexOf(option) + 1;
  }

  /**
   * Gets the file path from the input list which is the input value just after the option
   *
   * @param option options allowed by airline application
   * @return File path from the input list
   */
  @Override
  public String getFilePath(String option) {
    return input.get(getFilePathIndex(option));
  }

  /**
   * Saves the airline to a text file
   *
   * @param airline Airline to be saved
   * @return Airline if airline is saved successfully, null otherwise
   */
  @Override
  public Airline saveAirlineToTextFile(Airline airline) {
    String filePath = getFilePath(TEXT_FILE.getOption());
    File file = new File(filePath);
    if (file.exists()) {
      try {
        Airline airlineFromFile = new TextParser(new FileReader(file)).parse();
        if (airline.getName().equals(airlineFromFile.getName())) {
          airlineFromFile.addFlight(new ArrayList<>(airline.getFlights()).get(0));
          return dumpAirline(airlineFromFile, file);
        } else {
          System.err.println(AIRLINE_NAME_MISMATCH);
        }
      } catch (IOException | ParserException e) {
        System.err.println("Error reading file: " + filePath);
      }
    } else {
      try {
        return dumpAirline(airline, file);
      } catch (IOException e) {
        System.err.println("Error creating file: " + filePath);
      }
    }
    return null;
  }

  private static Airline dumpAirline(Airline airline, File file) throws IOException {
    new TextDumper(new FileWriter(file)).dump(airline);
    return airline;
  }

}
