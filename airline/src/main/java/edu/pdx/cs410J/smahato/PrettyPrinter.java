package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AirlineDumper;
import edu.pdx.cs410J.AirportNames;

import java.io.PrintWriter;
import java.io.Writer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <code>TextDumper</code> class for Project3 pretty prints airline object in file.
 */
public class PrettyPrinter implements AirlineDumper<Airline> {
  private static final String PRETTY_PRINT_FORMAT = "%1$-2s|%2$-10s|%3$-42s|%4$-42s|%5$-20s|%6$-20s|%7$-6s\n";
  private final Writer writer;

  /**
   * Creates a new <code>PrettyPrinter</code> object
   *
   * @param writer
   */
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
      pw.println();
      pw.println("Flights for airline " + airline.getName());
      pw.println();
      String[] array = {"#", "Flight", "Source", "Destination", "Departure", "Arrival", "Duration(minutes)"};
      pw.println(String.format(PRETTY_PRINT_FORMAT, (Object[]) array));
      AtomicInteger count = new AtomicInteger();

      new ArrayList<>(airline.getFlights()).stream().sorted().forEach(flight -> {

        long durationInMinutes = Duration.between(flight.getDeparture().toInstant(),
            flight.getArrival().toInstant()).toMinutes();

        String[] flightDetails = {String.valueOf(count.incrementAndGet()),
            String.valueOf(flight.getNumber()), AirportNames.getName(flight.getSource()),
            AirportNames.getName(flight.getDestination()), flight.getDepartureString(),
            flight.getArrivalString(), String.valueOf(durationInMinutes)};

        pw.println(String.format(PRETTY_PRINT_FORMAT, (Object[]) flightDetails));
      });
    }
  }
}
