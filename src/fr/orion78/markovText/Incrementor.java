package fr.orion78.markovText;

public class Incrementor {
	private long value;
	
	public Incrementor() {
		this.value=1;
	}
	public void increment(){
		this.value++;
	}
	public long getValue(){
		return this.value;
	}
}
