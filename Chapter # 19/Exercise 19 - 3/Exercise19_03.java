/*
Author: Chad Lister
Date: 07/27/2020

Description: This program writes the following method that returns a new ArrayList. The new list contains the non-duplicate elements from the original list using Exercise_19_03 as the test class.  With header/signature:

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) 
*/

import java.util.ArrayList;

public class Exercise19_03 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(14);
    list.add(24);
    list.add(14);
    list.add(42);
    list.add(25);
    
    ArrayList<Integer> newList = removeDuplicates(list);
    
    System.out.println(newList);
  }
  
  public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
    
    //  For every number.
    for (int e = 0; e < list.size() - 1; e++) {
      
      //  Compare against next.
      for (int n = e + 1; n < list.size() - 2; n++) {
        
        //  If they are the same, then remove duplicate.
        if (list.get(e) == list.get(n)) {
          list.remove(n);
        }
      }
    }
    return list;  
  }
}