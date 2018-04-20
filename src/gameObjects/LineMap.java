package gameObjects;

public class LineMap {
	
	private int xLeft, xRight, y; 
	
	public LineMap(int xLeft, int xRight, int y){
		this.setxLeft(xLeft); 
		this.setxRight(xRight); 
		this.setY(y);
	}

	public int getxRight() {
		return xRight;
	}

	public void setxRight(int xRight) {
		this.xRight = xRight;
	}

	public int getxLeft() {
		return xLeft;
	}

	public void setxLeft(int xLeft) {
		this.xLeft = xLeft;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
