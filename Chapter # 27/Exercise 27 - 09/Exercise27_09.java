/* 
  Author:  Chad Lister
  Date:  08/26/2020

  (Implement hashCode for string) Write a method that returns a hashcode for string using the approach described in Section 27.3.2 with b value 31. Here is the test class with the following function header.

  public static int hashCodeForString(String s)

  Given: "4.5"      Expected: 51451
  Given: "Hello"    Expected: 69609650
*/

public class Exercise27_09 { 
  public static void main(String[] args) {
    String s = "4.5";
    System.out.println(hashCodeForString(s));
    
    s = "Hello";
    System.out.println(hashCodeForString(s));
  }
  
  public static int hashCodeForString(String s) {
    // Add your code here
    
    int hashCode = 0;
    for (int c = 0; c < s.length(); c++) {

      hashCode = hashCode * 31 + s.charAt(c);

    }
    
    return hashCode;
  }
}