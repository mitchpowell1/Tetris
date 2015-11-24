import java.awt.Color;
import java.awt.Point;

/**
 * The J-shaped Tetromino piece
 * @author Mitch Powell
 *
 */
public class J_Piece extends Tetromino {

	private int[] rotation_1;
	private int[] rotation_2;
	private int[] rotation_3;
	private int[] rotation_4;
	
	/**
	 * Constructor for the J_Piece
	 * @param s the screen the J_Piece is drawn on
	 * @param p the point that the blocks are constructed in reference to.
	 */
	public J_Piece(PlayScreen s, Point p){
		super(s,p);
		this.color = Color.PINK;
		rotation_1 = new int[]{9,13,14,15};
		rotation_2 = new int[]{6,7,10,14};
		rotation_3 = new int[]{15,11,10,9};
		rotation_4 = new int[]{13,14,10,6};
		
	}
	
	@Override
	/**
	 * Initializes the blocks in the tetromino
	 */
	public void addBlocks() {
		blocks[0] = new Block(this, 9);
		blocks[1] = new Block(this, 13);
		blocks[2] = new Block(this, 14);
		blocks[3] = new Block(this, 15);
		rotation = 1;
	}

	
	/**
	 * Rotates the piece
	 */
	@Override
	public void rotate() {
		if(rotation == 1){
			for(int i=0; i<4; i++){
				blocks[i] = new Block(this,rotation_2[i]);
			}
			rotation = 2;
		} else if(rotation == 2){
			for(int i=0; i< 4; i++){
				blocks[i] = new Block(this,rotation_3[i]);
			}
			rotation = 3;
		} else if(rotation == 3){
			for(int i=0; i< 4; i++){
				blocks[i] = new Block(this,rotation_4[i]);
				rotation = 4;
			}
		} else {
			for(int i=0; i<4; i++){
				blocks[i] = new Block(this,rotation_1[i]);
				rotation = 1;
			}
		}

	}
}
