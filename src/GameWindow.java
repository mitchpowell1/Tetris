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
 * The GameWindow Class is an extension of the JFrame class, it holds all of the
 * other game components, as well as the media player for the Tetris theme song
 * 
 * @author Mitch Powell
 *
 */
public class GameWindow extends JFrame {

	private Font gameFont;
	private Timer timer;
	private ImageIcon backgroundIMG;
	private JLabel background;
	private PlayScreen screen;
	private StatsWindow stats;
	private NextBlockPanel next;
	private HoldBlockPanel hold;
	private JLabel logo;
	private StartMenu start;
	private PauseMenu pause;
	private int time;
	private Media themeSong;
	private MediaPlayer player;
	private GameKeyListener menuListener;
	private PauseButton pauseButton;
	private int level = 1;
	public double DEFAULTVOLUME = 0.3;

	/**
	 * Constructor for a game window class.
	 */
	public GameWindow() {
		
		setTitle("Tetris 2: Son of Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuListener = new GameKeyListener();
		this.setSize(1200, 750);
		this.setResizable(false);
		setLayout(new BorderLayout());
		initializeAudio();
		createBackground();
		initializeInstanceVars();

		addKeyListener(menuListener);
		add(pause);
		add(screen);
		add(stats);
		add(next);
		add(hold);
		add(start);
		add(logo);
		add(pauseButton);
		add(background);

		this.setVisible(true);
	}

	/**
	 * Paints the background image onto the frame
	 */
	public void createBackground() {
		backgroundIMG = new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage("Tetris_Background.jpg")
				.getScaledInstance(1200, 1200, 2));
		background = new JLabel();
		background.setIcon(backgroundIMG);
	}

	/**
	 * Creates all of the game component instance variables
	 */
	public void initializeInstanceVars() {

		time = 0;
		pauseButton = new PauseButton(this);
		timer = new Timer(20, new GameTimeListener());
		timer.setInitialDelay(3000);
		logo = new JLabel("TETRIS");
		logo.setFont(new Font("Arial Black", Font.PLAIN, 50));
		logo.setForeground(Color.BLACK);
		logo.setOpaque(false);
		logo.setSize(250,100);
		logo.setBackground(Color.BLACK);
		logo.setLocation(850,465);
		gameFont = new Font("Arial", Font.PLAIN, 25);
		start = new StartMenu(this);
		start.addKeyListener(menuListener);
		screen = new PlayScreen(this);
		stats = new StatsWindow(this);
		next = new NextBlockPanel(this);
		hold = new HoldBlockPanel(this);
		pause = new PauseMenu(this);
		screen.setVisible(false);
		stats.setVisible(false);
		next.setVisible(false);
		hold.setVisible(false);
		pauseButton.setVisible(false);
		pause.setVisible(false);
	}

	/**
	 * Creates the Audio components of the game
	 */
	public void initializeAudio() {
		JFXPanel mediaPanel = new JFXPanel();
		add(mediaPanel);
		themeSong = new Media(new File("Tetris_Theme.mp3").toURI().toString());
		player = new MediaPlayer(themeSong);
		player.setVolume(DEFAULTVOLUME);
		player.setAutoPlay(true);
		//These lines put the theme song on a loop
		player.setOnEndOfMedia(new Runnable() {
			public void run() {
				player.seek(Duration.ZERO);
			}
		});
		player.play();
	}

	/**
	 * Starts the timer and displays the gameplay components
	 */
	public void startGame() {
		start.removeKeyListener(menuListener);
		pause.removeKeyListener(menuListener);
		timer.start();
		start.setVisible(false);
		pause.setVisible(false);
		logo.setVisible(true);
		screen.setVisible(true);
		screen.startKeyListening();
		screen.requestFocusInWindow();
		stats.setVisible(true);
		next.setVisible(true);
		pauseButton.setVisible(true);
		hold.setVisible(true);
		revalidate();
	}

	/**
	 * Stops the timer and shows the pause menu component
	 */
	public void pauseGame() {
		timer.stop();
		screen.setVisible(false);
		screen.stopKeyListening();
		stats.setVisible(false);
		next.setVisible(false);
		hold.setVisible(false);
		pauseButton.setVisible(false);
		pause.setVisible(true);
		//pause.addKeyListener(menuListener);
		//pause.requestFocus(true);
		System.out.println(pause.hasFocus());
		System.out.println("The timer is stopped and the game is paused");
	}

	/**
	 * Returns the Game timer so that other components can use it
	 * 
	 * @return The game timer.
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * The font that is used for all of the Titles and Labels.
	 */
	public Font getFont() {
		return gameFont;
	}

	/**
	 * The Media Player that plays the Tetris theme music
	 * 
	 * @return The GameWindows theme music player
	 */
	public MediaPlayer getPlayer() {
		return player;
	}

	/**
	 * An inner class containing an extension of an ActionListener. Updates the
	 * time elapsed in the statistics panel
	 */
	private class GameTimeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			time += 20;
			screen.incrementDrop(level);
			screen.repaint();
			if(time % 1000 == 0){
				stats.getTimeLabel().setText("Time: " + time/1000 + "\n");
			}
		}
	}

	public PauseMenu getPauseMenu(){
		return pause;
	}
	
	/**
	 * creates a new GameWindow
	 * 
	 * @param args
	 *            command-line arguments (ignored)
	 */
	public static void main(String[] args) {
		new GameWindow();

	}
	
	/**
	 * This private inner class listens for keyboard input to adjust the volume
	 * of the theme song.
	 * 
	 * @author Mitch Powell
	 *
	 */
	private class GameKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("Key Pressed in menu screen");
			switch (e.getKeyCode()) {
			case KeyEvent.VK_M:
				player.setMute(!player.isMute());
				pause.getMuteBox().setSelected(player.isMute());
				break;
			case KeyEvent.VK_MINUS:
				if(player.getVolume() <= .1){
					player.setVolume(0);
				} else {
					player.setVolume(
							player.getVolume() - .1);	
				}
				pause.getVolumeSlider().setValue((int) Math.round(player.getVolume() * 10));
				break;
			case KeyEvent.VK_EQUALS:
				if(player.getVolume() >= .9){
					player.setVolume(1);
				} else {
					player.setVolume(
						player.getVolume() + .1);
				}
				pause.getVolumeSlider().setValue((int) Math.round(player.getVolume() * 10));
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
