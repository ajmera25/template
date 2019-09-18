package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	private String MOVIE_TEST_DATA = "/testData/movies.txt";
	
	public void readMovieName() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/test/resources/" + MOVIE_TEST_DATA));
			//String line ="";
			while ((br.readLine()) != null) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			br.close();
		}
	} 
}
