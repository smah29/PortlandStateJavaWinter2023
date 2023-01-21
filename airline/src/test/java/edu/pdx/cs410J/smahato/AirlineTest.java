package edu.pdx.cs410J.smahato;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.hamcrest.CoreMatchers.*;
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

}
