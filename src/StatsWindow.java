import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class StatsWindow extends JPanel {
	public StatsWindow(){
		setBackground(Color.BLACK);
		TitledBorder title = BorderFactory.createTitledBorder("Statistics");
		title.setTitleColor(Color.LIGHT_GRAY);
		title.setTitleJustification(2);
		title.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY, Color.DARK_GRAY));
		setBorder(title);
		setSize(300,300);
		setLocation(50,300);
		setVisible(true);
	}
}
