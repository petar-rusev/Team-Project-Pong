import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Ball {
	int x, y; // setting up the ball
	int size = 16;
	int speed = 3;
	int vx, vy;

	Rectangle boundingBox; // ball collision

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;

		// giving the ball velocity
		vx = speed;
		vy = speed;

		boundingBox = new Rectangle(x, y, size, size); // collision box
		boundingBox.setBounds(x, y, size, size);
	}

	@SuppressWarnings("restriction")
	public void tick(Game game) {
		boundingBox.setBounds(x, y, size, size);
		

		if (x <= 0) { // player2 score counter
			game.p2Score++;
			vx = speed;
		    this.x = 100;
		    this.y = 80;
		    try {
				InputStream in = new FileInputStream("res\\3.wav");
				AudioStream audioStream = new AudioStream(in);
				AudioPlayer.player.start(audioStream); // giving the collision sound
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else if (x + size >= game.getWidth()) {
			game.p1Score++; // player1 score counter
			vx = -speed;
			this.x = 300;
			this.y = 80;
			try {
				InputStream in = new FileInputStream("res\\3.wav");
				AudioStream audioStream = new AudioStream(in);
				AudioPlayer.player.start(audioStream); // giving the collision sound
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (y <= 0) { // ball movements
			vy = speed;
		} else if (y + +size >= game.getHeight()) {
			vy = -speed;
		}

		x += vx;
		y += vy;

		paddleCollide(game);
		if (game.p1Score == 10 || game.p2Score == 10) { // adding endscore
			game.stop();

		}
	}

	@SuppressWarnings("restriction")
	private void paddleCollide(Game game) { // giving the collision a meaning
												
		if (boundingBox.intersects(Game.player.boundingBox)) { // giving ball collision to player1paddle
			vx = speed;
			try {
				InputStream in = new FileInputStream("res\\1.wav");
				AudioStream audioStream = new AudioStream(in);
				AudioPlayer.player.start(audioStream);//giving the collision sound
				
			} catch (FileNotFoundException e) {				
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else if (boundingBox.intersects(Game.player2.boundingBox)) { // giving ball collision to player2paddle
			vx = -speed;
			try {
				InputStream in = new FileInputStream("res\\2.wav");
				AudioStream audioStream = new AudioStream(in);
				AudioPlayer.player.start(audioStream); // giving the collision sound
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	public void render(Graphics g) { // giving the ball graphics
		g.setColor(Color.WHITE);
		g.fillOval(x, y, size, size);
	}
}
