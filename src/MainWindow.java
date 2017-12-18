import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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





        /// Testing !!

    public static int rows = 6;

    public static int cols = 5;

    int count = 0;

    public Box[][] grid = new Box[rows][cols];

    ///




    public MainWindow()
    {
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuListen ml = new MenuListen();
        setLayout(new BorderLayout());


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

        DrawingPanel dp = new DrawingPanel();

        JPanel glassPanel = (JPanel) this.getGlassPane();
        glassPanel.setLayout(new BorderLayout());
        glassPanel.add(dp);
        glassPanel.setVisible(true);






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
                    dispose();
                }


            }
        }



    }

    // End of Class BoardDialog !! ... //



    public class BoxesPanel extends JPanel
    {
        public BoxesPanel() {
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

    public class DrawingPanel extends JComponent
    {


        public void paintComponent(Graphics g)
        {

           // super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillOval(50,50,200,200);

        }
    }




}

