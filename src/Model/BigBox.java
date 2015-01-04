package Model;

import java.util.LinkedList;
import java.util.List;

import Control.Controller;

public class BigBox extends Box {
	// Une grand boite ne peut en contenir une autre
	private List<LittleBox> listBoxes = new LinkedList<LittleBox>();
	int[][] matrice;
	// next free coords bottom
	private int rowB = 0;
	private int colB = 0;
	// next free coords right
	private int rowR = 0;
	private int colR = 0;

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
			return false;

		for(int i=0; i<b.getRow(); i++) {
			for(int j=0; j<b.getColumn(); j++) {
				try {
					if (!isCaseEmpty(r + i, c + j)) 
						return false;
				} catch(Exception e) { 
					return false;
				}
			}
		}
		return true;
	}
	
	////////////////////
	
	// a partir des derniere coordonnée où on a ajouté une boite, calcule les prochaines coordonnées libre en dessous
	public void calculeNewCoordRight(LittleBox b) {
		this.colR += b.getColumn();
		
		if(this.rowB<b.getRow()) {
			this.rowB = b.getRow();
		}
		
		// si on arrive au bord
		if(this.colR >= this.column){
			this.rowR = -1;
			this.colR = -1;
		}
	}
	
	public void calculeNewCoordBottom(LittleBox b) {
		this.rowR = this.rowB;
		this.colR = 0;
		this.rowB += b.getRow();
		
	

		if(this.colR<b.getColumn()) {
			this.colR = b.getColumn();
		}

		// si on arrive au bord
		if(this.rowB >= this.row){
			this.rowB = -1;
			this.colB = -1;
			
		}
	}
	
	public boolean addRightOrBottom(LittleBox b, int k) throws Exception {
		if(this.isSurfaceEmpty(b, rowR, colR)) {
			this.addLittleBox(b, rowR, colR, k);
			calculeNewCoordRight(b);
			return true;
		} else if(this.isSurfaceEmpty(b, rowB, colB)) {
			this.addLittleBox(b, rowB, colB, k);
			calculeNewCoordBottom(b);
			return true;
		}
		return false;
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
	
	/*public static void main(String[] args) throws Exception {
		BigBox b = new BigBox(10,20);
		System.out.println(b);
		b.addLittleBox(new LittleBox(2,3), 2, 2, 1);
		System.out.println(b);
	}*/
}
