package pcm_MutateFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.stream.Stream;

import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.impl.io.KMFJSONExporter;
import org.opencompare.api.java.impl.io.KMFJSONLoader;

import org.opencompare.api.java.io.PCMLoader;

import pcm_Filter.PCMInfoContainer;
import pcm_Filter.pcm_predicate.PCMCompositeFilter;
import pcm_Filter.pcm_predicate.PCMPredicateMinColumnProduct;
import pcm_Filter.pcm_predicate.PCMPredicateMinRowProduct;

public class Main {

	public static final boolean writefile = false;

	public static final String pcm_path = "";

	// "input-pcm" testing inputs
	// "../../input-model" prod inputs
	// "D:/Windows/input-pcm"

	// "output-pcm/" //test outputs
	// "../../output-model/" //prod outputs
	// "output-pcm-JS/" //PCM prod for JS Team

	public static void main(String[] args) throws IOException {

		long startTime = System.nanoTime();

		Stream<Path> paths = Files.walk(Paths.get(pcm_path));

		paths.forEach(filePath -> {
			if (Files.isRegularFile(filePath) && filePath.toString().endsWith(".pcm")) {
				System.out.println("> PCM imported from " + filePath);

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

					/*
					
						TO DO : Filtre mutant.
						
						Lignes
						
						Colones
					
					*/

					if (writefile) {
						KMFJSONExporter pcmExporter = new KMFJSONExporter();
						String pcmString = pcmExporter.export(pcmContainer);

						Path p = Paths.get(pcm_path + filePath.getFileName());
						try {
							Files.write(p, pcmString.getBytes());
						} catch (Exception e) {
							e.printStackTrace();
						}
						System.out.println("> PCM exported to " + p);
					}
				}
			}
		});

		paths.close();

		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) / (1000000) + " ms");
		System.out.println("Took " + (endTime - startTime) / (1000000000) + " s");

	}

}
