package Model;

/**
 * Created by Maxime on 22/12/2014.
 */
public abstract class Boite {
	/** largeur */
	protected int n;
	
	/** hauteur */
	protected int m;

	public Boite() {
		this.n = 3;
		this.m = 3;
	}
	
	public Boite(int n, int m) {
		this.n = n;
		this.m = m;
	}

	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}
}
