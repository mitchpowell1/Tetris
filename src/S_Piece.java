import java.awt.Color;
import java.awt.Point;


public class S_Piece extends Tetromino {

	private int[] rotation_1;
	private int[] rotation_2;
	
	public S_Piece(PlayScreen s, Point p){
		super(s,p);
		this.color = Color.BLUE;
		rotation_1 = new int[]{9,10,6,7};
		rotation_2 = new int[]{5,9,10,14};
		
	}
	
	@Override
	public void addBlocks() {
		blocks[0] = new Block(this, 9);
		blocks[1] = new Block(this, 10);
		blocks[2] = new Block(this, 6);
		blocks[3] = new Block(this, 7);
		rotation = 1;
	}

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

<<<<<<< HEAD
=======
	@Override
	public void setPieces() {
		// TODO Auto-generated method stub

	}
>>>>>>> 06a9402c9ec87ab33a5a2f7758756f880a323397

}
