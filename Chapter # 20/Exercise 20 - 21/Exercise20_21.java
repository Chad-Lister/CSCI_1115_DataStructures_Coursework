/*
  Author: Chad Lister
  Date: 08/02/2020

  Description: 
  (Use Comparator ) Write the following generic method using selection sort and a comparator.

  public static <E> void selectionSort(E[] list, Comparator<? super E> comparator)
  Exercise20_21.java is a test program that creates an array of 10 and invokes this method using the GeometricObjectComparator introduced in Listing 20.4 to sort the elements. Display the sorted elements. Use the following statement to create the array.

  GeometricObject[] list = {new Circle(5), new Rectangle(4, 5),
   new Circle(5.5), new Rectangle(2.4, 5), new Circle(0.5),
   new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1),
   new Circle(6.5), new Rectangle(4, 5)};

  Download these files to start.
*/

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Exercise20_21 {
  public static void main(String[] args) {
    GeometricObject[] list = { new Circle(5), new Rectangle(4, 5), new Circle(5.5), new Rectangle(2.4, 5),
        new Circle(0.5), new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1), new Circle(6.5),
        new Rectangle(4, 5) };

    // Added
    selectionSort(list, new GeometricObjectComparator());
    for (int i = 0; i < list.length; i++)
      System.out.println(list[i].getArea() + " ");
      
    System.out.println();

    Circle[] list1 = { new Circle(2), new Circle(3), new Circle(2), new Circle(5), new Circle(6), new Circle(1),
        new Circle(2), new Circle(3), new Circle(14), new Circle(12) };
    selectionSort(list1, new GeometricObjectComparator());
    for (int i = 0; i < list1.length; i++)
      System.out.println(list1[i].getArea() + " ");
  }

  public static <E> void selectionSort(E[] list, Comparator<? super E> comparator) {
    
    Arrays.sort(list, comparator);

  }
}