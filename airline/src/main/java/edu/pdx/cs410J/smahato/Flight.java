package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.smahato.utils.AirlineValidationUtils;
import edu.pdx.cs410J.smahato.utils.DateTimeUtils;

import java.util.Date;

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.*;

/**
 * Flight has a number, source, destination, departure time and arrival time
 */
public class Flight extends AbstractFlight implements Comparable<Flight> {

  private final int number;
  private final String source;
  private final String destination;
  private final String departureString;
  private final String arrivalString;
  private Date departure;
  private Date arrival;

  /**
   * Creates a new flight with the given number, source, destination, departure time and arrival time
   *
   * @param number          Flight number
   * @param source          Source airport code
   * @param destination     Destination airport code
   * @param departureString Departure time
   * @param arrivalString   Arrival time
   */
  public Flight(int number, String source, String destination, String departureString, String arrivalString) {
    this.number = number;

    String[][] airportCodes = {{source, SOURCE}, {destination, DESTINATION}};
    AirlineValidationUtils.validateAirportCodes(airportCodes);
    this.source = source;
    this.destination = destination;

    String[][] flightSchedules = {{departureString, DEPARTURE}, {arrivalString, ARRIVAL}};
    DateTimeUtils.validateFlightSchedule(flightSchedules);
    this.departureString = departureString;
    this.arrivalString = arrivalString;
  }

  @Override
  public int getNumber() {
    return this.number;
  }

  @Override
  public String getSource() {
    return this.source;
  }

  @Override
  public String getDepartureString() {
    return this.departureString;
  }

  @Override
  public String getDestination() {
    return this.destination;
  }

  @Override
  public String getArrivalString() {
    return this.arrivalString;
  }

  @Override
  public Date getDeparture() {
    return this.departure;
  }

  @Override
  public Date getArrival() {
    return this.arrival;
  }

  /**
   * Sort the list of flights by source and then by departure time
   *
   * @param o the flight object to be compared.
   * @return -1, 0 or 1 if less than, equal to, or greater than the specified object.
   */
  @Override
  public int compareTo(Flight o) {
    int k = this.source.compareTo(o.source);
    if (k == 0)
      return this.departure.compareTo(o.departure);
    else
      return k;
  }
}
