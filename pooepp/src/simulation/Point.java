package simulation;

public class Point {
	int r;
	int c;
	int[] edges = new int[4];
	public Point(int[] _edges)
	{
		this.edges=_edges;
	}
	public Point (int _c, int _r)
	{
		this.r=_r;
		this.c=_c;
	}
	@Override
	public String toString()
	{
		return "("+c+","+r+"):N "+edges[0]+" E "+edges[1]+" S "+edges[2]+" W "+edges[3];
	}
}
