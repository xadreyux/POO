package simulation;


import pec.Event;

public class Move extends Event {
	
	Grid grid;
	
	public Move(Individual ind, double timestamp, Grid grid) {
		super(ind, timestamp);
		this.grid = grid;
	}
	
	@Override
	public Individual procEvent() {
		Point currPoint = ind.getCurrPoint();
		int dir = currPoint.getDir();
		if(dir != -1) {
			Point nextPoint = grid.getNextPoint(ind.getCurrPoint(), dir);
			ind.updatePath(currPoint, nextPoint, dir);
		}
		
		return ind;
	}
	
	@Override
	public String toString() {
		return "[MOVE] t="+timestamp;
	}


}
