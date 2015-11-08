import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class HoldBlockPanel extends BlockDisplayPanel {
	public HoldBlockPanel(){
		setLocation(100,50);
		setSize(200,200);
		TitledBorder title = BorderFactory.createTitledBorder("Hold");
		title.setTitleColor(Color.LIGHT_GRAY);
		title.setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY,Color.DARK_GRAY));
		title.setTitleJustification(2);
		setBorder(title);
		setBackground(Color.BLACK);
	}
}
