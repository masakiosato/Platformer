package kek;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class kek extends JPanel implements KeyListener, Runnable {
	static int windowWidth = 600, windowHeight = 400; 
	Character player = new Character(220, windowHeight - 10, 10, 10);
	boolean right = false, left = false, jump = false;
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT) right = true;
		if (keyCode == KeyEvent.VK_LEFT) left = true;
		if (keyCode == KeyEvent.VK_UP) jump = true;
	}
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT) right = false;
		if (keyCode == KeyEvent.VK_LEFT) left = false;
	}
	public void run() {
		while(true) {
			if (right) {
				player.accelX = -0.1;
				player.velX = 3;
			} else if (left) {
				player.accelX = 0.1;
				player.velX = -3;
			}
			player.hitBox.x += (int)player.velX;
			if (player.velX <= 0.2 && player.velX >= -0.2) player.accelX = 0;
			else player.velX += player.accelX;
			
			if (player.hitBox.x < 0) {
				player.hitBox.x = 0;
				player.accelX = 0;
				player.velX = 0;
			}
			if (player.hitBox.x > windowWidth - player.hitBox.width) {
				player.hitBox.x = windowWidth - player.hitBox.width;
				player.accelX = 0;
				player.velX = 0;
			}
			if (player.hitBox.y > windowHeight - player.hitBox.height) {
				player.hitBox.y = windowHeight - player.hitBox.height;
				player.accelY = 0;
				player.velY = 0;
			}
			
			if (jump) {
				player.jump(windowHeight);
				if (player.hitBox.y >= windowHeight - player.hitBox.height) {
					jump = false;
					player.accelY = 0;
					player.velY = 0;
				}
			}
			
			repaint();
			try {Thread.sleep(10);} catch (Exception ex) {}
		}
	}
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, windowWidth, windowHeight);
		g.setColor(Color.WHITE);
		g.fillRect(player.hitBox.x, player.hitBox.y, player.hitBox.width, player.hitBox.height);
	}
	public static void main(String[] args) {
		kek game = new kek();
		JFrame frame = new JFrame();
		frame.setSize(windowWidth, windowHeight+22);
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		game.addKeyListener(game);
		game.setFocusable(true);
		Thread t = new Thread(game);
		t.start();
	}
}
