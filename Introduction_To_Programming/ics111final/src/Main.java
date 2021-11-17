import java.awt.Color;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// 2018 Joshua Hartmann
// 2018 Taylor Gabatino

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {

		EZ.initialize(1024, 768);

		Operations operations = new Operations();
		operations.startScreen();

		EZImage map = EZ.addImage("mapBase.png", 512, 384);
		int row = 2; // row deals with Y axis
		int column = 2; // column deals with X axis

		Player myPlayer = new Player("Knight2.png", 512, 384, 32, 48, 10); // make the player
		int playerX = myPlayer.getX();
		int playerY = myPlayer.getY();
		EZCircle playerRadius = EZ.addCircle(512, 384, 200, 200, null, false);
		EZImage trees = EZ.addImage("mapTrees.png", 512, 384); // the background

		CharacterShow character[] = new CharacterShow[6]; // this is going to show the characters
		for (int z = 0; z < 6; z++) {
			character[z] = new CharacterShow(z);
		}

		playerRadius.pushToBack();

		Badguy[] enemy = new Badguy[7];
		for (int i = 0; i < 7; i++) {
			enemy[i] = new Badguy(i);
		}
		for (int i = 0; i < 5; i++) {
			enemy[i].movementBounds(i);
		}

		Battle battleMode = new Battle(); // initiate battle class

		EZText dying = EZ.addText(512, 45, "dying...... slowly", new Color(0, 0, 0), 40);
		playerstats.screenStats();
		playerstats.heartInit();

		playerstats.readScore();
		operations.backgroundmusic();
		while (playerstats.readStats(4) > 0) {
			
			operations.tutorialScreen();
			if(EZInteraction.wasKeyReleased('7')) {
				myPlayer.setter();
			}

			operations.timer();

			myPlayer.moveCharacter(); // move the player
			playerRadius.translateTo(myPlayer.getX(), myPlayer.getY());
			playerX = myPlayer.getX();
			playerY = myPlayer.getY();

			for (int i = 0; i < 5; i++) {
				enemy[i].autoMove(i);
			}
			// enemy battle
			for (int i = 0; i < 7; i++) { // checks each enemy
				if (myPlayer.isInside(enemy[i].getX(), enemy[i].getY()) && enemy[i].shown() == false) {
				operations.backgroundStop();
				operations.battleMusic();

					battleMode.show(column, row); // if touched then start the battle
					battleMode.BattleMethod(i);
					if (battleMode.playerWin() == true) {
						enemy[i].die(); // remove the enemy if you win the fight
						System.out.println("one down");
					} else {
						myPlayer.respawn(); // removes 1 of the players life if you lose  
					}
					battleMode.hide();
					operations.battleMusicStop();
					operations.backgroundmusic();
				}
			}

			// All of the characters will show, along with their text

			// heal
			for (int i = 0; i < 6; i++) {
				if (playerRadius.isPointInElement(character[i].getX(), character[i].getY())
						&& character[i].shown() == false) {
					character[i].textShow();
					if (i == 3) {
						playerstats.heal();
					}
				} else {
					character[i].textHide();
				}
			}

			if (row == 3 && column == 3) {
				character[3].show();
				character[4].show();
			} else {
				character[3].hide();

				character[4].hide();
			}

			if (row == 2 && column == 1) {
				character[1].show();
			} else {
				character[1].hide();
			}

			if (row == 2 && column == 2) {
				character[2].show();
			} else {
				character[2].hide();
			}

			if (row == 1 && column == 2) {
				character[0].show();

			} else {
				character[0].hide();

			}
			if (row == 1 && column == 3) {
				character[5].show();
			} else {
				character[5].hide();
			}

			// when player is in a certain row and column of the map then show enemy
			// when player leaves the row and column an enemy is in then hide enemy
			// when player is in a certain row and column of the map then show enemy
			// when player leaves the row and column an enemy is in then hide enemy
			// ship area 2 enemy
			if (row == 1 && column == 2) {
				enemy[0].show();
				enemy[1].show();
				if (playerRadius.isPointInElement(enemy[0].getX(), enemy[0].getY())) {
					enemy[0].move2(playerX, playerY);
				} else {
					enemy[0].reset(0);
				}
				if (playerRadius.isPointInElement(enemy[1].getX(), enemy[1].getY())) {
					enemy[1].move2(playerX, playerY);
				} else {
					enemy[1].reset(1);
				}
			} else {
				enemy[0].hide();
				enemy[1].hide();
			}
			// top corner one, enemy
			if (row == 1 && column == 3) {
				enemy[2].show();
				if (playerRadius.isPointInElement(enemy[2].getX(), enemy[2].getY())) {
					enemy[2].move2(playerX, playerY);
				} else {
					enemy[2].reset(2);
				}
			} else {
				enemy[2].hide();
			}
			// guards
			if (row == 2 && column == 1) {
				enemy[5].show();
				enemy[6].show();
				if (playerRadius.isPointInElement(enemy[5].getX(), enemy[5].getY())) {
					enemy[5].move2(playerX, playerY);
				} else {
					enemy[5].reset(5);
				}
				if (playerRadius.isPointInElement(enemy[6].getX(), enemy[6].getY())) {
					enemy[6].move2(playerX, playerY);
				} else {
					enemy[6].reset(6);
				}
			} else {
				enemy[5].hide();
				enemy[6].hide();
			}
			// side enemy
			if (row == 2 && column == 3) {
				enemy[3].show();
				if (playerRadius.isPointInElement(enemy[3].getX(), enemy[3].getY())) {
					enemy[3].move2(playerX, playerY);
				} else {
					enemy[3].reset(3);
				}
			} else {
				enemy[3].hide();
			}
			// bottom corner enemy
			if (row == 3 && column == 1) {
				enemy[4].show();
				if (playerRadius.isPointInElement(enemy[4].getX(), enemy[4].getY())) {
					enemy[4].move2(playerX, playerY);
				} else {
					enemy[4].reset(4);
				}
			} else {
				enemy[4].hide();
			}

			// move map as player walks to the edges
			// there are three rows within a 3x3 area
			if (row == 1 || row == 2) { // if row is 1 or 2 then can move map down  
				if (myPlayer.getY() >= 768) { // check if player is bottom of screen
					for (int n = 0; n < 7; n++) {
						enemy[n].hide();
					}
					for (int n = 0; n < 6; n++) {
						character[n].hide();
					}
					for (int i = 0; i < 764; i += 4) {
						map.translateBy(0, -4);
						trees.translateBy(0, -4);
						myPlayer.yDownTranslate();
						EZ.refreshScreen();
					}
					row++; // since going down row number increases

				}
			}
			if (row == 2 || row == 3) { // if row is 2 or 3 then can move map up  
				if (myPlayer.getY() <= 0) { // check if player is top of screen
					for (int n = 0; n < 7; n++) {
						enemy[n].hide();
					}
					for (int n = 0; n < 6; n++) {
						character[n].hide();
					}
					for (int i = 0; i < 764; i += 4) {
						map.translateBy(0, 4);
						trees.translateBy(0, 4);
						myPlayer.yUpTranslate();
						EZ.refreshScreen();
					}
					row--; // since going up row number decreases
				}
			}
			// there are three columns within a 3x3 area
			if (column == 1 || column == 2) { // if column is 1 or 2 can move map left  
				if (myPlayer.getX() >= 1024) {
					for (int n = 0; n < 7; n++) {
						enemy[n].hide();
					}
					for (int n = 0; n < 6; n++) {
						character[n].hide();
					}
					for (int i = 0; i < 1024; i += 4) {
						map.translateBy(-4, 0);
						trees.translateBy(-4, 0);
						myPlayer.xRightTranslate();
						EZ.refreshScreen();
					}
					column++;
				}
			}
			if (column == 2 || column == 3) { // if column is 2 or 3 can move map right  
				if (myPlayer.getX() <= 0) {
					for (int n = 0; n < 7; n++) {
						enemy[n].hide();
					}
					for (int n = 0; n < 6; n++) {
						character[n].hide();
					}
					for (int i = 0; i < 1024; i += 4) {
						map.translateBy(4, 0);
						trees.translateBy(4, 0);
						myPlayer.xLeftTranslate();
						EZ.refreshScreen();
					}
					column--;
				}
			}
			if (myPlayer.castleEntry()) {
				operations.backgroundStop();
				operations.battleMusic();
                BossBattle boss = new BossBattle();
                while (boss.playerH() > 0 && boss.enemyH() > 0) {
                    boss.finalBattle();
                    EZ.refreshScreen();
                }
                if(boss.playerH() > 0) {
                    Operations.endScreen();
                }
            }
			
			EZ.refreshScreen();

		}

	}

}