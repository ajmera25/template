package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class fileUtils {

	private String MOVIE_TEST_DATA = "/testData/movies.txt";
	
	public void readMovieName() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/test/resources/" + MOVIE_TEST_DATA));
			String line ="";
			while ((line = br.readLine()) != null) {
				
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			br.close();
		}
	} 
}
