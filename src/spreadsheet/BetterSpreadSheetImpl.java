package spreadsheet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A class that represents a BetterSpreadSheet that assigns a single value to a rectangular block of
 * cells in a spreadsheet.
 */
public class BetterSpreadSheetImpl implements BetterSpreadSheet {

  private final Map<CellPosition, Double> sheet;
  private int width;
  private int height;

  /**
   * Constructs a new Better SpreadSheet object with the specified row, column, and value.
   */

  public BetterSpreadSheetImpl() {
    this.sheet = new HashMap<CellPosition, Double>();
    this.width = 0;
    this.height = 0;
  }

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

  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    return this.sheet.getOrDefault(new CellPosition(row, col), 0.0);
  }

  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    this.sheet.put(new CellPosition(row, col), value);
    if ((row + 1) > height) {
      height = row + 1;
    }

    if ((col + 1) > width) {
      width = col + 1;
    }
  }

  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    return !this.sheet.containsKey(new CellPosition(row, col));
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  private static class CellPosition {

    private final int row;
    private final int column;

    private CellPosition(int row, int column) {
      this.row = row;
      this.column = column;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CellPosition)) {
        return false;
      }
      CellPosition other = (CellPosition)o;
      return this.row == other.row && this.column == other.column;
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.row, this.column);
    }
  }
}
