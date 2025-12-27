package org.wayl3s;

import java.awt.Color;
import java.awt.Graphics2D;

public class ChessPiece {
    public ChessPieces piece;
    public ChessColor color;

    public ChessPiece(ChessPieces piece, ChessColor color) {
        this.piece = piece;
        this.color = color;
    }

    static void draw(Graphics2D g, ChessPiece chessPiece, double x, double y, double height) {
        double size = height * 0.75;
        int[] xPoints;
        int[] yPoints;
        switch (chessPiece.piece) {
            case PAWN:
                xPoints = new int[] {
                    (int)(x - size*0.45), (int)(x - size*0.4), (int)(x - size*0.1),
                    (int)(x - size*0.25), (int)(x - size*0.1), (int)(x - size*0.15),
                    (int)(x), (int)(x + size*0.15), (int)(x + size*0.1),
                    (int)(x + size*0.25), (int)(x + size*0.1), (int)(x + size*0.4),
                    (int)(x + size*0.45)
                };
                yPoints = new int[] {
                    (int)(y + size*0.5), (int)(y + size*0.3), (int)(y + size*0.15),
                    (int)(y), (int)(y - size*0.15), (int)(y - size*0.25),
                    (int)(y - size*0.4), (int)(y - size*0.25), (int)(y - size*0.15),
                    (int)(y), (int)(y + size*0.15), (int)(y + size*0.3),
                    (int)(y + size*0.5)
                };
                
                g.setColor(chessPiece.color == ChessColor.WHITE ? Color.WHITE : Color.BLACK);
                g.fillPolygon(xPoints, yPoints, xPoints.length);
                g.setColor(chessPiece.color == ChessColor.WHITE ? Color.BLACK : Color.WHITE);
                g.drawPolygon(xPoints, yPoints, xPoints.length);
                break;
            case ROOK:
                xPoints = new int[] {
                    (int)(x - size*0.35), (int)(x - size*0.25), (int)(x - size*0.25),
                    (int)(x - size*0.15), (int)(x - size*0.15), (int)(x - size*0.05),
                    (int)(x - size*0.05), (int)(x + size*0.05), (int)(x + size*0.05),
                    (int)(x + size*0.15), (int)(x + size*0.15), (int)(x + size*0.25),
                    (int)(x + size*0.25), (int)(x + size*0.35), (int)(x + size*0.3),
                    (int)(x - size*0.3)
                };
                yPoints = new int[] {
                    (int)(y - size*0.3), (int)(y - size*0.3), (int)(y - size*0.1),
                    (int)(y - size*0.1), (int)(y - size*0.3), (int)(y - size*0.3),
                    (int)(y - size*0.1), (int)(y - size*0.1), (int)(y - size*0.3),
                    (int)(y - size*0.3), (int)(y - size*0.1), (int)(y - size*0.1),
                    (int)(y - size*0.3), (int)(y - size*0.3), (int)(y + size*0.5),
                    (int)(y + size*0.5)
                };
                g.setColor(chessPiece.color == ChessColor.WHITE ? Color.WHITE : Color.BLACK);
                g.fillPolygon(xPoints, yPoints, xPoints.length);
                g.setColor(chessPiece.color == ChessColor.WHITE ? Color.BLACK : Color.WHITE);
                g.drawPolygon(xPoints, yPoints, xPoints.length);
                break;
            case BISHOP:
                xPoints = new int[] {
                    (int)(x - size*0.45), (int)(x - size*0.1), (int)(x - size*0.08),
                    (int)(x - size*0.2), (int)(x), (int)(x + size*0.2),
                    (int)(x + size*0.08), (int)(x + size*0.1), (int)(x + size*0.45)
                };
                yPoints = new int[]{
                    (int)(y + size*0.5), (int)(y + size*0.35), (int)(y + size*0.15),
                    (int)(y - size*0.15), (int)(y - size*0.4), (int)(y - size*0.15),
                    (int)(y + size*0.15), (int)(y + size*0.35), (int)(y + size*0.5)
                };
                g.setColor(chessPiece.color == ChessColor.WHITE ? Color.WHITE : Color.BLACK);
                g.fillPolygon(xPoints, yPoints, xPoints.length);
                g.setColor(chessPiece.color == ChessColor.WHITE ? Color.BLACK : Color.WHITE);
                g.drawPolygon(xPoints, yPoints, xPoints.length);
                break;
            case KNIGHT:
                xPoints = new int[] {
                    (int)(x - size*0.25), (int)(x), (int)(x - size*0.2),
                    (int)(x - size*0.28), (int)(x - size*0.25), (int)(x - size*0.30),
                    (int)(x - size*0.15), (int)(x - size*0.08), (int)(x),
                    (int)(x + size*0.15), (int)(x + size*0.4), (int)(x + size*0.4)
                };
                yPoints = new int[]{
                    (int)(y + size*0.5), (int)(y + size*0.15), (int)(y + size*0.25),
                    (int)(y + size*0.20), (int)(y - size*0.25), (int)(y - size*0.35),
                    (int)(y - size*0.30), (int)(y - size*0.45), (int)(y - size*0.35),
                    (int)(y - size*0.38), (int)(y), (int)(y + size*0.5)
                };
                g.setColor(chessPiece.color == ChessColor.WHITE ? Color.WHITE : Color.BLACK);
                g.fillPolygon(xPoints, yPoints, xPoints.length);
                g.setColor(chessPiece.color == ChessColor.WHITE ? Color.BLACK : Color.WHITE);
                g.drawPolygon(xPoints, yPoints, xPoints.length);
                break;
            case QUEEN:
                xPoints = new int[] {
                    (int)(x - size*0.4), (int)(x - size*0.1),
                    (int)(x), (int)(x + size*0.1), (int)(x + size*0.4),
                    (int)(x + size*0.3), (int)(x + size*0.4),
                    (int)(x - size*0.4), (int)(x - size*0.3)
                };
                yPoints = new int[] {
                    (int)(y - size*0.45), (int)(y),
                    (int)(y - size*0.45), (int)(y), (int)(y - size*0.45),
                    (int)(y + size*0.3), (int)(y + size*0.5),
                    (int)(y + size*0.5), (int)(y + size*0.3)
                };
                g.setColor(chessPiece.color == ChessColor.WHITE ? Color.WHITE : Color.BLACK);
                g.fillPolygon(xPoints, yPoints, xPoints.length);
                g.setColor(chessPiece.color == ChessColor.WHITE ? Color.BLACK : Color.WHITE);
                g.drawPolygon(xPoints, yPoints, xPoints.length);
                break;
            case KING:
                xPoints = new int[] {
                    (int)(x - size*0.2), (int)(x - size*0.25), (int)(x - size*0.1),
                    (int)(x), (int)(x + size*0.1), (int)(x + size*0.25),
                    (int)(x + size*0.2), (int)(x + size*0.4), (int)(x - size*0.4)
                };
                yPoints = new int[] {
                    (int)(y + size*0.1), (int)(y - size*0.35), (int)(y - size*0.38),
                    (int)(y - size*0.35), (int)(y - size*0.38), (int)(y - size*0.35),
                    (int)(y + size*0.1), (int)(y + size*0.5), (int)(y + size*0.5)
                };
                g.setColor(chessPiece.color == ChessColor.WHITE ? Color.WHITE : Color.BLACK);
                g.fillPolygon(xPoints, yPoints, xPoints.length);
                g.setColor(chessPiece.color == ChessColor.WHITE ? Color.BLACK : Color.WHITE);
                g.drawPolygon(xPoints, yPoints, xPoints.length);
                g.drawLine((int)(x), (int)(y - size*0.35),
                    (int)(x), (int)(y - size*0.5));
                g.drawLine((int)(x - size*0.08), (int)(y - size*0.45),
                    (int)(x + size*0.08), (int)(y - size*0.45));
                break;
            
        }
    }
}
