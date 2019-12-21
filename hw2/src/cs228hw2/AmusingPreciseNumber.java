package cs228hw2;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

import cs228hw2.AmusingLinkedList.AmusingListIterator;

/**
 * 
 * @author Demetrius Christou
 *
 */
public class AmusingPreciseNumber {

	private boolean positive;

	// these will hold the number of digits on either side of the decimal
	private int leftOfDecimal;
	private int rightOfDecimal;

	public AmusingLinkedList<Integer> data = new AmusingLinkedList<>();

	/**
	 * 
	 * @param numb
	 * 
	 *             Create an AmusingPreciseNumber from an int type.
	 */
	public AmusingPreciseNumber(int numb) {
		int numbDigits = 0;
		if (numb < 0) {
			positive = false;
			numb *= -1;
		} else {
			positive = true;
		}

		// can find out how many digits a number has by rounding up the log 10 of it
		if (numb != 0) {
			numbDigits = (int) Math.log10(numb) + 1;
		}
		// while the number is more than one digit take the last digit and add it to the
		// start of this list
		while (numb > 9) {
			data.add((int) (numb / Math.pow(10, numbDigits - 1)));
			numbDigits--;
			numb %= Math.pow(10, numbDigits);
		}
		data.add(numb);
		// ints dont have decimal places so all of the number is left of the point
		leftOfDecimal = data.size();
	}

	/**
	 * creates a APN from a string
	 */
	public AmusingPreciseNumber(String numb) {

		if (numb == "") {
			return;
		}

		int index = 0;

		if (numb.charAt(index) == '-') {
			positive = false;
			index++;

		} else {
			positive = true;
		}

		// while on the left side of the decimal
		while (index < numb.length() && numb.charAt(index) != '.') {
			int temp = Character.getNumericValue(numb.charAt(index));
			data.add(temp);
			leftOfDecimal++;
			index++;
		}

		if (index < numb.length()) {
			index++;
		}

		while (index < numb.length()) {
			int temp = Character.getNumericValue(numb.charAt(index));
			data.add(temp);
			rightOfDecimal++;
			index++;
		}
	}

	/**
	 * creates a APN from the reader r
	 * 
	 */
	public AmusingPreciseNumber(Reader r) {

		int temp = -1;

		try {
			temp = r.read();
			if ((char) temp == '-') {
				positive = false;
				temp = r.read();
			} else if ((char) temp == '+') {
				positive = true;
				temp = r.read();
			} else {
				positive = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (temp != -1 && temp != 46) {// 46 is the ascii code for .
			data.add(Character.getNumericValue(temp));
			leftOfDecimal++;

			try {
				temp = r.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if ((char) temp == '.') {
			try {
				temp = r.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while (temp != -1) {
			data.add(Character.getNumericValue(temp));
			rightOfDecimal++;

			try {
				temp = r.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * creates a deep copy of the APN given
	 * 
	 * @param numb
	 */
	public AmusingPreciseNumber(AmusingPreciseNumber numb) {
		this.positive = numb.positive;
		this.leftOfDecimal = numb.leftOfDecimal;
		this.rightOfDecimal = numb.rightOfDecimal;

		Iterator iter = numb.data.listIterator();

		while (iter.hasNext()) {
			data.add(iter.next());
		}

	}

	public void add(AmusingPreciseNumber numb) {
		AmusingPreciseNumber temp = add(this, numb);
		this.data = temp.data;
		this.leftOfDecimal = temp.leftOfDecimal;
		this.rightOfDecimal = temp.rightOfDecimal;
		this.positive = temp.positive;
	}

	public void subtract(AmusingPreciseNumber numb) {
		AmusingPreciseNumber temp = subtract(this, numb);
		this.data = temp.data;
		this.leftOfDecimal = temp.leftOfDecimal;
		this.rightOfDecimal = temp.rightOfDecimal;
		this.positive = temp.positive;

	}

	public void negate() {
		// negate multiplies by -1 so you just do the inverse of what you
		// currently have in positive
		positive = !positive;
	}

	public void abs() {
		positive = true;
	}

	public static AmusingPreciseNumber add(AmusingPreciseNumber numb1, AmusingPreciseNumber numb2) {

		// if numb1 is positive and numb2 is negative
		if (numb1.positive && !numb2.positive) {
			numb2.abs();
			return subtract(numb1, numb2);
		} else if (!numb1.positive && numb2.positive) {// numb1 is negative and numb2 is positive
			numb1.abs();
			return subtract(numb2, numb1);
		}
		// check math for negatives

		AmusingPreciseNumber tempList = new AmusingPreciseNumber("");
		// if the program made it this far then both numb1 and numb2
		// have the same sign
		tempList.positive = numb1.positive;
		int carry = 0;
		int remainder = 0;

		// need to make the two numbers the same size with padded 0s
		addPadding(numb1, numb2);

		tempList.leftOfDecimal = numb1.leftOfDecimal;
		tempList.rightOfDecimal = numb1.rightOfDecimal;

		// start from the end of the list
		AmusingListIterator dataIt = (AmusingListIterator) numb1.data.listIterator(numb1.data.size() - 1);
		AmusingListIterator numbIt = (AmusingListIterator) numb2.data.listIterator(numb2.data.size() - 1);

		while (dataIt.hasPrevious()) {
			int t1 = (Integer) dataIt.previous();
			int t2 = (Integer) numbIt.previous();

			int temp = t1 + t2 + carry;
			remainder = temp % 10;
			carry = temp / 10;
			tempList.data.add(0, remainder);
		}
		// adds the final carry to the first postion
		if (carry != 0) {
			// carry should always end up on the left side of the decimal
			tempList.data.add(0, carry);
			tempList.leftOfDecimal++;
		}

		return tempList;
	}

	public static AmusingPreciseNumber subtract(AmusingPreciseNumber numb1, AmusingPreciseNumber numb2) {

		AmusingPreciseNumber tempList = new AmusingPreciseNumber("");
		// assume the number is positive until it isnt
		tempList.positive = true;
		AmusingPreciseNumber temp1 = new AmusingPreciseNumber(numb1);
		AmusingPreciseNumber temp2 = new AmusingPreciseNumber(numb2);

		// check signs
		if (!temp1.positive && temp2.positive) {
			// -1 - 1 is the same as -1 + -1
			temp2.negate();
			return add(temp1, temp2);
		} else if (temp1.positive && !temp2.positive) {
			// 1 - -1 is the same as 1 + 1
			temp2.abs();
			return add(temp1, temp2);
		} else if (!temp1.positive && !temp2.positive) {
			// -1 - -1 is the same as -1 + 1
			temp2.abs();
			return add(temp1, temp2);
		}

		int index = 0;

		// need to make the two numbers the same size with padded 0s
		addPadding(temp1, temp2);

		// start from the end of the list
		AmusingListIterator dataIt = (AmusingListIterator) temp1.data.listIterator(numb1.data.size() - 1);
		AmusingListIterator numbIt = (AmusingListIterator) temp2.data.listIterator(numb2.data.size() - 1);

		int t1 = 0;
		int t2 = 0;
		while (dataIt.hasPrevious()) {
			int temp;
			t1 = (Integer) dataIt.previous();
			t2 = (Integer) numbIt.previous();

			if (t1 < t2) {
				if (getCarry(index, temp1)) {
					temp = t1 - t2 + 10;
					tempList.data.add(0, temp);
				} else { // if there is no carry just do the subtraction the opposite way and make the
							// result negative
					AmusingPreciseNumber sub = subtract(numb2, numb1);

					tempList.data = sub.data;
					tempList.positive = false;
					tempList.leftOfDecimal = sub.leftOfDecimal;
					tempList.rightOfDecimal = sub.rightOfDecimal;

					return tempList;
				}
			} else {
				temp = t1 - t2;
				tempList.data.add(0, temp);
			}

			index++;

		}

		tempList.leftOfDecimal = temp1.leftOfDecimal;
		tempList.rightOfDecimal = temp1.rightOfDecimal;
		return tempList;

	}

	public static AmusingPreciseNumber negate(AmusingPreciseNumber numb) {
		AmusingPreciseNumber temp = new AmusingPreciseNumber(numb);
		temp.positive = false;
		return temp;
	}

	public static AmusingPreciseNumber abs(AmusingPreciseNumber numb) {
		AmusingPreciseNumber temp = new AmusingPreciseNumber(numb);
		temp.positive = true;
		return temp;
	}

	/**
	 * 
	 * @param numb1 first number to change
	 * @param numb2 second number to change
	 * 
	 *              adds 0s to both ends of the numbers so that they have the same
	 *              length
	 * 
	 */
	private static void addPadding(AmusingPreciseNumber numb1, AmusingPreciseNumber numb2) {
		if (numb1.leftOfDecimal < numb2.leftOfDecimal) {
			while (numb1.leftOfDecimal < numb2.leftOfDecimal) {
				numb1.data.add(0, 0);
				numb1.leftOfDecimal++;
			}
		} else {
			while (numb2.leftOfDecimal < numb1.leftOfDecimal) {
				numb2.data.add(0, 0);
				numb2.leftOfDecimal++;
			}
		}

		if (numb1.rightOfDecimal < numb2.rightOfDecimal) {
			while (numb1.rightOfDecimal < numb2.rightOfDecimal) {
				numb1.data.add(0);
				numb1.rightOfDecimal++;
			}
		} else {
			while (numb2.rightOfDecimal < numb1.rightOfDecimal) {
				numb2.data.add(0);
				numb2.rightOfDecimal++;
			}
		}

	}

	/**
	 * 
	 * @return true if there is something to carry while also subtracting 1 from
	 *         whatever you carried from
	 */
	private static boolean getCarry(int start, AmusingPreciseNumber list) {

		start = list.data.size() - start - 1;
		AmusingListIterator all = (AmusingListIterator) list.data.listIterator(start);

		// if this is the last index hit it will not have
		if (start - 1 < 0) {

			return false;
		}

		while (all.hasPrevious()) {
			all.previous();
			int cur = (Integer) all.previous();

			if (cur > 0) {
				cur--;
				all.set(cur);

				return true;
			} else {

				return false;
			}
		}
		return true;
	}

	/**
	 * removes the unnecessary zeros at the ends of the number
	 * 
	 * @param numb the AmusingPreciseNumber you want to remove padded zeros from
	 */
	// TODO fix this keeps throwing null pointers
	private static void removePadding(AmusingPreciseNumber numb) {

		AmusingPreciseNumber tempList = new AmusingPreciseNumber("");

		if (numb.leftOfDecimal == 0 && numb.rightOfDecimal == 0
				|| numb.data.size() != numb.rightOfDecimal + numb.leftOfDecimal) {
			return;
		}

		// remove front padding
		AmusingListIterator front = (AmusingListIterator) numb.data.listIterator();

		int temp = (Integer) front.next();
		int index = 0;

		// go while on the left side of decimal and is just a 0
		while (index < numb.data.size() && index < numb.leftOfDecimal) {

			if (temp != 0) {
				tempList.data.add(temp);
				tempList.leftOfDecimal++;

			}

			index++;
			temp = (Integer) front.next();
		}

		// remove back padding
		index = 0;
		AmusingListIterator back = (AmusingListIterator) numb.data.listIterator(numb.data.size() - 1);
		temp = (Integer) back.previous();

		while (index >= 0 && index < numb.rightOfDecimal && temp == 0) {

			if (temp != 0) {
				tempList.data.add(temp);
				tempList.rightOfDecimal++;
			}

			index++;
			temp = (Integer) back.previous();
		}

		numb.data = tempList.data;
		numb.leftOfDecimal = tempList.leftOfDecimal;
		numb.rightOfDecimal = tempList.rightOfDecimal;
	}

	@Override
	public String toString() {
		String ret = "";

		// makes sure this number is in its simplest form.
		// removePadding(this);

		if (this.leftOfDecimal == 0 && this.rightOfDecimal == 0
				|| this.data.size() != this.leftOfDecimal + this.rightOfDecimal) {
			return ret;
		}

		AmusingListIterator all = (AmusingListIterator) this.data.listIterator();

		if (positive == false) {
			ret += "-";
		}

		for (int i = 0; i < this.leftOfDecimal; i++) {

			ret += all.next();
		}
		if (this.rightOfDecimal > 0) {
			ret += ".";
		}
		for (int i = 0; i < this.rightOfDecimal; i++) {
			ret += all.next();
		}
		return ret;
	}

}
