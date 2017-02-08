package pcm_Metric;

/**
 * contains "metrics" of PCM
 * @author macher1
 *
 */
public class StatPcm {
	
	private int nbRows;
	private int nbFeatures;
	private int nbCells;
	private int nbEmptyCells;
	private double ratioEmptyCells;
	private int nbFeaturesHomog;
	private double ratioFeaturesHomog;
	private int score;
	
	final double nbCellsWeigth=1.0;
	final double nbEmptyCellsWeigth=-0.1;

	public int getNbRows() {
		return nbRows;
	}
	
	public void setNbRows(int nbRows) {
		this.nbRows = nbRows;
	}
	
	public int getNbFeatures() {
		return nbFeatures;
	}
	
	public void setNbFeatures(int nbFeatures) {
		this.nbFeatures = nbFeatures;
	}
	
	public int getNbCells() {
		return nbCells;
	}
	
	public void setNbCells(int nbCells) {
		this.nbCells = nbCells;
	}
	
	public int getNbEmptyCells() {
		return nbEmptyCells;
	}
	
	public void setNbEmptyCells(int nbEmptyCells) {
		this.nbEmptyCells = nbEmptyCells;
	}
	
	public double getRatioEmptyCells() {
		return ratioEmptyCells;
	}
	
	public void setRatioEmptyCells(Double ratioEmptyCells) {
		this.ratioEmptyCells = ratioEmptyCells;
	}

	public Double scoreCell()  {
		return (Math.sqrt(nbCells)*nbCellsWeigth);
	}

	public Double scoreEmptyCells() {
		System.out.println(nbEmptyCells+":"+Math.pow(nbEmptyCells,2));
		return (Math.pow(nbEmptyCells,2)*nbEmptyCellsWeigth);
	}
	
	public int getNbFeaturesHomog() {
		return nbFeaturesHomog;
	}

	public void setNbFeaturesHomog(int nbFeaturesHomog) {
		this.nbFeaturesHomog = nbFeaturesHomog;
	}

	public double getRatioFeaturesHomog() {
		return ratioFeaturesHomog;
	}

	public void setRatioFeaturesHomog(double ratioFeaturesHomog) {
		this.ratioFeaturesHomog = ratioFeaturesHomog;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
