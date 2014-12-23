package Model;


/**
 * Created by Maxime on 22/12/2014.
 */
public abstract class Box implements Comparable {
	protected int row;
	protected int column;

	/* ----- ----- Constructors ----- ----- */
	public Box() {
		this.row = 3;
		this.column = 3;
	}
	
	public Box(int r, int c) throws Exception {
		if(r<0 || c<0) {
			throw new Exception("Negatives values are forbidden.");
		}
		this.row = r;
		this.column = c;
	}
	
	/* ----- ----- Methodes ----- ----- */
	@Override
	public int compareTo(Object o) {
		int surface1 = this.getSurface();
		int surface2 = ((LittleBox)o).getSurface();
		if(surface1 > surface2) return -1;
		else if(surface1 == surface2) return 0;
		else return 1;
	}

	/* ----- ----- Accessors ----- ----- */
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public int getSurface() {
		return row*column;
	}

	@Override
	public String toString() {
		String res = column + "x" + row + "\n";
		
		for(int i=0; i<row; i++) {
			for(int j=0; j<column; j++)  {
				res += "c ";
			}
			res += "\n";
		}
		return res;
	}
}
