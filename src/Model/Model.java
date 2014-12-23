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
	
	
	// On pourrait croire que ça marche un peu mais nan, pas opti et fait disparaitre certaines ptites boites .. :S
	public void arrangeRecursif(int numLittleBox, int row, int col) throws Exception {
		// si plus de petites box, on arrete
		if(numLittleBox==this.littleBoxes.size()) {
			return;
		} 
		else {
			BigBox bigboxcurrent = this.bigBoxes.get(this.bigBoxes.size()-1);
			LittleBox littlebox = this.littleBoxes.get(numLittleBox);

			// On ajoute la petite box dans la grande et on calcule les prochaines coordonnées possible libre
			boolean res = bigboxcurrent.addLittleBox(littlebox,row,col, this.littleBoxes.indexOf(littlebox));
			if(res) {
				// on décale d'une colonne
				col +=littlebox.getColumn();
				
				// si on arrive au bout des colonnes, on décale d'une ligne
				if(col >= bigboxcurrent.getColumn()) {
					col = 0;
					row += littlebox.getRow(); 
					
					// si on arrive au bout des lignes, on crée une nouvelle boite (sauf si c'est la derniere)
					if(row >= bigboxcurrent.getRow()) {
						if(!((numLittleBox+1)==this.littleBoxes.size())) {
							col = row = 0;
							this.bigBoxes.add(new BigBox(bigboxcurrent.getRow(), bigboxcurrent.getColumn()));
						}
					}
				}
			} 
			// Si on a pas réussi a ajouter, on crée directement une nouvelle boite et on ajoute
			else {
				col = row = 0;
				this.bigBoxes.add(new BigBox(bigboxcurrent.getRow(), bigboxcurrent.getColumn()));
				numLittleBox++;
				bigboxcurrent.addLittleBox(littlebox,row,col, this.littleBoxes.indexOf(littlebox));
			}
			// et on recommence
			numLittleBox++;
			arrangeRecursif(numLittleBox, row, col);
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
}
