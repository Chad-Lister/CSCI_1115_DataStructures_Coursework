/*
Author: Chad Lister
Date: 07/27/2020

Description: (Maximum element in an array) Implement the following method that returns the maximum element in an array. Use Exercise19_05.java as the test class.

public static <E extends Comparable<E>> E max(E[] list) 

 */

public class Exercise19_05 {
  public static void main(String[] args) {
    Integer[] numbers = {1, 2, 3};
    System.out.println(max(numbers));
    
    String[] words = {"red", "green", "blue"};
    System.out.println(max(words));
    
    Circle[] circles = {new Circle(3), new Circle(2.9), new Circle(5.9)};
    System.out.println(max(circles));
  }
  
  public static <E extends Comparable<E>> E max(E[] list) {
    
    //  Put first element type in max.
    E maximum = list[0];
    
    //  For the other elements in array.
    for (int e = 1; e < list.length; e++) {
      
      //  If greater than maximum.  It is the new maximum.
      if (list[e].compareTo(maximum) > 0) {
        maximum = list[e];
      }
    }
    return maximum;
  }
  
  static class Circle implements Comparable<Circle> {
    double radius;
    
    public Circle(double radius) {
      this.radius = radius;
    }
    
    @Override
    public int compareTo(Circle c) {
      if (radius < c.radius) 
        return -1;
      else if (radius == c.radius)
        return 0;
      else
        return 1;
    }
    
    @Override
    public String toString() {
      return "Circle radius: " + radius;
    }
  }
}