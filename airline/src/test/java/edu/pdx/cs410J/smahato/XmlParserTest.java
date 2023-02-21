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

public class XmlParserTest {

  @Test
  void validXmlFileCanBeParsed() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("valid-airline.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    Airline airline = parser.parse();
    assertThat(airline.getName(), equalTo("Valid Airlines"));
  }

  @Test
  void invalidTextFile() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("valid-airline.txt");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  @Test
  void invalidDay() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-day.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  @Test
  void invalidMonth() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-month.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  @Test
  void invalidYear() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-year.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  @Test
  void invalidHour() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-hour.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  @Test
  void invalidMinute() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-minute.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  @Test
  void invalidFlightNumber() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-flightNum.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  @Test
  void invalidAirportCode() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-airport.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }

  @Test
  void invalidRootElement() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("invalid-airline-root.xml");
    assertThat(resource, notNullValue());

    XmlParser parser = new XmlParser(new InputStreamReader(resource));
    assertThrows(XMLException.class, parser::parse);
  }
}
