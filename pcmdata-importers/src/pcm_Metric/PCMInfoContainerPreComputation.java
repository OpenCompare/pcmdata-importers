package pcm_Metric;

import org.opencompare.api.java.PCM;

public class PCMInfoContainerPreComputation implements IPCMInfoContainer {
	
	private StatPcm _statPcm ; 
	private PCM _pcm;
	
	public PCMInfoContainerPreComputation(PCM pcm) {
		_pcm = pcm; 
		preCompute();
	}
	
	private void preCompute() {
		PCMInfoContainer infoPcm = new PCMInfoContainer(_pcm);
		_statPcm = new StatPcm();		
		_statPcm.setNbRows(infoPcm.nbRows());
		_statPcm.setNbFeatures(infoPcm.nbRows());
	}
	
	@Override
	public int nbRows() {
		return _statPcm.getNbRows();
	}

	@Override
	public int nbFeatures() {
		return _statPcm.getNbFeatures();
	}

	@Override
	public int nbCells() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int nbEmptyCells() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
