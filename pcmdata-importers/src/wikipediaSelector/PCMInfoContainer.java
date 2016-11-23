package wikipediaSelector;

import org.opencompare.api.java.PCM;

public class PCMInfoContainer {

	private PCM pcm;
	
	private int rows;
	
	public PCMInfoContainer(PCM pcm){
		this.pcm=pcm;
		computeRow();
	}

	private void computeRow() {
		rows=10;
	}
}
