/*
	Author:  Chad Lister
	Date: 08/08/2020
	
	(Revise Listing 21.9) Rewrite Listing 21.9 with the map variable as a HashMap. The program should display the words in ascending order of occurrence counts.

	Download this file to start.

	(Hint: Create a class named WordOccurrence that implements the Comparable interface. The class contains two fields, word and count. The compareTo method compares the counts. For each pair in the hash set in Listing 21.9, create an instance of WordOccurrence and store it in an array list. Sort the array list using the Collections.sort method. What would be wrong if you stored the instances of WordOccurrence in a tree set?)  It would be sorted by key.
*/

import java.util.*;

public class Exercise_21_07 {
	
	private String word;
	private int count;
		
	public static void main(String[] args) {
		
		// Set text in a string
		String text = "Good morning. Have a good class. " +
			"Have a good visit. Have fun!";

		// Create a TreeMap to hold words as key and count as value
		Map<String, Integer> map = new HashMap<>();
		
		ArrayList<WordOccurance> wordList = new ArrayList<>();
//		String key = "";
//		int value = 0;
		
		String[] words = text.split("[\\s+\\p{P}]");
		for (int i = 0; i < words.length; i++) {
			String key = words[i].toLowerCase();
//			key = words[i].toLowerCase();
						
			if (key.length() > 0) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
				}
				else {
					int value = map.get(key);
//					value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}
		
		Set<String> keySet = map.keySet();
		ArrayList<String> keyList = new ArrayList<String>(keySet);
		Collection<Integer> values = map.values();
		ArrayList<Integer> valueList = new ArrayList<>(values);
		for (int e = 0; e < keyList.size(); e++) {
			wordList.add(new WordOccurance(keyList.get(e), valueList.get(e)));
		}
		CountSorter countSorted = new CountSorter(wordList);
		ArrayList<WordOccurance> sortedList = countSorted.getSorted();
		Iterator itr = sortedList.iterator();
		while (itr.hasNext()) {
			WordOccurance w = (WordOccurance) itr.next();
			System.out.println(w.word + "\t" + w.count);
		}
		
		// Display key and value for each entry
	//	map.forEach((k, v) -> System.out.println(k + "\t" + v));
	}

	public static class WordOccurance implements Comparable<WordOccurance>{
		private String word;
		private int count;
			
		public WordOccurance(String w, int c){
			this.word = w;
			this.count = c;
		}
		
		
		public int getCount() {         
			return count;     
		}       

		@Override     
		public int compareTo(WordOccurance number) {          
			return (this.getCount() < number.getCount() ? -1 : 
							(this.getCount() == number.getCount() ? 0 : 1));     
		}
	}
	
	public static class CountSorter {     
		ArrayList<WordOccurance> sortedList = new ArrayList<>();       

		public CountSorter(ArrayList<WordOccurance> sortedList) {         
			this.sortedList = sortedList;     
		}       

		public ArrayList<WordOccurance> getSorted() {         
			Collections.sort(sortedList);         
			return sortedList;     
		} 
	}
}
