import java.awt.Color;
import java.io.*;
import java.util.*;

//2018 Joshua Hartmann

public class playerstats {

	// health bar and player stats
	private static EZRectangle healthBar = EZ.addRectangle(512, 50, 400, 75, new Color(123, 0, 0), true);
	private static EZRectangle hOutline = EZ.addRectangle(512, 50, 400, 75, new Color(123, 0, 0), false);
	private static EZText att = EZ.addText(970, 610, "10", new Color(0,0,0), 50);
	private static EZText def = EZ.addText(970, 660, "0", new Color(0,0,0), 50);
	private static EZText exp = EZ.addText(970, 710, "0", new Color(0,0,0), 50);
	private static EZImage sword = EZ.addImage("sword.png", 900, 615);
	private static EZImage shield = EZ.addImage("shield.png",900, 665);
	private static EZImage experience = EZ.addImage("exp.png", 900, 713);
	private static EZImage[] hearts = new EZImage[3];
	static int highScore;
	
	/* 
	 * from left to right each value in the array represents the players stat value
	 * [0] = health
	 * [1] = attack strength
	 * [2] = defense strength
	 * [3] = experience level
	 * [4] = lives, start off with 3
	 */
	private static int[] stats = {100, 10, 0, 0, 3};
	
	static void heartInit() {	// show the hearts and stat icons
		for (int i = 0; i <3; i++) {
			hearts[i] = EZ.addImage("heart.png", 810 + (i * 63), 50);
		}
		sword.show();
		shield.show();
		experience.show();
	}
	
	public static int readStats(int i) {	// looks up the value of a stat
		return stats[i];
	}
	
	public static void writeStats(int i, int b) {	// changes the value of a stat
		if (i == 0) {	// only changes the health
			stats[i] = b;
		} else {	// changes the other stats
			stats[i] += b;
		}
	}

	public static void heal() {	// restore the players health
		int i = stats[3];
		int a = 100 + (i * 10);
		while (stats[0] < a) {
			stats[0]++;
			screenStats();
		}
		stats[0] = a;
		screenStats();
	}
	
	public static void battleLoss() {	// reset after losing a battle
		stats[4]--;
		int minus = stats[4];
		EZ.removeEZElement(hearts[minus]);
		int i = 0;
		stats[0] = 20;	// set player health to 20 after loss
	}
	
	public static void screenStats() {	// show the stats on the screen
		int w = readStats(0) * 4;
		if (readStats(0) > 400) {	// so the health bar stays in place
			w = 400;
		}
		healthBar.setWidth(w);
		healthBar.translateTo(512, 50);
		healthBar.translateBy((w - 400)/2, 0);
		att.setMsg("" + stats[1]);
		def.setMsg("" + stats[2]);
		exp.setMsg("" + stats[3]);
		System.out.println("actual health " + stats[0]);
		System.out.println("healthbar     " + w);
		EZ.refreshScreen();
	}
	
	public static int readScore() throws IOException {
		FileReader fr = new FileReader("highscore.txt");
		Scanner sc = new Scanner(fr);
		while (sc.hasNext()) {
			highScore = sc.nextInt();
		}
		fr.close();
		sc.close();
		return highScore;
	}
	
	public static void writeScore(int i) throws IOException {
		FileWriter fw = new FileWriter("highscore.txt");
		fw.write(i + "");
		fw.close();
	}
}
