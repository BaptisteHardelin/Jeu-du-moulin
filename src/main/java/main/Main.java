package main;

import java.io.File;
import eventhandler.CarreEventHandler;
import eventhandler.ContinuerEventHandler;
import eventhandler.DeuxEventHandler;
import eventhandler.JouerEventHandler;
import eventhandler.MenuPrincipalEventHandler;
import eventhandler.PlayEventHandler;
import eventhandler.TriangleEventHandler;
import eventhandler.UnEventHandeler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import terminaltographique.Game;

public class Main extends Application {

	private static final double PREF_HEIGHT = 100;
	private static final double VGAP = 30;
	/**
	 * This is the height of our windows
	 */
	private final double HEIGHT = 1920.0;
	/**
	 * This is the width of our windows
	 */
	private final double WIDTH = 1080.0;
	/**
	 * Set the size of our buttons
	 */
	private final double PREF_WIDTH = 400.0;
	/**
	 * The font size of our text
	 */
	private final double FONT_SIZE = 30.0;
	/**
	 * Set the gap between two nodes
	 */
	private final double GAP = 50.0;
	/**
	 * We call the class game
	 */
	private Game g = new Game();
	/**
	 * Our main container
	 */
	private GridPane mainRoot;
	/**
	 * Container where you select the parameters
	 */
	private GridPane choixTerrainRoot;
	/**
	 * Container with the terrain
	 */
	private HBox terrainRoot;
	/**
	 * Label to welcome you	
	 */
	private Label welcome;
	/**
	 * Button to move to another scene 
	 */
	private Button game;
	/**
	 * Button to resume the previous game
	 */
	private Button continuer;
	/**
	 * Button to give help to the user
	 */
	private Button help;
	/**
	 * Button to return to the main scene
	 */
	private Button mainMenu;
	/**
	 * Our main scene
	 */
	private Scene mainScene;
	/**
	 * Scene where you select the parameters
	 */
	private Scene choixTerrainScene;
	/**
	 * Scene with the terrain
	 */
	private Scene terrainScene;
	/**
	 * Our main stage
	 */
	private Stage mainStage = new Stage();
	/**
	 * Our stage where you select the parameters
	 */
	private Stage choixTerrainStage = new Stage();
	
	public double getHEIGHT() {
		return HEIGHT;
	}

	public double getWIDTH() {
		return WIDTH;
	}

	public double getPREF_WIDTH() {
		return PREF_WIDTH;
	}

	public double getFONT_SIZE() {
		return FONT_SIZE;
	}

	public double getGAP() {
		return GAP;
	}

	public Game getG() {
		return g;
	}


	public GridPane getRoot() {
		return mainRoot;
	}

	public GridPane getChoixTerrainRoot() {
		return choixTerrainRoot;
	}

	public HBox getTerrainRoot() {
		return terrainRoot;
	}

	public Label getBienvenu() {
		return welcome;
	}

	public Button getJouer() {
		return game;
	}

	public Button getContinuer() {
		return continuer;
	}

	public Button getAide() {
		return help;
	}

	public Button getMainMenu() {
		return mainMenu;
	}

	public Scene getScene() {
		return mainScene;
	}

	public Scene getChoixTerrain() {
		return choixTerrainScene;
	}

	public Scene getTerrainScene() {
		return terrainScene;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public Stage getChoixTerrainStage() {
		return choixTerrainStage;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		
		
		stage = mainStage;
		stage.setMaximized(true);
		
		/**
		 * set a new Icon TODO
		 */
		File file = new File("D:\\eclipse\\eclipseProjetPOO\\ProjetPooGraphique\\src\\main\\icon.png");
		Image icon = new Image("https://media.discordapp.net/attachments/821388180724645970/845209638512361482/logo.png?width=670&height=670");
		
		rootSettings();
		choixTerrainRoot();
		terrainRoot();

		welcome = new Label("Bienvenue dans le jeu du moulin !");
		welcome.setFont(new Font(FONT_SIZE));
		welcome.setAlignment(Pos.CENTER);

		game = new Button("Jouer");
		game.setFont(Font.font(FONT_SIZE-5));
		game.setPrefWidth(PREF_WIDTH);
		game.setPrefHeight(PREF_HEIGHT);
		game.setAlignment(Pos.CENTER);

		continuer = new Button("Continuer");
		continuer.setFont(Font.font(FONT_SIZE-10));
		continuer.setPrefWidth(PREF_WIDTH);
		continuer.setPrefHeight(PREF_HEIGHT);
		continuer.setAlignment(Pos.CENTER);

		help = new Button("Aide");
		help.setFont(Font.font(FONT_SIZE-10));
		help.setPrefWidth(PREF_WIDTH);
		help.setPrefHeight(PREF_HEIGHT);
		help.setAlignment(Pos.CENTER);

		/**
		 * We set the scenes
		 */
		mainScene = new Scene(mainRoot, HEIGHT, WIDTH);
		choixTerrainScene = new Scene(choixTerrainRoot, HEIGHT, WIDTH);
		terrainScene = new Scene(terrainRoot, HEIGHT, WIDTH);
		addToRoot();

		/* Scene Menu */

		Label labelChoixHumain = new Label("Mode de jeu:");
		labelChoixHumain.setPrefWidth(PREF_WIDTH-50);
		labelChoixHumain.setPrefHeight(PREF_HEIGHT-50);
		labelChoixHumain.setAlignment(Pos.CENTER);
		labelChoixHumain.setFont(Font.font(FONT_SIZE-10));
		Button un = new Button("Joueur 1 vs IA");
		un.setPrefWidth(PREF_WIDTH-50);
		un.setPrefHeight(PREF_HEIGHT-50);
		un.setAlignment(Pos.CENTER);
		un.setFont(Font.font(FONT_SIZE-10));
		Button deux = new Button("Joueur 1 vs Joueur 2");
		deux.setPrefWidth(PREF_WIDTH-50);
		deux.setPrefHeight(PREF_HEIGHT-50);
		deux.setAlignment(Pos.CENTER);
		deux.setFont(Font.font(FONT_SIZE-10));


		Label labelChoixTerrain = new Label("Quel type de terrain voulez vous ?");
		labelChoixTerrain.setPrefWidth(PREF_WIDTH-50);
		labelChoixTerrain.setPrefHeight(PREF_HEIGHT-50);
		labelChoixTerrain.setAlignment(Pos.CENTER);
		labelChoixTerrain.setFont(Font.font(FONT_SIZE-10));

		Button carre = new Button("Carré");
		carre.setPrefWidth(PREF_WIDTH-50);
		carre.setPrefHeight(PREF_HEIGHT-50);
		carre.setAlignment(Pos.CENTER);
		carre.setFont(Font.font(FONT_SIZE-10));
		Button triangle = new Button("Triangle");
		triangle.setPrefWidth(PREF_WIDTH-50);
		triangle.setPrefHeight(PREF_HEIGHT-50);
		triangle.setAlignment(Pos.CENTER);
		triangle.setFont(Font.font(FONT_SIZE-10));

		Button play = new Button("JOUER");
		play.setPrefWidth(PREF_WIDTH-50);
		play.setPrefHeight(PREF_HEIGHT-50);
		play.setAlignment(Pos.CENTER);
		play.setFont(Font.font(FONT_SIZE));

		mainMenu = new Button("Menu principal");

		addToChoixTerrainRoot(labelChoixHumain, un, deux, labelChoixTerrain, carre, triangle, play);
		
		choixTerrainRoot.add(mainMenu, 15, 9);
		
		addToTerrainRoot();
		

		game.addEventHandler(ActionEvent.ACTION, new JouerEventHandler(this));
		/**
		 * No new scene here when we click on the button it brings us to the game scene
		 */
		continuer.addEventHandler(ActionEvent.ACTION, new ContinuerEventHandler(this));

		mainMenu.addEventHandler(ActionEvent.ACTION, new MenuPrincipalEventHandler(this));

		/* Scene selection of parameters */
		un.addEventHandler(ActionEvent.ACTION, new UnEventHandeler(this));
		deux.addEventFilter(ActionEvent.ACTION, new DeuxEventHandler(this));
		carre.addEventHandler(ActionEvent.ACTION, new CarreEventHandler(this));
		triangle.addEventHandler(ActionEvent.ACTION, new TriangleEventHandler(this));
		play.addEventHandler(ActionEvent.ACTION, new PlayEventHandler(this));

		stage.setScene(mainScene);
		stage.setTitle("Jeu du Moulin");
		stage.show();
		stage.getIcons().add(icon);
	}
	/**
	 * Add nodes to the choixTerrainRoot
	 * @param labelChoixHumain
	 * @param un
	 * @param deux
	 * @param labelCoixTerrain
	 * @param carre
	 * @param triangle
	 * @param play
	 */

	private void addToChoixTerrainRoot(Label labelChoixHumain, Button un, Button deux,
			Label labelCoixTerrain, Button carre, Button triangle, Button play) {
		choixTerrainRoot.add(labelChoixHumain, 15, 1);
		//choixTerrainRoot.add(un, 5, 1);
		choixTerrainRoot.add(deux, 15, 2);
		choixTerrainRoot.add(labelCoixTerrain, 15, 3);
		choixTerrainRoot.add(carre, 15, 4);
		choixTerrainRoot.add(triangle, 15, 5);
		choixTerrainRoot.add(play, 15, 7);
	}

	/**
	 * Add nodes to the TerrainRoot
	 */
	private void addToTerrainRoot() {
		//terrainRoot.add(mainMenu, 5, 9);
	}
	
	/**
	 * Add node to the mainRoot
	 */
	private void addToRoot() {
		mainRoot.add(welcome, 15, 4);
		mainRoot.add(game, 15, 8);
		mainRoot.add(continuer, 15, 12);
		mainRoot.add(help, 15, 16);
	}

	/**
	 * 	Set up the choixTerrainRoot
	 */
	private void choixTerrainRoot() {
		choixTerrainRoot = new GridPane();
		choixTerrainRoot.setHgap(GAP);
		choixTerrainRoot.setVgap(GAP);
		choixTerrainRoot.setPrefHeight(PREF_WIDTH);
	}
	
	/**
	 * Set up the terrainRoot
	 */
	private void terrainRoot() {
		terrainRoot = new HBox();
		terrainRoot.setPrefHeight(1000);
		
	}

	/**
	 * Set up the mainRoot
	 */
	private void rootSettings() {
		mainRoot = new GridPane();
		mainRoot.setHgap(GAP);
		mainRoot.setVgap(VGAP);
		mainRoot.setPrefWidth(PREF_WIDTH);
	}

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

}
