package pong;

import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Graphics2D;

class RBat{

	//In principe hetzelfde als de LBat class, maar dan voor speler 2.
	
	static int y = 220;
	int ya = 0;
	int w = 10;
	int h = 60;
	
	private Game game;

		public RBat(Game game){
			this.game = game;
		}

		public void move(){
			if (y + ya > 0 && y + ya < game.getHeight()-(h))
				y = y + ya;
		}

		public void paint(Graphics2D g){
			g.fillRect(game.getWidth()-(w+5), y, w, h);
		}

		public void keyReleased(KeyEvent e){
			ya = 0;
		}

		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == 38) //pijl-omhoog toets
				ya = -1;
			if(e.getKeyCode() == 40) //pijl-omlaag toets
				ya = 1;
		}

		public Rectangle getBounds() {
			return new Rectangle(game.getWidth()-(w+5), y, w, h);
		}

		public int getLeftX () {
			return game.getWidth()-(w+5);
		}
}
