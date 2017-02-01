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
		_statPcm.setNbFeatures(infoPcm.nbFeatures());
		_statPcm.setNbCells(infoPcm.nbCells());
		_statPcm.setNbEmptyCells(infoPcm.nbEmptyCells());
		_statPcm.setRatioEmptyCells(infoPcm.ratioEmptyCells());
	}
	
	@Override
	public Integer nbRows() {
		return _statPcm.getNbRows();
	}

	@Override
	public Integer nbFeatures() {
		return _statPcm.getNbFeatures();
	}

	@Override
	public Integer nbCells() {
		return _statPcm.getNbCells();
	}

	@Override

	public Integer nbEmptyCells() {
		return _statPcm.getNbEmptyCells();
	}

	@Override
	public Integer ratioEmptyCells() {
		return _statPcm.getRatioEmptyCells();
	}
	
	
	

}