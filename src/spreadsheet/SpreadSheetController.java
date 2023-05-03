package spreadsheet;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents the controller of an interactive spreadsheet application. This controller
 * offers a simple text interface in which the user can type instructions to manipulate a
 * spreadsheet.
 * This controller works with any Readable to read its inputs and any Appendable to transmit output.
 * This controller directly uses the Appendable object (i.e. there is no official "view")
 * A cell in the spreadsheet is referred to using a row-letter and a column number. The row letter
 * starts from A-Z and then AA-ZZ, then AAA-ZZZ and so on. The column numbers begin with 1.
 * For example, the cell in the first row and column is A 1. The cell in the 30th row and 26th
 * column is AD 26.
 * In this way it tries to simulate how Microsoft Excel works (except that it uses letters for rows,
 * not columns).
 */
public class SpreadSheetController {

  private final Readable readable;
  private final Appendable appendable;
  private final BetterSpreadSheet sheet;


  /**
   * contructor to initialize objects.
   * @param sheet  sheet
   * @param readable readable
   * @param appendable appendable
   */
  public SpreadSheetController(BetterSpreadSheet sheet, Readable readable, Appendable appendable) {
    if ((sheet == null) || (readable == null) || (appendable == null)) {
      throw new IllegalArgumentException("Sheet, readable or appendable is null");
    }
    this.sheet = sheet;
    this.appendable = appendable;
    this.readable = readable;
  }

  /**
   * execution block for controller.
   * @throws IllegalStateException for invalid commands
   */
  public void goExecute() throws IllegalStateException {
    Scanner sc = new Scanner(readable);
    boolean quit = false;
    int row;
    int col;
    double value;

    //print the welcome message
    this.welcomeMessage();

    while (!quit) { //continue until the user quits
      writeMessage("Type instruction: "); //prompt for the instruction name
      String userInstruction = sc.next(); //take an instruction name
      switch (userInstruction) {
        case "assign-value": //assign a value to a cell
          try {
            row = getRowNum(sc.next()); //get in the row string
            col = sc.nextInt(); //get in the column number, starting with 1
            System.out.println("Setting cell (" + row + "," + (col - 1));
            sheet.set(row, col - 1, sc.nextDouble()); //use the spreadsheet
          } catch (IllegalArgumentException e) {
            writeMessage("Error: " + e.getMessage() + System.lineSeparator());
          }
          break;
        case "print-value": //print a value from the cell
          try {
            row = getRowNum(sc.next()); //get the row string
            col = sc.nextInt(); //get the column number, starting with 1
            writeMessage("Value: " + sheet.get(row, col - 1) + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            writeMessage("Error: " + e.getMessage() + System.lineSeparator());
          }
          break;
        case "bulk-assign": //bulk assign a value to a range of cells
          try {
            int row1;
            int col1;
            row = getRowNum(sc.next()); //get the row string
            col = sc.nextInt(); //get the column number, starting with 1
            row1 = getRowNum(sc.next()); //get the row string
            col1 = sc.nextInt(); //get the column number, starting with 1
            writeMessage(
                "Bulk setting cells (" + row + "," + (col - 1) + "," + row1 + "," + (col1 - 1));
            sheet.setInBulk(row, col - 1, row1, col1 - 1, sc.nextDouble());
          } catch (IllegalArgumentException e) {
            writeMessage("Error: " + e.getMessage() + System.lineSeparator());
          }
          break;
        case "menu": //print the menu of supported instructions
          welcomeMessage();
          break;
        case "q": //quit
        case "quit": //quit
          quit = true;
          break;
        default: //error due to unrecognized instruction
          writeMessage("Undefined instruction: " + userInstruction + System.lineSeparator());
      }
    }

    //after the user has quit, print farewell message
    this.farewellMessage();

  }

  //converts a row string into a row number starting with 0
  private int getRowNum(String rowLetters) throws IllegalArgumentException {
    int rownumber = 0;

    for (int i = 0; i < rowLetters.length(); i = i + 1) {
      char c = rowLetters.charAt(i);
      if (!Character.isAlphabetic(c)) {
        throw new IllegalArgumentException("Invalid row");
      }
      rownumber = 26 * rownumber + ((int) Character.toLowerCase(c) - 'a' + 1);
    }
    return rownumber - 1;
  }


  private void writeMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  private void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage(
        "assign-value row-num col-num value (set a cell to a value)" + System.lineSeparator());
    writeMessage(
        "print-value row-num col-num (print the value at a given cell)" + System.lineSeparator());
    writeMessage(
        "bulk-assign row-num col-num (set values to given cell range)" + System.lineSeparator());
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }

  private void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the spreadsheet program!" + System.lineSeparator());
    printMenu();
  }

  private void farewellMessage() throws IllegalStateException {
    writeMessage("Thank you for using this program!");
  }


}
