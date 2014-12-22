package Control;

/**
 * Created by Maxime on 22/12/2014.
 */
public class Controller {

	public void run(String urlFile) {
		String res = ReadFile.load(urlFile);
		System.out.println(res);
	}	
}
