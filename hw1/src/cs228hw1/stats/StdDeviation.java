package cs228hw1.stats;

import java.util.ArrayList;

public class StdDeviation<T extends Number> extends AbstractStatObject<T> implements StatObject<T> {

	/**
	 * list that holds data list. Can be any type as long as it extends number
	 */
	private ArrayList<T> CurrentData = this.GetData();

	public StdDeviation(ArrayList<T> inputData) throws RuntimeException {
		super(inputData);
	}

	public StdDeviation() {

	}

	/**
	 * finds the std deviation and returns it in an arraylist of size 1
	 */
	public ArrayList<Number> GetResult() throws RuntimeException {
		ArrayList<Number> result = new ArrayList<>();

		double avg = new Average<T>(CurrentData).GetResult().get(0).doubleValue();

		double dev = 0;

		for (int i = 0; i < CurrentData.size(); i++) {
			dev += Math.pow(CurrentData.get(i).doubleValue() - avg, 2);
		}

		dev = Math.sqrt(dev / (CurrentData.size() - 1));
		result.add((Number) dev);
		return result;
	}
}
