import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

// 2018 Taylor Gabatino

public class Battle {

	Color c = new Color(153, 102, 0);
	Color v = new Color(0, 0, 0); 
	int fontsize = 50;
	EZRectangle battleupdate; 
	EZRectangle attackselection; 


	EZText battleUpdates;

	EZImage player;
	EZImage playerhurt;
	EZImage enemy;
	EZImage enemyhurt;
	EZImage enemyattacks;
	EZImage attacks;

	EZImage background1;

	EZImage knight;
	EZImage troll;

	EZRectangle background;
	Random randomGenerator = new Random();
	Thread thread = new Thread();
	boolean win = false;
	EZRectangle selection; // attack selector
	int pHpBarMinus = 200;
	int eHpBarMinus = 200;
	EZRectangle playerHpBar; // player hp bar
	EZRectangle playerHpBarOut; // hp outline
	EZRectangle enemyHpBar; // enemy hp bar
	EZRectangle enemyHpBarOut; // hp outline
	EZText textupdate;

	int hpplayer = 100;
	int hpenemy = 100;

	int enemyattack;

	Battle() {
		
	}

	void show(int a, int b) {

		if (a == 1 && b == 2) {
			background1 = EZ.addImage("greenlavabattle.png", 1024 / 2, 768 / 2);
			troll = EZ.addImage("troll1.png", 800, 768 / 2);
		} else {
			background1 = EZ.addImage("fieldbattle.png", 1024 / 2, 768 / 2);
			troll = EZ.addImage("minitroll.png", 800, 768 / 2);
		}
		knight = EZ.addImage("battleknight.png", 1024 / 4, 768 / 2);

		battleupdate = EZ.addRectangle(100, 25, 1950, 50, c, true); // Use this rectangle to update what is happening in
																	// the game
		battleUpdates = EZ.addText(512, 25, "", v, fontsize);
		attacks = EZ.addImage("attacks.png", 512, 710); // Use this rectangle to allow the player to select their
														// choices
		selection = EZ.addRectangle(280, 680, 300, 50, new Color(0, 0, 0), false); // attack selector

		playerHpBar = EZ.addRectangle(300, 200, pHpBarMinus, 50, Color.BLUE, true);
		playerHpBarOut = EZ.addRectangle(700, 200, 200, 50, Color.BLUE, false);
		enemyHpBar = EZ.addRectangle(700, 200, eHpBarMinus, 50, Color.RED, true);
		enemyHpBarOut = EZ.addRectangle(700, 200, 200, 50, Color.RED, false);
		hpplayer = playerstats.readStats(0);
	}

	public void hide() {

		EZ.removeEZElement(background1);
		EZ.removeEZElement(background);
		EZ.removeEZElement(troll);

		EZ.removeEZElement(background);
		EZ.removeEZElement(troll);

		EZ.removeEZElement(battleupdate);
		EZ.removeEZElement(battleUpdates);
		EZ.removeEZElement(knight);
		EZ.removeEZElement(attacks);
		EZ.removeEZElement(selection); // attack selector
		EZ.removeEZElement(playerHpBar);
		EZ.removeEZElement(playerHpBarOut);
		EZ.removeEZElement(enemyHpBar);
		EZ.removeEZElement(enemyHpBarOut);
		hpenemy = 100;
	}

	void eHpMinus(int i) {
		enemyHpBar.setWidth(hpenemy * 2); // set bar to be enemy health
		enemyHpBar.translateBy(-i, 0); // translate by negative attack damage divided by 2
	}

	void pHpMinus (int i) {
		playerHpBar.setWidth(hpenemy * 2); // set bar to be player health
		playerHpBar.translateBy(-i, 0); // translate by negative attack damage divided by 2
	}
	
	void BattleMethod(int i) throws InterruptedException, IOException {

		while (hpplayer >= 1 && hpenemy >= 1) {
			playerAttack();
			System.out.println("enemy:   " + hpenemy);
			EnemyAttack(i);
			System.out.println("player:   " + hpplayer);
		}

		if (hpenemy <= 0) {
			win = true;
			playerstats.writeStats(0, hpplayer + 10 + enemyattack);
			playerstats.writeStats(1, 2);
			playerstats.writeStats(2, 2);
			playerstats.writeStats(3, 1);
		} else if (hpplayer <= 0) {
			playerstats.battleLoss();
			win = false;
		}
		playerstats.screenStats();
	}

	public boolean playerWin() { // checker to see if player won the fight
		return win;
	}

	private void playerAttack() { // movement of the selector box with arrow keys
		int select = 6; // keeps track of which option youre on
		select = 0;
		while (true) {
			if (EZInteraction.isKeyDown(KeyEvent.VK_UP) && selection.getYCenter() != 680) {
				selection.translateBy(0, -60);
				select -= 2;
			}
			if (EZInteraction.isKeyDown(KeyEvent.VK_DOWN) && selection.getYCenter() != 740) {
				selection.translateBy(0, 60);
				select += 2;
			}
			if (EZInteraction.isKeyDown(KeyEvent.VK_LEFT) && selection.getXCenter() != 280) {
				selection.translateBy(-450, 0);
				select -= 1;
			}
			if (EZInteraction.isKeyDown(KeyEvent.VK_RIGHT) && selection.getXCenter() != 730) {
				selection.translateBy(450, 0);
				select += 1;
			}

			if (EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {
				// if (selection.getXCenter() == 60 && selection.getYCenter() == 500) {//old
				// long method
				if (select == 0) { // feels a little buggy this way but is easier
					fireball();
					battleUpdates.setMsg("Player used Fireball Blast!");
				}
				if (select == 1) { // wind
					aircannon();
					battleUpdates.setMsg("Player used Wind Strike!");
				}
				if (select == 2) { // thunder
					thunderbolt();
					battleUpdates.setMsg("Player used Thunderbolt!");
				}
				if (select == 3) { // water
					tsunamigun();
					battleUpdates.setMsg("Player used Water Cannon!");
				}

				break;

			}
			knight.rotateTo(0);
			EZ.refreshScreen();
		}
		selection.translateTo(280, 680);

	}

	void fireball() { // fireball attack
		int a = 45;
		a += playerstats.readStats(1);
		hpenemy -= a;
		EZImage fireball = EZ.addImage("fireball.png", 150, 400);
		while (fireball.getXCenter() <= 800) {
			fireball.rotateBy(50);
			fireball.translateBy(5, 0);

			if (fireball.isPointInElement(troll.getXCenter(), troll.getYCenter())) {
				troll.rotateTo(100);
				
			}

			EZ.refreshScreen();
		}
		troll.rotateTo(0);
		eHpMinus(a);
		EZ.removeEZElement(fireball);
	}

	void thunderbolt() {
		int b = 15;
		b += playerstats.readStats(1);
		hpenemy -= b;
		EZImage thunderbolt = EZ.addImage("thunderbolt.png", 150, 400);
		while (thunderbolt.getXCenter() <= 800) {
			thunderbolt.rotateBy(180);
			thunderbolt.translateBy(5, 0);
			
			if (thunderbolt.isPointInElement(troll.getXCenter(), troll.getYCenter())) {
				troll.rotateTo(100);
				
			}
			EZ.refreshScreen();
		}
		troll.rotateTo(0);
		eHpMinus(b);
		EZ.removeEZElement(thunderbolt);
	}

	void tsunamigun() {
		int c = 10;
		c += playerstats.readStats(1);
		hpenemy -= c;
		EZImage tsunamigun = EZ.addImage("tsunamigun.png", 150, 400);
		while (tsunamigun.getXCenter() <= 800) {
			tsunamigun.rotateBy(200);
			tsunamigun.translateBy(5, 0);
			
			if (tsunamigun.isPointInElement(troll.getXCenter(), troll.getYCenter())) {
				troll.rotateTo(100);
			}
			
			EZ.refreshScreen();
		}
		troll.rotateTo(0);
		eHpMinus(c);
		EZ.removeEZElement(tsunamigun);
	}

	void aircannon() {
		int d = 5;
		d += playerstats.readStats(1);
		hpenemy -= d;
		EZImage aircannon = EZ.addImage("airsummon.png", 150, 400);
		while (aircannon.getXCenter() <= 800) {
			aircannon.rotateBy(125);
			aircannon.translateBy(5, 0);
			
			if (aircannon.isPointInElement(troll.getXCenter(), troll.getYCenter())) {
				troll.rotateTo(100);
			}
			
			EZ.refreshScreen();
		}
		troll.rotateTo(0);
		eHpMinus(d);
		EZ.removeEZElement(aircannon);
	}

	void EnemyAttack(int i) throws InterruptedException {
		int startTimer = 2;
		while (startTimer >= 0) {
			thread.sleep(1000);
			startTimer--;
		}
		randomGenerator = new Random();
		enemyattack = randomGenerator.nextInt(5) + 15;
		if (i >= 6) { // add extra attack if the enemy is a guard
			enemyattack += 25;
		}

		EZImage enemyattacks = EZ.addImage("enemyattack.png", 800, 768 / 2);

		while (enemyattacks.getXCenter() >= 200) {
			enemyattacks.rotateBy(180);
			enemyattacks.translateBy(-5, 0);
			if (enemyattacks.isPointInElement(knight.getXCenter(), knight.getYCenter())) {
				knight.rotateBy(8);
			}
			EZ.refreshScreen();
		}
		pHpMinus(enemyattack);
		EZ.removeEZElement(enemyattacks);
		enemyattack -= playerstats.readStats(2); // minus points from players defense
		System.out.println(enemyattack);
		hpplayer -= enemyattack;

	}
}