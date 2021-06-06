package eventhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.Main;
import terminaltographique.Game;

public class CarreEventHandler implements EventHandler<ActionEvent> {

	private Main main;

	public CarreEventHandler(Main main) {
		this.main = main;
	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println("Vous avez choisi le terrain de type : carré ");
		main.getG().getBoard().generateSquare();
	}

}
