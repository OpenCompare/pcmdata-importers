package data_off;

import java.io.IOException;

public class OFFPCMCreator {
	
	public static void mkPCMFromCSV(String filename) throws IOException{
		
		PCMInterpreter.CSVToPCM(filename);
	}
}
