package View;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Class for create a HTML file for see the result.
 * This class will create a HTML file.
 *
 * @author Maxime Dito
 *
 */
public final class Afficheur {

    private File file;
    private FileWriter fw;
    private List couleurs;

    /**
     * Default constructor
     *
     */
    public Afficheur(int nbCouleur) {
        this.file = new File("Resultat.html");
        try {
            fw = new FileWriter(this.file);
            couleurs = new ArrayList<Color>();
            initialiserCouleurs(nbCouleur);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialiserCouleurs(int nbCouleur) {
            couleurs.add(new Color(255,255,255));
        for(int i=1;i <= nbCouleur;i++) {
            couleurs.add(generateRandomColor());
        }
    }

    /**
     * Intialiaze the HTML file
     *
     * Write into the HTML file, the Header.
     */
    public void initialiseFile(int nbBoxes) {
        try {
            String head = "<html>"
                    + "\n<head>"
                    + "\n<title>Résultat de la simulation | Marongiu et Dito</title>"
                    + "\n<meta charset=\"utf-8\">"
                    + "\n<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css\">"
                    + "\n<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css\">"
                    + "\n<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js\"></script>"
                    + "\n</head>" + "\n<body class=\"container\"> \n<style>tr { height:75px;}</style>\n<h1 class=\"h1\">Résultat de la simulation <small>Total grosses boites : "+nbBoxes+"</small></h1>";
            // On écrit la chaîne
            this.fw.write(head);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Color generateRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        // mix the color
            red = (red + 220) / 2;
            green = (green + 240) / 2;
            blue = (blue + 180) / 2;

        Color color = new Color(red, green, blue);
        return color;
    }

    public void afficheBoite(int[][] boiteInt, int nb) {
        String boite = "\n<div class=\"col-md-4\"><table class=\"table table-bordered\"><caption>Boite "+nb+"</caption>";
        for(int i=0; i<boiteInt.length;i++) {
            boite += "\n<tr>";
            for (int u = 0; u < boiteInt[0].length; u++) {
                Color couleur = ((Color)couleurs.get(boiteInt[i][u]));
                boite +="<td style=\"background-color:rgb("+couleur.getRed()+","+couleur.getGreen()+","+couleur.getBlue()+")\"></td>";
            }
            boite += "</tr>";
        }
        boite += "\n</table></div>";
        try {
            fw.write(boite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Finish the HTML file
     *
     * Write into the HTML file, the end of the File and close the FileWriter.
     */
    public void endEdit() {
        try {
            // On écrit la chaîne
            this.fw.write("\n</body></html>");
            this.fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creerAffichage(List<int[][]> boites){
        this.initialiseFile(boites.size());
        Iterator iterator = boites.iterator();
        while(iterator.hasNext()) {
            int[][] boite = (int[][]) iterator.next();
            afficheBoite(boite,boites.indexOf(boite)+1);
        }
        this.endEdit();
    }

    public static void main(String[] args) {
        Afficheur test = new Afficheur(29);
        int[][] boite = {
                {1,2,3,4,0},
                {1,2,3,4,5},
                {1,2,3,4,5},
                {11,12,12,12,12},
        };
        int[][] boite2 = {
                {6,7,8,8,9},
                {6,7,8,8,9},
                {6,7,8,8,9},
                {10,10,10,10,10},
        };
        int[][] boite3 = {
                {13,13,13,13,13},
                {13,13,13,13,13},
                {13,13,13,13,13},
                {0,0,0,0,0},
        };
        int[][] boite4 = {
                {14,15,16,17,18},
                {19,20,21,22,23},
                {24,25,26,0,0},
                {27,27,27,28,28},
        };
        LinkedList<int[][]> toto = new LinkedList<int[][]>();
        toto.add(boite);
        toto.add(boite2);
        toto.add(boite3);
        toto.add(boite4);
        test.creerAffichage(toto);
    }
}
