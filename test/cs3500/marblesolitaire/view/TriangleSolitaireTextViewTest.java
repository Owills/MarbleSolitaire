package cs3500.marblesolitaire.view;

import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * tests TriangleSolitaireTextViewTest class.
 */
public class TriangleSolitaireTextViewTest {

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
    TriangleSolitaireModel defaultBoard = new TriangleSolitaireModel();
    TriangleSolitaireTextView throwsErrors = new TriangleSolitaireTextView(defaultBoard,
            new ThrowsExceptionAppendable());

    assertThrows(IOException.class, throwsErrors::renderBoard);

    //tests completed render messages
    Appendable systemOut = new StringWriter();
    TriangleSolitaireTextView textDefaultBoard = new TriangleSolitaireTextView(defaultBoard,
            systemOut);
    assertEquals("", systemOut.toString());
    try {
      textDefaultBoard.renderBoard();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", systemOut.toString());
  }


  /**
   * tests MarbleSolitaireTextView renderMessage method.
   */
  @Test
  public void testRenderMessage() {
    //tests throws IllegalArgumentException
    TriangleSolitaireModel defaultBoard = new TriangleSolitaireModel();
    TriangleSolitaireTextView throwsErrors = new TriangleSolitaireTextView(defaultBoard,
            new ThrowsExceptionAppendable());

    assertThrows(IOException.class, () ->
            throwsErrors.renderMessage("hi"));

    assertThrows(IOException.class, () ->
            throwsErrors.renderMessage("hello"));

    //tests completed render messages
    Appendable systemOut = new StringWriter();
    TriangleSolitaireTextView textDefaultBoard = new TriangleSolitaireTextView(defaultBoard,
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
    assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireTextView(null));
    assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireTextView(
            new TriangleSolitaireModel(),
            null));
    assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireTextView(null,
            null));
    assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireTextView(null,
            new StringWriter()));
  }

  /**
   * Tests the MarbleSolitaireView toString method for a TriangleSolitaireModel.
   */
  @Test
  public void testToString() {
    TriangleSolitaireModel defaultBoard = new TriangleSolitaireModel();
    TriangleSolitaireTextView textDefaultBoard = new TriangleSolitaireTextView(defaultBoard);
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", textDefaultBoard.toString());

    defaultBoard.move(2, 0, 0, 0);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O", textDefaultBoard.toString());


    TriangleSolitaireModel defualtBoardEmpty44 = new TriangleSolitaireModel(4, 4);
    textDefaultBoard = new TriangleSolitaireTextView(defualtBoardEmpty44);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O _", textDefaultBoard.toString());


    TriangleSolitaireModel largerBoard = new TriangleSolitaireModel(6);
    textDefaultBoard = new TriangleSolitaireTextView(largerBoard);
    assertEquals("     _\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "O O O O O O", textDefaultBoard.toString());

    TriangleSolitaireModel largerBoardEmpty55 = new TriangleSolitaireModel(
            6, 5, 5);
    textDefaultBoard = new TriangleSolitaireTextView(largerBoardEmpty55);
    assertEquals("     O\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "O O O O O _", textDefaultBoard.toString());
  }


}