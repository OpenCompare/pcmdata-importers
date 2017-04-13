package pcm_Metric;

import org.opencompare.api.java.PCM;

public class OLD_realPCMIC implements OLD_interfacePCMIC {

	private OLD_statPCM _statPcm;
	private PCM _pcm;

	public OLD_realPCMIC(PCM pcm) {
		_pcm = pcm;
		preCompute();
	}

	private void preCompute() {
		OLD_basicPCMIC infoPcm = new OLD_basicPCMIC(_pcm);
		_statPcm = new OLD_statPCM();
		_statPcm.setNbRows(infoPcm.nbRows());
		_statPcm.setNbFeatures(infoPcm.nbFeatures());
		_statPcm.setNbCells(infoPcm.nbCells());
		_statPcm.setNbEmptyCells(infoPcm.nbEmptyCells());
		_statPcm.setRatioEmptyCells(infoPcm.ratioEmptyCells());
		_statPcm.setNbFeaturesHomog(infoPcm.nbFeaturesHomog());
		_statPcm.setRatioFeaturesHomog(infoPcm.ratioFeatureHomog());
		_statPcm.setNbFeaturesHomogNumeric(infoPcm.nbFeaturesHomogNumeric());
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
	public Double ratioEmptyCells() {
		return (double) _statPcm.getRatioEmptyCells();
	}

	@Override
	public Integer nbFeaturesHomog() {
		return _statPcm.getNbFeaturesHomog();
	}

	@Override
	public Double ratioFeatureHomog() {
		return _statPcm.getRatioFeaturesHomog();
	}

	@Override
	public Integer score() {
		return _statPcm.getScore();
	}

	public Double testScore() {
		Double score = 0.0;
		score += (_statPcm.scoreFeature() * 20);
		score += (_statPcm.scoreProduct() * 20);
		score += (_statPcm.scoreRatioEmptyCells() * 20);
		score += (_statPcm.scoreFeatureHomog() * 20);
		score += (_statPcm.scoreRatioFeatureHomog() * 20);
		System.out.println(score);

		score = Math.round(score * 100.0) / 100.0;

		return score;
	}

	public Integer nbFeaturesHomogNumeric() {
		return _statPcm.getNbFeaturesHomogNumeric();
	}
	
	public Boolean isProductChartable() {
		if(_statPcm.scoreProductChartable()>=0.5)
			return true;
		else
			return false;
			
	}



}
