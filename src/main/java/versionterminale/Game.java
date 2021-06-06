package versionterminale;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Game {

	/**
	 * To store players
	 */
	private Player[] players;
	/**
	 * We got back our game board
	 */
	private Board board;
	/**
	 * To know in which phase of the game you are
	 */
	private int phase;
	/**
	 * To know if the game is started
	 */
	private boolean started;
	/**
	 * Round of the game
	 */
	private int round;

	/**
	 * The constructor initialise the Game with all these parameters
	 */
	public Game() {
		this.players = new Player[2];
		this.phase = 0;
		this.round = 0;
		this.board = new Board();
		this.started = false;

	}

	public static Player finishedPlayer;

	/**
	 * Launch the zero phase (placement)
	 */
	static Scanner sc;

	public void start() {
		sc = new Scanner(System.in);
		selectPlayer();
		selectBoard();
		placementPhase(); // Loop to place 6 pawns
		//loadSave();
		movementPhase(); // Main game loop, stop at the victory of one of the players
	}

	private void selectBoard() {
		System.out.println("Choix du terrain : ");
		System.out.println(" 1. Triangle");
		System.out.println(" 2. Carr�");
		int nbval = 0;
		do {
			nbval = sc.nextInt();
			sc.nextLine();
			if (nbval == 1) {
				this.board.generateTriangle();
			} else if (nbval == 2) {
				this.board.generateSquare();
			}
		} while (nbval != 1 && nbval != 2);
	}

	private void selectPlayer() {
		// Manage the demand of the number of players
		System.out.println("Choix du nombre de joueurs : ");
		System.out.println(" 1. Joueur 1 VS IA");
		System.out.println(" 2. Joueur 1 VS Joueur 2");
		int nbval = 0;
		do {
			nbval = sc.nextInt();
			if (nbval == 1) {
				players[0] = Player.P1;
				players[1] = Player.AI;
			} else if (nbval == 2) {
				players[0] = Player.P1;
				players[1] = Player.P2;
			}
		} while (nbval != 1 && nbval != 2);
		this.started = true;
	}

	/**
	 * The player placed their pawn on the board
	 */
	private void placementPhase() {
		if (started != true)
			return;
		if (players[0] != Player.EMPTY || players[1] != Player.EMPTY) {
			Player p1 = players[0];
			Player p2 = players[1];
			System.out.println("Le jeu commence, phase de placement des pions");
			this.board.display();
			for (int i = 0; i < 6; i++) {
				if (i % 2 == 0) {
					System.out.println("Tour " + i + " | Placez un pion Joueur 1");
					placePawn(p1);
					this.board.display();
				} else {
					this.board.display();
					System.out.println("Tour " + i + " | Placez un pion Joueur 2");
					placePawn(p2);
					this.board.display();
				}
			}
			phase = 1;
		}
	}

	/**
	 * Methode to placed player's pawns
	 * 
	 * @param player to find out who owns these pawn
	 */
	private void placePawn(Player player) {
		System.out.println("Entrez des coordonn�es (format:  x,y) : exemple 1,2 puis appuyez sur entr�e");
		boolean valid = false; // flag for valid coords
		String[] coordtab;
		do {
			if (player == Player.AI) {
				int x = 0;
				int y = 0;
				Random r = new Random();
				x = r.nextInt(3 - 1) + 1;
				y = r.nextInt(board.getType() * 2 - 1) + 1;
				if (board.getBoard().get(board.getPawn(x, y)) == Player.EMPTY) {
					this.board.setPawn(x, y, player);
					valid = true;
				}
			} else {
				String coord = sc.nextLine();
				coordtab = coord.split(",");
				int x = Integer.parseInt(coordtab[0]);
				int y = Integer.parseInt(coordtab[1]);
				Pawn pawn = board.getPawn(x, y);
				if (board.getBoard().get(pawn) == Player.EMPTY) {
					this.board.setPawn(x, y, player);
					System.out.println("Pion plac� avec succ�s !");
					valid = true;
				} else {
					System.out.println("Coordonn�es invalides !");
				}
			}
		} while (valid == false);
	}

	/**
	 * The player move their pawn on the board
	 */
	private void movementPhase() {
		if (phase == 1) {
			System.out.println("D�but de la phase de mouvement !");
			do {
				if (this.round % 2 == 1) { // Player 1
					System.out.println("C'est au tour du joueur 1 de jouer");
					finishedPlayer = players[0];
					this.board.movement(finishedPlayer);
					this.round++;
				} else if (this.round % 2 == 0) { // Player 2
					System.out.println("C'est au tour du joueur 2 de jouer");
					finishedPlayer = players[1];
					this.board.movement(finishedPlayer);
					this.round++;
				}
				System.out.println("Tours : " + this.round + " | " + isFinished());
			} while (!isFinished()); // Victory condition
		}
	}

	private boolean isFinished() {
		for (Pawn pawn1 : this.board.getBoard().keySet()) {
			for (Pawn pawn2 : this.board.getBoard().keySet()) {
				for (Pawn pawn3 : this.board.getBoard().keySet()) {
					// We see if it is a single pawn.
					if (pawn1 != pawn2 && pawn2 != pawn3 && pawn3 != pawn1) {
						if (board.getBoard().get(pawn1) == finishedPlayer
								&& board.getBoard().get(pawn2) == finishedPlayer
								&& board.getBoard().get(pawn3) == finishedPlayer) {
							if (isInLine(pawn1, pawn2, pawn3)) {
								System.out.println("P1 : " + pawn1 + "\t P2 : " + pawn2 + "\t P3" + pawn3);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isInLine(Pawn p1, Pawn p2, Pawn p3) {

		ArrayList<Pawn> pawnList = new ArrayList<Pawn>();
		pawnList.add(p1);
		pawnList.add(p2);
		pawnList.add(p3);
		Collections.sort(pawnList);

		Pawn orderedP1 = pawnList.get(0);
		Pawn orderedP2 = pawnList.get(1);
		Pawn orderedP3 = pawnList.get(2);

		if (Node.isLinked(orderedP1, orderedP2, board.getType())
				&& Node.isLinked(orderedP2, orderedP3, board.getType())) {
			if (orderedP1.getY() == orderedP3.getY() && (orderedP1.getX() % 2 == 1 && orderedP3.getX() % 2 == 1)) {
				return true;
			}
			if (orderedP1.getX() == orderedP3.getX()) {
				return true;
			}
		}
		return false;
	}

	public static Scanner getSc() {
		return sc;
	}

	public void saveGame() {
		try {
			FileOutputStream file = new FileOutputStream("saveGame.ser");
			ObjectOutputStream os = new ObjectOutputStream(file);
			os.writeObject(this.board.getBoard());
			os.close();
			file.close();
			System.out.println("Sauvegarde r�ussi !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadSave() {
		HashMap<Pawn,Player> b = null;
		try {
			FileInputStream file = new FileInputStream("saveGame.ser");
			ObjectInputStream os = new ObjectInputStream(file);
			b = (HashMap<Pawn,Player>) os.readObject();
			board.setBoard(b);
			os.close();
			file.close();
		}catch (IOException obj1) {
            obj1.printStackTrace();
            return;
        }catch (ClassNotFoundException obj2) {
            System.out.println("Class not found");
            obj2.printStackTrace();
            return;
        }
		  
    }
				


}
