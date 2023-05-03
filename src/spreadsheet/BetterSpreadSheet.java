package spreadsheet;

/**
 * assigns a single value to a rectangular block of cells in a spreadsheet.
 */

public interface BetterSpreadSheet extends SpreadSheet {

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
  void setInBulk(int sX, int sY, int eX, int eY, double value) throws IllegalArgumentException;

}
