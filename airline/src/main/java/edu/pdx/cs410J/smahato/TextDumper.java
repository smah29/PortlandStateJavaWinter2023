package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AirlineDumper;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

import static edu.pdx.cs410J.smahato.constants.DateFormatConstants.TWELVE_HOUR_TIME_FORMAT;
import static edu.pdx.cs410J.smahato.constants.FileConstants.TEXT_FILE_SEPARATOR;

/**
 * <code>TextDumper</code> class for Project 2 dumps airline object in file.
 */
public class TextDumper implements AirlineDumper<Airline> {
  private final Writer writer;

  /**
   * Creates a new <code>TextDumper</code> object.
   *
   * @param writer The writer to which the airline will be written
   */
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
        String destinationString = TWELVE_HOUR_TIME_FORMAT.format(flight.getDeparture()).toLowerCase();
        String arrivalString = TWELVE_HOUR_TIME_FORMAT.format(flight.getArrival()).toLowerCase();
        String[] array = {String.valueOf(flight.getNumber()), flight.getSource(), flight.getDestination(), destinationString, arrivalString};
        pw.println(String.join(TEXT_FILE_SEPARATOR, array));
      });
      pw.flush();
    }
  }

}
