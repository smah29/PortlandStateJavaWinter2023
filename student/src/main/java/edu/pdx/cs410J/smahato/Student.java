package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
import java.util.Arrays;

/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {
  private final ArrayList<String> classes;
  private final double gpa;
  private final String gender;
                                                                                    
  /**                                                                               
   * Creates a new <code>Student</code>                                             
   *                                                                                
   * @param name                                                                    
   *        The student's name                                                      
   * @param classes                                                                 
   *        The names of the classes the student is taking.  A student              
   *        may take zero or more classes.                                          
   * @param gpa                                                                     
   *        The student's grade point average                                       
   * @param gender                                                                  
   *        The student's gender ("male", "female", or "other", case insensitive)
   */
  public Student(String name, ArrayList<String> classes, double gpa, String gender) {
    super(name);
    if (name == null) {
      throw new NullPointerException("Name cannot be null");
    }
    if (classes == null) {
      throw new NullPointerException("Classes cannot be null");
    }
    if (gpa < 0) {
      throw new IllegalArgumentException("GPA cannot be negative");
    }
    if (gender == null) {
      throw new NullPointerException("gender cannot be null");
    }
    if (Arrays.stream(Gender.values()).noneMatch(s -> s.name().equalsIgnoreCase(gender))) {
      throw new IllegalArgumentException("Gender value is not valid");
    }
    this.classes = classes;
    this.gpa = gpa;
    this.gender = Gender.valueOf(gender.toLowerCase()).name();
  }

  /**                                                                               
   * All students say "This class is too much work"
   */
  @Override
  public String says() {
    return "This class is too much work";
  }
                                                                                    
  /**                                                                               
   * Returns a <code>String</code> that describes this                              
   * <code>Student</code>.                                                          
   */                                                                               
  public String toString() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    System.err.println("Missing command line arguments");
  }
}