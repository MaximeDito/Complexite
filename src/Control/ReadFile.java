package Control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class ReadFile {

	public static String load(String url) {
		String res = "";

		try {
			InputStream ips = new FileInputStream(url);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);

			String ligne;
			while ((ligne = br.readLine()) != null) {
				res += ligne + "\n";
			}
			br.close();
			System.out.println("Chargement du fichier " + url + " : OK");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Chargement du fichier " + url + " : NOK");
		}
		
		return res;
	}
}
