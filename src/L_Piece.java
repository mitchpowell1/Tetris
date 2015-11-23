import java.awt.Color;
import java.awt.Point;


public class L_Piece extends Tetromino {

	private int[] rotation_1;
	private int[] rotation_2;
	private int[] rotation_3;
	private int[] rotation_4;
	
	public L_Piece(PlayScreen s, Point p){
		super(s,p);
		this.color = Color.YELLOW;
		rotation_1 = new int[]{9,10,11,7};
		rotation_2 = new int[]{5,9,13,14};
		rotation_3 = new int[]{9,5,6,7};
		rotation_4 = new int[]{14,10,6,5};
		
	}
	@Override
	public void addBlocks() {
		blocks[0] = new Block(this, 13);
		blocks[1] = new Block(this, 14);
		blocks[2] = new Block(this, 15);
		blocks[3] = new Block(this, 11);
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
		// TODO Auto-generated method stub

	}

<<<<<<< HEAD
=======
	@Override
	public void setPieces() {
		// TODO Auto-generated method stub

	}
>>>>>>> 06a9402c9ec87ab33a5a2f7758756f880a323397

}
