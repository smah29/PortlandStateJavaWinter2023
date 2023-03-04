package edu.pdx.cs410J.smahato;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages {

  public static String MISSING_SRC_DEST = "Source and Destination both have to be provided!";

  public static String missingRequiredParameter(String parameterName) {
    return String.format("The required parameter \"%s\" is missing", parameterName);
  }

  public static String airlineDoesNotExist(String airlineName) {
    return String.format("Airline with name \"%s\" doesn't exists", airlineName);
  }

  public static String flightDoesNotExist(String source, String destination, String airlineName) {
    return String.format("No flights found between \"%s\" and \"%s\" for airline \"%s\"", source, destination, airlineName);
  }

  public static String definedWordAs(String word, String definition) {
    return String.format("Defined %s as %s", word, definition);
  }

  public static String allDictionaryEntriesDeleted() {
    return "All dictionary entries have been deleted";
  }

}
