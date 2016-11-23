package wikipediaSelector;

import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;

public class PCMInfoContainer {

	private PCM pcm;

	private int rows;

	public PCMInfoContainer(PCM pcm) {
		this.pcm = pcm;
		//computeRow();
	}

	public void computeRows() {
		rows=0;
		for (Product product : pcm.getProducts()) {
			rows++;
			//System.out.println(product.getKeyContent());
		}
		//System.out.println(rows);
	}
	
	public int getRows(){
		return rows;
	}
}
