import java.util.Queue;
import java.util.LinkedList;

public class PercolationBFS extends PercolationDFSFast{

	public PercolationBFS(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void dfs(int row, int col){
		// these arrays are for operations to check the left, right, upper, lower grid
		int[] rowOps = {-1,1,0,0};
        int[] colOps = {0,0,-1,1};
        
		if (! inBounds(row,col)) return;
		if (isFull(row, col)||!isOpen(row, col)) return;
		Queue<Integer> q= new LinkedList<>();
		myGrid[row][col] = FULL;
		q.add(row * myGrid.length + col);
		
		while (q.size() != 0) {
			int i = q.remove();
			for(int j=0; j < 4; j++){
				col = colOps[j] + i%myGrid.length;
                row = rowOps[j] + i/myGrid.length;
                
                if (row >= 0 && col >= 0 && row < myGrid.length && col < myGrid.length && isOpen(row,col) && myGrid[row][col] != FULL) {
                    q.add(row * myGrid.length + col);
                    myGrid[row][col] = FULL;
                }
            }
		}
	}

}
