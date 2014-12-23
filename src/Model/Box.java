package Model;


/**
 * Created by Maxime on 22/12/2014.
 */
public abstract class Box {
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
