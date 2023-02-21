package edu.pdx.cs410J.smahato.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * defines constants related to Date Formats
 */
public interface DateFormatConstants {
  /**
   * 12-hour time format
   */
  String MM_DD_YYYY_hh_MM_a = "MM/dd/yyyy hh:mm a";
  /**
   * flight schedule format for regex MM/dd/yyyy hh:mm a
   */
  SimpleDateFormat TWELVE_HOUR_TIME_FORMAT = new SimpleDateFormat(MM_DD_YYYY_hh_MM_a);

  /**
   * short date format for writing to file ex MM/dd/yyyy, hh:mm a
   */
  DateFormat PRETTY_DATE_FORMAT = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US);
}
