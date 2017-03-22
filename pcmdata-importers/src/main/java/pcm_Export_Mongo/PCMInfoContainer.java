package pcm_Export_Mongo;

import org.opencompare.api.java.PCM;

public class PCMInfoContainer {

	private StatPcm _statPcm;
	private PCM _pcm;

	public PCMInfoContainer(PCM pcm) {
		_pcm = pcm;
		preCompute(pcm);
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
