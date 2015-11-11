import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * An extension of the JPanel class to display the relevant statistics of a
 * Tetris game.
 * 
 * @author Mitch Powell
 *
 */
public class StatsWindow extends JPanel {

	private GameWindow window;
	private JLabel timeLabel;
	private JLabel levelLabel;
	private JLabel scoreLabel;
	private JLabel lineLabel;

	/**
	 * Constructor for an instance of the StatsWindow class
	 * 
	 * @param w
	 *            the parent GameWindow that contains the instance.
	 */
	public StatsWindow(GameWindow w) {
		this.window = w;
		setBackground(Color.BLACK);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		TitledBorder title = BorderFactory.createTitledBorder("STATISTICS");
		title.setTitleColor(Color.LIGHT_GRAY);
		title.setTitleJustification(2);
		title.setTitleFont(window.getFont());
		title.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.DARK_GRAY));
		setBorder(title);
		setSize(300, 200);
		setLocation(50, 300);
		timeLabel = new JLabel("Time: 0\n");
		timeLabel.setForeground(Color.LIGHT_GRAY);
		timeLabel.setFont(window.getFont());
		// timeLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		levelLabel = new JLabel("Level: ");
		levelLabel.setForeground(Color.LIGHT_GRAY);
		levelLabel.setFont(window.getFont());
		scoreLabel = new JLabel("Score: ");
		scoreLabel.setForeground(Color.LIGHT_GRAY);
		scoreLabel.setFont(window.getFont());
		lineLabel = new JLabel("Lines Cleared: ");
		lineLabel.setForeground(Color.LIGHT_GRAY);
		lineLabel.setFont(window.getFont());
		add(timeLabel);
		add(levelLabel);
		add(scoreLabel);
		add(lineLabel);
		setVisible(true);
	}

	/**
	 * Returns the timeLabel instance Variable
	 * 
	 * @return the time label (updated with the timer)
	 */
	public JLabel getTimeLabel() {
		return this.timeLabel;
	}
}
