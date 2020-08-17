/*
	Modified Listing 23.9 Heap Class for Exercise 23 - 07.
	
	Description:  (Min-heap) The heap presented in the text is also known as a max-heap , in which each node is GREATER than or equal to any of its children. A min-heap is a heap in which each node is LESS than or equal to any of its children. Min-heaps are often used to implement priority queues. Revise the Heap class in Listing 23.9 to implement a min-heap. 
	
*/

public class HeapSort {
	/** Heap sort method */
	public static <E extends Comparable<E>> void heapSort(E[] list) {
		// Create a Heap of integers
		Heap<E> heap = new Heap<>();

		// Add elements to the heap
		for (int i = 0; i < list.length; i++)
			heap.add(list[i]);

		// Remove elements from the heap
		for (int i = list.length - 1; i >= 0; i--)
			list[i] = heap.remove();
	}
	
	/** A test method */
	public static void main(String[] args) {
		Integer[] list = {-44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53}; 
		heapSort(list);
		System.out.println();
		for (int i = 0; i < list.length; i++)
			System.out.print(list[i] + " ");
	}
}