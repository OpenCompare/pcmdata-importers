package JSONformating;

import java.util.regex.Pattern;

public class Dummy {

	public static void main(String[] args) {
		String test = "https://static.openfoodfacts.org/images.jpg/products/007/092/047/4366/front_en.6.400.jpg";
		System.out.println(Pattern.matches(".*\\.jpg\\s*$", test));

		/*
		 * + "|[\\.][s][v][g][\\s]*$"
		 * + "|[\\.][j][p][e][g][\\s]*$"
			+ "|[\\.][b][m][p][\\s]*$"
			+ "|[\\.][p][n][g][\\s]*$"
			+ "|[\\.][g][i][f][\\s]*$")
		 */
		
	}

}
