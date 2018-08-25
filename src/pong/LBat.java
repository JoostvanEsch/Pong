package pong;

import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Graphics2D;


class LBat{

	//De positie en beweging van de bat bij start
	static int y = 220;
	int ya = 0;

	//De grootte van de bat
	int w = 10;
	int h = 60;
	
	private Game game;

		public LBat(Game game){
			this.game = game;
		}

		//Zo lang als de bat binnen het scherm is, kan die bewegen
		public void move(){
			if (y + ya > 0 && y + ya < game.getHeight()-(h))
				y = y + ya;
		}

		//De bat tekenen
		public void paint(Graphics2D g){
			g.fillRect(5, y, w, h);
		}

		//Speler 1 kan met de shift- en controltoets de bat op en neer bewegen. Bij shift wordt de richting van de bat omhoog, bij control naar beneden
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == 65) //up
				ya = -1;
			if(e.getKeyCode() == 90) //down
				ya = 1;
		}

		//Als de toets is losgelaten, gaat de bat weer stil staan
		public void keyReleased(KeyEvent e){
			ya = 0;
		}

		//Geeft positie en dimensies van de bat om botsing met de bal te detecteren
		public Rectangle getBounds() {
			return new Rectangle(5, y, w, h);
		}

		//Geeft de 'doellijn' aan van speler 1
		public int getRightX() {
			return (w + 5);
		}
}
