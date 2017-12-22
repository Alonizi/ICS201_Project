import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class Ladder extends JPanel{
	int initialX;
	int initialY;
	int finalX;
	int finalY;
	
	public Ladder(int initialX, int initialY, int finalX, int finalY) {
		this.initialX = initialX;
		this.initialY = initialY;
		this.finalX = finalX;
		this.finalY = finalY;
	}

	public int getInitialX() {
		return initialX;
	}

	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}

	public int getInitialY() {
		return initialY;
	}

	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}

	public int getFinalX() {
		return finalX;
	}

	public void setFinalX(int finalX) {
		this.finalX = finalX;
	}

	public int getFinalY() {
		return finalY;
	}

	public void setFinalY(int finalY) {
		this.finalY = finalY;
	}
	
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.GRAY);
		g2d.setStroke(new BasicStroke(8));
		g2d.draw(new Line2D.Float(this.initialX, this.initialY, this.finalX, this.finalY));
	}

}
