package simulation;


import pec.Event;

class Move extends Event {
	
	private Grid grid;
	
	public Move(Individual ind, double timestamp, Grid grid) {
		super(ind, timestamp);
		this.grid = grid;
	}
	
	@Override
	public void procEvent(Simulation sim) {
		if(ind.isAlive) {
			Point currPoint = ind.getCurrPoint();
			int dir = currPoint.getDir();
			if(dir != -1) {
				Point nextPoint = grid.getNextPoint(ind.getCurrPoint(), dir);
				ind.updatePath(currPoint, nextPoint, dir);
			}
			ind.calcComfort(grid.getDest(), sim.comfortsens, grid.getCmax(), grid.getncols(), grid.getnrows());
			timestamp = ind.calcTimeStamp(sim.paramMove);
			if (sim.currentime + timestamp <= sim.finalinst && sim.currentime + timestamp < ind.getDeathTime())
				sim.pec.addEvPEC(new Move(ind, sim.currentime + timestamp, grid));
			sim.bestPath.update(ind);
			sim.nEv++;
		}
		
		
	}
	
	@Override
	public String toString() {
		return "[MOVE] t="+timestamp;
	}


}
