package cs228hw1.stats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class StatisticsShell<T extends Number> implements Statistics<T>{
	
	//
	private ArrayList<StatObject<T>> CurrentData = new ArrayList<>();
	
	/**
	 * holds the data from the file
	 */
	private ArrayList<T> ActiveData;
	
	private IParser<T> parser;
	

	
	public StatisticsShell(IParser<T> p) {
		parser = p;
	}
	/**
	 * Reads the weather data (of the specified variety) in the specified file into the active data set.
	 * The data in the file is treated as type T. Missing values should be ignored.
	 * @param d The set of data to be read from the file.
	 * @param path The path of the file.
	 * @return Returns true if the file is successfully read and false otherwise.
	 */
	public  boolean ReadFile(String path, DATA d) {
		
		ActiveData = new ArrayList<>();
		
		File file = new File(path);
		
		try(Scanner input = new Scanner(file)) {
			Scanner firstLine = new Scanner(input.nextLine());
			
			//tells what collumn of data we care about
			int collumn = 1;
			int currCollumn = 1;
			
			//since these datas dont 
			if(d.equals(DATA.YR_MO_DA_HR_MN)) {
				collumn = 3;
			} else if(d.equals(DATA.MW_1)) {
				collumn = 13;
			} else if(d.equals(DATA.MW_2)) {
				collumn = 14;
			} else if(d.equals(DATA.MW_3)) {
				collumn = 15;
			} else if(d.equals(DATA.MW_4)) {
				collumn = 16;
			} else if(d.equals(DATA.AW_1)) {
				collumn = 17;
			} else if(d.equals(DATA.AW_2)) {
				collumn = 18;
			} else if(d.equals(DATA.AW_3)) {
				collumn = 19;
			} else if(d.equals(DATA.AW_4)) {
				collumn = 20;
			} else while(!d.toString().equalsIgnoreCase(firstLine.next())) {
				collumn++;
			}

			firstLine.close();
			
			
			while(input.hasNextLine()) {
				
			
				//read through the data types at the start
				
				String sentence = input.nextLine();
				Scanner line = new Scanner(sentence);
				currCollumn = 1;
				
				while(line.hasNext()) {
					
					String number = line.next();
					if(collumn == currCollumn && number.charAt(0) != '*') {
						//will use a parser to change number from string to a Number
						ActiveData.add(parser.parse(number));
						
					}
					currCollumn++;
					
				}
				line.close();
			}
			
			input.close();
			
		} catch(FileNotFoundException e) {
			return false;
		}
		
		return true;
	}

	/**
	 * Adds a StatObject to this Statistics object to the end of the list of StatObjects currently in it.
	 * The data currently stored in this Statistics object will be assigned to the new StatObject.
	 */
	public void AddStatObject(StatObject<T> so) {
		
		so.SetData(ActiveData);		
		CurrentData.add(so);
		
	}

	/**
	 * Adds a StatObject to this Statistics object to the end of the list of StatObjects currently in it.
	 * A subset of the data currently stored in this Statistics object will be assigned to the new StatObject.
	 * @param so The new StatObject to add.
	 * @param first The index of the first piece of data to add to the provided StatObject.
	 * @param last The index of the last piece of data to add to the provided StatObject.
	 */
	public void AddStatObject(StatObject<T> so, int first, int last) {
		ArrayList<T> temp = new ArrayList<>();
		
		if(last >= this.ActiveData.size()) {
			last = this.ActiveData.size() - 1;
		}
		
		for(int i = first; i <= last; i++) {
			temp.add(ActiveData.get(i));
		}

		so.SetData(temp);
		CurrentData.add(so);
		
	}

	/**
	 * Obtains the [i]th StatObject in this Statistics object.
	 * @param i The index to return.
	 * @return The specified StatObject or null if no such index exists.
	 */
	public StatObject<T> GetStatObject(int i) {
		
		if(i < CurrentData.size()) {
			return CurrentData.get(i);
		}
		return null;
	}

	/**
	 * Removes a StatObject from this Statistics object.
	 * @param i The index to remove.
	 * @return Returns the StatObject removed or null if no such index exists.
	 */
	public StatObject<T> RemoveStatObject(int i) {
		StatObject<T> temp;
		if(i < CurrentData.size()) {
			temp = CurrentData.get(i);
			CurrentData.remove(i);
			return temp;
		}
		return null;
	}

	/**
	 * Returns the number of StatObjects currently in this Statistics object.
	 */
	public int Count() {
		
		return CurrentData.size();
	}

	/**
	 * Returns a deep copy of the list containing the current data set used for calculations.
	 */
	public ArrayList<T> GetDataSet() {
		ArrayList<T> temp = new ArrayList<>();
		
		for(int i = 0; i < ActiveData.size(); i++) {
				temp.add(ActiveData.get(i));
		}
		
		return temp;
	}

	/**
	 * Performs each calculation in stored in this Statistics object in order.
	 * @return an ArrayList of results when GetResult is called in every StatObject in order.
	 */
	public ArrayList<ArrayList<Number>> MapCar() {
		ArrayList<ArrayList<Number>> results = new ArrayList<>();
		
		for(int i = 0; i < CurrentData.size(); i++) {
			//CurrentData.get(i).SetData(ActiveData);
			results.add(CurrentData.get(i).GetResult());
		}
		return results;
	}

}
