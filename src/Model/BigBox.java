package Model;

import java.util.LinkedList;
import java.util.List;

public class BigBox extends Box {
	// Une grand boite ne peut en contenir une autre
	private List<LittleBox> listBoxes = new LinkedList<LittleBox>();
	int[][] matrice;

	/* ----- ----- Constructors ----- ----- */
	public BigBox() {
		super();
		this.matrice = new int[this.row][this.column];
	}

	public BigBox(int r, int c) throws Exception {
		super(r, c);
		this.matrice = new int[r][c];
	}
	
	/* ----- ----- Methodes ----- ----- */
	/**
	 * @param posX : abscisse of the origin of the little box (first case 1x1 at the top left)
	 * @param posY : ordonne of the origin of the little box (first case 1x1 at the top left)
	 * */
	public boolean addLittleBox(LittleBox b, int r, int c, int nb) throws Exception {
		if(!isSurfaceEmpty(b,r,c)) 
			return false;
		
		this.listBoxes.add(b);
		
		for(int i=0; i<b.getRow(); i++) {
			for(int j=0; j<b.getColumn(); j++) {
				this.matrice[r+i][c+j] = nb+1;
			}
		}
		return true;
	}

	public boolean isCaseEmpty(int r, int c) {
		if(this.matrice[r][c] != 0) return false;
		return true;
	}

	public boolean isSurfaceEmpty(LittleBox b,int r, int c) throws Exception {
		if(r > this.row || c > this.column)
			throw new Exception("The little box is bigger than the big box !");

		for(int i=0; i<b.getRow(); i++) {
			for(int j=0; j<b.getColumn(); j++) {
				try {
					if (!isCaseEmpty(r + i, c + j)) 
						return false;
				} catch(Exception e) { 
					return false;
				}
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
		for(int i=0; i<this.matrice.length; i++) {
			for(int j=0; j<this.matrice[0].length; j++)  {
				res += this.matrice[i][j] + " ";
			}
			res += "\n";
		}
		return res;
	}
}
