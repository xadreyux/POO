package simulation;

import java.util.LinkedList;

public class BestPath {
	
	public LinkedList<Point> bestPath;
	int cost;
	double comfort;
	boolean finalized;
	Point dest;
	
	public BestPath(Point dest, Individual ind) {
		bestPath = new LinkedList<Point>(ind.getPath());
		cost = ind.getPathCost();
		comfort = ind.getComfort();
		this.dest = dest;
		if(ind.getCurrPoint() == dest)
			finalized = true;
		else
			finalized = false;
		
	}
	
	public void update(Individual ind) {
		
		if(finalized && ind.getCurrPoint() == dest) {
			if(ind.getPathCost() < cost) {
				cost = ind.getPathCost();
				comfort = ind.getComfort();
				bestPath = new LinkedList<Point>(ind.getPath());
			}
		}else if(!finalized) {
			if(ind.getCurrPoint() == dest) {
				finalized = true;
				cost = ind.getPathCost();
				comfort = ind.getComfort();
				bestPath = new LinkedList<Point>(ind.getPath());
			}else if(ind.getComfort() > comfort) {
				cost = ind.getPathCost();
				comfort = ind.getComfort();
				bestPath = new LinkedList<Point>(ind.getPath());
			}
		}
	}
	
	@Override
	public String toString() {
		return bestPath.toString()+"   cost= "+cost+"   comfort= "+comfort;
	}
}
