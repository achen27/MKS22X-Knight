import java.util.Collections.*;
import java.util.ArrayList;

public class OptimizedBoard{
  private ArrayList<int[]> order;
  private int[] x;
  private int[] y;

  public OptimizedBoard(int[][] moves, int row, int col){
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
    for(int i = 0; i < 8; i++){
      int[] x = new int[3];
      x[0] = moves[row][col];
      x[1] = row + x[i];
      x[2] = col + y[i];
      order.add(x);
    }
  }
}
