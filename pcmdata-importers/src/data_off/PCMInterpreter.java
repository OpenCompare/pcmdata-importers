package data_off;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.impl.io.KMFJSONExporter;


public class PCMInterpreter {
	
	public static void CSVToPCM(String filename) throws IOException{
		_serializeToPCMJSON(mkPCMInterpreted(filename), filename.replace(".csv", ".json"));
	}

	private static void _serializeToPCMJSON(PCMContainer pcmContainer, String jsonFileName) throws IOException {
		KMFJSONExporter exporter = new KMFJSONExporter();
		String json = exporter.export(pcmContainer);
		// Write modified PCM
		writeToFile("" + jsonFileName, json);
	}
	

	public static PCMContainer mkPCMInterpreted(String csvFileName) throws IOException {
	
		List<PCMContainer> pcms = PCMUtil.loadCSV(csvFileName); // already interpreted with CellContentInterpreter
		PCMContainer pcmContainer = pcms.get(0);
		
/*		not necessary!
		PCM pcm = pcmContainer.getPcm();


		PCMFactory factory = new PCMFactoryImpl();
		CellContentInterpreter interpreter = new CellContentInterpreter(factory);

		pcm.normalize(factory);
		interpreter.interpretCells(pcm);
*//*		System.out.println(PCMUtil.getFeature(
				pcmContainer.getPcm(), "id")
				.getName()); // TODO NULL POINTER EXCEPTION
*/		return pcmContainer;

	}

	public static void writeToFile(String path, String content) throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
		writer.write(content);
		writer.close();
	}

}
