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
	public void addLittleBox(LittleBox b, int posX, int posY) throws Exception {
		if(posX > this.n || posY > this.m)
			throw new Exception("Invalide position : out of the matrice");
		
		this.listBoxes.add(b);
		
		for(int i=0; i<b.getN(); i++) {
			for(int j=0; j<b.getM(); j++) {
				if(this.matrice[posX+i][posY+j] != 0)
					throw new Exception("There is already a little box at position " + i + ","+ j);
				this.matrice[posX+i][posY+j] = this.listBoxes.indexOf(b)+1;
			}
		}
	}

	public void trierLittleBox() {
		Collections.sort(this.listBoxes);
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
