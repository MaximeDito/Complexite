package Control;

import java.util.LinkedList;

import Model.BigBox;
import Model.Model;
import View.Afficheur;

import javax.swing.*;

/**
 *
 * @author Anaïs Marongiu
 */
public class Controller {
	private Model model;
	private Parser parser = new Parser();
	private Afficheur view;

	public void run(String urlFile) {
		final JOptionPane exec = new JOptionPane();
		int value = exec.showConfirmDialog(null, "Bienvenue dans le programme de Bin Packing\nVoulez vous commencer la simulation ?","Bin Packing", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (value == JOptionPane.YES_OPTION) {
			String res = ReadFile.load(urlFile);
			//System.out.println(res);

			if (!parser.parse(res)) {
				JOptionPane.showMessageDialog(null, "\"Erreur lors de la lecture du fichier entree.txt, fichier introuvable ou illisible !", "Erreur : Bin Packing", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				this.model = new Model(parser.getMOfBigBox(), parser.getNOfBigBox(), parser.getCoordLittleBoxes());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "\"Erreur lors de la lecture du fichier entree.txt, erreur de syntaxe de données en entrée !", "Erreur : Bin Packing", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}

			try {
				this.model.fit();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "\"Erreur d'exécution de l'algorithme !", "Erreur : Bin Packing", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}

			System.out.println(this.model);

			LinkedList<int[][]> l = new LinkedList<int[][]>();
			for (BigBox b : this.model.getBigBoxes())
				l.add(b.getMatrice());

			view = new Afficheur(model.getLittleBoxes().size());
			view.creerAffichage(l);
			JOptionPane.showMessageDialog(null, "Simulation terminée !\nLe Résultat se trouve dans le fichier \"Résultat.html\"", "Bin Packing", JOptionPane.INFORMATION_MESSAGE);
		} else return;
	}
}
