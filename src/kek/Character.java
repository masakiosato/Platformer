package kek;

import java.awt.Rectangle;

public class Character {
	Rectangle hitBox;
	double accelX, accelY, velX, velY;
	int windowWidth, windowHeight;
	boolean jump = false;
	Character(int x, int y, int width, int height, int ww, int wh) {
		hitBox = new Rectangle(x, y, width, height);
		windowWidth = ww;
		windowHeight = wh;
	}
	void correct() {
		if (hitBox.x < 0) {
			hitBox.x = 0;
			accelX = 0;
			velX = 0;
		}
		if (hitBox.x > windowWidth - hitBox.width) {
			hitBox.x = windowWidth - hitBox.width;
			accelX = 0;
			velX = 0;
		}
		if (hitBox.y > windowHeight - hitBox.height) {
			hitBox.y = windowHeight - hitBox.height;
			accelY = 0;
			velY = 0;
			jump = false;
		}
	}
	void jump() {
		if (jump) {
			if (velY == 0) velY = 10;
			accelY = -0.3;
			hitBox.y -= (int)velY;
			velY += accelY;
		}
	}
	void move(boolean right, boolean left) {
		if (right) {
			accelX = -0.1;
			velX = 3;
		} else if (left) {
			accelX = 0.1;
			velX = -3;
		}
		hitBox.x += (int)velX;
		if (velX <= 0.2 && velX >= -0.2) {
			accelX = 0;
			velX = 0;
		} else velX += accelX;
	}
}
