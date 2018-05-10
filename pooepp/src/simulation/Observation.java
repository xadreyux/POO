package simulation;

import pec.Event;

public class Observation extends Event{
	
	public Observation(double timestamp, int n)
	{
		super(timestamp, n);
	}

	@Override
	public void procEvent(Simulation sim) {
		System.out.println("Observation "+n+ ":\n \t "
				+ "Present instant:\t \t \t "+timestamp+ "\n \t "
				+ "Number of realized events: \t \t" + sim.nEv+ "\n \t "
				+ "Population size: \t \t \t"+ sim.pop+ "\n \t "
				+ "Final point has been hit: \t \t"+ sim.bestPath.beenHit()+ "\n \t "
				+ "Path of the best fit individual: \t"+sim.bestPath);
		
		if(sim.bestPath.finalized)
			System.out.println("\t Cost:\t \t \t \t \t "+sim.bestPath.cost);
		else
			System.out.println("\t Comfort: \t \t \t \t "+sim.bestPath.comfort);
			
				
			
		
	}
}
