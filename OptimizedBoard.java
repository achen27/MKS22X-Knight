import java.util.*;
import java.util.ArrayList;

public class OptimizedBoard{
  private int moves;
  private int x;
  private int y;


  public OptimizedBoard(int[][] movesBoard, int row, int col){
    moves = movesBoard[row][col];
    x = row;
    y = col;
  }
}
