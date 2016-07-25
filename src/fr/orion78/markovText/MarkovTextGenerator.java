package fr.orion78.markovText;

import java.io.File;
import java.util.List;
import java.util.Stack;

public class MarkovTextGenerator {
	public static void main(String[] args){
		Stack<File> files = new Stack<File>();
		files.push(new File("inputs"));
		
		while(!files.isEmpty()){
			File f = files.pop();
			if(f.isDirectory()){
				for(File child : f.listFiles()){
					files.push(child);
				}
			} else {
				System.out.println(f.getAbsolutePath());
				List<String> l = Parser.parse(f);
				MarkovChainCreator.populate(l);
			}
		}
		
		String[] generatedStrings = Generator.generate(20);
		
		for(String s : generatedStrings){
			System.out.println(s);
		}
	}
}
