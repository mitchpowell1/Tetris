import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The PlayScreenClass is an extension of the JPanel class which displays the
 * Tetrominos and is the focal point of the GameWindow class.
 * 
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
	private Random pieceGenerator;
	private Tetromino activePiece;
	private Tetromino nextPiece;
	private Tetromino holdPiece;
	private Point spawnPoint;
	/**
	 * Constructor for the PlayScreen class
	 * @param w the parent GameWindow
	 */
	public PlayScreen(GameWindow w){
		this.window = w;
		this.setFocusable(true);
		this.dropRate = 10;
		spawnPoint = new Point(3*(WIDTH/10),-150);
		pieceGenerator = new Random();
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
		requestFocus();
		addKeyListener(gameKeys);
	}
	
	/**
	 * Returns the current piece drop rate
	 * @return the current piece drop rate
	 */
	public int getDropRate(){
		return this.dropRate;
	}
	
	
	/**
	 * Returns the List of used Tetrominos (All of the Tetrominos that require drawing
	 * less the active piece.)
	 * @return Used Tetromino list
	 */
	public ArrayList<Tetromino> getUsedTetrominos(){
		return this.usedTetrominos;
	}
		
	/**
	 * Creates a random type of Tetromino.
	 * @return a random Tetromino subclass. (I_Piece, J_Piece, L_Piece, O_Piece, S_Piece,
	 * 		Z_Piece, T_Piece)
	 */

	public Tetromino randomPiece(){
		int pieceChoice = pieceGenerator.nextInt(7);
		Tetromino newPiece = null;
		switch(pieceChoice){
			case 0: 
				newPiece = new Z_Piece(this, spawnPoint);
				break;
			case 1:
				newPiece = new I_Piece(this, spawnPoint);
				break;
			case 2:
				newPiece = new J_Piece(this, spawnPoint);
				break;
			case 3: 
				newPiece = new L_Piece(this, spawnPoint);
				break;
			case 4:
				newPiece = new T_Piece(this, spawnPoint);
				break;
			case 5:
				newPiece = new S_Piece(this, spawnPoint);
				break;
			case 6:
				newPiece = new O_Piece(this, spawnPoint);
				break;
		}
		return newPiece;
	}
	

	/**
	 * Creates the very first piece, adds the next piece to the next Piece panel.
	 */
	public void addNewPiece(){
		activePiece = randomPiece();
		activePiece.setPieces();
		tetrominos.add(activePiece);
		nextPiece = randomPiece();
		nextPiece.setLocation(0,0);
		nextPiece.setPieces();
		window.getNextPanel().setBlock(nextPiece);
		window.getNextPanel().repaint();
	}
	

	/**
	 * Pulls the nextPiece from the NextPiecePanel, sets it as the active piece and
	 * generates the next piece, which is then displayed in the panel.
	 */

	public void useNextPiece(){
		tetrominos.add(nextPiece);
		activePiece = tetrominos.get(tetrominos.size() -1);
		activePiece.setLocation(spawnPoint);
		activePiece.setPieces();
		nextPiece = randomPiece();
		nextPiece.setLocation(0,0);
		nextPiece.setPieces();
		window.getNextPanel().setBlock(nextPiece);
		window.getNextPanel().repaint();
	}
	
	/**
	 * Removes the KeyListener
	 */
	public void stopKeyListening(){
		removeKeyListener(gameKeys);
		
	}
	

	/**
	 * Locks the Active Piece on the screen, This is where the method call to check if
	 * any rows have been completed should be placed.
	 */

	public void lockPiece(){
		System.out.println("Locked");
		usedTetrominos.add(activePiece);
		for(Tetromino piece : usedTetrominos){
			for(Block block : piece.getBlocks()){
				if(block.getY() <= 0){
					window.getTimer().stop();
					window.getCountDownLabel().setText("You get Nothing! You Lose!");
					window.getCountDownLabel().setVisible(true);
				}
			}
		}
		if(nextPiece == null){
			addNewPiece();
		} else {
			useNextPiece();
		}
	}
	

	/**
	 * Pulls the piece from the hold panel, places the current piece in the hold panel.
	 */

	public void useHoldPiece(){
		if(holdPiece == null){
			holdPiece = ((Tetromino)activePiece.clone());
			tetrominos.remove(activePiece);
			holdPiece.setLocation(0,0);
			holdPiece.setPieces();
			window.getHoldPanel().setBlock(holdPiece);
			window.getHoldPanel().repaint();
			useNextPiece();
		} else {
			activePiece.setLocation(0,0);
			Tetromino temp = (Tetromino)activePiece.clone();
			tetrominos.remove(activePiece);
			activePiece = (Tetromino) holdPiece.clone();
			tetrominos.add(activePiece);
			holdPiece = temp;
			holdPiece.setPieces();
			window.getHoldPanel().setBlock(holdPiece);
			window.getHoldPanel().repaint();
			
		}
	}
	

	/**
	 * Returns the active piece.
	 * @return
	 */

	public Tetromino getActivePiece(){
		return this.activePiece;
	}
	

	/**
	 * Iterates through and draws every Tetromino in the UsedTetrominos list and
	 * the Tetrominos list.
	 */

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
				case KeyEvent.VK_H:
					useHoldPiece();
					break;
				case KeyEvent.VK_SPACE:
					System.out.println("Tetromino Hard Dropped");
					activePiece.hardDrop();
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("Tetromino Moved Left");
					activePiece.moveLeft();
					for(Block block : activePiece.getBlocks()){
						for(Tetromino piece : usedTetrominos){
							for(Block block2 : piece.getBlocks()){
								if(block.intersects(block2)){
									activePiece.moveRight();
								}
							}
						}
					}
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("Tetromino Moved Right");
					activePiece.moveRight();
					for(Block block : activePiece.getBlocks()){
						for(Tetromino piece : usedTetrominos){
							for(Block block2 : piece.getBlocks()){
								if(block.intersects(block2)){
									activePiece.moveLeft();
								}
							}
						}
					}
					break;
				case KeyEvent.VK_UP:
					System.out.println("Tetromino Rotated");
					activePiece.rotate();
					break;
				case KeyEvent.VK_DOWN:
					System.out.println("Tetromino Soft Dropped");
					activePiece.drop(50);
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
