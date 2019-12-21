package cs228hw2.tests;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import cs228hw2.AmusingPreciseNumber;

public class AmusingPreciseNumberTest {
	private static final double EPSILON = .01;
	
	@Test
	public void AddTest() {
		AmusingPreciseNumber all = new AmusingPreciseNumber(1234);
		
		AmusingPreciseNumber all2 = new AmusingPreciseNumber("");
		
		assertEquals(1, all.data.get(0));
		assertEquals(2, all.data.get(1));
		assertEquals(3, all.data.get(2));
		assertEquals(4, all.data.get(3));
	}
	
	@Test
	public void AdditionTest() {
		AmusingPreciseNumber all = new AmusingPreciseNumber(1234);
		AmusingPreciseNumber all2 = new AmusingPreciseNumber(12);
		
		all.add(all2);
		
		assertEquals(1, all.data.get(0));
		assertEquals(2, all.data.get(1));
		assertEquals(4, all.data.get(2));
		assertEquals(6, all.data.get(3));
		
		
	}
	
	@Test
	public void StringTest() {
		AmusingPreciseNumber all = new AmusingPreciseNumber("13.24");
		
		
		assertEquals(1, all.data.get(0));
		assertEquals(3, all.data.get(1));
		assertEquals(2, all.data.get(2));
		assertEquals(4, all.data.get(3));
		
		
	}
	
	@Test
	public void DecimalTest() {
		AmusingPreciseNumber all = new AmusingPreciseNumber("13.24");
		AmusingPreciseNumber all2 = new AmusingPreciseNumber("87.24");
		
		all.add(all2);
		
		System.out.println(all);
		
		assertEquals(1, all.data.get(0));
		assertEquals(0, all.data.get(1));
		assertEquals(0, all.data.get(2));
		assertEquals(4, all.data.get(3));
		assertEquals(8, all.data.get(4));
		
	}
	
	@Test
	public void SubtractionTest() {
		AmusingPreciseNumber all = new AmusingPreciseNumber("13.24");
		AmusingPreciseNumber all2 = new AmusingPreciseNumber("87.24");
		
		all.subtract(all2);
		
		System.out.println(all);
		all.abs();
		System.out.println(all);
		all.negate();
		
		System.out.println(all);
		
		assertEquals(7, all.data.get(0));
		assertEquals(4, all.data.get(1));
		assertEquals(0, all.data.get(2));
		assertEquals(0, all.data.get(3));
		//assertEquals(false, all.positive);
		
	}
	

	@Test
	public void ReaderTest() {
		Reader r = new StringReader("-42.31");
		
		AmusingPreciseNumber all = new AmusingPreciseNumber(r);
		System.out.println(all);
		all.abs();
		System.out.println(all);
		try {
			r.read();
			r.read();
			r.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	@Test
	public void SubTest2() {
		AmusingPreciseNumber all = new AmusingPreciseNumber(4);
		AmusingPreciseNumber all2 = new AmusingPreciseNumber(4);
		
		all.subtract(all2);
		
		System.out.println(all);
	}
	@Test
	public void absTest2() {
		AmusingPreciseNumber all = new AmusingPreciseNumber(-1);
		Reader r = new StringReader("-1");
		AmusingPreciseNumber all2 = new AmusingPreciseNumber(r);
		AmusingPreciseNumber all3 = new AmusingPreciseNumber(all);
		AmusingPreciseNumber all4 = new AmusingPreciseNumber("-1");
		
		all.abs();
		System.out.println("\n int test " + all);
		
		all2.abs();
		System.out.println("\n reader test " + all2);
		
		all3.abs();
		System.out.println("\n copy of int test " + all3);
		
		all4.abs();
		System.out.println("\n string test " + all4);
		
		
		
		
	}
	@Test
	public void addNegTest() {
		AmusingPreciseNumber all = new AmusingPreciseNumber(-1);
		AmusingPreciseNumber all2 = new AmusingPreciseNumber(-1);
		
		//System.out.println(all);
		
		all.add(all2);
		System.out.println(all);
	}
	
}
