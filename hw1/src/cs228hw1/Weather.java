package cs228hw1;

import java.util.ArrayList;

import cs228hw1.stats.Average;
import cs228hw1.stats.DoubleParser;
import cs228hw1.stats.Histogram;
import cs228hw1.stats.IParser;
import cs228hw1.stats.Maximum;
import cs228hw1.stats.Median;
import cs228hw1.stats.Minimum;
import cs228hw1.stats.Statistics.DATA;
import cs228hw1.stats.StatisticsShell;

public class Weather {

	public static void main(String[] args) {
		String inputFile = args[0];

		IParser p = new DoubleParser();

		StatisticsShell<Number> ss = new StatisticsShell<Number>(p);

		ss.ReadFile(inputFile, DATA.YR_MO_DA_HR_MN);

		ArrayList<Number> time = new ArrayList<>();

		ArrayList<Number> temperture = new ArrayList<>();

		time.addAll(ss.GetDataSet());

		// holds the index where the months change
		ArrayList<Integer> months = new ArrayList<>();
		// holds the indexes where the day changes
		ArrayList<Integer> days = new ArrayList<>();

		int pastMonth = (int) (time.get(0).doubleValue() / 1000000 % 100);

		for (int i = 0; i < time.size(); i++) {
			if (pastMonth != (int) (time.get(i).doubleValue() / 1000000 % 100)) {
				months.add(i);
				pastMonth = (int) (time.get(i).doubleValue() / 1000000 % 100);
			}
		}

		int pastDay = (int) (time.get(0).doubleValue() / 10000 % 100);

		for (int i = 0; i < time.size(); i++) {
			if (pastDay != (int) (time.get(i).doubleValue() / 10000 % 100)) {
				days.add(i);
				pastDay = (int) (time.get(i).doubleValue() / 10000 % 100);
			}
		}
		System.out.println(months.size());

		System.out.println(days.size());
		// tell the highs and lows of each month
		ss.ReadFile(inputFile, DATA.TEMP);

		temperture.addAll(ss.GetDataSet());

		int lastIndex = 0;
		// for loop that creates a new maximum and minimum each month using the months
		// arraylist
		for (int i = 0; i < months.size(); i++) {

			ss.AddStatObject(new Maximum(temperture), lastIndex, months.get(i));
			ss.AddStatObject(new Minimum(temperture), lastIndex, months.get(i));
			lastIndex = months.get(i);
		}
		ArrayList<ArrayList<Number>> MaxAndMin = ss.MapCar();

		for (int i = 0; i < months.size() * 2; i++) {
			System.out.print("Max is: ");
			System.out.print(MaxAndMin.get(i));
			i++;
			System.out.print(" Min is: ");
			System.out.println(MaxAndMin.get(i));
		}

		System.out.println();

		lastIndex = 0;

		ss = new StatisticsShell(p);
		ss.ReadFile(inputFile, DATA.TEMP);

		temperture = new ArrayList<Number>();
		temperture.addAll(ss.GetDataSet());

		// for loop that creates a new maximum and minimum each month using the days
		// arraylist
		for (int i = 0; i < 355; i++) {
			ss.AddStatObject(new Average(temperture), lastIndex, days.get(i));
			ss.AddStatObject(new Median(temperture), lastIndex, days.get(i));
			lastIndex = days.get(i);
		}

		ArrayList<ArrayList<Number>> AvgAndMed = ss.MapCar();

		for (int i = 0; i < days.size(); i++) {
			System.out.print("Avg is: ");
			System.out.print(AvgAndMed.get(i));
			i++;
			System.out.print(" Median is: ");
			System.out.println(AvgAndMed.get(i));
		}

		System.out.println();

		// resets the list again
		for (int i = 0; i < ss.Count(); i++) {
			ss.RemoveStatObject(i);

		}

		ss.ReadFile(inputFile, DATA.TEMP);
		temperture.addAll(ss.GetDataSet());
		lastIndex = 0;
		// for loop that creates a new maximum each day using the days arraylist
		for (int i = 0; i < months.size(); i++) {

			ss.AddStatObject(new Maximum<Number>(temperture), lastIndex, days.get(i));

			lastIndex = days.get(i);
		}

		ArrayList<ArrayList<Number>> Maxs = ss.MapCar();
		ArrayList<Number> Maximum = new ArrayList<>();
		for (int i = 0; i < Maxs.size(); i++) {
			Maximum.add(Maxs.get(i).get(0));
		}

		Histogram<Number> h = new Histogram<Number>(Maximum);
		h.SetNumberBins(15);
		h.SetMaxRange(110);
		h.SetMinRange(-40);

		ArrayList<Number> histResult = h.GetResult();

		for (int i = 0; i < histResult.size(); i++) {
			System.out.println("bin " + (i + 1) + " has " + histResult.get(i) + " items");
		}

	}
}
