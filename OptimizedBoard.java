public class OptimizedBoard{
  private int moves; //stores number of outgoing moves
  private int x; //stores x coordinate
  private int y; //stores y coordinate

  public OptimizedBoard(int[][] movesBoard, int row, int col){ //constructs new object and instantiates fields
    moves = movesBoard[row][col];
    x = row;
    y = col;
  }

  public int getMoves(){ //returns outgoing moves
    return moves;
  }

  public int getX(){ //returns x coordinate
    return x;
  }

  public int getY(){ //returns y coordinate
    return y;
  }
}
