package simulation;

import pec.Event;

public class Death extends Event {
	
	public Death(Individual ind, double timestamp) {
		super(ind, timestamp);
	}
	
	
	public void procEvent() {
		ind.killInd();
	}
}
