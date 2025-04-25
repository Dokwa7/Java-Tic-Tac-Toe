import javax.swing.*; //for GUI components like JFrame, JPanel, JButton
import java.awt.*; // for creating buttons and other visual components like color, font, etc.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GUI {
    private JButton[] buttons;
    private char[] num = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private char player, computer, player2;
    private ImageIcon playericon, computericon, player2icon;
    private boolean running = true, ptop = false, p1_turn = true, playerwin = false, compwin = false;

    public GUI() {
        JFrame frame = new JFrame("Tic tac toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits the frame when clicked on x
        frame.setLayout(null); // used to automatically position the components in the frame but when set to null you need to set everything manually using .setBounds()
        BackgroundPanel panel = new BackgroundPanel("ticbg.png"); // panel is used to display buttons, label(which displays the text and images)
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // extend the frame to full screen
        frame.setContentPane(panel); // add all the components(buttons) to the panel.
        JButton button = new JButton(); // creates a new button
        button.setBounds(637, 500, 89, 93);
        button.setIcon(new ImageIcon("start button.png")); // add image to button
        button.setBorder(BorderFactory.createEtchedBorder()); // for border decor
        button.setFocusable(false); // removes the box that displays around the text in the button
        button.addActionListener(e -> { // tells what to do when click on the button
            ChooseGame();
            frame.dispose(); // used to close the window/frame and in this code moves to another frame/screen/window
        });
        panel.add(button); // adding the button to the panel
        frame.setVisible(true); // makes the frame visible
    }

    public class BackgroundPanel extends JPanel { // BackgroundPanel is a custom panel that extends JPanel to display background image
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) { // overriding paintComponent that draws the bg image
            super.paintComponent(g); // clears the background to draw the image
            g.drawImage(backgroundImage, 0, 0, 1370, 710, this); // draws the image
        }
    }

    public void ChooseGame() {
        JFrame frame = new JFrame("Choose game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BackgroundPanel panel3 = new BackgroundPanel("ticbg3.png");
        frame.setContentPane(panel3);
        frame.setLayout(null);
        JButton p2p = new JButton(); // player to player button
        p2p.setBounds(555, 330, 280, 75);
        p2p.setIcon(new ImageIcon("p2p.png"));
        p2p.setBorder(BorderFactory.createEtchedBorder());
        p2p.setFocusable(false);
        p2p.addActionListener(e -> {
            ptop = true; // ptop = player to player
            SelectToken();
            frame.dispose();
        });
        JButton p2c = new JButton(); // player to computer button
        p2c.setBounds(555, 440, 280, 75);
        p2c.setIcon(new ImageIcon("p2c.png"));
        p2c.setBorder(BorderFactory.createEtchedBorder());
        p2c.setFocusable(false);
        p2c.addActionListener(e -> {
            ptop = false;
            SelectToken();
            frame.dispose();
        });
        panel3.add(p2p);
        panel3.add(p2c);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public void SelectToken() {
        JFrame frame = new JFrame("Select Token");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BackgroundPanel panel2 = new BackgroundPanel("ticbg2.png");
        frame.setContentPane(panel2);
        frame.setLayout(null);

        JButton button2 = new JButton();
        button2.setBounds(650, 210, 98, 98);
        button2.setIcon(new ImageIcon("X.png"));
        button2.setBorder(BorderFactory.createEtchedBorder());
        button2.setFocusable(false);
        button2.addActionListener(e -> {
            player = 'X';
            computer = 'O';
            player2 = 'O';
            playericon = new ImageIcon("X.png");
            computericon = new ImageIcon("O.png");
            player2icon = new ImageIcon("O.png");
            button2.setEnabled(false);
        });

        JButton button3 = new JButton("O");
        button3.setBounds(650, 320, 98, 98);
        button3.setIcon(new ImageIcon("O.png"));
        button3.setBorder(BorderFactory.createEtchedBorder());
        button3.setFocusable(false);
        button3.addActionListener(e -> {
            player = 'O';
            computer = 'X';
            player2 = 'X';
            playericon = new ImageIcon("O.png");
            computericon = new ImageIcon("X.png");
            player2icon = new ImageIcon("X.png");
            button3.setEnabled(false); // disabling the button once it is clicked
        });

        JButton startgame = new JButton();
        startgame.setBounds(558, 430, 280, 60);
        startgame.setIcon(new ImageIcon("Play Now.png"));
        startgame.setBorder(BorderFactory.createEtchedBorder());
        startgame.setFocusable(false);
        startgame.addActionListener(e -> {
            Gamegrid();
            frame.dispose();
        });

        JButton back = new JButton();
        back.setBounds(608, 500, 165, 60);
        back.setIcon(new ImageIcon("back.png"));
        back.setBorder(BorderFactory.createEtchedBorder());
        back.setFocusable(false);
        back.addActionListener(e -> {
            ChooseGame();
            frame.dispose();
        });

        panel2.add(button2);
        panel2.add(button3);
        panel2.add(startgame);
        panel2.add(back);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public void Gamegrid() {
        JFrame frame = new JFrame("Grid");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3));
        buttons = new JButton[9]; // creating array of buttons to form a grid
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(String.valueOf(num[i]));
            buttons[i].setBorder(BorderFactory.createEtchedBorder());
            buttons[i].setIcon(new ImageIcon("gridbg.png"));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(new ButtonListener());
            frame.add(buttons[i]);
        }
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener { // class to tell what to do when a button is clicked
        public void actionPerformed(ActionEvent e) {
            if (running) {
                JButton button = (JButton) e.getSource();
                int index = Integer.parseInt(button.getText()) - 1;
                if (num[index] == player || num[index] == computer) {
                    JOptionPane.showMessageDialog(null, "Invalid move! Please choose an empty cell.");
                } else {
                    if (ptop) {
                        if (p1_turn) {
                            num[index] = player;
                            button.setIcon(playericon);
                            if (checkWinner()) {
                                running = false;
                            } else if (checkTie()) {
                                running = false;
                            }
                            p1_turn = false;
                        } else {
                            num[index] = player2;
                            button.setIcon(player2icon);
                            if (checkWinner()) {
                                running = false;
                            } else if (checkTie()) {
                                running = false;
                            }
                            p1_turn = true;
                        }
                    } else {
                        num[index] = player;
                        button.setIcon(playericon);
                        if (checkWinner()) {
                            running = false;
                        } else if (checkTie()) {
                            running = false;
                        } else {
                            if (!ptop) {
                                ComputerMove();
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Game over! Please restart to play again.");
            }
        }
    }

    public void ComputerMove() {
        // Check for a winning move
        for (int i = 0; i < 9; i++) {
            if (num[i] >= '1' && num[i] <= '9') {
                char original = num[i];
                num[i] = computer;
                if (checkWinner()) {
                    num[i] = original;  // Undo the move
                    buttons[i].setIcon(computericon);
                    num[i] = computer;  // Redo the move
                    running = false;
                    return;  // Computer wins
                }
                    num[i] = original;  // Undo the move

            }
        }
        // Check mid button
        if (num[4] >= '1' && num[4] <= '9') {
            num[4] = computer;
            buttons[4].setIcon(computericon);
            checkWinner(); // Check after making the move
            return;
        }
        // Check even indexes
        for (int i : new int[]{0, 2, 6, 8}) {
            if (num[i] >= '1' && num[i] <= '9') {
                num[i] = computer;
                buttons[i].setIcon(computericon);
                checkWinner(); // Check after making the move
                return;
            }
        }
        // Check odd indexes
        for (int i : new int[]{1, 3, 5, 7}) {
            if (num[i] >= '1' && num[i] <= '9') {
                num[i] = computer;
                buttons[i].setIcon(computericon);
                checkWinner(); // Check after making the move
                return;
            }
        }
        // If no winning move, make a random move
        Random r = new Random();
        int j;
        do {
            j = r.nextInt(9);
        } while (num[j] == 'X' || num[j] == 'O');
        num[j] = computer;
        buttons[j].setIcon(computericon);
        checkWinner(); // Check after making the move
    }

    public boolean checkWinner() {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // check rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // check columns
                {0, 4, 8}, {2, 4, 6} // check diagonals
        };
        for (int[] combo : winningCombinations) {
            if (num[combo[0]] == num[combo[1]] && num[combo[1]] == num[combo[2]]) {
                if (num[combo[0]] == player) {
                    playerwin = true;
                    if (ptop) {
                        WinningLine(combo);
                        JOptionPane.showMessageDialog(null, "Player 1 wins :)");
                    } else {
                        WinningLine(combo);
                        JOptionPane.showMessageDialog(null, "You win :)");
                    }
                    return true;
                } else if (num[combo[0]] == computer) {
                    compwin = true;
                    if (ptop) {
                        WinningLine(combo);
                        JOptionPane.showMessageDialog(null, "Player 2 wins :)");
                    } else {
                        WinningLine(combo);
                        JOptionPane.showMessageDialog(null, "Computer Wins >_<");
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void WinningLine(int[] combo) {
        if (RowWin(combo)) {
            // Winning combination is in a row
            for (int index : combo) {
                if (playerwin) {
                    buttons[index].setIcon(new ImageIcon(player == 'X' ? "Xwin.png" : "Owin.png"));
                } else if (compwin) {
                    buttons[index].setIcon(new ImageIcon(computer == 'X' ? "Xwin.png" : "Owin.png"));
                }
            }
        } else if (ColumnWin(combo)) {
            // Winning combination is in a column
            for (int index : combo) {
                if (playerwin) {
                    buttons[index].setIcon(new ImageIcon(player == 'X' ? "Xwincolm.png" : "Owincolm.png"));
                } else if (compwin) {
                    buttons[index].setIcon(new ImageIcon(computer == 'X' ? "Xwincolm.png" : "Owincolm.png"));
                }
            }
        } else if (DiagonalrightWin(combo)) {
            // Winning combination is in a diagonal
            for (int index : combo) {
                if (playerwin) {
                    buttons[index].setIcon(new ImageIcon(player == 'X' ? "XwinDR.png" : "OwinDR.png"));
                } else if (compwin) {
                    buttons[index].setIcon(new ImageIcon(computer == 'X' ? "XwinDR.png" : "OwinDR.png"));
                }
            }
        } else if (DiagonalleftWin(combo)) {
            // Winning combination is in a diagonal
            for (int index : combo) {
                if (playerwin) {
                    buttons[index].setIcon(new ImageIcon(player == 'X' ? "XwinDL.png" : "OwinDL.png"));
                } else if (compwin) {
                    buttons[index].setIcon(new ImageIcon(computer == 'X' ? "XwinDL.png" : "OwinDL.png"));
                }
            }
        }
    }

    public boolean RowWin(int[] combo) {
        return (combo[0] / 3 == combo[1] / 3) && (combo[1] / 3 == combo[2] / 3);
    }

    public boolean ColumnWin(int[] combo) {
        return (combo[0] % 3 == combo[1] % 3) && (combo[1] % 3 == combo[2] % 3);
    }

    public boolean DiagonalrightWin(int[] combo) {
        return (combo[0] == 0 && combo[1] == 4 && combo[2] == 8);
    }

    public boolean DiagonalleftWin(int[] combo) {
        return (combo[0] == 2 && combo[1] == 4 && combo[2] == 6);
    }

    public boolean checkTie() {
        for (int i = 0; i < 9; i++) {
            if (num[i] != 'X' && num[i] != 'O') {
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "Game Draw :(");
        return true;
    }
}
