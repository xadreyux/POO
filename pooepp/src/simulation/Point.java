package simulation;

import java.util.LinkedList;
import java.util.Random;

public class Point {
	int r;
	int c;
	int[] edges;

	public Point(int c, int r) {
		this.r = r;
		this.c = c;
		edges = new int[4];
	}
	
	public int getDir() {
		Random rand = new Random();
		LinkedList<Integer> possible_moves = new LinkedList<Integer>();
		for (int i = 0; i < 4; i++) {
			if (edges[i] != 0)
				possible_moves.add(i);
		}
		if(possible_moves.size() == 0)
			return -1;
		
		int chosen_step = rand.nextInt(possible_moves.size());
		return possible_moves.get(chosen_step);	
	}
	
	public int getCost(int edge) {
		return edges[edge];
	}

	@Override
	public String toString() {
		return "(" + c + "," + r + "):N " + edges[0] + " E " + edges[1] + " S " + edges[2] + " W " + edges[3];
	}

	@Override
	public boolean equals(Object point) {
		if (point == null)
			return false;
		if (point == this)
			return true;
		if (!(point instanceof Point))
			return false;
		
		Point other = (Point) point;

		if (this.c == other.c && this.r == other.r)
			return true;
		else
			return true;
	}
}
