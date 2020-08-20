package SnakeGame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends Applet implements Runnable, KeyListener{
	
	Graphics gfx;
	Image img;
	Thread thread;
	Snake snake;
	boolean gameOver;
	Token token;
	
	public void init() {
		this.resize(400, 420);
		gameOver = false;
		img = createImage(400, 420);
		gfx = img.getGraphics();
		this.addKeyListener(this);
		snake = new Snake();
		token = new Token(snake);
		thread = new Thread(this);
		thread.start();
		
	}
	
	public void paint(Graphics g) {
		
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, 400, 420);
		
		gfx.setColor(Color.white);
		gfx.fillRect(0, 400, 400, 20); 	//boundary
		
		if (!gameOver) {
			snake.draw(gfx);
			token.draw(gfx);
			gfx.setColor(Color.red);
			gfx.drawString("Score: " + token.getScore(), 10, 415); 
		}
		
		else {
			gfx.setColor(Color.red);
			gfx.drawString("GAME OVER", 160, 150);
			gfx.drawString("Score: " + token.getScore(), 165, 180);
			
		}
			
		g.drawImage(img, 0, 0, null);
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void repaint(Graphics g) {
		paint(g);
	}

	@Override
	public void run() {
		while (true) {
			
			
			if (!gameOver) {
				snake.move();
				this.checkGameOver();
				token.snakeCollision();
			}
			
			this.repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 
		}
				
	}

	public void checkGameOver() {
		if (snake.getX() < 0 || snake.getX() > 396)
			gameOver = true;
		if (snake.getY() < 0 || snake.getY() > 396)
			gameOver = true;
		if (snake.snakeCollision())
			gameOver = true;
	}
	
	public void restart() {
		gameOver = false;
		snake = new Snake();
		token = new Token(snake);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (!snake.isMoving()) {
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || 
					e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || 
					e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
				snake.setIsMoving(true);
			}
		}
		if (!snake.isPaused()) {
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				if (snake.getYDir() != 1) {
					snake.setYDir(-1);
					snake.setXDir(0);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				if (snake.getYDir() != -1) {
					snake.setYDir(1);
					snake.setXDir(0);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				if (snake.getXDir() != 1) {
					snake.setXDir(-1);
					snake.setYDir(0);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				if (snake.getXDir() != -1) {
					snake.setXDir(1);
					snake.setYDir(0);
				}
			}
		}
		
		
		if (gameOver) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				restart();
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			snake.pause();
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
				
	}

	@Override
	public void keyTyped(KeyEvent e) {
				
	}
	
	

}
