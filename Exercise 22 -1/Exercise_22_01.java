/*
  Author:  Chad Lister
  Date: 08/09/2020
  
  (Maximum consecutive increasingly ordered substring ) Write a program that prompts the user to enter a string and displays the maximum consecutive increasingly ordered substring. Analyze the time complexity of your program.

  Complexity is Linear Search or time complexity of O(n) where n is string length.
*/

import java.util.Scanner;

public class Exercise_22_01 {
  public static void main(String[] args) {
            
    Scanner input = new Scanner(System.in);
    
    //  Variables known.
    String userString = "";
    char character = 0;
    char prevCharacter = 0;
    int index = 0;
    String addChar ="";
    String temp = "";
  
    //  Prompt user for string.  Get end and substrig from input.
    System.out.print("Enter a string:  ");
    userString = input.nextLine();
    int end = userString.length() - 1;
    String subString = userString.charAt(0) + "";
    
    //  While index is less than end index.
    while (index < end) {
      
      //  Initialize characters for comparision.
      prevCharacter = userString.charAt(index);
      character = userString.charAt(index + 1);
      
      //  If character is the same as previous character or lower alphabetically.  Add it to substring.
      if (Character.compare(prevCharacter, character) <= 0) {
        
        subString = subString + character;
        index++;
        
        //  If this substring is longer than temp, it's the longest.
        if (subString.length() > temp.length()) {
          temp = subString;
        }

      //  If the character is alphabetically before the current one, start a new substring.
      }
      else {
        subString = character + "";
        index++;
      }
    }
    
    //  Display to screen.
    System.out.println();
    System.out.println("Maximum consecutive substring is:  " + temp);
    System.out.println("Complexity is Linear Search or time complexity of O(n), where n is the length of the string entered.");
  }
}