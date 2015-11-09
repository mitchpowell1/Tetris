import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class PauseMenu extends JPanel {
	private GameWindow window;
	private JButton resume;
	public PauseMenu(GameWindow w){
		this.window = w;
		resume = new JButton("Resume Game");
		resume.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				window.startGame();			
			}
			
		});
		setSize(1100,1100);
		setLocation(50,0);
		setBackground(Color.BLACK);
		TitledBorder title = new TitledBorder("PAUSED");
		title.setTitleColor(Color.LIGHT_GRAY);
		title.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		add(resume);
		setBorder(title);
	}
}
