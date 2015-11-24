import java.awt.Color;
import java.awt.Point;

/**
 * The Long, thin Tetromino
 * @author Mitch Powell
 *
 */
public class I_Piece extends Tetromino {
	
	private int[] rotation_1;
	private int[] rotation_2;

	/**
	 * Constructor for the I_Piece class
	 * @param s the screen the piece is being displayed on
	 * @param p the point that the blocks will refer to for their coordinates.
	 */
	public I_Piece(PlayScreen s, Point p) {
		super(s, p);
		this.color = Color.RED;
		rotation_1 = new int[]{2,6,10,14};
		rotation_2 = new int[]{12,13,14,15};
	}

	@Override
	/**
	 * Puts the blocks in their initial positions
	 */
	public void addBlocks() {
		blocks[0] = new Block(this, 2);
		blocks[1] = new Block(this, 6);
		blocks[2] = new Block(this, 10);
		blocks[3] = new Block(this, 14);
		rotation = 1;
	}

	@Override
	/**
	 * Rotates the piece into its next rotation
	 */
	public void rotate() {
		if(rotation == 1){
			for(int i=0; i<4; i++){
				blocks[i] = new Block(this,rotation_2[i]);
			}
			rotation = 2;
		} else if(rotation == 2){
			for(int i=0; i< 4; i++){
				blocks[i] = new Block(this,rotation_1[i]);
			}
			rotation = 1;
		}

	}

}

