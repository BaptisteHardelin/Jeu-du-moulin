package eventhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.Main;
import terminaltographique.Game;

public class DeuxEventHandler implements EventHandler<ActionEvent> {

	private Main main;

	public DeuxEventHandler(Main main) {
		this.main = main;
	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println("Vous avez choisi : un joueur VS un joueur");
		this.main.getG().selectPlayer(2);
	}
}
