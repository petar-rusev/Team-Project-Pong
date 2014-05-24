import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.sound.sampled.*;
import java.io.*;


import javax.swing.*;



public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static PlayerPaddle player; // loading player1 paddle
	InputHandler IH ;
	public static Player2Paddle player2; // loading player2 paddle
    public static Ball ball;
  


	JFrame frame; // window of the game
	public final int WIDTH = 400; // window width
	public final int HEIGHT = WIDTH / 16 * 9; // window height
	public final Dimension gameSize = new Dimension(WIDTH,HEIGHT); // merges height and width of the window into a single variable
	public final String TITLE = "World of Pong : Wrath of the SoftUni"; // adding the name of the game


	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // setting up the image


	public static boolean gameRunning; // checking if game is running

	int p1Score , p2Score; // creating scores


	public void run(){
		requestFocus(); // doesn't need to click on game to run it

		while(gameRunning){ // if gameRunning==true
			tick();
			render();

			try {				
				Thread.sleep(7); // render speed
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public synchronized void start(){ // starting the game
		gameRunning = true;
		new Thread(this).start();
	}

	public synchronized void stop(){ // stopping the game
		gameRunning = true; //false
		//System.exit(0);
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public Game(){ // game frame
		frame = new JFrame(); // creating the frame of the game

		//Setting the prefered size
		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		//setting frame defaults
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		

		IH = new InputHandler(this);		//setting up the inputhandler

		player = new PlayerPaddle(0, 60);	// adding paddles to p1 and p2
		player2 = new Player2Paddle(395, 60);
		ball = new Ball(getWidth() / 2, getHeight() /2);

	}

	public void tick(){ // this is the tick counter
		player.tick(this);
		player2.tick(this);
		ball.tick(this);

	}
	public void render(){ // graphics

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // buffers 3 times and reduces tearing
			return;							
		}

		Graphics g = bs.getDrawGraphics(); // creating graphics

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	

		g.setColor(Color.WHITE); // score color

		//drawing scores
		g.drawString("Player 1: " + p1Score , 0 , 10);
		g.drawString("Player 2: " + p2Score , getWidth() -60 , 10);
		//drawing instructions
		g.drawString("Player 1 Controls:", 0, 210);
		g.drawString("Up - 'W'", 0, 220);
		g.drawString("Down - 'S'", 0, 230);
		g.drawString("Player 2 Controls:", 310, 210);
		g.drawString("Up - ArrowUp", 330, 220);
		g.drawString("Down - ArrowDown", 300, 230);
		g.drawString("FIrst one to 10 is the winner", 130 , 10);
	    g.drawLine(0, 115, 410, 115);
	    g.drawLine(205, 0, 205, 250);
		player.render(g);
		player2.render(g);
		ball.render(g);
		//drawing the winner , when he reaches his score
		if (p1Score==10) {
			g.drawString("PLAYER 1 WINS", 150, 100);
		}else if(p2Score == 10) {
			g.drawString("PLAYER 2 WINS", 150, 100);
		}
		g.dispose();
		bs.show();
		

	}

	public static void main (String[] args){
		Game game = new Game();						
		game.start();
	}
}
