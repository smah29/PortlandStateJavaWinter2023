package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AirlineDumper;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * <code>TextDumper</code> class for Project 2 dumps airline object in file.
 */
public class TextDumper implements AirlineDumper<Airline> {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  /**
   * Dumps the contents of the airline object in file.
   * Flight details are dumped in the following format: <code>flightNumber,source,destination,departureTime,arrivalTime</code>
   *
   * @param airline Airline object to be dumped
   */
  @Override
  public void dump(Airline airline) {
    try (
        PrintWriter pw = new PrintWriter(this.writer)
    ) {
      pw.println(airline.getName());
      new ArrayList<>(airline.getFlights()).forEach(flight -> {
        String[] array = {String.valueOf(flight.getNumber()), flight.getSource(), flight.getDestination(), flight.getDepartureString(), flight.getArrivalString()};
        pw.println(String.join(",", array));
      });
      pw.flush();
    }
  }
}
