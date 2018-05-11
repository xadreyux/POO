package simulation;

import java.util.LinkedList;
import java.util.Random;
/**
 * Class that contains the information about each point of the grid. It's defined by its coordinate and by a vector of edges, that contain the cost
 * of each edge of the point in the order North, East, South and West
 *
 *
 */
class Point {
	/**
	 * row of the point
	 */
	int r;
	/**
	 * column of the point
	 */
	int c;
	/**
	 * vector of edges of the point
	 */
	private int[] edges;
	/**
	 * Constructs a point with the specified coordinates and initializes all edges with cost 1
	 * @param c column of the point
	 * @param r row of the point
	 */
	public Point(int c, int r) {
		this.r = r;
		this.c = c;
		edges = new int[4];
	}
	/**
	 * Generates a random direction based on the possible moves that can be made from this point, determined by the edges that are different from 0
	 * @return random direction of movement. Returns -1 if no movement is possible
	 */
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
	/**
	 * Returns the cost of the specified edge of this point
	 * @param edge edge of the point
	 * @return cost of the edge
	 */
	int getCost(int edge) {
		return edges[edge];
	}
	/**
	 * Sets the cost of the specified edge with the specified value 
	 * @param edge edge to be updated
	 * @param cost cost to be attributed
	 */
	void setEdgeCost(int edge, int cost) {
		edges[edge] = cost;
	}

	@Override
	public String toString() {
		return "(" + c + "," + r + ")";
	}

}
