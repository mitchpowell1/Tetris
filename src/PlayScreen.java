import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class PlayScreen extends JPanel{
	private GameWindow window;
	private GameKeyListener gameKeys;
	
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
				
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void startKeyListening(){
		gameKeys = new GameKeyListener();
		addKeyListener(gameKeys);
	}
	
	public void stopKeyListening(){
		removeKeyListener(gameKeys);
	}

}
