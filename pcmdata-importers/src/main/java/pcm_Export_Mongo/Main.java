package pcm_Export_Mongo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

//import org.junit.Test;
import org.opencompare.api.java.*;
import org.opencompare.api.java.impl.io.KMFJSONExporter;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

import com.opencsv.CSVWriter;

public class Main {

	public static String inputpath = ""; // give path with argv

	public static void main(String[] args) throws IOException {

		inputpath = args[0];

		Stream<Path> paths = Files.walk(Paths.get(inputpath));

		paths.forEach(filePath -> {
			if (Files.isRegularFile(filePath) && filePath.toString().endsWith(".pcm")) {
				System.out.println("> PCM read from " + filePath);

				File pcmFile = new File(filePath.toString());

				PCMLoader loader = new KMFJSONLoader();
				List<PCMContainer> pcmContainers = null;
				try {
					pcmContainers = loader.load(pcmFile);
				} catch (Exception e) {
					e.printStackTrace();
				}

				for (PCMContainer pcmContainer : pcmContainers) {
					// Get the PCM
					PCM pcm = pcmContainer.getPcm();
					PCMInfoContainer pcmic = null;

					try {
						pcmic = new PCMInfoContainer(pcm);
					} catch (Exception e) {
					}

					if (pcmic != null || pcmic.isProductChartable()) {

						KMFJSONExporter pcmExporter = new KMFJSONExporter();
						String pcmString = pcmExporter.export(pcmContainer);

						// TODO
						// Export to mongoDB database

						/*
						 * Path p = Paths.get(inputpath + "/" +
						 * filePath.getFileName()); try { Files.write(p,
						 * pcmString.getBytes()); } catch (Exception e) {
						 * e.printStackTrace(); }
						 */
						System.out.println("> PCM exported to Database");

					}

				}

			}

		});

		paths.close();
	}

}
