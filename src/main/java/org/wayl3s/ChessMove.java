package org.wayl3s;

import java.awt.Point;
import java.util.ArrayList;

public class ChessMove {
    
    public static Point moveBottomLeft(Point pos) {
        return new Point(pos.x + 1, pos.y - pos.x/5);
    }

    public static Point moveForward(Point pos) {
        return new Point(pos.x - 1, pos.y - (1 - pos.x/6));
    }

    public static Point moveBackward(Point pos) {
        return new Point(pos.x + 1, pos.y + (1 - pos.x/5));
    }

    public static Point moveTopRight(Point pos) {
        return new Point(pos.x - 1, pos.y + pos.x/6);
    }

    public static Point moveTopLeft(Point pos) {
        return new Point(pos.x, pos.y - 1);
    }

    public static Point moveBottomRight(Point pos) {
        return new Point(pos.x, pos.y + 1);
    }
    
    public static ArrayList<Point> getLegalMoves(Point pos, ChessPiece chessPiece, ArrayList<ChessPiece[]> grid, Point enPassantPawn) {
        ArrayList<Point> legalMoves = new ArrayList<>();
        Point temp;
        switch (chessPiece.piece) {
            case PAWN:
                if (chessPiece.color == ChessColor.WHITE) {
                    temp = moveForward(pos);
                    if (isInGrid(temp)) {
                        if (grid.get(temp.x)[temp.y] == null) {
                            legalMoves.add(temp);
                            temp = moveForward(temp);
                            if (isInGrid(temp)) {
                                if (grid.get(temp.x)[temp.y] == null && Board.whitePawnStartPositions.contains(pos)) {
                                    legalMoves.add(temp);
                                }
                            }
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
                    if (enPassantPawn != null) {
                        if (moveTopRight(enPassantPawn).equals(pos)) {
                            legalMoves.add(moveTopLeft(pos));
                        }
                        if (moveTopLeft(enPassantPawn).equals(pos)) {
                            legalMoves.add(moveTopRight(pos));
                        }
                    }
                }
                if (chessPiece.color == ChessColor.BLACK) {
                    temp = moveBackward(pos);
                    if (isInGrid(temp)) {
                        if (grid.get(temp.x)[temp.y] == null) {
                            legalMoves.add(temp);
                            temp = moveBackward(temp);
                            if (isInGrid(temp)) {
                                    if (grid.get(temp.x)[temp.y] == null && Board.blackPawnStartPositions.contains(pos)) {
                                        legalMoves.add(temp);
                                    }
                                }
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
                    if (enPassantPawn != null) {
                        if (moveBottomRight(enPassantPawn).equals(pos)) {
                            legalMoves.add(moveBottomLeft(pos));
                        }
                        if (moveBottomLeft(enPassantPawn).equals(pos)) {
                            legalMoves.add(moveBottomRight(pos));
                        }
                    }
                }
                break;
            case ROOK:
                temp = moveForward(pos);
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveForward(temp);
                }
                temp = moveBackward(pos);
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBackward(temp);
                }
                temp = moveTopRight(pos);
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveTopRight(temp);
                }
                temp = moveTopLeft(pos);
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveTopLeft(temp);
                }
                temp = moveBottomRight(pos);
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBottomRight(temp);
                }
                temp = moveBottomLeft(pos);
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBottomLeft(temp);
                }
                break;
            case BISHOP:
                temp = moveTopRight(moveForward(pos));
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveTopRight(moveForward(temp));
                }
                temp = moveTopLeft(moveForward(pos));
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveTopLeft(moveForward(temp));
                }
                temp = moveBottomRight(moveBackward(pos));
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBottomRight(moveBackward(temp));
                }
                temp = moveBottomLeft(moveBackward(pos));
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBottomLeft(moveBackward(temp));
                }
                temp = moveBottomLeft(moveTopLeft(pos));
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBottomLeft(moveTopLeft(temp));
                }
                temp = moveBottomRight(moveTopRight(pos));
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBottomRight(moveTopRight(temp));
                }
                break;
            case KNIGHT:
                temp = moveTopRight(moveForward(moveForward(pos)));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveTopLeft(moveForward(moveForward(pos)));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveBottomLeft(moveBackward(moveBackward(pos)));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveBottomRight(moveBackward(moveBackward(pos)));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveForward(moveTopRight(moveTopRight(pos)));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveBottomRight(moveTopRight(moveTopRight(pos)));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveTopRight(moveBottomRight(moveBottomRight(pos)));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveBackward(moveBottomRight(moveBottomRight(pos)));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveTopLeft(moveBottomLeft(moveBottomLeft(pos)));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveBackward(moveBottomLeft(moveBottomLeft(pos)));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveBottomLeft(moveTopLeft(moveTopLeft(pos)));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveForward(moveTopLeft(moveTopLeft(pos)));
                addLegal(legalMoves, chessPiece, temp, grid);
                break;
            case QUEEN:
                temp = moveForward(pos);
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveForward(temp);
                }
                temp = moveBackward(pos);
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBackward(temp);
                }
                temp = moveTopRight(pos);
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveTopRight(temp);
                }
                temp = moveTopLeft(pos);
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveTopLeft(temp);
                }
                temp = moveBottomRight(pos);
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBottomRight(temp);
                }
                temp = moveBottomLeft(pos);
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBottomLeft(temp);
                }
                temp = moveTopRight(moveForward(pos));
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveTopRight(moveForward(temp));
                }
                temp = moveTopLeft(moveForward(pos));
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveTopLeft(moveForward(temp));
                }
                temp = moveBottomRight(moveBackward(pos));
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBottomRight(moveBackward(temp));
                }
                temp = moveBottomLeft(moveBackward(pos));
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBottomLeft(moveBackward(temp));
                }
                temp = moveBottomLeft(moveTopLeft(pos));
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBottomLeft(moveTopLeft(temp));
                }
                temp = moveBottomRight(moveTopRight(pos));
                while (isInGrid(temp)) {
                    addAttacking(legalMoves, chessPiece, temp, grid);
                    if (grid.get(temp.x)[temp.y] != null) {
                        break;
                    }
                    temp = moveBottomRight(moveTopRight(temp));
                }
                break;
            case KING:
                temp = moveForward(pos);
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveBackward(pos);
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveTopRight(pos);
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveTopLeft(pos);
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveBottomRight(pos);
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveBottomLeft(pos);
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveTopRight(moveForward(pos));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveTopLeft(moveForward(pos));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveBottomRight(moveBackward(pos));
                addLegal(legalMoves, chessPiece, temp, grid);
                temp = moveBottomLeft(moveBackward(pos));
                addLegal(legalMoves, chessPiece, temp, grid);
                break;
        }
        return legalMoves;
    }

    private static boolean isInGrid(Point pos) {
        if (pos.x < 11 && pos.x >= 0 && pos.y >= 0) {
            return pos.y < Board.sizes[pos.x];
        }
        return false;
    }

    private static void addLegal(ArrayList<Point> legalMoves, ChessPiece chessPiece, Point temp, ArrayList<ChessPiece[]> grid) {
        if (isInGrid(temp)) {
            addAttacking(legalMoves, chessPiece, temp, grid);
        }
    }

    private static void addAttacking(ArrayList<Point> legalMoves, ChessPiece chessPiece, Point temp, ArrayList<ChessPiece[]> grid) {
        if (grid.get(temp.x)[temp.y] != null) {
            if (grid.get(temp.x)[temp.y].color != chessPiece.color) {
                legalMoves.add(temp);
            }
        } else {
            legalMoves.add(temp);
        }
    }
}
