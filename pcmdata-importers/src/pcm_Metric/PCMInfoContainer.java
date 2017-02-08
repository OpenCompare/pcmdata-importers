package pcm_Metric;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;
import jdk.internal.org.objectweb.asm.tree.analysis.Interpreter;

import org.opencompare.api.java.AbstractFeature;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.value.IntegerValue;
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

	@Override
	public Integer nbFeaturesHomog() {
		int nbFeaturesHomog = 0;
		int cBooleanValue = 0;
		int cConditional = 0;
		int cDateValue = 0;
		int cDimension = 0;
		int cIntegerValue = 0;
		int cMultiple = 0;
		int cNotApplicable = 0;
		int cNotAvailable = 0;
		int cPartial = 0;
		int cRealValue = 0;
		int cStringValue = 0;
		int cUnit = 0;
		int cVersion = 0;
		int cNoInterpreation = 0;
		boolean featureHomog;
		Map<Value, Integer> mapMetrics = new HashMap<Value, Integer>();
		List<Feature> feats = _pcm.getConcreteFeatures();
		for(Feature feat : feats){
			featureHomog = false;
			List<Cell> cells = feat.getCells();
			for(Cell cell : cells){
				String c = cell.getInterpretation().toString();
				switch (c) {
				case "BooleanValue":
					cBooleanValue++;	
					break;
					
				case "Conditional":
					cConditional++;		
					break;
					
				case "DateValue":
					cDateValue++;	
					break;
					
				case "Dimension":
					cDimension++;		
					break;
					
				case "IntegerValue":
					cIntegerValue++;
					break;
					
				case "Multiple":
					cMultiple++;
					break;
					
				case "NotApplicable":
					cNotApplicable++;	
					break;
					
				case "NotAvailable":
					cNotAvailable++;	
					break;
					
				case "Partial":
					cPartial++;		
					break;
					
				case "RealValue":
					cRealValue++;
					break;
	
				case "StringValue":
					cStringValue++;
					break;
					
				case "Unit":
					cUnit++;	
					break;
					
				case "Version":
					cVersion++;
					break;
					
				case "None":
					cNoInterpreation++;
					break;

				default:
					break;
				}
				
			}
			
			if(featureHomog) nbFeaturesHomog++;
		}	
		
		return nbFeaturesHomog;
	}
	
	@Override
	public Double ratioFeatureHomog() {
		if(nbFeaturesHomog().equals(0)){
			return (double) 0;
			
		}
		return (double)(nbFeaturesHomog()*100)/nbFeatures();
	}
	
	@Override
	public Integer score(){
		return null;
		
	}

}
