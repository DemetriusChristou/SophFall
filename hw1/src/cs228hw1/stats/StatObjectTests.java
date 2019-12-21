package cs228hw1.stats;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class StatObjectTests {
	private static final double EPSILON = .01;

	@Test
	public void AverageTests() {
		ArrayList<Double> x = new ArrayList<>();
		x.add(15.5);
		x.add(19.7);
		x.add(.01);
		x.add(999.3);
		x.add(39.8);
		x.add(47.876);
		// x.add(99999999.3);
		Average<Double> y = new Average<>();
		y.SetData(x);
		y.SetDescription("Completely useful Dataset");
		assertEquals("Completely useful Dataset", y.GetDescription());
		assertEquals(187.031, y.GetResult().get(0).doubleValue(), EPSILON);
	}

	@Test
	public void MedianTests() {
		ArrayList<Double> x = new ArrayList<>();
		x.add(15.5);
		x.add(19.7);
		x.add(.01);
		x.add(999.3);
		x.add(39.8);
		x.add(47.876);
		Median<Double> y = new Median<>();
		y.SetData(x);
		assertEquals(29.75, y.GetResult().get(0).doubleValue(), EPSILON);
		x.remove(2);
		x.add(null);
		y.SetData(x);
		assertEquals(39.8, y.GetResult().get(0).doubleValue(), EPSILON);
	}

	@Test
	public void MaximumTests() {
		ArrayList<Double> x = new ArrayList<>();
		x.add(15.5);
		x.add(19.7);
		x.add(.01);
		x.add(999.3);
		x.add(39.8);
		x.add(47.876);
		Maximum<Double> y = new Maximum<>();
		y.SetData(x);
		assertEquals(999.3, y.GetResult().get(0).doubleValue(), EPSILON);
		ArrayList<Double> z = new ArrayList<>();
		z.add(-999999.0);
		z.add(null);
		y.SetData(z);
		assertEquals((double) -999999, y.GetResult().get(0).doubleValue(), EPSILON);
		z.add(5.0);
		y.SetData(z);
		assertEquals(5.0, y.GetResult().get(0).doubleValue(), EPSILON);

	}

	@Test
	public void MinimumTests() {
		ArrayList<Double> x = new ArrayList<>();
		x.add(15.5);
		x.add(19.7);
		x.add(.01);
		x.add(999.3);
		x.add(39.8);
		x.add(47.876);
		Minimum<Double> y = new Minimum<>();
		y.SetData(x);
		assertEquals(.01, y.GetResult().get(0).doubleValue(), EPSILON);
		x.add(null);
		x.add(-15.0);
		y.SetData(x);
		assertEquals(-15.0, y.GetResult().get(0).doubleValue(), EPSILON);

	}

	@Test
	public void StdDeviationTests() {
		ArrayList<Double> x = new ArrayList<>();
		x.add(15.5);
		x.add(19.7);
		x.add(.01);
		x.add(999.3);
		x.add(39.8);
		x.add(47.876);
		StdDeviation<Double> y = new StdDeviation<>();
		y.SetData(x);
		assertEquals(398.293298, y.GetResult().get(0).doubleValue(), EPSILON);
	}

	@Test
	public void HIstTests() {
		ArrayList<Double> x = new ArrayList<>();
		x.add(3.0);
		x.add(3.0);
		x.add(2.99);
		x.add(4.0);
		x.add(8.0);
		x.add(9.0);
		x.add(15.0);
		Histogram<Double> y = new Histogram<>();
		y.SetData(x);
		y.SetNumberBins(3);
		y.GetResult();
		
		y.SetMaxRange(9.0);
		y.SetMinRange(3.0);
		System.out.println(y.GetResult());
		assertEquals(3.0, y.GetResult().get(0).doubleValue(), EPSILON);
		assertEquals(0.0, y.GetResult().get(1).doubleValue(), EPSILON);
		assertEquals(2.0, y.GetResult().get(2).doubleValue(), EPSILON);
	}

	@Test
	public void HistTest2() {
		ArrayList<Double> x = new ArrayList<>();
		x.add(null);
		x.add(null);
		x.add(null);
		x.add(null);
		Histogram<Double> y = new Histogram<>();
		y.SetData(x);
		y.SetNumberBins(5);
		assertEquals(0, y.GetResult().get(0).doubleValue(), EPSILON);
		assertEquals(0, y.GetResult().get(1).doubleValue(), EPSILON);
		assertEquals(0, y.GetResult().get(2).doubleValue(), EPSILON);
		x.clear();
		x.add(2.9);
		x.add(2.8);
		y.SetData(x);
		y.SetNumberBins(2);
		assertEquals(1, y.GetResult().get(0).doubleValue(), EPSILON);
		assertEquals(1, y.GetResult().get(1).doubleValue(), EPSILON);
	}
}
