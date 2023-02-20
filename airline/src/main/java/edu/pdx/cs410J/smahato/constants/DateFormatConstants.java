package edu.pdx.cs410J.smahato.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * defines constants related to Date Formats
 */
public interface DateFormatConstants {
  /**
   * expected date time pattern/regex from user
   */
  String MM_DD_YYYY_HH_MM = "MM/dd/yyyy HH:mm";

  /**
   * flight schedule format for regex MM/dd/yyyy hh:mm
   */
  SimpleDateFormat FLIGHT_SCHEDULE_FORMAT = new SimpleDateFormat(MM_DD_YYYY_HH_MM);

  /**
   * short date format for writing to file ex MM/dd/yyyy, hh:mm a
   */
  DateFormat SHORT_DF = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US);
}
