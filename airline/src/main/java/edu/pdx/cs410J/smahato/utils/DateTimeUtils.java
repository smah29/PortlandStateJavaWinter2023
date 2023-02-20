package edu.pdx.cs410J.smahato.utils;

import java.text.ParseException;
import java.time.DateTimeException;
import java.util.Date;

import static edu.pdx.cs410J.smahato.constants.DateFormatConstants.FLIGHT_SCHEDULE_FORMAT;
import static edu.pdx.cs410J.smahato.constants.DateFormatConstants.MM_DD_YYYY_HH_MM;
import static edu.pdx.cs410J.smahato.constants.ErrorMessages.CANNOT_BE_NULL_OR_EMPTY;

/**
 * This class contains utility methods for validating flight schedules
 */
public class DateTimeUtils {

  /**
   * Validate the flight schedule
   * <p>flight schedule must be of format mm/dd/yyyy hh:mm</p>
   *
   * @param dateTime           <p>flight schedule given as input by the user</p>
   * @param flightScheduleType <p>flight schedule type such as departure or arrival</p>
   */
  public static void dateTimeFormatCheck(String dateTime, String flightScheduleType) {
    if (!dateTime.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}")) {
      throw new DateTimeException("Invalid " + flightScheduleType + " date format - " + dateTime + "! Please follow the format: " + MM_DD_YYYY_HH_MM);
    }
    String[] dateTimeSplit = dateTime.split(" ");
    String date = dateTimeSplit[0];
    String time = dateTimeSplit[1];
    String[] dateSplit = date.split("/");
    String month = dateSplit[0];
    String day = dateSplit[1];
    String[] timeSplit = time.split(":");
    String hour = timeSplit[0];
    String minute = timeSplit[1];
    int monthInt = Integer.parseInt(month);
    int dayInt = Integer.parseInt(day);
    int hourInt = Integer.parseInt(hour);
    int minuteInt = Integer.parseInt(minute);
    if (monthInt < 1 || monthInt > 12) {
      throw new DateTimeException(flightScheduleType + " month should be between 1 and 12");
    }
    if (dayInt < 1 || dayInt > 31) {
      throw new DateTimeException(flightScheduleType + " day should be between 1 and 31");
    }
    if (monthInt == 2) {
      if (dayInt > 29) {
        throw new DateTimeException(flightScheduleType + " day should be between 1 and 29 for February");
      }
      if (dayInt == 29) {
        if (!isLeapYear(Integer.parseInt(dateSplit[2]))) {
          throw new DateTimeException(flightScheduleType + " day should be between 1 and 28 for February");
        }
      }
    }
    if (hourInt > 23) {
      throw new DateTimeException(flightScheduleType + " hour should be between 0 and 23");
    }
    if (minuteInt > 59) {
      throw new DateTimeException(flightScheduleType + " minute should be between 0 and 59");
    }
  }

  /**
   * Check if the year is a leap year
   *
   * @param year <p>year to check</p>
   * @return <p>true if the year is a leap year, false otherwise</p>
   */
  private static boolean isLeapYear(int year) {
    if (year % 4 == 0) {
      if (year % 100 == 0) {
        if (year % 400 == 0) {
          return true;
        }
        return false;
      }
      return true;
    }
    return false;
  }

  /**
   * This method checks if the date and time is null or empty
   *
   * @param flightDateTime     date and time
   * @param flightScheduleType type of date and time ex departure or arrival
   */
  public static void dateTimeNullCheck(String flightDateTime, String flightScheduleType) {
    if (flightDateTime == null || flightDateTime.isBlank())
      throw new NullPointerException(flightScheduleType + " Date and time" + CANNOT_BE_NULL_OR_EMPTY);
  }

  /**
   * This method converts the date and time string to a Date object
   *
   * @param dateTime           date and time string
   * @param flightScheduleType type of date and time ex departure or arrival
   * @return Date object
   */
  public static Date getDateFromString(String dateTime, String flightScheduleType) {
    try {
      dateTimeNullCheck(dateTime, flightScheduleType);
      Date date = FLIGHT_SCHEDULE_FORMAT.parse(dateTime);
      dateTimeFormatCheck(dateTime, flightScheduleType);
      return date;
    } catch (ParseException e) {
      throw new DateTimeException("Invalid " + flightScheduleType + " date format - " + dateTime + "! Please follow the format: " + MM_DD_YYYY_HH_MM);
    }
  }
}
