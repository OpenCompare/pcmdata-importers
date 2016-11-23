package data_omdb;
import java.io.IOException;
import java.io.* ;
import java.net.MalformedURLException;
import java.net.URL ;
import java.nio.charset.StandardCharsets ;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.* ;

public class OmdbtoProduct {
		
	
	public static Product createProductFromId(String id){
		return null ;
	}
	
	public static Product createProductFromJson(JSONObject obj) throws JSONException  {
		
		
		

		//on regarde si le film existe
		if(obj.getString("Response").equals("False")){
			// qunad le film n'existe pas
			return null ;
		}
		else{
			Product pro = new Product() ;
			pro.Title = obj.getString("Title");
			try {
				pro.Year = obj.getInt("Year");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				pro.Year = -1 ;
			}
			pro.Rated = obj.getString("Rated") ;
			pro.Released = obj.getString("Released");
			pro.Runtime = obj.getString("Runtime") ;
			pro.Genre = Arrays.asList(obj.getString("Genre").split(", "));
			pro.Director = obj.getString("Director") ;
			pro.Writer = obj.getString("Writer") ;
			pro.Actors = Arrays.asList(obj.getString("Actors").split(", "));
			pro.Plot = obj.getString("Plot") ;
			pro.Language = Arrays.asList(obj.getString("Language").split(", "));
			pro.Country = Arrays.asList(obj.getString("Country").split(", "));
			pro.Poster = obj.getString("Poster");
			try {
				pro.Metascore = obj.getInt("Metascore");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Metascore = -1;
			}
			try {
				pro.imdbRating = Float.parseFloat(obj.getString("imdbRating")) ;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				pro.imdbRating = -1 ;
			}
			try {
				pro.imdbVotes = Integer.parseInt(obj.getString("imdbVotes").replaceAll(",", ""));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				pro.imdbVotes = -1 ;
			}
			pro.imdbID = obj.getString("imdbID") ;
			pro.Type = obj.getString("Type") ;
			try {
				pro.totalSeasons = obj.getInt("totalSeasons");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.totalSeasons = -1;
			}
			
			return pro ;
			
		}
		
	}

	
	public static JSONObject idToJson(long id) throws IOException, JSONException{
		String nom_id = "" ;
		long j = 0 ;
		j = j+id ;
		nom_id = String.valueOf(j) ;
		int taille = nom_id.length() ;
		while(taille <7){
			nom_id = "0" + nom_id ;
			taille ++ ;
		}
		nom_id = "tt"+nom_id ;
		URL url = new URL("http://www.omdbapi.com/?i="+ nom_id +"&plot=short&r=json") ;
		BufferedReader in = new BufferedReader (new InputStreamReader(url.openStream(),StandardCharsets.UTF_8)) ;
		String input = in.readLine() ;
		in.close(); 
		
		JSONObject obj = new JSONObject(input) ;
		return obj ;
	}
	
	public static void main(String[] args) throws IOException, JSONException {
		
		
		String films = "" ;
		List<Product> film = new ArrayList<Product>() ;
		String series = "" ;
		List<Product> serie = new ArrayList<Product>() ;
		for(int i=900000;i<950000;i++)
		{
			
			
			 Product p  = createProductFromJson(idToJson(i)) ;
			 if(p != null)
			 {
				 
				 //on regarde si c'est un film ou une s�rie
				 if(p.Type.equals("movie")){
					 films += ProductMovie(p, film) ;
					 System.out.println("on est dans le " + i + "eme id film");
				 }
				 else if(p.Type.equals("series")) {
					 series += ProductSerie(p, serie) ;
					 System.out.println("on est dans le " + i + "eme id serie");
				 }				 
				 
			 }
			
		}
		
		// on �crit le string dans un fichier
		Write_file.write_file("data_omdb_film.csv");
		System.out.println(films);
		Write_file.write_file("data_omdb_serie.csv");
		System.out.println(series);
	//	System.out.println(film.size());
//		System.out.println(film.get(74).Actors.size());
			
	}
	
	public static String ProductMovie(Product p, List<Product> film){
		film.add(p) ;
		String sortie = "" ;
		 sortie  += "\""+ p.imdbID + "\";\"";
		 sortie  += p.Title + "\";\"" ;
		 sortie  += p.imdbRating + "\";\"";
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
		 sortie  += p.Year + "\";";

		 String Newligne=System.getProperty("line.separator"); 
		 sortie += Newligne;
		 return sortie ;
	}
	
	public static String ProductSerie (Product p , List<Product> film){
		 film.add(p) ; 
		 String sortie = "\"" ;
		 sortie  += p.imdbID + "\";\"";
		 sortie  += p.Title + "\";\"" ;
		 sortie  += p.imdbRating + "\";\"";
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
		 cpt = 0 ;
		 for(String a : p.Actors){
			 if(cpt == 0){
				 sortie  += a;
				 cpt ++ ;
			 }
			 else{
				 sortie  += ","+ a;
			 }
		 }
		 sortie  += "\";\"";
		 sortie  += p.Year + "\";\"";
		 
		 sortie  += p.totalSeasons +"\";";

		 String Newligne=System.getProperty("line.separator"); 
		 sortie += Newligne;
		 
		 System.out.println(sortie);
		 
		 return sortie ;
	}
	
	
}
