package pec;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PEC {
	
	Comparator<Event> comparator;
	PriorityQueue<Event> queue;
	
	public PEC() {
		comparator = new TimeComparator();
		queue = new PriorityQueue<Event>(200, comparator);
	}
	
	public void addEvPEC(Event event) {
		queue.add(event);
	}
}
