package cs3500.marblesolitaire.view;

import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * tests MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest {

  /**
   * tests Appendable class the always throws exceptions.
   */
  public static class ThrowsExceptionAppendable implements Appendable {

    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException();
    }
  }

  /**
   * tests MarbleSolitaireTextView renderBoard method.
   */
  @Test
  public void testRenderBoard() {
    //tests throws IllegalArgumentException
    EnglishSolitaireModel defaultBoard = new EnglishSolitaireModel();
    MarbleSolitaireTextView throwsErrors = new MarbleSolitaireTextView(defaultBoard,
            new ThrowsExceptionAppendable());

    assertThrows(IOException.class, throwsErrors::renderBoard);

    //tests completed render messages
    Appendable systemOut = new StringWriter();
    MarbleSolitaireTextView textDefaultBoard = new MarbleSolitaireTextView(defaultBoard,
            systemOut);
    assertEquals("", systemOut.toString());
    try {
      textDefaultBoard.renderBoard();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", systemOut.toString());
  }

  /**
   * tests MarbleSolitaireTextView renderMessage method.
   */
  @Test
  public void testRenderMessage() {
    //tests throws IllegalArgumentException
    EnglishSolitaireModel defaultBoard = new EnglishSolitaireModel();
    MarbleSolitaireTextView throwsErrors = new MarbleSolitaireTextView(defaultBoard,
            new ThrowsExceptionAppendable());

    assertThrows(IOException.class, () ->
            throwsErrors.renderMessage("hi"));

    assertThrows(IOException.class, () ->
            throwsErrors.renderMessage("hello"));

    //tests completed render messages
    Appendable systemOut = new StringWriter();
    MarbleSolitaireTextView textDefaultBoard = new MarbleSolitaireTextView(defaultBoard,
            systemOut);
    assertEquals("", systemOut.toString());
    try {
      textDefaultBoard.renderMessage("hi\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("hi\n", systemOut.toString());
    try {
      textDefaultBoard.renderMessage("hello\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("hi\nhello\n", systemOut.toString());
  }

  /**
   * Tests MarbleSolitaireTextView constructor.
   */
  @Test
  public void testConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new MarbleSolitaireTextView(null));
    assertThrows(IllegalArgumentException.class, () -> new MarbleSolitaireTextView(
            new EnglishSolitaireModel(),
            null));
    assertThrows(IllegalArgumentException.class, () -> new MarbleSolitaireTextView(null,
            null));
    assertThrows(IllegalArgumentException.class, () -> new MarbleSolitaireTextView(null,
            new StringWriter()));
  }

  /**
   * Tests the MarbleSolitaireView toString method for an EnglishSolitaireModel.
   */
  @Test
  public void testToString() {
    EnglishSolitaireModel defaultBoard = new EnglishSolitaireModel();
    MarbleSolitaireView textDefaultBoard = new MarbleSolitaireTextView(defaultBoard);

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", textDefaultBoard.toString());

    MarbleSolitaireModelState armOneBoard = new EnglishSolitaireModel(1);
    MarbleSolitaireView textArmOneBoard = new MarbleSolitaireTextView(armOneBoard);

    assertEquals("_", textArmOneBoard.toString());

    MarbleSolitaireModelState armFiveBoard = new EnglishSolitaireModel(5);
    MarbleSolitaireView textArmFiveBoard = new MarbleSolitaireTextView(armFiveBoard);

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", textArmFiveBoard.toString());

    MarbleSolitaireModelState armThreeStartZeroTwoBoard =
            new EnglishSolitaireModel(3, 0, 2);
    MarbleSolitaireView textArmThreeStartZeroTwoBoard =
            new MarbleSolitaireTextView(armThreeStartZeroTwoBoard);

    assertEquals("    _ O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", textArmThreeStartZeroTwoBoard.toString());

    // draw after one move
    defaultBoard.move(1, 3, 3, 3);
    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", textDefaultBoard.toString());

  }

  /**
   * Tests the MarbleSolitaireView toString method for a EuropeanSolitaireModel.
   */
  @Test
  public void testToStringV2() {
    EuropeanSolitaireModel defaultBoard = new EuropeanSolitaireModel();
    MarbleSolitaireView textDefaultBoard = new MarbleSolitaireTextView(defaultBoard);

    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", textDefaultBoard.toString());

    MarbleSolitaireModelState armOneBoard = new EuropeanSolitaireModel(1);
    MarbleSolitaireView textArmOneBoard = new MarbleSolitaireTextView(armOneBoard);

    assertEquals("_", textArmOneBoard.toString());

    MarbleSolitaireModelState armFiveBoard = new EuropeanSolitaireModel(5);
    MarbleSolitaireView textArmFiveBoard = new MarbleSolitaireTextView(armFiveBoard);

    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", textArmFiveBoard.toString());

    MarbleSolitaireModelState armThreeStartZeroTwoBoard =
            new EuropeanSolitaireModel(3, 0, 2);
    MarbleSolitaireView textArmThreeStartZeroTwoBoard =
            new MarbleSolitaireTextView(armThreeStartZeroTwoBoard);

    assertEquals("    _ O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", textArmThreeStartZeroTwoBoard.toString());

    // draw after one move
    defaultBoard.move(1, 3, 3, 3);
    assertEquals("    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", textDefaultBoard.toString());

  }

}