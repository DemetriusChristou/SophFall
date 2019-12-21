package cs228hw1.stats;

import java.util.ArrayList;
import java.util.Random;

import cs228hw1.stats.Statistics.DATA;

public class TESTCLASSDELETELATER {
	//TESTING REMOVE LATER
	public static void main(String args []) {
		ArrayList<Number> x = new ArrayList<Number>();
		int j = 2;
		Number k = 39999999;
		k.doubleValue();
		x.add(2);
		x.add(k);
		x.add(-12);
		x.add(4.5);
		x.add(1);
		x.add(7);
		x.add(7);
		x.add(13);
		
		ArrayList<Number> ran = new ArrayList<>();
		
		Random r = new Random(4);
		
		for(int i = 0; i < 100; i++) {
			ran.add(r.nextInt(20));
		}
	
		
		ArrayList<Number> y = new ArrayList<Number>();
		y.add(3);
		y.add(100);
		y.add(0);
		
		Histogram<Number> a = new Histogram<Number>(x);
		a.SetData(ran);
		
		a.SetNumberBins(4);
		a.SetMaxRange(10);
		a.SetMinRange(0);
		
		ArrayList<Number> result = new ArrayList<>();
		result.addAll(a.GetResult());
		IParser p = new DoubleParser();
		StatisticsShell<Number> test = new StatisticsShell<>(p);
		test.ReadFile("C:\\Users\\deema\\OneDrive\\Desktop\\School\\Sophomore\\Comp Sci 228\\hw1\\sampleData.txt", DATA.YR_MO_DA_HR_MN);
		
		Minimum<Number> b = new Minimum<>();
		//System.out.println(b.GetData());
		test.AddStatObject(b);
		//System.out.println(b.GetData());
		System.out.println();
		System.out.println(test.MapCar().get(0).get(0).doubleValue());
		
		//year
		System.out.println( (int)(test.MapCar().get(0).get(0).doubleValue() / 100000000));
		
		//day
		System.out.println( (int)(test.MapCar().get(0).get(0).doubleValue() / 10000 % 100));
		
		//month
		System.out.println( (int)(test.MapCar().get(0).get(0).doubleValue() / 1000000 % 100));
		
		
		//System.out.println(test.GetStatObject(0).GetData());
		
	}
}
