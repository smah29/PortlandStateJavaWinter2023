package edu.pdx.cs410J.smahato.constants;

/**
 * This enum contains all the parameters that are used in the airline servlet.
 */
public enum AirlineParams {
  /**
   * The name of the airline
   */
  AIRLINE_NAME("airline"),
  /**
   * The flight number
   */
  FLIGHT_NUMBER("flightNumber"),
  /**
   * The source of the flight
   */
  SOURCE("src"),
  /**
   * The departure time of the flight
   */
  DEPARTURE("depart"),
  /**
   * The destination of the flight
   */
  DESTINATION("dest"),
  /**
   * The arrival time of the flight
   */
  ARRIVAL("arrive");

  private final String parameterName;

  /**
   * Constructor for the enum
   *
   * @param requestParameter The name of the parameter
   */
  AirlineParams(String requestParameter) {
    this.parameterName = requestParameter;
  }

  /**
   * Returns the name of the parameter
   *
   * @return The name of the parameter
   */
  public String getParameterName() {
    return parameterName;
  }
}
