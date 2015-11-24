import java.awt.Color;
import java.awt.Point;

/**
 * The O/Square-shaped Tetromino piece
 * @author Mitch Powell
 *
 */
public class O_Piece extends Tetromino {

	/**
	 * Constructor for the O_Piece
	 * @param s the screen that the piece is drawn on
	 * @param p the point that the blocks are drawn in reference to.
	 */
	public O_Piece(PlayScreen s, Point p){
		super(s,p);
		this.color = Color.CYAN;
	}
	
	@Override
	/**
	 * Initializes the blocks.
	 */
	public void addBlocks() {
		blocks[0] = new Block(this, 9);
		blocks[1] = new Block(this, 10);
		blocks[2] = new Block(this, 13);
		blocks[3] = new Block(this, 14);

	}

	/**
	 * Rotates the piece (All rotations of the O_Piece are identical, so this method
	 * does nothing.
	 */
	@Override
	public void rotate() {
		//There are no alternate orientations for the o_piece, so this method is left blank

	}
}
