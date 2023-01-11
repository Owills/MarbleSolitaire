package cs3500.marblesolitaire.model.hw02;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * tests for a european solitaire model.
 */
public class EuropeanSolitaireModelTest extends AbstractSolitaireModelTest {

  //Valid games
  EuropeanSolitaireModel armThree11Empty;
  EuropeanSolitaireModel armFive33Empty;


  /**
   * Initializes valid game states to the starting game state.
   */
  protected void makeValidGames() {
    defaultGame = new EuropeanSolitaireModel();
    armOne = new EuropeanSolitaireModel(1);
    armFiveMiddleEmpty = new EuropeanSolitaireModel(5);
    default03Empty = new EuropeanSolitaireModel(0, 3);
    default02Empty = new EuropeanSolitaireModel(0, 2);
    default30Empty = new EuropeanSolitaireModel(3, 0);
    armFive55Empty = new EuropeanSolitaireModel(5, 5, 5);

    armThree11Empty = new EuropeanSolitaireModel(1, 1);
    armFive33Empty = new EuropeanSolitaireModel(5, 3, 3);
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
    assertEquals(1, default02Empty.getScore());
    assertTrue(default02Empty.isGameOver());
  }

  /**
   * winning set of moves for european game with the empty square at (0,2).
   */
  @Override
  protected void winningSetOfMoves() {
    this.makeValidGames();
    default02Empty.move(0, 4, 0, 2);
    default02Empty.move(2, 3, 0, 3);
    default02Empty.move(1, 5, 1, 3);
    default02Empty.move(0, 3, 2, 3);
    default02Empty.move(3, 4, 1, 4);
    default02Empty.move(2, 6, 2, 4);
    default02Empty.move(2, 3, 2, 5);
    default02Empty.move(4, 6, 2, 6);
    default02Empty.move(2, 6, 2, 4);
    default02Empty.move(1, 4, 3, 4);
    default02Empty.move(3, 4, 3, 6);
    default02Empty.move(5, 5, 3, 5);
    default02Empty.move(3, 6, 3, 4);
    default02Empty.move(4, 3, 4, 5);
    default02Empty.move(6, 4, 4, 4);
    default02Empty.move(3, 4, 5, 4);
    default02Empty.move(6, 2, 6, 4);
    default02Empty.move(6, 4, 4, 4);
    default02Empty.move(4, 5, 4, 3);
    default02Empty.move(4, 3, 6, 3);
    default02Empty.move(5, 1, 5, 3);
    default02Empty.move(6, 3, 4, 3);
    default02Empty.move(3, 2, 5, 2);
    default02Empty.move(4, 0, 4, 2);
    default02Empty.move(4, 3, 4, 1);
    default02Empty.move(2, 0, 4, 0);
    default02Empty.move(4, 0, 4, 2);
    default02Empty.move(5, 2, 3, 2);
    default02Empty.move(2, 1, 4, 1);
    default02Empty.move(3, 3, 3, 1);
    default02Empty.move(4, 1, 2, 1);
    default02Empty.move(2, 1, 2, 3);
    default02Empty.move(0, 2, 2, 2);
    default02Empty.move(2, 3, 2, 1);
    default02Empty.move(1, 1, 3, 1);
  }

  /**
   * losing set of moves for a defualt european game.
   */
  @Override
  protected void losingSetOfMoves() {
    this.makeValidGames();
    defaultGame.move(3, 1, 3, 3);
    defaultGame.move(1, 1, 3, 1);
    defaultGame.move(2, 3, 2, 1);
    defaultGame.move(2, 0, 2, 2);
    defaultGame.move(2, 5, 2, 3);
    defaultGame.move(2, 3, 2, 1);
    defaultGame.move(4, 0, 2, 0);
    defaultGame.move(2, 0, 2, 2);
    defaultGame.move(0, 4, 2, 4);
    defaultGame.move(4, 1, 2, 1);
    defaultGame.move(2, 1, 2, 3);
    defaultGame.move(2, 3, 2, 5);
    defaultGame.move(0, 2, 2, 2);
    defaultGame.move(2, 6, 2, 4);
    defaultGame.move(4, 3, 4, 1);
    defaultGame.move(0, 3, 2, 3);
    defaultGame.move(2, 3, 2, 5);
    defaultGame.move(5, 1, 3, 1);
    defaultGame.move(4, 6, 2, 6);
    defaultGame.move(2, 6, 2, 4);
    defaultGame.move(4, 5, 2, 5);
    defaultGame.move(2, 5, 2, 3);
    defaultGame.move(2, 3, 4, 3);
    defaultGame.move(4, 3, 4, 5);
    defaultGame.move(6, 2, 4, 2);
    defaultGame.move(6, 4, 4, 4);
    defaultGame.move(5, 5, 3, 5);
    defaultGame.move(6, 3, 4, 3);
    defaultGame.move(4, 3, 4, 5);
    defaultGame.move(3, 4, 3, 6);
  }

  /**
   * Tests the MarbleSolitaireModel move method.
   */
  @Test
  public void testMove() {
    super.testMove();
    this.testMoveDiagonally();

    //move to unique empty position in european boards
    makeValidGames();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armThree11Empty.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armThree11Empty.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armThree11Empty.getSlotAt(1, 3));
    assertEquals(36, armThree11Empty.getScore());
    armThree11Empty.move(1, 3, 1, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armThree11Empty.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armThree11Empty.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armThree11Empty.getSlotAt(1, 3));
    assertEquals(35, armThree11Empty.getScore());

    makeValidGames();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armFive33Empty.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFive33Empty.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFive33Empty.getSlotAt(3, 5));
    assertEquals(128, armFive33Empty.getScore());
    armFive33Empty.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFive33Empty.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armFive33Empty.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armFive33Empty.getSlotAt(3, 5));
    assertEquals(127, armFive33Empty.getScore());
  }

  /**
   * Tests the MarbleSolitaireModelState getScore method.
   */
  @Test
  public void testGetScore() {
    makeValidGames();
    //no moves
    assertEquals(36, defaultGame.getScore());
    assertEquals(0, armOne.getScore());
    assertEquals(36, default03Empty.getScore());
    assertEquals(36, default30Empty.getScore());
    assertEquals(128, armFive55Empty.getScore());
    assertEquals(128, armFiveMiddleEmpty.getScore());
    assertEquals(36, armThree11Empty.getScore());
    assertEquals(128, armFive33Empty.getScore());
  }

  /**
   * Tests the MarbleSolitaireModelState getSlotAt method.
   */
  @Test
  public void testGetSlotAt() {
    super.testGetSlotAt();
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            defaultGame.getSlotAt(0, 0));


    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armThree11Empty.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armFive33Empty.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFive55Empty.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFive55Empty.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFive55Empty.getSlotAt(3, 3));
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
            IllegalArgumentException.class, () -> new EuropeanSolitaireModel(0, 0));
    assertThrows("Invalid empty cell position (8,8)",
            IllegalArgumentException.class, () -> new EuropeanSolitaireModel(8, 8));
    assertThrows("Invalid empty cell position (0,1)",
            IllegalArgumentException.class, () -> new EuropeanSolitaireModel(0, 1));
    assertThrows("Invalid empty cell position (3,8)",
            IllegalArgumentException.class, () -> new EuropeanSolitaireModel(3, 8));
    assertThrows("Invalid empty cell position (5,6)",
            IllegalArgumentException.class, () -> new EuropeanSolitaireModel(5, 6));

    //invalid armThickness, default empty pos
    assertThrows(IllegalArgumentException.class, () -> new EuropeanSolitaireModel(0));
    assertThrows(IllegalArgumentException.class, () -> new EuropeanSolitaireModel(-1));
    assertThrows(IllegalArgumentException.class, () -> new EuropeanSolitaireModel(4));

    //valid armThickness, invalid empty pos
    assertThrows("Invalid empty cell position (0,0)", IllegalArgumentException.class, () ->
            new EuropeanSolitaireModel(3, 0, 0));
    assertThrows("Invalid empty cell position (8,8)", IllegalArgumentException.class, () ->
            new EuropeanSolitaireModel(3, 8, 8));
    assertThrows("Invalid empty cell position (0,1)", IllegalArgumentException.class, () ->
            new EuropeanSolitaireModel(3, 0, 1));
    assertThrows("Invalid empty cell position (3,8)", IllegalArgumentException.class, () ->
            new EuropeanSolitaireModel(3, 3, 8));
    assertThrows("Invalid empty cell position (5,6)", IllegalArgumentException.class, () ->
            new EuropeanSolitaireModel(3, 5, 6));

    //invalid armThickness and empty pos
    assertThrows(IllegalArgumentException.class, () ->
            new EuropeanSolitaireModel(-1, 0, 0));
    assertThrows(IllegalArgumentException.class, () ->
            new EuropeanSolitaireModel(0, 0, 0));
    assertThrows(IllegalArgumentException.class, () ->
            new EuropeanSolitaireModel(4, 0, 0));

    // invalid armThickness, valid empty pos
    assertThrows(IllegalArgumentException.class, () ->
            new EuropeanSolitaireModel(-1, 3, 3));
    assertThrows(IllegalArgumentException.class, () ->
            new EuropeanSolitaireModel(0, 3, 3));
    assertThrows(IllegalArgumentException.class, () ->
            new EuropeanSolitaireModel(4, 3, 3));
  }

  /**
   * test correct conditions of valid constructors.
   */
  @Test
  public void testValidConstructors() {
    super.testValidConstructors();

    //armThree11Empty
    //test that there is exactly one empty square and its in the center
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armThree11Empty.getSlotAt(1, 1));
    int temp = 0;
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        if (armThree11Empty.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          temp += 1;
        }
      }
    }
    assertEquals(1, temp);

    //armFive33Empty
    //test that there is exactly one empty square and its in the center
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armFive33Empty.getSlotAt(3, 3));
    temp = 0;
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        if (armFive33Empty.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          temp += 1;
        }
      }
    }
    assertEquals(1, temp);
  }


}
