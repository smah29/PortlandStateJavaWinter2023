package edu.pdx.cs410J.smahato;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.smahato.constants.AirlineParams;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Map;

import static edu.pdx.cs410J.web.HttpRequestHelper.Response;
import static edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

/**
 * A helper class for accessing the rest client.  this class gets and posts to a URL.
 */
public class AirlineRestClient {
  private static final String WEB_APP = "airline";
  private static final String SERVLET = "flights";

  private final HttpRequestHelper http;


  /**
   * Creates a client to the airline REST service running on the given host and port
   *
   * @param hostName The name of the host
   * @param port     The port
   */
  public AirlineRestClient(String hostName, int port) {
    this(new HttpRequestHelper(String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET)));
  }

  /**
   * Creates a client to the airline REST service running on the given host and port
   *
   * @param http
   */
  @VisibleForTesting
  AirlineRestClient(HttpRequestHelper http) {
    this.http = http;
  }

  private void throwExceptionIfNotOkayHttpStatus(Response response) {
    int code = response.getHttpStatusCode();
    if (code != HTTP_OK) {
      String message = response.getContent();
      throw new RestException(code, message);
    }
  }

  /**
   * Searches for flights
   *
   * @param airlineName Name of the airline
   * @param source      Source of the flight
   * @param destination Destination of the flight
   * @throws IOException     if there is an error communicating with the server
   * @throws ParserException if there is an error parsing the response from the server
   */
  public void searchFlights(String airlineName, String source, String destination) throws IOException, ParserException {
    Response response = http.get(Map.of(
        AirlineParams.AIRLINE_NAME.getParameterName(), airlineName,
        AirlineParams.SOURCE.getParameterName(), source,
        AirlineParams.DESTINATION.getParameterName(), destination));
    String content = response.getContent();
    int code = response.getHttpStatusCode();
    if (code != HTTP_OK) {
      if (code == SC_NOT_FOUND)
        System.err.println(content);
      else
        throw new RestException(code, content);
    } else {
      Airline airline = new XmlParser(new StringReader(content)).parse();
      new AirlinePrettyPrinter(new PrintWriter(System.out)).dump(airline);
    }
  }

  /**
   * Saves a flight
   *
   * @param airlineName     Name of the airline
   * @param flightNumber    Flight number
   * @param source          Source of the flight
   * @param destination     Destination of the flight
   * @param departureString Departure time
   * @param arrivalString   Arrival time
   * @throws IOException if there is an error communicating with the server
   */
  public void saveFlight(String airlineName, String flightNumber, String source, String destination, String departureString, String arrivalString) throws IOException {
    Response response = http.post(Map.of(
        AirlineParams.AIRLINE_NAME.getParameterName(), airlineName,
        AirlineParams.FLIGHT_NUMBER.getParameterName(), flightNumber,
        AirlineParams.SOURCE.getParameterName(), source,
        AirlineParams.DESTINATION.getParameterName(), destination,
        AirlineParams.DEPARTURE.getParameterName(), departureString,
        AirlineParams.ARRIVAL.getParameterName(), arrivalString));
    throwExceptionIfNotOkayHttpStatus(response);
  }
}
