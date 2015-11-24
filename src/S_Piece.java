import java.awt.Color;
import java.awt.Point;

/**
 * The S-shaped Tetromino piece
 * @author Mitch Powell
 *
 */
public class S_Piece extends Tetromino {

	private int[] rotation_1;
	private int[] rotation_2;
	
	/**
	 * Constructor for the S_Piece object
	 * @param s the screen the piece will be drawn on
	 * @param p the point the blocks will be drawn in reference to.
	 */
	public S_Piece(PlayScreen s, Point p){
		super(s,p);
		this.color = Color.BLUE;
		rotation_1 = new int[]{13,14,10,11};
		rotation_2 = new int[]{5,9,10,14};
		
	}
	
	/**
	 * Initializes the blocks
	 */
	@Override
	public void addBlocks() {
		blocks[0] = new Block(this, 13);
		blocks[1] = new Block(this, 14);
		blocks[2] = new Block(this, 10);
		blocks[3] = new Block(this, 11);
		rotation = 1;
	}

	/**
	 * Rotates the piece.
	 */
	@Override
	public void rotate() {
		if(rotation == 1){
			for(int i=0; i<4; i++){
				blocks[i] = new Block(this,rotation_2[i]);
				rotation = 2;
			}
		} else {
			for(int i=0; i<4; i++){
				blocks[i] = new Block(this,rotation_1[i]);
				rotation =1;
			}
		}

	}

}
