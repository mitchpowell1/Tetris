import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Superclass of the HoldBlockPanel and the NextBlockPanel, extension of the
 * JPanel class. All BlockDisplay panel instances must be able to display a
 * tetromino
 * 
 * @author Mitch Powell
 *
 */
public class BlockDisplayPanel extends JPanel {

	private Tetromino displayPiece;
	
	/**
	 * Instructions for displaying a particular Tetromino
	 * 
	 * @param w
	 *            the parent GameWindow containing the instance
	 */
	public void displayBlock(GameWindow w) {
		System.out.println("Block");
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(displayPiece != null){
			for(Block block : displayPiece.getBlocks()){
				g2.setColor(displayPiece.getColor());
				g2.fill(block);
				g2.setColor(Color.BLACK);
				g2.draw(block);
			}	
		}
	}

	public void setBlock(Tetromino piece) {
		this.displayPiece = piece;
		displayPiece.setLocation(0,0);
	}
}
