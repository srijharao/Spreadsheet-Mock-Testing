package spreadsheet;

/**
 * bettersparsespreadsheet.
 */
public class BetterSparseSpreadSheet extends SparseSpreadSheet implements BetterSpreadSheet {

  /**
   * bulk assign a value to a range of cells.
   *
   * @param sX    the starting x-coordinate of the block (must be non-negative).
   * @param sY    the starting y-coordinate of the block (must be non-negative).
   * @param eX    the ending x-coordinate of the block (must be non-negative and greater than or
   *              equal to sX).
   * @param eY    the ending y-coordinate of the block (must be non-negative and greater than or
   *              equal to sY).
   * @param value the value to assign
   * @throws IllegalArgumentException if sX or sY is negative, or if eX or eY is negative or less *
   *                                  than sX or sY.
   */
  @Override
  public void setInBulk(int sX, int sY, int eX, int eY, double value)
      throws IllegalArgumentException {
    if (sX < 0 || sY < 0) {
      throw new IllegalArgumentException("Source start row or column can't be negative");
    }
    if (eX < 0 || eY < 0) {
      throw new IllegalArgumentException("Source end row or column can't be negative");
    }
    if (eX < sX || eY < sY) {
      throw new IllegalArgumentException(
          "Starting cell can't have its x or y less than that of ending cell");
    }

    for (int r = sX; r < eX; r++) {
      for (int c = sY; c < eY; c++) {
        this.set(r, c, value);
      }
    }

  }
}
