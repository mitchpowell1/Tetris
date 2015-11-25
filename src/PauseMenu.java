import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * The Pause Menu class is an extension of the JPanel class and displays when
 * the Tetris game is paused.
 * 
 * @author Mitch Powell
 *
 */
public class PauseMenu extends JPanel {
	private GameWindow window;
	private JButton resume;
	private JButton reset;
	private JButton options;
	private JPanel volumePanel;
	private JCheckBox muteToggle;
	private JLabel muteLabel;
	private JLabel volumeLabel;
	private JSlider volumeSlider;
	private JPanel buttonPanel;
	private JPanel mutePanel;
	private JPanel slidePanel;

	/**
	 * Constructor for the Pause Menu class
	 * 
	 * @param w
	 *            the parent GameWindow that the pause menu is contained within.
	 */
	public PauseMenu(GameWindow w) {
		this.window = w;
		setSize(600, 300);
		setLocation(300, 100);
		setBackground(Color.BLACK);
		setLayout(new GridLayout(0, 1, 0, 0));
		setBorder(makeTitle());
		
		makeButtonPanel();
		instantiateVolumeControls();
		
		add(buttonPanel);
		add(volumePanel);
	}
	
	/**
	 * Creates the Pause menu title
	 * @return the titled border for the pause menu.
	 */
	public TitledBorder makeTitle(){
		TitledBorder title = new TitledBorder("PAUSED");
		title.setTitleColor(Color.LIGHT_GRAY);
		title.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		title.setTitleFont(window.getFont());
		title.setTitleJustification(2);
		return title;
	}
	
	/**
	 * Creates the "Reset Game" and "Resume Game" buttons and the panel that houses them.
	 */
	public void makeButtonPanel(){
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		
		resume = new JButton("Resume Game");
		resume.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.startGame();
			}

		});
		
		reset = new JButton("Reset Game");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.getHoldPanel().setNull();
				window.getScreen().getTetrominos().clear();
				window.getScreen().getUsedTetrominos().clear();
				window.getScreen().removeAll();
				System.out.println("Reset Game");

			}

		});

		
		buttonPanel.add(resume);
		buttonPanel.add(reset);
	}
	
	/**
	 * Creates the volume controls and their panels.
	 */
	public void instantiateVolumeControls(){
		
		
		makeVolumePanel();
		makeMutePanel();
		makeVolumeSlider();
		
		mutePanel.add(muteLabel);
		mutePanel.add(muteToggle, BorderLayout.EAST);
		volumePanel.add(mutePanel);
		volumePanel.add(slidePanel, BorderLayout.SOUTH);
		
	}
	
	/**
	 * 
	 */
	
	
	
	/**
	 * Creates the Mute Panel that houses the mute button and its label
	 */
	public void makeMutePanel(){
		mutePanel = new JPanel();
		mutePanel.setBackground(Color.BLACK);
		mutePanel.setLayout(new BorderLayout(0, 0));
		muteLabel = new JLabel("Mute that sweet, sweet theme music?");
		muteLabel.setForeground(Color.LIGHT_GRAY);
		
		muteToggle = new JCheckBox();
		muteToggle.setOpaque(false);
		muteToggle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				window.getPlayer().setMute(muteToggle.isSelected());
				
			}
			
		});
		
	}
	
	/** 
	 * Creates the Volume Panel that houses all of the volume adjustment components and their labels
	 */
	public void makeVolumePanel(){
		volumePanel = new JPanel();
		volumePanel.setSize(600,600);
		volumePanel.setLayout(new BorderLayout(0, 0));
		volumePanel.setBackground(Color.BLACK);
		TitledBorder volumeOptionTitle = new TitledBorder(new LineBorder(new Color(192, 192, 192)), "VOLUME OPTIONS ", TitledBorder.ABOVE_TOP,
				TitledBorder.TOP, null, new Color(192, 192, 192));
		volumeOptionTitle.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		volumePanel.setBorder(volumeOptionTitle);
	}
	
	
	/**
	 * Creates the volumeSlider that adjusts the volume of the background music and the panel that houses it
	 */
	public void makeVolumeSlider(){
		

		volumeLabel = new JLabel("Or perhaps turn it down a little?");
		volumeLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		volumeLabel.setForeground(Color.LIGHT_GRAY);
		
		slidePanel = new JPanel();
		slidePanel.setBackground(Color.BLACK);
		slidePanel.setLayout(new BorderLayout(0, 0));
		slidePanel.add(volumeLabel);
		
		volumeSlider = new JSlider(0,10);
		volumeSlider.setPaintLabels(true);
		volumeSlider.setForeground(Color.WHITE);
		
		JLabel minLabel = new JLabel("Min");
		minLabel.setForeground(Color.WHITE);
		
		JLabel maxLabel = new JLabel("Max");
		maxLabel.setForeground(Color.WHITE);
		
		JLabel midLabel = new JLabel("Just Right");
		midLabel.setForeground(Color.WHITE);
		
		Hashtable labelTable = new Hashtable();
		labelTable.put(new Integer(0), minLabel);
		labelTable.put(new Integer(10), maxLabel);
		labelTable.put(new Integer(5), midLabel);
		volumeSlider.setLabelTable(labelTable);
		
		slidePanel.add(volumeSlider, BorderLayout.EAST);
		volumeSlider.setBackground(Color.WHITE);
		volumeSlider.setOpaque(false);

		volumeSlider.setValue((int) Math.round(window.getPlayer().getVolume()*10));
		volumeSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				window.getPlayer().setVolume((double)volumeSlider.getValue() / 10);
				System.out.println(window.getPlayer().getVolume());
				
			}
			
		});
		
	}
	
	/**
	 * Returns the volume slider
	 * @return the slider that controls the music volume
	 */
	public JSlider getVolumeSlider(){
		return volumeSlider;
	}
	
	/**
	 * Returns the mute box
	 * @return the JCheckbox that mutes the music
	 */
	public JCheckBox getMuteBox(){
		return muteToggle;
	}
}
