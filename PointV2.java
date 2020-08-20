package SnakeV2;

public class PointV2 {
	private int x, y;
	
	public PointV2() {
		x = 0;
		y = 0;
	}
	
	public PointV2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
