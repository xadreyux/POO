package pec;

import java.util.Comparator;
import java.util.PriorityQueue;
import simulation.Individual;
import simulation.Death;
import simulation.Move;
import simulation.Reproduction;



public class PEC {
	
	Comparator<Event> comparator;
	PriorityQueue<Event> queue;
	
	public PEC() {
		comparator = new TimeComparator();
		queue = new PriorityQueue<Event>(200, comparator);
	}
	
	public void addEvPEC(Individual ind, String type, float timestamp) {
		if(type.equals("death"))
			queue.add(new Death(ind, timestamp));
		
		else if(type.equals("move")) 
			queue.add(new Move(ind, timestamp));
		
		else if(type.equals("reproduction")) 
			queue.add(new Reproduction(ind, timestamp));
		
		
	}
}
