package pec;

import simulation.Individual;
public abstract class Event {
	protected double timestamp;
	protected Individual ind;
	
	public Event(Individual ind, double timestamp){
		this.timestamp = timestamp;
		this.ind = ind;
	}
	
	public Event(double timestamp) {
		this.timestamp = timestamp;
	}
	
	public double getTime() {
		return timestamp;
	}
	
	public Individual getInd() {
		return ind;
	}
	
	public abstract Individual procEvent();

}
