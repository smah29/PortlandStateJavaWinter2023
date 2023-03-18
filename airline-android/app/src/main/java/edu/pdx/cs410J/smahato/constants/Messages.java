package edu.pdx.cs410J.smahato.constants;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages {
    /**
     * Message for when the source or destination are missing
     */
    public static String MISSING_SRC_DEST = "Source and Destination both have to be provided!";

    /**
     * Message for when the source and destination are the same
     *
     * @param parameterName The name of the parameter
     * @return The formatted message
     */
    public static String missingRequiredParameter(String parameterName) {
        return String.format("The required parameter \"%s\" is missing", parameterName);
    }

    /**
     * Message for when the airline doesn't exist
     *
     * @param airlineName The name of the airline
     * @return The formatted message
     */
    public static String airlineDoesNotExist(String airlineName) {
        return String.format("Airline with name \"%s\" doesn't exists", airlineName);
    }

    /**
     * Message for when the flights don't exist
     *
     * @param source      The source airport
     * @param destination The destination airport
     * @param airlineName The airline name for the flight
     * @return The formatted message
     */
    public static String flightDoesNotExist(String source, String destination, String airlineName) {
        return String.format("No flights found between \"%s\" and \"%s\" for airline \"%s\"", source, destination, airlineName);
    }

    /**
     * airline entry deleted message
     *
     * @param airlineName airline name
     * @return formatted message
     */
    public static String airlineEntryDeleted(String airlineName) {
        return String.format("%s entry have been deleted", airlineName);
    }

    public static String invalidHostOrPort(String host, String port) {
        return String.format("Error : Invalid host %s or port %s", host, port);
    }

    /**
     * issue in parsing the XML response from the server
     */
    public static String XML_PARSING_ERROR = "Issue in parsing the XML response from the server";

    public static String invalidDateFormat(String dateTimeFormat) {
        return String.format("Invalid date format! Desired format is %s", dateTimeFormat);
    }

}
