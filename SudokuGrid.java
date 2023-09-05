import javax.swing.*;
import java.awt.*;

public class SudokuGrid extends JPanel {
    private static final int GRID_SIZE = 9;
    private JTextField[][] gridCells;
    public static final Color closedCellColor = new Color(242, 120, 71);
    public static final Color openCellColor = new Color(159, 216, 92);
    public static final Font cellFont = new Font("Monospaced", Font.BOLD, 20);

    public SudokuGrid() {
        setLayout(new GridBagLayout());

        gridCells = new JTextField[GRID_SIZE][GRID_SIZE];
        Dimension cellSize = new Dimension(40, 40); // Set the desired cell size here
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                gridCells[row][col] = new JTextField();
                gridCells[row][col].setPreferredSize(cellSize); // Set the preferred size
                gridCells[row][col].setHorizontalAlignment(JTextField.CENTER);
                gridCells[row][col].setFont(cellFont);
                gbc.gridx = col;
                gbc.gridy = row;
                add(gridCells[row][col], gbc);

                // Set the background of grid cells to light gray
                gridCells[row][col].setBackground(new Color(177, 177, 177));
                gridCells[row][col].setOpaque(true);
            }
        }

        // Add gridlines to the Sudoku grid
        for (int i = 0; i < GRID_SIZE; i++) {
            if (i % 3 == 0) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    if (j % 3 == 0) {
                        gridCells[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                    } else {
                        gridCells[i][j].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
                    }
                }
            } else {
                for (int j = 0; j < GRID_SIZE; j++) {
                    if (j % 3 == 0) {
                        gridCells[i][j].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
                    } else {
                        gridCells[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
                    }
                }
            }
        }

        this.setOpaque(false);

    }

    // Implement method to fetch user input from the grid
    public boolean solvePuzzleWithVisualization() {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        // Copy user input from the gridCells to the 'board' array
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                String cellText = gridCells[row][col].getText();
                if (!cellText.isEmpty()) {
                    int num = Integer.parseInt(cellText);
                    board[row][col] = num;
                    gridCells[row][col].setBackground(closedCellColor);
                } else {
                    board[row][col] = 0; // Empty cells represented by 0 in 'board' array
                    gridCells[row][col].setBackground(openCellColor);
                }
            }
        }

        boolean isSolved = SudokuSolver.solveBoard(board);

        // Update the gridCells with the solved values
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                gridCells[row][col].setText(Integer.toString(board[row][col]));
                gridCells[row][col].setEditable(false);
            }
        }

        return isSolved;
    }

    // Clear all the numbers in the gridCells
    public void resetGrid() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                gridCells[row][col].setEditable(true);
                gridCells[row][col].setText("");
                gridCells[row][col].setBackground(new Color(177, 177, 177));
                gridCells[row][col].setOpaque(true);
            }
        }
    }
}
