package terminaltographique;

/**
 * 
 * @author Paul.V/Matthieu.M/Matteo.M/R�mi.V/Baptiste.H
 *
 */
public class Node {

	/**
	 * Position x of the node
	 */
	protected int posX;
	/**
	 * Position y of the node
	 */
	protected int posY;
	// 'o' = empty node
	// '@' = filled node
	/**
	 * 
	 */
	protected Node c1;
	/**
	 * 
	 */
	protected Node c2;
	/**
	 * 
	 */
	protected int type;

	/**
	 * Create a node
	 * @param x
	 * @param y
	 */
	public Node(int x, int y) {
		super();
		this.posX = x;
		this.posY = y;
	}

	/**
	 * Get position x of the node
	 * @return position x of the node
	 */
	public int getX() {
		return this.posX;
	}

	/**
	 * Get position y of the node
	 * @return position y of the node 
	 */
	public int getY() {
		return this.posY;
	}

	/**
	 * change the position @param x of the node 
	 */
	public void setX(int value) {
		this.posX = value;
	}

	/**
	 * change the position @param y of the node 
	 */
	public void setY(int value) {
		this.posY = value;
	}

	/**
	 * Look if the node has a pawn on him
	 * 
	 * @param
	 * @return true if the node has a pawn on him else false
	 */
	public boolean isOccuped(Node node) {
		if (this.posX == node.getX() && this.posY == node.getY()) {
			// return this.type.isEmpty(); // return true
		} // else we can put a traped or he is empty

		return false;
	}
	
	/**
	 * function that checks if the pawns are linked to each other according to the
	 * rules of the game
	 * 
	 * @para
	 * @param c2
	 * @param type
	 * @return c1.getPosX() == c2.getPosX() && (c1.getPosY()+1 == c2.getPosY() ||
	 * c1.getPosY()-1 == c2.getPosY());
	 */

	public static boolean isLinked(Node c1, Node c2, int type) {
		return ((c1.getY() == c2.getY() && Math.abs(c2.getX() - c1.getX()) == 1
				&& c1.getY() % 2 == 0)
				|| (c1.getX() == c2.getX() // 1 et 1 ----- OK
				&& (((Math.abs(c2.getY() % (type * 2) - c1.getY() % (type * 2))) == 1 )
				|| (Math.abs(c2.getY() % (type * 2) - c1.getY() % (type * 2)) == type*2-1))));
	}

	/**
	 * @return return the pos x and the pos y of a node
	 */
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "(" + posX + ", " + posY + ") ";
	}

}
