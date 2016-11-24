import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.junit.Test;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.extractor.CellContentInterpreter;
import org.opencompare.api.java.impl.PCMFactoryImpl;
import org.opencompare.api.java.io.CSVLoader;
import org.opencompare.api.java.io.PCMDirection;

import data_omdb.OMDBMediaType;
import data_omdb.OmdbtoProduct;


public class OMDBTest {
	
	@Test 
	public void testOMDBFilms1() throws IOException, JSONException {
		String m = new OmdbtoProduct().mkCSV(OMDBMediaType.MOVIE);
		
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter 
				(new FileOutputStream("output/data_omdb_film.csv"), 
						StandardCharsets.UTF_8));
		writer.write(m);
		writer.close();
	}
	
	@Test 
	public void testOMDBSeries1() throws IOException, JSONException {
		String m = new OmdbtoProduct().mkCSV(OMDBMediaType.SERIES);
		
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter 
				(new FileOutputStream("output/data_omdb_serie.csv"), 
						StandardCharsets.UTF_8));
		writer.write(m);
		writer.close();
	}
	
	@Test 
	public void testOMDBEpisodes1() throws IOException, JSONException {
		String m = new OmdbtoProduct().mkCSV(OMDBMediaType.EPISODE);
		
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter 
				(new FileOutputStream("output/data_omdb_episode.csv"), 
						StandardCharsets.UTF_8));
		writer.write(m);
		writer.close();
	}
	
	@Test 
	public void test1() throws IOException {
			
			// create the CSV
			//new OmdbtoProduct().mk... 
		
			// read the CSV file
			File file = new File("output/data_omdb_episode.csv");
			CSVLoader csvL = new CSVLoader(
	                new PCMFactoryImpl(),
	                new CellContentInterpreter(new PCMFactoryImpl()), 
	                PCMDirection.PRODUCTS_AS_LINES);
	        List<PCMContainer> pcmC = csvL.load(file);
	        PCM pcm = pcmC.get(0).getPcm();
			
			// check some properties of "pcm"
			assertNotNull(pcm);			
			assertEquals(93, pcm.getProducts().size());
	}
	
	
	@Test 
	public void test3() throws IOException, JSONException {
		System.err.println("" + 
				new OmdbtoProduct().mkCSVs(Arrays.asList(OMDBMediaType.SERIES)));
	}
	
	@Test 
	public void test4() throws IOException, JSONException {
		System.err.println("" + 
				new OmdbtoProduct().mkCSVs(Arrays.asList(OMDBMediaType.MOVIE)));
	}
	
	@Test 
	public void test5() throws IOException, JSONException {
		System.err.println("" + 
				new OmdbtoProduct().mkCSVs(Arrays.asList(OMDBMediaType.EPISODE)));
	}
	
	@Test 
	public void test6() throws IOException, JSONException {
		Map<OMDBMediaType, String> csvs = new OmdbtoProduct().mkCSVs(Arrays.asList(
				OMDBMediaType.SERIES, 
				OMDBMediaType.EPISODE,
				OMDBMediaType.MOVIE
				));
		
		CSVLoader csvL = new CSVLoader(
                new PCMFactoryImpl(),
                new CellContentInterpreter(new PCMFactoryImpl()), 
                PCMDirection.PRODUCTS_AS_LINES);
        List<PCMContainer> pcmC = csvL.load(csvs.get(OMDBMediaType.MOVIE));
        PCM pcm = pcmC.get(0).getPcm();
        assertNotNull(pcm);
        assertEquals(33, pcm.getProducts().size());
        
        CSVLoader csvL2 = new CSVLoader(
                new PCMFactoryImpl(),
                new CellContentInterpreter(new PCMFactoryImpl()), 
                PCMDirection.PRODUCTS_AS_LINES);
        List<PCMContainer> pcmC2 = csvL2.load(csvs.get(OMDBMediaType.EPISODE));
        PCM pcm2 = pcmC2.get(0).getPcm();
        assertNotNull(pcm2);
        assertEquals(44, pcm2.getProducts().size());
        
				
		
	}

}
