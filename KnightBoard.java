import java.util.*;
import java.util.ArrayList;

public class KnightBoard {

  private int[][] board;
  private int[][] moves;
  private int[] x;
  private int[] y;

  public KnightBoard(int startingRows,int startingCols){
    x = new int[8];
    y = new int[8];
    x[0] = 1;
    y[0] = 2;
    x[1] = 1;
    y[1] = -2;
    x[2] = 2;
    y[2] = 1;
    x[3] = 2;
    y[3] = -1;
    x[4] = -1;
    y[4] = 2;
    x[5] = -1;
    y[5] = -2;
    x[6] = -2;
    y[6] = 1;
    x[7] = -2;
    y[7] = -1;
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
          if (j == 0 || j == startingCols - 1) {
            moves[i][j] = 2;
          } else if (j == 1 || j == startingCols - 2){
            moves[i][j] = 3;
          } else {
            moves[i][j] = 4;
          }
        }
      } else if (i == 1 || i == startingRows - 2){
        for (int j = 0; j < startingCols; j++){
          if (j == 0 || j == startingCols - 1) {
            moves[i][j] = 3;
          } else if (j == 1 || j == startingCols - 2){
            moves[i][j] = 4;
          } else {
            moves[i][j] = 6;
          }
        }
      } else {
        for (int j = 0; j < startingCols; j++){
          if (j == 0 || j == startingCols - 1) {
            moves[i][j] = 4;
          } else if (j == 1 || j == startingCols - 2){
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

  private void sortOrder(ArrayList<OptimizedBoard> o){
    for (int i = 1; i < o.size(); i++){
      OptimizedBoard temp = o.get(i);
      int j = i;
      while (j > 0 && o.get(j-1).getMoves() > temp.getMoves()){//shift until the number on the left is not larger
        o.set(j, o.get(j-1));
        j--;
      }
      o.set(j,temp);
    }
  }

  private boolean solveH(int row ,int col, int level){
    if (level >= board.length * board[0].length){
      //System.out.println("end reached");
      return true;
    }
    if (addKnight(row,col,level)){
      updateMoves(row,col,0);
    }
    ArrayList<OptimizedBoard> o = new ArrayList<OptimizedBoard>();
    for (int i = 0; i < 8 ; i++){
      int r = row + x[i];
      int c = col + y[i];
      if (!(r < 0 || r > board.length - 1 || c < 0 || c > board[0].length - 1)){
        OptimizedBoard square = new OptimizedBoard(moves, r, c);
        o.add(square);
      }
    }
    sortOrder(o);
    for (int i = 0; i < 8 ; i++){
      //System.out.println(this.toString());
      //System.out.println(this.getBoard());
      //System.out.println(row + " " + col);
      if(addKnight(o.get(i).getX(),o.get(i).getY(),level+1)){
        updateMoves(o.get(i).getX(),o.get(i).getY(),0);
        if(solveH(o.get(i).getX(),o.get(i).getY(),level+1)){
          return true;
        } else {
          removeKnight(o.get(i).getX(),o.get(i).getY());
          updateMoves(o.get(i).getX(),o.get(i).getY(),1);
        }
      }
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
    KnightBoard test = new KnightBoard(30,80);
    //System.out.println(test.countSolutions(0,0));
    //System.out.println(test);
    //System.out.println(test.getBoard());

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
