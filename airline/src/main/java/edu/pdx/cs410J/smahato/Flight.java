package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AbstractFlight;

import static edu.pdx.cs410J.smahato.AirlineConstants.*;

public class Flight extends AbstractFlight {

  private final int number;
  private final String source;
  private final String destination;
  private final String departureString;
  private final String arrivalString;

  // creating this constructor will not compile initially as the zero argument constructor is called at many places
  // so, we need to create a zero argument constructor as well
  public Flight(int number, String source, String destination, String departureString, String arrivalString) {
    this.number = number;

    String[][] airportCodes = {{source, SOURCE}, {destination, DESTINATION}};
    AirlineUtils.validateAirportCodes(airportCodes);
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
}
