package cs228hw1.stats;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractStatObject<T extends Number> implements StatObject<T> {
	/**
	 * holds the description of the stat object
	 */
	private String description;

	/**
	 * list that holds data list. Can be any type as long as it extends number
	 */
	private ArrayList<T> CurrentData = new ArrayList<>();

	public AbstractStatObject(ArrayList<T> inputData) throws RuntimeException {

		SetData(inputData);
	}

	public AbstractStatObject() {

	}

	/**
	 * sets the description to given string
	 */
	public void SetDescription(String d) {
		description = d;
	}

	/**
	 * returns the current description
	 */
	public String GetDescription() {

		return description;
	}

	/**
	 * finds the result of whatever the current calculation is.
	 */
	public abstract ArrayList<Number> GetResult() throws RuntimeException;

	/**
	 * creates a deep copy and checks to see if array has null values or is just
	 * empty also sorts array
	 */
	public void SetData(ArrayList<T> data) throws RuntimeException {

		CurrentData.clear();

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i) == null) {
				data.remove(i);
			}
		}

		if (data.size() != 0) {
			data.sort(new NumberComparator());
		} else {
			throw new RuntimeException("List is empty");
		}

		CurrentData.addAll(data);
	}

	/**
	 * returns the current data arraylist
	 */
	public ArrayList<T> GetData() {

		return CurrentData;
	}

}
