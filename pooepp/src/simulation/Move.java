package simulation;

import pec.Event;
public class Move extends Event {
	
	public Move(Individual ind, double timestamp) {
		super(ind, timestamp);
	}
	
	public void procEvent(Grid grid) {
		ind.moveInd(grid);
	}


	@Override
	public void procEvent() {
		System.out.println("Event move didnt receive a Grid");
	}


}
