import java.awt.*;

import javax.swing.*;

import java.awt.geom.Line2D;
import java.io.Serializable;


public class Snake extends JPanel implements Serializable{
	int initialX;
	int initialY;
	int finalX;
	int finalY;
	
	public Snake(int initialX, int initialY, int finalX, int finalY) {
		this.initialX = initialX;
		this.initialY = initialY;
		this.finalX = finalX;
		this.finalY = finalY;
	}
	
	public Snake() {
		
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
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(8));
		g2d.drawLine(this.initialX, this.initialY, this.finalX, this.finalY);
	}
	
}
