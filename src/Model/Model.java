package Model;

import java.util.LinkedList;
import java.util.List;

public class Model {
	private List<BigBox> bigBoxes = new LinkedList<BigBox>();
	private List<LittleBox> littleBoxes = new LinkedList<LittleBox>();
	
	/* ----- ----- Constructors ----- ----- */
	public Model(int n, int m, List<LinkedList<Integer>> ptitesboites) throws Exception {
		BigBox gb = new BigBox(n, m);
		this.bigBoxes.add(gb);
		
		for(LinkedList<Integer> coord : ptitesboites) {
			LittleBox pb = new LittleBox(coord.get(0), coord.get(1));
			this.littleBoxes.add(pb);
		}
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
//		this.bigBoxes.getFirst().addLittleBox(this.littleBoxes.getFirst(), 0, 0);
//		this.bigBoxes.getFirst().addLittleBox(this.littleBoxes.getLast(), 1, 1);
//		this.bigBoxes.getFirst().addLittleBox(this.littleBoxes.get(1), 2, 1);
		
		//TODO notre algo de la mort qui tue
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
