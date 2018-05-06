package pec;

import simulation.Individual;
public abstract class Event {
	protected Individual ind;
	protected double timestamp;
	
	public Event(Individual ind, double timestamp){
		this.ind = ind;
		this.timestamp = timestamp;
	}
	
	public abstract void procEvent();

}
