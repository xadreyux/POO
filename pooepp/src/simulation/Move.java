package simulation;

import pec.Event;
import simulation.Grid;

public class Move extends Event {
	
	Grid grid;
	
	public Move(Individual ind, double timestamp, Grid grid) {
		super(ind, timestamp);
		this.grid = grid;
	}
	
	public void procEvent() {
		Point currPoint = ind.getCurrPoint();
		int dir = currPoint.getDir();
		Point nextPoint = grid.getNextPoint(ind.getCurrPoint(), dir);
		ind.updatePath(nextPoint);
	}




}
