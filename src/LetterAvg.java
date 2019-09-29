import java.io.IOException;

public class LetterAvg {
	private char letter = 0;
	
	public LetterAvg(char letter) {
		this.letter = letter;
	}
	
	public int numberOfStationWithLetterAvg() throws IOException {
		return new MesoInherit(new MesoStation("NRMN")).getNoStationsForLetter(letter);
	}
	
	@Override
	public String toString() {
		String output = "They are:\n";
		String[] stations = new String[0];
		try {
			stations = new MesoInherit(new MesoStation("NRMN")).getStationsForLetter(letter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int index = 0; index < stations.length; ++index) {
			output += stations[index] + "\n";
		}
		return output;
	}
}
