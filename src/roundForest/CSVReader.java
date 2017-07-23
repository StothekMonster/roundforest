package roundForest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
	
	// Variable Declarations
	private ArrayList<String> profileName = new ArrayList<String>();
	private ArrayList<String> productID   = new ArrayList<String>();
	private ArrayList<String> reviews     = new ArrayList<String>();
	
	//Constructor
	public CSVReader(String filePath, String splitCharacter) {
		BufferedReader br = null;
        String line = "";  // line variable is the line of input data currently worked on from input file
        String csvSplitBy = splitCharacter;  //character that we're splitting on such as comma
        
        try {
        	//read file
            br = new BufferedReader(new FileReader(filePath));
            //dismiss file header (column names)
            if ((line = br.readLine()) != null) {
            	//do nothing (removes header line)
            }
            //now the rest of the lines are data we're looking for
            while ((line = br.readLine()) != null) {
                // use comma as separator
            	String[] l = line.split(csvSplitBy);
            	addData(l);
            }
            //exceptions if file not found or IOExceptions...
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
	}
	//3 getters for the private variables
	public ArrayList<String> getProfileName() {
		return profileName;
	}
	
	public ArrayList<String> getProductIds() {
		return productID;
	}
	
	public ArrayList<String> getAllReviewWords() {
		return reviews;
	}
	
	// Method to extract data from input line. Is used in this way (by taking in a full line and assigning values
	// in order to reduce the amount of times the data is read, opposed to making the input the column name to extract.
	private void addData(String[] data) {
		this.profileName.add(data[3]);
		this.productID.add(data[1]);
		String[] reviewWords = data[9].split(" ");
		for (int i = 0; i< reviewWords.length; i++){
			this.reviews.add(reviewWords[i]);
		}
	}
}
