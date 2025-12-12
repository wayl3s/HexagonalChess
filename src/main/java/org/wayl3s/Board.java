package org.wayl3s;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.Timer;

public class Board extends JComponent implements ActionListener {
    private final int TIMER_DELAY;
    private Timer timer;

    private int minWidth = 200;
    private int minHeight = 200;
    private int prefWidth = 400;
    private int prefHeight = 400;
    private final double SCALE = 0.80;

    private ArrayList<ChessPiece[]> grid = new ArrayList<ChessPiece[]>();
    private ChessColor turn = ChessColor.WHITE;

    private int selectedPieceX = 5;
    private int selectedPieceY = 5;
    
    public Board(final int timerDelay) {
        this.TIMER_DELAY = timerDelay;

        timer = new Timer(timerDelay, this);
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(prefWidth, prefHeight);
    }
    
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(minWidth, minHeight);
    }
    
    @Override
    public Dimension getMaximumSize() {
        // Return a large size or use super.getMaximumSize() for unlimited
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public void start() {
        this.turn = ChessColor.WHITE;
        
        grid.clear();

        int temp = 6;
        for (int i = 0; i < 12; i++) {
            grid.add(new ChessPiece[temp]);
            if (i < 6) {
                    temp++;
            } else {
                    temp--;
            }
        }

        grid.get(0)[0] = new ChessPiece(Pieces.BISHOP, ChessColor.BLACK);
        grid.get(1)[1] = new ChessPiece(Pieces.BISHOP, ChessColor.BLACK);
        grid.get(2)[2] = new ChessPiece(Pieces.BISHOP, ChessColor.BLACK);
        grid.get(1)[0] = new ChessPiece(Pieces.QUEEN, ChessColor.BLACK);
        grid.get(0)[1] = new ChessPiece(Pieces.KING, ChessColor.BLACK);
        grid.get(0)[2] = new ChessPiece(Pieces.KNIGHT, ChessColor.BLACK);
        grid.get(2)[0] = new ChessPiece(Pieces.KNIGHT, ChessColor.BLACK);
        grid.get(0)[3] = new ChessPiece(Pieces.ROOK, ChessColor.BLACK);
        grid.get(3)[0] = new ChessPiece(Pieces.ROOK, ChessColor.BLACK);
        grid.get(0)[4] = new ChessPiece(Pieces.PAWN, ChessColor.BLACK);
        grid.get(1)[4] = new ChessPiece(Pieces.PAWN, ChessColor.BLACK);
        grid.get(2)[4] = new ChessPiece(Pieces.PAWN, ChessColor.BLACK);
        grid.get(3)[4] = new ChessPiece(Pieces.PAWN, ChessColor.BLACK);
        grid.get(4)[4] = new ChessPiece(Pieces.PAWN, ChessColor.BLACK);
        grid.get(4)[3] = new ChessPiece(Pieces.PAWN, ChessColor.BLACK);
        grid.get(4)[2] = new ChessPiece(Pieces.PAWN, ChessColor.BLACK);
        grid.get(4)[1] = new ChessPiece(Pieces.PAWN, ChessColor.BLACK);
        grid.get(4)[0] = new ChessPiece(Pieces.PAWN, ChessColor.BLACK);

        grid.get(11)[5] = new ChessPiece(Pieces.BISHOP, ChessColor.WHITE);
        grid.get(10)[6] = new ChessPiece(Pieces.BISHOP, ChessColor.WHITE);
        grid.get(9)[7] = new ChessPiece(Pieces.BISHOP, ChessColor.WHITE);
        grid.get(11)[4] = new ChessPiece(Pieces.QUEEN, ChessColor.WHITE);
        grid.get(10)[6] = new ChessPiece(Pieces.KING, ChessColor.WHITE);
        grid.get(11)[3] = new ChessPiece(Pieces.KNIGHT, ChessColor.WHITE);
        grid.get(9)[7] = new ChessPiece(Pieces.KNIGHT, ChessColor.WHITE);
        grid.get(11)[2] = new ChessPiece(Pieces.ROOK, ChessColor.WHITE);
        grid.get(8)[8] = new ChessPiece(Pieces.ROOK, ChessColor.WHITE);
        grid.get(11)[1] = new ChessPiece(Pieces.PAWN, ChessColor.WHITE);
        grid.get(10)[2] = new ChessPiece(Pieces.PAWN, ChessColor.WHITE);
        grid.get(9)[3] = new ChessPiece(Pieces.PAWN, ChessColor.WHITE);
        grid.get(8)[4] = new ChessPiece(Pieces.PAWN, ChessColor.WHITE);
        grid.get(7)[5] = new ChessPiece(Pieces.PAWN, ChessColor.WHITE);
        grid.get(7)[6] = new ChessPiece(Pieces.PAWN, ChessColor.WHITE);
        grid.get(7)[7] = new ChessPiece(Pieces.PAWN, ChessColor.WHITE);
        grid.get(7)[8] = new ChessPiece(Pieces.PAWN, ChessColor.WHITE);
        grid.get(7)[9] = new ChessPiece(Pieces.PAWN, ChessColor.WHITE);
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        Graphics2D g = (Graphics2D) gr;
        // g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int height = getHeight();
        int width = getWidth();
        double boxWidth = SCALE * Math.min(height, width);

        Polygon polygon;
        int temp = 6;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < temp; j++) {
                double polygonX = (double) ((double) width / 2 
                - boxWidth / 2 * Math.sin(Math.PI * 60 / 180) * (i + i / 6 - j) / 5
                + boxWidth / 2 * Math.sin(Math.PI * 60 / 180) * (i - 4) / 5 * (int) (i / 6));
                double polygonY = (double) ((double) height / 2
                - Math.min(width, height) / 2
                + ((1 - SCALE) * Math.min(height, width) / 2) 
                + boxWidth / 2 * Math.cos(Math.PI * 60 / 180) * (i + i / 6 + j) / 5
                + boxWidth / 2 * Math.cos(Math.PI * 60 / 180) * (i - 6) / 5 * (int) (i / 6));
                polygon = buildCellPolygon(boxWidth / (10 * Math.sin(Math.PI * 60 / 180)), polygonX, polygonY);

                if (i == selectedPieceX && j == selectedPieceY) {
                    g.setColor(new Color(230, 250, 0));
                    g.fillPolygon(polygon);
                } else if ((i + i/6 * (i + 1) + j) % 3 == 0) {
                    g.setColor(new Color(210, 140, 69));
                    g.fillPolygon(polygon);
                } else if ((i + i/6 * (i + 1) + j) % 3 == 1) {
                    g.setColor(new Color(233, 173, 112));
                    g.fillPolygon(polygon);
                } else {
                    g.setColor(new Color(255, 207, 159));
                    g.fillPolygon(polygon);
                }

                g.setColor(Color.BLACK);
                g.drawPolygon(polygon);
            }
            if (i < 5) {
                    temp++;
            } else {
                    temp--;
            }
        }
    }

    private Polygon buildCellPolygon(double height, double centerX, double centerY) {
        Polygon polygon = new Polygon();
        
        for (int i = -3; i < 3; i++) {
            polygon.addPoint(
                (int) (centerX - height * (Math.sin(Math.PI * (30 + 60 * i) / 180)) / 2),
                (int) (centerY - height * (Math.cos(Math.PI * (30 + 60 * i) / 180)) / 2)
            );
        }

        return polygon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            if (this.getParent() != null) {
                Container parent = getParent();
                setSize(parent.getWidth() - 20, parent.getHeight() - 20);
                revalidate();
            }
            repaint();
        }
    }
}


//      |b|_|_|_|_|_|
//     |_|_|_|_|_|_|_|
//    |_|_|_|_|_|_|_|_|
//   |_|_|_|_|_|_|_|_|_|
//  |_|_|_|_|_|_|_|_|_|_|
// |_|_|_|_|_|_|_|_|_|_|_|
//  |_|_|_|_|_|_|_|_|_|_|
//   |_|_|_|_|_|_|_|_|_|
//    |_|_|_|_|_|_|_|_|
//     |_|_|_|_|_|_|_|
//      |_|_|_|_|_|w|
