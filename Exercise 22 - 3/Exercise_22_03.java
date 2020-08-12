/*
  Author:  Chad Lister
  Date: 08/09/2020
  
(Pattern matching ) Write a program that prompts the user to enter two strings and tests whether the second string is a substring of the first string. Suppose the neighboring characters in the string are distinct.  (Don’t use the indexOf method in the String class.) Analyze the time complexity of your algorithm. Your algorithm needs to be at least O(n) time.

  Complexity is linear search O(n) where n is the length second string.
*/

import java.util.Scanner;

public class Exercise_22_03 {
  public static void main(String[] args) {
            
    Scanner input = new Scanner(System.in);
 
    //  Variable initialization.
    String string1 = "";
    char character = 0;
    String string2 = "";
    char character2 = 0;
    
    //  Prompt for strings.
    System.out.print("Enter a string # s1:  ");
    string1 = input.nextLine();
    System.out.print("Enter a string # s2:  ");
    string2 = input.nextLine();
    
    //  Initialize index counter to 0.
    int index = 0;
   
    //  If the first string doesn't contain the second string display message.
    if (string1.contains(string2) == true) {
      
      //  Load the first character in second string.
      character2 = string2.charAt(0);
      
      //  For the length of the second string.  O(n) where n is the length of the second string.
      for (int i = 0; i < string2.length() - 1; i++) {
        
        //  Load the first or next character in first string.
        character = string1.charAt(i);

        index++;
        //  If the character in String 2 matches the character in string 1.
        if (Character.compare(character2, character) == 0) {
          
          //  Exit
          break;
        }
      }
    }
    else {
      
      //  Not in message.
      System.out.println("Second string is not in the first string.");
    }
    
    //  Display to screen.
    System.out.println("\nMatched at index # " + index + " with a time complexity of O(n).");

  }
}