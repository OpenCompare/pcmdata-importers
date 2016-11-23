package wikipediaSelector;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.impl.io.KMFJSONExporter;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.CSVExporter;
import org.opencompare.api.java.io.PCMLoader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		/*
		 * GettingStartedTest test = new GettingStartedTest(); try {
		 * test.testGettingStarted(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		

	               
	               
		Stream<Path> paths = Files.walk(Paths.get("input-pcm"));
		

		paths.forEach(filePath -> {
			if (Files.isRegularFile(filePath)
					&& filePath.toString().endsWith(".pcm")) {
				System.out.println(filePath);

				File pcmFile = new File(filePath.toString());

				PCMLoader loader = new KMFJSONLoader();
				List<PCMContainer> pcmContainers = null;
				try {
					pcmContainers = loader.load(pcmFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (PCMContainer pcmContainer : pcmContainers) {
					// Get the PCM
					PCM pcm = pcmContainer.getPcm();

					Boolean thispcmisgood = true;

					// now determine if the pcm is good

					if (thispcmisgood) {
						KMFJSONExporter pcmExporter = new KMFJSONExporter();
						String pcmString = pcmExporter.export(pcmContainer);

						Path p = Paths.get("output-pcm/"
								+ filePath.getFileName());
						try {
							Files.write(p, pcmString.getBytes());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("PCM exported to " + p);

					}
				}
			}
		});
		
		paths.close();

	}

}
