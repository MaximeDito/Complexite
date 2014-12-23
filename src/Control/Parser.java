package Control;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
*
* Class which build data for boxes, with data extracted from the entry file to a string
*
* @author Anaïs Marongiu
*/
public class Parser {	
	private int nOfBigBox;
	private int mOfBigBox;
	private List<LinkedList<Integer>> nmOfLittleBox = new LinkedList<LinkedList<Integer>>();
	
	/** Build data for boxes, with data extracted from the entry file to a string
	* @param dataEntry of the data extracted from the entry file
	* @return true if succeed, false if not
	*/
	public boolean parse(String dataEntry) {
		Scanner scanner = new Scanner(dataEntry);
		
		// --- FIRST LINE : Data for the big box
		String firstline = scanner.nextLine(); 
		try 
		{ 
			String[] dataBigBox = firstline.split("x");
			this.nOfBigBox = Integer.parseInt(dataBigBox[0]);
			this.mOfBigBox = Integer.parseInt(dataBigBox[1]);
			//System.out.println("GrandeBoite : " + nOfBigBox + "x" + mOfBigBox);
		} 
		catch (NumberFormatException nfe) 
		{ 
			System.err.println("Error : first line must be nxm with n,m integers.");
			scanner.close();
			return false;
		}
		
		// --- NEXT LINES : Data for little boxes
		String line = scanner.nextLine();	
		try 
		{ 
			String[] dataLittleBoxes = line.split(", ");
			int x, y;
			for(int i = 0; i < dataLittleBoxes.length; i++) {
				String[] dataLittleBox = dataLittleBoxes[i].split("x");
				x = Integer.parseInt(dataLittleBox[0]);
				y = Integer.parseInt(dataLittleBox[1]);	
				LinkedList<Integer> l = new LinkedList<Integer>();
				l.add(x); 
				l.add(y);
				nmOfLittleBox.add(l);
				//System.out.println("PtiteBoite : " + x + "x" + y);
			}
		} 
		catch (NumberFormatException nfe) 
		{ 
			System.err.println("Error : second line must be axb, cxd, ... with a, b, c, d,... integers.");
			scanner.close();
			return false;
		}
		
		scanner.close();
		return true;
	}

	/* ----- ----- Accessors ----- ----- */
	public int getNOfBigBox() {
		return nOfBigBox;
	}

	public int getMOfBigBox() {
		return mOfBigBox;
	}

	public List<LinkedList<Integer>> getCoordLittleBoxes() {
		return nmOfLittleBox;
	}
}
