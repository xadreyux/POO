package simulation;

import java.util.LinkedList;

public class Individual {
	int comfort;
	float deathtime;
	LinkedList<Point> path;
	int cost;
	public Individual(Point birthpoint) 
	{
		path = new LinkedList<Point>();
		path.add(birthpoint);
		cost=0;
	}

	public double calcComfort(Point targetpoint, int comfort_sens, int cmax, int ncols, int nrows) {
		Point currpoint=path.peek();
		int length = path.size();
		int distance=(currpoint.c-targetpoint.c)+(currpoint.r-targetpoint.r);
		double left_side=1-(cost-length+2)/((cmax-1)*length+3);
		double right_side=1-distance/(ncols+nrows);
		return Math.pow(left_side*right_side,comfort_sens);
	}

	public void moveInd(Point point, int leap) {
		path.add(point);
		cost+=leap;
	}
	
	public Individual repInd(Point targetpoint)
	{
		double child_pathsize=Math.ceil(0.9*path.size());
		LinkedList<Point> child_path = new LinkedList<Point> (path.subList(0, (int)(child_pathsize)));
		Individual child = new Individual(child_path.peek());
		child.path=child_path;
		return child;
	}
	
	public int moveWhere()
	{
		Point currpoint=path.peek();
		LinkedList<Integer>possible_moves=new LinkedList<Integer>();
		for (int i=0;i<4;i++)
		{
			if(currpoint.edges[i] != 0)
				possible_moves.add(i);
		}
		int chosen_step=(int) Math.floor(Math.random() * possible_moves.size());
		return possible_moves.get(chosen_step);
	}

	public float calcTimeStamp(int param) {
		return 1;
	}
}
