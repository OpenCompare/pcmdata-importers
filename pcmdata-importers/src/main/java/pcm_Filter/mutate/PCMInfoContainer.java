package pcm_Filter.mutate;

import org.opencompare.api.java.PCM;


public class PCMInfoContainer {

	private StatPcm _statPcm;
	private PCM _pcm;

	private StatPcm _statPcmMutate;
	private PCM _pcmMutate;

	public PCMInfoContainer(PCM pcm) {
		_pcm = pcm;
		_pcmMutate = PCMMutate.Mutate(pcm);
		preComputeMutate(_pcmMutate);
		preCompute(_pcm);
	}

	private void preCompute(PCM pcm) {
		_statPcm = new StatPcm(_pcm);

	}

	private void preComputeMutate(PCM pcm) {
		_statPcmMutate = new StatPcm(_pcmMutate);
	}

	public Boolean isProductChartable() {
		return _statPcm.scoreProductChartable() >= 0.5;
	}

	public Boolean isProductChartableMutate() {
		return _statPcmMutate.scoreProductChartable() >= 0.5;
	}

	public PCM getPCM() {
		return _pcm;
	}
	
	public PCM getMutedPcm() {
		return _pcmMutate;
	}
}
