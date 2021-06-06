package eventhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.Main;

public class JouerEventHandler implements EventHandler<ActionEvent> {

	private Main main;

	public JouerEventHandler(Main main) {
		this.main = main;
	}

	@Override
	public void handle(ActionEvent event) {
		this.main.getMainStage().setScene(this.main.getChoixTerrain());

	}

}
