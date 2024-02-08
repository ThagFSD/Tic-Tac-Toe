import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    // Main window
    int boardWidth = 600;
    int boradHeight = 650; // 50px for the text panel on top

    // jvswing lib class
    JFrame frame = new JFrame("Tic Tac Toe");
    JPanel textPanel = new JPanel();
    JLabel textLabel = new JLabel();
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[4][4];
    JButton replay = new JButton();

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        // Window setting
        frame.setVisible(true);
        frame.setSize(boardWidth, boradHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel text setting
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        // Title setting
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        //board setting
        boardPanel.setLayout(new GridLayout(4, 4, 2, 2));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        // r = row, c = column
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setForeground(Color.darkGray);
                tile.setFont(new Font("Comic Sans MS", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setCursor(new Cursor(Cursor.HAND_CURSOR));

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton tile = (JButton) e.getSource();
                        if (gameOver) {
                            // playAgain();
                            return;
                        }
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                if (currentPlayer == playerX) {
                                    tile.setForeground(Color.BLUE);
                                } else {
                                    tile.setForeground(Color.RED);
                                }
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }
        // Reset button
        JButton resetButton = new JButton();
        board[3][0] = resetButton;
        boardPanel.add(resetButton);
        resetButton.setText("Reset");
        resetButton.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetButton.setFocusable(false);

        // Action 
        resetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // JButton resetButton = (JButton) e.getSource();
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        board[r][c].setText("");
                        turns = 0;
                        // boardPanel.add(resetButton);
                    }
                }
                gameOver = false; // Reset the game over flag
            }
        });
        
    }

    // Check winner
    void checkWinner() {
        // horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "")
                continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                    board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setTheWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        // vertical
        for (int c = 0; c < 3; c++) {
            if (board[1][c].getText() == "")
                continue;

            if (board[0][c].getText() == board[1][c].getText() &&
                    board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setTheWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        // diogonally
        if (board[0][0].getText() != "" && board[0][0].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][2].getText()) {
            for (int i = 0; i < 3; i++) {
                setTheWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        // anti-diogonally
        if (board[0][2].getText() != "" && board[0][2].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][0].getText()) {
            setTheWinner(board[0][2]);
            setTheWinner(board[1][1]);
            setTheWinner(board[2][0]);
            gameOver = true;
            return;
        }

        // tie
        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;

        }

    }

    // Set the winner
    void setTheWinner(JButton tile) {
        tile.setForeground(Color.green);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    // Tie case
    void setTie(JButton tile) {
        tile.setForeground(Color.ORANGE);
        textLabel.setText("Tie!");
    }

    // void playAgain() {
    //     int choice = JOptionPane.showConfirmDialog(
    //             null,
    //             "Do you wish to play again?",
    //             (currentPlayer + " is the winner"),
    //             JOptionPane.YES_NO_OPTION);
    //     if (choice == JOptionPane.YES_OPTION) {
    //         for (int r = 0; r < 3; r++) {
    //             for (int c = 0; c < 3; c++) {
    //                 board[r][c].setText("");
    //                 turns = 0;
    //                 // boardPanel.add(tile);
    //             }
    //         }
    //         gameOver = false; // Reset the game over flag
    //     } else {
    //         System.out.println("Thanks for playing!");
    //         System.exit(0); // Exit the game
    //     }

    // }
}
