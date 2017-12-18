import java.awt.FlowLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.LineBorder;

public class Box extends JPanel{

private int rows;

private int cols;

public Box(int rows, int cols) {

this.rows = rows;

this.cols = cols;

setBorder(new LineBorder(Color.BLACK, 1));

//setBackground(Color.YELLOW);

}

}