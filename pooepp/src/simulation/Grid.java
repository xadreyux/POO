package simulation;

public class Grid {
	Point[][] pGrid;
	int i;
	int j;
	int ncols;
	int nrows;
	int[] pointstarter= new int[4];
	public Grid(int n_cols, int n_rows)
	{
		pGrid =new Point[n_cols][n_rows];
		ncols=n_cols;
		nrows=n_rows;
		for (i=0;i<n_cols;i++)
		{
			for (j=0;j<n_rows;j++)
			{
				pGrid[i][j].c=i+1;
				pGrid[i][j].r=j+1;
				pGrid[i][j].edges=pointstarter;
				if (i != 0)
					pGrid[i][j].edges[3]=1;
				if (i != n_rows-1)
					pGrid[i][j].edges[1]=1;
				if (j != 0)
					pGrid[i][j].edges[0]=1;
				if (j != n_cols-1)
					pGrid[i][j].edges[2]=1;
			}
		}
	}
	public void addObstacle(int c, int r)
	{
		i=c-1;
		j=r-1;
		if (i != 0)
		{
			pGrid[i][j].edges[3]=0;
			pGrid[i-1][j].edges[1]=0;
		}
		if (i != ncols-1)
		{
			pGrid[i][j].edges[1]=0;
			pGrid[i+1][j].edges[3]=0;
		}
		if (j != 0)
		{
			pGrid[i][j].edges[2]=0;
			pGrid[i][j-1].edges[0]=0;
		}
		if (j != nrows-1)
		{
			pGrid[i][j].edges[0]=0;
			pGrid[i][j+1].edges[2]=0;
		}
	}
	public void addSCZone(int c_init, int r_init, int c_end, int r_end, int cost)
	{
		int cinit=c_init-1;
		int cend=c_end-1;
		int rinit=r_init-1;
		int rend=r_end-1;
		for (i=cinit;i<cend;i++)
		{
			if (i != cinit)
			{
				pGrid[i][rinit].edges[3]=cost;
				pGrid[i][rend].edges[3]=cost;
			}
			if (i != cend)
			{
				pGrid[i][rinit].edges[1]=cost;
				pGrid[i][rend].edges[1]=cost;
			}
		}
		for (j=rinit;j<rend;j++)
		{
			if (j != rinit)
			{
				pGrid[cinit][j].edges[0]=cost;
				pGrid[cend][j].edges[0]=cost;
			}
			if (j != rend)
			{
				pGrid[cinit][j].edges[2]=cost;
				pGrid[cend][j].edges[2]=cost;
			}
		}
	}
	@Override
	public String toString()
	{
		String ret = new String();
		for (i=0;i<ncols;i++)
		{
			for (j=nrows-1;j>0;j--)
			{
				ret = ret + pGrid[i][j].toString() + "\t";
			}
			ret=ret+"\n";
		}
		return ret;
	}
}
