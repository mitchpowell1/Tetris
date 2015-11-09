import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GameWindow extends JFrame{
	
	private ImageIcon backgroundIMG;
	private JLabel background;
	private PlayScreen screen;
	private StatsWindow stats;
	private NextBlockPanel next;
	private HoldBlockPanel hold;
	private StartMenu start;
	private PauseMenu pause;
	
	public GameWindow(){
		
		setLayout(new BorderLayout());
		backgroundIMG = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Tetris_Background.jpg").getScaledInstance(1200, 1200, 2));
		background = new JLabel();
		background.setIcon(backgroundIMG);
		setTitle("Tetris 2: Son of Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(1200, 700);
		this.setResizable(false);
		
		start = new StartMenu(this);
		
		screen = new PlayScreen(this);
		
		stats = new StatsWindow();
		
		next = new NextBlockPanel();
		
		hold = new HoldBlockPanel();
		
		pause = new PauseMenu(this);
		add(pause);
		add(screen);
		add(stats);
		add(next);
		add(hold);	
		add(start);
		add(background);
		
		screen.setVisible(false);
		stats.setVisible(false);
		next.setVisible(false);
		hold.setVisible(false);
		pause.setVisible(false);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new GameWindow();

	}
	
	public void startGame(){
		start.setVisible(false);
		pause.setVisible(false);
		screen.setVisible(true);
		screen.startKeyListening();
		screen.requestFocus();
		stats.setVisible(true);
		next.setVisible(true);
		hold.setVisible(true);
		revalidate();
	}
	
	public void pauseGame(){
		screen.setVisible(false);
		screen.stopKeyListening();
		stats.setVisible(false);
		next.setVisible(false);
		hold.setVisible(false);
		pause.setVisible(true);
		System.out.println("The timer is stopped and the game is paused");
	}

}
