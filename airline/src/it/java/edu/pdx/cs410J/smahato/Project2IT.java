package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.*;
import static edu.pdx.cs410J.smahato.utils.DateTimeUtils.BE_OF_FORMAT_MM_DD_YYYY_HH_MM;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project2} main class.
 */
class Project2IT extends InvokeMainTestCase {

  /**
   * Invokes the main method of {@link Project2} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain(Project2.class, args);
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
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Flight 1 departs PDX at 10/10/2020 10:10 arrives LAX at 10/10/2020 10:10\n"));
  }

  /**
   * Tests that invoking the main method with the -README argument prints the README.txt
   */
  @Test
  void testReadme() {
    MainMethodResult result = invokeMain("-README");
    try {
      InputStream readme = Project2.class.getResourceAsStream("README.txt");
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
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10", "extraArgument");
    assertThat(result.getTextWrittenToStandardError(), equalTo(EXTRA_COMMAND_LINE_ARGS));
  }

  /**
   * Tests that invoking the main method with no -print option prints nothing
   */
  @Test
  void testCommandLineArgumentsWithNoPrintOption() {
    MainMethodResult result = invokeMain("CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardOut(), equalTo(""));
  }

  /**
   * Tests that invoking the main method with no -print option but an error prints the error
   */
  @Test
  void testCommandLineArgumentsWithNoPrintOptionButError() {
    MainMethodResult result = invokeMain("CS410J Air Express", "1s", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardError(), equalTo(FLIGHT_NUMBER_MUST_BE_AN_INTEGER + README_MESSAGE + "\n"));
  }

  /**
   * Tests that invoking the main method with less than 9 arguments issues a missing args error
   */
  @Test
  void testWithLessThan9CommandLineArguments() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020");
    assertThat(result.getTextWrittenToStandardError(), equalTo(MISSING_COMMAND_LINE_ARGS));
  }

  /**
   * Tests that invoking the main method with invalid flight number issues an error message
   * and does not print flight details
   */
  @Test
  void testWithInvalidFlightNumber() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "PDX", "1", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardError(), equalTo(FLIGHT_NUMBER_MUST_BE_AN_INTEGER + README_MESSAGE + "\n"));
  }

  /**
   * Tests that invoking the main method with invalid Departure Date and Time format issues an error message
   * and does not print flight details
   */
  @Test
  void testDepartureDateTimeExchangedArguments() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10:10", "10/10/2020", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardError(), equalTo(DEPARTURE + BE_OF_FORMAT_MM_DD_YYYY_HH_MM + README_MESSAGE + "\n"));
  }

  /**
   * invalid airport code, order is interchanged between arrival date and destination
   */
  @Test
  void testInValidAirPortCodeArguments() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "10/10/2020", "10:10", "LAX");
    assertThat(result.getTextWrittenToStandardError(), equalTo(DESTINATION + MUST_BE_EXACTLY_3_CHARACTERS_LONG + README_MESSAGE + "\n"));
  }

  /**
   * source and destination airport code are same which is invalid
   */
  @Test
  void testWithSrcDestSame() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "PDX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardError(), equalTo(SOURCE_AND_DESTINATION_CANNOT_BE_SAME + README_MESSAGE + "\n"));
  }

  /**
   * Airline name cannot be empty
   */
  @Test
  void testWithBlankAirlineName() {
    MainMethodResult result = invokeMain("-print", "", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Airline name" + CANNOT_BE_NULL_OR_EMPTY + README_MESSAGE + "\n"));
  }

  /**
   * P2 test with valid command line arguments
   */
  @Test
  void testP2AllValidCommandLineArguments() {
    MainMethodResult result = invokeMain("-print", "-textFile", "file.txt", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Flight 1 departs PDX at 10/10/2020 10:10 arrives LAX at 10/10/2020 10:10\n"));
  }

  /**
   * P2 test with no print
   */
  @Test
  void testP2WithNoPrint() {
    MainMethodResult result = invokeMain("-textFile", "file.txt", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardOut(), equalTo(""));
  }

  /**
   * P2 test with no print but returns error
   */
  @Test
  void testP2WithNoPrintButError() {
    MainMethodResult result = invokeMain("-textFile", "file.txt", "", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Airline name" + CANNOT_BE_NULL_OR_EMPTY + README_MESSAGE + "\n"));
  }

  /**
   * P2 test with by same file and airline name
   */
  @Test
  void testP2AppendingFlightsInText() {
    MainMethodResult result = invokeMain("-print", "-textFile", "file.txt", "CS410J Air Express", "2", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Flight 2 departs PDX at 10/10/2020 10:10 arrives LAX at 10/10/2020 10:10\n"));
  }

  /**
   * P2 test by switching options order and invalid flight number
   */
  @Test
  void testP2BySwitchingOptionsOrderAndInvalidFlightNumber() {
    MainMethodResult result = invokeMain("-textFile", "file.txt", "-print", "CS410J Air Express", "3s", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardError(), equalTo(FLIGHT_NUMBER_MUST_BE_AN_INTEGER + README_MESSAGE + "\n"));
  }

  /**
   * P2 test with same file but new airline name
   */
  @Test
  void testP2WithSameFileButNewAirlineName() {
    MainMethodResult result = invokeMain("-print", "-textFile", "file.txt", "CS410J Air Express1", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardError(), equalTo(AIRLINE_NAME_MISMATCH + "\n"));
  }

  /**
   * P2 test with new file
   */
  @Test
  void testP2WithNewFile() {
    String fileName = UUID.randomUUID().toString();
    MainMethodResult result = invokeMain("-print", "-textFile", fileName, "CS410J Air Express1", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Flight 1 departs PDX at 10/10/2020 10:10 arrives LAX at 10/10/2020 10:10\n"));
  }

  /**
   * P2 test with invalid file name in directory
   */
  @Test
  void testWithFileInDir() {
    MainMethodResult result = invokeMain("-print", "-textFile", "a/b/c/file.txt", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Error creating file: a/b/c/file.txt\n"));
  }
}