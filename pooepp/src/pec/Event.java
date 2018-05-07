package pec;

import simulation.Individual;
public abstract class Event {
	double timestamp;
	protected Individual ind;
	
	public Event(Individual ind, double timestamp){
		this.timestamp = timestamp;
		this.ind = ind;
	}
	
	public Event(double timestamp) {
		this.timestamp = timestamp;
	}
	
	public abstract void procEvent();

}
