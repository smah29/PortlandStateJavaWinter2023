package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AbstractAirline;

import java.util.Collection;

public class Airline extends AbstractAirline<Flight> {
  private final String name;

  public Airline(String name) {
    if (name == null || name.isBlank()) {
      throw new NullPointerException("Airline name cannot be null or blank");
    }
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void addFlight(Flight flight) {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public Collection<Flight> getFlights() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
