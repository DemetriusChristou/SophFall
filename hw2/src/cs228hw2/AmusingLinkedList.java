package cs228hw2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Demetrius Christou
 *
 */
public class AmusingLinkedList<E> implements List {

	private Node head;
	private Node tail;
	private int size;

	/**
	 * generic constructor
	 */
	public AmusingLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public class Node {

		Node next;
		Node prev;
		E data;

		public Node(E e, Node n, Node p) {
			data = e;
			next = n;
			prev = p;
		}

		public E getData() {
			return data;
		}

		public Node getNext() {
			return next;
		}

		public Node getPrev() {
			return prev;
		}
	}

	public class AmusingListIterator implements ListIterator<E> {

		Node cur;
		// holds the last node passed over in either a next or previous
		Node lastNode = null;
		int curIndex;

		public AmusingListIterator() {
			curIndex = 0;
			cur = head;
			lastNode = cur;
		}

		public AmusingListIterator(int start) {

			if (start >= 0 && start <= size) {
				curIndex = start;
				cur = getNodeAtIndex(start);
				lastNode = cur;
			} else {
				throw new IndexOutOfBoundsException();
			}

		}

		/**
		 * creates a node with the given data and adds it to the end of the list
		 */
		public void add(E data) {
			Node newNode = new Node((E) data, null, null);

			if (head == null) {
				head = newNode;
				newNode.next = newNode;
				newNode.prev = newNode;
				size++;
				tail = newNode;
				return;
			}

			tail.next = newNode;
			tail = tail.next;

			if (size % 2 == 0) {
				newNode.prev = tail.prev;
				tail = newNode;
				newNode.next = head.next;
				head.next.prev = tail;
				size++;
				return;
			} else {
				newNode.next = head.next;
				tail = newNode;
				head.next.prev = tail;
				size++;
				return;
			}

		}

		/**
		 * checks to see if the current node has a node in front of it
		 */
		public boolean hasNext() {
			return curIndex < size;
		}

		/**
		 * returns true if there is a node behind the current node
		 */
		public boolean hasPrevious() {

			return curIndex >= 0;
		}

		/**
		 * returns the data in the next index
		 */
		public E next() {
			if (!hasNext()) {
				throw new IndexOutOfBoundsException();
			}
			lastNode = cur;
			E ret = cur.data;
			// if this is the last item in the list dont increment
			// or update next as it will go to the font of the list
			if (curIndex + 1 == size) {
				curIndex++;
				return ret;
			}
			cur = cur.next;
			curIndex++;
			return ret;
		}

		/**
		 * returns the index of the next index
		 */

		public int nextIndex() {
			if (size == 1) {
				return 0;
			}
			// last indexs next goes to the first item
			if (curIndex + 1 == size) {
				return 0;
			}
			return curIndex + 1;
		}

		/**
		 * returns the data in the previous node
		 */

		public E previous() {

			if (!hasPrevious()) {
				throw new IndexOutOfBoundsException();
			}

			E ret = cur.data;
			// dont increment or change current since it will follow the previous node
			// and go all the way to the last even index in the list
			if (curIndex == 0) {
				curIndex--;
				return ret;
			} 
			if(curIndex == size) {
				curIndex--;
			}
			
			if (curIndex % 2 == 0) {
				
				cur = cur.prev.next;
			} else {
				
				cur = cur.next.prev;
			}
			
			curIndex--;


			return ret;

		}

		/**
		 * returns the previous index by following the last node's previous -1 if its
		 * null
		 */
		public int previousIndex() {

			if (size == 1) {
				return 0;
			}
			if (cur.prev == null) {
				return -1;
			}
			// first index prev goes to the end of the list
			if (curIndex == 0 && size % 2 == 0) {
				return size - 2;
			} else if (curIndex == 0) {
				return size - 1;
			}

			return curIndex - 2;
		}

		/**
		 * removes the last element in this list
		 */
		public void remove() {
			if (isEmpty()) {
				throw new NoSuchElementException();
			}

			if (size == 1) {
				clear();
				return;
			}

			if (size % 2 == 0) {
				head.prev.next = null;
				tail = head.prev.next;
				tail.next = head;

			} else {
				tail = head.prev.prev.next;
				tail.next = head;
				head.prev = head.prev.prev;

			}

			size--;
		}

		/**
		 * sets the data of the current node to the given data
		 */
		public void set(E newData) {
			if (lastNode != null) {
				lastNode.data = newData;
			}
		}

	}

	/**
	 * adds an object to the end of the linked list giving the new node the correct
	 * prev and next pointers
	 */
	public boolean add(Object data) {

		Node newNode = new Node((E) data, null, null);

		if (head == null) {
			head = newNode;
			newNode.next = newNode;
			newNode.prev = newNode;
			size++;
			tail = newNode;
			return true;
		}

		if (size % 2 == 0) {
			newNode.next = head;
			tail.next = newNode;
			tail = newNode;
			newNode.prev = head.prev;
			head.prev = newNode;

			size++;
			return true;
		} else {
			newNode.next = head;
			tail.next = newNode;
			tail = newNode;

			size++;
			return true;
		}
	}

	/**
	 * puts the given data into a new Node and inserts it at the given position
	 * moving the rest of the node over to the right and changing them from even to
	 * odd or odd to even
	 */
	public void add(int index, Object data) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}

		// if we are adding to the end of the list then
		// we can just treat it as a normal add
		if (isEmpty() && index == 0 || index == size) {
			add(data);
			return;
		}

		Node cur = new Node((E) data, null, null);
		Node moved = getNodeAtIndex(index);
		Node previous = null;

		// if you are adding at the front of the list
		if (index == 0) {
			head = cur;
			previous = tail;
		}

		if (moved.prev == null) { // give the inserted node the next and prev of what was in that index before

			previous = moved.next.prev;
			previous.next = cur;
			cur.next = moved;
			moved.next.prev.next = cur;
		} else {
			cur.next = moved;
			if (index != 0) {
				previous = moved.prev.next;
			}
			if (size == 1 && index == 0) {
				cur.prev = cur;
			}

			if (index == 0 && size % 2 == 0) {
				cur.prev = moved.prev.prev.next;
			} else if (index == 0) {
				cur.prev = tail;

			}
			previous.next = cur;

		}
		size++;

		// we have to change all the nodes after the insert to
		// reflect that they have switched from even to odd or vice versa
		while (cur.next != head) {

			// if cur is odd
			if (cur.prev == null) {
				cur.next.prev = previous;

			} else { // cur is even
				cur.next.prev = null;

			}

			previous = cur;
			cur = cur.next;
		}

		if (size % 2 == 0) {
			head.prev = previous;

		} else {
			head.prev = cur;
		}
	}

	/**
	 * adds the entire collection into the linked list
	 */
	public boolean addAll(Collection list) {
		Iterator<E> i = list.iterator();
		while (i.hasNext()) {
			add(i.next());
		}
		return true;
	}

	/**
	 * adds the entire list into the Nodes starting at the given index
	 */
	public boolean addAll(int start, Collection list) {
		if (start >= size) {
			return false;
		}
		Iterator<E> i = list.iterator();
		int index = start;

		while (i.hasNext()) {
			add(start, i.next());
			start++;
		}
		return true;
	}

	/**
	 * clears the list by setting the head to null
	 */
	public void clear() {
		head = null;
		size = 0;
		tail = null;
	}

	/**
	 * goes through the list and returns as soon as it finds data that matches key
	 */
	public boolean contains(Object key) {
		Node cur = head;

		if (this.isEmpty()) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (cur.data == key) {
				return true;
			}
			cur = cur.next;
		}

		return false;
	}

	/**
	 * returns true if every item in a collection can be found in the current list
	 */
	public boolean containsAll(Collection keys) {
		Node cur = head;

		boolean ret = true;

		Iterator iter = keys.iterator();

		while (iter.hasNext()) {
			if (!this.contains(iter.next())) {
				ret = false;
			}
		}

		return ret;

	}

	/**
	 * returns the data at the given index
	 */
	public Object get(int index) {
		if (index >= size) {
			throw new NoSuchElementException();
		}

		Node cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.data;
	}

	/**
	 * returns the index of the first linked list with data matching the key
	 * otherwise it will return -1
	 */
	public int indexOf(Object key) {
		Node cur = head;

		for (int i = 0; i < size; i++) {
			if (cur.data == key) {
				return i;
			}
			cur = cur.next;
		}

		return -1;
	}

	/**
	 * tells if the list is empty by seeing if the head points to a node
	 */
	public boolean isEmpty() {

		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * returns an AmusingListIterator for this list
	 */
	public Iterator iterator() {

		return new AmusingListIterator();
	}

	/**
	 * goes through linked list and returns the last index that item is found
	 * 
	 * returns -1 if that item is not in the list
	 */
	public int lastIndexOf(Object key) {
		Node cur = head;
		int ret = -1;
		for (int i = 0; i < size; i++) {
			if (cur.data == key) {
				ret = i;
			}
			cur = cur.next;
		}

		return ret;

	}

	/**
	 * returns an AmusingListIterator for this list
	 */
	public ListIterator listIterator() {

		return new AmusingListIterator();
	}

	/**
	 * returns an AmusingListIterator starting at given start value
	 */
	public ListIterator listIterator(int start) {

		return new AmusingListIterator(start);
	}

	@Override
	public boolean retainAll(Collection list) {

		Iterator i = list.iterator();
		while (i.hasNext()) {
			E key = (E) i.next();
			while (contains(key)) {
				remove(key);
			}
		}
		return true;
	}

	/**
	 * sets the data in the node at the given index to the new Data
	 */
	public Object set(int index, Object newData) {
		Object ret = get(index);
		getNodeAtIndex(index).data = (E) newData;

		return ret;
	}

	/**
	 * returns the size of the linked list
	 */
	public int size() {

		return size;
	}

	/**
	 * creates a list that goes from start inclusive to end exclusive
	 */
	public List subList(int start, int end) {
		if (start < 0 || end > size || start > end) {
			throw new IndexOutOfBoundsException();
		}
		Node cur = getNodeAtIndex(start);
		ArrayList<E> ret = new ArrayList<>();

		if (start == end) {
			return ret;
		}
		for (int i = start; i < end; i++) {
			ret.add(cur.data);
			cur = cur.next;
		}
		return ret;
	}

	/**
	 * returns an array with all of the data in the Linked list
	 */
	public Object[] toArray() {
		E[] ret = (E[]) new Object[size];

		Node cur = head;

		for (int i = 0; i < size; i++) {
			ret[i] = cur.data;
			cur = cur.next;
		}

		return ret;
	}

	/**
	 * returns an array with all of the data in the Linked list with the given type
	 * of arr
	 */
	public Object[] toArray(Object[] arr) {

		Object[] largerArr = new Object[arr.length];

		// creates an array large enough to hold all the data in linked list
		// if the given array can't hold it all
		while (largerArr.length < size) {
			largerArr = new Object[largerArr.length * 2];
		}

		for (int i = 0; i < arr.length; i++) {
			largerArr[i] = arr[i];
		}
		Node cur = head;

		for (int i = 0; i < size; i++) {
			largerArr[i] = cur.data;
			cur = cur.next;
		}

		return largerArr;
	}

	/**
	 * 
	 * @param index
	 * @return returns the node at the given index
	 */
	public Node getNodeAtIndex(int index) {
		if (index > size) {
			throw new IndexOutOfBoundsException();
		}
		Node cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur;
	}

	@Override
	public boolean removeAll(Collection c) {
		if (!containsAll(c)) {
			return false;
		}

		Iterator i = c.iterator();

		while (i.hasNext()) {
			E temp = (E) i.next();
			if(c.contains(temp)) {
				remove(i.next());
			}
		}
		return true;
	}

	/**
	 * removes the node at the given index
	 */
	public Object remove(int index) {

		if (index < 0 || index > size() || isEmpty()) {
			throw new IndexOutOfBoundsException();
		}

		// cur will start at the object to be removed then will
		// move through the list to switch from even to odd
		Node remove = getNodeAtIndex(index);
		Object ret = remove.data;

		// if there is only one item in the list then you can
		// just clear the whole list
		if (size == 1) {
			clear();
			return ret;
		}

		Node previous = null;

		// delete the pointers that go to remove
		// also do special case checking if you are removing the first node
		if (remove.prev == null) {
			previous = remove.next.prev;
			previous.next = remove.next;
		} else {
			if (remove.next == head) {
				remove.next.prev = remove.prev;
			} else {
				remove.next.prev = remove.prev;
			}
			if (index != 0) {
				previous = remove.prev.next;
				previous.next = remove.next;
			} else {
				if (size % 2 == 0) {
					previous = remove.prev.next;
				} else {
					previous = remove.prev;
				}
				previous.next = remove.next;
				head = previous.next;
			}

		}
		size--;

		if (index == size) {
			tail = previous;
		}

		// the while loop only works for lists with size greater than 2
		if (size == 2) {
			return ret;
		}

		// change the name of remove so the code is easier to understand
		// since from now on we are just going through and updating pointers
		Node cur = remove;

		// we have to change all the nodes after the insert to
		// reflect that they have switched from even to odd or vice versa
		while (cur != head) {
			if (cur.next == head && size % 2 == 0) {
				head.prev = previous;
				break;
			} else if (cur.next == head) {
				head.prev = cur;
				break;
			}
			// if cur is odd
			if (cur.prev == null) {
				cur.next.prev = null;

			} else { // cur is even
				cur.next.prev = previous;

			}

			if (cur != remove) {
				previous = cur;
			}

			cur = cur.next;
		}

		return ret;

	}

	/**
	 * removes the first instance of the object o in the list
	 */
	public boolean remove(Object o) {
		if (!contains(o)) {
			return false;
		}
		int index = indexOf(o);
		remove(index);
		return true;
	}

	/**
	 * prints out the list and its links in a uniform format
	 */
	public String toString() {

		AmusingListIterator all = (AmusingLinkedList<E>.AmusingListIterator) this.listIterator();
		String ret = "";

		for (int i = 0; i < size; i++) {

			ret += i + " " + all.previousIndex() + " " + all.nextIndex() + " " + all.next();

			if (i + 1 != size) {
				ret += "\n";
			}
		}

		return ret;

	}

}