package simulation;

import pec.Event;
/**
 * Class that extends the abstract class Event and implements the Reproduction event
 * 
 *
 */
class Reproduction extends Event{
	/**
	 * Constructs an event of type Reproduction with a specified individual and instant of time
	 * @param ind individual to which the event is associated
	 * @param timestamp time of occurrence of the event
	 */
	public Reproduction(Individual ind, double timestamp) {
		super(ind, timestamp);
	}
	/**
	 * Generates a new Individual, and its corresponding Events (Death, Move and Reproduction) adding them to the PEc, and adds it to the alive
	 * individuals list of the simulation. Also generates a new Reproduction Event for this Individual. All new events have its time of occurrence
	 * generated independently
	 */
	@Override
	public void procEvent(Simulation sim) {
		if(ind.isAlive) {
			Individual child;
			child = ind.repInd();
			
			timestamp = ind.calcTimeStamp(sim.paramRep);
			if (sim.currentime + timestamp <= sim.finalinst && sim.currentime + timestamp < ind.getDeathTime())
				sim.pec.addEvPEC(new Reproduction(ind, sim.currentime + timestamp));

			child.calcComfort(sim.grid.getDest(), sim.comfortsens, sim.grid.getCmax(), sim.grid.getncols(), sim.grid.getnrows());

			timestamp = child.calcTimeStamp(sim.paramDeath);
			if (sim.currentime + timestamp <= sim.finalinst) {
				sim.pec.addEvPEC(new Death(child, sim.currentime + timestamp));
			}

			child.deathtime = sim.currentime + timestamp;

			timestamp = child.calcTimeStamp(sim.paramMove);
			if (sim.currentime + timestamp <= sim.finalinst && sim.currentime + timestamp < child.deathtime)
				sim.pec.addEvPEC(new Move(child, sim.currentime + timestamp, sim.grid));

			timestamp = ind.calcTimeStamp(sim.paramRep);
			if (sim.currentime + timestamp <= sim.finalinst && sim.currentime + timestamp < child.deathtime)
				sim.pec.addEvPEC(new Reproduction(child, sim.currentime + timestamp));

			sim.indAlive.addFirst(child);
			sim.pop++;
			sim.nEv++;
		}
		
	
	}
	
	@Override
	public String toString() {
		return "[REPRODUCTION] t="+timestamp;
	}

}
