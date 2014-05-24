import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	// deciding parameters to the MainMenu window
	int screenWidth = 275;
	int screenHeight = 200;

	int buttonWidth = 100;
	int buttonHeight = 40;

	JButton Play, Quit;

	public MainMenu() {
		// calling buttons and actions
		addButtons();
		addActions();

		getContentPane().setLayout(null);

		// setting button positions
		Play.setBounds((screenWidth - buttonWidth) / 2, 5, buttonWidth,
				buttonHeight);
		Quit.setBounds((screenWidth - buttonWidth) / 2, 50, buttonWidth,
				buttonHeight);

		// adding buttons
		getContentPane().add(Play);
		getContentPane().add(Quit);

		// Some JFrame utilities
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth, screenHeight);
		setTitle("World of Pong : Wrath of the SoftUni");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);

	}

	private void addButtons() {
		// adding buttons to JFrame
		Play = new JButton("Play");
		Quit = new JButton("Quit");

	}

	private void addActions() {
		// giving the play button a meaning
		Play.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				new Game().start();

			}
		});
		// giving the quit button a meaning
		Quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});
	}
}
