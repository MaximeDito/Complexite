package Model;

import java.util.*;

public class Model {
	private List<BigBox> bigBoxes = new ArrayList<BigBox>();
	private List<LittleBox> littleBoxes = new LinkedList<LittleBox>();
	
	/* ----- ----- Constructors ----- ----- */
	public Model(int r, int c, List<LinkedList<Integer>> ptitesboites) throws Exception {
		BigBox gb = new BigBox(r, c);
		this.bigBoxes.add(gb);
		
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
			if(!currentBB.addLittleBox(boite,row,col, this.littleBoxes.indexOf(boite), maxRow, maxCol)) {
				// creer nouvelle boite
				this.bigBoxes.add(new BigBox(currentBB.getRow(), currentBB.getColumn()));
				currentBB = this.getBigBoxes().get(++i);
				col = row = maxCol = maxRow = 0;
			}
			else {
				// recalcule coordonnee
				col +=boite.getColumn();
				if(maxCol < boite.getColumn()) maxCol = boite.getColumn();
				if(maxRow < boite.getRow()) maxRow = boite.getRow();
				if(col >= currentBB.getColumn()) {
					col=0;
					row += maxRow;
					if(row >= currentBB.getRow()) row = currentBB.getRow()-1;
				}
				if(row >= currentBB.getRow()) {
					row=0;
					col += maxCol;
					if(col >= currentBB.getColumn()) col = currentBB.getColumn()-1;
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
		return "bigBoxes=" + bigBoxes + "\n" /*+ "littleBoxes=" + littleBoxes*/;
	}
}
