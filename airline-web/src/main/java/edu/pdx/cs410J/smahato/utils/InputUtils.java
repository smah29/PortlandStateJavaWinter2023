package edu.pdx.cs410J.smahato.utils;

/**
 * Interface InputUtils contains abstract methods to get values (airline and flight information) from arguments
 * and check if the number of arguments are correct
 * <p>
 * This interface is implemented by {@link SaveFlightUtils}
 */
public interface InputUtils {
  /**
   * Returns the airline name from the input list
   *
   * @return Airline object
   */
  String getAirlineName();

  /**
   * Returns the source from the input list
   *
   * @return Source
   */
  String getSource();

  /**
   * Returns the destination from the input list
   *
   * @return Destination
   */
  String getDestination();

  /**
   * Returns the flight number from the input list
   *
   * @return Flight number
   */
  String getFlightNumber();

  /**
   * Returns the departure time from the input list
   *
   * @return Departure time
   */
  String getDepartureString();

  /**
   * Returns the arrival time from the input list
   *
   * @return Arrival time
   */
  String getArrivalString();
}
