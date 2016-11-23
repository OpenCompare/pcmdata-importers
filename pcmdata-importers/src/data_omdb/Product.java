package data_omdb;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

public class Product {
	
	//tous les param�tres du produits
	String Title ;
	int Year ;
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
	int Metascore ;
	float imdbRating ; // format 7.7
	int imdbVotes ; // format 802,661
	String imdbID ;
	String Type ;
	int totalSeasons ;
	String seriesID ;
	
	public Product() throws JSONException{
		this.Title = "";
		this.Year = -1 ;
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
		this.Metascore = -1 ;
		this.imdbRating = -1 ;
		this.imdbVotes = -1 ;
		this.imdbID = "" ;
		this.Type = "" ;
		this.totalSeasons = -1;
		this.seriesID = "";
	}
}
