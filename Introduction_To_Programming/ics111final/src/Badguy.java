import java.util.Random;

//2018 Joshua Hartmann

public class Badguy {

	private int x;
	private int y;
	private EZImage enemy;
	private boolean hidden = true;
	private int rightX;
	private int leftX;
	private int topY;
	private int botY;
	private int directionX = 1;
	private int directionY = 1;
	private int change = 1;

	Badguy(int i) {
		switch (i) {
		case 0:
			x = 160;
			y = 200;
			break;
		case 1:
			x = 512;
			y = 384;
			break;
		case 2:
			x = 560;
			y = 100;
			break;
		case 3:
			x = 900;
			y = 50;
			break;
		case 4:
			x = 150;
			y = 150;
			break;
		case 5:
			x = 620;
			y = 100;
			break;
		case 6:
			x = 780;
			y = 100;
			break;
		}
		if (i <= 4) {
			enemy = EZ.addImage("minitroll.png", x, y);
		} else {
			enemy = EZ.addImage("troll1.png", x, y);
		}
	}

	public void show() { // show enemy
		enemy.show();
		hidden = false;
	}

	public void hide() { // hide enemy
		enemy.hide();
		hidden = true;
	}

	public boolean shown() {	// return if enemy if on the screen
		return hidden;
	}

	public int getX() {
		return enemy.getXCenter();
	}

	public int getY() {
		return enemy.getYCenter();
	}

	public boolean isInside(int posx, int posy) { // check if badguy is in a certain point
		return enemy.isPointInElement(posx, posy);
	}

	public void move2(int a, int b) { // move the enemy towards the player
		int width = a - x;
		int height = b - y;
		int distance = width * width + height * height;
		distance = (int) Math.sqrt(distance);
		a = width * 5 / distance;
		b = height * 5 / distance;
		x = x + a;
		y = y + b;
		enemy.translateTo(x, y);
	}

	public void die() { // remove from the screen
		x = 5000;
		y = 5000;
		enemy.translateTo(x, y);
	}

	public void movementBounds(int i) { // make movement bounds
		switch (i) {
		case 0:
			leftX = 160;
			rightX = 900;
			topY = 200;
			botY = 500;
			break;
		case 1:
			topY = 100;
			botY = 384;
			break;
		case 2:
			leftX = 560;
			rightX = 900;
			topY = 100;
			botY = 400;
			break;
		case 3:
			topY = 50;
			botY = 700;
			break;
		case 4:
			leftX = 150;
			rightX = 1000;
			break;
		}
	}

	public void autoMove(int i) { // make the enemy follow its path
		switch (i) {
		case 0:
			if (y == topY && x == leftX) {
				change = 1;
				directionY = 1;
				directionX = 0;
			}
			if (y == botY && x == leftX) {
				if (change == 1) {
					directionY = 0;
					directionX = 1;
				} else {
					directionY = -1;
					directionX = 0;
				}
			}
			if (y == botY && x == rightX) {
				if (change == 1) {
					directionY = -1;
					directionX = 0;
				} else {
					directionY = 0;
					directionX = -1;
				}
			}
			if (y == topY && x == rightX) {
				change = -1;
				directionY = 1;
				directionX = 0;
			}
			break;
		case 1:
			directionX = 0;
			if (y == botY) {
				directionY = -1;
			}
			if (y == topY) {
				directionY = 1;
			}
			break;
		case 2:
			if (y == topY && x == leftX) {
				directionY = 1;
				directionX = 0;
			}
			if (y == botY && x == leftX) {
				directionY = 0;
				directionX = 1;
			}
			if (x == rightX && y == botY) {
				directionX = 0;
				directionY = -1;
			}
			if (y == topY && x == rightX) {
				directionY = 0;
				directionX = -1;
			}
			break;
		case 3:
			directionX = 0;
			if (y == topY) {
				directionY = 1;
			}
			if (y == botY) {
				directionY = -1;
			}
			break;
		case 4:
			directionY = 0;
			if (x == leftX) {
				directionX = 1;
			}
			if (x == rightX) {
				directionX = -1;
			}
			break;
		}

		x = x + directionX;
		y = y + directionY;
		enemy.translateTo(x, y);
	}

	public void reset(int i) {	// reset the location of enemy after player battle
		switch (i) {
		case 0:
			if ((x < 160 || x > 900) || (y < 200 || y > 500)) {
				x = 160;
				y = 200;
			}
			break;
		case 1:
			if (x != 512) {
				x = 512;
				y = 384;
			}
			break;
		case 2:
			if ((x < 560 || x > 900) || (y < 100 || y > 400)) {
				x = 560;
				y = 100;
			}
			break;
		case 3:
			if (x != 900) {
				x = 900;
				y = 50;
			}
			break;
		case 4:
			if (y != 100) {
				x = 150;
				y = 100;
			}
			break;
		case 5:
			if (x == 5000 && y == 5000) {
				x = 5000;
				y = 5000;
			} else if (y != 100) {
				x = 620;
				y = 100;
			}
		case 6:
			if (x == 5000 && y == 5000) {
				x = 5000;
				y = 5000;
			} else if (y != 100) {
				x = 780;
				y = 100;
			}
		}
	}

}
