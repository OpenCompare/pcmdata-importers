package pcm_Filter.mutate;

import java.util.ArrayList;
import java.util.List;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;


public class PCMMutate {
	
	private static final double RATIO_EMPTY_CELL = 0.001;
	
	public static PCM Mutate(PCM pcm){
		// Il faut appeler une fonction pour déterminer
		// le detail sur les lignes
		pcm = clear_ligne(pcm) ;
		
		// Il faut appeler une fonction pour déterminer
		// le detail sur les colonnes
		pcm = clear_colonne(pcm) ;
		
		return pcm ;
	}
	
	/**
    Enlever les lignes inutiles
    @param pcm : Le pcm
    @return Le pcm avec les lignes inutiles en moins
	 */
	private static PCM clear_ligne(PCM pcm){
		List<Product> pdts = pcm.getProducts();
		List<Cell> cells = new ArrayList<Cell>() ;
		for (Product pr : pdts) {
			float nbCellsEmpty = 0 ;
			// On ajoute les cellules du product dans une liste
			cells = pr.getCells();
			// On traite les infos des cellules
			for(Cell c : cells){
				if(c.getContent().isEmpty()){
					nbCellsEmpty ++ ;
				}
			}
			if(cells.size() != 0){
				System.out.println("Dans les lignes -- > \n Nombre de cellule vide :" + nbCellsEmpty + "\n Nombre de cellule : " + cells.size());
				System.out.println("Valeur du if : " + nbCellsEmpty/cells.size());
				if((nbCellsEmpty/cells.size()) > RATIO_EMPTY_CELL){
					System.out.println("on dégage la ligne !");
					pcm.removeProduct(pr);
				}
			}			
		}
		
		return pcm;
	}
	
	/**
    Enlever les colonnes inutiles
    @param pcmic : Le pcm info container du pcm
    @param pcm : Le pcm
    @return Le pcm avec les colonnes inutiles en moins
	 */
	private static PCM clear_colonne(PCM pcm){
		List<Feature> pdts = pcm.getConcreteFeatures();
		List<Cell> cells = new ArrayList<Cell>() ;
		for (Feature pr : pdts) {
			float nbCellsEmpty = 0 ;
			// On ajoute les cellules du product dans une liste
			cells = pr.getCells();
			// On traite les infos des cellules
			for(Cell c : cells){
				if(c.getContent().isEmpty()){
					nbCellsEmpty ++ ;
				}
			}
			if(cells.size() != 0){
				System.out.println("Dans les colonnes -- > \n Nombre de cellule vide :" + nbCellsEmpty + "\n Nombre de cellule : " + cells.size());
				System.out.println("Valeur du if : " + nbCellsEmpty/cells.size());
				if((nbCellsEmpty/cells.size()) > RATIO_EMPTY_CELL){
					System.out.println("on dégage la colonne !");
					pcm.removeFeature(pr);;
				}
			}
		}
		
		return pcm;
	}

}
