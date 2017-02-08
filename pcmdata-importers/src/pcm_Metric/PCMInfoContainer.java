package pcm_Metric;

import java.util.List;
import java.util.logging.Logger;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.value.NotAvailable;
import org.opencompare.model.BooleanValue;


public class PCMInfoContainer implements IPCMInfoContainer {

	private static final Logger _log = Logger.getLogger(PCMInfoContainer.class.getName());
	
	private PCM _pcm;

	
	public PCMInfoContainer(PCM pcm) {
		_pcm = pcm;
	}	
	
	/* 
	public void print() {
		for (Product product : _pcm.getProducts()) {
			List<Cell> cells = product.getCells();
			for (Cell cell : cells) {
				Value v = cell.getInterpretation();
				if (v instanceof BooleanValue) {
					_log.info("boolean");
				}
			}
		}
	}
	*/
	
	@Override
	public Integer nbRows() {		
		return _pcm.getProducts().size();
	}

	@Override
	public Integer nbFeatures() {
		return _pcm.getConcreteFeatures().size();
	}

	@Override
	public Integer nbCells() {
		int nbCell = 0;
		List<Product> pdts = _pcm.getProducts();
		for (Product pdt : pdts) {
			nbCell += pdt.getCells().size();
		}
		return nbCell;
	}

	@Override
	public Integer nbEmptyCells() {
		int nbEmptyCell = 0;
		List<Product> pdts = _pcm.getProducts();
		for (Product pdt : pdts) {
			List<Cell> cells = pdt.getCells();
			for (Cell cell : cells) {
				if (cell.getContent().isEmpty())
					nbEmptyCell++;
				//if (cell.getInterpretation() instanceof NotAvailable)
				//	nbEmptyCell++;
			}
		}
		return nbEmptyCell;
	}

	@Override
	public Double ratioEmptyCells() {
		if(nbCells().equals(0)){
			return (double) 0;
			
		}
		return (double)(nbEmptyCells()*100)/nbCells();
	}

	public Integer nbFeaturesHomog() {
		return null;
	}
	
	public Double ratioFeatureHomog() {
		return null;
	}

}
