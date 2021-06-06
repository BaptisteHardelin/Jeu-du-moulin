package eventhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.Main;
import terminaltographique.Game;

public class UnEventHandeler implements EventHandler<ActionEvent> {

	private Main main;

	public UnEventHandeler(Main main) {
		this.main = main;
	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println("Vous avez choisi : un joueur VS une IA");
		this.main.getG().selectPlayer(1);
	}

}
