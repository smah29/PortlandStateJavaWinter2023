package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.smahato.constants.AirlineConstants;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static edu.pdx.cs410J.smahato.constant.AirlineTestValues.*;
import static edu.pdx.cs410J.smahato.constants.AirlineParams.*;
import static edu.pdx.cs410J.smahato.constants.DateFormatConstants.MM_DD_YYYY_hh_MM_a;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.*;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link AirlineServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class AirlineServletTest {

  @Test
  void initiallyServletContainsNoAirline() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    servlet.doGet(request, response);
    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter(AIRLINE_NAME.getParameterName()));
  }

  @Test
  void airlineNotPresentInMap() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    String airlineName = "TestAirline";
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(airlineName);
    servlet.doGet(request, response);
    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_NOT_FOUND, Messages.airlineDoesNotExist(airlineName));
  }

  @Test
  void addInvalidAirlineToMap() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter(AIRLINE_NAME.getParameterName()));
  }

  @Test
  void addInvalidAirlineToMapOnlyAirlineName() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter(FLIGHT_NUMBER.getParameterName()));
  }

  @Test
  void addInvalidAirlineToMapFlightNumber() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    when(request.getParameter(FLIGHT_NUMBER.getParameterName())).thenReturn(FLIGHT_NUMBER_VALUE);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter(SOURCE.getParameterName()));
  }

  @Test
  void addInvalidAirlineToMapSource() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    when(request.getParameter(FLIGHT_NUMBER.getParameterName())).thenReturn(FLIGHT_NUMBER_VALUE);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn(SOURCE_VALUE);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_PRECONDITION_FAILED
            , Messages.missingRequiredParameter(DEPARTURE.getParameterName()));
  }

  @Test
  void addInvalidAirlineToMapDeparture() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    when(request.getParameter(FLIGHT_NUMBER.getParameterName())).thenReturn(FLIGHT_NUMBER_VALUE);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn(SOURCE_VALUE);
    when(request.getParameter(DEPARTURE.getParameterName())).thenReturn(DEPARTURE_VALUE);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_PRECONDITION_FAILED
            , Messages.missingRequiredParameter(DESTINATION.getParameterName()));
  }

  @Test
  void addInvalidAirlineToMapDestination() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    when(request.getParameter(FLIGHT_NUMBER.getParameterName())).thenReturn(FLIGHT_NUMBER_VALUE);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn(SOURCE_VALUE);
    when(request.getParameter(DEPARTURE.getParameterName())).thenReturn(DEPARTURE_VALUE);
    when(request.getParameter(DESTINATION.getParameterName())).thenReturn(DESTINATION_VALUE);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_PRECONDITION_FAILED
            , Messages.missingRequiredParameter(ARRIVAL.getParameterName()));
  }

  @Test
  void addInvalidAirlineToMapArrival() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    when(request.getParameter(FLIGHT_NUMBER.getParameterName())).thenReturn(FLIGHT_NUMBER_VALUE);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn(SOURCE_VALUE);
    when(request.getParameter(DEPARTURE.getParameterName())).thenReturn(DEPARTURE_VALUE);
    when(request.getParameter(DESTINATION.getParameterName())).thenReturn(DESTINATION_VALUE);
    when(request.getParameter(ARRIVAL.getParameterName())).thenReturn(ARRIVAL_VALUE);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  void addInvalidAirlineToMapWrongFlightNumber() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    when(request.getParameter(FLIGHT_NUMBER.getParameterName())).thenReturn("stringFlightNumber");
    when(request.getParameter(SOURCE.getParameterName())).thenReturn(SOURCE_VALUE);
    when(request.getParameter(DEPARTURE.getParameterName())).thenReturn(DEPARTURE_VALUE);
    when(request.getParameter(DESTINATION.getParameterName())).thenReturn(DESTINATION_VALUE);
    when(request.getParameter(ARRIVAL.getParameterName())).thenReturn(ARRIVAL_VALUE);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_BAD_REQUEST, "stringFlightNumber - " + FLIGHT_NUMBER_MUST_BE_AN_INTEGER);
  }

  @Test
  void addInvalidAirlineToMapArrivalTime() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    when(request.getParameter(FLIGHT_NUMBER.getParameterName())).thenReturn(FLIGHT_NUMBER_VALUE);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn(SOURCE_VALUE);
    when(request.getParameter(DEPARTURE.getParameterName())).thenReturn(DEPARTURE_VALUE);
    when(request.getParameter(DESTINATION.getParameterName())).thenReturn(DESTINATION_VALUE);
    when(request.getParameter(ARRIVAL.getParameterName())).thenReturn("stringDate");

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid " + AirlineConstants.ARRIVAL + " date format - stringDate! Please follow the format: " + MM_DD_YYYY_hh_MM_a);
  }

  @Test
  void addInvalidAirlineToMapDepartureTime() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    when(request.getParameter(FLIGHT_NUMBER.getParameterName())).thenReturn(FLIGHT_NUMBER_VALUE);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn(SOURCE_VALUE);
    when(request.getParameter(DEPARTURE.getParameterName())).thenReturn("22/12/2012 12:12 pm");
    when(request.getParameter(DESTINATION.getParameterName())).thenReturn(DESTINATION_VALUE);
    when(request.getParameter(ARRIVAL.getParameterName())).thenReturn(ARRIVAL_VALUE);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_BAD_REQUEST, AirlineConstants.DEPARTURE + " month should be between 1 and 12");
  }

  @Test
  void addInvalidAirlineToMapSourceInvalid() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    when(request.getParameter(FLIGHT_NUMBER.getParameterName())).thenReturn(FLIGHT_NUMBER_VALUE);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn("WWW");
    when(request.getParameter(DEPARTURE.getParameterName())).thenReturn(DEPARTURE_VALUE);
    when(request.getParameter(DESTINATION.getParameterName())).thenReturn(DESTINATION_VALUE);
    when(request.getParameter(ARRIVAL.getParameterName())).thenReturn(ARRIVAL_VALUE);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_BAD_REQUEST, AirlineConstants.SOURCE + "(" + "WWW" + ")" + MUST_BE_A_VALID_AIRPORT_CODE);
  }

  @Test
  void addInvalidAirlineToMapDestinationInvalid() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    when(request.getParameter(FLIGHT_NUMBER.getParameterName())).thenReturn(FLIGHT_NUMBER_VALUE);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn("ABE");
    when(request.getParameter(DEPARTURE.getParameterName())).thenReturn(DEPARTURE_VALUE);
    when(request.getParameter(DESTINATION.getParameterName())).thenReturn("ABE");
    when(request.getParameter(ARRIVAL.getParameterName())).thenReturn(ARRIVAL_VALUE);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_BAD_REQUEST, SOURCE_AND_DESTINATION_CANNOT_BE_SAME);
  }

}
