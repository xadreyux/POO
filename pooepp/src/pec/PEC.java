package pec;

import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * This is the class that contains the data structure of the Pending Event Container and add and remove events from it
 * 
 *
 */
public class PEC {
	/**
	 * Comparator used to order the events by time of occurrence
	 */
	private Comparator<Event> comparator;
	/**
	 * Queue of events ordered by time of occurrence
	 */
	private PriorityQueue<Event> queue;
	/**
	 * Constructs a PEC and creates a Comparator and Priority Queue 
	 */
	public PEC() {
		comparator = new TimeComparator();
		queue = new PriorityQueue<Event>(200, comparator);
	}
	/**
	 * Adds an event to the PEC
	 * @param event event to be added to the event queue
	 */
	public void addEvPEC(Event event) {
		queue.add(event);
	}
	/**
	 * Removes the next event from the PEC
	 * @return event in first place of the queue, with lowest time of occurrence (null if there are no more events in the PEC)
	 */
	public Event popEv() {
		if(!queue.isEmpty())
			return queue.poll();
		else
			return null;
			
	}
	
	@Override
	public String toString() {
		String ret = "";
		while (!queue.isEmpty())
		  ret = ret + queue.poll();
		return ret;
	}
	
}
