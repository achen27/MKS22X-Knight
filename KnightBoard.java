public class KnightBoard {

  private int[][] board;
  private int[][] moves;

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

    moves = new int[startingRows][startingCols];
    for (int i = 0; i < startingRows; i++){

      if (i == 0 || i == startingRows - 1){
        for (int j = 0; j < startingCols; j++){
          if (j == 0 || j == startingRows - 1) {
            moves[i][j] = 2;
          } else if (j == 1 || j == startingRows - 2){
            moves[i][j] = 3;
          } else {
            moves[i][j] = 4;
          }
        }
      } else if (i == 1 || i == startingRows - 2){
        for (int j = 0; j < startingCols; j++){
          if (j == 0 || j == startingRows - 1) {
            moves[i][j] = 3;
          } else if (j == 1 || j == startingRows - 2){
            moves[i][j] = 4;
          } else {
            moves[i][j] = 6;
          }
        }
      } else {
        for (int j = 0; j < startingCols; j++){
          if (j == 0 || j == startingRows - 1) {
            moves[i][j] = 4;
          } else if (j == 1 || j == startingRows - 2){
            moves[i][j] = 6;
          } else {
            moves[i][j] = 8;
          }
        }
      }
    }
  }

  public String getBoard(){
    String s = "";
    for (int i = 0; i < moves.length; i++){
      for (int j = 0; j < moves[0].length; j++){
        s += moves[i][j] + " ";
      }
      s += "\n";
    }
    return s;
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

  private void updateMoves(int row, int col, int change){
    for (int i = 0; i < 8 ; i++){
      if (i == 0){
        if(!(row+1 < 0 || row+1 > board.length - 1 || col+2 < 0 || col+2 > board[0].length - 1)){
          if (change == 0) {
            moves[row+1][col+2]--;
          } else {
            moves[row+1][col+2]++;
          }
        }
      }
      if (i == 1){
        if(!(row+1 < 0 || row+1 > board.length - 1 || col-2 < 0 || col-2 > board[0].length - 1)){
          if (change == 0) {
            moves[row+1][col-2]--;
          } else {
            moves[row+1][col-2]++;
          }
        }
      }
      if (i == 2){
        if(!(row+2 < 0 || row+2 > board.length - 1 || col+1 < 0 || col+1 > board[0].length - 1)){
          if (change == 0) {
            moves[row+2][col+1]--;
          } else {
            moves[row+2][col+1]++;
          }
        }
      }
      if (i == 3){
        if(!(row+2 < 0 || row+2 > board.length - 1 || col-1 < 0 || col-1 > board[0].length - 1)){
          if (change == 0) {
            moves[row+2][col-1]--;
          } else {
            moves[row+2][col-1]++;
          }
        }
      }
      if (i == 4){
        if(!(row-1 < 0 || row-1 > board.length - 1 || col+2 < 0 || col+2 > board[0].length - 1)){
          if (change == 0) {
            moves[row-1][col+2]--;
          } else {
            moves[row-1][col+2]++;
          }
        }
      }
      if (i == 5){
        if(!(row-1 < 0 || row-1 > board.length - 1 || col-2 < 0 || col-2 > board[0].length - 1)){
          if (change == 0) {
            moves[row-1][col-2]--;
          } else {
            moves[row-1][col-2]++;
          }
        }
      }
      if (i == 6){
        if(!(row-2 < 0 || row-2 > board.length - 1 || col+1 < 0 || col+1 > board[0].length - 1)){
          if (change == 0) {
            moves[row-2][col+1]--;
          } else {
            moves[row-2][col+1]++;
          }
        }
      }
      if (i == 7){
        if(!(row-2 < 0 || row-2 > board.length - 1 || col-1 < 0 || col-1 > board[0].length - 1)){
          if (change == 0) {
            moves[row-2][col-1]--;
          } else {
            moves[row-2][col-1]++;
          }
        }
      }
    }
  }

  private boolean solveH(int row ,int col, int level){
    if (level >= board.length * board[0].length){
      //System.out.println("end reached");
      return true;
    }
    addKnight(row,col,level);
    for (int i = 0; i < 8 ; i++){

      if (i == 0){
        //System.out.println("0");
        //System.out.println(this.toString());
        //System.out.println(this.getBoard());
        if(addKnight(row+1,col+2,level+1)){
          updateMoves(row+1,col+2,0);
          if(solveH(row+1,col+2,level+1)){
            return true;
          } else {
            removeKnight(row+1,col+2);
            updateMoves(row+1,col+2,1);
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
          moves[row-2][col+1] -= 1;
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
    //System.out.println(level);
    removeKnight(row,col);
    return false;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  *@return the number of solutions from the starting position specified
  */
  public int countSolutions(int startingRow, int startingCol){
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
    return countH(startingRow, startingCol, 1, 0);
  }

  private int countH(int row ,int col, int level, int count){
    if (level >= board.length * board[0].length){
      count++;
      return count;
    }
    addKnight(row,col,level);
    for (int i = 0; i < 8 ; i++){

      if (i == 0){
        //System.out.println("0");
        //System.out.println(this.toString());
        if(addKnight(row+1,col+2,level+1)){
          count = countH(row+1,col+2,level+1,count);
          removeKnight(row+1,col+2);
        }
      }

      if (i == 1){
        //System.out.println("1");
        if(addKnight(row+1,col-2,level+1)){
          count = countH(row+1,col-2,level+1,count);
          removeKnight(row+1,col-2);
        }
      }

      if (i == 2){
        //System.out.println("2");
        if(addKnight(row+2,col+1,level+1)){
          count = countH(row+2,col+1,level+1,count);
          removeKnight(row+2,col+1);
        }
       }

      if (i == 3){
        //System.out.println("3");
        if(addKnight(row+2,col-1,level+1)){
          count = countH(row+2,col-1,level+1,count);
          removeKnight(row+2,col-1);
        }
      }

      if (i == 4){
        //System.out.println("4");
        if(addKnight(row-1,col+2,level+1)){
          count = countH(row-1,col+2,level+1,count);
          removeKnight(row-1,col+2);
        }
      }

      if (i == 5){
        //System.out.println("5");
        if(addKnight(row-1,col-2,level+1)){
          count = countH(row-1,col-2,level+1,count);
          removeKnight(row-1,col-2);
        }
      }

      if (i == 6){
        //System.out.println("6");
        if(addKnight(row-2,col+1,level+1)){
          count = countH(row-2,col+1,level+1,count);
          removeKnight(row-2,col+1);
        }
      }

      if (i == 7){
        //System.out.println("7");
        if(addKnight(row-2,col-1,level+1)){
          count = countH(row-2,col-1,level+1,count);
          removeKnight(row-2,col-1);
        }
      }
      //System.out.println(this.toString());
    }
    //System.out.println(level);
    removeKnight(row,col);
    return count;
  }

  public static void main(String[] args){
    KnightBoard test = new KnightBoard(5,5);
    //System.out.println(test.countSolutions(0,0));
    System.out.println(test);
    System.out.println(test.getBoard());

    System.out.println(test.solve(0,0));
    System.out.println(test);
    System.out.println(test.getBoard());

    /*System.out.println(test.addKnight(0,4,1));
    System.out.println(test);

    System.out.println(test.addKnight(0,5,2));
    System.out.println(test);

    test.removeKnight(0,4);
    System.out.println(test);*/

  }

}
