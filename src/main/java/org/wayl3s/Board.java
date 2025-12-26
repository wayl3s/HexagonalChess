package org.wayl3s;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.Timer;

public class Board extends JComponent implements ActionListener, MouseListener {
    // WINDOW PARAMETERS
    private int minWidth = 200;
    private int minHeight = 200;
    private int prefWidth = 400;
    private int prefHeight = 400;
    private final double SCALE = 0.80;
    private double boxWidth = SCALE * Math.min(prefWidth, prefHeight);
    private Timer timer;

    // GAME STATE
    private ArrayList<ChessPiece[]> grid = new ArrayList<ChessPiece[]>();
    private ChessColor turn = ChessColor.WHITE;
    private Point selectedPiece = null;
    private boolean isSelected = false;
    private ArrayList<Point> legalMoves = new ArrayList<>();

    // FINAL VARIABLES
    public static final int[] sizes = {6, 7, 8, 9, 10, 11, 10, 9, 8, 7, 6};
    public static final ArrayList<Point> whitePawnStartPositions = new ArrayList<>(Arrays.asList(new Point[]{
        new Point(10, 1), new Point(9, 2), new Point(8, 3),
        new Point(7, 4), new Point(6, 5), new Point(6, 6),
        new Point(6, 7), new Point(6, 8), new Point(6, 9)
    }));
    public static final ArrayList<Point> blackPawnStartPositions = new ArrayList<>(Arrays.asList(new Point[]{
        new Point(0, 4), new Point(1, 4), new Point(2, 4),
        new Point(3, 4), new Point(4, 4), new Point(4, 3),
        new Point(4, 2), new Point(4, 1), new Point(4, 0)
    }));
    
    public Board(int timerDelay) {
        timer = new Timer(timerDelay, this);
        timer.start();

        addMouseListener(this);

        this.start();
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

        for (int i = 0; i < 11; i++) {
            grid.add(new ChessPiece[sizes[i]]);
        }

        grid.get(0)[0] = new ChessPiece(ChessPieces.BISHOP, ChessColor.BLACK);
        grid.get(1)[1] = new ChessPiece(ChessPieces.BISHOP, ChessColor.BLACK);
        grid.get(2)[2] = new ChessPiece(ChessPieces.BISHOP, ChessColor.BLACK);
        grid.get(1)[0] = new ChessPiece(ChessPieces.QUEEN, ChessColor.BLACK);
        grid.get(0)[1] = new ChessPiece(ChessPieces.KING, ChessColor.BLACK);
        grid.get(0)[2] = new ChessPiece(ChessPieces.KNIGHT, ChessColor.BLACK);
        grid.get(2)[0] = new ChessPiece(ChessPieces.KNIGHT, ChessColor.BLACK);
        grid.get(0)[3] = new ChessPiece(ChessPieces.ROOK, ChessColor.BLACK);
        grid.get(3)[0] = new ChessPiece(ChessPieces.ROOK, ChessColor.BLACK);
        grid.get(0)[4] = new ChessPiece(ChessPieces.PAWN, ChessColor.BLACK);
        grid.get(1)[4] = new ChessPiece(ChessPieces.PAWN, ChessColor.BLACK);
        grid.get(2)[4] = new ChessPiece(ChessPieces.PAWN, ChessColor.BLACK);
        grid.get(3)[4] = new ChessPiece(ChessPieces.PAWN, ChessColor.BLACK);
        grid.get(4)[4] = new ChessPiece(ChessPieces.PAWN, ChessColor.BLACK);
        grid.get(4)[3] = new ChessPiece(ChessPieces.PAWN, ChessColor.BLACK);
        grid.get(4)[2] = new ChessPiece(ChessPieces.PAWN, ChessColor.BLACK);
        grid.get(4)[1] = new ChessPiece(ChessPieces.PAWN, ChessColor.BLACK);
        grid.get(4)[0] = new ChessPiece(ChessPieces.PAWN, ChessColor.BLACK);

        grid.get(10)[5] = new ChessPiece(ChessPieces.BISHOP, ChessColor.WHITE);
        grid.get(9)[5] = new ChessPiece(ChessPieces.BISHOP, ChessColor.WHITE);
        grid.get(8)[5] = new ChessPiece(ChessPieces.BISHOP, ChessColor.WHITE);
        grid.get(10)[4] = new ChessPiece(ChessPieces.QUEEN, ChessColor.WHITE);
        grid.get(9)[6] = new ChessPiece(ChessPieces.KING, ChessColor.WHITE);
        grid.get(10)[3] = new ChessPiece(ChessPieces.KNIGHT, ChessColor.WHITE);
        grid.get(8)[7] = new ChessPiece(ChessPieces.KNIGHT, ChessColor.WHITE);
        grid.get(10)[2] = new ChessPiece(ChessPieces.ROOK, ChessColor.WHITE);
        grid.get(7)[8] = new ChessPiece(ChessPieces.ROOK, ChessColor.WHITE);
        grid.get(10)[1] = new ChessPiece(ChessPieces.PAWN, ChessColor.WHITE);
        grid.get(9)[2] = new ChessPiece(ChessPieces.PAWN, ChessColor.WHITE);
        grid.get(8)[3] = new ChessPiece(ChessPieces.PAWN, ChessColor.WHITE);
        grid.get(7)[4] = new ChessPiece(ChessPieces.PAWN, ChessColor.WHITE);
        grid.get(6)[5] = new ChessPiece(ChessPieces.PAWN, ChessColor.WHITE);
        grid.get(6)[6] = new ChessPiece(ChessPieces.PAWN, ChessColor.WHITE);
        grid.get(6)[7] = new ChessPiece(ChessPieces.PAWN, ChessColor.WHITE);
        grid.get(6)[8] = new ChessPiece(ChessPieces.PAWN, ChessColor.WHITE);
        grid.get(6)[9] = new ChessPiece(ChessPieces.PAWN, ChessColor.WHITE);
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        Graphics2D g = (Graphics2D) gr;
        // g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int height = getHeight();
        int width = getWidth();
        boxWidth = SCALE * Math.min(height, width);

        Polygon polygon;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < sizes[i]; j++) {
                double polygonX = (double) ((double) width / 2 
                    - boxWidth / 2 * Math.sin(Math.PI * 60 / 180) * (i + i / 6 - j) / 5
                    + boxWidth / 2 * Math.sin(Math.PI * 60 / 180) * (i - 4) / 5 * (int) (i / 6)
                );
                double polygonY = (double) ((double) height / 2
                    - Math.min(width, height) / 2
                    + ((1 - SCALE) * Math.min(height, width) / 2) 
                    + boxWidth / 2 * Math.cos(Math.PI * 60 / 180) * (i + i / 6 + j) / 5
                    + boxWidth / 2 * Math.cos(Math.PI * 60 / 180) * (i - 6) / 5 * (int) (i / 6)
                );
                polygon = buildCellPolygon(boxWidth / (10 * Math.sin(Math.PI * 60 / 180)), polygonX, polygonY);

                if ((isSelected && i == selectedPiece.x && j == selectedPiece.y) || legalMoves.contains(new Point(i, j))) {
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

                // if (legalPoints.contains(new Point(i, j))) {
                //     g.setColor(Color.BLACK);
                //     g.fillOval((int) (polygonX - boxWidth/120), (int) (polygonY - boxWidth/120), (int) boxWidth/60, (int) boxWidth/60);
                // }
                
                if (grid.get(i)[j] != null) {
                    if (grid.get(i)[j].color == ChessColor.WHITE){
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    g.fillRect((int) polygonX, (int) polygonY, 5, 5);
                }

                g.setColor(Color.BLACK);
                g.drawPolygon(polygon);
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

    public void getSelectedPiece(int x, int y) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            int height = getHeight();
            int width = getWidth();
            boolean pressed = false;
            boxWidth = SCALE * Math.min(height, width);
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < sizes[i]; j++) {
                    double distance = Point.distance(
                        e.getX(),
                        e.getY(),
                        (double) ((double) width / 2 
                        - boxWidth / 2 * Math.sin(Math.PI * 60 / 180) * (i + i / 6 - j) / 5
                        + boxWidth / 2 * Math.sin(Math.PI * 60 / 180) * (i - 4) / 5 * (int) (i / 6)),
                        (double) ((double) height / 2
                        - Math.min(width, height) / 2
                        + ((1 - SCALE) * Math.min(height, width) / 2) 
                        + boxWidth / 2 * Math.cos(Math.PI * 60 / 180) * (i + i / 6 + j) / 5
                        + boxWidth / 2 * Math.cos(Math.PI * 60 / 180) * (i - 6) / 5 * (int) (i / 6))
                    );
                    if (distance < boxWidth / 20) {
                        if (legalMoves.contains(new Point(i, j))) {
                            grid.get(i)[j] = grid.get(selectedPiece.x)[selectedPiece.y];
                            grid.get(selectedPiece.x)[selectedPiece.y] = null;
                            turn = turn == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;
                        } else if (grid.get(i)[j] != null && grid.get(i)[j].color == turn) {
                            selectedPiece = new Point(i, j);
                            pressed = true;
                            isSelected = true;
                            legalMoves = ChessMove.getLegalMoves(selectedPiece, grid.get(i)[j], grid);
                            // legalPoints = ChessMove.getLegalMoves(selectedPiece, ChessPieces.ROOK, ChessColor.WHITE, grid);
                        }
                    }
                }
            }
            if (pressed == false) {
                legalMoves.clear();
                isSelected = false;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}
}
