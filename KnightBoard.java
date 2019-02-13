public class KnightBoard {

  private int[] board;

  public KnightBoard(int startingRows,int startingCols){
    if (startingRows < 0 || startingCols < 0){
      throw new IllegalArgumentException("Negative Parameters");
    }

  }

}
