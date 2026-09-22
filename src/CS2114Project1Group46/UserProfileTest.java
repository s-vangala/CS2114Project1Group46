// Project 1
//Virginia Tech Honor Code Pledge:
//
//As a Hokie, I will conduct myself with honor and integrity at all times.
//I will not lie, cheat, or steal, nor will I accept the actions of those who 
//do.
//-- Liam McKee (liamkmckee)
//LLM Statement:
//I have not used any assistance for the assignment beyond course resources and
//staff.
package CS2114Project1Group46;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

//-------------------------------------------------------------------------
/**
* The UserProfileTest class contains unit tests for the UserProfile
* class.
* 
* @author Liam McKee (liamkmckee)
* @version 2026.09.22
*/
public class UserProfileTest {
 // The UserProfile instance under test
 private UserProfile profile;

 // ----------------------------------------------------------
 /**
  * Creates a fresh UserProfile with a known starting salary before
  * each test.
  */
 @Before
 public void setUp() {
     profile = new UserProfile();
     profile.setSalary(3000.00);
 }


 // ----------------------------------------------------------
 /**
  * A valid salary should be stored and retrievable.
  */
 @Test
 public void setSalary_normal_updatesSalary() {
     profile.setSalary(4000.00);
     assertEquals(4000.00, profile.getSalary(), 0.001);
 }


 // ----------------------------------------------------------
 /**
  * A negative salary is invalid input and should be rejected,
  * leaving the previous salary in place.
  */
 @Test
 public void setSalary_negativeAmount_isRejected() {
     double before = profile.getSalary();
     profile.setSalary(-500.00);
     assertEquals(before, profile.getSalary(), 0.001);
 }


 // ----------------------------------------------------------
 /**
  * A negative balance is a legitimate deficit, not an error, and
  * should be stored as is.
  */
 @Test
 public void updateSurplusDeficit_normal_setsDeficitValue() {
     profile.updateSurplusDeficit(-250.00);
     assertEquals(-250.00, profile.getSurplusDeficit(), 0.001);
 }


 // ----------------------------------------------------------
 /**
  * NaN represents a corrupted upstream calculation and should be
  * rejected, leaving the previous valid value in place.
  */
 @Test
 public void updateSurplusDeficit_NaN_isRejected() {
     profile.updateSurplusDeficit(100.00);
     double before = profile.getSurplusDeficit();
     profile.updateSurplusDeficit(Double.NaN);
     assertEquals(before, profile.getSurplusDeficit(), 0.001);
 }
}