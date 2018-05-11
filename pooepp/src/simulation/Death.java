package simulation;

import pec.Event;
/**
 * Class that extends the abstract class Event and implements the Death event
 * 
 *
 */
class Death extends Event {
	/**
	 * /**
	 * Constructs an event of type Death with a specified instant of time and individual
	 * @param ind individual to which the event is associated
	 * @param timestamp instant of time in which the event will occur
	 */
	public Death(Individual ind, double timestamp) {
		super(ind, timestamp);
	}
	
	/**
	 * Marks the individual as dead and removes him from the list of alive individuals 
	 */
	@Override
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
