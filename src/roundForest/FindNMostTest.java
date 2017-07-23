package roundForest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;

public class FindNMostTest {
	
	@Test
	public void FindNMostTest() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("abc");al.add("bcd");al.add("bcd");al.add("bcd");al.add("cde");al.add("cde");
		String[] results =FindNMostFrequentOccurences(al, 2);
		String[] expectedResults = {"bcd","cde"};
		for (int i = 0; i<results.length;i++){
			assertTrue(results[i].equals(expectedResults[i]));
		}
		
		
	}
	
	public static String[] FindNMostFrequentOccurences(ArrayList<String> profileName,int n) {
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		//iterate through our data 
		for(int i = 0; i< profileName.size(); i++){
			//Here we count how many occurences and place inside hashmap for O(1) extraction
			String current_id = profileName.get(i).toString();
			// this means that it's not found in our array yet, so put it in and set it's count to 1
			if(hmap.get(current_id) == null){
				hmap.put(current_id, 1);
			} else {
				//Otherwise increase the count
				int current_count = hmap.get(current_id);
				current_count += 1;
				hmap.put(current_id, current_count);
			}
		}
		//multimaps can be used to sort by value of the key value pair which will be useful when finding the top 1000.
		// Because a multimap can have many values to one key, so we reverse the hash and make it count1->(stringA,StringB)
		ListMultimap<Integer, String> multimap = ArrayListMultimap.create();
		hmap.entrySet().forEach(entry -> {
			multimap.put(entry.getValue(), entry.getKey());
		}); 
		//We want to store the values in alphabetical order, only up to 1000 entries as specified in requirements.
		String[] alphabetical_results = new String[n];
		
		for (int i = 0; i < n; i++){
			//in case we have data less than 1000 entries
			if (!multimap.isEmpty()){
				//get last because the hash goes from 1->n and we want highest count
				int lastKey = Iterables.getLast(multimap.keys());				
				String lastValue = Iterables.getLast(multimap.values());
				//Remove one of the values, meaning there would still be other values for that key
				//makes sure we don't just remove one value per key
				multimap.remove(lastKey, lastValue);
				//set our array to the value we found
				alphabetical_results[i] = lastValue;
				System.out.println(i+1+": "+lastValue+", Occurences: "+lastKey);
			}
		}
		return alphabetical_results;
	}
}
