package pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


// was eerst extends JPanel
public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	// Bal, linker- en rechterbat aanmaken, drie nieuwe objects
	Ball ball = new Ball(this);
	LBat lbat = new LBat(this);
	RBat rbat = new RBat(this);

	//constructor
	public Game() {

		//KeyListener voor toetsdetectie
		addKeyListener(new KeyListener(){

			public void keyTyped(KeyEvent e){
			}

			//Als een toets wordt ingedrukt, dan moeten de KeyPressed methods van lbat en rbat worden aangeroepen
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == 65 || e.getKeyCode() == 90){
				lbat.keyPressed(e);
				}
				if (e.getKeyCode() == 38 || e.getKeyCode() == 40){
				rbat.keyPressed(e);
				}
				//if (e.getKeyCode() == 32 || e.getKeyCode() == 49 || e.getKeyCode() == 50){
				//ball.keyPressed(e);
				//}
			}

			//Hetzelfde, voor de KeyReleased methods
			public void keyReleased(KeyEvent e){
				if (e.getKeyCode() == 65 || e.getKeyCode() == 90){
					lbat.keyReleased(e);
				}
				if (e.getKeyCode() == 38 || e.getKeyCode() == 40){
				rbat.keyReleased(e);
				}
			}
		});
		//De KeyListener werkt alleen als er focus is op de method:
		setFocusable(true);
	}

	//Een move method met alle drie de move methods van de bal en de twee bats, zodat die in een keer aangeroepen kan worden
	private void move() {
		ball.move();
		lbat.move();
		rbat.move();
	}

	//Het scherm tekenen. Elke keer als deze method wordt aangeroepen wordt het scherm helemaal opnieuw getekend
	@Override
	public void paint(Graphics g){

		Graphics2D g2d = (Graphics2D) g;

		//Statische elementen van het scherm tekenen
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.gray);
		g2d.fillRect((getWidth())/2 - 2, 0, 4, getHeight());
		
		
		Font font = new Font("Monospaced", Font.PLAIN, 16);
		Font scoreFont = new Font("Monospaced", Font.PLAIN, 200);
		
		g2d.setFont(scoreFont);
		g2d.setColor(Color.darkGray);
		
		if (ball.score1 < 10){
			g2d.drawString(String.valueOf(ball.score1), getWidth()/2 - 132, getHeight()/2 + 72);
		} else {
			g2d.drawString(String.valueOf(ball.score1), getWidth()/2 - 250, getHeight()/2 + 72);
		}
		g2d.drawString(String.valueOf(ball.score2), getWidth()/2 + 10, getHeight()/2 + 72);
		
		if (ball.startStop == 1){
			g2d.setFont(font);
			g2d.drawString("Bounce = " + String.valueOf((int) ball.level), getWidth()/2 - 120, 120);
			g2d.drawString("Speed = " + String.valueOf(1 + (ball.level/40)), getWidth()/2 + 10, 120);
		}
		
		if (ball.startStop == -1){
			g2d.setFont(font);
			g2d.setColor(Color.black);
			g2d.fillRect(getWidth()/2 - 2, 100, 4, 25);
			g2d.setColor(Color.gray);
			g2d.drawString("Press SPACE to fire", getWidth()/2-90, 120);
			g2d.drawString("Player 1 controls:", 50, 100);
			g2d.drawString("SHIFT = up", 50, 120);
			g2d.drawString("CONTROL = down", 50, 140);
			g2d.drawString("Player 2 controls:", getWidth()-230, 100);
			g2d.drawString("ARROW UP = up", getWidth()-230, 120);
			g2d.drawString("ARROW DOWN = down", getWidth()-230, 140);
		} 

		

		//Dynamische elementen tekenen met de paint method van het betreffende object
		ball.paint(g2d);
		lbat.paint(g2d);
		rbat.paint(g2d);
	}

	//Als speler 1 wint wordt deze method aangeroepen, er popt een scherm op met een melding
	public void playerOneWins() {
		JOptionPane.showMessageDialog(this, "Player 1 wins!!!\n" + "The score is:\n" + "Player 1: " + ball.score1 + "\n"+ "Player 2: " + ball.score2, "End of round", JOptionPane.YES_NO_OPTION);
	}

	//Hetzelfde voor speler 2
	public void playerTwoWins() {
		JOptionPane.showMessageDialog(this, "Player 2 wins!!!\n" + "The score is:\n" + "Player 1: " + ball.score1 + "\n"+ "Player 2: " + ball.score2, "End of round", JOptionPane.YES_NO_OPTION);
	}

	//De main method om het programma te starten
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Pong");						//Nieuw venster maken
		Game game = new Game();									//Nieuwe game maken
		frame.add(game);										//De game in het venster plaatsen
		//frame.setSize(800, 600);								//De initiele grootte van het venster bepalen
		frame.getRootPane().setPreferredSize(new Dimension(800,500));
		frame.pack();
		frame.setVisible(true);									//Maakt het scherm zichtbaar
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Zorgt ervoor dat processen gestopt worden als het venster wordt afgesloten
		

		//De animatieloop
		while (true){
			game.move();		//Beweegt alle objecten
			game.repaint();		//Tekent het scherm opnieuw
			Thread.sleep(2);	//Bepaalt de snelheid (eigenlijk traagheid) van het spel
		}
	}
}
