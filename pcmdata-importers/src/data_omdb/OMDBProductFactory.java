package data_omdb;

public class OMDBProductFactory {
	
	private static OMDBProductFactory _instance = null;
	
	private OMDBProductFactory() {
		
	}
	
	public static OMDBProductFactory getInstance() {
		if (_instance == null)
			_instance = new OMDBProductFactory();
		return _instance;
	}
	
	
	public String mkProduct(Product p, OMDBMediaType t) {
		
		if (t.equals(OMDBMediaType.MOVIE))
			return mkProductMovie(p) ;
		if (t.equals(OMDBMediaType.SERIES))
			return mkProductSerie(p) ;
		if (t.equals(OMDBMediaType.EPISODE))
			return mkProductEpisode(p);
		
		return null;
	}
	
	public String mkProductMovie(Product p){
 		 String sortie = "" ;
		 sortie  += "\""+ p.imdbID + "\";\"";
		 sortie  += p.Title + "\";\"" ;
		 sortie  += p.imdbRating + "\";\"";
		 sortie  += p.imdbVotes + "\";\"";
		 int cpt = 0 ;
		 //affichage du genre du film parcours de la liste
		 for(String g : p.Genre){
			 if(cpt == 0){
				sortie  +=  g ;
				 cpt++ ;
			 }
			 else{
				 sortie  += "," + g;
			 }
			 	
		 }
		 sortie  += "\";\"";
		 
		 sortie  += p.Director + "\";\"";
		 sortie  += p.Writer + "\";\"";
		 cpt = 0 ;
		 for(String a : p.Actors){
			 if(cpt == 0){
				 sortie  += a;
				 cpt ++ ;
			 }
			 else{
				 sortie  += "," + a;
			 }
		 }
		 sortie  += "\";\"";
		 cpt = 0 ;
		 for(String b : p.Language){
			 if(cpt == 0){
				 sortie  += b;
				 cpt ++ ;
			 }
			 else{
				 sortie  += "," + b;
			 }
		 }
		 sortie  += "\";\"";cpt = 0 ;
		 for(String c : p.Country){
			 if(cpt == 0){
				 sortie  += c;
				 cpt ++ ;
			 }
			 else{
				 sortie  += "," + c;
			 }
		 }
		 sortie  += "\";\"";
		 sortie  += p.Year + "\";\"";
		 sortie  += p.Metascore + "\";\"";
		 sortie  += p.Poster + "\";\"";
		 sortie  += p.Plot + "\";";
		 
		 

		 String Newligne=System.getProperty("line.separator"); 
		 sortie += Newligne;
		 return sortie ;
	}
	
	public String mkProductSerie (Product p){

		 String sortie = "" ;
		 sortie  += "\""+ p.imdbID + "\";\"";
		 sortie  += p.Title + "\";\"" ;
		 sortie  += p.totalSeasons +"\";\"";
		 sortie  += p.imdbRating + "\";\"";
		 sortie  += p.imdbVotes + "\";\"";
		 int cpt = 0 ;
		 //affichage du genre du film parcours de la liste
		 for(String g : p.Genre){
			 if(cpt == 0){
				sortie  +=  g ;
				 cpt++ ;
			 }
			 else{
				 sortie  += "," + g;
			 }
			 	
		 }
		 sortie  += "\";\"";
		 
		 sortie  += p.Director + "\";\"";
		 sortie  += p.Writer + "\";\"";
		 cpt = 0 ;
		 for(String a : p.Actors){
			 if(cpt == 0){
				 sortie  += a;
				 cpt ++ ;
			 }
			 else{
				 sortie  += "," + a;
			 }
		 }
		 sortie  += "\";\"";
		 cpt = 0 ;
		 for(String b : p.Language){
			 if(cpt == 0){
				 sortie  += b;
				 cpt ++ ;
			 }
			 else{
				 sortie  += "," + b;
			 }
		 }
		 sortie  += "\";\"";cpt = 0 ;
		 for(String c : p.Country){
			 if(cpt == 0){
				 sortie  += c;
				 cpt ++ ;
			 }
			 else{
				 sortie  += "," + c;
			 }
		 }
		 sortie  += "\";\"";
		 sortie  += p.Year + "\";\"";
		 sortie  += p.Metascore + "\";\"";
		 sortie  += p.Poster + "\";\"";
		 sortie  += p.Plot + "\";";
		 
		 

		 String Newligne=System.getProperty("line.separator"); 
		 sortie += Newligne;
		 
		 System.out.println(sortie);
		 
		 return sortie ;
	}
	
	public String mkProductEpisode(Product p){

		String sortie = "" ;
		 sortie  += "\""+ p.imdbID + "\";\"";
		 sortie  += p.Title + "\";\"" ;
		 sortie  += p.seriesID + "\";\"";
		 sortie  += p.Season + "\";\"";
		 sortie  += p.Episode + "\";\"";
		 sortie  += p.imdbRating + "\";\"";
		 sortie  += p.imdbVotes + "\";\"";
		 int cpt = 0 ;
		 //affichage du genre du film parcours de la liste
		 for(String g : p.Genre){
			 if(cpt == 0){
				sortie  +=  g ;
				 cpt++ ;
			 }
			 else{
				 sortie  += "," + g;
			 }
			 	
		 }
		 sortie  += "\";\"";
		 
		 sortie  += p.Director + "\";\"";
		 sortie  += p.Writer + "\";\"";
		 cpt = 0 ;
		 for(String a : p.Actors){
			 if(cpt == 0){
				 sortie  += a;
				 cpt ++ ;
			 }
			 else{
				 sortie  += "," + a;
			 }
		 }
		 sortie  += "\";\"";
		 cpt = 0 ;
		 for(String b : p.Language){
			 if(cpt == 0){
				 sortie  += b;
				 cpt ++ ;
			 }
			 else{
				 sortie  += "," + b;
			 }
		 }
		 sortie  += "\";\"";cpt = 0 ;
		 for(String c : p.Country){
			 if(cpt == 0){
				 sortie  += c;
				 cpt ++ ;
			 }
			 else{
				 sortie  += "," + c;
			 }
		 }
		 sortie  += "\";\"";
		 sortie  += p.Year + "\";\"";
		 sortie  += p.Metascore + "\";\"";
		 sortie  += p.Poster + "\";\"";
		 sortie  += p.Plot + "\";";
		 

		 
		

		 String Newligne=System.getProperty("line.separator"); 
		 sortie += Newligne;
		 return sortie ;
	}

}
