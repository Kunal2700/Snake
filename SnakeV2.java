package SnakeV2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class SnakeV2 {
	
	List<PointV2> snakePoints;
	int xDir, yDir, newXDir, newYDir;
	final int STARTSIZE = 1, STARTX = 150, STARTY = 150;
	
	boolean isMoving, elongate, isPaused;
	
	
	public SnakeV2() {
		snakePoints = new ArrayList<PointV2>();
		
		xDir = 0;
		yDir = 0;
		
		isMoving = false;
		elongate = false;
		isPaused = false;
		
		snakePoints.add(new PointV2(STARTX, STARTY));
		for (int i = 1; i < STARTSIZE; i++) {
			snakePoints.add(new PointV2(STARTX - i*6, STARTY));
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.green);
		
		for (PointV2 p: snakePoints) {
			g.fillRect(p.getX(), p.getY(), 6, 6);
		}
	}
	
	public void move() {
		if (isMoving && !isPaused) {
			PointV2 temp = snakePoints.get(0);
			PointV2 last = snakePoints.get(snakePoints.size() - 1);
			PointV2 newStart = new PointV2(temp.getX() + xDir * 6, temp.getY() + yDir * 6);
			
			for (int i = snakePoints.size() - 1; i > 0; i--) {
				snakePoints.set(i, snakePoints.get(i - 1));
			}
			snakePoints.set(0, newStart);
			if (elongate) {
				for (int i = 0; i < 3; i++) {
					snakePoints.add(last);
					if (last.getX() < snakePoints.get(snakePoints.size() - 2).getX()) {
						newXDir = 1;
					} else if (last.getX() > snakePoints.get(snakePoints.size() - 2).getX()) {
						newXDir = -1;
					} else
						newXDir = 0;
					if (last.getY() < snakePoints.get(snakePoints.size() - 2).getY()) {
						newYDir = 1;
					} else if (last.getY() > snakePoints.get(snakePoints.size() - 2).getY()) {
						newYDir = -1;
					} else
						newYDir = 0;
					
					last = new PointV2(last.getX() - newXDir*6, last.getY() - newYDir*6);
				}
				elongate = false;
			}
		}
		
	}
	
	public void pause() {
		isPaused = !isPaused;
	}
	
	public boolean snakeCollision() {
		int x = this.getX();
		int y = this.getY();
		
		for (int i = 1; i < snakePoints.size() - 1; i++) {
			if (snakePoints.get(i).getX() == x && snakePoints.get(i).getY() == y) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	public boolean isMoving() {
		return isMoving;
	}
	
	public void setIsMoving(boolean b) {
		isMoving = b;
	}
	
	public int getXDir() {
		return xDir;
	}
	
	public int getYDir() {
		return yDir;
	}
	
	public void setXDir (int x) {
		xDir = x;
	}
	
	public void setYDir (int y) {
		yDir = y;
	}
	
	//X position of head of snake
	public int getX() {
		return snakePoints.get(0).getX();
	}
	
	//Y position of head of snake
	public int getY() {
		return snakePoints.get(0).getY();
	}
	
	public void setElongate(boolean b) {
		elongate = b;
	}
	
	public int getSize() {
		return snakePoints.size();
	}
	

}
