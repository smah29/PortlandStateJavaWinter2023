package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.smahato.constants.AirlineConstants;
import edu.pdx.cs410J.smahato.constants.ErrorMessages;
import edu.pdx.cs410J.smahato.constants.Option;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static edu.pdx.cs410J.smahato.Messages.MISSING_SRC_DEST;
import static edu.pdx.cs410J.smahato.constant.AirlineTestValues.*;
import static edu.pdx.cs410J.smahato.constants.DateFormatConstants.MM_DD_YYYY_hh_MM_a;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.*;
import static edu.pdx.cs410J.smahato.constants.Option.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.MethodOrderer.MethodName;

/**
 * An integration test for {@link Project5} that invokes its main method with
 * various arguments
 */
@TestMethodOrder(MethodName.class)
class Project5IT extends InvokeMainTestCase {
  private static final String HOSTNAME = "localhost";
  private static final String PORT = System.getProperty("http.port", "8080");

  @Test
  void testNoCommandLineArguments() {
    MainMethodResult result = invokeMain(Project5.class);
    assertThat(result.getTextWrittenToStandardError(), containsString(ErrorMessages.MISSING_COMMAND_LINE_ARGS));
  }

  @Test
  void testReadme() {
    MainMethodResult result = invokeMain(Project5.class, "-README");
    try {
      InputStream readme = Project5.class.getResourceAsStream("README.txt");
      assertThat(readme, not(nullValue()));
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line;
      while ((line = reader.readLine()) != null) {
        assertThat(result.getTextWrittenToStandardOut(), CoreMatchers.containsString(line));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void testHostNotGiven() {
    MainMethodResult result = invokeMain(Project5.class, "Airline");
    assertThat(result.getTextWrittenToStandardError(), containsString(HOST.getOption() + OPTION_MISSING));
  }

  @Test
  void testPortNotGiven() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME);
    assertThat(result.getTextWrittenToStandardError(), containsString(Option.PORT.getOption() + OPTION_MISSING));
  }

  @Test
  void testPortValueNotGiven() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption());
    assertThat(result.getTextWrittenToStandardError(), containsString(Option.PORT.getOption() + OPTION_VALUE_MISSING));
  }

  @Test
  void testInvalidPortValueGiven() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), "Airline");
    assertThat(result.getTextWrittenToStandardError(), containsString(PORT_NUMBER_SHOULD_BE_AN_INTEGER));
  }

  @Test
  void testAddAirline() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT,
        AIRLINE_NAME_VALUE, "1", SOURCE_VALUE, "12/12/2012", "12:12", "AM", DESTINATION_VALUE, "12/12/2012", "1:12", "AM");
    assertThat(result.getTextWrittenToStandardError(), equalTo(""));
  }

  @Test
  void testAddAirlineWithPrintOption() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT, PRINT.getOption(),
        AIRLINE_NAME_VALUE, "1", SOURCE_VALUE, "12/12/2012", "12:12", "AM", DESTINATION_VALUE, "12/12/2012", "1:12", "AM");
    assertThat(result.getTextWrittenToStandardOut(), containsString("Flight 1 departs PDX at 12/12/12, 12:12 AM arrives LAX at 12/12/12, 1:12 AM"));
  }

  @Test
  void testAddAirlineWithInvalidHost() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), "bdjdbj", Option.PORT.getOption(), PORT, PRINT.getOption(),
        AIRLINE_NAME_VALUE, "1", SOURCE_VALUE, "12/12/2012", "12:12", "AM", DESTINATION_VALUE, "12/12/2012", "1:12", "AM");
    assertThat(result.getTextWrittenToStandardError(), containsString(invalidHostOrPort("bdjdbj", PORT)));
  }

  @Test
  void testAddAirlineExtraArgs() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT,
        AIRLINE_NAME_VALUE, "1", SOURCE_VALUE, "12/12/2012", "12:12", "AM", DESTINATION_VALUE, "12/12/2012", "1:12", "AM", "extra");
    assertThat(result.getTextWrittenToStandardError(), containsString(EXTRA_COMMAND_LINE_ARGS));
  }

  @Test
  void testAddAirlineExtraOption() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT, "-invalidOption",
        AIRLINE_NAME_VALUE, "1", SOURCE_VALUE, "12/12/2012", "12:12", "AM", DESTINATION_VALUE, "12/12/2012", "1:12", "AM", "extra");
    assertThat(result.getTextWrittenToStandardError(), containsString(UNKNOWN_OPTION));
  }

  @Test
  void testAddAirlineLessArgs() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT,
        AIRLINE_NAME_VALUE, "1", SOURCE_VALUE, "12/12/2012", "12:12", "AM", DESTINATION_VALUE, "12/12/2012", "1:12");
    assertThat(result.getTextWrittenToStandardError(), containsString(invalidDateFormat(AirlineConstants.ARRIVAL, "12/12/2012 1:12", MM_DD_YYYY_hh_MM_a)));
  }

  @Test
  void testSearchAirlineWithSrcDest() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT, SEARCH.getOption(),
        AIRLINE_NAME_VALUE, SOURCE_VALUE, DESTINATION_VALUE);
    assertThat(result.getTextWrittenToStandardOut(), containsString("Flights for airline Air Express"));
  }

  @Test
  void testSearchAirlineWithSrcDestWithPrintOption() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT, SEARCH.getOption(), PRINT.getOption(),
        AIRLINE_NAME_VALUE, SOURCE_VALUE, DESTINATION_VALUE);
    assertThat(result.getTextWrittenToStandardOut(), containsString("Flights for airline Air Express"));
  }

  @Test
  void testSearchAirlineNotFound() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT, SEARCH.getOption(),
        "Air Express1");
    assertThat(result.getTextWrittenToStandardError(), containsString(Messages.airlineDoesNotExist("Air Express1")));
  }

  @Test
  void testSearchAirlineWrongSrc() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT, SEARCH.getOption(),
        AIRLINE_NAME_VALUE, SOURCE_VALUE);
    assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_SRC_DEST));
  }

  @Test
  void testSearchAirlineWithSrcDestWithInvalidPort() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), "1211", SEARCH.getOption(),
        AIRLINE_NAME_VALUE, SOURCE_VALUE, DESTINATION_VALUE);
    assertThat(result.getTextWrittenToStandardError(), containsString(invalidHostOrPort(HOSTNAME, "1211")));
  }

  @Test
  void testSearchAirlineWithExtraArgs() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT, SEARCH.getOption(),
        AIRLINE_NAME_VALUE, SOURCE_VALUE, DESTINATION_VALUE, "extra");
    assertThat(result.getTextWrittenToStandardError(), containsString(EXTRA_COMMAND_LINE_ARGS));
  }

  @Test
  void testSearchAirlineWithDifferentDest() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT, SEARCH.getOption(),
        AIRLINE_NAME_VALUE, SOURCE_VALUE, "ABE");
    assertThat(result.getTextWrittenToStandardError(), containsString(Messages.flightDoesNotExist(SOURCE_VALUE, "ABE", AIRLINE_NAME_VALUE)));
  }

  @Test
  void testSearchAirlineWithDifferentSrc() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT, SEARCH.getOption(),
        AIRLINE_NAME_VALUE, "ABE", DESTINATION_VALUE);
    assertThat(result.getTextWrittenToStandardError(), containsString(Messages.flightDoesNotExist("ABE", DESTINATION_VALUE, AIRLINE_NAME_VALUE)));
  }

  @Test
  void testSearchAirlineWithSameSrcDest() {
    MainMethodResult result = invokeMain(Project5.class, HOST.getOption(), HOSTNAME, Option.PORT.getOption(), PORT, SEARCH.getOption(),
        AIRLINE_NAME_VALUE, SOURCE_VALUE, SOURCE_VALUE);
    assertThat(result.getTextWrittenToStandardError(), containsString(SOURCE_AND_DESTINATION_CANNOT_BE_SAME));
  }

}