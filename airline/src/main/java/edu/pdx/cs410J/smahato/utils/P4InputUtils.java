package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.smahato.Airline;
import edu.pdx.cs410J.smahato.XmlDumper;
import edu.pdx.cs410J.smahato.XmlParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.AIRLINE_NAME_MISMATCH;
import static edu.pdx.cs410J.smahato.constants.Option.PRETTY;
import static edu.pdx.cs410J.smahato.constants.Option.XML_FILE;

/**
 * P4InputUtils extends {@link P3InputUtils} has additional logic of xml file
 */
public class P4InputUtils extends P3InputUtils {
  /**
   * Constant field Expected number of arguments in the input list ex 15 in Project 4
   */
  public static final int EXPECTED_INPUT_SIZE = 15;
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

  /**
   * @return true if input contains pretty option, false otherwise
   */
  public boolean doesInputContainsPrettyOption() {
    return doesInputContainsOption(this.input, PRETTY.getOption());
  }

  /**
   * Saves the airline to XML file
   *
   * @param airline Airline to be saved
   * @return Airline if airline is saved successfully, null otherwise
   */
  public Airline saveAirlineToXmlFile(Airline airline) {
    boolean inputContainsPrettyOption = doesInputContainsPrettyOption();
    if (!inputContainsPrettyOption || prettyPrintAirlineInFile(airline) != null) {
      String xmlFilePath = getFilePath(XML_FILE.getOption());
      File file = new File(xmlFilePath);
      if (file.exists()) {
        try {
          Airline airlineFromFile = new XmlParser(new FileReader(file)).parse();
          if (airline.getName().equals(airlineFromFile.getName())) {
            airlineFromFile.addFlight(new ArrayList<>(airline.getFlights()).get(0));
            new XmlDumper(file).dump(airlineFromFile);
            return airlineFromFile;
          } else {
            System.err.println(AIRLINE_NAME_MISMATCH.replace("text", "xml"));
          }
        } catch (Exception e) {
          System.err.println("Error reading file: " + xmlFilePath + ", " + e.getMessage());
        }
      } else {
        try {
          new XmlDumper(file).dump(airline);
          return airline;
        } catch (Exception e) {
          System.err.println("Error creating file: " + xmlFilePath + ", " + e.getMessage());
        }
      }
    }
    return null;
  }
}
