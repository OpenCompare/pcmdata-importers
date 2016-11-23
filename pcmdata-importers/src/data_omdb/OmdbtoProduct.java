package data_omdb;
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
		
		List<String> vide = new ArrayList<String>();
		vide.add("");
		

		//on regarde si le film existe
		if(obj.getString("Response").equals("False")){
			// qunad le film n'existe pas
			return null ;
		}
		else{
			Product pro = new Product() ;
			
			try {
				pro.Title = obj.getString("Title") ;
				if(pro.Title.equals("N/A")) pro.Title="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Title = "";
			}
			
			
			try {
				pro.Year = obj.getString("Year");
				if(pro.Year.equals("N/A")) pro.Year="";
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				pro.Year = "" ;
			}
			
			try {
                pro.Rated = obj.getString("Rated");
                if(pro.Rated.equals("N/A")) pro.Rated="" ;
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                pro.Rated = "";
            }
            
            try {
                pro.Released = obj.getString("Released");
                if(pro.Released.equals("N/A")) pro.Released="" ;
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                pro.Released = "";
            }
            
            try {
                pro.Runtime = obj.getString("Runtime");
                if(pro.Runtime.equals("N/A")) pro.Runtime="" ;
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                pro.Runtime = "";
            }
            
            try {
                pro.Genre = Arrays.asList(obj.getString("Genre").split(", "));
                if(pro.Genre.contains("N/A")) pro.Genre= vide ;
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                pro.Genre= vide;
            }
			
			
			try {
				pro.Director = obj.getString("Director") ;
				if(pro.Director.equals("N/A")) pro.Director="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Director = "";
			}
			
			
			try {
				pro.Writer = obj.getString("Writer") ;
				if(pro.Writer.equals("N/A")) pro.Writer="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Writer = "";
			}
			
			
			try {
				pro.Actors = Arrays.asList(obj.getString("Actors").split(", "));
				if(pro.Actors.contains("N/A")) pro.Actors=vide;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Actors = vide;
			}
			
			try {
				pro.Plot = obj.getString("Plot") ;
				if(pro.Plot.equals("N/A")) pro.Plot="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Plot = "";
			}
			
			try {
				pro.Language = Arrays.asList(obj.getString("Language").split(", "));
				if(pro.Language.contains("N/A")) pro.Language=vide;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Language = vide;
			}
			
			try {
				pro.Country = Arrays.asList(obj.getString("Country").split(", "));
				if(pro.Country.contains("N/A")) pro.Country=vide;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Country = vide;
			}
			
			
			try {
				pro.Poster = obj.getString("Poster");
				if(pro.Poster.equals("N/A")) pro.Poster="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Poster = "";
			}
			
			
			try {
				pro.Metascore = obj.getString("Metascore");
				if(pro.Metascore.equals("N/A")) pro.Metascore="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Metascore = "";
			}
			
			
			try {
				pro.imdbRating = obj.getString("imdbRating") ;
				if(pro.imdbRating.equals("N/A")) pro.imdbRating="";
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				pro.imdbRating = "" ;
			}
			
			
			try {
				pro.imdbVotes = obj.getString("imdbVotes").replaceAll(",", "");
				if(pro.imdbVotes.equals("N/A")) pro.imdbVotes="";
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				pro.imdbVotes = "" ;
			}
			
			
			try {
				pro.imdbID = obj.getString("imdbID") ;
				if(pro.imdbID.equals("N/A")) pro.imdbID="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.imdbID = "";
			}
			
			try {
				pro.Type = obj.getString("Type") ;
				if(pro.Type.equals("N/A")) pro.Type="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Type = "";
			}
			
			try {
				pro.totalSeasons = obj.getString("totalSeasons");
				if(pro.totalSeasons.equals("N/A")) pro.totalSeasons="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.totalSeasons = "";
			}
			
			
			try {
				pro.seriesID = obj.getString("seriesID");
				if(pro.seriesID.equals("N/A")) pro.seriesID="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.seriesID = "";
			}
			

			try {
				pro.Season = obj.getString("Season");
				if(pro.Season.equals("N/A")) pro.Season="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Season = "";
			}
			
			try {
				pro.Episode = obj.getString("Episode");
				if(pro.Episode.equals("N/A")) pro.Episode="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Episode = "";
			}
			
			try {
				pro.Plot = obj.getString("Plot");
				if(pro.Plot.equals("N/A")) pro.Plot="";
				else{pro.Plot = "http://www.imdb.com/title/"+ pro.imdbID + "/plotsummary?ref_=tt_ov_pl";}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Plot = "";
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
		String episodes = "" ;
		List<Product> episode = new ArrayList<Product>() ;
		for(int i=944000;i<944100;i++)
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
				 else if (p.Type.equals("episode")) {
					 episodes += ProductSerie(p, episode) ;
					 System.out.println("on est dans le " + i + "eme id episode");
				 }	
				 
			 }
			
		}
		
		
		// on �crit le string dans un fichier
		Write_file.write_file("data_omdb_film.csv");
		System.out.println(films);
		Write_file.write_file("data_omdb_serie.csv");
		System.out.println(series);
		Write_file.write_file("data_omdb_episode.csv");
		System.out.println(episodes);
	//	System.out.println(film.size());
//		System.out.println(film.get(74).Actors.size());
			
	}
	
	public static String ProductMovie(Product p, List<Product> film){
		film.add(p) ;
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
	
	public static String ProductSerie (Product p , List<Product> serie){
		 serie.add(p) ;
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
	
	public static String ProductEpisode(Product p, List<Product> episode){
		episode.add(p) ;
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
