import java.awt.Color;
import java.awt.Point;


public class O_Piece extends Tetromino {

	public O_Piece(PlayScreen s, Point p){
		super(s,p);
		this.color = Color.CYAN;
	}
	@Override
	public void addBlocks() {
		blocks[0] = new Block(this, 5);
		blocks[1] = new Block(this, 6);
		blocks[2] = new Block(this, 10);
		blocks[3] = new Block(this, 9);

	}

	@Override
	public void rotate() {
		//There are no alternate orientations for the o_piece, so this method is left blank

	}

}
