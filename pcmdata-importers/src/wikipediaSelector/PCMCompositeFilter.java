package wikipediaSelector;

import java.util.Collection;

import org.opencompare.model.PCM;

public class PCMCompositeFilter implements PCMPredicateFilter {
	
	private Collection<PCMPredicateFilter> filters;

	@Override
	public boolean isSatisfiable(PCMInfoContainer pcmic) {
		for (PCMPredicateFilter pcmPredicateFilter : filters) {
			if (!pcmPredicateFilter.isSatisfiable(pcmic))
				return false;
		}
		return true;
	}
	
	// add (AND)
	// add (OR)

}
