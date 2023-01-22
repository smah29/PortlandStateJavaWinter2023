package edu.pdx.cs410J.smahato;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest {

  @Test
  void studentNamedPatIsNamedPat() {
    String name = "Pat";
    var pat = new Student(name, new ArrayList<>(), 0.0, "male");
    assertThat(pat.getName(), equalTo(name));
  }

  @Test
  void testCreateStudentWithInvalidGender() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Student("Pat", new ArrayList<>(), 0.0, "invalid");
    });
  }

  @Test
  void testCreateStudentWithNullGender() {
    assertThrows(NullPointerException.class, () -> {
      new Student("Pat", new ArrayList<>(), 0.0, null);
    });
  }

  @Test
  void allStudentSaysThisClassIsTooMuchWork() {
    Student student = new Student("Name", new ArrayList<>(), 0.0, "female");
    assertThat(student.says(), equalTo("This class is too much work"));
  }

  @Test
  void setStudentNameAsNull() {
    assertThrows(NullPointerException.class, () -> new Student(null, new ArrayList<>(), 0.0, "female"));
  }

  @Test
  void testCreateStudentWithNullClass() {
    assertThrows(NullPointerException.class, () ->
        new Student("Pat", null, 3.4, "other"));
  }

  @Test
  void testCreateStudentWithNegativeGPA() {
    assertThrows(IllegalArgumentException.class, () ->
        new Student("Pat", new ArrayList<>(), -1.0, "other"));
  }
}
