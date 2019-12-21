package cs228hw1.stats;

import java.util.Comparator;

public class NumberComparator<T extends Number> implements Comparator<T> {

	@Override
	public int compare(T arg0, T arg1) {
		
		return (int)(arg0.doubleValue() - arg1.doubleValue());
	}

}
