package cs228hw1.stats;

public class DoubleParser implements IParser<Double> {
		public Double parse(String str) {
			Double ret = Double.parseDouble(str);
			return ret;
		}

}
