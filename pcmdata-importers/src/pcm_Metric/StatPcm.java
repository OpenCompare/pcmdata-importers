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
	
}
