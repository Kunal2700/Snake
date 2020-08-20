package SnakeV2;

import java.awt.Color;
import java.awt.Graphics;

public class TokenV2 {
	
	private int x, y, score;
	private SnakeV2 snake;
	
	public TokenV2(SnakeV2 s) {
		x = (int) (Math.random() * 393);
		y = (int) (Math.random() * 393);

		snake = s;
	}
	
	public int getScore() {
		return score;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 8, 8);
		
	}
	public void changePosition() {
		x = (int) (Math.random() * 393);
		y = (int) (Math.random() * 393);
	}
	
	public boolean snakeCollision() {
		int snakeX = snake.getX() + 3;	// +2 to get center
		int snakeY = snake.getY() + 3;
		
		if (snakeX >= (x - 1) && snakeX <= (x + 9)) {
			if (snakeY >= (y - 1) && snakeY <= (y + 9)) {
				changePosition();
				score++;
				snake.setElongate(true);
				return true;
			}
		}
		return false;
		
	}
}
