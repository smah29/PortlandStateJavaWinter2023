package edu.pdx.cs410J.smahato;

import com.google.common.annotations.VisibleForTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static edu.pdx.cs410J.smahato.ErrorMessages.MISSING_COMMAND_LINE_ARGS;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

  @VisibleForTesting
  static boolean isValidDateAndTime(String dateAndTime) {
    return true;
  }

  public static void main(String[] args) {
    if (args == null || args.length == 0) {
      System.err.print(MISSING_COMMAND_LINE_ARGS);
      return;
    }
    for (String arg : args) {
      if (arg.equals("-README")) {
        try {
          InputStream readme = Project1.class.getResourceAsStream("README.txt");
          BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
          String line;
          while ((line = reader.readLine()) != null) {
            System.out.println(line);
          }
        } catch (IOException e) {
          System.err.println("Error reading README.txt");
        }
        return;
      }
    }
  }
}