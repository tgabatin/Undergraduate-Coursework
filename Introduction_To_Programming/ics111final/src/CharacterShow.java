
public class CharacterShow {

	private int x;
    private int y;
    private EZImage character;
    private boolean hidden = true;
    EZImage text; 

    
    //This allows the characters and the text to show depending on where the player is on the map. 
    CharacterShow(int z) {

        switch (z) {
        case 0: //Archer text and image.
            x = 1024/2;
            y = 650;
            character = EZ.addImage("archer.png", x, y);
            text = EZ.addImage("archertext.png", 620, 650);	
            break;
        case 1: //Elf text and image.
            x = 680;
            y = 250;
            character = EZ.addImage("elf.png", x, y); 
            text = EZ.addImage("dangertext.png", 585, 250);
            break;
        case 2: //Treeperson text and image.
            x = 250;
            y = 384;
            character = EZ.addImage("treeperson.png", x, y);
            text = EZ.addImage("treepersontext.png", 450, 768/2);	
            break;
        case 3: //Female knight text and image.
            x = 450;
            y = 600;
            character = EZ.addImage("femaleknight.png", x, y);
            text = EZ.addImage("healtext.png", 375, 600);
            break;
        case 4: //Gold knight text and image.
            x = 789;
            y = 256;
            character = EZ.addImage("goldknight.png", x, y);
            text = EZ.addImage("knighttext.png", 900,256);	
            break;
        case 5: //Wizard text and image.
            x = 222;
            y = 600;
            character = EZ.addImage("wizard.png", x, y);
            text = EZ.addImage("wizardtext.png", 325, 600);	
            default:
                break;
        }
    }
    
    //Allows the characters to be shown on the screen, returns false if they are not in dimension.
    void show() {
        character.show();
        hidden = false;
    }
    //Allows the characters to be hidden on the screen, returns true if the image is shown. 
    void hide() {
        character.hide();
        hidden = true;
    }
    
    public boolean shown() {
		return hidden;
	}

    //Allows the x and y coordinates of the player to be returned.
    public int getX() {
        return character.getXCenter();
    }

    public int getY() {
        return character.getYCenter();
    }

    public boolean isInside(int posx, int posy) {
        return character.isPointInElement(posx, posy);
    }

    //These methods show or hide the text depending on where the player is on the screen.
    void textShow() {
        text.show();
    }

    void textHide() {
        text.hide();
    }
   

}
