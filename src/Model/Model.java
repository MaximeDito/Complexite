package Model;

import java.util.*;

public class Model {
	private List<BigBox> bigBoxes = new ArrayList<BigBox>();
	private List<LittleBox> littleBoxes = new LinkedList<LittleBox>();
	
	/* ----- ----- Constructors ----- ----- */
	public Model(int n, int m, List<LinkedList<Integer>> ptitesboites) throws Exception {
		BigBox gb = new BigBox(n, m);
		this.bigBoxes.add(gb);
		
		for(List<Integer> coord : ptitesboites) {
			LittleBox pb = new LittleBox(coord.get(0), coord.get(1));
			this.littleBoxes.add(pb);
		}

		this.trierLittleBoxes();
	}
	
	public Model(List<LittleBox> ptiteBoites, BigBox firstGrandeBoite) {
		this.bigBoxes.add(firstGrandeBoite);
		this.littleBoxes = ptiteBoites;
	}
	
	/* ----- ----- Methodes ----- ----- */

	public void arrange() throws Exception {
		Iterator iterator = littleBoxes.iterator();
		int i=0;
		int posX =0;
		int posY=0;
		int maxX=0;
		int maxY=0;
		BigBox currentBB = this.bigBoxes.get(i);
		LittleBox boite = (LittleBox) iterator.next();
		while(iterator.hasNext()) {
			if(!currentBB.addLittleBox(boite,posX,posY, this.littleBoxes.indexOf(boite))) {
					this.bigBoxes.add(new BigBox(currentBB.getN(), currentBB.getM()));
					currentBB = this.getBigBoxes().get(++i);
					posX = posY = maxX = 0;
				
			}
			else {
				posY +=boite.getM();
				if(maxX < boite.getN()) maxX = boite.getN();
				if(posY >= currentBB.getM()) {
						posX+=maxX; posY =0;
				}
				if(posX > currentBB.getN()) posX=0;
				boite = (LittleBox) iterator.next();
			}
		}
		//le soucis vient de l'itérator qui ne peux pas appliquer l'algo à la dernière box !
		if(!currentBB.addLittleBox(boite,0,0, this.littleBoxes.indexOf(boite))) {
			this.bigBoxes.add(new BigBox(currentBB.getN(),currentBB.getM()));
			currentBB = this.getBigBoxes().get(++i);
			currentBB.addLittleBox(boite,0,0, this.littleBoxes.indexOf(boite));
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
		return "bigBoxes=" + bigBoxes + "\nlittleBoxes=" + littleBoxes;
	}
}
