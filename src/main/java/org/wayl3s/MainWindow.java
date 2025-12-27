package org.wayl3s;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private Board board;

    public MainWindow() throws HeadlessException {
        this.setTitle("HexogonalChess -- ESC to reset");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setResizable(false);

        this.setPreferredSize(new Dimension(600, 600));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        board = new Board(100);
        
        this.add(board, gbc);
        this.pack();
        // this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}