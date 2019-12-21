package cs228hw2.tests;

import java.util.Deque;

import org.junit.Test;

import cs228hw2.Deque228;

public class DequeTest {

	@Test
	public void qTest() {
		Deque<Integer> d = new Deque228();
		
		d.offerLast(1);
		d.offerLast(2);
		d.offerLast(3);
		d.offerLast(4);
		
		System.out.println(d.pollFirst());
		System.out.println(d.pollFirst());
		System.out.println(d.pollFirst());
		System.out.println(d.pollFirst());
		
	}
}
