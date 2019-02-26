import java.util.*;
import java.util.ArrayList;

public class KnightBoard {

  private int[][] board; //keeps track of knight moves
  private int[][] moves; //keeps track of number of outgoing moves from each square
  private int[] x; //keeps track of rows (8 ways)
  private int[] y; //keeps track of cols (8 ways)

  public KnightBoard(int startingRows,int startingCols){
    x = new int[8]; //initializes rows (8 ways)
    y = new int[8]; //initializes rows (8 ways)
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
    if (startingRows <= 0 || startingCols <= 0){ //exception for negative numbers
      throw new IllegalArgumentException("Negative Parameters");
    }
    board = new int[startingRows][startingCols];
    for (int i = 0; i < startingRows; i++){ //fills board with zeros
      for (int j = 0; j < startingCols; j++){
        board[i][j] = 0;
      }
    }

    moves = new int[startingRows][startingCols];
    for (int i = 0; i < startingRows; i++){ //fills outgoing moves board with proper numbers
      if (i == 0 || i == startingRows - 1){ //first and last rows have pattern 2 3 4 ... 4 3 2
        for (int j = 0; j < startingCols; j++){
          if (j == 0 || j == startingCols - 1) {
            moves[i][j] = 2;
          } else if (j == 1 || j == startingCols - 2){
            moves[i][j] = 3;
          } else {
            moves[i][j] = 4;
          }
        }
      } else if (i == 1 || i == startingRows - 2){ //second and second to last rows have pattern 3 4 6 ... 6 4 3
        for (int j = 0; j < startingCols; j++){
          if (j == 0 || j == startingCols - 1) {
            moves[i][j] = 3;
          } else if (j == 1 || j == startingCols - 2){
            moves[i][j] = 4;
          } else {
            moves[i][j] = 6;
          }
        }
      } else { //other rows have pattern 4 6 8 ... 8 6 4
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

  public String getBoard(){ //debugging method to check what outgoing moves board looks like
    String s = "";
    for (int i = 0; i < moves.length; i++){
      for (int j = 0; j < moves[0].length; j++){
        s += moves[i][j] + " ";
      }
      s += "\n";
    }
    return s;
  }

  public String toString(){ //method to print what board looks like
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
    for (int i = 0; i < board.length; i++){ //checks to make sure board starts empty
      for (int j = 0; j < board[0].length; j++){
        if (board[i][j] != 0){
          throw new IllegalStateException("board contains non-zero values");
        }
      }
    }
    if (startingRow < 0 || startingRow > board.length - 1 || startingCol < 0 || startingCol > board[0].length - 1){
      throw new IllegalArgumentException(); //checks to make sure parameters are within the board
    }
    return solveH(startingRow, startingCol, 1); //helper function
  }

  private boolean addKnight(int row ,int col, int level){ //helper function to add a knight on a square
    if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1){
      return false; //checks to make sure parameters are within the board
    }
    if (board[row][col] == 0){ //checks to make sure square is empty
      board[row][col] = level;
      return true;
    }
    return false; //square already has a knight
  }

  private boolean removeKnight(int row ,int col){ //helper function to remove a knight from a square
    if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1){
      return false; //checks to make sure parameters are within the board
    }
    board[row][col] = 0; //sets square to 0 to remove knight
    return true;
  }

  private void updateMoves(int row, int col, int change){ //helper method to modify outgoing moves board
    for (int i = 0; i < 8 ; i++){
      if(!(row + x[i] < 0 || row + x[i] > board.length - 1 || col + y[i] < 0 || col + y[i] > board[0].length - 1)){ //checks to make sure parameters are within the board
        if (change == 0){ //when a knight was added
          moves[row + x[i]][col + y[i]]--; //subtract one from surrounding squares
        } else { //when a knight was removed
          moves[row + x[i]][col + y[i]]++; //add one to surrounding squares
        }
      }
    }
  }

  private void sortOrder(ArrayList<OptimizedBoard> o){ //helper method to sort order of moves (insertion sort)
    for (int i = 1; i < o.size(); i++){
      OptimizedBoard temp = o.get(i); //holds temporary value
      int j = i;
      while (j > 0 && o.get(j-1).getMoves() > temp.getMoves()){ //shift until the number on the left is not larger
        o.set(j, o.get(j-1)); //moves value to proper position
        j--;
      }
      o.set(j,temp); //places old value to where new value was
    }
  }

  private boolean solveH(int row ,int col, int level){ //helper method for solve
    if (level >= board.length * board[0].length){ //board has been filled
      return true;
    }
    if (addKnight(row,col,level)){ //adds a knight a current position if possible
      updateMoves(row,col,0); //updates outgoing moves board if knight was added
    }
    ArrayList<OptimizedBoard> o = new ArrayList<OptimizedBoard>(); //List of objects
    for (int i = 0; i < 8 ; i++){ //all 8 ways to move a knight
      int r = row + x[i];
      int c = col + y[i];
      if (!(r < 0 || r > board.length - 1 || c < 0 || c > board[0].length - 1)){ //checks if move is within board
        OptimizedBoard square = new OptimizedBoard(moves, r, c); //creates new object with that square
        o.add(square); //adds object to list
      }
    }
    sortOrder(o); //sorts list from least to most number of outgoing moves
    for (int i = 0; i < o.size(); i++){ //loops through list
      if(addKnight(o.get(i).getX(),o.get(i).getY(),level+1)){ //checks to see if knight can be added
        updateMoves(o.get(i).getX(),o.get(i).getY(),0); //updates outgoing moves board
        if(solveH(o.get(i).getX(),o.get(i).getY(),level+1)){ //recursion
          return true;
        } else {
          removeKnight(o.get(i).getX(),o.get(i).getY()); //removes knight from square
          updateMoves(o.get(i).getX(),o.get(i).getY(),1); //updates outgoing moves board
        }
      }
    }
    removeKnight(row,col); //removes knight from square
    return false;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  *@return the number of solutions from the starting position specified
  */
  public int countSolutions(int startingRow, int startingCol){
    for (int i = 0; i < board.length; i++){ //checks to make sure board starts empty
      for (int j = 0; j < board[0].length; j++){
        if (board[i][j] != 0){
          throw new IllegalStateException("board contains non-zero values");
        }
      }
    }
    if (startingRow < 0 || startingRow > board.length - 1 || startingCol < 0 || startingCol > board[0].length - 1){
      throw new IllegalArgumentException(); //checks to make sure parameters are within the board
    }
    return countH(startingRow, startingCol, 1, 0); //helper function
  }

  private int countH(int row ,int col, int level, int count){ //helper function for count
    if (level >= board.length * board[0].length){ //one permutation found
      count++; //count increases by one
      return count;
    }
    addKnight(row,col,level); //add knight to board
    for (int i = 0; i < 8 ; i++){ //checks all possible moves
      if(addKnight(row + x[i],col + y[i],level+1)){ //checks to see if knight can be added
        count = countH(row + x[i],col + y[i],level+1,count); //recursion
        removeKnight(row + x[i],col + y[i]); //removes knight from board
      }
    }
    removeKnight(row,col); //removes knight from board
    return count;
  }

  public static void main(String[] args){
    KnightBoard test = new KnightBoard(5,6);
    System.out.println(test.getBoard());

    System.out.println(test.countSolutions(0,0));
    System.out.println(test);
    //System.out.println(test.getBoard());

    /*System.out.println(test.addKnight(0,4,1));
    System.out.println(test);

    System.out.println(test.addKnight(0,5,2));
    System.out.println(test);

    test.removeKnight(0,4);
    System.out.println(test);*/

  }

}
