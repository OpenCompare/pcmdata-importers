
public class WikidataImageRetrieval {
	
	
	// propertyID: either P18 or P154
	public String retrieve(String entityID, String propertyID) {
		
		// first step
		String wikiDataReq = "https://www.wikidata.org/w/api.php?action=wbgetclaims&entity=" + entityID + "&property=" + propertyID;
		
		// (2) request for getting the image name
		String imageName = processRequest(wikiDataReq);
		
		// (3) Wikimedia 
		String wikimediaReq = "https://commons.wikimedia.org/w/api.php?action=query&prop=imageinfo&iiprop=url&titles=File:" + imageName;
		
		// (4) get URL
		return getImageURL(wikimediaReq);
		
	}

	private String getImageURL(String wikimediaReq) {
		// TODO Auto-generated method stub
		return null;
	}

	private String processRequest(String wikiDataReq) {
		// TODO Auto-generated method stub
		return null;
	}

}
