package cs228hw1.stats;

import java.util.ArrayList;

public class Minimum<T extends Number> extends AbstractStatObject<T> implements StatObject<T> {

	/**
	 * list that holds data list. Can be any type as long as it extends number
	 */
	private ArrayList<T> CurrentData = this.GetData();

	public Minimum(ArrayList<T> inputData) throws RuntimeException {
		super(inputData);
	}

	public Minimum() {

	}

	/**
	 * finds the minimum and returns it in an arraylist of size 1
	 */
	public ArrayList<Number> GetResult() throws RuntimeException {
		ArrayList<Number> result = new ArrayList<>();

		// since the arraylist is sorted the minimum should always be the first element
		result.add((Number) CurrentData.get(0));
		return result;
	}

}
