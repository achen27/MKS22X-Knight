public class OptimizedBoard{
  private int moves;
  private int x;
  private int y;

  public OptimizedBoard(int[][] movesBoard, int row, int col){
    moves = movesBoard[row][col];
    x = row;
    y = col;
  }

  public int getMoves(){
    return moves;
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }
}
