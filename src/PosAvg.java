import java.io.IOException;

public class PosAvg {
	private String stID = "";
	
	private int index = 0;
	
	private String[] avgStations = new String[4];
	
	public PosAvg(String stID) throws IOException {
		//get data from MesoInherit helper methods
		this.stID = stID;
		MesoInherit temp = new MesoInherit(new MesoStation(this.stID));
		this.index = temp.getIndex();
		this.avgStations = temp.getAvgStations(this.index);
	}
	
	//two methods to return data in appropriate format to driver
	public int indexOfStation() {
		return this.index;	
	}
	@Override
	public String toString() {
		return "This index is average of " + avgStations[1] + " and " + avgStations[2] + ", " + avgStations[0] + " and " + avgStations[3] + ", and so on.";
	}
}
