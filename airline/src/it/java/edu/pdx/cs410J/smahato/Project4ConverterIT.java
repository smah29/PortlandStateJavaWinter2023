package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.MISSING_COMMAND_LINE_ARGS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Project4ConverterIT extends InvokeMainTestCase {
  private MainMethodResult invokeMain(String... args) {
    return invokeMain(Converter.class, args);
  }

  @Test
  void test1NoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertThat(result.getTextWrittenToStandardError(), equalTo(MISSING_COMMAND_LINE_ARGS + "\n"));
  }

  @Test
  void test2AllValidCommandLineArguments() {
    MainMethodResult result = invokeMain("txtFile", "file.xml");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Successfully created file: file.xml\n"));
  }

  @Test
  void test3WithInvalidTextFile() {
    MainMethodResult result = invokeMain("a/txtFile", "file.xml");
    assertThat(result.getTextWrittenToStandardError(), equalTo("a/txtFile file does not exist!\n"));
  }

  @Test
  void test4WithInvalidTextFile() {
    MainMethodResult result = invokeMain("file.xml", "file.xml");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Error while parsing the file : file.xml\n"));
  }

  @Test
  void test5InvalidXMLFile() {
    MainMethodResult result = invokeMain("txtFile", "a/file.xml");
    assertThat(result.getTextWrittenToStandardError(), equalTo("Error creating file: a/file.xml\n"));
  }
}
