package cs3500.marblesolitaire.model.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * General tests for a MarbleSolitaireModel.
 */
public abstract class AbstractSolitaireModelTest {

  MarbleSolitaireModel defaultGame;
  MarbleSolitaireModel armOne;
  MarbleSolitaireModel armFiveMiddleEmpty;
  MarbleSolitaireModel default03Empty;
  MarbleSolitaireModel default02Empty;
  MarbleSolitaireModel default30Empty;
  MarbleSolitaireModel armFive55Empty;

  public AbstractSolitaireModelTest() {
    this.makeValidGames();
  }

  /**
   * Tests the MarbleSolitaireModel isGameOver method.
   */
  @Test
  public void testIsGameOver() {
    //no moves
    assertFalse(defaultGame.isGameOver());
    assertTrue(armOne.isGameOver());
    assertFalse(default03Empty.isGameOver());
    assertFalse(default30Empty.isGameOver());
    assertFalse(armFive55Empty.isGameOver());
    assertFalse(armFiveMiddleEmpty.isGameOver());

    //game lost (ran out of valid moves)
    this.losingSetOfMoves();
    assertTrue(defaultGame.getScore() > 1);
    assertTrue(defaultGame.isGameOver());
  }

  /**
   * set of much such that the game has been won.
   */
  protected abstract void winningSetOfMoves();


  /**
   * set of much such that the game has been lost.
   */
  protected abstract void losingSetOfMoves();

  /**
   * test moves that are invalid for all solitaire models.
   */
  @Test
  public void testInvalidMoves() {
    makeValidGames();
    //defaultBoard negative to board
    assertThrows(IllegalArgumentException.class, () -> defaultGame.move(
            -2, 0, 0, 0));

    //defaultBoard board to negative
    assertThrows(IllegalArgumentException.class, () -> defaultGame.move(
            0, 0, -2, 0));

    //defaultBoard board to off board
    assertThrows(IllegalArgumentException.class, () -> defaultGame.move(
            defaultGame.getBoardSize() / 2, 0,
            defaultGame.getBoardSize() / 2, -2));

    //defaultBoard board to off board
    assertThrows(IllegalArgumentException.class, () -> defaultGame.move(
            defaultGame.getBoardSize() / 2, -2,
            defaultGame.getBoardSize() / 2, 0));
  }

  /**
   * tests moves that work for similar marble solitaire games.
   */
  @Test
  public void testMove() {
    makeValidGames();
    //defaultBoard valid move to center
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 5));
    defaultGame.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 5));

    makeValidGames();
    //defaultBoard invalid move from two rows above same column
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(0, 3));
    assertThrows(IllegalArgumentException.class, () ->
            defaultGame.move(0, 3, 3, 3));

    makeValidGames();
    //defaultBoard invalid move from two rows below same column
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(6, 3));
    assertThrows(IllegalArgumentException.class, () ->
            defaultGame.move(6, 3, 3, 3));

    makeValidGames();
    //defaultBoard valid move to center
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 1));
    int score = defaultGame.getScore();
    defaultGame.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 1));
    assertEquals(score - 1, defaultGame.getScore());
    //chain move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 4));
    defaultGame.move(3, 4, 3, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 4));
    assertEquals(score - 2, defaultGame.getScore());

    makeValidGames();
    //invalid moves
    //move to center from one away
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 2));
    score = defaultGame.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            defaultGame.move(3, 2, 3, 3));
    assertEquals(score, defaultGame.getScore());

    //move to center from two away
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 0));
    score = defaultGame.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            defaultGame.move(3, 0, 3, 3));
    assertEquals(score, defaultGame.getScore());

    //move from empty to empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    score = defaultGame.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            defaultGame.move(3, 3, 3, 3));
    assertEquals(score, defaultGame.getScore());

    //move from marble to marble
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 2));
    score = defaultGame.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            defaultGame.move(3, 2, 3, 4));
    assertEquals(score, defaultGame.getScore());

    //move from invalid to empty across the board
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, defaultGame.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    score = defaultGame.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            defaultGame.move(0, 0, 3, 3));
    assertEquals(score, defaultGame.getScore());

    //move from invalid to empty seperated by one marble
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            default03Empty.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default03Empty.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            default03Empty.getSlotAt(0, 3));
    score = default03Empty.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            default03Empty.move(0, 1, 0, 3));
    assertEquals(score, default03Empty.getScore());

    //move from invalid to marble no separation
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            default03Empty.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default03Empty.getSlotAt(0, 2));
    score = default03Empty.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            default03Empty.move(0, 1, 0, 2));
    assertEquals(score, default03Empty.getScore());

    //move from invalid to empty no separation
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            default02Empty.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            default02Empty.getSlotAt(0, 2));
    score = default02Empty.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            default02Empty.move(0, 1, 0, 2));
    assertEquals(score, default02Empty.getScore());

    //move from empty to invalid across the board
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, defaultGame.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    score = defaultGame.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            defaultGame.move(3, 3, 0, 0));
    assertEquals(score, defaultGame.getScore());

    //move from empty to invalid seperated by one marble
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            default03Empty.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default03Empty.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            default03Empty.getSlotAt(0, 3));
    score = default03Empty.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            default03Empty.move(0, 3, 0, 1));
    assertEquals(score, default03Empty.getScore());

    //move from marble to invalid no separation
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            default03Empty.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default03Empty.getSlotAt(0, 2));
    assertEquals(score, default03Empty.getScore());
    assertThrows(IllegalArgumentException.class, () ->
            default03Empty.move(0, 2, 0, 1));
    assertEquals(score, default03Empty.getScore());

    // move from outside the board
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    score = defaultGame.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            defaultGame.move(-1, -1, 3, 3));
    assertEquals(score, defaultGame.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    assertEquals(score, defaultGame.getScore());
    assertThrows(IllegalArgumentException.class, () ->
            defaultGame.move(7, 7, 3, 3));
    assertEquals(score, defaultGame.getScore());

    // move to outside
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(0, 2));
    score = defaultGame.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            defaultGame.move(0, 2, -2, 2));
    assertEquals(score, defaultGame.getScore());
  }

  /**
   * test that moving diagonally is impossible for english and european solitaire models.
   */
  protected void testMoveDiagonally() {
    //move diagonally
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armFiveMiddleEmpty.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(7, 7));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(8, 8));
    int score = armFiveMiddleEmpty.getScore();
    assertThrows(IllegalArgumentException.class, () ->
            armFiveMiddleEmpty.move(8, 8, 6, 6));
    assertEquals(score, armFiveMiddleEmpty.getScore());
    assertThrows(IllegalArgumentException.class, () ->
            armFiveMiddleEmpty.move(7, 7, 6, 6));
    assertEquals(score, armFiveMiddleEmpty.getScore());
    assertThrows(IllegalArgumentException.class, () ->
            armFiveMiddleEmpty.move(4, 4, 6, 6));
    assertEquals(score, armFiveMiddleEmpty.getScore());
  }

  /**
   * Tests the MarbleSolitaireModelState getSlotAt method.
   */
  @Test
  public void testGetSlotAt() {
    makeValidGames();
    //defaultGame

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, defaultGame.getSlotAt(6, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, defaultGame.getSlotAt(0, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, defaultGame.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(2, 3));
    assertThrows(IllegalArgumentException.class, () -> defaultGame.getSlotAt(-1, -1));
    assertThrows(IllegalArgumentException.class, () -> defaultGame.getSlotAt(7, 7));

    //armOne
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, armOne.getSlotAt(0, 0));
    assertThrows(IllegalArgumentException.class, () -> armOne.getSlotAt(-1, 0));
    assertThrows(IllegalArgumentException.class, () -> armOne.getSlotAt(-1, -1));
    assertThrows(IllegalArgumentException.class, () -> armOne.getSlotAt(1, 0));
    assertThrows(IllegalArgumentException.class, () -> armOne.getSlotAt(1, 1));

    //defaultArm, different empty
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            default30Empty.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            default30Empty.getSlotAt(6, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            default30Empty.getSlotAt(0, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            default30Empty.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default30Empty.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default30Empty.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default30Empty.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            default30Empty.getSlotAt(3, 0));
    assertThrows(IllegalArgumentException.class, () -> default30Empty.getSlotAt(-1, -1));
    assertThrows(IllegalArgumentException.class, () -> default30Empty.getSlotAt(7, 7));

    //armThickness 5, middle empty
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            armFiveMiddleEmpty.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(6, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(0, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armFiveMiddleEmpty.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(8, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            armFiveMiddleEmpty.getSlotAt(3, 0));
    assertThrows(IllegalArgumentException.class, () -> default30Empty.getSlotAt(-1, -1));
    assertThrows(IllegalArgumentException.class, () -> default30Empty.getSlotAt(13, 13));

    //armThickness 5, different empty
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            armFive55Empty.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFive55Empty.getSlotAt(6, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFive55Empty.getSlotAt(0, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFive55Empty.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armFive55Empty.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFive55Empty.getSlotAt(8, 8));


    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            armFive55Empty.getSlotAt(3, 0));
    assertThrows(IllegalArgumentException.class, () -> armFive55Empty.getSlotAt(-1, -1));
    assertThrows(IllegalArgumentException.class, () -> armFive55Empty.getSlotAt(13, 13));
  }

  /**
   * Tests the MarbleSolitaireModelState getBoardSize method.
   */
  @Test
  public void testGetBoardSize() {
    makeValidGames();
    assertEquals(7, defaultGame.getBoardSize());
    assertEquals(1, armOne.getBoardSize());
    assertEquals(7, default03Empty.getBoardSize());
    assertEquals(7, default30Empty.getBoardSize());
    assertEquals(13, armFive55Empty.getBoardSize());
    assertEquals(13, armFiveMiddleEmpty.getBoardSize());
  }

  /**
   * test correct conditions of valid constructors.
   */
  @Test
  public void testValidConstructors() {
    //default
    //test that there is exactly one empty square
    int temp = 0;
    for (int i = 0; i < defaultGame.getBoardSize(); i++) {
      for (int j = 0; j < defaultGame.getBoardSize(); j++) {
        if (defaultGame.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          temp += 1;
        }
      }
    }
    assertEquals(1, temp);

    //default armThickness, empty at 3, 0
    //test that there is exactly one empty square
    temp = 0;
    for (int i = 0; i < default30Empty.getBoardSize(); i++) {
      for (int j = 0; j < default30Empty.getBoardSize(); j++) {
        if (default30Empty.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          temp += 1;
        }
      }
    }
    assertEquals(1, temp);

    //armThickness 5, empty at center
    //test that there is exactly one empty square
    temp = 0;
    for (int i = 0; i < armFiveMiddleEmpty.getBoardSize(); i++) {
      for (int j = 0; j < armFiveMiddleEmpty.getBoardSize(); j++) {
        if (armFiveMiddleEmpty.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          temp += 1;
        }
      }
    }
    assertEquals(1, temp);

    //armThickness 5, position (5, 5)
    //test that there is exactly one empty square
    temp = 0;
    for (int i = 0; i < armFive55Empty.getBoardSize(); i++) {
      for (int j = 0; j < armFive55Empty.getBoardSize(); j++) {
        if (armFive55Empty.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          temp += 1;
        }
      }
    }
    assertEquals(1, temp);
  }

  /**
   * Initializes valid game states to the starting game state.
   */
  protected abstract void makeValidGames();

  /**
   * tests get score method.
   */
  protected abstract void testGetScore();

  /**
   * tests creating invalid constructors throws exceptions.
   */
  protected abstract void testInvalidConstructors();


}
