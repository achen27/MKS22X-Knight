public class KnightBoard {
  public KnightBoard(int startingRows,int startingCols){
    if (startingRows < 0 || startingCols < 0){
      throw new IllegalArgumentException("Negative Parameters");
    }
  }
}
