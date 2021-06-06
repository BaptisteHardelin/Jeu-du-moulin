package versionterminale;

public enum AllSymbol {
	/**
	 * Setting up all the characters we need
	 */

	EMPTY("o"), HORIZONTALLINK("-"), VERTICALLINK("|"), LEFTDIAGONALLINK("/"), RIGHTDIAGONALLINK("\\"), SPACE(" "), FILLEDCIRCLE("@"), TRAPEDCIRCLE("�");

	private String allSymbol;

	private AllSymbol(String allSymbol) {
		this.allSymbol = allSymbol;
	}

	public String getAllSymbol() {
		return allSymbol;
	}
}
