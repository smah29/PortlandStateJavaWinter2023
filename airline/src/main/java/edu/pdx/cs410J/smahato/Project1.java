package edu.pdx.cs410J.smahato;

import com.google.common.annotations.VisibleForTesting;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

  @VisibleForTesting
  static boolean isValidDateAndTime(String dateAndTime) {
    return true;
  }

  public static void main(String[] args) {
    if (args == null || args.length != 8) {
      System.err.println("Missing command line arguments!\nTo run this program, please provide the following in order: airline name, flight number, source, departure date and time(mm/dd/yyyy hh:mm), destination, arrival date and time(mm/dd/yyyy hh:mm)");
      return;
    }
    for (String arg : args) {
      System.out.println(arg);
    }
  }
}