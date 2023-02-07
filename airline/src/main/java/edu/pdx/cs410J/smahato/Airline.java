package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AbstractAirline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static edu.pdx.cs410J.smahato.constants.ErrorMessages.CANNOT_BE_NULL_OR_EMPTY;


/**
 * Airline has and name and a list of flights
 */
public class Airline extends AbstractAirline<Flight> {
  /**
   * Name of the airline
   */
  private final String name;
  /**
   * List of flights
   */
  private final List<Flight> flights;

  /**
   * Creates a new airline with the given name and empty list of flights
   *
   * @param name Name of the airline
   */
  public Airline(String name) {
    if (name == null || name.isBlank()) {
      throw new NullPointerException("Airline name" + CANNOT_BE_NULL_OR_EMPTY);
    }
    this.name = name;
    this.flights = new ArrayList<>();
  }

  /**
   * Returns the name of the airline
   *
   * @return Name of the airline
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Adds a flight to the list of flights
   *
   * @param flight Flight to be added
   */
  @Override
  public void addFlight(Flight flight) {
    if (flight == null) {
      throw new NullPointerException("Flight cannot be null");
    }
    flights.add(flight);
  }

  /**
   * Returns the list of flights
   *
   * @return List of flights
   */
  @Override
  public Collection<Flight> getFlights() {
    return this.flights;
  }
}
