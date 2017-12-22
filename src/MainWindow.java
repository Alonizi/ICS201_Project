import javax.print.attribute.standard.PrinterLocation;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Created by abdulazizalonizi on 12/9/17.
 */
public class MainWindow extends JFrame{


    JMenuBar mainBar ;

    JMenu fileMenu ;
    JMenuItem insertPlayer ;
    JMenuItem reset ;
    JMenuItem save ;
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


    int startX = 23 ;
    int startY = 485;

    /// Testing !!

      int rows = 10;

      int cols = 10;

    int count = 0;
    
    int numSnakes = 3;
    
    int numLadders = 3;
    
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
        close = new JMenuItem("Close");

        fileMenu.add(insertPlayer);
        fileMenu.add(reset);
        fileMenu.add(save);
        fileMenu.add(close);
        //

        // Settings Menu
        settingsMenu = new JMenu("Settings");

        playerNum = new JMenu("Insert Number of Players");
        players_num2 = new JRadioButtonMenuItem("2 Players");
        players_num3 = new JRadioButtonMenuItem("3 Players");
        players_num4 = new JRadioButtonMenuItem("4 Players");
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
        posLabel = new JLabel("x,y :");
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

        }


    }
    // End of Class MenuListener //

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
        JTextField numSnakes ;
        JTextField numLadders ;
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
            numSnakes = new JTextField(13);
            numSnakes.setText("0-6");
            numSnakes.addActionListener(bdl);
            numSnakes.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e)
                {numSnakes.setText("");} });
            centerPanel.add(snakeLabel,BorderLayout.WEST);
            centerPanel.add(numSnakes,BorderLayout.EAST);

            JLabel ladderLabel = new JLabel("Number of Ladders");
            numLadders = new JTextField(13);
            numLadders.setText("0-6");
            numLadders.addActionListener(bdl);
            numLadders.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e)
                {numLadders.setText("");} });
            centerPanel.add(ladderLabel);
            centerPanel.add(numLadders);


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
                if(e.getSource() == numSnakes) {
                  //  numSnakes.setText();
                    System.out.println(numSnakes.getText());
                }
                if(e.getSource() == okButton) {
                    System.out.println(numSnakes.getText());
                    count = 0 ;
                    rows = 5 ;
                    cols = 10 ;
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
                    System.out.println("working");
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
//            this.setLayout(new GridLayout(rows, cols));
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

                        for (int j = cols - 1; j > -1; j--)
                            grid[i][j].add(new JLabel(count++ + ""));


                    else {
                        for (int j = 0; j < cols; j++)
                            grid[i][j].add(new JLabel(count++ + ""));

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
        	Random rand = new Random();
        	int coordX_initial = rand.nextInt(655-45) + 45;
        	int coordX_final = rand.nextInt(655-45) + 45;
        	int coordY_initial = rand.nextInt(370-85) + 85;
        	int coordY_final = rand.nextInt(520-420) + 420;
        	
        	Snake[] snakes = new Snake[numSnakes];
        	Ladder[] ladders = new Ladder[numLadders];
        		
           for(int i = 0; i < snakes.length; i++) 
        	   snakes[i] = new Snake(45 + (int) (Math.random() * (656-45)), 85 + (int) (Math.random() * (371-85)), 45 + (int) (Math.random() * (656-45)),420 + (int) (Math.random() * (500-420)) );
        	   
           for(int i = 0; i < snakes.length; i++) 
           snakes[i].draw(g); 
           
           for(int i = 0; i < ladders.length; i++) {
        	   ladders[i] = new Ladder(45 + (int) (Math.random() * (656-45)) , 370 + (int) (Math.random() * (470-370)) , 45 + (int) (Math.random() * (656-45)),85 + (int) (Math.random() * (180-85)));
        	   ladders[i].draw(g);
           }


           Player p1 = new Player("player1",startX,startY,Color.BLACK);
           p1.paint(g);

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


    public class PlayerRun extends Thread {
        @Override
        public void run() {
            super.run();
            startX++;
        }
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

        public void paint(Graphics g)
        {
            g.setColor(c);
            g.fillOval(x,y,13,13);
            g.fillRect(x,y+12,13,13);
        }
    }



}

