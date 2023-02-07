package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.smahato.exception.AirportCodeException;
import edu.pdx.cs410J.smahato.utils.AirlineValidationUtils;
import edu.pdx.cs410J.smahato.utils.DateTimeUtils;

import java.text.ParseException;
import java.time.DateTimeException;
import java.util.Date;

import static edu.pdx.cs410J.smahato.constants.AirlineConstants.*;
import static edu.pdx.cs410J.smahato.constants.DateFormatConstants.*;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.*;

/**
 * Flight has a number, source, destination, departure time and arrival time
 */
public class Flight extends AbstractFlight implements Comparable<Flight> {

  private final int number;
  private String source;
  private String destination;
  private String departureString;
  private String arrivalString;
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
    setSource(source);
    setDestination(destination);
    if (this.source.equals(this.destination)) throw new AirportCodeException(SOURCE_AND_DESTINATION_CANNOT_BE_SAME);
    setDeparture(departureString);
    setArrival(arrivalString);
    if (this.departure.compareTo(this.arrival) > -1)
      throw new DateTimeException(DEPARTURE_BEFORE_ARRIVAL);
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
    this.departureString = SHORT_DF.format(this.departure);
  }

  private void setArrival(String arrivalString) {
    this.arrival = getDateFromString(arrivalString, ARRIVAL);
    this.arrivalString = SHORT_DF.format(this.arrival);
  }

  private static Date getDateFromString(String dateTime, String flightScheduleType) {
    try {
      DateTimeUtils.dateTimeNullCheck(dateTime, flightScheduleType);
      Date date = FLIGHT_SCHEDULE_FORMAT.parse(dateTime);
      DateTimeUtils.dateTimeFormatCheck(dateTime, flightScheduleType);
      return date;
    } catch (ParseException e) {
      throw new DateTimeException("Invalid " + flightScheduleType + " date format! Please follow the format: " + MM_DD_YYYY_HH_MM_A);
    }
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
