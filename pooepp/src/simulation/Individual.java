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
		double right_side = 1.0 - distance / (ncols + nrows + 0.0);
		comfort = Math.pow(left_side * right_side, comfort_sens);
	}

	public Individual repInd() {
		int child_pathsize = (int) Math.ceil(0.9 * path.size());
		LinkedList<Point> child_path = new LinkedList<Point>(path.subList(0, child_pathsize));
		Individual child = new Individual(child_path.peek());
		child.path = child_path;
		return child;
	}

	public void updatePath(Point point) {
		ListIterator<Point> iter = path.listIterator();

		while(iter.hasNext()) {
			if(iter.next().equals(point)) {
				path.subList(0, iter.previousIndex()).clear();
			}
		}
		
		path.addFirst(point);
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
	
	public Point getCurrPoint() {
		return path.peek();
	}
	
	@Override
	public String toString() {
		return "Path: "+Arrays.toString(path.toArray()) +"    comfort: "+comfort+"    cost: "+cost+"    deathtime: "+deathtime;
	}
}
