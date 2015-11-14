import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JComponent;


public class Tetromino extends JComponent{
	private Block[] blocks = new Block[4];
	private Color color;
	private int x;
	private int y;
	
	public Tetromino(PlayScreen p, Color c){
		this.color = c;
		for(int i=0; i<4; i++){
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.draw(blocks[0]);;
	}
}
