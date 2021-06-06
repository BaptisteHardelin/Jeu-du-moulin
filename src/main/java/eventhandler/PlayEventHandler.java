package eventhandler;

import java.awt.geom.Point2D;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.Main;
import main.MyRectangle;
import main.Rectangle;
import terminaltographique.*;

import static terminaltographique.Game.finishedPlayer;

public class PlayEventHandler implements EventHandler<ActionEvent> {

    private Main main;
    public static HashMap<MyRectangle, Pawn> visualPawn = new HashMap<>();
    public static int placedPawn = 0;


    public boolean selected = false;
    public boolean next = false;
    public Pawn selectedPawn = null;
    public Pawn nextPawn = null;

    public ArrayList<javafx.scene.shape.Rectangle> j1_pions = new ArrayList<javafx.scene.shape.Rectangle>();
    public ArrayList<javafx.scene.shape.Rectangle> j2_pions = new ArrayList<javafx.scene.shape.Rectangle>();

    /**
     * Main TabPane
     */
	TabPane tab = new TabPane();
    /**
     * Tab for placement phase
     */
    Tab tab1 = new Tab("Placement");
    /**
     * Tab for movement phase
     */
    Tab tab2 = new Tab("Mouvement");


	VBox vbox = new VBox();
    Label labelp1 = new Label("Player 1 : ");
    Label labelp2 = new Label("Player 2 : ");
    Label cptRound = new Label ("Tours : 0");
    Label playInfo = new Label("C'est au tour du joueur ");
	int nbPions = 2; // on commence à 0 donc 3 pions
    HBox pionGp1 = new HBox();
    HBox pionGp2 = new HBox();
    Button save = new Button("Sauvegarder");


    public PlayEventHandler(Main main) {
        this.main = main;
    }


    public final static int PAWN_WIDTH = 25;
    public final static int PAWN_HEIGHT = 25;

    @Override
    public void handle(ActionEvent event) {
        if(main.getG().getPlayers().length == 2 && !main.getG().getBoard().getBoard().isEmpty()){
            generateTerrain();
            cptRound.setText("Tours : "+main.getG().getRound());
            this.main.getMainStage().setScene(this.main.getTerrainScene());
        }
    }

    private void generateTerrain() {
        Canvas canvas = new Canvas(990, 2000);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("4f5d75"));
        gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        int i = 0;

        //Generate line
        generateLine(gc, i);
        i = 0;

        //Generate Circle
        generateVisualPawn(gc, i);

        this.main.getTerrainRoot().getChildren().add(canvas); //hbox?

        for (int a = 0; a <= nbPions; a++) {
            j1_pions.add(new javafx.scene.shape.Rectangle(PAWN_WIDTH, PAWN_HEIGHT,null));
			j1_pions.get(a).setFill(Player.P1.getColor());
			j1_pions.get(a).setStroke(Color.BLACK);
            pionGp1.getChildren().add(a, j1_pions.get(a));
            j2_pions.add(new javafx.scene.shape.Rectangle(PAWN_WIDTH, PAWN_HEIGHT, null));
			j2_pions.get(a).setFill(Player.P2.getColor());
			j2_pions.get(a).setStroke(Color.BLACK);
            pionGp2.getChildren().add(a, j2_pions.get(a));
		}


        VBox vboxGlobal = new VBox();

        //Style of label
        labelp1.setFont(Font.font(20));
        labelp1.setTextFill(Color.web("ffffff"));
        labelp2.setFont(Font.font(20));
        labelp2.setTextFill(Color.web("ffffff"));
        playInfo.setFont(Font.font(20));
        playInfo.setTextFill(Color.web("ffffff"));

        save.setOnMousePressed((event -> {
            save();
        }));

        vboxGlobal.getChildren().addAll(labelp1, pionGp1, labelp2, pionGp2);
        tab1.setClosable(false);
        tab1.setStyle("-fx-font-size: 16px;-fx-text-base-color: #FFFFFF;-fx-background-color: #2d3142;");
        tab2.setStyle("-fx-font-size: 16px;-fx-text-base-color: #FFFFFF;-fx-background-color: #2d3142;");
        tab1.setContent(vboxGlobal);
        tab2.setClosable(false);
        tab2.setDisable(true);
        cptRound.setTextFill(Color.web("ffffff"));
        cptRound.setFont(Font.font(20));

        tab.setPrefWidth(main.getMainStage().getWidth());

        tab.getTabs().add(tab1);
        tab.getTabs().add(tab2);


        vbox.getChildren().add(tab);
        vbox.getChildren().add(cptRound);
        vbox.getChildren().add(playInfo);
        vbox.getChildren().add(save);
        vbox.setStyle("-fx-background-color: #4f5d75;");


        this.main.getTerrainRoot().getChildren().add(vbox);


    }

    private void save() {
        saveGame();
        saveVisualPawn();
    }

    private void saveVisualPawn() {
    }

    private void generateVisualPawn(GraphicsContext gc, int i) {
        Board b = main.getG().getBoard();
		labelp2.setVisible(false);
        pionGp2.setVisible(false);
        for (Pawn p1 :
                b.getOrthPawn().keySet()) {
            if (i < b.getOrthPawn().size()) {
                for (Pawn p2 :
                        b.getOrthPawn().keySet()) {
                    if (Node.isLinked(p1, p2, b.getType())) {
                        Point2D pt1 = b.getOrthPawn().get(p1);

                        MyRectangle rec = new MyRectangle(new Rectangle(pt1.getX() - PAWN_WIDTH/2, pt1.getY() -PAWN_HEIGHT/2, PAWN_WIDTH,PAWN_HEIGHT));

                        gc.getCanvas().setOnMouseMoved(e -> {
                            for (MyRectangle r: visualPawn.keySet()) {
                                if (r.rect.contains(e.getX(), e.getY())) {
                                    r.hover = true;
                                } else {
                                    r.hover = false;
                                }
                                repaint(r,gc);
                            }
                            if(main.getG().getRound()%2 == 1){
                                playInfo.setText("C'est au tour du joueur 1");
                            } else if(main.getG().getRound()%2 == 0) {
                                playInfo.setText("C'est au tour du joueur 2");
                            }
                        });
                        gc.getCanvas().setOnMousePressed((event -> {
                            for (MyRectangle rct : visualPawn.keySet()) {
                                if(rct.rect.contains(event.getX(), event.getY())){
                                    Player[] p = main.getG().getPlayers();

                                    int round = main.getG().getRound();

                                    //Phase 0 Placement
                                    if(main.getG().getPhase() == 0) {
                                        placementPhase(gc, rct, p);
                                    //Phase 1 Mouvement
                                    } else if(main.getG().getPhase() == 1){
                                        movementPhase(gc, rct, p, round);
                                    }
                                    if(main.getG().isFinished()){
                                        System.out.println("FINI "+finishedPlayer + "\t"+main.getG().getFinishedPlayer());
                                    }
                                }
                            }
                        }));
                        //
                        if(!visualPawn.containsValue(p1)){
                            visualPawn.put(rec,p1);
                        }

                        drawRec(gc, Color.WHITE, pt1.getX() - PAWN_WIDTH / 2, pt1.getY() - PAWN_HEIGHT / 2, pt1.getX() + 1 - PAWN_WIDTH/2, pt1.getY() + 1-PAWN_HEIGHT/2);
                       // gc.strokeText(p1.getX() + "," + p1.getY(), pt1.getX() - PAWN_WIDTH/2, pt1.getY() - PAWN_HEIGHT/2);
                    }
                    gc.setFill(Color.BLACK);
                }
            }
            i++;
        }
    }

    private void placementPhase(GraphicsContext gc, MyRectangle rct, Player[] p) {
        tab2.setDisable(true);
        tab.getSelectionModel().select(0);
        Pawn pawn = visualPawn.get(rct);
        if (p[0] != Player.EMPTY || p[1] != Player.EMPTY) {
            Player pl1 = p[0];
            Player pl2 = p[1];
            if(placedPawn <6){
                if (placedPawn % 2 == 0) {
                    placePawn(pl1, rct, gc);
                } else {
                    placePawn(pl2, rct, gc);
                }
            }
            if(placedPawn == 6) {
                main.getG().setPhase(1);
                tab1.setDisable(true);
                tab2.setDisable(false);
                tab.getSelectionModel().select(1);
            }

        }
    }

    private void movementPhase(GraphicsContext gc, MyRectangle rct, Player[] p, int round) {
        if (round % 2 == 1) { // Player 1
            playInfo.setText("C'est au tour du joueur " + 1);
            finishedPlayer = p[0];
            if(!selected){
                selectPawn(p[0], rct, gc);
            } else {
                nextPawn(p[0], rct, selectedPawn, gc);
            }
            if(selected && next){
                movePawn(p[0], gc);
            }
        } else if (round % 2 == 0) { // Player 2
            playInfo.setText("C'est au tour du joueur " + 2);
            finishedPlayer = p[1];
            if(!selected){
                selectPawn(p[1], rct, gc);
            } else {
                nextPawn(p[1], rct, selectedPawn, gc);
            }
            if(selected && next){
                movePawn(p[1], gc);
            }
        }
    }

    private void movePawn(Player player, GraphicsContext gc) {
        main.getG().getBoard().getBoard().replace(selectedPawn, Player.EMPTY);
        main.getG().getBoard().getBoard().replace(nextPawn, player);
        System.out.println("Ancienne position : "+selectedPawn +"\t"+main.getG().getBoard().getBoard().get(selectedPawn));
        System.out.println("Nouvelle position : "+nextPawn+"\t"+main.getG().getBoard().getBoard().get(nextPawn));
        nextPawn = null;
        selectedPawn = null;
        next = false;
        selected = false;
        main.getG().setRound(main.getG().getRound()+1);
        cptRound.setText("Tours : " + main.getG().getRound());

    }

    private void nextPawn(Player p, MyRectangle rec, Pawn selectedPawn, GraphicsContext gc) {
        Board board = main.getG().getBoard();
        Pawn pawn = visualPawn.get(rec);
        if(board.isPossible(selectedPawn,pawn)){
            if(board.isPositionFree(pawn)){
                nextPawn = pawn;
                next = true;
                return;
            }
        }
        selectedPawn = null;
        selected = false;
    }

    private void selectPawn(Player p, MyRectangle rec, GraphicsContext gc){
        Board board = main.getG().getBoard();
        Pawn pawn = visualPawn.get(rec);
        if(p == board.getBoard().get(pawn)){
            System.out.println("Sélection " + pawn);
            selected = true;
            selectedPawn = pawn;
            return;
        }
    }


    private void placePawn(Player p, MyRectangle rect,GraphicsContext gc) {
        Board board = main.getG().getBoard();
        Pawn pawn = visualPawn.get(rect);
        if (board.getBoard().get(pawn) == Player.EMPTY) {
            placedPawn++;

            //Place
            board.setPawn(pawn.getX(),pawn.getY(),p);
            visualPawn.replace(rect,pawn);
            //Redessine
            drawRec(gc, p.getColor(), rect.rect.getX(), rect.rect.getY(), rect.rect.getX() + 1, rect.rect.getY() + 1);
            if(p == Player.P1){
                labelp1.setVisible(false);
                pionGp1.setVisible(false);
                labelp2.setVisible(true);
                pionGp2.setVisible(true);
                pionGp1.getChildren().remove(0);
            } else if(p == Player.P2) {
                labelp1.setVisible(true);
                pionGp1.setVisible(true);
                labelp2.setVisible(false);
                pionGp2.setVisible(false);
                pionGp2.getChildren().remove(0);
            }
            main.getG().setRound(main.getG().getRound()+1);
        }
    }

    private void drawRec(GraphicsContext gc, Color color, double x, double y, double v, double v2) {
        gc.setFill(color);
        gc.setStroke(Color.BLACK);
        gc.fillRect(x, y, PAWN_WIDTH, PAWN_HEIGHT);
        gc.strokeRect(v, v2, PAWN_WIDTH, PAWN_HEIGHT);
        cptRound.setText("Tours : " + main.getG().getRound());


    }

    private void generateLine(GraphicsContext gc, int i) {
        Board b = main.getG().getBoard();
        for (Pawn p1 :
                b.getOrthPawn().keySet()) {
            if (i < b.getOrthPawn().size()) {
                for (Pawn p2 :
                        b.getOrthPawn().keySet()) {
                    if (Node.isLinked(p1, p2, b.getType())) {
                        Point2D pt1 = b.getOrthPawn().get(p1);
                        Point2D pt2 = b.getOrthPawn().get(p2);
                        gc.strokeLine(pt1.getX(), pt1.getY(), pt2.getX(), pt2.getY());
                        gc.setFill(Color.WHITE);
                    }
                    gc.setFill(Color.BLACK);
                }
            }
            i++;
        }
    }

    public void repaint(MyRectangle r,GraphicsContext gc){
        if (r.hover) {
            gc.setFill(Color.web("ef8354"));
        } else {
            gc.setFill(main.getG().getBoard().getBoard().get(visualPawn.get(r)).getColor());
		}
        gc.setStroke(Color.BLACK);
        gc.fillRect(r.rect.getX(), r.rect.getY(), PAWN_WIDTH, PAWN_HEIGHT);
        gc.strokeRect(r.rect.getX(), r.rect.getY(), PAWN_WIDTH, PAWN_HEIGHT);
    }

    public void saveGame() {
        try {
            FileOutputStream file = new FileOutputStream("saveGame.ser");
            ObjectOutputStream os = new ObjectOutputStream(file);
            os.writeObject(main.getG());
            os.close();
            file.close();
            System.out.println("Sauvegarde réussi !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
