package pcm_Metric;

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
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

import com.opencsv.CSVWriter;

public class PCMInfoTest {

	public static final String inputpath = "metrics";

	// @Test
	public static void main(String[] args) throws IOException {

		Stream<Path> paths = Files.walk(Paths.get(inputpath));

		File outputcsv = new File(inputpath + "/metrics.csv");

		try {
			outputcsv.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (Writer writer = new BufferedWriter(new FileWriter(outputcsv))) {
			CSVWriter csvwriter = new CSVWriter(writer, ';', '\"');
			String[] header = { "Name", "cells" };
			csvwriter.writeNext(header);// writing the header

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

						PCMInfoContainer pcmic = new PCMInfoContainer(pcm);

						// create csv file

						String[] str = { filePath.toString(), pcmic.nbCells().toString() };
						System.out.println(str[0]);
						System.out.println(str[1]);

						csvwriter.writeNext(str);

					}

				}

			});

			csvwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		paths.close();

		assert (true);

		/*
		 * for // {
		 * 
		 * PCM pcm = null;
		 * 
		 * PCMInfoContainer pcmInfo = new PCMInfoContainer(pcm);
		 * 
		 * pcmInfo.nbCells(); pcmInfo.nbEmptyCells(); // ...
		 * 
		 * // create CSV file
		 */

	}
}
