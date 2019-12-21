package cs228hw2;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Demetrius Christou
 *
 */
public class Deque228<E> implements Deque<E> {

	AmusingLinkedList<E> data = new AmusingLinkedList<>();

	public Deque228() {

	}

	@Override
	public boolean addAll(Collection arg0) {

		return data.addAll(arg0);
	}

	/**
	 * resets the data in the deque
	 */
	public void clear() {
		data.clear();

	}

	/**
	 * returns true if every key can be found in the data of deque
	 */
	public boolean containsAll(Collection keys) {

		return data.containsAll(keys);
	}

	/**
	 * returns true if size = 0
	 */
	public boolean isEmpty() {

		return data.isEmpty();
	}

	/**
	 * returns true if every element in the arg has been removed
	 */
	public boolean removeAll(Collection arg0) {

		return data.removeAll(arg0);
	}

	/**
	 * removes all the data except for the one specified in arg0
	 */
	public boolean retainAll(Collection arg0) {

		return data.retainAll(arg0);
	}

	@Override
	public Object[] toArray() {

		return data.toArray();
	}

	@Override
	public Object[] toArray(Object[] arg0) {

		return data.toArray(arg0);
	}

	@Override
	public boolean add(Object arg0) {
		return data.add(arg0);
	}

	/**
	 * adds element to the front of the deque
	 */
	public void addFirst(Object item) {
		data.add(0, item);
	}

	/**
	 * adds element to the end of the deque
	 */

	public void addLast(Object item) {
		data.add(item);

	}

	@Override
	public boolean contains(Object arg0) {

		return data.contains(arg0);
	}

	@Override
	public Iterator descendingIterator() {

		return data.iterator();
	}

	/**
	 * returns the first item in the deque
	 */
	public E element() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return (E) data.get(0);
	}

	/**
	 * returns the first item in the deque
	 */

	public E getFirst() {
		// element checks to see if the list is empty
		return element();
	}

	/**
	 * returns the last item in the deque
	 */
	public E getLast() {

		return (E) data.get(data.size() - 1);
	}

	@Override
	public Iterator iterator() {

		return data.iterator();
	}

	/**
	 * adds item to the end of the deque
	 */
	public boolean offer(Object item) {

		data.add(item);
		// no size restriction so it should always be true
		return true;
	}

	/**
	 * adds item to the front of the deque
	 */
	public boolean offerFirst(Object item) {

		data.add(0, item);

		return true;
	}

	@Override

	public boolean offerLast(Object item) {
		// offer does the same thing as offerLast
		return offer(item);
	}

	@Override
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return (E) data.get(0);
	}

	@Override
	public E peekFirst() {
		// peek does the same thing
		return peek();
	}

	@Override
	public E peekLast() {
		if (isEmpty()) {
			return null;
		}
		return (E) data.get(data.size() - 1);
	}

	/**
	 * removes and returns the first item in the deque
	 */
	public E poll() {
		if (isEmpty()) {
			return null;
		}
		E ret = (E) data.get(0);
		data.remove(0);
		return ret;
	}

	@Override

	public E pollFirst() {
		// poll does the same thing
		return poll();
	}

	/**
	 * returns and removes the last item in the deque
	 */
	public E pollLast() {
		if (isEmpty()) {
			return null;
		}
		E ret = (E) data.get(data.size() - 1);
		data.remove(data.size() - 1);
		return ret;
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			return null;
		}
		E ret = (E) data.get(data.size() - 1);
		data.remove(data.size() - 1);
		return ret;
	}

	/**
	 * adds an item to the end of the list
	 */
	public void push(Object item) {
		data.add(item);

	}

	@Override
	public E remove() {

		return (E) data.remove(0);
	}

	@Override
	public boolean remove(Object item) {

		return data.remove(item);
	}

	@Override

	public E removeFirst() {

		return (E) data.remove(0);
	}

	@Override
	public boolean removeFirstOccurrence(Object key) {

		return data.remove(key);
	}

	@Override
	public E removeLast() {
		// pollLast does the same thing
		return pollLast();
	}

	@Override
	public boolean removeLastOccurrence(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {

		return data.size();
	}

}
