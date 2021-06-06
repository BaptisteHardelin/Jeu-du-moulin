package eventhandler;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.Main;

public class TriangleEventHandler implements EventHandler<ActionEvent> {

	private Main main;
	

	public TriangleEventHandler(Main main) {
		this.main = main;
	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println("Vous avez choisi le terrain de type : triangle");
		main.getG().getBoard().generateTriangle();
	}



}
