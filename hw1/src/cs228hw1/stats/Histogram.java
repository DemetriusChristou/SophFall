package cs228hw1.stats;

import java.util.ArrayList;

public class Histogram<T extends Number> extends AbstractStatObject<T> implements StatObject<T> {
	/**
	 * list that holds data list. Can be any type as long as it extends number
	 */
	private ArrayList<T> CurrentData = this.GetData();

	/**
	 * sets number of equal sized bins for histogram defaults to 10
	 */
	private int numBins = 10;

	/**
	 * holds the lowest number allowed in the bin
	 */
	private double histMin = (Double.NEGATIVE_INFINITY);

	/**
	 * holds the highest number allowed in the bin
	 */
	private double histMax = Double.POSITIVE_INFINITY;

	/**
	 * 
	 * @param inputData input arraylist of numbers
	 * @throws RuntimeException will throw a runtime error if list is empty or null
	 */
	public Histogram(ArrayList<T> inputData) throws RuntimeException {
		super(inputData);
	}

	public Histogram() {

	}

	/**
	 * 
	 * @param pBins number of bins to use in histogram
	 */
	public void SetNumberBins(int pBins) {
		numBins = pBins;
	}

	/**
	 * 
	 * @return number of bins for histogram
	 */
	public int GetNumberBins() {
		return numBins;
	}

	/**
	 * 
	 * @param pMin lowest number allowed into the bins
	 */
	public void SetMinRange(T pMin) throws RuntimeException {
		histMin = pMin.doubleValue();
		if (histMin > histMax) {
			throw new RuntimeException("Minimum is higher than maximum.");
		}

	}

	/**
	 * 
	 * @return the lowest number allowed into the bins
	 */
	public Number GetMinRange() {
		return histMin;
	}

	/**
	 * 
	 * @param pMax highest number allowed into the bins
	 */
	public void SetMaxRange(T pMax) throws RuntimeException {
		histMax = pMax.doubleValue();
		if (histMax < histMin) {
			throw new RuntimeException("Maximum is lower than minimum.");
		}
	}

	/**
	 * 
	 * @return highest number allowed into the bins
	 */
	public Number GetMaxRange() {
		return histMax;
	}

	/**
	 * finds the histogram and returns it in an arraylist of size 1
	 */
	public ArrayList<Number> GetResult() throws RuntimeException {

		// makes sure the data is in the correct sorted order since this is
		// important for my algorithm to find the histogram.
		CurrentData = this.GetData();

		ArrayList<Number> result = new ArrayList<>();
		ArrayList<Number> temp = new ArrayList<>();

		// k holds the current position in the temp arraylist
		int k = 0;

		double increment = (histMax - histMin) / numBins;

		// goes through current data and creates a new arraylist only with numbers in
		// the
		// acceptable bounds.
		for (int i = 0; i < CurrentData.size(); i++) {
			if (CurrentData.get(i).doubleValue() >= histMin && CurrentData.get(i).doubleValue() < histMax) {
				temp.add(CurrentData.get(i));
			}
		}

		for (int j = 1; j <= numBins; j++) {
			int count = 0;

			while (k < temp.size() && temp.get(k).doubleValue() < (histMin + increment * j)) {
				count++;
				k++;
			}

			result.add(count);

		}

		return result;
	}

}
