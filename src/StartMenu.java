import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;

/**
 * The StartMenu class displays instructions for the user before the game
 * begins.
 * 
 * @author Mitch Powell
 *
 */
public class StartMenu extends JPanel {
	private JButton playButton;
	private JPanel instructionPanel;
	private GameWindow window;

	public StartMenu(GameWindow w) {
		setFocusable(true);
		this.window = w;
		// setLayout(new BoxLayout(1));
		setLocation(50, 25);
		setSize(1100, 625);
		instructionPanel = new JPanel();
		TitledBorder instructionBorder = new TitledBorder("HOW TO PLAY");
		instructionBorder.setTitleColor(Color.LIGHT_GRAY);
		instructionBorder.setBorder(BorderFactory
				.createLineBorder(Color.LIGHT_GRAY));
		instructionBorder.setTitleJustification(2);
		instructionBorder.setTitleFont(window.getFont());
		setBorder(instructionBorder);
		instructionPanel.setSize(100, 1000);
		instructionPanel.setBackground(Color.BLACK);
		playButton = new JButton("Play!");
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				w.startGame();

			}

		});

		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));
		add(instructionPanel);
		add(playButton, BorderLayout.SOUTH);
	}

}
