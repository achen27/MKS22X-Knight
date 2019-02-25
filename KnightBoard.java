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
            s += " " + board[i][j]+" ";
          } else {
            s += board[i][j]+" ";
          }
        } else {
          if (board[i][j] == 0) {
            s += "_ ";
          } else {
            s += board[i][j]+" ";
          }
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
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[0].length; j++){
        if (board[i][j] != 0){
          throw new IllegalStateException("board contains non-zero values");
        }
      }
    }
    if (startingRow < 0 || startingRow > board.length - 1 || startingCol < 0 || startingCol > board[0].length - 1){
      throw new IllegalArgumentException();
    }
    board[startingRow][startingCol] = 1;
    return solveH(startingRow, startingCol, 1);
  }

  private boolean addKnight(int row ,int col, int level){
    if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1){
      return false;
    }
    if (board[row][col] == 0){
      board[row][col] = level;
      return true;
    }
    return false;
  }

  private boolean removeKnight(int row ,int col){
    if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1){
      return false;
    }
    board[row][col] = 0;
    return true;
  }

  private boolean solveH(int row ,int col, int level){
    if (level > board.length * board[0].length){
      //System.out.println("end reached");
      return true;
    }
    addKnight(row,col,level);
    for (int i = 0; i < 8 ; i++){

      if (i == 0){
        //System.out.println("0");
        //System.out.println(this.toString());
        if(addKnight(row+1,col+2,level+1)){
          if(solveH(row+1,col+2,level+1)){
            return true;
          } else {
            removeKnight(row+1,col+2);
          }
        }
      }

      if (i == 1){
        //System.out.println("1");
        if(addKnight(row+1,col-2,level+1)){
          if(solveH(row+1,col-2,level+1)){
            return true;
          } else {
            removeKnight(row+1,col-2);
          }
        }
      }

      if (i == 2){
        //System.out.println("2");
        if(addKnight(row+2,col+1,level+1)){
          if(solveH(row+2,col+1,level+1)){
            return true;
          } else {
            removeKnight(row+2,col+1);
          }
        }
       }

      if (i == 3){
        //System.out.println("3");
        if(addKnight(row+2,col-1,level+1)){
          if(solveH(row+2,col-1,level+1)){
            return true;
          }else {
             removeKnight(row+2,col-1);
           }
        }
      }

      if (i == 4){
        //System.out.println("4");
        if(addKnight(row-1,col+2,level+1)){
          if(solveH(row-1,col+2,level+1)){
            return true;
          } else {
            removeKnight(row-1,col+2);
          }
        }
      }

      if (i == 5){
        //System.out.println("5");
        if(addKnight(row-1,col-2,level+1)){
          if(solveH(row-1,col-2,level+1)){
            return true;
          } else {
            removeKnight(row-1,col-2);
          }
        }
      }

      if (i == 6){
        //System.out.println("6");
        if(addKnight(row-2,col+1,level+1)){
          if(solveH(row-2,col+1,level+1)){
            return true;
          } else {
            removeKnight(row-2,col+1);
          }
        }
      }

      if (i == 7){
        //System.out.println("7");
        if(addKnight(row-2,col-1,level+1)){
          if(solveH(row-2,col-1,level+1)){
            return true;
          } else {
            removeKnight(row-2,col-1);
          }
        }
      }
      //System.out.println(this.toString());
    }
    //System.out.println(col);
    removeKnight(row,col);
    return false;
  }

  public static void main(String[] args){
    KnightBoard test = new KnightBoard(5,5);
    System.out.println(test.solve(0,0));
    System.out.println(test);

    /*System.out.println(test.addKnight(0,4,1));
    System.out.println(test);

    System.out.println(test.addKnight(0,5,2));
    System.out.println(test);

    test.removeKnight(0,4);
    System.out.println(test);*/

  }

}
