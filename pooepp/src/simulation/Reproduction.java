package simulation;

import pec.Event;

public class Reproduction extends Event{
	
	public Reproduction(Individual ind, double timestamp) {
		super(ind, timestamp);
	}
	
	@Override
	public void procEvent() {
		ind.repInd();
	}

}
