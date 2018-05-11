package simulation;


import pec.Event;
/**
 * Class that extends the abstract class Event and implements the Move event
 * 
 *
 */
class Move extends Event {
	/**
	 * grid of the simulation in which the event is taking place
	 */
	private Grid grid;
	/**
	 * Constructs an event of type Move with a specified individual, instant of time and grid
	 * @param ind individual to which the event is associated
	 * @param timestamp time of occurrence of the event
	 * @param grid simulation's grid
	 */
	public Move(Individual ind, double timestamp, Grid grid) {
		super(ind, timestamp);
		this.grid = grid;
	}
	/**
	 * Generates a random direction and gets the point that it will lead to, updates the individual's path with the new point, updates the
	 * individual's comfort and the simulations best path and generates a new Move Event, with a generated time of occurrence, and adds it to the PEC
	 */
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
