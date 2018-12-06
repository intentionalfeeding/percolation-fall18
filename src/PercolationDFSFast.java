
public class PercolationDFSFast extends PercolationDFS {

	public PercolationDFSFast(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void updateOnOpen(int row, int col) {
		if (! inBounds(row,col)) throw new IndexOutOfBoundsException("index out of bounds");
		if (row == 0 || isFull(row, col) || inBounds(row+1, col) && isFull(row+1, col) || inBounds(row-1, col) && isFull(row-1, col) || inBounds(row, col+1) && isFull(row, col+1) || inBounds(row, col-1) && isFull(row, col-1))
		dfs(row,col);
	}

}
