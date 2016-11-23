package data_omdb;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

public class Product {
	
	//tous les param�tres du produits
	String Title ;
	String Year ;
	String Rated  ;
	String Released ;
	String Runtime  ;
	List<String> Genre ;
	String Director  ;
	String Writer ;
	List<String> Actors ;
	String Plot ;
	List<String> Language ;
	List<String> Country ;
	String Poster ;
	String Metascore ;
	String imdbRating ; // format 7.7
	String imdbVotes ; // format 802,661
	String imdbID ;
	String Type ;
	String totalSeasons ;
	String seriesID ;
	String Season;
	String Episode;
	
	public Product() throws JSONException{
		this.Title = "";
		this.Year = "" ;
		this.Rated = "" ;
		this.Released = "";
		this.Runtime = "" ;
		this.Genre = new ArrayList<String>() ;
		this.Director = "" ;
		this.Writer = "" ;
		this.Actors = new ArrayList<String>() ;
		this.Plot = "" ;
		this.Language = new ArrayList<String>() ;
		this.Country = new ArrayList<String>() ;
		this.Poster = "";
		this.Metascore = "" ;
		this.imdbRating = "" ;
		this.imdbVotes = "" ;
		this.imdbID = "" ;
		this.Type = "" ;
		this.totalSeasons = "";
		this.seriesID = "";
		this.Season = "";
		this.Episode = "";
	}
}
