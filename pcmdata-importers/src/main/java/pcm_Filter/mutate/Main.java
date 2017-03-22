package pcm_Filter.mutate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.opencompare.api.java.*;
import org.opencompare.api.java.impl.io.KMFJSONExporter;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

public class Main {

	public static String inputpath = ""; // give path with argv
	public static String outputpath = ""; // give path with argv

	public static void main(String[] args) throws IOException {

		// inputpath = args[0];
		inputpath = "input-pcm/";
		outputpath = "output-pcm/";

		// MongoClient mongoClient = new MongoClient();
		try {
			// MongoCollection<Document> collection =
			// mongoClient.getDatabase("OpenCompare").getCollection("pcms");

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

						pcmic = new PCMInfoContainer(pcm);
						pcmContainer.setPcm(pcmic.getMutedPcm());

						KMFJSONExporter pcmExporter = new KMFJSONExporter();
						String pcmString = pcmExporter.export(pcmContainer);

						Path p = Paths.get(outputpath + "muted_" + filePath.getFileName());
						try {
							Files.write(p, pcmString.getBytes());
						} catch (Exception e) {
							e.printStackTrace();
						}
						System.out.println("> PCM exported to " + p);

					}

				}

			});
			// mongoClient.close();
			paths.close();

		} catch (Exception e) {
			// mongoClient.close();
			e.printStackTrace();
		}
	}
}
