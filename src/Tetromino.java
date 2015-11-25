import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javax.swing.JComponent;

/**
 * 
 * @author Mitch Powell
 * 
 * Abstract Tetromino class is a parent class for all of the game pieces
 *
 */

public abstract class Tetromino extends JComponent implements Cloneable{
	
	private PlayScreen screen;
	protected Block[] blocks = new Block[4];
	protected Color color;
	protected int rotation;
	private int blockSideLength;
	

	/**
	 * Constructor for the Tetromino class
	 * @param s the playscreen the piece will be drawn on
	 * @param p The point that the upper left corner of the pieces 4x4 block grid will be located
	 * 
	 */

	public Tetromino(PlayScreen s, Point p){
		this.screen = s;
		setLocation(p);
		this.blockSideLength = (s.getWidth()/10);
		addBlocks();
	}
	
	/**
	 * Abstract method for initializing the blocks in the tetromino
	 */
	public abstract void addBlocks();

	/**
	 * Abstract method for rotating the tetromino
	 */
	public abstract void rotate();
	
	/**
	 * realigns the blocks with the reference point
	 */
	public void setPieces(){
		for(int i=0; i<4; i++){
			if(blocks[i] != null){
				int pos = blocks[i].getPosition();
				blocks[i] = new Block(this, pos);
			}
		}
	}
	
	/**
	 * Standard clone method, implemented to avoid pointer errors when copying the
	 * activePiece in the PlayScreen to the holdPiece and vice versa.
	 */
	public Object clone() {
		Tetromino newPiece = null;
		try {
	        newPiece = (Tetromino)super.clone();
	    }
	    	catch (CloneNotSupportedException e) {
	    }
		return newPiece;
	}

	
	/**
	 * Drops the Tetromino all the way to the bottom of the screen...
	 * Doesn't work quite the way that it should
	 */
	public void hardDrop(){
		boolean downShiftable = true;
		for(Block block : blocks){
			if((block.getPosition() == 12) || (block.getPosition() == 13)
					|| (block.getPosition() == 14) || (block.getPosition() == 15)){
				downShiftable = false;
				break;
			}
		}
		setLocation(getX(),screen.getHeight() - (4*blockSideLength));
		if(downShiftable){
			for(int i=0; i<4; i++){
				int pos = blocks[i].getPosition();
				blocks[i] = new Block(this, pos+4);
			}
		} else {
			setPieces();
		}
		screen.lockPiece();
	}

	/**
	 * Provides the logic for making the pieces fall
	 */
	public void drop(int dropDistance, boolean repaint){
		outerLoop:
		for(int i=0; i<dropDistance; i++){
			setLocation(getX(),getY()+1);
			if(getY() == screen.getHeight()-4*blockSideLength){
				screen.lockPiece();
				break;
			}
			setPieces();
			for(Block block : blocks){
				for(Tetromino piece : screen.getUsedTetrominos()){
					for(int j=0; j<4; j++){
						
						if(piece.getBlocks()[j]!= null &&
								block.intersects(piece.getBlocks()[j])){
							setLocation(getX(),getY()-1);
							setPieces();
							screen.lockPiece();
							break outerLoop; //sorry :*(
						}
					}
				}
			}
			if(repaint = true){
				screen.repaint();
			}
		}
	}
	
	

	
	/**
	 * Provides Logic to move a tetromino one square to the left
	 */
	public void moveRight(){
		boolean blocksShiftable = true;
		for(Block block : blocks){
			if((block.getPosition() == 3) || (block.getPosition() == 7) ||
					(block.getPosition() == 11) || (block.getPosition() == 15)){
				blocksShiftable = false;
				break;
			}
		}
		if(blocksShiftable){
			for(int i=0; i<4; i++){
				int pos = blocks[i].getPosition();
				blocks[i] = new Block(this, pos+1);
			}
		} else {
			if(getX() < screen.getWidth() - 4*blockSideLength){
				setLocation(getX()+blockSideLength,getY());
				for(int i=0; i<4; i++){
					int pos = blocks[i].getPosition();
					blocks[i] = new Block(this, pos);
				}
			}
		}
	}
	
	/**
	 * provides logic to move a tetromino one square to the right
	 */
	public void moveLeft(){
		boolean blocksShiftable = true;
		for(Block block : blocks){
			if((block.getPosition() == 0) || (block.getPosition() == 4) ||
					(block.getPosition() == 8) || (block.getPosition() == 12)){
				blocksShiftable = false;
				break;
			}
		}
		if(blocksShiftable){
			for(int i=0; i<4; i++){
				int pos = blocks[i].getPosition();
				blocks[i] = new Block(this, pos-1);
			}
		} else {
			if(getX() > 0){
				setLocation(getX()-blockSideLength,getY());
				for(int i=0; i<4; i++){
					int pos = blocks[i].getPosition();
					blocks[i] = new Block(this, pos);
				}
			}
		}
	}
	
	/**
	 * Returns the length of the sides of one of the blocks in the Tetromino
	 * @return The Block side length
	 */
	public int getBlockSideLength(){
		return this.blockSideLength;
	}
	

	/**
	 * Returns the color of the Tetromino
	 * @return the color of the Tetromino/
	 */

	public Color getColor(){
		return this.color;
	}
	
	/**
	 * Returns the array of Blocks
	 * @return the array of Blocks
	 */
	public Block[] getBlocks(){
		return blocks;
	}
}
