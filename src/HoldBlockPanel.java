import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Extension of the BlockDisplayPanel class, displays the block that the player
 * is holding.
 * 
 * @author Mitch Powell
 *
 */
public class HoldBlockPanel extends BlockDisplayPanel {

	private GameWindow window;

	/**
	 * Constructor for the HoldBlockPanel class
	 * 
	 * @param w
	 *            the parent GameWindow containing the instance.
	 */
	public HoldBlockPanel(GameWindow w) {
		this.window = w;
		setLocation(50, 50);
		setSize(200, 200);
		TitledBorder title = BorderFactory.createTitledBorder("HOLD");
		title.setTitleColor(Color.LIGHT_GRAY);
		title.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,
				Color.DARK_GRAY));
		title.setTitleJustification(2);
		title.setTitleFont(window.getFont());
		setBorder(title);
		setBackground(Color.BLACK);
	}
}
