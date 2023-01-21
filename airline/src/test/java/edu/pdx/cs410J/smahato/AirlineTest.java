package edu.pdx.cs410J.smahato;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AirlineTest {

  // Test1 : create an airline with null name
  // NullPointerException is thrown because name cannot be null
  @Test
  void testNullName() {
    assertThrows(NullPointerException.class, () -> new Airline(null));
  }

  // Test2 : create an airline with empty name
  // NullPointerException is thrown because name cannot be blank
  @Test
  void testEmptyName() {
    assertThrows(NullPointerException.class, () -> new Airline(""));
  }

  // Test3 : create an airline with a valid name as CS410J Air Express
  @Test
  void testValidName() {
    Airline airline = new Airline("CS410J Air Express");
    assertThat(airline.getName(), equalTo("CS410J Air Express"));
  }

  // Test4 : create an airline with a valid name as CS410J Air Express and add a flight
  @Test
  void testAddFlight() {
    Airline airline = new Airline("CS410J Air Express");
    Flight flight = new Flight(1, "PDX", "LAX", "10/10/2020 10:10", "10/10/2020 11:10");
    airline.addFlight(flight);
    assertThat(airline.getFlights().size(), equalTo(1));
  }

  // Test5 : create an airline with a valid name as CS410J Air Express and two flights
  @Test
  void testAddTwoFlights() {
    Airline airline = new Airline("CS410J Air Express");
    Flight flight1 = new Flight(1, "PDX", "LAX", "10/10/2020 10:10", "10/10/2020 11:10");
    Flight flight2 = new Flight(2, "LAX", "PDX", "10/10/2020 12:10", "10/10/2020 13:10");
    airline.addFlight(flight1);
    airline.addFlight(flight2);
    assertThat(airline.getFlights().size(), equalTo(2));
  }

  // Test6 : create an airline with a valid name as CS410J Air Express and add a flight with null flight
  // NullPointerException is thrown because flight cannot be null
  @Test
  void testAddNullFlight() {
    Airline airline = new Airline("CS410J Air Express");
    assertThrows(NullPointerException.class, () -> airline.addFlight(null));
  }

}
