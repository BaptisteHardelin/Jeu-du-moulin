package main;

public class Rectangle {
	// Position of the upper left corner
	double posx = 0, posy = 0;
	double width = 0, height = 0;

	public Rectangle(double posx, double posy, int width, int height) {
		this.posx = posx;
		this.posy = posy;
		this.width = width;
		this.height = height;
	}

	// returns true if the point with (x, y) coordinates is located inside the rectangle
	public boolean contains(double x, double y) {
		return (x >= posx) && (x <= posx + width) && (y >= posy) && (y <= posy + height);
	}

	public double getX() {
		return posx;
	}

	public double getY() {
		return posy;
	}
	
	public void setX(double x) {
		posx = x;
	}

	public void setY(double y) {
		posy = y;	
	}
	
	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double width) {
		this.width = width;
	}

	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}


}
