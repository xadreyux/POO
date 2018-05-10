package pec;

import simulation.Individual;
import simulation.Simulation;

public abstract class Event {
	protected double timestamp;
	protected Individual ind;
	protected int n;
	
	public Event(Individual ind, double timestamp){
		this.timestamp = timestamp;
		this.ind = ind;
	}
	
	public Event(double timestamp, int n) {
		this.timestamp = timestamp;
		this.n = n;
	}
	
	public double getTime() {
		return timestamp;
	}
	
	public Individual getInd() {
		return ind;
	}
	
	public abstract void procEvent(Simulation sim);

}
