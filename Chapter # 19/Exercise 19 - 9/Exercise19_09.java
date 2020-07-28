/*
Author: Chad Lister
Date: 07/28/2020

Description: (Sort ArrayList) Write the following method that sorts an ArrayList. Use Exercise19_03.java as the test class.

  public static <E extends Comparable<E>> void sort(ArrayList<E> list) 

*/
import java.util.*;
import java.util.ArrayList;

public class Exercise19_09 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(14);
    list.add(24);
    list.add(4);
    list.add(42);
    list.add(5);
    Exercise19_09.<Integer>sort(list);
    
    System.out.print(list);
  }
  
  public static <E extends Comparable<E>> void sort(ArrayList<E> list) {


    Collections.sort(list);
  }
}
