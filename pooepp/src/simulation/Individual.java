package simulation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class Individual {
	double comfort;
	double deathtime;
	int cost;
	boolean isAlive;
	LinkedList<Point> path;

	public Individual(Point birthpoint) {
		path = new LinkedList<Point>();
		path.add(birthpoint);
		cost = 0;
		isAlive = true;
	}

	public void calcComfort(Point targetpoint, int comfort_sens, int cmax, int ncols, int nrows) {
		Point currpoint = path.peek();
		int length = path.size();
		int distance = Math.abs(currpoint.c - targetpoint.c) + Math.abs(currpoint.r - targetpoint.r);
		double left_side = 1.0 - (cost - length + 2.0) / ((cmax - 1.0) * length + 3.0);
		double right_side = 1.0 - distance / (ncols + nrows + 1.0);
		comfort = Math.pow(left_side * right_side, comfort_sens);
	}

	public Individual repInd() {
		int child_pathsize = (int) Math.ceil(0.9 * path.size())+(int)Math.round(0.1 * comfort * path.size());
		LinkedList<Point> child_path = new LinkedList<Point>(path.subList(path.size()-child_pathsize, path.size()));
		Individual child = new Individual(child_path.peek());
		child.path = child_path;
		child.cost = child.getPathCost();
		return child;
	}

	public void updatePath(Point currPoint, Point nextPoint, int dir) {
		ListIterator<Point> iter = path.listIterator();
		
		int isRepeated = 0;
		System.out.println("Next: " + nextPoint);
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
	
	public Individual killInd() {
		isAlive = false;
		return this;
	}

	public double calcTimeStamp(int param) {
		Random rand = new Random();
		double m = (1.0 - Math.log(1.0 - comfort))*param;
		
		return -m*Math.log(1.0-rand.nextDouble());
	}
	
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
	
	public Point getCurrPoint() {
		return path.peek();
	}
	
	public double getDeathTime() {
		return deathtime;
	}
	
	public LinkedList<Point> getPath() {
		return path;
	}
	
	public double getComfort() {
		return comfort;
	}
	
	
	@Override
	public String toString() {
		return "Path: "+Arrays.toString(path.toArray()) +"    comfort: "+comfort+"    cost: "+cost+"    inddeathtime: "+deathtime;
	}
}
