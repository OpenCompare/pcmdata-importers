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

import data_off.PCMUtil;

public class Main {

	public static String inputpath = ""; // give path with argv
	public static String outputpath = ""; // give path with argv

	public static void main(String[] args) throws IOException {

		
		// TODO : le pcm mutate est bien différent du pcm de base, mais les changements ne sont pas pris en compte. A travailler !
		
		// inputpath = args[0];
		inputpath = "input-pcm-test/";
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

						try {
							pcmic = new PCMInfoContainer(pcm);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (pcmic != null) {
							if (pcmic.isSameSizePcm()) {
								System.out.println("> PCM muted is the same");
							} else {
								pcmContainer.setPcm(pcmic.getMutedPcm());
								KMFJSONExporter pcmExporter = new KMFJSONExporter();
								String pcmString = pcmExporter.export(pcmContainer);
								
								Path p = Paths.get(outputpath + "muted_" ) ;//filePath.getFileName());
								try {
									Files.write(p, pcmString.getBytes());
								} catch (Exception e) {
									e.printStackTrace();
								}
								System.out.println("> PCM exported to " + p);
							}
						}
						else {
							System.out.println("> PCM corrompu");
						}
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
