package pcm_Filter.pcm_predicate;

import org.opencompare.model.PCM;

import pcm_Filter.PCMInfoContainer;

public class PCMPredicateMinColumnProduct implements PCMPredicateFilter {

	public static final int minimumColumns = 2; //8 for test on Vo_IPs test pcms
	
	@Override
	public boolean isSatisfiable(PCMInfoContainer pcmic) {
		pcmic.computeColumns();
		if(pcmic.getColumns()>minimumColumns){
			return true;
		}
		return false;
	}

}
