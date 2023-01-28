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
   * @return File path
   */
  String getFilePath();

  /**
   * Saves airline to file
   *
   * @param airline Airline object
   * @return true if airline is saved to file, false otherwise
   */
  boolean saveAirlineToFile(Airline airline);
}
