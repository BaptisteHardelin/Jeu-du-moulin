package eventhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.Main;

public class MenuPrincipalEventHandler implements EventHandler<ActionEvent> {

	private Main main;
	
	public MenuPrincipalEventHandler(Main main) {
		this.main = main;
	}

	@Override
	public void handle(ActionEvent event) {
		this.main.getMainStage().setScene(this.main.getScene());
		
	}

}
