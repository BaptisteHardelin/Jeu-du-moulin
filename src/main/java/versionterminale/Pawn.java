package versionterminale;


/**
 * 
 * @author Paul.V/Matthieu.M/Matteo.M/R�mi.V/Baptiste.H
 *
 */
public class Pawn extends Node implements Comparable<Pawn> {

	private static final long serialVersionUID = 1350092881346723535L;
	/**
	 * Create a pawn
	 * 
	 * @param x
	 * @param y
	 */
	public Pawn(int x, int y) {
		super(x, y);
	}

	/**
	 * 
	 */
	@Override
	public int getX() {
		return super.getX();
	}

	/**
	 * 
	 */
	@Override
	public int getY() {
		return super.getY();
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Pawn [(" + getX() + "," + getY() + ")]";
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pawn) {
			Pawn p = (Pawn) obj;
			return this.posX == p.posX && this.posY == p.posY;
		}
		return false;
	}

	@Override
	public int compareTo(Pawn o) {
		if (this.getX() == o.getX() && this.getY() == o.getY()) {
			return 0;
		}

		if (this.getX() < o.getX()) {
			return 1;
		} else if (this.getX() == o.getX()) {
			if (this.getY() < o.getY()) {
				return 1;
			} else if (this.getY() > o.getY()) {
				return -1;
			}
		}
		return 0;
	}

}
