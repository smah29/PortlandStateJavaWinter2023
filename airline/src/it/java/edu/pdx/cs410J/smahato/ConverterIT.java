package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.MISSING_COMMAND_LINE_ARGS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Integration tests for the {@link Converter} main class.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class ConverterIT extends InvokeMainTestCase {
  private MainMethodResult invokeMain(String... args) {
    return invokeMain(Converter.class, args);
  }

  /**
   * P4 test just to make sure txtFile exists
   */
  @Test
  void test0() {
    MainMethodResult result = invokeMain(Project4.class, "-textFile", "txtFile", "-print", "CS410J Air Express", "1", "PDX", "10/10/2020", "10:10", "am", "LAX", "10/10/2020", "11:10", "am");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Flight 1 departs PDX at 10/10/20, 10:10 AM arrives LAX at 10/10/20, 11:10 AM\n"));
  }

  /**
   * Converter with no command line arguments
   */
  @Test
  void test1NoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertThat(result.getTextWrittenToStandardError(), equalTo(MISSING_COMMAND_LINE_ARGS + "\n"));
  }

  /**
   * Converter with all valid command line arguments
   */
  @Test
  void test2AllValidCommandLineArguments() {
    MainMethodResult result = invokeMain("txtFile", "file.xml");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Successfully created file: file.xml\n"));
  }

  /*
   * Converter with invalid text file
   */
  @Test
  void test3WithInvalidTextFile() {
    MainMethodResult result = invokeMain("a/txtFile", "file.xml");
    assertThat(result.getTextWrittenToStandardError(), equalTo("a/txtFile file does not exist!\n"));
  }

  /**
   * Converter with invalid xml file
   */
  @Test
  void test4WithInvalidTextFile() {
    MainMethodResult result = invokeMain("file.xml", "file.xml");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Error while parsing the file : file.xml\n"));
  }

  /**
   * Converter with invalid xml file
   */
  @Test
  void test5InvalidXMLFile() {
    MainMethodResult result = invokeMain("txtFile", "a/file.xml");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Error creating file: a/file.xml\n"));
  }
}
