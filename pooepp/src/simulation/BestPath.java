package simulation;

import java.util.LinkedList;
/**
 * This class contains the data structure of the path of the best fit individual
 * 
 *
 */
class BestPath {
	/**
	 * Linked List of Points that correspond to the path of the best fit individual
	 */
	private LinkedList<Point> bestPath;
	/**
	 * cost of the path
	 */
	int cost;
	/**
	 * comfort of the individual at the end of the path
	 */
	double comfort;
	/**
	 * true if this path reaches the simulation's final point
	 */
	boolean finalized;
	/**
	 * final point to be considered
	 */
	private Point dest;
	/**
	 * Constructs a best path structure with the information corresponding to a specified individual and a specified final point
	 * @param dest simulation's final point
	 * @param ind individual with which to initialize the best path information
	 */
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
	/**
	 * Updates the best path with the information of a specified individual if its cost/comfort is better that of the current best path
	 * @param ind individual with the information to be considered when trying to update the best path
	 */
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
	/**
	 * Says if the simulation's final point is reached by the best path
	 * @return "yes" if the path reaches the final point, "no" otherwise
	 */
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
