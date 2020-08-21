/*
	Author:  Chad Lister
	Date:  08/20/2020
	Class for Exercise 24 - 01.  Modified by Chad Lister for Exercise 24 - 01.
*/

public abstract class MyAbstractList<E> implements MyList<E> {
	protected int size = 0; // The size of the list

	/** Create a default list */
	protected MyAbstractList() {
	}

	/** Create a list from an array of objects */
	protected MyAbstractList(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	/** Add a new element at the end of this list */
	public void add(E e) {
		add(size, e);
	}

	//  Added addAll method by Chad Lister.
	@Override
	/** Adds the elements in otherList to this list.			//  Without Dup??
	* Returns true if this list changed as a result of the call */
	public boolean addAll(MyList<E> otherList) {
		boolean changed = false;

		for (int o = 0; o < otherList.size(); o++) {
			
			//  Duplicate check.			*** erase if if dups wanted.
			if (this.contains(otherList.get(o)) == false) {
				this.add(size(), otherList.get(o));
				changed = true;
			}
		}
		return changed;
	}
	
	/** Return true if this list contains no elements */
	public boolean isEmpty() {
		return size == 0;
	}

	/** Return the number of elements in this list */
	public int size() {
		return size;
	}

	/** Remove the first occurrence of the element o from this list.
	 *  Shift any subsequent elements to the left.
	 *  Return true if the element is removed. */
	public boolean remove(E e) {
		if (indexOf(e) >= 0) {
			remove(indexOf(e));
			return true;
		}
		else 
			return false;
	}
	
	//  Added removeAll method by Chad Lister.
	@Override
	/** Removes all the elements in otherList from this list
	* Returns true if this list changed as a result of the call */
	public boolean removeAll(MyList<E> otherList) {
		boolean changed = false;

		for (int i = 0; i < otherList.size(); i++) {
			
			if (this.contains(otherList.get(i)) == true) {
				this.remove(otherList.get(i));
				changed = true;
			}

		}
		return changed;
	}
	
	//  Added retainAll method by Chad Lister.
	@Override
	/** Retains the elements in this list that are also in otherList
	Returns true if this list changed as a result of the call */
	public boolean retainAll(MyList<E> otherList) {
		boolean changed = false;

		for (int i = 0; i < this.size(); i++) {
		
			if (otherList.contains(this.get(i)) == false) {
				
				this.remove(this.get(i));
				i--;
				changed = true;
			}
		}
		return changed;
	}
}