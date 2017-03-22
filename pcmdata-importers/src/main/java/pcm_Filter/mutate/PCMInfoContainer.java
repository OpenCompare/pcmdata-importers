package pcm_Filter.mutate;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.value.IntegerValue;
import org.opencompare.api.java.value.RealValue;

public class PCMInfoContainer {

	private static final double THRESHOLD_HOMOGENEOUS = 90;
	private StatPcm _statPcm;
	private PCM _pcm;

	private StatPcm _statPcmMutate;
	private PCM _pcmMutate;

	
	public PCMInfoContainer(PCM pcm) {
		_pcm = pcm;
		_pcmMutate = PCMMutate.Mutate(pcm);
		preCompute(_pcmMutate);
		preCompute(_pcm);
	}

	private void preCompute(PCM pcm) {
		_statPcm = new StatPcm(_pcm);

	}

	public Boolean isProductChartable() {
		if (_statPcm.scoreProductChartable() >= 0.5)
			return true;
		else
			return false;

	}
	
}
