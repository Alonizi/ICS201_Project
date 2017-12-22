import javax.print.attribute.standard.PrinterLocation;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by abdulazizalonizi on 12/9/17.
 */
public class MainWindow extends JFrame implements Serializable{


    JMenuBar mainBar ;

    JMenu fileMenu ;
    JMenuItem insertPlayer ;
    JMenuItem reset ;
    JMenuItem save ;
    JMenuItem load;
    JMenuItem close ;

    JMenu settingsMenu ;
    JMenu playerNum ;
    JMenuItem newBoard ;
    JMenu gameStyle ;
    JRadioButtonMenuItem allWinners ;
    JRadioButtonMenuItem firstWinner ;
    JMenuItem setTraps ;
    JRadioButtonMenuItem players_num2 ;
    JRadioButtonMenuItem players_num3 ;
    JRadioButtonMenuItem players_num4 ;


    JMenu helpMenu ;
    JMenuItem manual ;
    JMenuItem about ;

    BoardDialog boardDialog ;

    BoxesPanel bp ;
    DrawingPanel dp;

    JButton rollButton ;
    JLabel posLabel ;
    JLabel rollLabel ;
    int dice1 =0 ;
    int dice2 =0;
    Random randomGen ;

    int factor= 72 ;
    int startX = 23 ;
    int startY = 485;
    int xPlayer =startX;
    int yPlayer =startY;
    Player p1 ;

    /// Testing !!

      int rows = 10;

      int cols = 10;
      
      int playerCount = 0;
      int random;
    int count = 0;
    
    int numSnakes = 3;
    
    int numLadders = 3;
    int snakeIX, snakeFX, snakeIY, snakeFY, ladderIX, ladderFX, ladderIY, ladderFY;
    public Box[][] grid = new Box[rows][cols];

    ///





    public MainWindow()
    {
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuListen ml = new MenuListen();
        setLayout(new BorderLayout());
        setResizable(false);


        //File Menu
        fileMenu = new JMenu("File");
        insertPlayer = new JMenuItem("Insert Player Name");
        reset = new JMenuItem("Reset");
        save = new JMenuItem("Save Current Game");
        save.addActionListener(ml);
        load = new JMenuItem("Load Game");
        load.addActionListener(ml);
        close = new JMenuItem("Close");
        close.addActionListener(ml);

        fileMenu.add(insertPlayer);
        fileMenu.add(reset);
        fileMenu.add(save);
        fileMenu.add(load);
        fileMenu.add(close);
        //

        // Settings Menu
        settingsMenu = new JMenu("Settings");

        playerNum = new JMenu("Insert Number of Players");
        players_num2 = new JRadioButtonMenuItem("2 Players");
        players_num2.addActionListener(ml);
        players_num3 = new JRadioButtonMenuItem("3 Players");
        players_num3.addActionListener(ml);
        players_num4 = new JRadioButtonMenuItem("4 Players");
        players_num4.addActionListener(ml);
        ButtonGroup bg_players = new ButtonGroup();
        bg_players.add(players_num2);
        bg_players.add(players_num3);
        bg_players.add(players_num4);

        newBoard = new JMenuItem("Set New Board");
        newBoard.addActionListener(ml);


        gameStyle = new JMenu("Game Style");
        allWinners = new JRadioButtonMenuItem("All Winners");
        firstWinner = new JRadioButtonMenuItem("First Winner");
        ButtonGroup bg_style = new ButtonGroup();
        bg_style.add(allWinners);
        bg_style.add(firstWinner);

        setTraps = new JMenuItem("Set Traps");


        settingsMenu.add(playerNum);
        playerNum.add(players_num2);
        playerNum.add(players_num3);
        playerNum.add(players_num4);

        settingsMenu.add(newBoard);

        settingsMenu.add(gameStyle);
        gameStyle.add(firstWinner);
        gameStyle.add(allWinners);
        settingsMenu.add(setTraps);
        playerNum.add(players_num2);
        playerNum.add(players_num3);
        playerNum.add(players_num4);

        //

        //Help Menu
        helpMenu = new JMenu("Help");
        manual = new JMenuItem("Manual");
        manual.addActionListener(ml);
        helpMenu.add(manual);
        about = new JMenuItem("About");
        about.addActionListener(ml); //ml is a MenuListener object
        helpMenu.add(about);

        //

        //Main Menu Bar
        mainBar = new JMenuBar();
        mainBar.add(fileMenu);
        mainBar.add(settingsMenu);
        mainBar.add(helpMenu);
        setJMenuBar(mainBar);
        //


        bp = new BoxesPanel();
        add(bp);



        dp = new DrawingPanel();

        JPanel glassPanel = (JPanel) this.getGlassPane();
        glassPanel.setLayout(new BorderLayout());
        glassPanel.add(dp);
        glassPanel.setVisible(true);




        Dimension d = new Dimension(150,150);
        JPanel supportPanel = new JPanel();
        supportPanel.setPreferredSize(d);
        rollButton = new JButton("Roll Dice !");
        rollButton.addActionListener(new SupportListener());
        posLabel = new JLabel("x,y :");
        rollLabel = new JLabel(dice1+" : "+dice2);
        randomGen = new Random();
        supportPanel.add(rollLabel);
        supportPanel.add(rollButton);
        supportPanel.add(posLabel);
        add(supportPanel,BorderLayout.SOUTH);


        addMouseMotionListener(new FrameListen());

//        setGlassPane(dp);
//        dp.setVisible(true);
//        setResizable(true);




    }
    //***End of MainWindow Constructor ***//

    // *** Class Action Listener For MENU BAR **** //
    public class MenuListen implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == about)
            {
                JOptionPane.showMessageDialog(null,"ICS201 Project \n\nCreated by\n Amro Albahrawi\n         &\nAbdulaziz Alonizi\n \n          V1.0" );
            }
            if (e.getSource() == manual)
            {
                HelpDialog hp = new HelpDialog();
                hp.setVisible(true);
            }
            if(e.getSource() == newBoard)
            {
                boardDialog = new BoardDialog() ;
                boardDialog.setVisible(true);
            }
            
            if(e.getSource() == close)
            	System.exit(0);
            
            if(e.getSource() == save) {
            	try {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("myFile.data")));
					oos.writeObject(this);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            if(e.getSource() == players_num2) {
            	
            }
            
            if(e.getSource() == players_num3) {
            	playerCount = 3;
            }
            
            if(e.getSource() == players_num4) {
            	playerCount = 4;
            }

        }


    }
    // End of Class MenuListener //

    public class SupportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == rollButton);
            {
            dice1 = randomGen.nextInt(7-1)+1;
            dice2 = randomGen.nextInt(7-1)+1;
            rollLabel.setText(dice1+" : "+dice2);

            int newX = xPlayer +factor;
                 if(newX < 0 )
                 {
                     factor = 72  ;
                     yPlayer = yPlayer -50;
                     xPlayer = xPlayer - factor;


                 }

                if(newX >700)
                {
                    yPlayer = yPlayer -50;
                    factor = -72;
                    xPlayer = xPlayer - factor;
                }
                xPlayer = xPlayer + factor ;


            dp.repaint();



            }
        }
    }

    // Class for Frame Listener
    public class FrameListen implements MouseMotionListener
    {

        @Override
        public void mouseDragged(MouseEvent e) {


        }

        @Override
        public void mouseMoved(MouseEvent e) {
            posLabel.setText("x,y :"+e.getX()+","+e.getY());
        }
    }

    // *** Class HelpDialog for Help Menu ***///
    public class HelpDialog extends JDialog
    {
        JTextArea helpArea ;


        public HelpDialog()
        {
            setSize(500,300);
            setLayout(new FlowLayout());
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            helpArea = new JTextArea(8,30);
            helpArea.setEditable(false);

            JScrollPane scroll = new JScrollPane(helpArea);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

           helpArea.setText("How to play:\n" +
                   "\n" +
                   "Each player puts their counter on the space that says 'start here'.\n" +
                   "Take it in turns to roll the dice. Move your counter forward the number of spaces shown on the dice.\n" +
                   "If your counter lands at the bottom of a ladder, you can move up to the top of the ladder.\n" +
                   "If your counter lands on the head of a snake, you must slide down to the bottom of the snake.\n" +
                   "The first player to get to the space that says 'home' is the winner.\n" +
                   "Have fun!\n" +
                   "\n");
           helpArea.setVisible(true);
           add(scroll);


        }

        }

        // End Class Help Dialog //


    // Class for New Board Dialog ..

    public class BoardDialog extends JDialog
    {
        JTextField boardSize ;
        JTextField snakesText ;
        JTextField laddersText ;
        JButton okButton ;

        public BoardDialog()
        {
            setSize(300,200);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());
            BoardDialogListener bdl = new BoardDialogListener();

            JPanel topPanel = new JPanel();
            JPanel centerPanel = new JPanel();
            JPanel bottomPanel = new JPanel(new BorderLayout());


            JLabel sizeLabel = new JLabel("Board Size");
            boardSize = new JTextField(13);
            boardSize.setText("30-100");
            boardSize.addActionListener(bdl);
            boardSize.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    boardSize.setText("");}});
            topPanel.add(sizeLabel);
            topPanel.add(boardSize);

            JLabel snakeLabel = new JLabel("Number of Snakes");
            snakesText = new JTextField(13);
            snakesText.setText("0-6");
            snakesText.addActionListener(bdl);
            snakesText.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e)
                {snakesText.setText("");} });
            centerPanel.add(snakeLabel,BorderLayout.WEST);
            centerPanel.add(snakesText,BorderLayout.EAST);

            JLabel ladderLabel = new JLabel("Number of Ladders");
            laddersText = new JTextField(13);
            laddersText.setText("0-6");
            laddersText.addActionListener(bdl);
            laddersText.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e)
                {laddersText.setText("");} });
            centerPanel.add(ladderLabel);
            centerPanel.add(laddersText);


            okButton = new JButton("Save");
            okButton.addActionListener(bdl);
            bottomPanel.add(okButton,BorderLayout.SOUTH);


            add(topPanel,BorderLayout.NORTH);
            add(centerPanel,BorderLayout.CENTER);
            add(bottomPanel,BorderLayout.SOUTH);
        }


        public class BoardDialogListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int board = Integer.parseInt(boardSize.getText());
            	int snakes = Integer.parseInt(snakesText.getText());
            	int ladders = Integer.parseInt(laddersText.getText());
                
                if(e.getSource() == okButton) {

                    //System.out.println(numSnakes.getText());

                    count = 0 ;
                    rows = 5 ;
                    cols = 10 ;

                    count = 0 ;
                    rows = (board / 10) ;
                    cols = board / rows ;
                    numSnakes = snakes;
                    numLadders = ladders;
                    
                    snakeIX  = 45 + (int) (Math.random() * (656 - 45));
                    snakeFX  = 45 + (int) (Math.random() * (656 - 45));
                    ladderIX  = 45 + (int) (Math.random() * (656 - 45));
                    ladderFX  = 45 + (int) (Math.random() * (656 - 45));
                    snakeIY = 85 + (int) (Math.random() * (371 - 85));
                    snakeFY = 420 + (int) (Math.random() * (500 - 420));
                    ladderIY = 370 + (int) (Math.random() * (470 - 370));
                    ladderFY = 85 + (int) (Math.random() * (180 - 85));
                    
                    bp.removeAll();
                    bp.createBoxes();
                    bp.revalidate();
                    bp.repaint();
                    
//                    width = 100 ;
//
//                    bp.setVisible(false);
//                    bp.removeAll();
//                    bp.revalidate();
//
//                    rows = 5;
//                    cols = 10 ;
//
//                   bp.createBoxes();
//                   bp.revalidate();
//                   bp.repaint();
//                   bp.setVisible(true);
                    
                    dispose();

                }

            }
        }

    }

    // End of Class BoardDialog !! ... //



    public class BoxesPanel extends JPanel
    {
        public BoxesPanel() {
            createBoxes();
//<<<<<<< HEAD
//
////            this.setLayout(new GridLayout(rows, cols));
//=======
// //           this.setLayout(new GridLayout(rows, cols));
//>>>>>>> 17e3ce9c7bcfbf6e9bc11c476bcdb12353b7b490
//
//    //initializing the boxes
//            for (int i = 0; i < rows; i++)
//
//                for (int j = 0; j < cols; j++)
//
//                    this.add(grid[i][j] = new Box(i, j));
//    //drawing the boxes and the numbers inside them depending on whether the rows are even or odd.
//            for (int i = rows - 1; i > -1; i--) {
//                if (rows % 2 == 0) {
//
//                    if (i % 2 == 0)
//                        for (int j = cols - 1; j > -1; j--) {
//                            grid[i][j].add(new JLabel(count++ + ""));
//                            if (j % 2 == 0)
//                                grid[i][j].setBackground(Color.GREEN);
//                            else
//                                grid[i][j].setBackground(Color.YELLOW);
//                        }
//
//                    else {
//
//                        for (int j = 0; j < cols; j++) {
//                            grid[i][j].add(new JLabel(count++ + ""));
//                            if (j % 2 == 0)
//                                grid[i][j].setBackground(Color.GREEN);
//                            else
//                                grid[i][j].setBackground(Color.YELLOW);
//                        }
//
//                    }
//
//                } else {
//
//                    if (i % 2 != 0)
//
//                        for (int j = cols - 1; j > -1; j--)
//                            grid[i][j].add(new JLabel(count++ + ""));
//
//
//                    else {
//                        for (int j = 0; j < cols; j++)
//                            grid[i][j].add(new JLabel(count++ + ""));
//
//                    }
//
//
//                }
//
//            }

            //this.setVisible(true);
        }

        public void createBoxes()
        {
            this.setLayout(new GridLayout(rows, cols));

            //initializing the boxes
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < cols; j++)
                    this.add(grid[i][j] = new Box(i, j));
            
            
            //drawing the boxes and the numbers inside them depending on whether the rows are even or odd.
            for (int i = rows - 1; i > -1; i--) {
                if (rows % 2 == 0) {

                    if (i % 2 == 0)
                        for (int j = cols - 1; j > -1; j--) {
                            grid[i][j].add(new JLabel(count++ + ""));
                            if (j % 2 == 0)
                                grid[i][j].setBackground(Color.GREEN);
                            else
                                grid[i][j].setBackground(Color.YELLOW);
                        }

                    else {

                        for (int j = 0; j < cols; j++) {
                            grid[i][j].add(new JLabel(count++ + ""));
                            if (j % 2 == 0)
                                grid[i][j].setBackground(Color.GREEN);
                            else
                                grid[i][j].setBackground(Color.YELLOW);
                        }

                    }

                } else {

                    if (i % 2 != 0)

                        for (int j = cols - 1; j > -1; j--) {
                        	 grid[i][j].add(new JLabel(count++ + ""));
                        	 if (j % 2 == 0)
                                 grid[i][j].setBackground(Color.GREEN);
                             else
                                 grid[i][j].setBackground(Color.YELLOW);
                        }
                           


                    else {
                        for (int j = 0; j < cols; j++) {
                        	grid[i][j].add(new JLabel(count++ + ""));
                        	if (j % 2 == 0)
                                grid[i][j].setBackground(Color.GREEN);
                            else
                                grid[i][j].setBackground(Color.YELLOW);
                        }
                            

                    }


                }

            }

            //this.setVisible(true);
        }
        }



        // Draw Class , AMROOOOO DRAW YOUR STUFF HERE !!! ///

    public class DrawingPanel extends JComponent //implements ComponentListener
    {


        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
        	Random rand = new Random();
        	
        	
        	Snake[] snakes = new Snake[numSnakes];
        	Ladder[] ladders = new Ladder[numLadders];

        	for (int i = 0; i < snakes.length; i++)
                snakes[i] = new Snake(snakeIX, snakeIY, snakeFX, snakeFY);

            for (int i = 0; i < snakes.length; i++)
                snakes[i].draw(g);

            for (int i = 0; i < ladders.length; i++) {
                ladders[i] = new Ladder(ladderIX, ladderIY, ladderFX, ladderFY);
                ladders[i].draw(g);
            }


            p1 = new Player("player1",xPlayer,yPlayer,Color.BLACK);
           p1.paint(g);

          

         
           
           Player p2 = new Player("player2",startX, startY,Color.RED);
           p2.paint(g);
           
           if(playerCount == 3) {
        	   Player p3 = new Player("player3",startX,startY,Color.BLUE);
        	   p3.paint(g);
           }
           
           if(playerCount == 4) {

        	   Player p3 = new Player("player3", startX,startY,Color.BLUE);
        	   Player p4 = new Player("player4",startX,startY,Color.CYAN);
        	   p3.paint(g);
        	   p4.paint(g);
           }
        }


//        @Override
//        public void componentResized(ComponentEvent e) {
//            //this.setPreferredSize();
//        }
//
//        @Override
//        public void componentMoved(ComponentEvent e) {
//
//        }
//
//        @Override
//        public void componentShown(ComponentEvent e) {
//
//        }
//
//        @Override
//        public void componentHidden(ComponentEvent e) {
//
//        }
    }


    public class Player
    {
        int x ;
        int y ;
        Color c ;
        String name  ;

        public Player(String name ,int x,int y , Color c)
        {

            this.x = x ;
            this.y = y ;
            this.name = name ;
            this.c = c ;

        }

        public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public Color getC() {
			return c;
		}

		public void setC(Color c) {
			this.c = c;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
		public void paint(Graphics g)
        {
            g.setColor(c);
            g.fillOval(x,y,13,13);
            g.fillRect(x,y+12,13,13);
        }


    }



}

