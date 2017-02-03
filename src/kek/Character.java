package kek;

import java.awt.Rectangle;

public class Character {
	Rectangle hitBox;
	double accelX, accelY, velX, velY;
	
	Character(int x, int y, int width, int height) {
		hitBox = new Rectangle(x, y, width, height);
	}
	
	void jump(int wH) {
		if (velY == 0) velY = 3;
		accelY = -0.4;
		hitBox.y -= velY;
		velY += accelY;
	}
}
