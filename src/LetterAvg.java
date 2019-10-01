import java.io.IOException;

public class LetterAvg {
	private char letter = 0;
	
	public LetterAvg(char letter) {
		this.letter = letter;
	}
	
	public int numberOfStationWithLetterAvg() throws IOException {
		//return the data from the MesoInherit helper method.
		//"NRMN" is used because it is not necessary to know the stID for this operation. The stations array in MesoInherit need only be searched.
		return new MesoInherit(new MesoStation("NRMN")).getNoStationsForLetter(letter);
	}
	
	@Override
	public String toString() {
		//get all stations beginning with the letter, format, and return.
		String output = "\nThey are:";
		String[] stations = new String[0];
		
		try {
			//"NRMN" is used because it is not necessary to know the stID for this operation. The stations array in MesoInherit need only be searched.
			stations = new MesoInherit(new MesoStation("NRMN")).getStationsForLetter(letter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int index = 0; index < stations.length; ++index) {
			output += "\n" + stations[index];
		}
		return output;
	}
}
