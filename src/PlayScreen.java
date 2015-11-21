import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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
	private ArrayList<Tetromino> usedTetrominos;
	private int width = 360;
	private int height = 2*width;
	private int dropRate;
	private Tetromino activePiece;
	private Tetromino nextPiece;
	private Tetromino holdPiece;
	/**
	 * Constructor for the PlayScreen class
	 * @param w the parent GameWindow
	 */
	public PlayScreen(GameWindow w){
		this.window = w;
		this.setFocusable(true);
		this.dropRate = 1;
		tetrominos = new ArrayList<Tetromino>();
		usedTetrominos = new ArrayList<Tetromino>();
		setSize(width,height);
		setBackground(Color.BLACK);
		setLocation(475,5);
		setBorder(BorderFactory.createEtchedBorder(1, Color.LIGHT_GRAY, Color.DARK_GRAY));
		addNewPiece();
		
	}
	
	/**
	 * Adds an instance of the GameKeyListener class
	 */
	public void startKeyListening(){
		gameKeys = new GameKeyListener();
		addKeyListener(gameKeys);
	}
	
	public int getDropRate(){
		return this.dropRate;
	}
	
	public ArrayList<Tetromino> getUsedTetrominos(){
		return this.usedTetrominos;
	}
	
	public void addNewPiece(){
		activePiece = new I_Piece(this, new Point(getWidth()/10, 50));
		tetrominos.add(activePiece);
	}
	
	/**
	 * Removes the KeyListener
	 */
	public void stopKeyListening(){
		removeKeyListener(gameKeys);
		
	}
	
	public void lockPiece(){
		usedTetrominos.add(activePiece);
		addNewPiece();
	}
	
	public Tetromino getActivePiece(){
		return this.activePiece;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(Tetromino piece: tetrominos){
			for(Block block : piece.getBlocks()){
				g2.setColor(piece.getColor());
				g2.fill(block);
				g2.setColor(Color.DARK_GRAY);
				g2.draw(block);
			}
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
					activePiece.setLocation(activePiece.getX(),height-4*activePiece.getBlockSideLength());
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("Tetromino Moved Left");
					activePiece.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("Tetromino Moved Right");
					activePiece.moveRight();
					break;
				case KeyEvent.VK_UP:
					System.out.println("Tetromino Rotated");
					activePiece.rotate();
					break;
				case KeyEvent.VK_DOWN:
					System.out.println("Tetromino Soft Dropped");
					activePiece.setLocation(activePiece.getX(),height - (4*activePiece.getBlockSideLength()));
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
