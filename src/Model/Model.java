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
	public void newBigBox() throws Exception {
		this.bigBoxes.add(new BigBox(((LinkedList<BigBox>)this.bigBoxes).getFirst().n, ((LinkedList<BigBox>)this.bigBoxes).getFirst().m));
	}
	
	
	public void arrange() throws Exception {
		Iterator iterator = littleBoxes.iterator();
		int i=0;
		BigBox currentBB = this.bigBoxes.get(i);
		LittleBox boite = (LittleBox) iterator.next();
		while(iterator.hasNext()) {
			if(!currentBB.addLittleBox(boite,0,0, this.littleBoxes.indexOf(boite))) {
				this.bigBoxes.add(new BigBox(currentBB.getN(),currentBB.getM()));
				currentBB = this.getBigBoxes().get(++i);
			}
			else boite = (LittleBox) iterator.next();
		}
		//le soucis vient de l'itérator qui ne peux pas appliquer l'algo à la dernière box !
		if(!currentBB.addLittleBox(boite,0,0, this.littleBoxes.indexOf(boite))) {
			this.bigBoxes.add(new BigBox(currentBB.getN(),currentBB.getM()));
			currentBB = this.getBigBoxes().get(++i);
			currentBB.addLittleBox(boite,0,0, this.littleBoxes.indexOf(boite));
		}
//		this.bigBoxes.getFirst().addLittleBox(this.littleBoxes.getFirst(), 0, 0);
//		this.bigBoxes.getFirst().addLittleBox(this.littleBoxes.getLast(), 1, 1);
//		this.bigBoxes.getFirst().addLittleBox(this.littleBoxes.get(1), 2, 1);
		
		//TODO notre algo de la mort qui tue
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
