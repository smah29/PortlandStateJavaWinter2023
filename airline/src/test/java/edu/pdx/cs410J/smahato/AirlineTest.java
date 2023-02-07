package edu.pdx.cs410J.smahato;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    Flight flight = new Flight(1, "PDX", "LAX", "10/10/2020 10:10 am", "10/10/2020 11:10 am");
    airline.addFlight(flight);
    assertThat(airline.getFlights().size(), equalTo(1));
  }

  // Test5 : create an airline with a valid name as CS410J Air Express and two flights
  @Test
  void testAddTwoFlights() {
    Airline airline = new Airline("CS410J Air Express");
    Flight flight1 = new Flight(1, "PDX", "LAX", "10/10/2020 10:10 am", "10/10/2020 11:10 am");
    Flight flight2 = new Flight(2, "LAX", "PDX", "10/10/2020 12:10 am", "10/10/2020 11:10 am");
    Flight flight3 = new Flight(3, "LAX", "ABE", "10/10/2020 12:20 am", "10/10/2020 11:10 am");
    airline.addFlight(flight1);
    airline.addFlight(flight2);
    airline.addFlight(flight3);
    assertThat(airline.getFlights().size(), equalTo(3));
    List<Flight> list = new ArrayList(airline.getFlights());
    assertThat(list.get(0).getSource(), equalTo("PDX"));
    Collections.sort(list);
    assertThat(list.get(0).getSource(), equalTo("LAX"));
    assertThat(list.get(0).getDestination(), equalTo("PDX"));
    assertThat(list.get(1).getSource(), equalTo("LAX"));
  }

  // Test6 : create an airline with a valid name as CS410J Air Express and add a flight with null flight
  // NullPointerException is thrown because flight cannot be null
  @Test
  void testAddNullFlight() {
    Airline airline = new Airline("CS410J Air Express");
    assertThrows(NullPointerException.class, () -> airline.addFlight(null));
  }

}
