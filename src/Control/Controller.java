package Control;

import Model.Model;

/**
 * Created by Maxime on 22/12/2014.
 */
public class Controller {
	private Model model;
	private Parser parser = new Parser();

	public void run(String urlFile) {
		String res = ReadFile.load(urlFile);
		//System.out.println(res);
		
		if(!parser.parse(res))
			return;
		
		try {
			this.model = new Model(parser.getNOfBigBox(), parser.getMOfBigBox(), parser.getNMOfLittleBoxes());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		try {
			this.model.arrange();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(this.model);
	}	
}
