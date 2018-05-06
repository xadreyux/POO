package simulation;

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
		double left_side = 1 - (cost - length + 2) / ((cmax - 1) * length + 3);
		double right_side = 1 - distance / (ncols + nrows);
		comfort = Math.pow(left_side * right_side, comfort_sens);
	}

	public Individual repInd() {
		int child_pathsize = (int) Math.ceil(0.9 * path.size());
		LinkedList<Point> child_path = new LinkedList<Point>(path.subList(0, child_pathsize));
		Individual child = new Individual(child_path.peek());
		child.path = child_path;
		return child;
	}

	public boolean moveInd(Grid grid) {
		ListIterator<Point> iter = path.listIterator();
		Random rand = new Random();
		Point currpoint = path.peek();
		Point targetpoint = null;
		LinkedList<Integer> possible_moves = new LinkedList<Integer>();
		for (int i = 0; i < 4; i++) {
			if (currpoint.edges[i] != 0)
				possible_moves.add(i);
		}
		int chosen_step = rand.nextInt(possible_moves.size());
		

		switch (possible_moves.get(chosen_step)) {
		case 0:
			targetpoint = grid.pGrid[currpoint.c][currpoint.r + 1];
			cost += currpoint.edges[0];
		case 1:
			targetpoint = grid.pGrid[currpoint.c + 1][currpoint.r];
			cost += currpoint.edges[1];
		case 2:
			targetpoint = grid.pGrid[currpoint.c][currpoint.r - 1];
			cost += currpoint.edges[2];
		case 3:
			targetpoint = grid.pGrid[currpoint.c - 1][currpoint.r];
			cost += currpoint.edges[3];
		}
		
		while(iter.hasNext()) {
			if(iter.next().equals(targetpoint)) {
				path.subList(0, iter.previousIndex()).clear();
				if(targetpoint.equals(grid.pGrid[grid.cfin][grid.rfin]))
					return true;
				else
					return false;
			}
		}
		
		path.add(targetpoint);
		
		if(targetpoint.equals(grid.pGrid[grid.cfin][grid.rfin]))
			return true;
		else
			return false;
	}
	
	public Individual killInd() {
		isAlive = false;
		return this;
	}

	public double calcTimeStamp(int param) {
		Random rand = new Random();
		double m = (1 - Math.log(1 - comfort))*param;
		
		return -m*Math.log(1.0-rand.nextDouble());
	}
}
