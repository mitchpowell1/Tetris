import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Pause Button class
 * @author Mitch Powell
 *
 */
public class PauseButton extends JButton {
	//Note: I couldn't get this to draw on the window unless I made it as
	// a separate class for some reason...
	private GameWindow window;
	
	/**
	 * Creates an instance of the pause button
	 * @param w The window the button is drawn onto.
	 */
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
