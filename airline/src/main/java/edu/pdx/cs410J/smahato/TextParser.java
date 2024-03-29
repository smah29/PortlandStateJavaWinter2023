package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.Reader;

import static edu.pdx.cs410J.smahato.constants.FileConstants.TEXT_FILE_SEPARATOR;

/**
 * <code>TextParser</code> class for Project 2 tries to read <code>Airline</code> from file
 */
public class TextParser implements AirlineParser<Airline> {
  private final Reader reader;

  /**
   * Creates a new <code>TextParser</code> object.
   *
   * @param reader The reader from which the airline will be read
   */
  public TextParser(Reader reader) {
    this.reader = reader;
  }

  /**
   * Parses the contents of the file and returns an <code>Airline</code> along with its <code>Flight</code>.
   *
   * @return An <code>Airline</code> that is described by the contents of the file.
   * @throws ParserException If the file cannot be parsed.
   */
  @Override
  public Airline parse() throws ParserException {
    try (
        BufferedReader br = new BufferedReader(this.reader)
    ) {

      String airlineName = br.readLine();

      if (airlineName == null) {
        throw new ParserException("Missing airline name");
      }
      Airline airline = new Airline(airlineName);
      String flightDetails;
      while ((flightDetails = br.readLine()) != null) {
        String[] array = flightDetails.split(TEXT_FILE_SEPARATOR);
        Flight flight = new Flight(array[0], array[1], array[2], array[3], array[4]);
        airline.addFlight(flight);
      }
      return airline;

    } catch (Exception e) {
      throw new ParserException("While parsing airline text", e);
    }
  }
}
