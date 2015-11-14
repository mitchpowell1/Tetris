import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
	private ArrayList<Tetromino> tetrominos;
	private int width = 360;
	private int height = 2*width;
	private int dropRate;
	
	/**
	 * Constructor for the PlayScreen class
	 * @param w the parent GameWindow
	 */
	public PlayScreen(GameWindow w){
		this.window = w;
		this.setFocusable(true);
		this.dropRate = 0;
		setSize(width,height);
		setBackground(Color.BLACK);
		setLocation(475,5);
		setBorder(BorderFactory.createEtchedBorder(1, Color.LIGHT_GRAY, Color.DARK_GRAY));
		//tetrominos.add(new Tetromino(this, Color.RED));
		//add(tetrominos.get(0));
		//setSize(400,600);
		//setVisible(true);
		
	}
	
	/**
	 * Adds an instance of the GameKeyListener class
	 */
	public void startKeyListening(){
		gameKeys = new GameKeyListener();
		addKeyListener(gameKeys);
	}
	
	public void incrementDrop (int level){
		this.dropRate += level;
	}
	
	/**
	 * Removes the KeyListener
	 */
	public void stopKeyListening(){
		removeKeyListener(gameKeys);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Color strokeCol = Color.DARK_GRAY;
		if((getWidth()/10+dropRate-100+(getWidth()/10) < height)){
			//g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.RED);
			g2.fillRect(getWidth()/10, -100+dropRate, getWidth()/10, getWidth()/10);
			g2.setColor(strokeCol);
			g2.drawRect(getWidth()/10, -100+dropRate, getWidth()/10, getWidth()/10);
			
			g2.setColor(Color.RED);
			g2.fillRect(getWidth()/5, -100+dropRate, getWidth()/10, getWidth()/10);
			g2.setColor(strokeCol);
			g2.drawRect(getWidth()/5, -100+dropRate, getWidth()/10, getWidth()/10);
			
			g2.setColor(Color.RED);
			g2.fillRect(getWidth()/5, getWidth()/10+dropRate-100, getWidth()/10, getWidth()/10);
			g2.setColor(strokeCol);
			g2.drawRect(getWidth()/5, getWidth()/10+dropRate-100, getWidth()/10, getWidth()/10);
			
			g2.setColor(Color.RED);
			g2.fillRect(3*getWidth()/10, getWidth()/10+dropRate-100, getWidth()/10, getWidth()/10);
			g2.setColor(strokeCol);
			g2.drawRect(3*getWidth()/10, getWidth()/10+dropRate-100, getWidth()/10, getWidth()/10);
		} else {
			//g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.RED);
			g2.fillRect(getWidth()/10, height-getWidth()/5, getWidth()/10, getWidth()/10);
			g2.setColor(strokeCol);
			g2.drawRect(getWidth()/10, height-getWidth()/5, getWidth()/10, getWidth()/10);
			
			g2.setColor(Color.RED);
			g2.fillRect(getWidth()/5, height-getWidth()/5, getWidth()/10, getWidth()/10);
			g2.setColor(strokeCol);
			g2.drawRect(getWidth()/5, height-getWidth()/5, getWidth()/10, getWidth()/10);
			
			g2.setColor(Color.RED);
			g2.fillRect(getWidth()/5, height-getWidth()/10, getWidth()/10, getWidth()/10);
			g2.setColor(strokeCol);
			g2.drawRect(getWidth()/5, height-getWidth()/10, getWidth()/10, getWidth()/10);
			
			g2.setColor(Color.RED);
			g2.fillRect(3*getWidth()/10, height-getWidth()/10, getWidth()/10, getWidth()/10);
			g2.setColor(strokeCol);
			g2.drawRect(3*getWidth()/10, height-getWidth()/10, getWidth()/10, getWidth()/10);
		}

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
					dropRate = height+(width/10);
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
					dropRate = 30;
					break;
				case KeyEvent.VK_M:
					window.getPlayer().setMute(!window.getPlayer().isMute());
					window.getPauseMenu().getMuteBox().setSelected(window.getPlayer().isMute());
					break;
				case KeyEvent.VK_MINUS:
					//These statements are here because the MediaPlayer class only has meaningful actions for volume
					//on a range from 0 to 1, but the setter method for the volume will still allow you to set a higher number
					if(window.getPlayer().getVolume() <= .1){
						window.getPlayer().setVolume(0);
					} else{
						window.getPlayer().setVolume(window.getPlayer().getVolume()-.1);
					}
					window.getPauseMenu().getVolumeSlider().setValue((int) Math.round(window.getPlayer().getVolume()*10));
					System.out.println(window.getPlayer().getVolume());
					break;
				case KeyEvent.VK_EQUALS:
					if(window.getPlayer().getVolume() >= .9){
						window.getPlayer().setVolume(1);
					} else {
						window.getPlayer().setVolume(window.getPlayer().getVolume()+.1);
					}
					window.getPauseMenu().getVolumeSlider().setValue((int) Math.round(window.getPlayer().getVolume()*10));
					System.out.println(window.getPlayer().getVolume());
					break;
			}
			
		}
		


		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		
	}
	

}
