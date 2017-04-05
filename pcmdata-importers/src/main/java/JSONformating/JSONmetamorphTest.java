package JSONformating;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.impl.io.KMFJSONExporter;

import JSONformating.model.JSONFormat;
import JSONformating.reader.JSONReader;
import JSONformating.reader.JSONtoPCM;
import data_off.PCMUtil;

public class JSONmetamorphTest {
	
	String inputpath="input-pcm/";
	ArrayList<PCMContainer> listPcmC = new ArrayList<>();
	
	@BeforeClass
	public void loadPCMs(){
		
	}
	
	public PCMContainer importOldFormat(String filename) throws IOException{
		return PCMUtil.loadPCMContainer(filename);
	}
	
	public PCMContainer importNewFormat(String filename) throws IOException{
		return JSONtoPCM.JSONFormatToPCM(JSONReader.importJSON(filename));
	}
	
	@Deprecated
	public String exportOldFormat(PCMContainer pcmC){
		return new KMFJSONExporter().export(pcmC);
	}

	public String exportNewFormat(PCMContainer pcmC) throws IOException{
		return PCMtoJSON.mkNewJSONFormatFromPCM(pcmC).export();
	}
	
	@Test
	public void OldToNewparsable(){
		
	}
	
	@Test
	public void NewToNewparsable(){
		
	}
	
	@Test
	public void firstPCMequalsToSecondPCM(){
		
	}

	@Test
	public void firstJSONequalsToSecondJSON(){
		
	}
	
}	
