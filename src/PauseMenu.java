import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * The Pause Menu class is an extension of the JPanel class and displays when
 * the Tetris game is paused.
 * 
 * @author Mitch Powell
 *
 */
public class PauseMenu extends JPanel {
	private GameWindow window;
	private JButton resume;
	private JButton reset;
	private JButton options;

	/**
	 * Constructor for the Pause Menu class
	 * 
	 * @param w
	 *            the parent GameWindow that the pause menu is contained within.
	 */
	public PauseMenu(GameWindow w) {
		this.window = w;
		resume = new JButton("Resume Game");
		resume.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.startGame();
			}

		});
		reset = new JButton("Reset Game");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Reset Game");

			}

		});
		options = new JButton("Options");
		options.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Display options menu");
			}

		});

		setSize(1100, 1100);
		setLocation(50, 0);
		setBackground(Color.BLACK);
		TitledBorder title = new TitledBorder("PAUSED");
		title.setTitleColor(Color.LIGHT_GRAY);
		title.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		title.setTitleFont(window.getFont());
		title.setTitleJustification(2);
		add(resume);
		add(reset);
		add(options);
		setBorder(title);
	}
}
