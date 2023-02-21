package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.smahato.exception.XMLException;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link XmlParser} class.
 */
public class XmlParserTest {

  /**
   * Tests that a valid XML file can be parsed.
   *
   * @throws ParserException if the XML file cannot be parsed
   */
  @Test
  void validXmlFileCanBeParsed() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("valid-airline.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    Airline airline = parser.parse();
    assertThat(airline.getName(), equalTo("Valid Airlines"));
  }

  /**
   * Tests that an invalid XML file throws an exception.
   *
   * @throws ParserException if the XML file cannot be parsed
   */
  @Test
  void invalidTextFile() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("valid-airline.txt");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  /**
   * Tests that an invalid day in XML file throws an exception.
   *
   * @throws ParserException if the XML file cannot be parsed
   */
  @Test
  void invalidDay() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-day.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  /**
   * Tests that an invalid month in XML file throws an exception.
   *
   * @throws ParserException if the XML file cannot be parsed
   */
  @Test
  void invalidMonth() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-month.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  /**
   * Tests that an invalid year in XML file throws an exception.
   *
   * @throws ParserException if the XML file cannot be parsed
   */
  @Test
  void invalidYear() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-year.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  /**
   * Tests that an invalid hour in XML file throws an exception.
   *
   * @throws ParserException if the XML file cannot be parsed
   */
  @Test
  void invalidHour() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-hour.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  /**
   * Tests that an invalid minute in XML file throws an exception.
   *
   * @throws ParserException if the XML file cannot be parsed
   */
  @Test
  void invalidMinute() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-minute.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  /**
   * Tests that an invalid flight number XML file throws an exception.
   *
   * @throws ParserException if the XML file cannot be parsed
   */
  @Test
  void invalidFlightNumber() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-flightNum.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  /**
   * Tests that an invalid airport code in XML file throws an exception.
   *
   * @throws ParserException if the XML file cannot be parsed
   */
  @Test
  void invalidAirportCode() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-airport.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  /**
   * Tests that an invalid element in XML file throws an exception.
   *
   * @throws ParserException if the XML file cannot be parsed
   */
  @Test
  void invalidRootElement() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-root.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }
}
