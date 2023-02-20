package edu.pdx.cs410J.smahato.utils;

import edu.pdx.cs410J.smahato.Airline;

/**
 * Interface FilePathInputUtils extends {@link InputUtils} contains methods related to file path
 * and saves airline to file
 * <p>
 * This interface is implemented by {@link P2InputUtils}
 */
public interface FilePathInputUtils extends InputUtils {
  /**
   * Returns the file path from the input list
   *
   * @param option options allowed by airline application
   * @return File path after the option
   */
  String getFilePath(String option);

  /**
   * Saves airline to file
   *
   * @param airline Airline object
   * @return Airline if airline is saved to file, null otherwise
   */
  Airline saveAirlineToTextFile(Airline airline);
}
