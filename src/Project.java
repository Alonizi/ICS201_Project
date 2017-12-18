import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.*;
import javax.swing.*;

public class Project extends JFrame{

public static int rows = 5;

public static int cols = 10;

int count = 0;

public Box[][] grid = new Box[rows][cols];

public Project(){

this.setSize(700,700);

this.setDefaultCloseOperation(EXIT_ON_CLOSE);

this.setLocationRelativeTo(null);

this.setResizable(false);

this.setLayout(new GridLayout(rows,cols));

//initializing the boxes
for(int i = 0; i < rows; i++)

for(int j = 0; j < cols; j++)

this.add(grid[i][j] = new Box(i,j));
//drawing the boxes and the numbers inside them depending on whether the rows are even or odd.
for(int i = rows -1; i > -1; i--) {
if(rows % 2 == 0) {

if(i % 2 == 0)
for(int j = cols - 1; j > -1; j--) {
	grid[i][j].add(new JLabel(count++ + ""));
	if(j % 2 == 0)
		grid[i][j].setBackground(Color.GREEN);
	else
		grid[i][j].setBackground(Color.YELLOW);
}

else {

for(int j = 0; j < cols; j++) {
	grid[i][j].add(new JLabel(count++ + ""));
	if(j % 2 == 0)
		grid[i][j].setBackground(Color.GREEN);
	else
		grid[i][j].setBackground(Color.YELLOW);
}

}

}

else {

if(i % 2 != 0)

for(int j = cols-1; j > -1; j--) 
	grid[i][j].add(new JLabel(count++ + ""));


else {
for(int j = 0; j < cols; j++)
grid[i][j].add(new JLabel(count++ + ""));

}

}

}

this.setVisible(true);

}
public void paint(Graphics g) {
	super.paint(g);
	
	g.setColor(Color.RED);
	//g.drawLine(grid[0][1].getX(), grid[0][1].getY(), grid[4][4].getX(), grid[4][4].getY());
}
}