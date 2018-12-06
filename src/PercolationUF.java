import java.util.Arrays;

public class PercolationUF implements IPercolate{
	private boolean[][] myGrid;
	private IUnionFind myFinder;
	private int nOpen;
	private int mySize = 0;
	private final int VTOP;
	private final int VBOTTOM;

	
	public PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size];
		VTOP = size*size;
		VBOTTOM = size*size + 1; 
		mySize = size; 
		 
		for(boolean[] temp: myGrid) {
			Arrays.fill(temp, false);
		}
		finder.initialize(size*size + 2);
		myFinder = finder; 
	}
	
	public int getIndex(int row, int col) {
		if (! inBounds(row, col)) {
            throw new IndexOutOfBoundsException(); 
        }
		int index = 0;
		index = col+mySize * (row); 
		return index;
	}
	
	
	public void updateOnOpen(int row, int col) {
		if (! inBounds(row, col)) {
            throw new IndexOutOfBoundsException(); 
		}
		
		if (row == 0) { 
			myFinder.union(getIndex(row, col), VTOP);
		}
		if (row == mySize-1) { 
			myFinder.union(getIndex(row, col), VBOTTOM); 
	    }
		if (inBounds(row+1, col) && isOpen(row + 1, col)) { 
			myFinder.union(getIndex(row, col), getIndex(row + 1, col)); 
		}
		if (inBounds(row-1, col) && isOpen(row - 1, col)) { 
			myFinder.union(getIndex(row, col), getIndex(row - 1, col)); 
	    }
		if (inBounds(row, col+1) && isOpen(row, col + 1)) { 
			myFinder.union(getIndex(row, col), getIndex(row, col + 1)); 
		}
		if (inBounds(row, col-1) && isOpen(row, col - 1)) { 
			myFinder.union(getIndex(row, col), getIndex(row, col - 1));
		}		
	}
	
	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row, col)) {
            throw new IndexOutOfBoundsException(); 
        }
		return myGrid[row][col]; 
	}

	@Override
	public void open(int row, int col) {
		if (! inBounds(row, col)) {
            throw new IndexOutOfBoundsException();
		}
		if(! isOpen(row, col)) {
			myGrid[row][col] = true; 
			nOpen += 1; 
			updateOnOpen(row, col); 
		}
	}

	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row, col)) throw new IndexOutOfBoundsException("index out of bounds");
        return myFinder.connected(VTOP, getIndex(row, col)); 
	}

	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM); 
	}

	@Override
	public int numberOfOpenSites() {
		return nOpen; 
	}
	

	public boolean inBounds(int row, int col) {
		if (row < 0 || row >= mySize||col < 0 || col >= mySize) return false; 
		return true;
	}

}
