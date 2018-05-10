package simulation;

import java.util.LinkedList;

class BestPath {
	
	private LinkedList<Point> bestPath;
	int cost;
	double comfort;
	boolean finalized;
	private Point dest;
	
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
	
	void update(Individual ind) {
		
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
	
	String beenHit() {
		if(finalized)
			return "yes";
		else
			return "no";
	}
	
	@Override
	public String toString() {
	
		String ret = "";
		
		for(int i = bestPath.size() - 1; i >= 0; i--) {
			ret = ret +bestPath.get(i);
			if(i != 0)
				ret = ret + ", ";
		}
		
		return "{"+ret+"}";
	}
}
