package Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BigBox extends Box {
	// Une grand boite ne peut en contenir une autre
	private List<LittleBox> listBoxes = new LinkedList<LittleBox>();
	int[][] matrice;

	/* ----- ----- Constructors ----- ----- */
	public BigBox() {
		super();
		this.matrice = new int[this.n][this.m];
	}

	public BigBox(int n, int m) throws Exception {
		super(n, m);
		this.matrice = new int[n][m];
	}
	
	/* ----- ----- Methodes ----- ----- */
	/**
	 * @param posX : abscisse of the origin of the little box (first case 1x1 at the top left)
	 * @param posY : ordonne of the origin of the little box (first case 1x1 at the top left)
	 * */
	public boolean addLittleBox(LittleBox b, int posX, int posY, int nb) throws Exception {
		if(!isSurfaceEmpty(b,posX,posY)) return false;
		this.listBoxes.add(b);
		
		for(int i=0; i<b.getN(); i++) {
			for(int j=0; j<b.getM(); j++) {
				this.matrice[posX+i][posY+j] = nb+1;
			}
		}
		return true;
	}

	public boolean isCaseEmpty(int posX, int posY) {
		if(this.matrice[posX][posY] != 0) return false;
		return true;
	}

	public boolean isSurfaceEmpty(LittleBox b,int posX, int posY) throws Exception {
		if(posX > this.n || posY > this.m)
			throw new Exception("The little Box is bigger than the big boxes !");

		for(int i=0; i<b.getN(); i++) {
			for(int j=0; j<b.getM(); j++) {
				if(!isCaseEmpty(posX+i,posY+j)) return false;
				//throw new Exception("There is already a little box at position " + i + ","+ j);
			}
		}
		return true;
	}

	/* ----- ----- Accessors ----- ----- */
	public List<LittleBox> getListBoxes() {
		return this.listBoxes;
	}

	public int[][] getMatrice() {
		return matrice;
	}

	@Override
	public String toString() {
		String res = "\n";
		for(int i=0; i<this.matrice[0].length; i++) {
			for(int j=0; j<this.matrice[1].length; j++)  {
				res += this.matrice[i][j] + " ";
			}
			res += "\n";
		}
		return res;
	}
}
