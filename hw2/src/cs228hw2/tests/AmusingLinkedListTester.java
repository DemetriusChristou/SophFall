package cs228hw2.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import cs228hw2.AmusingLinkedList;
import cs228hw2.AmusingLinkedList.AmusingListIterator;
import cs228hw2.AmusingLinkedList.Node;

public class AmusingLinkedListTester {

		private static final double EPSILON = .01;

		@Test
		public void AddTest() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(2);
			all.add(3);
			all.add(4);
			
			assertEquals(1, all.get(0));
			assertEquals(2, all.get(1));
			assertEquals(3, all.get(2));
		}
		
		@Test
		public void AddSize() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(2);
			all.add(3);
			all.add(4);
			
			assertEquals(4, all.size());
			
		}
		
		@Test
		public void ClearTest() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(2);
			all.add(3);
			all.add(4);
			
			all.clear();
			
			assertEquals(0, all.size());
			
		}
		
		@Test
		public void ClearThenAddTest() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(2);
			all.add(3);
			all.add(4);
			
			all.clear();
			
			all.add(82);
			all.add(41);
			
			assertEquals(82, all.get(0));
			assertEquals(41, all.get(1));
			
		}
		
		@Test
		public void IndexOfTest() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(2);
			all.add(3);
			all.add(4);
			
			
			assertEquals(2, all.indexOf(3));
			assertEquals(-1, all.indexOf(-48));
			
		}
		@Test
		public void AddAndRemove() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(2);
			all.add(3);
			
			all.remove(1);
			
			
			assertEquals(1, all.get(0));
			assertEquals(3, all.get(1));
			assertEquals(2, all.size());
			
			all.add(7);
			all.add(42);
			
			assertEquals(7, all.get(2));
			assertEquals(42, all.get(3));
			
		}
		
		@Test
		public void LastIndexOf() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(2);
			all.add(3);
			all.add(1);
			
			assertEquals(3, all.lastIndexOf(1));
			
			all.add(1);
			
			assertEquals(4, all.lastIndexOf(1));
			

			
		}

		@Test
		public void ContainsTest() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(2);
			all.add(3);
			all.add(4);
			
			
			assertEquals(true, all.contains(2));
			assertEquals(false, all.contains(42));
			
		}
		
		@Test
		public void ContainsAllTest() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(2);
			all.add(3);
			all.add(4);
			
			List<Integer> key = Arrays.asList(1, 4);
			
			
			assertEquals(true, all.containsAll(key));
			
			List<Integer> key2 = Arrays.asList(1, 4, 8);
			assertEquals(false, all.containsAll(key2));
			
		}
		
		@Test
		public void IteratorTest() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(0);
			Node cur = all.getNodeAtIndex(0);
			all.add(1);
			all.add(2);
			all.add(3);
			all.add(4);
			
			AmusingListIterator i = (AmusingListIterator) all.listIterator();
			
			
			
			System.out.println(all.toString());
			
			assertEquals(0, i.next());
			assertEquals(1, i.next());
			assertEquals(2, i.next());
			assertEquals(3, i.next());
			assertEquals(4, i.next());
			assertEquals(4, i.previous());
			assertEquals(3, i.previous());
			assertEquals(2, i.previous());
			assertEquals(1, i.previous());
			assertEquals(0, i.previous());
			assertEquals(0, i.next());
			assertEquals(1, i.next());
			
			
		}
		
		@Test
		public void toArrayTest() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(2);
			all.add(3);
			all.add(4);
			
			Object[] test = all.toArray();
			
			assertEquals(1, test[0]);
			assertEquals(2, test[1]);
			assertEquals(3, test[2]);
			assertEquals(4, test[3]);
			
		}
		
		@Test
		public void AddAtIndexTest() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(3);
			all.add(4);
			all.add(5);
			
			all.add(1 , 2);
			all.add(2, 7);
			
			Object[] test = all.toArray();
			
			System.out.println(all);
			
			assertEquals(1, test[0]);
			assertEquals(2, test[1]);
			assertEquals(7, test[2]);
			assertEquals(3, test[3]);
			assertEquals(4, test[4]);
			assertEquals(5, test[5]);
			
		}
		
		@Test
		public void AddatIndexPrevNextTest() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(3);
			all.add(4);
			all.add(5);
			
			all.add(1 , 2);
			all.add(2, 7);
			all.add(2,27);
			
			Node cur = all.getNodeAtIndex(0);
			
			assertEquals(1, cur.getData());
			cur = cur.getNext();
			assertEquals(2, cur.getData());
			cur = cur.getNext();
			assertEquals(27, cur.getData());
			cur = cur.getNext();
			assertEquals(7, cur.getData());
			cur = cur.getNext();
			assertEquals(3, cur.getData());
			cur = cur.getNext();
			assertEquals(4, cur.getData());
			cur = cur.getNext();
			assertEquals(5, cur.getData());
			cur = cur.getPrev();
			assertEquals(3, cur.getData());
			cur = cur.getPrev();
			assertEquals(27, cur.getData());
			cur = cur.getPrev();
			assertEquals(1, cur.getData());
			
			
		}
		
		@Test
		public void AddAtIndexTest2() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(0,1);
			all.add(1,3);
			all.add(1,4);
			all.add(2,5);
			
			
			Object[] test = all.toArray();
			
			assertEquals(1, test[0]);
			assertEquals(4, test[1]);
			assertEquals(5, test[2]);
			assertEquals(3, test[3]);
			
			assertEquals(all.getNodeAtIndex(0).getPrev().getData(), all.get(2));
			assertEquals(all.getNodeAtIndex(0).getNext().getData(), all.get(1));
			assertEquals(all.getNodeAtIndex(1).getPrev(), null);
			assertEquals(all.getNodeAtIndex(1).getNext().getData(), all.get(2));
			assertEquals(all.getNodeAtIndex(2).getPrev().getData(), all.get(0));
			assertEquals(all.getNodeAtIndex(2).getNext().getData(), all.get(3));
			assertEquals(all.getNodeAtIndex(3).getPrev(), null);
			assertEquals(all.getNodeAtIndex(3).getNext().getData(), all.get(0));
			
			
		}
		
		@Test
		public void AddAll() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(0,1);
			all.add(1,3);
			all.add(1,4);
			all.add(2,5);
			
			Collection <Integer> list = new ArrayList<Integer>();
			list.add(8);
			list.add(9);
			list.add(10);
			list.add(11);
			
			all.addAll(1, list);
			
			Object[] test = all.toArray();
			
			assertEquals(1, test[0]);
			assertEquals(8, test[1]);
			assertEquals(9, test[2]);
			assertEquals(10, test[3]);
			assertEquals(11, test[4]);
			assertEquals(4, test[5]);
			assertEquals(5, test[6]);
			assertEquals(3, test[7]);
		}
		
		@Test
		public void SubList() {
			AmusingLinkedList<Number> all = new AmusingLinkedList<>();
			
			all.add(1);
			all.add(3);
			all.add(4);
			all.add(5);
			
			Object[] test = all.toArray();
			ArrayList<Integer> temp = (ArrayList<Integer>) all.subList(1, 3);
			
			System.out.println(all);
			
			assertEquals(temp.get(0), test[1]);
			assertEquals(temp.get(1), test[2]);
			
			System.out.println(1234 % 1000);
		}
}
