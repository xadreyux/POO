package simulation;

import pec.Event;
/**
 * Class that extends the abstract class Event and implements the Observation event
 *
 *
 */
class Observation extends Event{
	/**
	 * number of the Observation
	 */
	private int n;
	/**
	 * Constructs an event of type Observation with a specified instant of time and number of observation
	 * @param timestamp time of occurrence of the event
	 * @param n number of the observation
	 */
	public Observation(double timestamp, int n)
	{
		super(timestamp);
		this.n = n;
	}
	/**
	 * Prints the information about the state of the simulation in the current instant of time
	 */
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
