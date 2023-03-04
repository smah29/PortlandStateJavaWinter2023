package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.smahato.exception.AirportCodeException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.pdx.cs410J.smahato.Messages.MISSING_SRC_DEST;
import static edu.pdx.cs410J.smahato.constants.AirlineParams.*;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.FLIGHT_NUMBER_MUST_BE_AN_INTEGER;

public class AirlineServlet extends HttpServlet {
  private final Map<String, Airline> airlineNameMap = new HashMap<>();

  /**
   * Handles an HTTP POST request from a client. Adds a flight to the airline.
   *
   * @param request  HttpServletRequest
   * @param response HttpServletResponse
   * @throws IOException if there is an error writing to the HTTP response
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/plain");
    String airlineName = getParameter(AIRLINE_NAME.getParameterName(), request);
    if (airlineName == null) {
      missingRequiredParameter(response, AIRLINE_NAME.getParameterName());
      return;
    }
    String flightNumber = getParameter(FLIGHT_NUMBER.getParameterName(), request);
    if (flightNumber == null) {
      missingRequiredParameter(response, FLIGHT_NUMBER.getParameterName());
      return;
    }
    String source = getParameter(SOURCE.getParameterName(), request);
    if (source == null) {
      missingRequiredParameter(response, SOURCE.getParameterName());
      return;
    }
    String departure = getParameter(DEPARTURE.getParameterName(), request);
    if (departure == null) {
      missingRequiredParameter(response, DEPARTURE.getParameterName());
      return;
    }
    String destination = getParameter(DESTINATION.getParameterName(), request);
    if (destination == null) {
      missingRequiredParameter(response, DESTINATION.getParameterName());
      return;
    }
    String arrival = getParameter(ARRIVAL.getParameterName(), request);
    if (arrival == null) {
      missingRequiredParameter(response, ARRIVAL.getParameterName());
      return;
    }
    try {
      Airline airline = airlineNameMap.get(airlineName.toLowerCase());
      if (airline == null)
        airline = new Airline(airlineName);
      airline.addFlight(new Flight(flightNumber, source, destination, departure, arrival));
      airlineNameMap.put(airlineName.toLowerCase(), airline);
      response.setStatus(HttpServletResponse.SC_OK);
    } catch (NumberFormatException e) {
      String message = flightNumber + " - " + FLIGHT_NUMBER_MUST_BE_AN_INTEGER;
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
    } catch (DateTimeException | AirportCodeException | NullPointerException e) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }
  }

  /**
   * Handles an HTTP GET request from a client. Returns information about the flights in the airline.
   *
   * @param request  HttpServletRequest
   * @param response HttpServletResponse
   * @throws IOException if there is an error writing to the HTTP response
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/xml");
    String airlineName = getParameter(AIRLINE_NAME.getParameterName(), request);
    if (airlineName == null) {
      missingRequiredParameter(response, AIRLINE_NAME.getParameterName());
      return;
    }
    Airline airline = airlineNameMap.get(airlineName.toLowerCase());
    if (airline == null) {
      String message = Messages.airlineDoesNotExist(airlineName);
      response.sendError(HttpServletResponse.SC_NOT_FOUND, message);
    } else {
      String source = getParameter(SOURCE.getParameterName(), request);
      String destination = getParameter(DESTINATION.getParameterName(), request);
      if (source != null && destination != null) {
        List<Flight> flights = airline.getFlights(source, destination);
        if (flights.isEmpty())
          response.sendError(HttpServletResponse.SC_NOT_FOUND, Messages.flightDoesNotExist(source, destination, airlineName));
        else {
          airline = new Airline(airlineName);
          airline.getFlights().addAll(flights);
          new XmlDumper(response.getWriter()).dump(airline);
          response.setStatus(HttpServletResponse.SC_OK);
        }
      } else if (source == null && destination == null) {
        new XmlDumper(response.getWriter()).dump(airline);
        response.setStatus(HttpServletResponse.SC_OK);
      } else {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, MISSING_SRC_DEST);
      }
    }
  }

  /**
   * Returns the value of the HTTP request parameter with the given name.
   *
   * @return <code>null</code> if the value of the parameter is
   * <code>null</code> or is the empty string
   */
  private String getParameter(String name, HttpServletRequest request) {
    String value = request.getParameter(name);
    if (value == null || "".equals(value)) {
      return null;
    } else {
      return value;
    }
  }


  /**
   * Writes an error message about a missing parameter to the HTTP response.
   * <p>
   * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
   */
  private void missingRequiredParameter(HttpServletResponse response, String parameterName) throws IOException {
    String message = Messages.missingRequiredParameter(parameterName);
    response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
  }

}
