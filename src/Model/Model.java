package Model;

import java.util.*;

public class Model {
	private List<BigBox> bigBoxes = new ArrayList<BigBox>();
	private List<BigBox> bigBoxesOPEN = new ArrayList<BigBox>();
	private List<LittleBox> littleBoxes = new LinkedList<LittleBox>();
	
	/* ----- ----- Constructors ----- ----- */
	public Model(int r, int c, List<LinkedList<Integer>> ptitesboites) throws Exception {
		BigBox gb = new BigBox(r, c);
		this.bigBoxesOPEN.add(gb);
		
		for(List<Integer> coord : ptitesboites) {
			LittleBox pb = new LittleBox(coord.get(1), coord.get(0));
			this.littleBoxes.add(pb);
		}

		this.trierLittleBoxes();
	}
	
	/* ----- ----- Methodes ----- ----- */

	// NAN ça marche plus... !!!!!!! ='(
	public void arrange() throws Exception {
		Iterator iterator = littleBoxes.iterator();
		int i=0, col=0, row=0, maxCol=0, maxRow=0;
		
		BigBox currentBB = this.bigBoxes.get(i);
		LittleBox boite = (LittleBox) iterator.next();
		
		while(iterator.hasNext()) {
			if(!currentBB.addLittleBox(boite,col,row, this.littleBoxes.indexOf(boite))) {
				// creer nouvelle boite
				this.bigBoxes.add(new BigBox(currentBB.getRow(), currentBB.getColumn()));
				currentBB = this.getBigBoxes().get(++i);
				col = row = maxCol = 0;
			}
			else {
				// recalcule coordonnee
				col +=boite.getColumn();
				
				if(col >= currentBB.getColumn()) {
					col=0;
					row += boite.getRow();
				}
				
				boite = (LittleBox) iterator.next();
			}
		}
		//le soucis vient de l'itérator qui ne peux pas appliquer l'algo à la dernière box !
		/*if(!currentBB.addLittleBox(boite,0,0, this.littleBoxes.indexOf(boite))) {
			this.bigBoxes.add(new BigBox(currentBB.getN(),currentBB.getM()));
			currentBB = this.getBigBoxes().get(++i);
			currentBB.addLittleBox(boite,0,0, this.littleBoxes.indexOf(boite));
		}*/
	}
	
	public void fit() throws Exception {		
		for(int k=0; k<this.littleBoxes.size(); k++) {
			boolean estRentree = false;
			LittleBox littleboxcurrent = this.littleBoxes.get(k);
			
			// dans essaye de rentrer la boite dans une des boites open
			for(int n=0; n<this.bigBoxesOPEN.size(); n++) {
				BigBox bigboxcurrent = this.bigBoxesOPEN.get(n);
				
				if(bigboxcurrent.addRightOrBottom(littleboxcurrent, k)) {		
					testIfClose(bigboxcurrent);
					estRentree = true;
					break;
				} 
			}
			
			// sinon on ouvre une nouvelle boite sans fermer l'ancienne et on l'ajoute dedans	
			if(!estRentree) {	
				BigBox newBigBox = new BigBox(this.bigBoxesOPEN.get(0).getRow(), this.bigBoxesOPEN.get(0).getColumn());
				this.bigBoxesOPEN.add(newBigBox);
				newBigBox.addRightOrBottom(littleboxcurrent, k);
				testIfClose(newBigBox);
			}
		}
		
		// quand on a fini on ferme toutes les boxes
		for(BigBox bb : this.bigBoxesOPEN) {
			this.bigBoxes.add(bb);
		}
		
		// on supprime la derniere box crée si elle est vide
		if(this.bigBoxes.get(this.bigBoxes.size()-1).getMatrice()[0][0]==0) {
			this.bigBoxes.remove(this.bigBoxes.size()-1);
		}
	}

	public void testIfClose(BigBox b) throws Exception {
		// si la derniere case en bas à droite n'est plus libre, on ferme la box
		if(b.getMatrice()[b.getRow()-1][b.getColumn()-1] == 1) {
			this.bigBoxes.add(b);
			this.bigBoxesOPEN.remove(b);
			// et on en ouvre une nouvelle
			this.bigBoxesOPEN.add(new BigBox(b.getRow(), b.getColumn()));
		}
	}
	
	public void trierLittleBoxes() {
		Collections.sort(this.littleBoxes);
	}

	/* ----- ----- Accessors ----- ----- */
	public List<BigBox> getBigBoxes() {
		return this.bigBoxes;
	}
	public List<LittleBox> getLittleBoxes() {
		return this.littleBoxes;
	}

	@Override
	public String toString() {
		return "bigBoxes=" + bigBoxes + "\n" + "littleBoxes=" + littleBoxes;
	}
	
//	public static void main(String[] args) throws Exception {
//		List<LinkedList<Integer>> ptitesboites = new LinkedList<LinkedList<Integer>>();
//		
//		Model m = new Model(5,5, ptitesboites);
//		int[] resB = m.calculeNewCoordBottom(new LittleBox(1,1), 4, 4);
//		int[] resR = m.calculeNewCoordRight(new LittleBox(1,1), 4, 4);
//		
//		System.out.println("resB r:" + resB[0] + " c:" + resB[1]);
//		System.out.println("resR r:" + resR[0] + " c:" + resR[1]);
//	}
}
