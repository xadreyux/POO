package pec;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PEC {
	
	private Comparator<Event> comparator;
	private PriorityQueue<Event> queue;
	
	public PEC() {
		comparator = new TimeComparator();
		queue = new PriorityQueue<Event>(200, comparator);
	}
	
	public void addEvPEC(Event event) {
		queue.add(event);
	}
	
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
