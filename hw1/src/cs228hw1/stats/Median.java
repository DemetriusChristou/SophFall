package cs228hw1.stats;

import java.util.ArrayList;

public class Median<T extends Number> extends AbstractStatObject<T> implements StatObject<T> {

	/**
	 * list that holds data list. Can be any type as long as it extends number
	 */
	private ArrayList<T> CurrentData = this.GetData();

	public Median(ArrayList<T> inputData) throws RuntimeException {
		super(inputData);
	}

	public Median() {

	}

	/**
	 * finds the median and returns it in an arraylist of size 1
	 */
	public ArrayList<Number> GetResult() throws RuntimeException {
		ArrayList<Number> result = new ArrayList<>();

		int size = CurrentData.size();
		// if list has an odd number of items
		if (CurrentData.size() % 2 == 1) {
			result.add(CurrentData.get(size / 2));
		}
		// if its not odd must be even
		else {
			double temp = (CurrentData.get(size / 2).doubleValue() + CurrentData.get((size / 2 - 1)).doubleValue()) / 2;
			result.add((Number) temp);
		}
		return result;
	}

}
