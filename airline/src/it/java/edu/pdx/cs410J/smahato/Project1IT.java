package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static edu.pdx.cs410J.smahato.ErrorMessages.EXTRA_COMMAND_LINE_ARGS;
import static edu.pdx.cs410J.smahato.ErrorMessages.MISSING_COMMAND_LINE_ARGS;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project1} main class.
 */
class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project1.class, args );
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
      InputStream readme = Project1.class.getResourceAsStream("README.txt");
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
   * Tests that invoking the main method with no -print option prints nothing to System.out
   */
  @Test
  void testCommandLineArgumentsWithNoPrintOption() {
    MainMethodResult result = invokeMain("CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardOut(), equalTo(""));
  }

  /**
   * Tests that invoking the main method with less than 9 arguments issues a missing args error
   */
  @Test
  void testWithLessThan9CommandLineArguments() {
    MainMethodResult result = invokeMain("-print", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020");
    assertThat(result.getTextWrittenToStandardError(), equalTo(MISSING_COMMAND_LINE_ARGS));
  }
}