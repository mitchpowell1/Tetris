import java.awt.Color;
import java.awt.Point;

/**
 * The Z-Shaped Tetromino piece
 * @author Mitch Powell
 *
 */
public class Z_Piece extends Tetromino {

	private int[] rotation_1;
	private int[] rotation_2;
	
	/**
	 * Constructor for the Z_Piece
	 * @param s the screen that the piece should be drawn on
	 * @param p the point that the blocks are drawn in reference to.
	 */
	public Z_Piece(PlayScreen s, Point p){
		super(s,p);
		this.color = Color.GREEN;
		rotation_1 = new int[]{5,6,10,11};
		rotation_2 = new int[]{3,7,6,10};
	}
	
	@Override
	/**
	 * Initializes the blocks to their starting orientation
	 */
	public void addBlocks() {
		blocks[0] = new Block(this, 5);
		blocks[1] = new Block(this, 6);
		blocks[2] = new Block(this, 10);
		blocks[3] = new Block(this, 11);
		rotation = 1;

	}

	@Override
	/**
	 * rotates the piece
	 */
	public void rotate() {
		if(rotation == 1){
			for(int i=0; i<4; i++){
				blocks[i] = new Block(this, rotation_2[i]);
			}
			rotation = 2;
		} else {
			for(int i=0; i<4; i++){
				blocks[i] = new Block(this, rotation_1[i]);
			}
			rotation = 1;
		}

	}
}
