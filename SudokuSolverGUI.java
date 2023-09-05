import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolverGUI extends JFrame{
    JButton startButton;
    JButton resetButton;
    SudokuGrid sudokuGrid;
    private JTextField[][] gridCells;
    public SudokuSolverGUI()
    {
        this.getContentPane().setBackground(new Color(50, 50, 50)); // Adjust the RGB values as needed
        prepareGUI();
        sudokuGrid = new SudokuGrid(); // Create an instance of SudokuGrid


        // Add the SudokuGrid component to the JFrame
        add(sudokuGrid, BorderLayout.CENTER);
        addComponents();
        this.setResizable(false);
        this.setVisible(true);
    }

    public void prepareGUI()
    {
        //preparing frame
        this.setTitle("Sudoku Puzzle Solver");
        this.setSize(550, 550);
        this.getContentPane().setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addComponents()
    {
        // Initialize the "Start" button
        startButton = new JButton("Start");
        startButton.setFocusPainted(false);
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(60, 130, 200)); // Adjust the RGB values as needed
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));


        // Initialize the "Reset" button
        resetButton = new JButton("Reset");
        resetButton.setFocusPainted(false);
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(208, 53, 66)); // Adjust the RGB values as needed
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));


        // Set up the button panel with the "Start", "Reset" buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);

        this.add(buttonPanel, BorderLayout.SOUTH);


        // Add ActionListeners to the buttons (moved from constructor to here)
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call your Sudoku solving algorithm here
                boolean isSolved = sudokuGrid.solvePuzzleWithVisualization();
                // Show pop-up message based on the result
                if (isSolved) {
                    JOptionPane.showMessageDialog(SudokuSolverGUI.this, "Solved Successfully!");
                } else {
                    JOptionPane.showMessageDialog(SudokuSolverGUI.this, "Unsolvable");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the resetGrid() method to reset the entire grid
                sudokuGrid.resetGrid();
            }
        });
    }

}
