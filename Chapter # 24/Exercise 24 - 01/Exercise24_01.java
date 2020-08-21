/*
  Author:  Provided.
  Date:  08/20/20
  
  Used in Exercise 24-01.
  Exercise24_01.java is a test program that creates two MyArrayLists, list1 and list2, with the initial values {"Tom", "George", "Peter", "Jean", "Jane"} and {"Tom", "George", "Michael", "Michelle", "Daniel"}, then perform the following operations:

  Invokes list1.addAll(list2), and displays list1 and list2.
  Recreates list1 and list2 with the same initial values, invokes list1.removeAll(list2), and displays list1 and list2.
  Recreates list1 and list2 with the same initial values, invokes list1.retainAll(list2), and displays list1 and list2.
*/

import java.util.*;

public class Exercise24_01 {
  public static void main(String[] args) {
    new Exercise24_01();
  }

  public Exercise24_01() {
    String[] name1 = {"Tom", "George", "Peter", "Jean", "Jane"};
    String[] name2 = {"Tom", "George", "Michael", "Michelle", "Daniel"};
    
    MyList<String> list1 = new MyArrayList<String>(name1);   
    MyList<String> list2 = new MyArrayList<String>(name2);   
    System.out.println("list1:" + list1);
    System.out.println("list2:" + list2);
    list1.addAll(list2);
    System.out.println("After addAll:" + list1 + "\n");
    
    list1 = new MyArrayList<String>(name1);
    list2 = new MyArrayList<String>(name2);   
    System.out.println("list1:" + list1);
    System.out.println("list2:" + list2);
    list1.removeAll(list2);
    System.out.println("After removeAll:" + list1 + "\n");
    
    list1 = new MyArrayList<String>(name1);
    list2 = new MyArrayList<String>(name2);   
    System.out.println("list1:" + list1);
    System.out.println("list2:" + list2);
    list1.retainAll(list2);
    System.out.println("After retainAll:" + list1 + "\n");
  }
}