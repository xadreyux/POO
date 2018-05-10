package simulation;

import pec.Event;

public class Death extends Event {
	
	public Death(Individual ind, double timestamp) {
		super(ind, timestamp);
	}
	
	
	public void procEvent(Simulation sim) {
		if(ind.isAlive) {
			ind.killInd();
			sim.indAlive.remove(ind);
			sim.pop--;
			sim.nEv++;
		}
			
		
	}
	
	@Override
	public String toString() {
		return "[DEATH] t="+timestamp;
	}
}
