package fr.orion78.markovText;

/**
 * Reads a MarkovChain and, using random numbers, generate a sentence from the
 * probabilities.
 *
 * @author Guillaume Turchini
 */
public class Generator {
	public static String[] generate(int nbLines) {
		StringBuffer sb = new StringBuffer();
		
		String[] lines = new String[nbLines];
		String s;
		
		for(int i = 0; i < nbLines; i++){
			s = ".";
			
			while(!(s = MarkovChain.getChain(s).getNextWithRandom()).equals(".")){
				sb.append(s);
				sb.append(" ");
			}
			
			sb.append(".");
			lines[i] = sb.toString();
			sb.setLength(0);
		}
		
		return lines;
	}
}
