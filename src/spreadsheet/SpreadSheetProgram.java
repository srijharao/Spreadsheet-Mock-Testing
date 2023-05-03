package spreadsheet;

import java.io.InputStreamReader;

/**
 * Main calling class for spreadsheet.
 */
public class SpreadSheetProgram {

  /**
   * Program start.
   * @param args args
   */
  public static void main(String []args) {
    BetterSpreadSheet model = new BetterSpreadSheetImpl();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    SpreadSheetController controller = new SpreadSheetController(model,rd,ap);
    controller.goExecute();
  }
}
