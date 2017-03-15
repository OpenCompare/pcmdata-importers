package main.java.pcm_Export_Mongo;

import org.opencompare.api.java.PCM;

public class PCMInfoContainer{

	private StatPcm _statPcm;
	private PCM _pcm;

	public PCMInfoContainer(PCM pcm) {
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
		_statPcm.setNbFeaturesHomog(infoPcm.nbFeaturesHomog());
		_statPcm.setRatioFeaturesHomog(infoPcm.ratioFeatureHomog());
		_statPcm.setNbFeaturesHomogNumeric(infoPcm.nbFeaturesHomogNumeric());
	}

	public Integer nbRows() {
		return _statPcm.getNbRows();
	}

	public Integer nbFeatures() {
		return _statPcm.getNbFeatures();
	}

	public Integer nbCells() {
		return _statPcm.getNbCells();
	}

	public Integer nbEmptyCells() {
		return _statPcm.getNbEmptyCells();
	}

	public Double ratioEmptyCells() {
		return (double) _statPcm.getRatioEmptyCells();
	}

	public Integer nbFeaturesHomog() {
		return _statPcm.getNbFeaturesHomog();
	}

	public Double ratioFeatureHomog() {
		return _statPcm.getRatioFeaturesHomog();
	}

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
