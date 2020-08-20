package SnakeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class Snake {
	
	List<Point> snakePoints;
	int xDir, yDir;
	final int STARTSIZE = 20, STARTX = 150, STARTY = 150;
	
	boolean isMoving, elongate, isPaused;
	
	
	public Snake() {
		snakePoints = new ArrayList<Point>();
		
		xDir = 0;
		yDir = 0;
		
		isMoving = false;
		elongate = false;
		isPaused = false;
		
		snakePoints.add(new Point(STARTX, STARTY));
		for (int i = 1; i < STARTSIZE; i++) {
			snakePoints.add(new Point(STARTX - i*4, STARTY));
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.green);
		
		for (Point p: snakePoints) {
			g.fillRect(p.getX(), p.getY(), 4, 4);
		}
	}
	
	public void move() {
		if (isMoving && !isPaused) {
			Point temp = snakePoints.get(0);
			Point last = snakePoints.get(snakePoints.size() - 1);
			Point newStart = new Point(temp.getX() + xDir * 4, temp.getY() + yDir * 4);
			
			for (int i = snakePoints.size() - 1; i > 0; i--) {
				snakePoints.set(i, snakePoints.get(i - 1));
			}
			snakePoints.set(0, newStart);
			if (elongate) {
				snakePoints.add(last);
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
	

}
