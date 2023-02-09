package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.smahato.exception.AirportCodeException;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link Flight} class.
 */
public class FlightTest {

  /**
   * Test1 : create a flight with number, source, destination, departureDateTime, arrivalDateTime
   * needs the creation of a multi arg constructor in Flight.java
   * check the flight number, source, destination, departureDateTime, arrivalDateTime
   */
  @Test
  void createFlightWithNumberSourceDestinationDepartureDateTimeArrivalDateTime() {
    Flight flight = new Flight("1", "PDX", "LAX", "1/10/2020 10:10 am", "10/1/2020 11:10 am");
    //checking the flight number with null or alphabets will give compile time error, that's why we are not checking it
    assertThat(flight.getNumber(), equalTo(1));
    assertThat(flight.getSource(), equalTo("PDX"));
    assertThat(flight.getDestination(), equalTo("LAX"));
    assertThat(flight.getDepartureString(), equalTo("1/10/20, 10:10 AM"));
    assertThat(flight.getArrivalString(), equalTo("10/1/20, 11:10 AM"));
    assertThat(flight.toString(), equalTo("Flight 1 departs PDX at 1/10/20, 10:10 AM arrives LAX at 10/1/20, 11:10 AM"));
  }

  /**
   * Test create a flight with flightNumber as abc
   * should throw an exception
   */
  @Test
  void createFlightWithNumberAsAbc() {
    assertThrows(NumberFormatException.class, () ->
        new Flight("abc", "PDX", "LAX", "10/10/2020 10:10 am", "10/10/2020 11:10 am")
    );
  }

  @Test
  void createFlightWithNumberAsBlank() {
    assertThrows(NullPointerException.class, () ->
        new Flight("", "PDX", "LAX", "10/10/2020 10:10 am", "10/10/2020 11:10 am")
    );
  }

  @Test
  void createFlightWithNumberAsNull() {
    assertThrows(NullPointerException.class, () ->
        new Flight(null, "PDX", "LAX", "10/10/2020 10:10 am", "10/10/2020 11:10 am")
    );
  }

  /**
   * Test create flight with null source
   * NullPointerException is thrown because source cannot be null
   */
  @Test
  void testNullSource() {
    assertThrows(NullPointerException.class, () -> new Flight("1", null, "LAX", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * Test create flight with empty source
   * NullPointerException is thrown because source cannot be blank
   */
  @Test
  void testEmptySource() {
    assertThrows(NullPointerException.class, () -> new Flight("1", "", "LAX", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * Test create flight with source as only A
   * AirportCodeException is thrown because the source is not a valid airport code  (it is only one character long)
   */
  @Test
  void testSourceAsOnlyA() {
    assertThrows(AirportCodeException.class, () -> new Flight("1", "A", "LAX", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * Test create flight with source as apple
   * AirportCodeException is thrown because source must be exactly 3 characters long
   */
  @Test
  void testSourceAsApple() {
    assertThrows(AirportCodeException.class, () -> new Flight("1", "apple", "LAX", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * Test create flight with source as 123
   * AirportCodeException is thrown because the source is not a valid airport code (it is not alphabets)
   */

  @Test
  void testSourceAs123() {
    assertThrows(AirportCodeException.class, () -> new Flight("1", "123", "LAX", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * Test create flight with source as 1a3
   * AirportCodeException is thrown because the source is not a valid airport code (it is not alphabets)
   */
  @Test
  void testSourceAs1a3() {
    assertThrows(AirportCodeException.class, () -> new Flight("1", "1a3", "LAX", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * Test create flight with null destination
   * NullPointerException is thrown because destination is null
   */
  @Test
  void testNullDestination() {
    assertThrows(NullPointerException.class, () -> new Flight("1", "PDX", null, "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * Test create flight with empty destination
   * NullPointerException is thrown because the destination is blank
   */
  @Test
  void testEmptyDestination() {
    assertThrows(NullPointerException.class, () -> new Flight("1", "PDX", "", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * Test create flight with destination as only A
   * AirportCodeException is thrown because the destination is not a valid airport code  (it is only one character long)
   */
  @Test
  void testDestinationAsOnlyA() {
    assertThrows(AirportCodeException.class, () -> new Flight("1", "PDX", "A", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * Test create flight with destination as apple
   * AirportCodeException is thrown because the destination is not a valid airport code  (it is more than three characters long)
   */
  @Test
  void testDestinationAsApple() {
    assertThrows(AirportCodeException.class, () -> new Flight("1", "PDX", "apple", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * Test create flight with destination as 123
   * AirportCodeException is thrown as the destination is not a valid airport code (it is not alphabets)
   */

  @Test
  void testDestinationAs123() {
    assertThrows(AirportCodeException.class, () -> new Flight("1", "PDX", "123", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * Test create flight with destination as 1a3
   * AirportCodeException is thrown because the destination is not a valid airport code (it is not alphabets)
   */
  @Test
  void testDestinationAs1a3() {
    assertThrows(AirportCodeException.class, () -> new Flight("1", "PDX", "1a3", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * Test create flight with same source and destination PDX
   * DuplicateAirportCodeException is thrown because the source and destination are the same
   */
  @Test
  void testSameSourceAndDestination() {
    assertThrows(AirportCodeException.class, () -> new Flight("1", "PDX", "PDX", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * airport code must match the AirportNames map keys, else gives AirportCodeException
   */
  @Test
  void testWithInvalidSourceAirportCode() {
    assertThrows(AirportCodeException.class, () -> new Flight("1", "ABC", "PDX", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  /**
   * airport code must match the AirportNames map keys, else gives AirportCodeException
   */
  @Test
  void testWithInvalidDestinationAirportCode() {
    assertThrows(AirportCodeException.class, () -> new Flight("1", "PDX", "ABC", "10/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  @Test
  void testWithValidCaseInsensitiveDestinationAirportCode() {
    Flight flight = new Flight("1", "PDX", "abe", "10/10/2020 10:10 am", "10/10/2020 11:10 am");
    assertThat(flight.toString(), equalTo("Flight 1 departs PDX at 10/10/20, 10:10 AM arrives ABE at 10/10/20, 11:10 AM"));
  }

  // Test create flight with null departureString
  @Test
  void testNullDepartureString() {
    assertThrows(NullPointerException.class, () -> new Flight("1", "PDX", "LAX", null, "10/10/2020 11:10"));
  }

  // Test create flight with empty departureString
  @Test
  void testEmptyDepartureString() {
    assertThrows(NullPointerException.class, () -> new Flight("1", "PDX", "LAX", "", "10/10/2020 11:10 am"));
  }

  @Test
  void testAlphabeticDepartureString() {
    assertThrows(DateTimeException.class, () -> new Flight("1", "PDX", "LAX", "Thursday 2 pm", "10/10/2020 11:10 am"));
  }

  // Test create flight with departureString as 10/10/20 10:10 am(The year should always be four digits)
  @Test
  void testDepartureStringWithInvalidYear() {
    assertThrows(DateTimeException.class, () -> new Flight("1", "PDX", "LAX", "10/10/20 10:10 am", "10/10/2020 11:10 am"));
  }

  //Test create flight with departureString as 21/10/2020 10:10 (The month should be between 1 and 12)
  @Test
  void testDepartureStringWithInvalidMonth() {
    assertThrows(DateTimeException.class, () -> new Flight("1", "PDX", "LAX", "21/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  //Test create flight with departureString as 0/10/2020 10:10 (The month should be between 1 and 12)
  @Test
  void testDepartureStringWithInvalidMonth0() {
    assertThrows(DateTimeException.class, () -> new Flight("1", "PDX", "LAX", "0/10/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  //Test create flight with departureString with invalid 31 day in Feb
  @Test
  void testDepartureStringWithInvalidDayInFeb() {
    assertThrows(DateTimeException.class, () -> new Flight("1", "PDX", "LAX", "2/31/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  //Test create flight with departureString with invalid 29 day in Feb
  @Test
  void testDepartureStringWith29DayInFeb() {
    assertThrows(DateTimeException.class, () -> new Flight("1", "PDX", "LAX", "2/29/2019 10:10 am", "10/10/2020 11:10 am"));
  }

  //Test create flight with departureString with invalid 29 day in Feb, not divisible by 400, not a leap year
  @Test
  void testDepartureStringWith29DayInFebInYear1000() {
    assertThrows(DateTimeException.class, () -> new Flight("1", "PDX", "LAX", "2/29/1000 10:10 am", "10/10/2020 11:10 am"));
  }

  //Test create flight with departureString with leap year day in Feb
  @Test
  void testDepartureStringWithLeapYearDayInFeb() {
    Flight flight = new Flight("1", "PDX", "LAX", "2/29/2020 10:10 am", "10/10/2020 11:10 am");
    assertThat(flight.getDepartureString(), equalTo("2/29/20, 10:10 AM"));
  }

  //Test create flight with departureString with day 32 (The day should be between 1 and 31)
  @Test
  void testDepartureStringWithInvalidDay() {
    assertThrows(DateTimeException.class, () -> new Flight("1", "PDX", "LAX", "12/32/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  //Test create flight with departureString as 10/0/2020 10:10 (The day should be between 1 and 31)
  @Test
  void testDepartureStringWithInvalidDay0() {
    assertThrows(DateTimeException.class, () -> new Flight("1", "PDX", "LAX", "10/0/2020 10:10 am", "10/10/2020 11:10 am"));
  }

  // Test create flight with ArrivalString as 10/10/2020 25:10 (The hour should be between 0 and 12)
  @Test
  void testArrivalStringWithInvalidHour() {
    assertThrows(DateTimeException.class, () -> new Flight("1", "PDX", "LAX", "10/10/2020 11:10 am", "2/29/2000 25:10 am"));
  }

  // Test create flight with ArrivalString as 10/10/2020 22:10 (The hour should be between 0 and 12)
  @Test
  void testArrivalStringWithInvalidHourBelow23() {
    assertThrows(DateTimeException.class, () -> new Flight("1", "PDX", "LAX", "10/10/2020 11:10 am", "2/29/2000 22:10 am"));
  }

  // Test create flight with ArrivalString as 10/10/2020 10:60 (The minute should be between 0 and 59)
  @Test
  void testArrivalStringWithInvalidMinute() {
    assertThrows(DateTimeException.class, () -> new Flight("1", "PDX", "LAX", "10/10/2020 11:10 am", "2/10/2020 10:60 am"));
  }

  // No more test cases required for the arrivalString as the same tests have been written for the departureString
  // invalid validateFlightSchedule have been already tested both for departureString and arrivalString

}
