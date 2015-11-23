import java.awt.Color;
import java.awt.Point;


public class I_Piece extends Tetromino {
	
	private int[] rotation_1;
	private int[] rotation_2;

	public I_Piece(PlayScreen s, Point p) {
		super(s, p);
		this.color = Color.RED;
		rotation_1 = new int[]{2,6,10,14};
		rotation_2 = new int[]{8,9,10,11};
	}

	@Override
	//Places the blocks of the tetromino in their initial positions.
	public void addBlocks() {
		blocks[0] = new Block(this, 2);
		blocks[1] = new Block(this, 6);
		blocks[2] = new Block(this, 10);
		blocks[3] = new Block(this, 14);
		rotation = 1;
	}

	@Override
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