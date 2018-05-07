package simulation;

import pec.Event;

public class Reproduction extends Event{
	
	public Reproduction(Individual ind, double timestamp) {
		super(ind, timestamp);
	}
	
	
	public void procEvent() {
		ind.repInd();
	}

}
