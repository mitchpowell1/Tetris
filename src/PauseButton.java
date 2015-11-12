import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class PauseButton extends JButton {
	private GameWindow window;
	public PauseButton(GameWindow w){
		this.window = w;
		setText("Pause");
		setLocation(1000,300);
		setSize(100,50);
		setFont(w.getFont());
		setBackground(Color.LIGHT_GRAY);
		System.out.println("Pause button created");
		addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				w.pauseGame();
				
			}
		});
	}
}
