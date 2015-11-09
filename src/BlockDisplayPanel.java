import javax.swing.JPanel;

/**
 * Superclass of the HoldBlockPanel and the NextBlockPanel,
 * extension of the JPanel class. All BlockDisplay panel instances
 * must be able to display a tetromino
 * @author Mitch Powell
 *
 */
public class BlockDisplayPanel extends JPanel {

	/**
	 * Instructions for displaying a particular Tetromino
	 * @param w the parent GameWindow containing the instance
	 */
	public void displayBlock(GameWindow w){
		System.out.println("Block");
	}
	
	public void setBlock(){
		
	}
}
