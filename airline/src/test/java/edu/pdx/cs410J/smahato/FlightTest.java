package edu.pdx.cs410J.smahato;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link Flight} class.
 */
public class FlightTest {

  @Test
  void forProject1ItIsOkayIfGetDepartureTimeReturnsNull() {
    //create a flight with all the required parameters
    Flight flight = new Flight(1, "PDX", "LAX", "12/12/2012 12:12", "12/12/2012 12:12");
    assertThat(flight.getDeparture(), is(nullValue()));
  }

  /**
   * Test1 : create a flight with number, source, destination, departureDateTime, arrivalDateTime
   * needs the creation of a multi arg constructor in Flight.java
   * check the flight number, source, destination, departureDateTime, arrivalDateTime
   */
  @Test
  void createFlightWithNumberSourceDestinationDepartureDateTimeArrivalDateTime() {
    Flight flight = new Flight(1, "PDX", "LAX", "10/10/2020 10:10", "10/10/2020 11:10");
    //checking the flight number with null or alphabets will give compile time error, that's why we are not checking it
    assertThat(flight.getNumber(), equalTo(1));
    assertThat(flight.getSource(), equalTo("PDX"));
    assertThat(flight.getDestination(), equalTo("LAX"));
    assertThat(flight.getDepartureString(), equalTo("10/10/2020 10:10"));
    assertThat(flight.getArrivalString(), equalTo("10/10/2020 11:10"));
    assertThat(flight.toString(), equalTo("Flight 1 departs PDX at 10/10/2020 10:10 arrives LAX at 10/10/2020 11:10"));
  }

  /**
   * Test create flight with null source
   * NullPointerException is thrown because source cannot be null
   */
  @Test
  void testNullSource() {
    assertThrows(NullPointerException.class, () -> new Flight(1, null, "LAX", "10/10/2020 10:10", "10/10/2020 11:10"));
  }

  /**
   * Test create flight with empty source
   * NullPointerException is thrown because source cannot be blank
   */
  @Test
  void testEmptySource() {
    assertThrows(NullPointerException.class, () -> new Flight(1, "", "LAX", "10/10/2020 10:10", "10/10/2020 11:10"));
  }

  /**
   * Test create flight with source as only A
   * InvalidSourceException is thrown because the source is not a valid airport code  (it is only one character long)
   */
  @Test
  void testSourceAsOnlyA() {
    assertThrows(InvalidSourceException.class, () -> new Flight(1, "A", "LAX", "10/10/2020 10:10", "10/10/2020 11:10"));
  }

  /**
   * Test create flight with source as apple
   * InvalidSourceException is thrown because source must be exactly 3 characters long
   */
  @Test
  void testSourceAsApple() {
    assertThrows(InvalidSourceException.class, () -> new Flight(1, "apple", "LAX", "10/10/2020 10:10", "10/10/2020 11:10"));
  }

  /**
   * Test create flight with source as 123
   * InvalidSourceException is thrown because the source is not a valid airport code (it is not alphabets)
   */

  @Test
  void testSourceAs123() {
    assertThrows(InvalidSourceException.class, () -> new Flight(1, "123", "LAX", "10/10/2020 10:10", "10/10/2020 11:10"));
  }

  /**
   * Test create flight with source as 1a3
   * InvalidSourceException is thrown because the source is not a valid airport code (it is not alphabets)
   */
  @Test
  void testSourceAs1a3() {
    assertThrows(InvalidSourceException.class, () -> new Flight(1, "1a3", "LAX", "10/10/2020 10:10", "10/10/2020 11:10"));
  }

  /**
   * Test create flight with null destination
   * InvalidDestinationException is thrown because destination is null
   */
  @Test
  void testNullDestination() {
    assertThrows(NullPointerException.class, () -> new Flight(1, "PDX", null, "10/10/2020 10:10", "10/10/2020 11:10"));
  }

  /**
   * Test create flight with empty destination
   * InvalidDestinationException is thrown because the destination is blank
   */
  @Test
  void testEmptyDestination() {
    assertThrows(NullPointerException.class, () -> new Flight(1, "PDX", "", "10/10/2020 10:10", "10/10/2020 11:10"));
  }

  /**
   * Test create flight with destination as only A
   * InvalidDestinationException is thrown because the destination is not a valid airport code  (it is only one character long)
   */
  @Test
  void testDestinationAsOnlyA() {
    assertThrows(InvalidDestinationException.class, () -> new Flight(1, "PDX", "A", "10/10/2020 10:10", "10/10/2020 11:10"));
  }

  /**
   * Test create flight with destination as apple
   * InvalidDestinationException is thrown because the destination is not a valid airport code  (it is more than three characters long)
   */
  @Test
  void testDestinationAsApple() {
    assertThrows(InvalidDestinationException.class, () -> new Flight(1, "PDX", "apple", "10/10/2020 10:10", "10/10/2020 11:10"));
  }

  /**
   * Test create flight with destination as 123
   * InvalidDestinationException is thrown as the destination is not a valid airport code (it is not alphabets)
   */

  @Test
  void testDestinationAs123() {
    assertThrows(InvalidDestinationException.class, () -> new Flight(1, "PDX", "123", "10/10/2020 10:10", "10/10/2020 11:10"));
  }

  /**
   * Test create flight with destination as 1a3
   * InvalidDestinationException is thrown because the destination is not a valid airport code (it is not alphabets)
   */
  @Test
  void testDestinationAs1a3() {
    assertThrows(InvalidDestinationException.class, () -> new Flight(1, "PDX", "1a3", "10/10/2020 10:10", "10/10/2020 11:10"));
  }

  /**
   * Test create flight with same source and destination PDX
   * DuplicateAirportCodeException is thrown because the source and destination are the same
   */
  @Test
  void testSameSourceAndDestination() {
    assertThrows(DuplicateAirportCodeException.class, () -> new Flight(1, "PDX", "PDX", "10/10/2020 10:10", "10/10/2020 11:10"));
  }

}
