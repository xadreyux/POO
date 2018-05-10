package simulation;

import pec.Event;

class Reproduction extends Event{
	
	public Reproduction(Individual ind, double timestamp) {
		super(ind, timestamp);
	}
	
	
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
