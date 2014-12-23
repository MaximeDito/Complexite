package Model;


/**
 * Created by Maxime on 22/12/2014.
 */
public abstract class Box implements Comparable {
	/** largeur */
	protected int n;
	
	/** hauteur */
	protected int m;

	/* ----- ----- Constructors ----- ----- */
	public Box() {
		this.n = 3;
		this.m = 3;
	}
	
	public Box(int n, int m) throws Exception {
		if(n<0 || m<0) {
			throw new Exception("Negatives values are forbidden.");
		}
		this.n = n;
		this.m = m;
	}
	
	/* ----- ----- Methode ----- ----- */
	public boolean isLowerThan(Box b) {
		return (this.getSurface()<b.getSurface());	
	}
	
	public boolean isLowerOrEqualThan(Box b) {
		return (this.getSurface()<=b.getSurface());	
	}
	
	public boolean isGreaterOrEqualThan(Box b) {
		return (this.getSurface()>=b.getSurface());	
	}
	
	public boolean isGreaterThan(Box b) {
		return (this.getSurface()>b.getSurface());	
	}

	@Override
	public int compareTo(Object o) {
		int surface1 = this.getSurface();
		int surface2 = ((LittleBox)o).getSurface();
		if(surface1 > surface2) return 1;
		else if(surface1 == surface2) return 0;
		else return -1;
	}

	/* ----- ----- Accessors ----- ----- */
	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}
	
	public int getSurface() {
		return n*m;
	}

	@Override
	public String toString() {
		return n + "x" + m;
	}
}
