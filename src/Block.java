import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Block extends Rectangle{
	
	private int position;
	
	public Block(Tetromino parentPiece, int position){
		this.position = position;
		this.width = parentPiece.getBlockSideLength();
		this.height = parentPiece.getBlockSideLength();
		if(position < 4){
			this.y = parentPiece.getY();
			this.x = parentPiece.getX()+((position%4)*width);
		} else if(position < 8){
			this.y = parentPiece.getY()+(width);
			this.x = parentPiece.getX()+((position%4)*width);
		} else if(position < 12){
			this.y = parentPiece.getY()+(2*width);
			this.x = parentPiece.getX()+((position%4)*width);
		} else {
			this.y = parentPiece.getY()+(3*width);
			this.x = parentPiece.getX()+((position%4)*width);
		}
	}
	
	public int getPosition(){
		return this.position;
	}
}
