import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class PlayScreen extends JPanel{
	
	public PlayScreen(GameWindow w){
		setSize((int) (w.getWidth()/2.5),w.getHeight()-40);
		setBackground(Color.BLACK);
		setLocation(w.getWidth()/3,10);
		setBorder(BorderFactory.createEtchedBorder(1, Color.BLACK, Color.DARK_GRAY));
		//setSize(400,600);
		setVisible(true);
		
	}
}
