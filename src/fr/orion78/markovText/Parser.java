package fr.orion78.markovText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Transform the source text(s) into a List of String containing words and 
 * dots, to give it to the MarkovChainCreator.
 * 
 *
 * @author Guillaume Turchini
 */
public class Parser {
	public static List<String> parse(File f){
		if(f == null){
			throw new NullPointerException("File f is null");
		}
		
		if(!f.exists() || !f.isFile() || !f.canRead()){
			throw new RuntimeException("The file doesn't exists, is a directory or cannot be read.");
		}
		
		
		List<String> l = new ArrayList<String>();
		
		BufferedReader r;
		try {
			r = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			// Should not happen, except in rare case of race condition of the file being deleted during the execution.
			throw new RuntimeException("Race condition?", e);
		} 
		
		String line;
		
		try {
			while((line = r.readLine()) != null){
				if(line.trim().equals("")){
					// Empty lines aren't interesting
					continue;
				}
				String[] sl = line.split("[ .]");
				for(String st : sl){
					if(st.equals("")){
						// A dot and a following space produces an empty string in the split table.
						l.add(".");
					} else {
						l.add(st.toLowerCase());
					}
				}
			}
		} catch (IOException e) {
			// Problem while reading, should normally not happen...
			throw new RuntimeException("Read error!", e);
		}
		
		try {
			r.close();
		} catch (IOException e) {
			// I really don't know how an IO error could happen on closing a read-only file...
		}
		
		return l;
	}
}
