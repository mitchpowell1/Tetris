import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GameWindow extends JFrame{
	
	private ImageIcon backgroundIMG;
	private JLabel background;
	public GameWindow(){
		
		setLayout(new BorderLayout());
		backgroundIMG = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Tetris_Background.jpg").getScaledInstance(1200, 1200, 2));
		background = new JLabel();
		background.setIcon(backgroundIMG);
		setTitle("Tetris 2: Son of Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(1200, 700);
		this.setResizable(false);
		
		PlayScreen screen = new PlayScreen(this);
		
		StatsWindow stats = new StatsWindow();
		
		NextBlockPanel next = new NextBlockPanel();
		
		HoldBlockPanel hold = new HoldBlockPanel();
		
		
		next.displayBlock();
		hold.displayBlock();
		add(screen);
		add(stats);
		add(next);
		add(hold);
		add(background);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new GameWindow();

	}

}
