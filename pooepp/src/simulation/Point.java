package simulation;

public class Point {
	int r;
	int c;
	int[] edges;

	public Point(int c, int r) {
		this.r = r;
		this.c = c;
		edges = new int[4];
	}

	@Override
	public String toString() {
		return "(" + c + "," + r + "):N " + edges[0] + " E " + edges[1] + " S " + edges[2] + " W " + edges[3];
	}
}
