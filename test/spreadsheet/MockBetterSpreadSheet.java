package spreadsheet;

/**
 * This class represents all the operations offered by a spreadsheet. These operations are supposed
 * to be a barebones set upon which other operations may be developed.
 */
public class MockBetterSpreadSheet implements BetterSpreadSheet {

  public StringBuilder log;

  /**
   * constructor to assign log.
   *
   * @param log logger
   */
  public MockBetterSpreadSheet(StringBuilder log) {
    this.log = log;
  }

  /**
   * Get the number at the specified cell.
   *
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return 10.0 always
   * @throws IllegalArgumentException if the row or column are negative
   */
  public double get(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException();
    }
    log.append("get successfully called with row: ").append(row).append(" column: ").append(col);
    return 10.0;
  }

  /**
   * Set the value of the specified cell to the specified value.
   *
   * @param row   the row number of the cell, starting with 0
   * @param col   the column number of the cell, starting at 0
   * @param value the value that this cell must be set to
   * @throws IllegalArgumentException if the row or column are negative
   */
  public void set(int row, int col, double value) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException();
    }
    log.append("set successfully called with row: ").append(row).append(" column: ").append(col)
        .append(" value:").append(value);
  }


  /**
   * Returns whether the specified cell is empty.
   *
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return false always
   * @throws IllegalArgumentException if the row or column are negative
   */
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException();
    }
    log.append("isEmpty successfully called with row: ").append(row).append(" column: ")
        .append(col);
    return false;
  }

  /**
   * Return the width of this spreadsheet. The width is defined by the cell with the highest column
   * number that is not empty.
   *
   * @return 5 always
   */
  public int getWidth() {
    log.append("getWidth() successfully called");
    return 5;
  }

  /**
   * Return the height of this spreadsheet. The height is defined by the cell with the highest row
   * number that is not empty.
   *
   * @return 4 always
   */
  public int getHeight() {
    log.append("getHeight() successfully called");
    return 4;
  }

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
    log.append("Successfully bulk assigned values");
  }
}
