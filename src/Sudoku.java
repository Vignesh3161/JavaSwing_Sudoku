import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sudoku {

    class Tile extends JButton {
        int r;
        int c;

        Tile(int r, int c) {
            this.r = r;
            this.c = c;

        }
    }

    private final int boardWidth = 600;
    private final int boardHeight = 650;
    private JFrame frame;
    private JLabel textLabel;
    private JPanel textPanel;
    private JPanel boardPanel;
    private JPanel buttonsPanel;
    private JButton numSelected = null;
    int errors = 0;

    String[] puzzle_1 = {
            "--74916-5",
            "2---6-3-9",
            "-----7-1-",
            "-586----4",
            "--3----9-",
            "--62--187",
            "9-4-7---2",
            "67-83----",
            "81--45---"
    };
    String[] solution_1 = {
            "387491625",
            "241568379",
            "659237418",
            "758619234",
            "123784596",
            "496253187",
            "934176852",
            "672835941",
            "815942763"
    };

    public Sudoku() {
        textLabel = new JLabel("SUDOKU: 0", SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.BOLD, 24));

        textPanel = new JPanel(new BorderLayout());
        textPanel.add(textLabel, BorderLayout.CENTER);

        frame = new JFrame("Sudoku");
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(9, 9));
        setUpTiles();
        frame.add(boardPanel, BorderLayout.CENTER);
        
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,9));
        setUpButtons();

        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    void setUpTiles() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Tile tile = new Tile(i, j);
                char tileChar = puzzle_1[i].charAt(j);
                if (tileChar != '-') {
                    tile.setFont(new Font("Arial", Font.BOLD, 22));
                    tile.setBackground(Color.green);
                    tile.setText(String.valueOf(tileChar));
                } else {
                    tile.setFont(new Font("Arial", Font.PLAIN, 22));
                    tile.setForeground(Color.green);
                    tile.setBackground(Color.darkGray);
                }

                if((i == 2 && j == 2) || (i == 2 && j == 5) || (i == 5 && j == 2) || ( i == 5 && j == 5)){
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 5, Color.BLACK));

                }else if (i == 2 || i == 5) {
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 1, Color.BLACK));
                }else if (j == 2 || j == 5) {
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 5, Color.BLACK));
                }else{
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                }
                tile.setFocusable(false);
                boardPanel.add(tile);
                
                tile.addActionListener(new ActionListener() {
                    public  void actionPerformed(ActionEvent e) {

                        Tile tile = (Tile) e.getSource();
                        int r = tile.r;
                        int c = tile.c;
                        if (numSelected != null) {
                            if(tile.getText() !=""){
                                return;
                            }
                            String numSelectedText = numSelected.getText();
                            String tileSolution = String.valueOf(solution_1[r].charAt(c));
                            if(tileSolution.equals(numSelectedText)){
                                tile.setText(numSelectedText);
                            }else{
                                errors += 1;
                                textLabel.setText("Sudoku: " + String.valueOf(errors));
                            }
                        }
                    }
                });
            }

        }
    }

    void setUpButtons(){
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton();
            button.setFont(new Font("Arial", Font.BOLD, 20 ));
            button.setText(String.valueOf(i));
            button.setFocusable(false);
            button.setBackground(Color.BLACK);
            button.setForeground(Color.green);
            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void  actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();

                    if(numSelected != null){
                        numSelected.setBackground(Color.BLACK);
                        numSelected.setForeground(Color.green);
                    }
                    numSelected = button;
                    numSelected.setBackground(Color.green);
                    numSelected.setForeground(Color.BLACK);
                }
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Sudoku::new);
    }
}
