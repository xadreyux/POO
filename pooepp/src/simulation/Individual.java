package simulation;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
/**
 * Class that contains the information about an Individual, including a Linked List of the path he has traveled and the correspondent cost,
 * its comfort, time of death and if it is alive or dead. Contains methods to update its information, to create a child individual with a portion
 * of its path and to calculate a time of occurrence of an event associated with it, based on its current parameters
 * 
 *
 */
public class Individual {
	/**
	 * current comfort of this individual
	 */
	double comfort;
	/**
	 * time of death of this individual
	 */
	double deathtime;
	/**
	 * cost of the path traveled by this individual
	 */
	private int cost;
	/**
	 * "true" if this individual is alive, "false" otherwise
	 */
	boolean isAlive;
	/**
	 * list of points correspondent to the path traveled by this individual
	 */
	private LinkedList<Point> path;
	
	/**
	 * constructs an alive individual and updates its path with its initial point
	 * @param birthpoint initial point of the individual
	 */
	public Individual(Point birthpoint) {
		path = new LinkedList<Point>();
		path.add(birthpoint);
		cost = 0;
		isAlive = true;
	}
	/**
	 * calculates and updates the comfort of this individual based on the information of the grid and simulation
	 * @param targetpoint final point of the grid
	 * @param comfort_sens comfort sensitivity to small variations of the parameters
	 * @param cmax maximum edge cost of the grid
	 * @param ncols total number of columns of the grid
	 * @param nrows total number of rows of the grid
	 */
	public void calcComfort(Point targetpoint, int comfort_sens, int cmax, int ncols, int nrows) {
		Point currpoint = path.peek();
		int length = path.size();
		int distance = Math.abs(currpoint.c - targetpoint.c) + Math.abs(currpoint.r - targetpoint.r);
		double left_side = 1.0 - (cost - length + 2.0) / ((cmax - 1.0) * length + 3.0);
		double right_side = 1.0 - distance / (ncols + nrows + 1.0);
		comfort = Math.pow(left_side * right_side, comfort_sens);
	}
	/**
	 * Creates a new individual with a path composed of 90% of this individual's path plus a fraction, equal to this individuals comfort, of the 
	 * remaining 10%
	 * @return newly created individual
	 */
	public Individual repInd() {
		int child_pathsize = (int) Math.ceil((0.9 * path.size())+(0.1 * comfort * path.size()));
		LinkedList<Point> child_path = new LinkedList<Point>(path.subList(path.size()-child_pathsize, path.size()));
		Individual child = new Individual(child_path.peek());
		child.path = child_path;
		child.cost = child.getPathCost();
		return child;
	}
	/**
	 * updates this individual's path with a new point, as well as its cost, and if the point to be added already belongs to the path,
	 * erases the loop from the path.
	 * @param currPoint original position of the individual before the point to be added
	 * @param nextPoint point to be added to the path
	 * @param dir direction of the movement between the two points, from the perspective of the first point
	 */
	public void updatePath(Point currPoint, Point nextPoint, int dir) {
		ListIterator<Point> iter = path.listIterator();
		
		int isRepeated = 0;
		while(iter.hasNext()) {
			Point aux = iter.next();
			if(aux == (nextPoint)) {
				path.subList(0, iter.previousIndex()).clear();
				isRepeated = 1;
				break;
			}
		}
		if(isRepeated == 0) {
			cost += currPoint.getCost(dir);
			path.addFirst(nextPoint);
		}
		else
			cost = getPathCost();
		
	}
	/**
	 * marks the individual as dead
	 */
	public void killInd() {
		isAlive = false;
	}
	/**
	 * Calculates a time of occurrence of an event associated with this individual based on the specified parameter related to the event
	 * @param param parameter related to the event
	 * @return time of occurrence of event
	 */
	public double calcTimeStamp(int param) {
		Random rand = new Random();
		double m = (1.0 - Math.log(1.0 - comfort))*param;
		
		return -m*Math.log(1.0-rand.nextDouble());
	}
	/**
	 * Iterates the path traveled by this individual and calculates its total cost
	 * @return total path cost
	 */
	public int getPathCost() {
		ListIterator<Point> iter = path.listIterator();
		Point a = iter.next();
		Point b;
		int cost = 0;
		while(iter.hasNext()) {
			b = a;
			a = iter.next();
			cost += getEdgeCost(a, b);
		}
		return cost;
	}
	/**
	 * Returns the edge cost between two specified points
	 * @param a first point
	 * @param b second point
	 * @return edge cost
	 */
	public int getEdgeCost(Point a, Point b) {
		if(b.c - a.c == 1)
			return a.getCost(1);
		else if(b.c - a.c == -1)
			return a.getCost(3);
		else if(b.r - a.r == 1)
			return a.getCost(0);
		else if(b.r - a.r == -1)
			return a.getCost(2);
		
		return -1;		
	}
	/**
	 * Returns the current position of the individual
	 * @return first element of the path data structure
	 */
	public Point getCurrPoint() {
		return path.peek();
	}
	/**
	 * Returns the time of death of this individual
	 * @return time of death
	 */
	public double getDeathTime() {
		return deathtime;
	}
	/**
	 * Returns the path traveled by this individual
	 * @return path of the individual
	 */
	public LinkedList<Point> getPath() {
		return path;
	}
	/**
	 * Returns the current comfort of this individual
	 * @return comfort of the individual
	 */
	public double getComfort() {
		return comfort;
	}
	
	
	@Override
	public String toString() {
		//return "Path: "+Arrays.toString(path.toArray()) +"    comfort: "+comfort+"    cost: "+cost+"    inddeathtime: "+deathtime;
		return "inddeathtime: "+deathtime;
	}
}
