package cs228hw1.stats;

import java.util.ArrayList;

public class Average<T extends Number> extends AbstractStatObject<T> implements StatObject<T> {

	/**
	 * list that holds data list. Can be any type as long as it extends number
	 */
	private ArrayList<T> CurrentData = this.GetData();

	public Average(ArrayList<T> inputData) throws RuntimeException {
		super(inputData);
	}

	public Average() {

	}

	/**
	 * takes an average and returns it in an arraylist of size 1
	 */
	public ArrayList<Number> GetResult() throws RuntimeException {
		ArrayList<Number> result = new ArrayList<>();

		// converts the Number into double to do calculations
		double temp = 0;
		for (int i = 0; i < CurrentData.size(); i++) {
			temp += CurrentData.get(i).doubleValue();
		}

		result.add((Number) (temp / CurrentData.size()));
		return result;
	}

}
