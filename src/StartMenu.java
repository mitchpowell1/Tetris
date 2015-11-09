import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class StartMenu extends JPanel {
	private JButton playButton;
	private JPanel instructionPanel;
	public StartMenu(GameWindow w){
		//setLayout(new BoxLayout(1));
		setLocation(50,0);
		setSize(1100,1100);
		instructionPanel = new JPanel();
		TitledBorder instructionBorder = new TitledBorder("How to play");
		instructionBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5));
		instructionPanel.setBorder(instructionBorder);
		instructionPanel.setSize(100, 1000);
		instructionPanel.setBackground(Color.BLACK);
		playButton = new JButton("Play!");
		playButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				w.startGame();
				
			}
			
		});
		setBackground(Color.BLACK);
		//playButton.setLocation(1000,0);
		add(instructionPanel);
		add(playButton);
	}
}
