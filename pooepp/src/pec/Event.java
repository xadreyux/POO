package pec;

import simulation.Individual;
import simulation.Simulation;
/**
 * This is the abstract class that will take the form of the multiple events possible of the simulation
 * 
 *
 */
public abstract class Event {
	/**
	 * instant of time in which this event will occur
	 */
	protected double timestamp;
	
	/**
	 * individual with which this event is associated
	 */
	protected Individual ind;
	
	/**
	 * Constructs an event with a specified instant of time and individual
	 * @param ind individual to which the event is associated
	 * @param timestamp instant of time in which the event will occur
	 */
	public Event(Individual ind, double timestamp){
		this.timestamp = timestamp;
		this.ind = ind;
	}
	/**
	 * Constructs an event with a specified instant of time
	 * @param timestamp instant of time in which the event will occur
	 */
	public Event(double timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * Returns the instant of time in which the event will occur
	 * @return this event's instant of time
	 */
	public double getTime() {
		return timestamp;
	}
	/**
	 * Returns the individual with which this event is associated
	 * @return this event's individual
	 */
	public Individual getInd() {
		return ind;
	}
	/**
	 * Executes the necessary operations and methods of the event
	 * @param sim the simulation in which the event is taking place
	 */
	public abstract void procEvent(Simulation sim);

}
