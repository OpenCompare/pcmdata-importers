package main.java.pcm_Filter.simple.pcm_predicate;

import org.opencompare.model.PCM;

import main.java.pcm_Filter.PCMInfoContainer;


public interface PCMPredicateFilter {
	public boolean isSatisfiable(PCMInfoContainer pcmic);
}
