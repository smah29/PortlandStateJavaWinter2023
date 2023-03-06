package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.smahato.constants.AirlineConstants;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static edu.pdx.cs410J.smahato.Messages.MISSING_SRC_DEST;
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
  void getAirlineWithoutAirlineName() throws IOException {
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
  void addInvalidAirlineToMapNoParameters() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter(AIRLINE_NAME.getParameterName()));
  }

  @Test
  void addInvalidAirlineToMapFlightNumberMissing() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter(FLIGHT_NUMBER.getParameterName()));
  }

  @Test
  void addInvalidAirlineToMapSourceMissing() throws IOException {
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
  void addInvalidAirlineToMapDepartureMissing() throws IOException {
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
  void addInvalidAirlineToMapDestinationMissing() throws IOException {
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
  void addInvalidAirlineToMapArrivalMissing() throws IOException {
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
  void addValidAirlineToMap() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    addingParamsForPostCall(request);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  void addBlankSourceToMap() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    when(request.getParameter(FLIGHT_NUMBER.getParameterName())).thenReturn(FLIGHT_NUMBER_VALUE);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn("");
    when(request.getParameter(DEPARTURE.getParameterName())).thenReturn(DEPARTURE_VALUE);
    when(request.getParameter(DESTINATION.getParameterName())).thenReturn(DESTINATION_VALUE);
    when(request.getParameter(ARRIVAL.getParameterName())).thenReturn(ARRIVAL_VALUE);

    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doPost(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter(SOURCE.getParameterName()));
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

  @Test
  void deleteAirlineWithoutName() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doDelete(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter(AIRLINE_NAME.getParameterName()));
  }

  @Test
  void deleteAirlineWithName() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    servlet.doDelete(request, response);

    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_NOT_FOUND, Messages.airlineDoesNotExist(AIRLINE_NAME_VALUE));
  }

  @Test
  void addValidAirlineToMapThenDelete() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    addingParamsForPostCall(request);
    servlet.doPost(request, response);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);
    servlet.doDelete(request, response);
    verify(response, atLeast(2)).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  void addValidAirlineToMapThenGet() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    addingParamsForPostCall(request);
    servlet.doPost(request, response);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);
    servlet.doGet(request, response);
    verify(response, atLeast(2)).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  void addValidAirlineToMapThenGetWithoutSrcAndDest() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    addingParamsForPostCall(request);
    servlet.doPost(request, response);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn(null);
    when(request.getParameter(DESTINATION.getParameterName())).thenReturn(null);
    servlet.doGet(request, response);
    verify(response, atLeast(2)).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  void addValidAirlineToMapThenGetWithSrcButNoDest() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    addingParamsForPostCall(request);
    servlet.doPost(request, response);
    verify(response).setStatus(HttpServletResponse.SC_OK);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);
    when(request.getParameter(DESTINATION.getParameterName())).thenReturn(null);
    servlet.doGet(request, response);
    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_BAD_REQUEST, MISSING_SRC_DEST);
  }

  @Test
  void addValidAirlineToMapThenGetWithDestButNoSrc() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    addingParamsForPostCall(request);
    servlet.doPost(request, response);
    verify(response).setStatus(HttpServletResponse.SC_OK);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn(null);
    servlet.doGet(request, response);
    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_BAD_REQUEST, MISSING_SRC_DEST);
  }

  @Test
  void addValidAirlineToMapThenGetWithInvalidSource() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    addingParamsForPostCall(request);
    servlet.doPost(request, response);
    verify(response).setStatus(HttpServletResponse.SC_OK);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn("123");
    servlet.doGet(request, response);
    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_BAD_REQUEST, AirlineConstants.SOURCE + "(" + "123" + ")" + MUST_CONTAIN_ONLY_ALPHABETS);
  }

  @Test
  void addValidAirlineToMapThenGetWithSrcDestButDifferent() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    addingParamsForPostCall(request);
    servlet.doPost(request, response);
    verify(response).setStatus(HttpServletResponse.SC_OK);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn("ABE");
    when(request.getParameter(DESTINATION.getParameterName())).thenReturn("ABQ");
    servlet.doGet(request, response);
    verify(response, atLeast(1))
        .sendError(HttpServletResponse.SC_NOT_FOUND, Messages.flightDoesNotExist("ABE", "ABQ", AIRLINE_NAME_VALUE));
  }

  private static void addingParamsForPostCall(HttpServletRequest request) {
    when(request.getParameter(AIRLINE_NAME.getParameterName())).thenReturn(AIRLINE_NAME_VALUE);
    when(request.getParameter(FLIGHT_NUMBER.getParameterName())).thenReturn(FLIGHT_NUMBER_VALUE);
    when(request.getParameter(SOURCE.getParameterName())).thenReturn(SOURCE_VALUE);
    when(request.getParameter(DEPARTURE.getParameterName())).thenReturn(DEPARTURE_VALUE);
    when(request.getParameter(DESTINATION.getParameterName())).thenReturn(DESTINATION_VALUE);
    when(request.getParameter(ARRIVAL.getParameterName())).thenReturn(ARRIVAL_VALUE);
  }

  @Test
  void addValidAirlineTwice() throws IOException {
    AirlineServlet servlet = new AirlineServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    addingParamsForPostCall(request);
    servlet.doPost(request, response);
    servlet.doPost(request, response);
    verify(response, atLeast(2)).setStatus(HttpServletResponse.SC_OK);
  }
}
