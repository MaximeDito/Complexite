package Control;

import java.util.LinkedList;

import Model.BigBox;
import Model.Model;
import View.Afficheur;

/**
 *
 * @author Anaïs Marongiu
 */
public class Controller {
	private Model model;
	private Parser parser = new Parser();
	private Afficheur view;

	public void run(String urlFile) {
		String res = ReadFile.load(urlFile);
		//System.out.println(res);
		
		if(!parser.parse(res))
			return;
		
		try {
			this.model = new Model(parser.getMOfBigBox(), parser.getNOfBigBox(), parser.getCoordLittleBoxes());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		try {
			//this.model.arrange();
			this.model.arrangeRecursif(0, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println(this.model);
		
		LinkedList<int[][]> l = new LinkedList<int[][]>();
		for(BigBox b : this.model.getBigBoxes())
			l.add(b.getMatrice());

		view = new Afficheur(model.getLittleBoxes().size());
        view.creerAffichage(l);
	}	
}
