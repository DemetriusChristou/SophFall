package cs228hw1.stats;

public class IntegerParser implements IParser<Integer> {

	public Integer parse(String str) {
		Integer ret = Integer.parseInt(str);
		return ret;
	}

}
