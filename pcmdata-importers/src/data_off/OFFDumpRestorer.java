package data_off;

import java.io.IOException;

public class OFFDumpRestorer {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("Restoring...");
		String[] command = {"cmd.exe", "mongorestore /home/mael/workspace/dump"};
		Process p = Runtime.getRuntime().exec(command);
		p.waitFor();
		System.out.println("Done restoring.");
	}

}
