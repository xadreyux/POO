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
