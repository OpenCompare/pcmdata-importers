package data_off;

import java.io.IOException;

public class Run3 {

	public static void main(String[] args) {

		String temp = "off_output/en_beers.csv";
		try {
			OFFPCMCreator.mkPCMFromCSV(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
