package org.wayl3s;

import java.awt.Point;
import java.util.ArrayList;

public class ChessMove {
    public static ArrayList<Point> getLegalMoves(Point pos, ChessPiece chessPiece, ArrayList<ChessPiece[]> grid) {
        ArrayList<Point> legalMoves = new ArrayList<>();
        Point temp;
        switch (chessPiece.piece) {
            case PAWN:
                if (chessPiece.color == ChessColor.WHITE) {
                    temp = moveForward(pos);
                    if (!isInGrid(temp)) {
                        return legalMoves;
                    }
                    if (grid.get(temp.x)[temp.y] == null) {
                        legalMoves.add(temp);
                        temp = moveForward(temp);
                        if (grid.get(temp.x)[temp.y] == null && Board.whitePawnStartPositions.contains(pos)) {
                            legalMoves.add(temp);
                        }
                    }
                    temp = moveTopLeft(pos);
                    if (isInGrid(temp)) {
                        if (grid.get(temp.x)[temp.y] != null && grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                    }
                    temp = moveTopRight(pos);
                    if (isInGrid(temp)) {
                        if (grid.get(temp.x)[temp.y] != null && grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                    }
                }
                if (chessPiece.color == ChessColor.BLACK) {
                    temp = moveBackward(pos);
                    if (!isInGrid(temp)) {
                        return legalMoves;
                    }
                    if (grid.get(temp.x)[temp.y] == null) {
                        legalMoves.add(temp);
                        temp = moveBackward(temp);
                        if (grid.get(temp.x)[temp.y] == null && Board.blackPawnStartPositions.contains(pos)) {
                            legalMoves.add(temp);
                        }
                    }
                    temp = moveBottomLeft(pos);
                    if (isInGrid(temp)) {
                        if (grid.get(temp.x)[temp.y] != null && grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                    }
                    temp = moveBottomRight(pos);
                    if (isInGrid(temp)) {
                        if (grid.get(temp.x)[temp.y] != null && grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                    }
                }
                break;
            case ROOK:
                temp = moveForward(pos);
                while (isInGrid(temp)) {
                    if (grid.get(temp.x)[temp.y] != null) {
                        if (grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                        break;
                    } else {
                        legalMoves.add(temp);
                    }
                    temp = moveForward(temp);
                }
                temp = moveBackward(pos);
                while (isInGrid(temp)) {
                    if (grid.get(temp.x)[temp.y] != null) {
                        if (grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                        break;
                    } else {
                        legalMoves.add(temp);
                    }
                    temp = moveBackward(temp);
                }
                temp = moveTopRight(pos);
                while (isInGrid(temp)) {
                    if (grid.get(temp.x)[temp.y] != null) {
                        if (grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                        break;
                    } else {
                        legalMoves.add(temp);
                    }
                    temp = moveTopRight(temp);
                }
                temp = moveTopLeft(pos);
                while (isInGrid(temp)) {
                    if (grid.get(temp.x)[temp.y] != null) {
                        if (grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                        break;
                    } else {
                        legalMoves.add(temp);
                    }
                    temp = moveTopLeft(temp);
                }
                temp = moveBottomRight(pos);
                while (isInGrid(temp)) {
                    if (grid.get(temp.x)[temp.y] != null) {
                        if (grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                        break;
                    } else {
                        legalMoves.add(temp);
                    }
                    temp = moveBottomRight(temp);
                }
                temp = moveBottomLeft(pos);
                while (isInGrid(temp)) {
                    if (grid.get(temp.x)[temp.y] != null) {
                        if (grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                        break;
                    } else {
                        legalMoves.add(temp);
                    }
                    temp = moveBottomLeft(temp);
                }
                break;
            case BISHOP:
                temp = moveTopRight(moveForward(pos));
                while (isInGrid(temp)) {
                    if (grid.get(temp.x)[temp.y] != null) {
                        if (grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                        break;
                    } else {
                        legalMoves.add(temp);
                    }
                    temp = moveTopRight(moveForward(temp));
                }
                temp = moveTopLeft(moveForward(pos));
                while (isInGrid(temp)) {
                    if (grid.get(temp.x)[temp.y] != null) {
                        if (grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                        break;
                    } else {
                        legalMoves.add(temp);
                    }
                    temp = moveTopLeft(moveForward(temp));
                }
                temp = moveBottomRight(moveBackward(pos));
                while (isInGrid(temp)) {
                    if (grid.get(temp.x)[temp.y] != null) {
                        if (grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                        break;
                    } else {
                        legalMoves.add(temp);
                    }
                    temp = moveBottomRight(moveBackward(temp));
                }
                temp = moveBottomLeft(moveBackward(pos));
                while (isInGrid(temp)) {
                    if (grid.get(temp.x)[temp.y] != null) {
                        if (grid.get(temp.x)[temp.y].color != chessPiece.color) {
                            legalMoves.add(temp);
                        }
                        break;
                    } else {
                        legalMoves.add(temp);
                    }
                    temp = moveBottomLeft(moveBackward(temp));
                }
                break;
            case KNIGHT:
                
                break;
            case QUEEN:
                
                break;
            case KING:
                
                break;
            default:
                return null;
        }
        return legalMoves;
    }

    private static boolean isInGrid(Point pos) {
        if (pos.x < 11 && pos.x >= 0 && pos.y >= 0) {
            return pos.y < Board.sizes[pos.x];
        }
        return false;
    }

    private static Point moveForward(Point pos) {
        return new Point(pos.x - 1, pos.y - (1 - pos.x/6));
    }

    private static Point moveBackward(Point pos) {
        return new Point(pos.x + 1, pos.y + (1 - pos.x/5));
    }

    private static Point moveTopRight(Point pos) {
        return new Point(pos.x - 1, pos.y + pos.x/6);
    }

    private static Point moveTopLeft(Point pos) {
        return new Point(pos.x, pos.y - 1);
    }

    private static Point moveBottomRight(Point pos) {
        return new Point(pos.x, pos.y + 1);
    }

    private static Point moveBottomLeft(Point pos) {
        return new Point(pos.x + 1, pos.y - pos.x/5);
    }
}
