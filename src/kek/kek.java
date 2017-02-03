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
	Character player = new Character(220, windowHeight - 10, 10, 10, windowWidth, windowHeight);
	boolean right = false, left = false;
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT) right = true;
		if (keyCode == KeyEvent.VK_LEFT) left = true;
		if (keyCode == KeyEvent.VK_UP) player.jump = true;
	}
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT) right = false;
		if (keyCode == KeyEvent.VK_LEFT) left = false;
	}
	public void run() {
		while(true) {
			player.move(right, left);
			player.jump();
			player.correct();
			repaint();
			try {Thread.sleep(10);} catch (Exception ex) {}
		}
	}
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, windowWidth, windowHeight);
		g.setColor(Color.WHITE);
		g.fillRect(player.hitBox.x, player.hitBox.y, player.hitBox.width, player.hitBox.height);
		g.setColor(Color.RED);
		g.drawString("accelX: " + player.accelX, 50, 25);
		g.drawString("velX: " + player.velX, 50, 50);
		g.drawString("accelY: " + player.accelY, 50, 75);
		g.drawString("velY: " + player.velY, 50, 100);
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
