package data_omdb;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL ;
import java.nio.charset.StandardCharsets ;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * Import from OMDB REST APIs (JSON format) a "product representation" 
 * that is then transformed into a CSV
 *
 */
public class OMDBToProduct {
	
	private static final Logger _log = Logger.getLogger(OMDBToProduct.class.getName());
	
		
	/*
	 * TODO
	 * Could be parameters of procedures
	 */
	public static final int STARTING_OMDB_ID = 944000;
	public static final int NUMBER_OF_OMDB_PRODUCTS = 200; 

	
	public static OMDBProduct createProductFromJson(JSONObject obj) throws JSONException  {
		
		List<String> vide = new ArrayList<String>(); // FIXME very dangerous to reuse the same reference
		vide.add("");
		

		//on regarde si le film existe
		if(obj.getString("Response").equals("False")){
			// qunad le film n'existe pas
			return null ;
		}
		else{
			OMDBProduct pro = new OMDBProduct() ;			
			
			// TODO this is weird 
			// I will explain in the next session how to fix it
			// basically you need only one try/catch for handling JSONException
			// you need to initialize the String to empty string in case there is 
			// no key in the JSON (but normally the key is here!)
			// I also suggest to keep N/A => it means more than an empty cell!
			// Overall the solution will be 
			// pro.Title = obj.getString("Title"); and that's all!
			pro.Title = obj.getString("Title") ;
			
			// instead of 
			/*
			try {
				pro.Title = obj.getString("Title") ;
				if(pro.Title.equals("N/A")) pro.Title="";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				pro.Title = "";
			}*/
			
			
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
                pro.Genre= vide; // TODO: same list reference for all attributes!
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


	
	/**
	 * @param t OMDB entries can be movies, series, episodes, etc. 
	 * @return a CSV representation of all OMDB entries that are of type specified by t
	 * @throws JSONException
	 * @throws IOException
	 */
	public String mkCSV(OMDBMediaType t) throws JSONException, IOException {
		Collection<OMDBMediaType> omTypes = new ArrayList<OMDBMediaType>();
		omTypes.add(t);
		Map<OMDBMediaType, String> csvs = mkCSVs(omTypes);
		if (!csvs.containsKey(t))
			return "";
		return csvs.get(t);
	}
	
	
	/**
	 * TODO generalize and design a "filter" for retaining only some OMDB entries
	 * @param omTypes OMDB entries can be movies, series, episodes, etc.
	 * @return a CSV representation of all OMDB entries that are of one of the type specified by omTypes 
	 * @throws JSONException
	 * @throws IOException
	 */
	public Map<OMDBMediaType, String> mkCSVs(Collection<OMDBMediaType> omTypes) throws JSONException, IOException {
		
		
		Map<OMDBMediaType, String> omdbTypes2CSV = new HashMap<OMDBMediaType, String>();
	
		for(int i = STARTING_OMDB_ID ; i < (STARTING_OMDB_ID + NUMBER_OF_OMDB_PRODUCTS); i++)	{
					
			 OMDBProduct p  = createProductFromJson(idToJson(i)) ;
			 if (p != null)
			 {
				 // seeking if it is a movie, an episode, etc.
				 String oType = p.Type;
				 boolean found = false;
				 for (OMDBMediaType omType : omTypes) {					
					 if(oType.equals(omType.toString())) { 
						 found = true;
						 String pdt = omdbTypes2CSV.get(omType);
						 if (pdt == null)
							 omdbTypes2CSV.put(omType, "");
						 else {							 
							 pdt += OMDBCSVProductFactory.getInstance().mkCSVProduct(p, omType) + System.getProperty("line.separator");
							 omdbTypes2CSV.put(omType, pdt);
						 }
					 }					 
				}
				if(!found) 
					 _log.warning("Unknown type " + oType);  
						 
			 }
			 
			 else {
				 _log.warning("Unable to load OMDB entry " + i);  
			 }
		}
				
		return omdbTypes2CSV;		
	}
	



	
}
