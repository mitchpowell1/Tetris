import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javax.swing.JComponent;


<<<<<<< HEAD
/**
 * 
 * @author Mitch Powell
 * 
 * Abstract Tetromino class is a parent class for all of the game pieces
 *
 */

=======
>>>>>>> 06a9402c9ec87ab33a5a2f7758756f880a323397
public abstract class Tetromino extends JComponent implements Cloneable{
	
	private PlayScreen screen;
	protected Block[] blocks = new Block[4];
	protected Color color;
	protected int rotation;
	private int blockSideLength;
	
<<<<<<< HEAD
	/**
	 * Constructor for the Tetromino class
	 * @param s the playscreen the piece will be drawn on
	 * @param p The point that the upper left corner of the pieces 4x4 block grid will be located
	 * 
	 */
=======
>>>>>>> 06a9402c9ec87ab33a5a2f7758756f880a323397
	public Tetromino(PlayScreen s, Point p){
		this.screen = s;
		setLocation(p);
		this.blockSideLength = (s.getWidth()/10);
		addBlocks();
	}
	
	public abstract void addBlocks();
	
	public abstract void rotate();
	
<<<<<<< HEAD
	public void setPieces(){
		for(int i=0; i<4; i++){
			int pos = blocks[i].getPosition();
			System.out.println(pos);
			blocks[i] = new Block(this, pos);
		}
	}
	
	//public abstract void setPieces();
=======
	public abstract void setPieces();
>>>>>>> 06a9402c9ec87ab33a5a2f7758756f880a323397
	
	public Object clone() {
		Tetromino newPiece = null;
		try {
	        newPiece = (Tetromino)super.clone();
	    }
	    	catch (CloneNotSupportedException e) {
	    }
		return newPiece;
	}

	    //getters and setters for the fields should go here........
	
<<<<<<< HEAD
	
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
	public void drop(){
		int floor = screen.getHeight() - screen.getDropRate();
		boolean droppable = true;
		
		for(Block block : blocks){
			if(block.getY() > screen.getHeight()-blockSideLength){
				droppable = false;
				break;
			}
		}
		if(droppable){
			this.setLocation(getX(),getY()+screen.getDropRate());
			if(getLocation().getY() >= screen.getHeight() - (4*blockSideLength)){
				setLocation(getX(),(screen.getHeight() - (4*blockSideLength)));
=======
	public void drop(){
		boolean droppable = true;

		for(Block block : blocks){
			if(block.getY() > screen.getHeight()-blockSideLength){
				droppable = false;
			}
		}
		if(droppable){
			this.setLocation(this.getLocation().x,this.getLocation().y+screen.getDropRate());
			if(getLocation().getY() >= screen.getHeight() - (4*blockSideLength)){
				setLocation((int)getLocation().getX(), screen.getHeight()-(4*blockSideLength));
>>>>>>> 06a9402c9ec87ab33a5a2f7758756f880a323397
				setPieces();
			}
			for(Block block : blocks){
				block.y += screen.getDropRate();
			}
		} else {
<<<<<<< HEAD
			
=======
>>>>>>> 06a9402c9ec87ab33a5a2f7758756f880a323397
			screen.lockPiece();
		}
	}
	
<<<<<<< HEAD
	
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
=======
	public void moveRight(){
		boolean moveable = true;
		for(Block block : blocks){
			if(block.getX() >= (screen.getWidth()-blockSideLength)){
				moveable = false;
			}
		}
		if(moveable){
			if(getX() < screen.getWidth() - (4*blockSideLength)){
				this.setLocation(getX()+blockSideLength,getY());
			}
			for(Block block : blocks){
				block.setLocation(block.x+blockSideLength, block.y);
>>>>>>> 06a9402c9ec87ab33a5a2f7758756f880a323397
			}
		}
	}
	
<<<<<<< HEAD
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
=======
	public void moveLeft(){
		boolean moveable = true;
		for(Block block : blocks){
			if(block.getX() <= 0){
				moveable = false;
			}
		}
		if (moveable){
			if(getX() > 0){
				setLocation(getX()-blockSideLength,getY());
			}
			for(Block block : blocks){
				block.setLocation(block.x-blockSideLength, block.y);
>>>>>>> 06a9402c9ec87ab33a5a2f7758756f880a323397
			}
		}
	}
	
<<<<<<< HEAD
	
	/**
	 * Returns the length of the sides of one of the blocks in the Tetromino
	 * @return The Block side length
	 */
=======
>>>>>>> 06a9402c9ec87ab33a5a2f7758756f880a323397
	public int getBlockSideLength(){
		return this.blockSideLength;
	}
	
<<<<<<< HEAD
	/**
	 * Returns the color of the Tetromino
	 * @return the color of the Tetromino/
	 */
=======
>>>>>>> 06a9402c9ec87ab33a5a2f7758756f880a323397
	public Color getColor(){
		return this.color;
	}
	
<<<<<<< HEAD
	/**
	 * Returns the array of Blocks
	 * @return the array of Blocks
	 */
	public Block[] getBlocks(){
		return blocks;
	}
=======
	public Block[] getBlocks(){
		return blocks;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.draw(blocks[0]);;
	}
>>>>>>> 06a9402c9ec87ab33a5a2f7758756f880a323397
}
