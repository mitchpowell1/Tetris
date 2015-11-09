import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


/**
 * The StartMenu class displays instructions for the user before the game begins.
 * @author Mitch Powell
 *
 */
public class StartMenu extends JPanel {
	private JButton playButton;
	private JPanel instructionPanel;
	private GameWindow window;
	public StartMenu(GameWindow w){
		setFocusable(true);
		this.window = w;
		//setLayout(new BoxLayout(1));
		setLocation(50,0);
		setSize(1100,1100);
		instructionPanel = new JPanel();
		TitledBorder instructionBorder = new TitledBorder("HOW TO PLAY");
		instructionBorder.setTitleColor(Color.LIGHT_GRAY);
		instructionBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		instructionBorder.setTitleJustification(2);
		instructionBorder.setTitleFont(window.getFont());
		setBorder(instructionBorder);
		instructionPanel.setSize(100, 1000);
		instructionPanel.setBackground(Color.BLACK);
		playButton = new JButton("Play!");
		playButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				w.startGame();
				
			}
			
		});
		addKeyListener(new GameKeyListener());
		setBackground(Color.BLACK);
		//playButton.setLocation(1000,0);
		add(instructionPanel);
		add(playButton);
	}
	
	/**
	 * This private inner class listens for keyboard input to adjust
	 * the volume of the theme song.
	 * @author Mitch Powell
	 *
	 */
	private class GameKeyListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()){
			case KeyEvent.VK_M:
				window.getPlayer().setMute(!window.getPlayer().isMute());
				break;
			case KeyEvent.VK_MINUS:
				window.getPlayer().setVolume(window.getPlayer().getVolume()-.1);
				break;
			case KeyEvent.VK_EQUALS:
				window.getPlayer().setVolume(window.getPlayer().getVolume()+.1);
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
