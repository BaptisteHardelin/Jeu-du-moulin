package terminaltographique;


/*
 * 
 * @author R�mi/Matteo/Matthieu/Paul/Baptiste
 *
 */


import javafx.scene.paint.Color;

public enum Player {

	EMPTY("o", Color.WHITE), P1("�", Color.BLUE), P2("$", Color.RED), AI("%", Color.PURPLE);

	private String name;
	private Color color;

	Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}
	
	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public String getColoredName(){
		return  this.color + this.name;
	}
}
