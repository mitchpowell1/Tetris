import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The PlayScreenClass is an extension of the JPanel class which displays the
 * Tetrominos and is the focal point of the GameWindow class.
 * @author Mitch Powell
 *
 */

public class PlayScreen extends JPanel{
	private GameWindow window;
	private GameKeyListener gameKeys;
	
	/**
	 * Constructor for the PlayScreen class
	 * @param w the parent GameWindow
	 */
	public PlayScreen(GameWindow w){
		this.window = w;
		this.setFocusable(true);
		setSize((int) (window.getWidth()/2.5),window.getHeight()-40);
		setBackground(Color.BLACK);
		setLocation(w.getWidth()/3,10);
		setBorder(BorderFactory.createEtchedBorder(1, Color.LIGHT_GRAY, Color.DARK_GRAY));
		//setSize(400,600);
		setVisible(true);
		
	}
	
	/**
	 * Adds an instance of the GameKeyListener class
	 */
	public void startKeyListening(){
		gameKeys = new GameKeyListener();
		addKeyListener(gameKeys);
	}
	
	/**
	 * Removes the KeyListener
	 */
	public void stopKeyListening(){
		removeKeyListener(gameKeys);
	}
	
	/**
	 * Private inner class to listen for keyboard input and
	 * manipulate the game accordingly.
	 * 
	 * Listens for keys related to pausing the game, keys related to 
	 * manipulating the game pieces and keys related to adjusting the parent windows
	 * media player.
	 * @author Mitch Powell
	 *
	 */
	private class GameKeyListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()){
				case KeyEvent.VK_P:
					window.pauseGame();
					break;
				case KeyEvent.VK_SPACE:
					System.out.println("Tetromino Hard Dropped");
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("Tetromino Moved Left");
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("Tetromino Moved Right");
					break;
				case KeyEvent.VK_UP:
					System.out.println("Tetromino Rotated");
					break;
				case KeyEvent.VK_DOWN:
					System.out.println("Tetromino Soft Dropped");
					break;
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
