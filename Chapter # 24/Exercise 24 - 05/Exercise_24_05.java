/*
	Author:  Chad Lister
	Date:  08/21/2020
	
	His 24.7 & 24.8 generic queue modified.
	
	(Implement GenericQueue using inheritance) In Section 24.5, GenericQueue is implemented using composition. This means that a LinkedList object is created inside of the class and then is used to access LinkedList's methods.

	Another way of creating GenericQueue is using inheritance. Instead of creating an instance of LinkedList in the object class, GenericQueue can extend LinkList and access its methods that way.

	Create a new GenericQueue class that uses inheritance. Create a test method that uses both the enqueue and dequeue methods and prints how the queue is affected.
*/

import java.util.*;

public class Exercise_24_05 {
	public static void main(String[] args) {
		
		// Create a queue
		MyGenericQueue<String> queue = new MyGenericQueue<>();

		// Add elements to the queue
		System.out.println("  ***  Add to queue using 'queue' method.   ***");
		System.out.println();
		queue.enqueue("Tom");
		System.out.println("Queue: (7) " + queue);

		queue.enqueue("Susan");
		System.out.println("Queue: (8) " + queue);

		queue.enqueue("Kim");
		queue.enqueue("Michael");
		System.out.println("Queue: (9) " + queue);

		// Remove elements from the queue
		System.out.println();
		System.out.println("   ***   Removed from queue using 'dequeue' method.   ***");
		System.out.println();
		System.out.println("Queue: (10) " + queue.dequeue());
		System.out.println("Queue: (11) " + queue.dequeue());
		System.out.println("Queue: (12) " + queue);	
	}
		
	public static class MyGenericQueue<E> extends LinkedList<E> {
		
		public void enqueue(E e) {
			this.addLast(e);
		}

		public E dequeue() {
			return this.removeFirst();
		}

		public int getSize() {
			return this.size();
		}

		//  Overrides Abstract Class inheritance.			**  Gives overflow error.
//		@Override
//		public String toString() {
//			return "Queue: " + this.toString();
//		}
	}
}