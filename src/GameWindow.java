import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * The GameWindow Class is an extension of the JFrame class,
 * it holds all of the other game components, as well as the media player for the
 * Tetris theme song
 * @author Mitch Powell
 *
 */
public class GameWindow extends JFrame{
	
	private Font gameFont;
	private Timer timer;
	private ImageIcon backgroundIMG;
	private JLabel background;
	private PlayScreen screen;
	private StatsWindow stats;
	private NextBlockPanel next;
	private HoldBlockPanel hold;
	private StartMenu start;
	private PauseMenu pause;
	private int time;
	private Media themeSong;
	private MediaPlayer player;
	
	/**
	 * Constructor for a game window class.
	 */
	public GameWindow(){
		
		JFXPanel mediaPanel = new JFXPanel();
		add(mediaPanel);
		setLayout(new BorderLayout());
		backgroundIMG = new ImageIcon(Toolkit.getDefaultToolkit().createImage("Tetris_Background.jpg").getScaledInstance(1200, 1200, 2));
		background = new JLabel();
		background.setIcon(backgroundIMG);
		themeSong = new Media(new File("Tetris_Theme.mp3").toURI().toString());
		player = new MediaPlayer(themeSong);
		player.setAutoPlay(true);
		player.setOnEndOfMedia(new Runnable() {
		       public void run() {
		           player.seek(Duration.ZERO);
		         }
		     });
		player.play();
		setTitle("Tetris 2: Son of Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(1200, 700);
		this.setResizable(false);
		
		time = 0;
		timer = new Timer(1000, new GameTimeListener());
		gameFont = new Font("Arial", Font.PLAIN, 25);
		start = new StartMenu(this);
		start.requestFocusInWindow();
		
		screen = new PlayScreen(this);
		
		stats = new StatsWindow(this);
		
		next = new NextBlockPanel(this);
		
		hold = new HoldBlockPanel(this);
		
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
	
	/**
	 * Starts the timer and displays the gameplay components
	 */
	public void startGame(){
		timer.start();
		start.setVisible(false);
		pause.setVisible(false);
		screen.setVisible(true);
		screen.startKeyListening();
		screen.requestFocusInWindow();
		stats.setVisible(true);
		next.setVisible(true);
		hold.setVisible(true);
		revalidate();
	}
	
	/**
	 * Stops the timer and shows the pause menu component
	 */
	public void pauseGame(){
		timer.stop();
		screen.setVisible(false);
		screen.stopKeyListening();
		stats.setVisible(false);
		next.setVisible(false);
		hold.setVisible(false);
		pause.setVisible(true);
		System.out.println("The timer is stopped and the game is paused");
	}
	
	/**
	 * Returns the Game timer so that other components can use it
	 * @return The game timer.
	 */
	public Timer getTimer(){
		return timer;
	}
	
	/**
	 * The font that is used for all of the Titles and Labels.
	 */
	public Font getFont(){
		return gameFont;
	}
	
	/**
	 * The Media Player that plays the Tetris theme music
	 * @return The GameWindows theme music player
	 */
	public MediaPlayer getPlayer(){
		return player;
	}
	
	/**
	 * An inner class containing an extension of an ActionListener.
	 * Updates the time elapsed in the statistics panel
	 */
	private class GameTimeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			time += 1;
			stats.getTimeLabel().setText("Time: "+time+"\n");
		}
	}

	/**
	 * creates a new GameWindow
	 * @param args command-line arguments (ignored)
	 */
	public static void main(String[] args) {
		new GameWindow();

	}
}
