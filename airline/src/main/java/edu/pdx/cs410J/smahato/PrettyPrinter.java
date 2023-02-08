package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AirlineDumper;
import edu.pdx.cs410J.AirportNames;

import java.io.PrintWriter;
import java.io.Writer;
import java.time.Duration;
import java.util.ArrayList;

import static edu.pdx.cs410J.smahato.constants.FileConstants.PRETTY_FILE_SEPARATOR;

/**
 * <code>TextDumper</code> class for Project3 pretty prints airline object in file.
 */
public class PrettyPrinter implements AirlineDumper<Airline> {
  private final Writer writer;

  public PrettyPrinter(Writer writer) {
    this.writer = writer;
  }

  /**
   * Dumps the contents of the airline object in file or std output.
   * Flight details are dumped in the following format: <code>flightNumber,source,destination,departureTime,arrivalTime,duration</code>
   *
   * @param airline Airline object to be dumped
   */
  @Override
  public void dump(Airline airline) {
    try (
        PrintWriter pw = new PrintWriter(this.writer)
    ) {
      pw.println(airline.getName());
      String[] array = {"Flight", "Source", "Destination", "Departure", "Arrival", "Duration(minutes)"};
      pw.println(String.join(PRETTY_FILE_SEPARATOR, array));
      new ArrayList<>(airline.getFlights()).stream().sorted().forEach(flight -> {
        long durationInMinutes = Duration.between(flight.getDeparture().toInstant(), flight.getArrival().toInstant()).toMinutes();
        String[] flightDetails = {String.valueOf(flight.getNumber()), AirportNames.getName(flight.getSource()), AirportNames.getName(flight.getDestination()),
            flight.getDepartureString(), flight.getArrivalString(), String.valueOf(durationInMinutes)};
        pw.println(String.join(PRETTY_FILE_SEPARATOR, flightDetails));
      });
    }
  }
}
