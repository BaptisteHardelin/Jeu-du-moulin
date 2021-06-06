package versionterminale;

/*
 * 
 * @author R�mi/Matteo/Matthieu/Paul/Baptiste
 *
 */


public enum Player {

	EMPTY("o", Colors.YELLOW), P1("�", Colors.CYAN), P2("$", Colors.GREEN), AI("%", Colors.PURPLE);

	private String name;
	private String color;

	Player(String name, String color) {
		this.name = name;
		this.color = color;
	}
	
	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public String getColoredName(){
		return  this.color + this.name + Colors.RESET;
	}
}
