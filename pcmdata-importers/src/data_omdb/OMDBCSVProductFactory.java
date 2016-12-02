package data_omdb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OMDBCSVProductFactory {
	
	private static OMDBCSVProductFactory _instance = null;
	
	private OMDBCSVProductFactory() {
		
	}
	
	public static OMDBCSVProductFactory getInstance() {
		if (_instance == null)
			_instance = new OMDBCSVProductFactory();
		return _instance;
	}
	
	
	/**
	 * A CSV representation of "Product" (in fact a CSV line)
	 * the CSV representation depends on OMDB type  
	 * @param p
	 * @param t
	 * @return
	 */
	public String mkCSVProduct(OMDBProduct p, OMDBMediaType t) {
		
		if (t.equals(OMDBMediaType.MOVIE))
			return mkCSVProductMovie(p) ;
		if (t.equals(OMDBMediaType.SERIES))
			return mkCSVProductSerie(p) ;
		if (t.equals(OMDBMediaType.EPISODE))
			return mkCSVProductEpisode(p);
		
		return null;
	}
	
	public String mkCSVProductMovie(OMDBProduct p) {
		
 		 String out = "" ;
		 out  += "\""+ p.imdbID + "\";\"";
		 out  += p.Title + "\";\"" ;
		 out  += p.imdbRating + "\";\"";
		 out  += p.Runtime + "\";\"";
		 out  += p.imdbVotes + "\";\"";
		 int cpt = 0 ;
		 //affichage du genre du film parcours de la liste
		 for(String g : p.Genre){
			 if(cpt == 0){
				out  +=  g ;
				 cpt++ ;
			 }
			 else{
				 out  += "," + g;
			 }
			 	
		 }
		 out  += "\";\"";
		 
		 out  += p.Director + "\";\"";
		 out  += p.Writer + "\";\"";
		 cpt = 0 ;
		 for(String a : p.Actors){
			 if(cpt == 0){
				 out  += a;
				 cpt ++ ;
			 }
			 else{
				 out  += "," + a;
			 }
		 }
		 out  += "\";\"";
		 cpt = 0 ;
		 for(String b : p.Language){
			 if(cpt == 0){
				 out  += b;
				 cpt ++ ;
			 }
			 else{
				 out  += "," + b;
			 }
		 }
		 out  += "\";\"";cpt = 0 ;
		 for(String c : p.Country){
			 if(cpt == 0){
				 out  += c;
				 cpt ++ ;
			 }
			 else{
				 out  += "," + c;
			 }
		 }
		 out  += "\";\"";
		 out  += p.Year + "\";\"";
		 out  += p.Metascore + "\";\"";
		 out  += p.Poster + "\";\"";
		 out  += p.Plot + "\";";
		 
		 

		 return out ;
	}
	
	public String mkCSVProductSerie (OMDBProduct p) {

		 String out = "" ;
		 out  += "\""+ p.imdbID + "\";\"";
		 out  += p.Title + "\";\"" ;
		 out  += p.totalSeasons +"\";\"";
		 out  += p.imdbRating + "\";\"";
		 out  += p.imdbVotes + "\";\"";
		 int cpt = 0 ;
		 //affichage du genre du film parcours de la liste
		 for(String g : p.Genre){
			 if(cpt == 0){
				out  +=  g ;
				 cpt++ ;
			 }
			 else{
				 out  += "," + g;
			 }
			 	
		 }
		 out  += "\";\"";
		 
		 out  += p.Director + "\";\"";
		 out  += p.Writer + "\";\"";
		 cpt = 0 ;
		 for(String a : p.Actors){
			 if(cpt == 0){
				 out  += a;
				 cpt ++ ;
			 }
			 else{
				 out  += "," + a;
			 }
		 }
		 out  += "\";\"";
		 cpt = 0 ;
		 for(String b : p.Language){
			 if(cpt == 0){
				 out  += b;
				 cpt ++ ;
			 }
			 else{
				 out  += "," + b;
			 }
		 }
		 out  += "\";\"";cpt = 0 ;
		 for(String c : p.Country){
			 if(cpt == 0){
				 out  += c;
				 cpt ++ ;
			 }
			 else{
				 out  += "," + c;
			 }
		 }
		 out  += "\";\"";
		 out  += p.Year + "\";\"";
		 out  += p.Metascore + "\";\"";
		 out  += p.Poster + "\";\"";
		 out  += p.Plot + "\";";
		 
			 
		 return out ;
	}
	
	public String mkCSVProductEpisode(OMDBProduct p){

		String out = "";
		 out  += "\""+ p.imdbID + "\";\"";
		 out  += p.Title + "\";\"" ;
		 out  += p.seriesID + "\";\"";
		 out  += p.Season + "\";\"";
		 out  += p.Episode + "\";\"";
		 out  += p.Runtime + "\";\"";
		 out  += p.imdbRating + "\";\"";
		 out  += p.imdbVotes + "\";\"";
		 int cpt = 0 ;
		 //affichage du genre du film parcours de la liste
		 for(String g : p.Genre){
			 if(cpt == 0){
				out  +=  g ;
				 cpt++ ;
			 }
			 else{
				 out  += "," + g;
			 }
			 	
		 }
		 out  += "\";\"";
		 
		 out  += p.Director + "\";\"";
		 out  += p.Writer + "\";\"";
		 cpt = 0 ;
		 for(String a : p.Actors){
			 if(cpt == 0){
				 out  += a;
				 cpt ++ ;
			 }
			 else{
				 out  += "," + a;
			 }
		 }
		 out  += "\";\"";
		 cpt = 0 ;
		 for(String b : p.Language){
			 if(cpt == 0){
				 out  += b;
				 cpt ++ ;
			 }
			 else{
				 out  += "," + b;
			 }
		 }
		 out  += "\";\"";cpt = 0 ;
		 for(String c : p.Country){
			 if(cpt == 0){
				 out  += c;
				 cpt ++ ;
			 }
			 else{
				 out  += "," + c;
			 }
		 }
		 out  += "\";\"";
		 out  += p.Year + "\";\"";
		 out  += p.Metascore + "\";\"";
		 out  += p.Poster + "\";\"";
		 out  += p.Plot + "\";";
		 
		 return out ;
	}

	public String mkHeaders(OMDBMediaType t) {
		String str = "";
		List<String> headers = new ArrayList<String>();
		if (t.equals(OMDBMediaType.MOVIE))
				headers = Arrays.asList("imdbID", "title", "imdbRating", "runtime", "imdbVotes", "genre", "director", 
		 "writer", "actors", "language", "country", "year", "metascore", "poster", "plot");
		else if (t.equals(OMDBMediaType.SERIES)) {
			headers = Arrays.asList("imdbID", "title", "totalSeasons", "imdbRating", "imdbVotes", "genre", "director", 
					 "writer", "actors", "language", "country", "year", "metascore", "poster", "plot");
		}
		else { // EPISODES
			headers = Arrays.asList("imdbID", "title", "seriesID", "season", "episode","runtime", "imdbRating", "imdbVotes", "genre", "director", 
					 "writer", "actors", "language", "country", "year", "metascore", "poster", "plot");
		}
		
		for (String h : headers) {
			str += h + ";";
		}
		return str;
		
	}

}
