import javax.swing.JFrame;


public class GameWindow extends JFrame{
	
	public GameWindow(){
		setTitle("Tetris 2: Son of Tetris");
		this.setSize(1000, 750);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new GameWindow();

	}

}
