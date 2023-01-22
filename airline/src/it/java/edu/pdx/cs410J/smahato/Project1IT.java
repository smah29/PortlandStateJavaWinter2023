package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
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
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

  // Test with all arguments
  @Test
  @Disabled
  void testAllValidCommandLineArguments() {
    MainMethodResult result = invokeMain("CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "LAX", "10/10/2020", "10:10");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Flight 1 departs PDX at 10/10/2020 10:10 arrives LAX at 10/10/2020 10:10"));
  }
}