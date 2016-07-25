package fr.orion78.markovText;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This contains words and the List of probabilities that of the words
 * that are following.
 *
 * @author Guillaume Turchini
 */
public class MarkovChain {
	private static Map<String, MarkovChain> singletons = new HashMap<String, MarkovChain>(); 
	
	public static MarkovChain getChain(String s){
		if(singletons.containsKey(s)){
			return singletons.get(s);
		} else {
			return createMarkovChain(s);
		}
	}
	
	private static synchronized MarkovChain createMarkovChain(String s){
		if(singletons.containsKey(s)){
			// Have already been created by someone else
			return singletons.get(s);
		} else {
			MarkovChain m = new MarkovChain(s);
			singletons.put(s, m);
			return m;
		}
	}

	private String s;
	private Map<String, Incrementor> nexts;
	private long max;
	
	private MarkovChain(String s) {
		this.s = s;
		this.nexts = new HashMap<String, Incrementor>();
		this.max = 0;
	}
	
	public synchronized void addNextWord(String s){
		this.max++;
		if(this.nexts.containsKey(s)){
			this.nexts.get(s).increment();
		} else {
			this.nexts.put(s, new Incrementor());
		}
	}

	public String getString() {
		return s;
	}
	
	public String getNextWithRandom(){
		long value = (long)(Math.random() * this.max);

		Set<Entry<String, Incrementor>> set = this.nexts.entrySet();
		
		for(Entry<String, Incrementor> e : set){
			value -= e.getValue().getValue();
			if(value < 0){
				return e.getKey();
			}
		}
		return null;
	}
}

