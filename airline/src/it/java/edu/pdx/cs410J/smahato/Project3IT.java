package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.DEPARTURE;
import static edu.pdx.cs410J.smahato.constants.AirlineConstants.DESTINATION;
import static edu.pdx.cs410J.smahato.constants.DateFormatConstants.MM_DD_YYYY_HH_MM_A;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project3} main class.
 */
class Project3IT extends InvokeMainTestCase {

  /**
   * Invokes the main method of {@link Project3} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain(Project3.class, args);
  }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  void testNoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertThat(result.getTextWrittenToStandardError(), equalTo(MISSING_COMMAND_LINE_ARGS));
  }

  /**
   * Tests that invoking the main method with all arguments prints flight details
   */
  @Test
  void testAllValidCommandLineArguments() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Flight 1 departs PDX at 10/10/20, 10:10 AM arrives LAX at 10/10/20, 11:10 AM\n"));
  }

  /**
   * Tests that invoking the main method with the -README argument prints the README.txt
   */
  @Test
  void testReadme() {
    MainMethodResult result = invokeMain("-README");
    try {
      InputStream readme = Project3.class.getResourceAsStream("README.txt");
      assertThat(readme, not(nullValue()));
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line;
      while ((line = reader.readLine()) != null) {
        assertThat(result.getTextWrittenToStandardOut(), containsString(line));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests that invoking the main method with extra arguments issues an error
   */
  @Test
  void testWithMoreThan9CommandLineArguments() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "10:10", "extraArgument", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo(EXTRA_COMMAND_LINE_ARGS));
  }

  /**
   * Tests that invoking the main method with extra options issues an error
   */
  @Test
  void testWithUnknownOption() {
    MainMethodResult result = invokeMain("-print", "-unKnownOption", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "10:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo(UNKNOWN_OPTION));
    MainMethodResult result1 = invokeMain("-print", "-", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "10:10", "am");
    assertThat(result1.getTextWrittenToStandardError(), equalTo(EXTRA_COMMAND_LINE_ARGS));
  }

  /**
   * Tests that invoking the main method with no -print option prints nothing
   */
  @Test
  void testCommandLineArgumentsWithNoPrintOption() {
    MainMethodResult result = invokeMain("CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "10:11", "am");
    assertThat(result.getTextWrittenToStandardOut(), equalTo(""));
  }

  /**
   * Tests that invoking the main method with no -print option but an error prints the error
   */
  @Test
  void testCommandLineArgumentsWithNoPrintOptionButError() {
    MainMethodResult result = invokeMain("CS410J Air Express", "1s", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "10:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo("1s - " + FLIGHT_NUMBER_MUST_BE_AN_INTEGER + README_MESSAGE + "\n"));
  }

  /**
   * Tests that invoking the main method with less than 9 arguments issues a missing args error
   */
  @Test
  void testWithLessThan9CommandLineArguments() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Invalid " + DEPARTURE + " date format - 10/10/2020 10:10 LAX! Please follow the format: " + MM_DD_YYYY_HH_MM_A + README_MESSAGE + "\n"));
  }

  /**
   * Tests that invoking the main method with invalid flight number issues an error message
   * and does not print flight details
   */
  @Test
  void testWithInvalidFlightNumber() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "PDX", "1", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "10:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo("PDX - " + FLIGHT_NUMBER_MUST_BE_AN_INTEGER + README_MESSAGE + "\n"));
  }

  /**
   * Tests that invoking the main method with invalid Departure Date and Time format issues an error message
   * and does not print flight details
   */
  @Test
  void testDepartureDateTimeExchangedArguments() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10:10", "10/10/2020", "am", "LAX", "10/10/2020", "10:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Invalid " + DEPARTURE + " date format - 10:10 10/10/2020 am! Please follow the format: " + MM_DD_YYYY_HH_MM_A + README_MESSAGE + "\n"));
  }

  /**
   * invalid airport code, order is interchanged between arrival date and destination
   */
  @Test
  void testInValidAirPortCodeArguments() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "10/10/2020", "10:10", "pm", "LAX");
    assertThat(result.getTextWrittenToStandardError(), equalTo(DESTINATION + "(10/10/2020)" + MUST_BE_EXACTLY_3_CHARACTERS_LONG + README_MESSAGE + "\n"));
  }

  /**
   * source and destination airport code are same which is invalid
   */
  @Test
  void testWithSrcDestSame() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "PDX", "10/10/2020", "10:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo(SOURCE_AND_DESTINATION_CANNOT_BE_SAME + README_MESSAGE + "\n"));
  }

  /**
   * Airline name cannot be empty
   */
  @Test
  void testWithBlankAirlineName() {
    MainMethodResult result = invokeMain("-print", "", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "10:10", "pm");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Airline name" + CANNOT_BE_NULL_OR_EMPTY + README_MESSAGE + "\n"));
  }

  /**
   * P2 test with destination and arrival time as same
   */
  @Test
  void testP2WithSameDestinationArrivalTime() {
    MainMethodResult result = invokeMain("-print", "-textFile", "textFile", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "10:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo(DEPARTURE_BEFORE_ARRIVAL + README_MESSAGE + "\n"));
  }

  /**
   * P2 test with valid command line arguments
   */
  @Test
  void testP2AllValidCommandLineArguments() {
    MainMethodResult result = invokeMain("-print", "-textFile", "textFile", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Flight 1 departs PDX at 10/10/20, 10:10 AM arrives LAX at 10/10/20, 11:10 AM\n"));
  }

  /**
   * P2 test with by same file and airline name
   */
  @Test
  void testP2AppendingFlightsInText() {
    MainMethodResult result = invokeMain("-print", "-textFile", "textFile", "CS410J Air Express", "2", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Flight 2 departs PDX at 10/10/20, 10:10 AM arrives LAX at 10/10/20, 11:10 AM\n"));
  }

  /**
   * P2 test with same file but new airline name
   */
  @Test
  void testP2WithSameFileButNewAirlineName() {
    MainMethodResult result = invokeMain("-print", "-textFile", "textFile", "CS410J Air Express1", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo(AIRLINE_NAME_MISMATCH + "\n"));
  }

  /**
   * P2 test with no print
   */
  @Test
  void testP2WithNoPrint() {
    MainMethodResult result = invokeMain("-textFile", "textFile", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "10:11", "am");
    assertThat(result.getTextWrittenToStandardOut(), equalTo(""));
  }

  /**
   * P2 test with no print but returns error
   */
  @Test
  void testP2WithNoPrintButError() {
    MainMethodResult result = invokeMain("-textFile", "textFile", "", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "10:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Airline name" + CANNOT_BE_NULL_OR_EMPTY + README_MESSAGE + "\n"));
  }

  /**
   * P2 test by switching options order and invalid flight number
   */
  @Test
  void testP2BySwitchingOptionsOrderAndInvalidFlightNumber() {
    MainMethodResult result = invokeMain("-textFile", "textFile", "-print", "CS410J Air Express", "3s", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo("3s - " + FLIGHT_NUMBER_MUST_BE_AN_INTEGER + README_MESSAGE + "\n"));
  }

  /**
   * P2 test with invalid file name in directory
   */
  @Test
  void testWithFileInDir() {
    MainMethodResult result = invokeMain("-print", "-textFile", "a/b/c/textFile", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Error creating file: a/b/c/textFile\n"));
  }

  /**
   * P3 test with valid command line arguments
   */
  @Test
  void testP3AllValidCommandLineArguments() {
    MainMethodResult result = invokeMain("-print", "-textFile", "textFile", "-pretty", "prettyFile", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Flight 1 departs PDX at 10/10/20, 10:10 AM arrives LAX at 10/10/20, 11:10 AM\n"));
  }

  @Test
  void testP3WithInvalidFlight() {
    MainMethodResult result = invokeMain("-print", "-textFile", "textFile", "-pretty", "prettyFile", "CS410J Air Express", "1s", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo("1s - " + FLIGHT_NUMBER_MUST_BE_AN_INTEGER + README_MESSAGE + "\n"));
  }

  @Test
  void testP3InvalidPrettyFile() {
    MainMethodResult result = invokeMain("-print", "-textFile", "textFile", "-pretty", "/a/a.txt", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Error creating file: /a/a.txt\n"));
  }

  @Test
  void testP3WithInvalidTextFile() {
    MainMethodResult result = invokeMain("-print", "-textFile", "/a/b.txt", "-pretty", "prettyFile", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Error creating file: /a/b.txt\n"));
  }

  @Test
  void testP3WithNoPrint() {
    MainMethodResult result = invokeMain("-textFile", "textFile", "-pretty", "prettyFile", "CS410J Air Express", "1", "PDX", "10/10/2020", "9:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardOut(), equalTo(""));
  }

  @Test
  void testP3WithNoTextFile() {
    MainMethodResult result = invokeMain("-pretty", "prettyFile", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:1", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardOut(), equalTo(""));
  }

  @Test
  void testP3WithHyphenInFileNameOfPretty() {
    MainMethodResult result = invokeMain("-pretty", "-", "CS410J Air Express", "1", "ABE", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo(""));
  }

  @Test
  void testP3WithHyphenInFileNameOfPrettyWithPrint() {
    MainMethodResult result = invokeMain("-print", "-pretty", "-", "CS410J Air Express", "1", "ABE", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardError(), equalTo(""));
  }
}