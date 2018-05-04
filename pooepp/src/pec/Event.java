package pec;

import simulation.Individual;
public abstract class Event {
	Individual ind;
	float timestamp;
	
	public Event(Individual ind, float timestamp){
		this.ind = ind;
		this.timestamp = timestamp;
	}
	
	public abstract void procEvent();

}
