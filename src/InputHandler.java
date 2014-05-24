import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		// giving players movement
		if (keyCode == KeyEvent.VK_UP) {
			Game.player2.goingDown = false;
			Game.player2.goingUp = true;

		}
		if (keyCode == KeyEvent.VK_DOWN) {
			Game.player2.goingUp = false;
			Game.player2.goingDown = true;
		}
		if (keyCode == KeyEvent.VK_W) {
			Game.player.goingDown = false;
			Game.player.goingUp = true;
		}
		if (keyCode == KeyEvent.VK_S) {
			Game.player.goingUp = false;
			Game.player.goingDown = true;
		}

	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// improving players movement for released key
		if (keyCode == KeyEvent.VK_UP) {
			Game.player2.goingDown = false;
			Game.player2.goingUp = false;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			Game.player2.goingDown = false;
			Game.player2.goingUp = false;
		}

		if (keyCode == KeyEvent.VK_W) {
			Game.player.goingDown = false;
			Game.player.goingUp = false;
		}
		if (keyCode == KeyEvent.VK_S) {
			Game.player.goingDown = false;
			Game.player.goingUp = false;
		}
		// exit button in the game
		if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}

	}

	public void keyTyped(KeyEvent e) {

	}
}
