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
	
	private String stID = "";
	
	public MesoInherit(MesoStation stn) throws IOException {
		this.stID = stn.getStID();
		readFile();
	}
	
	@Override
	int[] calAverage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	char letterAverage() {
		// TODO Auto-generated method stub
		return 0;
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
