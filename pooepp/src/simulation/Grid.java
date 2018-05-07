package simulation;

public class Grid {
	Point[][] pGrid;

	int ncols, nrows;
	int cini, rini, cfin, rfin;
	int nSCZones, nObs, cmax;

	public Grid(int n_cols, int n_rows) {
		pGrid = new Point[n_cols][n_rows];
		ncols = n_cols;
		nrows = n_rows;
		for (int i = 0; i < n_cols; i++) {
			for (int j = 0; j < n_rows; j++) {
				pGrid[i][j] = new Point(i + 1, j + 1);
				if (i != 0)
					pGrid[i][j].edges[3] = 1;
				if (i != n_cols - 1)
					pGrid[i][j].edges[1] = 1;
				if (j != 0)
					pGrid[i][j].edges[2] = 1;
				if (j != n_rows - 1)
					pGrid[i][j].edges[0] = 1;
			}
		}
		cmax = 1;
	}

	public void addObstacle(int c, int r) {
		int i = c - 1;
		int j = r - 1;
		if (i != 0) {
			pGrid[i][j].edges[3] = 0;
			pGrid[i - 1][j].edges[1] = 0;
		}
		if (i != ncols - 1) {
			pGrid[i][j].edges[1] = 0;
			pGrid[i + 1][j].edges[3] = 0;
		}
		if (j != 0) {
			pGrid[i][j].edges[2] = 0;
			pGrid[i][j - 1].edges[0] = 0;
		}
		if (j != nrows - 1) {
			pGrid[i][j].edges[0] = 0;
			pGrid[i][j + 1].edges[2] = 0;
		}
	}

	public void addSCZone(int c_init, int r_init, int c_end, int r_end, int cost) {
		for (int i = c_init - 1; i <= c_end - 1; i++) {
			if (i != c_init - 1 && i != 0) {
				pGrid[i][r_init - 1].edges[3] = Math.max(pGrid[i][r_init - 1].edges[3], cost);
				pGrid[i][r_end - 1].edges[3] = Math.max(pGrid[i][r_end - 1].edges[3], cost);
			}
			if (i != c_end - 1 && i != ncols - 1) {
				pGrid[i][r_init - 1].edges[1] = Math.max(pGrid[i][r_init - 1].edges[1], cost);
				pGrid[i][r_end - 1].edges[1] = Math.max(pGrid[i][r_end - 1].edges[1], cost);
			}
		}
		for (int j = r_init - 1; j <= r_end - 1; j++) {
			if (j != r_init - 1 && j != 0) {
				pGrid[c_init - 1][j].edges[2] = Math.max(pGrid[c_init - 1][j].edges[2],cost);
				pGrid[c_end - 1][j].edges[2] = Math.max(pGrid[c_end - 1][j].edges[2],cost);
			}
			if (j != r_end - 1 && j != nrows - 1) {
				pGrid[c_init - 1][j].edges[0] = Math.max(pGrid[c_init - 1][j].edges[0],cost);
				pGrid[c_end - 1][j].edges[0] = Math.max(pGrid[c_end - 1][j].edges[0],cost);
			}
		}
		if(cost > cmax)
			cmax = cost;
	}
	
	public Point getNextPoint(Point curr, int dir) {
		switch (dir) {
		case 0:
			return pGrid[curr.c][curr.r + 1];
		case 1:
			return pGrid[curr.c + 1][curr.r];
		case 2:
			return pGrid[curr.c][curr.r - 1];
		case 3:
			return pGrid[curr.c - 1][curr.r];
		}
		return null;
	}

	@Override
	public String toString() {
		String ret = new String();
		for (int j = nrows - 1; j >= 0; j--) {
			for (int i = 0; i <= ncols - 1; i++) {
				ret = ret + pGrid[i][j].toString() + "\t";
			}
			ret = ret + "\n";
		}
		return ret;
	}
}


