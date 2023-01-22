package edu.pdx.cs410J.smahato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static edu.pdx.cs410J.smahato.ErrorMessages.README;
import static edu.pdx.cs410J.smahato.ErrorMessages.EXTRA_COMMAND_LINE_ARGS;
import static edu.pdx.cs410J.smahato.ErrorMessages.MISSING_COMMAND_LINE_ARGS;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

  public static void main(String[] args) {
    int length = args.length;
    if (length == 0) {
      System.err.print(MISSING_COMMAND_LINE_ARGS);
      return;
    }
    boolean found = Stream.of(args).anyMatch(s -> s.equals("-README"));
    if (found) {
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
    if (length > 9) {
      System.err.print(EXTRA_COMMAND_LINE_ARGS);
      return;
    }
    //If the -print option is not present, then the program should print nothing to System.out.
    boolean print = Stream.of(args).anyMatch(s -> s.equals("-print"));
    if (!print) {
      return;
    }
    if (length != 9) {
      System.err.print(MISSING_COMMAND_LINE_ARGS);
      return;
    }
    try {
      // args[0] is -print expected
      Airline airline = new Airline(args[1]);
      Flight flight = new Flight(Integer.parseInt(args[2]), args[3], args[6], args[4] + " " + args[5], args[7] + " " + args[8]);
      airline.addFlight(flight);
      System.out.println(new ArrayList<>(airline.getFlights()).get(0).toString());
    } catch (NumberFormatException e) {
      System.err.println("Flight number must be an integer!" + README);
    } catch (DateTimeException | AirportCodeException | NullPointerException e) {
      System.err.println(e.getMessage() + README);
    }
  }
}