package eventhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.Main;

public class ContinuerEventHandler implements EventHandler<ActionEvent> {

	private Main main;
	
	public ContinuerEventHandler(Main main) {
		this.main = main;
	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println("On reprend la partie !");
		
	}
	
	

}
