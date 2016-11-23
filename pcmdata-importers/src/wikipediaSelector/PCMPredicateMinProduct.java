package wikipediaSelector;

import org.opencompare.model.PCM;

public class PCMPredicateMinProduct implements PCMPredicateFilter {

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
