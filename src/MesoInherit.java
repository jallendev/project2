import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MesoInherit extends MesoAbstract{
    /**
     * The beginning capacity of the stations array
     */
	private final int INITIAL_STATION_SIZE = 10;
	/**
	 * The filepath for the Mesonet.txt file. May need to be changed depending on relative working directory.
	 */
	private final String FILENAME = "Mesonet.txt";
	/**
	 * The fileReader looks for this column header to begin reading in station IDs.
	 */
	private final String STATION_COLUMN_HEADER = "STID";
	/**
	 * An array to hold station IDs read from the Mesonet.txt file. Initialized with a value of 10.
	 */
	private String[] stations = new String[INITIAL_STATION_SIZE];
	/**
	 * The stID String holds the code for the station given by the Driver class to use for execution.
	 */
	private String stID = "";
	
	/**
	 * Constructor for MesoInherit. Sets the stID and reads the Mesonet.txt file with the use of helper methods.
	 * 
	 * @param stn The station given by the Driver class to use for execution
	 * @throws IOException If readFile() fails to read Mesonet.txt.
	 */
	public MesoInherit(MesoStation stn) throws IOException {
		this.stID = stn.getStID();
		readFile();
	}
	
	/**
	 * Calculates the average ASCII value of the given station ID.
	 * 
	 * @return Returns an int array with the ceil of the average in [0], the floor of the average in [1], and the rounded value of the average in [2]
	 */
	@Override
	public int[] calAverage() {
		int[] avgs = new int[3];
		double avg = 0;
		int sum = 0;
		
		char[] stnChars = stID.toCharArray();
		for (int index = 0; index < stnChars.length; ++index) {
			sum += (int)stnChars[index];
		}
		avg = (double)sum / stnChars.length;
		
		avgs[0] = (int) Math.ceil(avg);
		avgs[1] = (int) Math.floor(avg);
		avgs[2] = (int) Math.round(avg);
		
		return avgs;
	}
	
	/**
	 * Converts the returned average of calAverage() to its char representation.
	 * 
	 * @return A char representation of the ASCII average found in calAverage()
	 */
	@Override
	public char letterAverage() {
		return (char)calAverage()[2];
	}
	
	/**
	 * Finds the index of the station ID in the Mesonet.txt file.
	 * 
	 * @return An int representing the index or "row" of the station ID in the Mesonet.txt file.
	 */
	public int getIndex() {
		int index = 0;
		for (int count = 0; count < stations.length; ++count) {
			if (stations[count].equals(stID)) {
				index = count;
			}
		}
		return index + 1;
	}
	
	/**
	 * Find the four stations adjacent to the station at the given index
	 * 
	 * @param index The index to use for adjacent stations.
	 * @return The four stations adjacent to the station at the given index given in a String[]
	 */
	public String[] getAvgStations(int index) {
		String[] avgStations = new String[4];
		avgStations[0] = stations[index - 3];
		avgStations[1] = stations[index - 2];
		avgStations[2] = stations[index];
		avgStations[3] = stations[index + 1];
		return avgStations;
	}
	
	/**
	 * Reads the stations array to find the number of stations beginning with the given letter
	 * 
	 * @param letter The letter to search
	 * @return The int number of stations in Mesonet.txt beginning with the given letter
	 */
	public int getNoStationsForLetter(char letter) {
		int counter = 0;
		for (int index = 0; index < stations.length; ++index) {
			if (stations[index].charAt(0) == letter) {
				++counter;
			}
		}
		return counter;
	}
	
	/**
	 * Creates a list with all stations beginning with the given letter.
	 * 
	 * @param letter The letter to search
	 * @return The String[] list of all stations beginning with the given letter
	 */
	public String[] getStationsForLetter(char letter) {
		String[] retStations = new String[getNoStationsForLetter(letter)];
		int count = 0;
		for (int index = 0; index < stations.length; ++index) {
			if (stations[index].charAt(0) == letter) {
				retStations[count] = stations[index];
				++count;
			}
		}
		return retStations;
	}

	/**
	 * Reads the Mesonet.txt file and inputs the results into the stations array. Helper method for the default constructor.
	 * 
	 * @throws IOException if the Mesonet.txt filename is incorrect and the file cannot be found. See the const String FILENAME.
	 */
	private void readFile() throws IOException{
	    //catch IOException if one occurs and print error message
		try {
		    //Declare necessary variables and objects
			BufferedReader fileReader = new BufferedReader(new FileReader(FILENAME));
			String line = new String("");
			String station = new String("");
			Scanner stationFinder;
			boolean startReading = false;
			int index = 0;
			
			//read the file
			line = fileReader.readLine();
			while(line != null) {
			    //get only the first token on each line
				stationFinder = new Scanner(line);
				station = stationFinder.next();
				//triggers upon column header being found by below conditional statement
				if (startReading) {
					if (index >= stations.length) {
						stations = expandStations();
					}
					stations[index] = station;
					++index;
				}
				//test for beginning of station IDs using const station header
				if (station.equals(STATION_COLUMN_HEADER)){
					startReading = true;
				}
				line = fileReader.readLine();
			}
			fileReader.close();
		}
		//An IOException will most likely occur in the case of the FILENAME variable being initialized with an incorrect path.
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Expands the size of the stations array when it becomes full. This helps the fileReader method in filling the array.
	 * 
	 * @return a String[] array containing the values of the original stations array + 10 new null spaces
	 */
	private String[] expandStations() {
		String[] tempStations = new String[stations.length + 10];
		
		//transfer data to temp array
		for (int index = 0; index < stations.length; ++index) {
			tempStations[index] = stations[index];
		}
		
		//resize stations when transfer is complete
		stations = new String[stations.length + 10];
		return tempStations;
	}
}
