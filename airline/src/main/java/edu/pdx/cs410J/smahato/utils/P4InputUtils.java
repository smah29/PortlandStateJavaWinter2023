package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.smahato.Airline;
import edu.pdx.cs410J.smahato.XmlDumper;
import edu.pdx.cs410J.smahato.XmlParser;
import edu.pdx.cs410J.smahato.constants.Option;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.AIRLINE_NAME_MISMATCH;
import static edu.pdx.cs410J.smahato.constants.Option.*;

/**
 * P4InputUtils extends {@link P3InputUtils} has additional logic of xml file
 */
public class P4InputUtils extends P3InputUtils {
  /**
   * Constant field Expected number of arguments in the input list
   */
  public static final int EXPECTED_INPUT_SIZE = P3InputUtils.EXPECTED_INPUT_SIZE + 2;
  /**
   * Constant field Index of the first argument where airline name starts
   */
  public static final int START_INDEX = P3InputUtils.START_INDEX + 2;

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
    if (doesInputContainsTextFileOption()) {
      throw new IllegalArgumentException(XML_FILE.getOption() + " option is not supported with " + TEXT_FILE.getOption() + " option");
    }
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
    String xmlFilePath = getFilePath(XML_FILE.getOption());
    File file = new File(xmlFilePath);
    if (file.exists()) {
      try {
        Airline airlineFromFile = new XmlParser(new FileReader(file)).parse();
        if (airline.getName().equals(airlineFromFile.getName())) {
          airlineFromFile.addFlight(new ArrayList<>(airline.getFlights()).get(0));
          return dumpAirline(airlineFromFile, file);
        } else {
          System.err.println(AIRLINE_NAME_MISMATCH.replace("text", "xml"));
        }
      } catch (Exception e) {
        System.err.println("Error reading file: " + xmlFilePath + ", " + e.getMessage());
      }
    } else {
      try {
        return dumpAirline(airline, file);
      } catch (Exception e) {
        System.err.println("Error creating file: " + xmlFilePath);
      }
    }

    return null;
  }

  private Airline dumpAirline(Airline airline, File file) throws IOException {
    if (doesInputContainsPrettyOption())
      prettyPrintAirlineInFile(airline);
    new XmlDumper(file).dump(airline);
    return airline;
  }
}
