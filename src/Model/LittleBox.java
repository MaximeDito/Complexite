package Model;

public class LittleBox extends Box implements Comparable {
	public LittleBox() {
		super();
	}

	public LittleBox(int n, int m) throws Exception {
		super(n, m);
	}

	@Override
	public int compareTo(Object o) {
		int surface1 = this.getSurface();
		int surface2 = ((LittleBox)o).getSurface();
		if(surface1 > surface2) return -1;
		else if(surface1 == surface2) return 0;
		else return 1;
	}
}
