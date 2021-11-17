import java.awt.Color;

//2018 Joshua Hartmann

public class Player {

	// player
	private EZImage warrior;
	private int spriteWidth;		// Width of each sprite
	private int spriteHeight;		// Height of each sprite
	private int direction = 0;		// Direction character is walking in
	private int walkSequence = 0;	// Walk sequence counter
	private int cycleSteps;			// Number of steps before cycling to next animation step
	private int counter = 0;		// Cycle counter
	private int x;					// Position of Sprite on screen
	private int y;
	private int mapX;				// global position on map
	private int mapY;
	private EZText coordinates = EZ.addText(100, 753, "", new Color(0, 0, 0), 20);

	// minimap
	private EZRectangle border; // minimap black border around it
	private EZImage miniMap; // actual minimap
	private int miniX = 100; // minimap player x
	private int miniY = 668; // minimap player y
	private EZCircle miniMe; // minimap player blimp

	// barrier around boss castle
	private int[] xp = { 0, 608, 608, 32, 32, 960, 960, 768, 768, 1056, 1056, 0 };
	private int[] yp = { 64, 64, -32, -32, -736, -736, -32, -32, 64, 64, -768, -768 };
	private EZPolygon barrier;

	Player(String imgFile, int startX, int startY, int width, int height, int steps) { // player object
		mapX = 1536; // set the player in the middle of the map
		mapY = 1152;
		border = EZ.addRectangle(100, 668, 194, 148, new Color(0, 0, 0), true); // minimap border
		miniMap = EZ.addImage("mapActualminiMap.png", 100, 668); // minimap
		miniMe = EZ.addCircle(miniX, miniY, 11, 11, new Color(0, 0, 0), true); // minimap player location
		x = startX;					// position of the sprite character on the screen
		y = startY;
		spriteWidth = width;		// Width of the sprite character
		spriteHeight = height;		// Height of the sprite character
		cycleSteps = steps;			// How many pixel movement steps to move before changing the sprite graphic
		warrior = EZ.addImage(imgFile, x, y);
		setImagePosition();
		barrier = EZ.addPolygon(xp, yp, new Color(80, 80, 80), true); // barrier	 
		barrier.translateBy(-1024, 0);
		barrier.pushToBack();	 
	}

	public int getX() {	// get player x coordinate
		return warrior.getXCenter();
	}
	
	public int getY() {	// get player y coordinate
		return warrior.getYCenter();
	}

	private void setImagePosition() {
		
		// Move the entire sprite sheet
		warrior.translateTo(x, y);
		
		// Show only a portion of the sprite sheet.
		// Portion is determined by setFocus which takes 4 parameters:
		// The 1st two numbers is the top left hand corner of the focus region.
		// The 2nd two numbers is the bottom right hand corner of the focus region.
		warrior.setFocus(walkSequence * spriteWidth, direction,
				walkSequence * spriteWidth + spriteWidth, direction + spriteHeight);
	}
	
	// move the player to the other side of the screen when they pass the edge and
	// move the barrier, move by 4 to have picture slowly translate on screen
	public void yUpTranslate() {
		y += 4;
		barrier.translateBy(0, 4);
		if (y >= 764) {
			y = 767;
		}
		warrior.translateTo(x, y);		
	}
	
	public void yDownTranslate() {
		y -= 4;
		barrier.translateBy(0, -4);
		if (y <= 4) {
			y = 1;
		}
		warrior.translateTo(x, y);
	}
	
	public void xLeftTranslate() {
		x += 4;
		barrier.translateBy(4, 0);
		if (x >= 1020) {
			x = 1023;
		}
		warrior.translateTo(x, y);
	}
	
	public void xRightTranslate() {
		x -= 4;
		barrier.translateBy(-4, 0);
		if (x <= 4) {
			x = 1;
		}
		warrior.translateTo(x, y);
	}
	
	public boolean isInside(int posx, int posy) {	// check if in a certain point
		return warrior.isPointInElement(posx, posy);
	}

	public void respawn() { // set player to bottom of the screen	 
		x = 512;
		y = 758;
		if (mapX < 1024) {	// check global x coordinate
			mapX = 512;
			miniX = 36;
		} else if (mapX > 1024 && mapX < 2048) {
			mapX = 1536;
			miniX = 100;
		} else if (mapX > 2048 && mapX < 3072) {
			mapX = 2560;
			miniX = 164;
		}
		if (mapY < 768) {	// check global y coordinate
			mapY = 764;
			miniY = 643;
		} else if (mapY > 768 && mapY < 1536) {
			mapY = 1532;
			miniY = 692;
		} else if (mapY > 1536 && mapY < 2304) {
			mapY = 2300;
			miniY = 740;
		}
		miniMe.translateTo(miniX, miniY);
		warrior.translateTo(x, y);
	}
	
	// direction player moves
	private void moveDown(int stepSize) {
		y = y + stepSize;

		direction = 0;

		if ((counter % cycleSteps) == 0) {
			walkSequence++;
			if (walkSequence > 3)
				walkSequence = 0;
		}
		mapY += stepSize;
		miniMe.translateBy(0, 0.12);
		if (barrier.isPointInElement(x, y)) {
			y -= stepSize;
			mapY -= stepSize;
			miniMe.translateBy(0, -0.12);
		}
		counter++;
		setImagePosition();
	}
	
	private void moveLeft(int stepSize) {
		x = x - stepSize;
		direction = spriteHeight;

		if ((counter % cycleSteps) == 0) {
			walkSequence--;
			if (walkSequence < 0)
				walkSequence = 3;
		}
		mapX -= stepSize;
		miniMe.translateBy(-0.12, 0);
		if (barrier.isPointInElement(x, y)) {
			x += stepSize;
			mapX += stepSize;
			miniMe.translateBy(0.12, 0);
		}
		counter++;
		setImagePosition();
	}

	private void moveRight(int stepSize) {
		x = x + stepSize;
		direction = spriteHeight * 2;
		
		if ((counter % cycleSteps) == 0) {
			walkSequence++;
			if (walkSequence > 3)
				walkSequence = 0;
		}
		mapX += stepSize;
		miniMe.translateBy(0.12, 0);
		if (barrier.isPointInElement(x, y)) {
			x -= stepSize;
			mapX -= stepSize;
			miniMe.translateBy(-0.12, 0);
		}
		counter++;

		setImagePosition();
	}

	private void moveUp(int stepSize) {
		y = y - stepSize;
		direction = spriteHeight * 3;

		if ((counter % cycleSteps) == 0) {
			walkSequence--;
			if (walkSequence < 0)
				walkSequence = 3;
		}
		mapY -= stepSize;
		miniMe.translateBy(0, -0.12);
		if (barrier.isPointInElement(x, y)) {
			y += stepSize;
			mapY += stepSize;
			miniMe.translateBy(0, 0.12);
		}
		setImagePosition();

		counter++;
	}


	// Keyboard controls for moving the character.
	public void moveCharacter() {
		if (EZInteraction.isKeyDown('w') && mapY > 0) {
			moveUp(2);
		} else if (EZInteraction.isKeyDown('a') && mapX > 0) {
			moveLeft(2);
		} else if (EZInteraction.isKeyDown('s') && mapY < 2304) {
			moveDown(2);
		} else if (EZInteraction.isKeyDown('d') && mapX < 3072) {
			moveRight(2);
		}
		coordinates.setMsg("X:  " + mapX + "   Y:  " + mapY);
	}
	
	public void setter() {
		mapX = 300;
		mapY = 600;
	}
	
	public boolean castleEntry() {
		boolean enter = false;
		if (mapX < 900 && mapY < 700) {
			enter = true;
		}
		return enter;
	}
	
}
