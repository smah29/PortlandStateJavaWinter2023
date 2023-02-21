package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.smahato.exception.AirportCodeException;
import edu.pdx.cs410J.smahato.utils.AirlineValidationUtils;

import java.time.DateTimeException;
import java.util.Date;

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.*;
import static edu.pdx.cs410J.smahato.constants.DateFormatConstants.PRETTY_DATE_FORMAT;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.DEPARTURE_BEFORE_ARRIVAL;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.SOURCE_AND_DESTINATION_CANNOT_BE_SAME;
import static edu.pdx.cs410J.smahato.utils.DateTimeUtils.getDateFromString;

/**
 * Flight has a number, source, destination, departure time and arrival time
 */
public class Flight extends AbstractFlight implements Comparable<Flight> {
  /**
   * Flight number
   */
  private int number;
  /**
   * Source airport code
   */
  private String source;
  /**
   * Destination airport code
   */
  private String destination;
  /**
   * Departure date and time String
   */
  private String departureString;
  /**
   * Arrival date and time String
   */
  private String arrivalString;
  /**
   * Departure date
   */
  private Date departure;
  /**
   * Arrival date
   */
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
  public Flight(String number, String source, String destination, String departureString, String arrivalString) {
    if (number == null || number.isBlank()) throw new NullPointerException("Flight Number is missing!");
    this.number = Integer.parseInt(number);
    setSource(source);
    setDeparture(departureString);
    setDestination(destination);
    if (this.source.equals(this.destination)) throw new AirportCodeException(SOURCE_AND_DESTINATION_CANNOT_BE_SAME);
    setArrival(arrivalString);
    if (this.departure.compareTo(this.arrival) > -1)
      throw new DateTimeException(DEPARTURE_BEFORE_ARRIVAL);
  }

  /**
   * @return the Flight number
   */
  @Override
  public int getNumber() {
    return this.number;
  }

  /**
   * @return the source airport code
   */
  @Override
  public String getSource() {
    return this.source;
  }

  /**
   * @return the departure time String
   */
  @Override
  public String getDepartureString() {
    return this.departureString;
  }

  /**
   * @return the destination airport code
   */
  @Override
  public String getDestination() {
    return this.destination;
  }

  /**
   * @return the arrival time String
   */
  @Override
  public String getArrivalString() {
    return this.arrivalString;
  }

  /**
   * @return the departure date
   */
  @Override
  public Date getDeparture() {
    return this.departure;
  }

  /**
   * @return the arrival date
   */
  @Override
  public Date getArrival() {
    return this.arrival;
  }

  /**
   * Sort the list of flights by source and then by departure time ascending
   *
   * @param o the flight object to be compared.
   * @return -1, 0 or 1 if less than, equal to, or greater than the specified object.
   */
  @Override
  public int compareTo(Flight o) {
    int k = this.source.compareTo(o.source);
    if (k == 0) return this.departure.compareTo(o.departure);
    else return k;
  }

  private void setDeparture(String departureString) {
    this.departure = getDateFromString(departureString, DEPARTURE);
    this.departureString = PRETTY_DATE_FORMAT.format(this.departure);
  }

  private void setArrival(String arrivalString) {
    this.arrival = getDateFromString(arrivalString, ARRIVAL);
    this.arrivalString = PRETTY_DATE_FORMAT.format(this.arrival);
  }

  private void setSource(String source) {
    AirlineValidationUtils.validateAirportCode(source, SOURCE);
    this.source = source.toUpperCase();
  }

  private void setDestination(String destination) {
    AirlineValidationUtils.validateAirportCode(destination, DESTINATION);
    this.destination = destination.toUpperCase();
  }
}
