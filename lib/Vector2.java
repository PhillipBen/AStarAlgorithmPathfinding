import java.io.Serializable;

class Vector2 implements Serializable{
	/*
	The Vector2 class is used to hold two integers, or one point.
	Must be serializable to save the Grid.
	*/
	private int xI;
	private int yI;
	private double xD;
	private double yD;
	
	public Vector2() {
		xI = 0;
		yI = 0;
		xD = 0.0;
		yD = 0.0;
	}
	
	public Vector2(int x, int y) {
		this.xI = x;
		this.yI = y;
	}
	public Vector2(double x, double y) {
		this.xD = x;
		this.yD = y;
	}
	
	public int xI() {
		return xI;
	}
	public int yI() {
		return yI;
	}
	
	public double xD() {
		return xD;
	}
	public double yD() {
		return yD;
	}
	
	
	public String PrintI() {
		return "(" + xI + ", " + yI + ")";
	}

	
	public boolean compareTo(Vector2 pos) {
		if (xI == pos.xI() && yI == pos.yI()) {
			return true;
		} else {
			return false;
		}
	}
}