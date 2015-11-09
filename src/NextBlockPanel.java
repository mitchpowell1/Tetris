import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Extension of the BlockDisplayPanel class, displays the next block on the
 * queue.
 * 
 * @author Mitch Powell
 *
 */
public class NextBlockPanel extends BlockDisplayPanel {
	private GameWindow window;

	/**
	 * Constructor for the NextBlockPanel class
	 * 
	 * @param w
	 *            the parent GameWindow containing the instance
	 */
	public NextBlockPanel(GameWindow w) {
		this.window = w;
		setLocation(950, 50);
		setSize(200, 200);
		TitledBorder title = BorderFactory.createTitledBorder("NEXT");
		title.setTitleColor(Color.LIGHT_GRAY);
		title.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.DARK_GRAY));
		title.setTitleJustification(2);
		title.setTitleFont(window.getFont());
		setBorder(title);
		setBackground(Color.BLACK);
	}
}
