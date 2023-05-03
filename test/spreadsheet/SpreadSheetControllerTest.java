package spreadsheet;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

/**
 * Test class for SpreadSheetController.
 */
public class SpreadSheetControllerTest {

  SpreadSheetController controller;

  BetterSpreadSheet spreadSheet;

  MockSpreadSheet mockSpreadSheet;
  private StringWriter writer;

  @org.junit.Before
  public void setUp() throws Exception {
    spreadSheet = new BetterSpreadSheetImpl();
    writer = new StringWriter();
    StringBuilder log = new StringBuilder();
    mockSpreadSheet = new MockSpreadSheet(log);
  }


  @Test
  public void testWelcomeMessage() {
    controller = new SpreadSheetController(spreadSheet, new StringReader("menu\nquit\n"), writer);
    controller.goExecute();
    assertEquals("Welcome to the spreadsheet program!\n"
            + "Supported user instructions are: \n"
            + "assign-value row-num col-num value (set a cell to a value)\n"
            + "print-value row-num col-num (print the value at a given cell)\n"
            + "bulk-assign row-num col-num (set values to given cell range)\n"
            + "menu (Print supported instruction list)\n"
            + "q or quit (quit the program) \n"
            + "Type instruction: Welcome to the spreadsheet program!\n"
            + "Supported user instructions are: \n"
            + "assign-value row-num col-num value (set a cell to a value)\n"
            + "print-value row-num col-num (print the value at a given cell)\n"
            + "bulk-assign row-num col-num (set values to given cell range)\n"
            + "menu (Print supported instruction list)\n"
            + "q or quit (quit the program) \n"
            + "Type instruction: Thank you for using this program!",
        writer.toString());
  }

  @Test
  public void testAppendable() {
    controller = new SpreadSheetController(spreadSheet, new StringReader("quit\n"), writer);
    controller.goExecute();
    String[] lines = writer.toString().split(System.lineSeparator());
    String output =
        lines[0] + "\n" + lines[1] + "\n" + lines[2] + "\n" + lines[3] + "\n" + lines[4] + "\n"
            + lines[5] + "\n" + lines[6];
    System.out.println(output);
    assertEquals("Welcome to the spreadsheet program!\n"
        + "Supported user instructions are: \n"
        + "assign-value row-num col-num value (set a cell to a value)\n"
        + "print-value row-num col-num (print the value at a given cell)\n"
        + "bulk-assign row-num col-num (set values to given cell range)\n"
        + "menu (Print supported instruction list)\n"
        + "q or quit (quit the program) ", output);
  }

  private String quitHelper() {
    return "\n"
        + "Type instruction: Thank you for using this program!";
  }

  private String welcomeHelper() {
    return "Welcome to the spreadsheet program!\n"
        + "Supported user instructions are: \n"
        + "assign-value row-num col-num value (set a cell to a value)\n"
        + "print-value row-num col-num (print the value at a given cell)\n"
        + "bulk-assign row-num col-num (set values to given cell range)\n"
        + "menu (Print supported instruction list)\n"
        + "q or quit (quit the program) ";
  }

  @Test
  public void testSet() {
    StringBuilder log = new StringBuilder();
    BetterSpreadSheet model = new MockBetterSpreadSheet(log);
    Readable rd = new StringReader("menu\nassign-value A 1 10\nquit\n");
    SpreadSheetController controller = new SpreadSheetController(model, rd, writer);
    controller.goExecute();
    String expectedString = "set successfully called with row: 0 column: 0 value:10.0";
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void testGet() {
    StringBuilder log = new StringBuilder();
    BetterSpreadSheet model = new MockBetterSpreadSheet(log);
    Readable rd = new StringReader("menu\nprint-value A 1\nquit\n");
    SpreadSheetController controller = new SpreadSheetController(model, rd, writer);
    controller.goExecute();
    String expectedString = "get successfully called with row: 0 column: 0";
    assertEquals(expectedString, log.toString());
    assertEquals(10.0, model.get(0, 0), 0.001);
  }

  @Test
  public void testIsEmpty() {
    StringBuilder log = new StringBuilder();
    BetterSpreadSheet model = new MockBetterSpreadSheet(log);
    Readable rd = new StringReader("menu\nprint-value A 1\nquit\n");
    SpreadSheetController controller = new SpreadSheetController(model, rd, writer);
    controller.goExecute();
    String expectedString = "get successfully called with row: 0 column: 0";
    assertEquals(expectedString, log.toString());
    assertEquals(10.0, model.get(0, 0), 0.001);
  }

  @Test
  public void testBulkAssign() {
    StringBuilder log = new StringBuilder();
    BetterSpreadSheet model = new MockBetterSpreadSheet(log);
    Readable rd = new StringReader("menu\nbulk-assign A 1 B 4 100\nquit\n");
    SpreadSheetController controller = new SpreadSheetController(model, rd, writer);
    controller.goExecute();
    String expectedString = "Successfully bulk assigned values";
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void testBulkAssignBetterSparseSpreadSheet() {
    BetterSpreadSheet model = new BetterSpreadSheetImpl();
    Readable rd = new StringReader("bulk-assign A 1 B 4 100\nquit\n");
    SpreadSheetController controller = new SpreadSheetController(model, rd, writer);
    controller.goExecute();
    String expectedString = "Bulk setting cells (0,0,1,3";
    assertEquals(welcomeHelper() + "\n"
        + "Type instruction: " + expectedString
        + "Type instruction: Thank you for using this program!", writer.toString());
  }

}