import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.Test;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.PCMFactory;
import org.opencompare.api.java.extractor.CellContentInterpreter;
import org.opencompare.api.java.impl.PCMFactoryImpl;
import org.opencompare.api.java.impl.io.KMFJSONExporter;
import org.opencompare.api.java.io.CSVExporter;

public class PCMInterpreter {

	@Test
	public void testCSV1() throws Exception {
		String csvFileName = "input-csv/Comparison_of_HTML_editors.csv";

		PCMContainer pcmContainer = mkPCMInterpreted(csvFileName);

		KMFJSONExporter exporter = new KMFJSONExporter();
		CSVExporter csvExporter = new CSVExporter();
		String json = exporter.export(pcmContainer);
		String csv = csvExporter.export(pcmContainer);

		// Write modified PCM
		writeToFile("" + csvFileName + ".json", json);
		writeToFile("" + csvFileName + ".csv", csv);
	}

	public PCMContainer mkPCMInterpreted(String csvFileName) throws IOException {

		// uninterpreted
		List<PCMContainer> pcms = PCMUtil.loadCSV(csvFileName);
		PCMContainer pcmContainer = pcms.get(0);
		PCM pcm = pcmContainer.getPcm();

		PCMFactory factory = new PCMFactoryImpl();
		CellContentInterpreter interpreter = new CellContentInterpreter(factory);

		pcm.normalize(factory);
		interpreter.interpretCells(pcm);

		return pcmContainer;

	}

	public void writeToFile(String path, String content) throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
		writer.write(content);
		writer.close();
	}

}
