import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GameWindow extends JFrame{
	
	private ImageIcon backgroundIMG;
	private JLabel background;
	public GameWindow(){
		
		backgroundIMG = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Tetris_Background.jpg"));
		background = new JLabel();
		background.setIcon(backgroundIMG);
		setTitle("Tetris 2: Son of Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(900, 700);
		this.setResizable(false);
		//setBackground(Color.LIGHT_GRAY);
		PlayScreen screen = new PlayScreen(this);
		add(screen);
		add(background);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new GameWindow();

	}

}
