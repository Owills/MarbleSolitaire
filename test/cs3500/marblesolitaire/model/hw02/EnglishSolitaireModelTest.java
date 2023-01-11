package cs3500.marblesolitaire.model.hw02;

import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the EnglishSolitaireModel class and its public methods.
 */
public class EnglishSolitaireModelTest extends AbstractSolitaireModelTest {


  public EnglishSolitaireModelTest() {
    super();
  }

  /**
   * Initializes valid game states to the starting game state.
   */
  protected void makeValidGames() {
    defaultGame = new EnglishSolitaireModel();
    armOne = new EnglishSolitaireModel(1);
    armFiveMiddleEmpty = new EnglishSolitaireModel(5);
    default03Empty = new EnglishSolitaireModel(0, 3);
    default02Empty = new EnglishSolitaireModel(0, 2);
    default30Empty = new EnglishSolitaireModel(3, 0);
    armFive55Empty = new EnglishSolitaireModel(5, 5, 5);
  }


  /**
   * Tests throw conditions in the four constructors and that the starting conditions
   * of the game are correct.
   */
  @Test
  public void testInvalidConstructors() {
    makeValidGames();
    //default armThickness, invalid empty pos
    assertThrows("Invalid empty cell position (0,0)",
            IllegalArgumentException.class, () -> new EnglishSolitaireModel(0, 0));
    assertThrows("Invalid empty cell position (8,8)",
            IllegalArgumentException.class, () -> new EnglishSolitaireModel(8, 8));
    assertThrows("Invalid empty cell position (0,1)",
            IllegalArgumentException.class, () -> new EnglishSolitaireModel(0, 1));
    assertThrows("Invalid empty cell position (3,8)",
            IllegalArgumentException.class, () -> new EnglishSolitaireModel(3, 8));
    assertThrows("Invalid empty cell position (5,6)",
            IllegalArgumentException.class, () -> new EnglishSolitaireModel(5, 6));

    //invalid armThickness, default empty pos
    assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(0));
    assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(-1));
    assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(4));

    //valid armThickness, invalid empty pos
    assertThrows("Invalid empty cell position (0,0)", IllegalArgumentException.class, () ->
            new EnglishSolitaireModel(3, 0, 0));
    assertThrows("Invalid empty cell position (8,8)", IllegalArgumentException.class, () ->
            new EnglishSolitaireModel(3, 8, 8));
    assertThrows("Invalid empty cell position (0,1)", IllegalArgumentException.class, () ->
            new EnglishSolitaireModel(3, 0, 1));
    assertThrows("Invalid empty cell position (3,8)", IllegalArgumentException.class, () ->
            new EnglishSolitaireModel(3, 3, 8));
    assertThrows("Invalid empty cell position (5,6)", IllegalArgumentException.class, () ->
            new EnglishSolitaireModel(3, 5, 6));

    //invalid armThickness and empty pos
    assertThrows(IllegalArgumentException.class, () ->
            new EnglishSolitaireModel(-1, 0, 0));
    assertThrows(IllegalArgumentException.class, () ->
            new EnglishSolitaireModel(0, 0, 0));
    assertThrows(IllegalArgumentException.class, () ->
            new EnglishSolitaireModel(4, 0, 0));

    // invalid armThickness, valid empty pos
    assertThrows(IllegalArgumentException.class, () ->
            new EnglishSolitaireModel(-1, 3, 3));
    assertThrows(IllegalArgumentException.class, () ->
            new EnglishSolitaireModel(0, 3, 3));
    assertThrows(IllegalArgumentException.class, () ->
            new EnglishSolitaireModel(4, 3, 3));
  }

  /**
   * Tests the MarbleSolitaireModelState getSlotAt method.
   */
  @Test
  public void testGetSlotAt() {
    super.testGetSlotAt();
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            defaultGame.getSlotAt(0, 0));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            armFiveMiddleEmpty.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            armFiveMiddleEmpty.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            armFiveMiddleEmpty.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            armFive55Empty.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            armFive55Empty.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            armFive55Empty.getSlotAt(3, 3));
  }


  /**
   * Tests the MarbleSolitaireModelState getScore method.
   */
  @Test
  public void testGetScore() {
    makeValidGames();
    //no moves
    assertEquals(32, defaultGame.getScore());
    assertEquals(0, armOne.getScore());
    assertEquals(32, default03Empty.getScore());
    assertEquals(32, default30Empty.getScore());
    assertEquals(104, armFive55Empty.getScore());
    assertEquals(104, armFiveMiddleEmpty.getScore());
  }

  /**
   * Tests the MarbleSolitaireModel move method.
   */
  @Test
  public void testMove() {
    super.testMove();
    this.testMoveDiagonally();

    makeValidGames();
    //defaultBoard valid move to center
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 5));
    defaultGame.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 5));
  }

  /**
   * Tests the MarbleSolitaireModel isGameOver method.
   */
  @Test
  public void testIsGameOver() {
    super.testIsGameOver();

    //game lost (ran out of valid moves)
    this.losingSetOfMoves();
    assertTrue(defaultGame.getScore() > 1);
    assertTrue(defaultGame.isGameOver());

    //game won
    this.winningSetOfMoves();
    assertEquals(1, defaultGame.getScore());
    assertTrue(defaultGame.isGameOver());
  }

  /**
   * winning set of moves for default english game.
   */
  @Override
  protected void winningSetOfMoves() {
    makeValidGames();
    defaultGame.move(1, 3, 3, 3);
    defaultGame.move(2, 1, 2, 3);
    defaultGame.move(0, 2, 2, 2);
    defaultGame.move(3, 2, 1, 2);
    defaultGame.move(2, 4, 2, 2);
    defaultGame.move(5, 2, 3, 2);
    defaultGame.move(4, 0, 4, 2);
    defaultGame.move(4, 3, 4, 1);
    defaultGame.move(2, 6, 2, 4);
    defaultGame.move(2, 0, 4, 0);
    defaultGame.move(4, 0, 4, 2);
    defaultGame.move(4, 5, 4, 3);
    defaultGame.move(4, 3, 4, 1);
    defaultGame.move(4, 1, 2, 1);
    defaultGame.move(2, 1, 2, 3);
    defaultGame.move(2, 3, 2, 5);
    defaultGame.move(6, 4, 4, 4);
    defaultGame.move(3, 4, 5, 4);
    defaultGame.move(4, 6, 2, 6);
    defaultGame.move(2, 6, 2, 4);
    defaultGame.move(6, 2, 6, 4);
    defaultGame.move(6, 4, 4, 4);
    defaultGame.move(0, 4, 0, 2);
    defaultGame.move(0, 2, 2, 2);
    defaultGame.move(2, 2, 4, 2);
    defaultGame.move(1, 4, 3, 4);
    defaultGame.move(3, 4, 5, 4);
    defaultGame.move(5, 4, 5, 2);
    defaultGame.move(5, 2, 3, 2);
    defaultGame.move(3, 2, 3, 4);
    defaultGame.move(3, 5, 3, 3);
  }

  /**
   * losing set of moves for default english game.
   */
  @Override
  protected void losingSetOfMoves() {
    makeValidGames();
    defaultGame.move(1, 3, 3, 3);
    defaultGame.move(2, 1, 2, 3);
    defaultGame.move(0, 2, 2, 2);
    defaultGame.move(3, 2, 1, 2);
    defaultGame.move(2, 4, 2, 2);
    defaultGame.move(5, 2, 3, 2);
    defaultGame.move(4, 0, 4, 2);
    defaultGame.move(4, 3, 4, 1);
    defaultGame.move(2, 6, 2, 4);
    defaultGame.move(2, 0, 4, 0);
    defaultGame.move(4, 0, 4, 2);
    defaultGame.move(4, 5, 4, 3);
    defaultGame.move(4, 3, 4, 1);
    defaultGame.move(4, 1, 2, 1);
    defaultGame.move(2, 1, 2, 3);
    defaultGame.move(2, 3, 2, 5);
    defaultGame.move(6, 4, 4, 4);
    defaultGame.move(3, 4, 5, 4);
    defaultGame.move(4, 6, 2, 6);
    defaultGame.move(2, 6, 2, 4);
    defaultGame.move(6, 2, 6, 4);
    defaultGame.move(6, 4, 4, 4);
    defaultGame.move(0, 4, 0, 2);
    defaultGame.move(0, 2, 2, 2);
    defaultGame.move(2, 2, 4, 2);
    defaultGame.move(2, 4, 0, 4);
  }
}