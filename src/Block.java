import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Block extends Rectangle{
	private Color color;
	
	public Block(PlayScreen p, int x, int y, Color col){
		this.width = p.getWidth()/10;
		this.height = p.getWidth()/10;
		this.x = x;
		this.y = y;
		this.color = col;
	}
	
	public void draw(Graphics2D g2){
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		g2.draw(this);
		g2.fill(this);
	}
}
