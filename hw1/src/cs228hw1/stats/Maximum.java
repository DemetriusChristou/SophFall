package cs228hw1.stats;

import java.util.ArrayList;

public class Maximum<T extends Number> extends AbstractStatObject<T> implements StatObject<T> {

	/**
	 * list that holds data list. Can be any type as long as it extends number
	 */
	private ArrayList<T> CurrentData = this.GetData();

	public Maximum(ArrayList<T> inputData) throws RuntimeException {
		super(inputData);
	}

	public Maximum() throws RuntimeException {

	}

	/**
	 * finds the maximum and returns it in an array list of size 1
	 */
	public ArrayList<Number> GetResult() throws RuntimeException {
		ArrayList<Number> result = new ArrayList<>();


		// since the arraylist is sorted the maximum should be the last element
		result.add((T) CurrentData.get(CurrentData.size() - 1));

		return result;
	}

}
