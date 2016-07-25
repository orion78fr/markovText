package fr.orion78.markovText;

import java.util.List;


/**
 * Gets a List of Strings and create the MarkovChain corresponding to it. 
 *
 * @author Guillaume Turchini
 */
public class MarkovChainCreator {
	public static void populate(List<String> l) {
		String oldStr = ".";
		for(String s : l){
			if(oldStr.equals(s) && s.equals(".")){
				continue;
			}
			MarkovChain.getChain(oldStr).addNextWord(s);
			oldStr = s;
		}
	}
}
