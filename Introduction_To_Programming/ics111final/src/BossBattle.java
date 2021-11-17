import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

// 2018 Joshua Hartmann

public class BossBattle {
	private EZImage troll;
	private EZImage knight;
	private EZImage background;
	private int trollX;
	private int trollY;
	private int knightX;
	private int knightY;
	private int playerHealth = 200;
	private int enemyHealth = 900;
	private int directionY = 1;
	private Random ranNum = new Random();
	private EZRectangle playerHBar;
	private EZRectangle playerHBarOut;
	private EZRectangle enemyHBar;
	private EZRectangle enemyHBarOut;
	private int pWidth;
	private int eWidth;

	private ArrayList<EZImage> fire = new ArrayList<EZImage>(); // players fireballs
	private ArrayList<EZImage> light = new ArrayList<EZImage>(); // enemy lightnings

	BossBattle() {
		background = EZ.addImage("graybattle.png", 512, 384);
		knightX = 300;
		knightY = 384;
		knight = EZ.addImage("battleKnight.png", knightX, knightY);
		trollX = 824;
		trollY = 200;
		troll = EZ.addImage("Troll3.png", trollX, trollY);
		troll.scaleTo(2);
		pWidth = 200;
		playerHBarOut = EZ.addRectangle(knightX, knightY - 90, 200, 35, Color.RED, false);
		playerHBar = EZ.addRectangle(knightX, knightY - 90, pWidth, 35, Color.RED, true);
		eWidth = 900;
		enemyHBarOut = EZ.addRectangle(512, 100, 900, 75, Color.BLACK, false);
		enemyHBar = EZ.addRectangle(512, 100, eWidth, 75, Color.RED, true);
	}

	void finalBattle() { // final battle
		playerMove();
		enemyMove();
		fireBall();
		lightning();
	}

	public int playerH() {
		return playerHealth;
	}

	public int enemyH() {
		return enemyHealth;
	}

	void playerMove() { // move the character
		if (EZInteraction.isKeyDown('w') && knightY > 0) {
			knightY -= 3;
		}
		if (EZInteraction.isKeyDown('a') && knightX > 0) {
			knightX -= 3;
		}
		if (EZInteraction.isKeyDown('s') && knightY < 768) {
			knightY += 3;
		}
		if (EZInteraction.isKeyDown('d') && knightX < 1024) {
			knightX += 3;
		}
		knight.translateTo(knightX, knightY);
		playerHBarOut.translateTo(knightX, knightY - 90);
		playerHBar.translateTo(knightX, knightY - 90);
		enemyHit();
	}

	void enemyMove() { // have the enemy move up and down
		if (trollY == 200) {
			directionY = 1;
		}
		if (trollY == 600) {
			directionY = -1;
		}
		trollY = trollY + directionY;
		troll.translateTo(trollX, trollY);
		playerHit();
	}

	private void playerHit() { // check if the player got hit
		for (int i = 0; i < light.size(); i++) {
			if (light.get(i).isPointInElement(knightX, knightY)) {
				light.get(i).translateTo(-100, knightY);
				playerHealth -= 40;
				playerHBar.setWidth(playerHealth);
				System.out.println(playerHealth);
			}
		}
	}

	private void enemyHit() {
		for (int i = 0; i < fire.size(); i++) {
			if (fire.get(i).isPointInElement(trollX, trollY)) { // check if enemy got hit
				fire.get(i).translateTo(1124, trollY);
				enemyHealth -= 20;
				enemyHBar.setWidth(enemyHealth);
				System.out.println(enemyHealth);
			}
		}
	}

	private void fireBall() { // players fireballs
		if (EZInteraction.wasKeyReleased(KeyEvent.VK_SPACE)) {
			fire.add(EZ.addImage("fireball.png", knightX, knightY));
		}
		for (int i = 0; i < fire.size(); i++) { // move each fireball
			fire.get(i).translateBy(5, 0);

			if (fire.get(i).getXCenter() > 1024) {
				EZ.removeEZElement(fire.get(i));
				fire.remove(i);
			}
			System.out.println(fire.size());
		}
	}
	
	//New code here
	

	private void lightning() { // enemys lightning
		if (trollY == ranNum.nextInt(100) + 200 || trollY == ranNum.nextInt(200) + 200
				|| trollY == ranNum.nextInt(400) + 200) {
			light.add(EZ.addImage("thunderbolt.png", trollX, trollY));
		}
		for (int i = 0; i < light.size(); i++) { // move each lightning
			light.get(i).translateBy(-5, 0);

			if (light.get(i).getXCenter() < 0) {
				EZ.removeEZElement(light.get(i));
				light.remove(i);
			}
		}
	}

}
