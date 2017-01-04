package data_off;

import java.io.IOException;

public class OFFPCMCreator {
	
	public static void mkPCMFromCSV(String filename) throws IOException{
		
		System.out.println("Converting CSV file "+filename+" to PCM");
		PCMInterpreter.CSVToPCM(filename);
	}
}
