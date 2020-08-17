/*
Author: Chad Lister
Date: 08/15/2020

Description: (Generic quick sort ) Write the following two generic methods using quick sort. The first method sorts the elements using the Comparable interface and the second uses the Comparator interface.

Download these files to start.

public static <E extends Comparable<E>> void quickSort(E[] list)

public static <E> void quickSort(E[] list, Comparator<? super E> comparator)
*/

import java.util.Comparator;

public class Exercise23_03 {
  public static void main(String[] args) {
    Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
    quickSort(list);
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }

    System.out.println();
    Circle[] list1 = {new Circle(2), new Circle(3), new Circle(2),
                     new Circle(5), new Circle(6), new Circle(1), new Circle(2),
                     new Circle(3), new Circle(14), new Circle(12)};
    quickSort(list1, new GeometricObjectComparator());
    for (int i = 0; i < list1.length; i++) {
      System.out.println(list1[i] + " ");
    }
  }
  
  public static <E extends Comparable<E>> void quickSort(E[] list) {
    
    //  Initialize beginning and end.
    int a = 0;
    int b = list.length - 1;
    
    //  Sort with list, beginning and end.
    qsort(list, a, b);
  }
  
  public static <E extends Comparable<E>> void qsort(E[] list, int a, int b) {
    
    //  If beginning is less than end.
    if (a < b) {
      int i = a;
      int j = b;
      
      //  Pivot.
      E x = list[(i + j) / 2];

      do {
        
        //  Compare against pivot.
        while (list[i].compareTo(x) < 0) i++;
        while (x.compareTo(list[j]) < 0) j--;

        //  If left less than right side swap.
        if ( i <= j) {
          E tmp = list[i];
          list[i] = list[j];
          list[j] = tmp;
          i++;
          j--;
        }

      } while (i <= j);

      //  Recursively call.
      qsort(list, a, j);
      qsort(list, i, b);
    }
  }
  
  public static <E> void quickSort(E[] list, Comparator<? super E> comparator) {
    
    // Initialize beginning and end.
    int a = 0;
    int b = list.length - 1;
    
    //  Pass list, beginning and end. 
    qsort(list, comparator, a, b);
  }
  
  public static <E> void qsort(E[] list, Comparator<? super E> cmp, int a, int b) {
    
    //  If beginning is less than end.
    if (a < b) {
      int i = a;
      int j = b;
      
      //  Pivot object.
      E x = list[(i + j) / 2];

      do {
        
        //  Compare area against pivot.
        while (cmp.compare(list[i], x) < 0) i++;
        while (cmp.compare(x, list[j]) < 0) j--;

        //  If left less than right side, swap.
        if ( i <= j) {
          E tmp = list[i];
          list[i] = list[j];
          list[j] = tmp;
          i++;
          j--;
        }

      } while (i <= j);

      //  Recursively call.
      qsort(list, cmp, a, j);
      qsort(list, cmp, i, b);
    }
  }
}