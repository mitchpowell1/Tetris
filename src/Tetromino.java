import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javax.swing.JComponent;


public abstract class Tetromino extends JComponent{
	
	private PlayScreen screen;
	protected Block[] blocks = new Block[4];
	protected Color color;
	protected int rotation;
	private int blockSideLength;
	
	public Tetromino(PlayScreen s, Point p){
		this.screen = s;
		setLocation(p);
		this.blockSideLength = (s.getWidth()/10);
		addBlocks();
	}
	
	public abstract void addBlocks();
	
	public abstract void rotate();
	
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
			}
			for(Block block : blocks){
				block.y += screen.getDropRate();
			}
		} else {
			screen.lockPiece();
		}
	}
	
	public void moveRight(){
		boolean moveable = true;
		for(Block block : blocks){
			if(block.getX() >= (screen.getWidth()-blockSideLength)){
				moveable = false;
			}
			if(screen.getUsedTetrominos().size() != 0){
				for(Tetromino piece : screen.getUsedTetrominos()){
					for(Block block2 : piece.getBlocks()){
						if(block.intersects(block2)){
							moveable = false;
						}
					}
				}
			}
		}
		if(moveable){
			if(getX() < screen.getWidth() - (4*blockSideLength)){
				this.setLocation(getX()+blockSideLength,getY());
			}
			for(Block block : blocks){
				block.setLocation(block.x+blockSideLength, block.y);
			}
		}
	}
	
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
			}
		}
	}
	
	public int getBlockSideLength(){
		return this.blockSideLength;
	}
	
	public Color getColor(){
		return this.color;
	}
	
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
}
