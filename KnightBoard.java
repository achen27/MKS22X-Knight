public class KnightBoard {

  private int[][] board;

  public KnightBoard(int startingRows,int startingCols){
    if (startingRows <= 0 || startingCols <= 0){
      throw new IllegalArgumentException("Negative Parameters");
    }
    board = new int[startingRows][startingCols];
    for (int i = 0; i < startingRows; i++){
      for (int j = 0; j < startingCols; j++){
        board[i][j] = 0;
      }
    }
  }

  public String toString(){
    String s = "";
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[0].length; j++){
        if (board.length*board[0].length >= 10){
          if (board[i][j] < 10){
            if (board[i][j] == 0) {
              s += " _ ";
            } else {
              s += " " + board[i][j]+" ";
            }
          }
        }
        if (board[i][j] == 0) {
          s += "_ ";
        } else {
          s += board[i][j]+" ";
        }
      }
      s += "\n";
    }
    return s;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  *@return true when the board is solvable from the specified starting position
  */
  public boolean solve(int startingRow, int startingCol){
    return solveH(startingRow, startingCol, 1);
  }

  private boolean solveH(int row ,int col, int level){
    if (level > board.length * board[0].length){
      return true;
    }
    if (row > board.length - 1 || col > board.length - 1){
      return false;
    }
    if (row < 0 || col  < 0){
      return false;
    }
    if (board[row][col] != 0){
      return false;
    }
    board[row][col] = level;
    for (int i = -2; i < 3; i++){
      for (int j = -2; j < 3; j++){
        if (i != 0 && j != 0){
          return solveH(row+i,col+j,level+1);
        }
      }
    }
    return false;
  }

  public static void main(String[] args){
    KnightBoard test = new KnightBoard(3,3);
    System.out.println(test);
  }

}
