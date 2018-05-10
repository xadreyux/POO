package simulation;

import pec.Event;

public class Reproduction extends Event{
	
	public Reproduction(Individual ind, double timestamp) {
		super(ind, timestamp);
	}
	
	
	public Individual procEvent() {
		Individual child;
		child = ind.repInd();
		return child;
	}
	
	@Override
	public String toString() {
		return "[REPRODUCTION] t="+timestamp;
	}

}
