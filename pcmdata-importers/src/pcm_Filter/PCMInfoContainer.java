package pcm_Filter;

import org.opencompare.api.java.AbstractFeature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;

public class PCMInfoContainer {

	private PCM pcm;

	private int rows;
	private int columns;


	public PCMInfoContainer(PCM pcm) {
		this.pcm = pcm;
		//computeRows();
		//computeColumns();
	}

	public void computeRows() {
		rows=0;
		for (Product product : pcm.getProducts()) {
			rows++;
			//System.out.println(product.getKeyContent());
		}
		//System.out.println(rows);
	}
	
	public void computeColumns(){
		columns=0;
		for (AbstractFeature feature : pcm.getFeatures()) {
			columns++;
			//System.out.println(feature.getName());
		}
		//System.out.println(columns);
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns(){
		return columns;
	}
}
