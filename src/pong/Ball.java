package pong;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Color;

class Ball{

	//Startpositie van de bal
	double x = 15;
	double y = 170;

	//Richting waarop de bal begint te bewegen
	double xa = 10;
	int ya = 0;
	
	double startStop = -1;
	int speed = 3;
	double level = 0;

	//Begin puntentelling
	int score1 = 0;
	int score2 = 0;
	
	int raakHoogteL = 0;
	int raakHoogteR = 0;

	private Game game;

		//constructor
		public Ball(Game game) {
			this.game = game;
		}
		
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == 50)
				level++;
			if(e.getKeyCode() == 49)
				if (level >= 1){
				level--;
				}
			if(e.getKeyCode() == 32)
				startStop = startStop * -1;
		}
		
		//Het gedrag van de bal instellen
		void move() {
			
			raakHoogteL = (int) Math.round(((this.y-15)-LBat.y)/6);
			raakHoogteR = (int) Math.round(((this.y-15)-RBat.y)/6);
			
			if(startStop == -1){
				if(x < 16){
					y = LBat.y+15;
				}
				if(x > game.getWidth() - 46){
					y = RBat.y+15;
				}
			}
			
			//Verander verticaal van richting als de boven- of onderkant van het scherm wordt geraakt
			if 	(y + ya < 0)
				ya = ya*-1;
			if 	(y + ya > game.getHeight() - 30)
				ya = ya*-1;
			
			//Verander horizontaal van richting als de bal tegen een bat aankomt
			if 	(collisionLeft()){
				switch(this.raakHoogteL){
				case -7: xa = 2; ya = -10; break;
				case -6: xa = 4; ya = -9; break;
				case -5: xa = 6; ya = -8; break;
				case -4: xa = 7; ya = -7; break;
				case -3: xa = 8; ya = -6; break;
				case -2: xa = 9; ya = -4; break;
				case -1: xa = 10; ya = -2; break;
				case  0: xa = 10; ya =  0; break;
				case  1: xa = 10; ya =  2; break;
				case  2: xa = 9; ya =  4; break;
				case  3: xa = 8; ya =  6; break;
				case  4: xa = 7; ya =  7; break;
				case  5: xa = 6; ya =  8; break;
				case  6: xa = 4; ya =  9; break;
				case  7: xa = 2; ya =  10; break;
				
				default: xa = 10; ya =  0; break;
				}
				x = game.lbat.getRightX();
				level++;
				}
			if 	(collisionRight()){
				switch(this.raakHoogteR){
				case -7: xa = -2; ya = -10; break;
				case -6: xa = -4; ya = -9; break;
				case -5: xa = -6; ya = -8; break;
				case -4: xa = -7; ya = -7; break;
				case -3: xa = -8; ya = -6; break;
				case -2: xa = -9; ya = -4; break;
				case -1: xa = -10; ya = -2; break;
				case  0: xa = -10; ya =  0; break;
				case  1: xa = -10; ya =  2; break;
				case  2: xa = -9; ya =  4; break;
				case  3: xa = -8; ya =  6; break;
				case  4: xa = -7; ya =  7; break;
				case  5: xa = -6; ya =  8; break;
				case  6: xa = -4; ya =  9; break;
				case  7: xa = -2; ya =  10; break;
				
				default: xa = -10; ya =  0; break;
				}
				x = game.rbat.getLeftX() - 30;
				level++;
				}
			
			//Als de bal voorbij de bat gaat, heeft speler 1 of 2 gewonnen
			if (x + xa < 1){
				xa = 10;
				ya = 0;
				score2++;
				level=0;
				startStop = -1;
				//game.playerTwoWins();
				x = 15;
				y = LBat.y + 15;
				}
			if 	(x + xa > game.getWidth() - 30){
				xa = -10;
				ya = 0;
				score1++;
				level=0;
				startStop = -1;
				//game.playerOneWins();
				x = game.getWidth() - 45;
				y = RBat.y + 15;
				}
			
			//Bij elke iteratie van het spel beweegt de bal als volgt:
			x = x + 0.1*(((startStop+1)/2)*(1 + (level/40))*xa);
			y = y + 0.1*(((startStop+1)/2)*(1 + (level/40))*ya);
		}
		
		//Tekent de bal op variabele positie (x, y)
		public void paint(Graphics2D g) {
			g.setColor(Color.green);
			g.fillOval((int) x,(int) y,30,30);
		}

		//Tekent een box om de bal heen om de botsing te detecteren
		public Rectangle getBounds() {
			return new Rectangle((int) x, (int) y, 30, 30);
		}
		
		//Checkt of de linkerbat en de bal elkaar raken
		private boolean collisionLeft() {
			return game.lbat.getBounds().intersects(getBounds());
		}

		//Checkt of de rechterbat en de bal elkaar raken
		private boolean collisionRight() {
			return game.rbat.getBounds().intersects(getBounds());
		}
}
