package cs228hw2;

import java.util.Deque;
import java.util.Scanner;

/**
 * 
 * @author Demetrius Christou
 *
 */
public class PostFixCalc {
	public static void main(String[] args) {

		// TODO change this
		Deque<AmusingPreciseNumber> d = new Deque228<AmusingPreciseNumber>();

		// TODO get the input from the user
		System.out.println("input your problem");
		Scanner input = new Scanner(System.in);

		Scanner in = new Scanner(input.nextLine());

		boolean failed = false;

		while (in.hasNext()) {
			String subString = in.next();

			if (isNumeric(subString)) {
				d.add(new AmusingPreciseNumber(subString));
			} else {
				if (subString.equals("+")) {

					if (!enoughElements("+", d)) {
						System.out.println("illegal expression");
						failed = true;
						break;
					}
					AmusingPreciseNumber temp = d.pop();
					temp.add(d.pop());
					d.add(temp);
					// ADD TOP 2 NUMBERS IN THE DEQUE
				} else if (subString.equals("-")) {

					if (!enoughElements("-", d)) {
						System.out.println("illegal expression");
						failed = true;
						break;
					}
					AmusingPreciseNumber temp = d.pop();
					AmusingPreciseNumber temp2 = d.pop();
					temp2.subtract(temp);
					d.add(temp2);
					// SUB TOP 2 NUMBERS IN THE DEQUE
				} else if (subString.equals("abs")) {

					if (!enoughElements("abs", d)) {
						System.out.println("illegal expression");
						failed = true;
						break;
					}
					AmusingPreciseNumber temp = d.pop();
					temp.abs();
					d.add(temp);
					// TAKE THE ABS OF THE TOP NUMBER IN THE DEQUE
				} else if (subString.equals("neg")) {

					if (!enoughElements("neg", d)) {
						System.out.println("illegal expression");
						failed = true;
						break;
					}

					AmusingPreciseNumber temp = d.pop();
					temp.negate();
					d.add(temp);
					// MULTIPLY THE TOP NUMBER BY -1
				} else {
					System.out.println(subString + " is unsupported \n");
					failed = true;
					break;
				}
			}
		}

		// the only object in the deque at the end should be the return value
		if (!failed) {
			System.out.println(d.peek());
		}
	}

	/**
	 * tells if there is enough room in the deque for the given opperation
	 * 
	 * @return
	 */
	private static boolean enoughElements(String operation, Deque d) {
		if (operation.equals("+") || operation.contentEquals("-")) {
			if (d.size() >= 2) {
				return true;
			} else {
				return false;
			}
		} else {// else must be abs or neg
			if (d.size() >= 1) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 
	 * @param str
	 * @return true if the number is numeric
	 */
	private static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
