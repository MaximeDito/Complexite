package Model;

import java.util.Arrays;
import java.util.LinkedList;

public class GrandeBoite extends Boite {
	// Une GrandeBoite ne peut contenir une autre GrandeBoite
	private LinkedList<PtiteBoite> listBox = new LinkedList<PtiteBoite>();
	int[][] matrice;

	public GrandeBoite() {
		super();
		this.matrice = new int[this.n][this.m];
	}

	public GrandeBoite(int n, int m) {
		super(n, m);
		this.matrice = new int[n][m];
	}

	public LinkedList<PtiteBoite> getListBox() {
		return listBox;
	}
	
	public void addBox(PtiteBoite b) {
		//TODO
	}

	@Override
	public String toString() {
		return "GrandeBoite [matrice=" + Arrays.toString(matrice) + "]";
	}
}
