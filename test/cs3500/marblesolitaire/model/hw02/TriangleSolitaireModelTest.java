package cs3500.marblesolitaire.model.hw02;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Tests the TriangleSolitaireModel class and its public methods.
 */
public class TriangleSolitaireModelTest extends AbstractSolitaireModelTest {

  TriangleSolitaireModel dim7defaultEmpty;
  TriangleSolitaireModel dim7Empty66;


  @Override
  protected void makeValidGames() {
    defaultGame = new TriangleSolitaireModel();
    armOne = new TriangleSolitaireModel(1);
    default30Empty = new TriangleSolitaireModel(3, 0);
    armFiveMiddleEmpty = new TriangleSolitaireModel(2, 2);
    armFive55Empty = new TriangleSolitaireModel(4, 4);

    dim7defaultEmpty = new TriangleSolitaireModel(7);
    dim7Empty66 = new TriangleSolitaireModel(7, 6, 6);

  }

  @Test
  public void testGetScore() {
    assertEquals(14, defaultGame.getScore());
    assertEquals(0, armOne.getScore());
    assertEquals(14, default30Empty.getScore());
    assertEquals(14, armFiveMiddleEmpty.getScore());
    assertEquals(14, armFive55Empty.getScore());
    assertEquals(27, dim7defaultEmpty.getScore());
    assertEquals(27, dim7defaultEmpty.getScore());
  }

  @Test
  public void testInvalidConstructors() {
    makeValidGames();
    //default dimensions, invalid empty pos
    assertThrows("Invalid empty cell position (1,1)",
            IllegalArgumentException.class, () -> new TriangleSolitaireModel(0, 1));
    assertThrows("Invalid empty cell position (0,4)",
            IllegalArgumentException.class, () -> new TriangleSolitaireModel(0, 4));
    assertThrows("Invalid empty cell position (1,2)",
            IllegalArgumentException.class, () -> new TriangleSolitaireModel(1, 2));
    assertThrows("Invalid empty cell position (-1,-1)",
            IllegalArgumentException.class, () -> new TriangleSolitaireModel(-1, -1));
    assertThrows("Invalid empty cell position (5,5)",
            IllegalArgumentException.class, () -> new TriangleSolitaireModel(5, 5));

    //invalid dimensions, default empty pos
    assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireModel(0));
    assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireModel(-1));
    assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireModel(-5));

    //valid dimensions, invalid empty pos
    assertThrows("Invalid empty cell position (0,1)", IllegalArgumentException.class, () ->
            new TriangleSolitaireModel(3, 0, 1));
    assertThrows("Invalid empty cell position (0,4)", IllegalArgumentException.class, () ->
            new TriangleSolitaireModel(3, 0, 4));
    assertThrows("Invalid empty cell position (1,2)", IllegalArgumentException.class, () ->
            new TriangleSolitaireModel(3, 1, 2));
    assertThrows("Invalid empty cell position (-1,-1)", IllegalArgumentException.class, () ->
            new TriangleSolitaireModel(3, -1, -1));
    assertThrows("Invalid empty cell position (5,5)", IllegalArgumentException.class, () ->
            new TriangleSolitaireModel(3, 5, 5));

    //invalid dimensions and empty pos
    assertThrows(IllegalArgumentException.class, () ->
            new TriangleSolitaireModel(-1, 0, 0));
    assertThrows(IllegalArgumentException.class, () ->
            new TriangleSolitaireModel(0, 0, 0));
    assertThrows(IllegalArgumentException.class, () ->
            new TriangleSolitaireModel(-2, 0, 0));

    // invalid dimensions, valid empty pos
    assertThrows(IllegalArgumentException.class, () ->
            new TriangleSolitaireModel(-1, 3, 3));
    assertThrows(IllegalArgumentException.class, () ->
            new TriangleSolitaireModel(0, 3, 3));
    assertThrows(IllegalArgumentException.class, () ->
            new TriangleSolitaireModel(-3, 3, 3));
  }

  @Test
  public void testMove() {
    makeValidGames();
    //defaultBoard up right diagonal move to 0,0
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(2, 0));
    defaultGame.move(2, 0, 0, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(2, 0));


    makeValidGames();
    //defaultBoard up left diagonal move to 0,0
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(2, 2));
    defaultGame.move(2, 2, 0, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(2, 2));

    makeValidGames();
    //armFiveMiddleEmpty down left diagonal move to 2,2
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armFiveMiddleEmpty.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(0, 0));
    armFiveMiddleEmpty.move(0, 0, 2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            armFiveMiddleEmpty.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armFiveMiddleEmpty.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            armFiveMiddleEmpty.getSlotAt(0, 0));

    makeValidGames();
    //default30Empty down right diagonal move to 2,2
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            default30Empty.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default30Empty.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default30Empty.getSlotAt(1, 0));
    default30Empty.move(1, 0, 3, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default30Empty.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            default30Empty.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            default30Empty.getSlotAt(1, 0));

    makeValidGames();
    //default30Empty left to right move to 2,2
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            default30Empty.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default30Empty.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default30Empty.getSlotAt(3, 2));
    default30Empty.move(3, 2, 3, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default30Empty.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            default30Empty.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            default30Empty.getSlotAt(3, 2));

    makeValidGames();
    //dim7Empty66 right to left move to 2,2
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            dim7Empty66.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            dim7Empty66.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            dim7Empty66.getSlotAt(6, 4));
    dim7Empty66.move(6, 4, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            dim7Empty66.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            dim7Empty66.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            dim7Empty66.getSlotAt(6, 4));

    makeValidGames();
    //dim7Empty66 invalid move down left diagonal across two marbles
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            dim7Empty66.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            dim7Empty66.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            dim7Empty66.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            dim7Empty66.getSlotAt(3, 3));
    assertThrows(IllegalArgumentException.class, () ->
            dim7Empty66.move(3, 3, 6, 6));

    makeValidGames();
    //dim7Empty66 invalid move right across two marbles
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            dim7Empty66.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            dim7Empty66.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            dim7Empty66.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            dim7Empty66.getSlotAt(6, 3));
    assertThrows(IllegalArgumentException.class, () ->
            dim7Empty66.move(6, 3, 6, 6));

    makeValidGames();
    //dim7Empty66 dim7defaultEmpty move up left diagonal across two marbles
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            dim7defaultEmpty.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            dim7defaultEmpty.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            dim7defaultEmpty.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            dim7defaultEmpty.getSlotAt(3, 3));
    assertThrows(IllegalArgumentException.class, () ->
            dim7defaultEmpty.move(3, 3, 0, 0));

    makeValidGames();
    //defaultBoard invalid move from two below
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 0));
    assertThrows(IllegalArgumentException.class, () -> defaultGame.move(
            3, 0, 0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultGame.getSlotAt(3, 0));

  }

  @Test
  public void testIsGameOver() {
    assertFalse(defaultGame.isGameOver());
    assertTrue(armOne.isGameOver());
    assertFalse(default30Empty.isGameOver());
    assertFalse(armFiveMiddleEmpty.isGameOver());
    assertFalse(armFive55Empty.isGameOver());
    assertFalse(dim7defaultEmpty.isGameOver());
    assertFalse(dim7Empty66.isGameOver());

    //game won
    this.winningSetOfMoves();
    assertEquals(1, defaultGame.getScore());
    assertTrue(defaultGame.isGameOver());

    //game lost (ran out of valid moves)
    this.losingSetOfMoves();
    assertTrue(defaultGame.getScore() > 1);
    assertTrue(defaultGame.isGameOver());
  }

  @Override
  protected void winningSetOfMoves() {
    this.makeValidGames();
    defaultGame.move(2, 0, 0, 0);
    defaultGame.move(2, 2, 2, 0);
    defaultGame.move(4, 1, 2, 1);
    defaultGame.move(4, 3, 4, 1);
    defaultGame.move(4, 0, 4, 2);
    defaultGame.move(4, 4, 2, 2);
    defaultGame.move(1, 1, 3, 3);
    defaultGame.move(4, 2, 2, 2);
    defaultGame.move(3, 0, 1, 0);
    defaultGame.move(3, 3, 1, 1);
    defaultGame.move(0, 0, 2, 0);
    defaultGame.move(2, 0, 2, 2);
    defaultGame.move(2, 2, 0, 0);
  }

  @Override
  protected void losingSetOfMoves() {
    this.makeValidGames();
    defaultGame.move(2, 0, 0, 0);
    defaultGame.move(2, 2, 2, 0);
    defaultGame.move(4, 1, 2, 1);
    defaultGame.move(4, 3, 4, 1);
    defaultGame.move(4, 0, 4, 2);
    defaultGame.move(4, 4, 2, 2);
    defaultGame.move(1, 1, 3, 3);
    defaultGame.move(4, 2, 2, 2);
    defaultGame.move(2, 0, 4, 0);
    defaultGame.move(2, 2, 4, 4);
  }

  /**
   * Tests the Triangle model getBoardSize method.
   */
  @Test
  public void testGetBoardSize() {
    makeValidGames();
    assertEquals(5, defaultGame.getBoardSize());
    assertEquals(1, armOne.getBoardSize());
    assertEquals(5, default30Empty.getBoardSize());
    assertEquals(5, armFiveMiddleEmpty.getBoardSize());
    assertEquals(7, dim7defaultEmpty.getBoardSize());
    assertEquals(7, dim7Empty66.getBoardSize());
  }

  /**
   * Tests the MarbleSolitaireModelState getSlotAt method.
   */
  @Test
  public void testGetSlotAt() {
    //default game
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultGame.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            defaultGame.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultGame.getSlotAt(4, 4));
    assertThrows(IllegalArgumentException.class, () -> defaultGame.getSlotAt(-1, -1));
    assertThrows(IllegalArgumentException.class, () -> defaultGame.getSlotAt(5, 5));

    //armOne
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, armOne.getSlotAt(0, 0));
    assertThrows(IllegalArgumentException.class, () -> armOne.getSlotAt(-1, -1));
    assertThrows(IllegalArgumentException.class, () -> armOne.getSlotAt(1, 1));

    //default30Empty
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default30Empty.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            default30Empty.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            default30Empty.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            default30Empty.getSlotAt(4, 4));
    assertThrows(IllegalArgumentException.class, () -> armOne.getSlotAt(-1, -1));
    assertThrows(IllegalArgumentException.class, () -> armOne.getSlotAt(5, 5));
  }
}
