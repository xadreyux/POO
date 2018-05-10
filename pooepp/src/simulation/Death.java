package simulation;

import pec.Event;

public class Death extends Event {
	
	public Death(Individual ind, double timestamp) {
		super(ind, timestamp);
	}
	
	
	public Individual procEvent() {
		ind.killInd();
		return ind;
	}
	
	@Override
	public String toString() {
		return "[DEATH] t="+timestamp;
	}
}
