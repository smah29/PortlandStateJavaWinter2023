package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.MISSING_COMMAND_LINE_ARGS;

/**
 * This class is used to convert a text file to an XML file
 */
public class Converter {
  /**
   * Main method to convert a text file to an XML file
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println(MISSING_COMMAND_LINE_ARGS);
      Project4.printFile("converterUsage.txt");
    } else {
      String textFileName = args[0];
      String xmlFileName = args[1];
      File textFile = new File(textFileName);
      Airline airline;
      try {
        airline = new TextParser(new FileReader(textFile)).parse();
      } catch (FileNotFoundException e) {
        System.err.println(textFileName + " file does not exist!");
        return;
      } catch (ParserException e) {
        System.err.println("Error while parsing the file : " + textFileName);
        return;
      }
      File xmlFile = new File(xmlFileName);
      try {
        new XmlDumper(xmlFile).dump(airline);
        System.out.println("Successfully created file: " + xmlFileName);
      } catch (IOException e) {
        System.err.println("Error creating file: " + xmlFileName);
      }
    }
  }
}
