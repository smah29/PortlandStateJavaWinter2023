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

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.AIRLINE_NAME_MISMATCH;
import static edu.pdx.cs410J.smahato.constants.OptionConstants.TEXT_FILE;

/**
 * P2InputUtils implements {@link FilePathInputUtils} and extends {@link P1InputUtils} is used to get the input from the command line arguments.
 * <p> It has Project 2 specific methods to get airline and flight information from the input list, and save the airline to a file
 */
public class P2InputUtils extends P1InputUtils implements FilePathInputUtils {
  /**
   * Constant field Expected number of arguments in the input list ex 11 in Project 2
   */
  public static final int EXPECTED_INPUT_SIZE = 11;
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
    super(input, EXPECTED_INPUT_SIZE, START_INDEX);
  }

  /**
   * Gets the index of the file path in the input list
   *
   * @return Index of the file path in the input list
   */
  private int getFilePathIndex() {
    return input.indexOf(TEXT_FILE) + 1;
  }

  /**
   * Gets the file path from the input list
   *
   * @return File path from the input list
   */
  @Override
  public String getFilePath() {
    return input.get(getFilePathIndex());
  }

  /**
   * Saves the airline to a file
   *
   * @param airline Airline to be saved
   * @return True if airline is saved successfully, false otherwise
   */
  @Override
  public boolean saveAirlineToFile(Airline airline) {
    String filePath = getFilePath();
    File file = new File(filePath);
    if (file.exists()) {
      try {
        Airline airlineFromFile = new TextParser(new FileReader(file)).parse();
        if (airline.getName().equals(airlineFromFile.getName())) {
          airlineFromFile.addFlight(new ArrayList<>(airline.getFlights()).get(0));
          new TextDumper(new FileWriter(file)).dump(airlineFromFile);
          return true;
        } else {
          System.err.println(AIRLINE_NAME_MISMATCH);
        }
      } catch (IOException | ParserException e) {
        System.err.println("Error reading file: " + filePath);
      }
    } else {
      try {
        new TextDumper(new FileWriter(file)).dump(airline);
        return true;
      } catch (IOException e) {
        System.err.println("Error creating file: " + filePath);
      }
    }
    return false;
  }

}
