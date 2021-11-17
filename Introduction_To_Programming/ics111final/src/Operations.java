// 2018 Taylor Gabatino

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Operations {

	static Color c = new Color(0, 0, 0);
	static Color a = new Color(255, 255, 255);

	static EZRectangle startscreen;
	static EZRectangle endscreen;
	static EZRectangle tutorialRectangle;

	static EZText pressStart;
	static EZText storyTitle;
	static EZText deathScreen;
	static EZText tip;
	static EZText goal;
	
	static EZImage tutorial = EZ.addImage("tutorialscreen.png", 1024 / 2, 768 / 2);
	

	static EZSound startSound;
	static EZSound backgroundmusic = EZ.addSound("backsound.wav");
	static EZSound battleMusic = EZ.addSound("battleMusic.wav");

	static int fontsize = 65;
	static int counter = 0;
	static int time = counter;

	Operations() {

	}

	void startScreen() {
		startscreen = EZ.addRectangle(1024 / 2, 768 / 2, 1024, 768, c, true);
        tutorialRectangle = EZ.addRectangle(1024 / 2, 300, 500, 300, c, false);

        storyTitle = EZ.addText(1024 / 2, 245, ".Knight Battle.", a, fontsize);
        pressStart = EZ.addText(1024 / 2, 300, "Press space to start", a, fontsize);
        tip = EZ.addText(1024 / 2, 700, "Tip. If you need tutorials press t for help!", a, fontsize);
        goal = EZ.addText(1024 / 2, 400, "Beat the trolls in the fastest amount of time.", a, fontsize);

        storyTitle.setFont("font.ttf");
        pressStart.setFont("font.ttf");
        tip.setFont("font.ttf");
        goal.setFont("font.ttf");
        
		startSound = EZ.addSound("startSong.wav");
		startSound.play();


		boolean start = true;

		while (start) {

	

			if (EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {

				start = false;

				break;
			}
			System.out.println("Slow down code");

		}
		System.out.println("Slow down code");
		startscreen.hide();
		storyTitle.hide();
		pressStart.hide();
		startSound.stop();
		EZ.refreshScreen();
	}

	void tutorialScreen() {

		if (EZInteraction.isKeyDown('t')) {
			tutorial.show();
			tutorial.pullToFront();
		} else {
			tutorial.hide();
		}
	}

	public static void endScreen() throws IOException {
		endscreen = EZ.addRectangle(512, 384, 1024, 768, c, true);
		endScore();
		
		while (true) {
			
		} 
	}

	public static void timer() {
		counter++;
		time = counter / 60;
		System.out.println("time: " + time);
	}
	
	private static void endScore() throws IOException {
		if (time < playerstats.highScore) {
			playerstats.writeScore(time);
			EZText score = EZ.addText(512, 430, "New High Score!!  " + (time/60) + ": " + time%60 + " min.", new Color(200, 200, 0), 70);
		} else {
			EZText score = EZ.addText(512, 430, "Your Score:  " + (time/60) + ": " + time%60 + " min.", new Color(200, 200, 0), 70);
			EZText high = EZ.addText(512, 480, "High Score:  " + (playerstats.highScore/60) + ": " + playerstats.highScore%60 + " min.", Color.yellow, 70);
			EZText fail = EZ.addText(512, 384, "Better Luck Next Time", Color.WHITE, 70);
		}
	}

	void battleMusic() {
		battleMusic.loop();
	}

	void battleMusicStop() {
		battleMusic.pause();
	}

	void backgroundmusic() {
		
		backgroundmusic.loop();
	}

	void backgroundStop() {
		backgroundmusic.pause();
	}

}
