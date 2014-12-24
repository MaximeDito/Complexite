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
	 * @param r : abscisse of the origin of the little box (first case 1x1 at the top left)
	 * @param c : ordonne of the origin of the little box (first case 1x1 at the top left)
	 * */
	public boolean addLittleBox(LittleBox b, int r, int c, int nb, int rM, int cM) throws Exception {
		if(cM+c >= this.getColumn()) cM = this.getColumn()-1;
		else cM +=c;
		if(rM+r >= this.getRow()) rM = this.getRow()-1;
		else rM += r;

		if(!isSurfaceEmpty(b,r,c)) {
			if(isSurfaceEmpty(b, r - 1, c)) r = r-1;
			else if(isSurfaceEmpty(b, r, c-1)) c = c-1;
			else if(isSurfaceEmpty(b,0,cM)) {r =0; c=cM;}
			else if(isSurfaceEmpty(b,rM,0)) {r=rM; c=0;}

			else return false;
		}

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
