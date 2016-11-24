package pcm_Filter;

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
import org.opencompare.api.java.io.CSVLoader;
import org.opencompare.api.java.io.PCMLoader;

import pcm_Filter.pcm_predicate.PCMCompositeFilter;
import pcm_Filter.pcm_predicate.PCMPredicateFilter;
import pcm_Filter.pcm_predicate.PCMPredicateMinColumnProduct;
import pcm_Filter.pcm_predicate.PCMPredicateMinRowProduct;

public class Main {

	public static void main(String[] args) throws IOException {

		Stream<Path> paths = Files.walk(Paths.get("input-pcm"));  //("../../input-model")); //for non testings inputs

		paths.forEach(filePath -> {
			if (Files.isRegularFile(filePath)
					&& filePath.toString().endsWith(".pcm")) {
				System.out.println("> PCM imported from " + filePath);

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

					PCMInfoContainer pcmic = new PCMInfoContainer(pcm);

					PCMCompositeFilter pFilter = new PCMCompositeFilter(); //for using multiple filters
					pFilter.addFilter(new PCMPredicateMinRowProduct());
					pFilter.addFilter(new PCMPredicateMinColumnProduct());
					
					
					Boolean thispcmisgood = pFilter.isSatisfiable(pcmic);
					
					//Boolean thispcmisgood = false;
					
					// now determine if the pcm is good

					if (thispcmisgood) {
						KMFJSONExporter pcmExporter = new KMFJSONExporter();
						String pcmString = pcmExporter.export(pcmContainer);

						Path p = Paths.get("output-pcm/" //"../../output-model" //for non testings outputs
								+ filePath.getFileName());
						try {
							Files.write(p, pcmString.getBytes());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("> PCM exported to " + p);

					}
				}
			}
		});

		paths.close();

	}

}
