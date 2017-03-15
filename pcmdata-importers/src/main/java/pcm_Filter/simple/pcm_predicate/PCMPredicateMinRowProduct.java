package main.java.pcm_Filter.simple.pcm_predicate;

import org.opencompare.model.PCM;

import main.java.pcm_Filter.PCMInfoContainer;


public class PCMPredicateMinRowProduct implements PCMPredicateFilter {

	public static final int minimumRows = 3;
	
	@Override
	public boolean isSatisfiable(PCMInfoContainer pcmic) {
		pcmic.computeRows();
		if(pcmic.getRows()>minimumRows){
			return true;
		}
		return false;
	}

}
