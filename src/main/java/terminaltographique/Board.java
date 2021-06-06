package terminaltographique;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author R�mi/Matteo/Matthieu/Paul/Baptiste
 *
 */

public class Board {

	/**
	 * Path of Square terrain
	 */
	public final String pathSquare = FileSystems.getDefault().getPath(".") + "\\data\\carre";
	/**
	 * Path of Triangle terrain
	 */
	public final String pathTriangle = FileSystems.getDefault().getPath(".") + "\\data\\triangle";
	/**
	 * HashMap named board contains the pawns of a player
	 */
	private HashMap<Pawn, Player> board;
	/**
	 * 
	 * To know the type of terrain (4 = square, 3 = triangle)
	 */
	private int type;
	/**
	 * To know if we won
	 */
	private boolean isVictory;

	/**
	 * The default constructor initialise the board(the HashMap)
	 */
	public Board() {
		this.board = new HashMap<Pawn, Player>();
	}

	private HashMap<Pawn, Point2D> orthPawn = new HashMap<Pawn,Point2D>();

	/**
	 * To have the value of the board
	 * 
	 * @return The value of the board
	 */
	public HashMap<Pawn, Player> getBoard() {
		return board;
	}

	/**
	 * To have the type of terrain
	 * 
	 * @return The type of terrain
	 */
	public int getType() {
		return type;
	}

	/**
	 * To change the type of terrain
	 * 
	 * @param type Choose the type of terrain
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * To have the value of isVictory
	 * 
	 * @return The value of isVictory
	 */
	public boolean getIsVictory() {
		return isVictory;
	}

	/**
	 * Change the value of isVictory
	 * 
	 * @param isVictory false = game in progress, true = end the game
	 */
	public void setIsVictory(boolean isVictory) {
		this.isVictory = isVictory;
	}

	/**
	 * Method for place a pawn on the board
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param p Selected the Player
	 */
	public void setPawn(int x, int y, Player p) {
		this.board.replace(getPawn(x, y), p);
	}

	/**
	 * 
	 * Method to generate the Square terain and we fixed the type to 4 because is a
	 * square
	 * 
	 */
	public void generateSquare() {
		this.type = 4;
		generate(pathSquare);
	}

	/**
	 * 
	 * Method to generate the Triangle terrain and we fixed the type to 3 because is
	 * a triangle
	 * 
	 */

	public void generateTriangle() {
		this.type = 3;
		generate(pathTriangle);
	}

	/**
	 * 
	 * Method for generate the terrain with a path in the parameters (path = source
	 * of text file)
	 * 
	 * @param path To specify the file that will be our terrain
	 */

	private void generate(String path) {
		this.board.clear();
		this.orthPawn.clear();
		try {
			// Input file
			File file = new File(path);
			// Create object File Reader
			FileReader fr = new FileReader(file);
			// Create object BufferedReader
			BufferedReader br = new BufferedReader(fr);

			int x = 0, y = 0, c = 0;
			double visualY = 20;
			double visualX = 0;
			boolean flag = false;
			Pawn pw;
			// Read character by character
			while ((c = br.read()) != -1) {
				// Convert integer to char
				char ch = (char) c;
				if(ch == '\n'){
					visualY+=20;
					visualX = -10;
				}
				// Display the character
				if (Character.isDigit(ch) && flag == false) {
					x = Character.getNumericValue(ch);
					flag = true;
				} else if (Character.isDigit(ch) && flag == true) {
					y = Character.getNumericValue(ch);
				} else if (ch == ')' && flag == true) {
					flag = false;
					pw = new Pawn(x, y);
					this.board.put(pw, Player.EMPTY);
					this.orthPawn.put(pw, new Point2D.Double(visualX, visualY));
				}
				visualX+=10;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Found a pawn with coordinates
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @return The pawn associate to the x and y parameters
	 */
	public Pawn getPawn(int x, int y) {
		for (Pawn p : this.board.keySet()) {
			if (p.getX() == x && p.getY() == y) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Method to display the terrain
	 */

	public void display() {

		try {
			// Input file
			File file = new File("");
			if (type == 4) {
				file = new File(pathSquare);
			} else if (type == 3) {
				file = new File(pathTriangle);
			}
			// Create object File Reader
			FileReader fr = new FileReader(file);
			// Create object BufferedReader
			BufferedReader br = new BufferedReader(fr);

			int x = 0, y = 0, c = 0;
			boolean flag = false;
			Pawn pw = new Pawn(x, y);
			// Put space in the cmd
			for (int i = 0; i < 25; i++)
				System.out.println();
			// Read characters one by one
			while ((c = br.read()) != -1) {
				// convert integer to char
				char ch = (char) c;
				// Show character
				// Take the number and keep only the first digit
				if (Character.isDigit(ch) && flag == false) {
					x = Character.getNumericValue(ch);
					flag = true;
				} else if (Character.isDigit(ch) && flag == true) {
					y = Character.getNumericValue(ch);
				} else if (ch == ')' && flag == true) {
					flag = false;
					System.out.print(this.board.get(getPawn(x, y)).getColor() + "(" + x + "," + y + ")" + Colors.RESET);
				} else if (ch != '(' && ch != ',') {
					System.out.print(ch);
				}
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * We move the player's pawns using our coordinate system
	 * 
	 * @param player To find out which player should move these pawns
	 */
	public void movement(Player player) {

		Pawn oldP = null;
		Pawn newPawn = null;
		Player selPawnPlayer = board.get(oldP);
		// Movement of AI
		if (player == Player.AI) {
			// Déplacement alétoire (cas rudimentaire)
		} else {
			// Ask Pawn
			// Different from null and let it be the right player
			do{
				oldP = askSelectedPawn();
				selPawnPlayer = board.get(oldP);
				System.out.println("Player : " + selPawnPlayer + "\t" + player);
				if (oldP != null && selPawnPlayer == player) {
					// Ask New Pawn (Coord)
					newPawn = askNewPawn(oldP);
					// old pawn
					this.board.replace(oldP, selPawnPlayer, Player.EMPTY);
					System.out.println("Suppression de l'ancien pion : " + board.get(oldP));
					// Place new pawn
					this.board.replace(newPawn, Player.EMPTY, player);
					System.out.println("Ajout du pion : " + board.get(newPawn));
					// Replace old pawn to empty player
				}
			}while(selPawnPlayer != player && oldP != null);
		}


		display();
	}

	/**
	 * Method that allows us to know if a player has aligned 3 checkers (in any
	 * direction)
	 * 
	 * @param player  To find out which player should move these pawns
	 * @param oldPawn The old placement of our player's pawn
	 * @param newPawn The new placement of our player's pawn
	 * @return True if there are 3 pawns aligned.
	 */
	public boolean isVictory(Player player, Pawn oldPawn, Pawn newPawn) {
		int ctp = 0;
		for (Pawn pawn : this.board.keySet()) {
			if (Node.isLinked(pawn, pawn, this.getType())) {
				ctp++;
			}
			if (ctp == 3) {
				System.out.println("Le gagnant est " + player + " bien jou� !");
				this.isVictory = true;
				return true;
			}
		}
		return false;
	}

	/**
	 * Ask the new position of a pawn at the user
	 * 
	 * @param oldPawn The old placement of our player's pawn
	 * @return The selected pawn by the user
	 */
	public Pawn askNewPawn(Pawn oldPawn) {
		System.out.println("\nSelectionnez le nouvel emplacement de votre pion (format:  x,y) : exemple 1,2 puis appuyez sur entr�e");
		boolean valid = false; // flag for valid coords
        String[] coordtab;
		int oldX = 0;
		int oldY = 0;
		Pawn pawn = getPawn(oldX, oldY);
        do {
			String coord = Game.getSc().nextLine();
			System.out.println(coord);
            coordtab = coord.split(",");
            oldX = Integer.parseInt(coordtab[0]);
            oldY = Integer.parseInt(coordtab[1]);
            pawn = getPawn(oldX, oldY);
			if (isPossible(oldPawn, pawn)) {
				// If the new location of the pawn is free
				if (isPositionFree(pawn)) {
					 valid = true;
				}
            } else {
				System.out.println("\nD�placement impossible, s�lectionnez de nouvelles positions");
				System.out.println(isPossible(oldPawn, pawn));
				System.out.println(isPositionFree(pawn));
			}
        } while (valid == false);
		return pawn;
	}

	/**
	 * Look if is possible to move thanks to isLinked of the class Node
	 * 
	 * @param oldP The old placement of our player's pawn
	 * @param newP The new placement of our player's pawn
	 * @return True is the movement is possible
	 */
	public boolean isPossible(Pawn oldP, Pawn newP) {
		return Node.isLinked(oldP, newP, type);
	}

	/**
	 * Look if the new position of a pawn is free
	 * 
	 * @param pawn The new placement of our player's pawn
	 * @return True is the position of the new pawn is free
	 */
	public boolean isPositionFree(Pawn pawn) {
		System.out.println("P: " + board.get(pawn));
		return (this.board.get(pawn) == Player.EMPTY);
	}

	/**
	 * Ask to user the pawn where want to move
	 * 
	 * @return The pawn selected by the player
	 */
	public Pawn askSelectedPawn() {
		System.out.println("S�lection du pion");
		System.out.println("Entrez des coordonn�es (format:  x,y) : exemple 1,2 puis appuyez sur entr�e");
        boolean valid = false; // flag for valid coords
        String[] coordtab;
		int oldX = 0;
		int oldY = 0;
        do {
			String coord = Game.getSc().nextLine();
			System.out.println(coord);
            coordtab = coord.split(",");
            oldX = Integer.parseInt(coordtab[0]);
            oldY = Integer.parseInt(coordtab[1]);
            Pawn pawn = getPawn(oldX, oldY);
            if (getBoard().get(pawn) != Player.EMPTY) { 
				System.out.println("Valide \t"+ pawn.getX() + "," + pawn.getY());
                valid = true;
            } else {
                System.out.println("Coordonnées invalides !");
            }
        } while (valid == false);
		return getPawn(oldX, oldY);
	}

	public void setBoard(HashMap<Pawn,Player> map){
		this.board = map;
	}
	public HashMap<Pawn, Point2D> getOrthPawn() {
		return orthPawn;
	}
}
