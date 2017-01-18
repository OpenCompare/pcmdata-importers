package data_rennes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.extractor.CellContentInterpreter;
import org.opencompare.api.java.impl.PCMFactoryImpl;
import org.opencompare.api.java.impl.io.KMFJSONExporter;
import org.opencompare.api.java.io.CSVLoader;
import org.opencompare.api.java.io.PCMDirection;


public class PCMInterpreter {

	public static void main(String[] arg0) throws IOException {
		String filename = "consommation-electrique-par-secteur-dactivite.csv";
		_serializeToPCMJSON(mkPCMInterpreted("input-csv/" + filename), "output-pcm/" + filename.replace(".csv", ".pcm"));
	}

	private static void _serializeToPCMJSON(PCMContainer pcmContainer, String jsonFileName) throws IOException {
		KMFJSONExporter exporter = new KMFJSONExporter();
		String json = exporter.export(pcmContainer);
		// Write modified PCM
		writeToFile(jsonFileName, json);
	}

	public static PCMContainer mkPCMInterpreted(String csvFileName) throws IOException {

		CSVLoader csvL = new CSVLoader(
				new PCMFactoryImpl(),
				new CellContentInterpreter(new PCMFactoryImpl()), 
				';', '"',
				PCMDirection.PRODUCTS_AS_LINES);
		List<PCMContainer> pcmC = csvL.load(new File(csvFileName));
		PCMContainer pcmContainer = pcmC.get(0);

//		System.out.println("--- Products ---");
//		for (AbstractFeature product : pcmContainer.getPcm().getFeatures()) {
//			System.out.println(product.getName());
//		}
		
		return pcmContainer;

	}

	public static void writeToFile(String path, String content) throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
		writer.write(content);
		writer.close();
	}

}
