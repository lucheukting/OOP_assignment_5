package simpledatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {

  public static void main(String[] args) {

	Reader obj = new Reader();
	obj.run();

  }

  public void run() {

	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";

	try {

		br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+"CourseEnroll"+".csv")));
		while ((line = br.readLine()) != null) {

		        // use comma as separator
			String[] country = line.split(cvsSplitBy);

			System.out.println("first item: " + country[0] 
                                 + " second item = " + country[1] 
                                 + " third item = " + country[2]);

		}
		
		br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+"CourseEnroll"+".csv")));
		while ((line = br.readLine()) != null) {

	        // use comma as separator
		String[] country = line.split(cvsSplitBy);

		System.out.println("first item: " + country[0] 
                             + " second item = " + country[1] 
                             + " third item = " + country[2]);

		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	System.out.println("Done");
  }

}