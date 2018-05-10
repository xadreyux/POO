package simulation;

import java.util.LinkedList;
import java.util.Random;

class Point {
	int r;
	int c;
	private int[] edges;

	public Point(int c, int r) {
		this.r = r;
		this.c = c;
		edges = new int[4];
	}
	
	int getDir() {
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
	
	int getCost(int edge) {
		return edges[edge];
	}
	
	void setEdgeCost(int edge, int cost) {
		edges[edge] = cost;
	}

	@Override
	public String toString() {
		return "(" + c + "," + r + ")";
	}

}
