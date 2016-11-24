package pcm_Filter.pcm_predicate;

import org.opencompare.model.PCM;

import pcm_Filter.PCMInfoContainer;

public interface PCMPredicateFilter {
	public boolean isSatisfiable(PCMInfoContainer pcmic);
}
