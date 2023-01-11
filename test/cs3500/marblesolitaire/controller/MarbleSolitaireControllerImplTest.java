package cs3500.marblesolitaire.controller;

import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * tests arbleSolitaireControllerImpl class.
 */
public class MarbleSolitaireControllerImplTest {
  Readable oneMoveQuitq;
  Readable oneMoveQuitQ;

  Readable quitq;
  Readable quitQ;

  Readable nonsenseQuitq;
  Readable nonsenseQuitQ;
  // nonsense characters in after move inputs
  Readable oneMoveNonsenseQuit;
  // nonsense characters in between move inputs
  Readable oneMoveNonsenseQuitv2;
  // nonsense characters in before move inputs
  Readable oneMoveNonsenseQuitv3;

  Readable negativeNumberQuit;
  // negative number after move inputs
  Readable oneMoveNegativeQuit;
  // negative number in between move inputs
  Readable oneMoveNegativeQuitv2;
  // negative number before move inputs
  Readable oneMoveNegativeQuitv3;

  Readable incompleteMoveQuitq;
  Readable moveIncompleteMoveQuitq;

  Readable winningSetOfMoves;
  Readable losingSetOfMoves;

  Readable winningSetOfMovesMoreReadable;
  Readable losingSetOfMovesMoreReadable;

  Readable oneMove;
  Readable nonsense;
  Readable negativeNumber;
  Readable incompleteMove;
  Readable invalidMove;

  Readable incompleteMove2q;
  Readable incompleteMove3q;

  // outside the board
  Readable invalidMoveQuit;
  // on the board, move not possible
  Readable invalidMoveQuitv2;
  Readable moveinvalidMoveQuit;
  Readable invalidMoveMoveQuit;

  MarbleSolitaireModel triangleGame;
  MarbleSolitaireModel europeanGame;

  MarbleSolitaireModel defaultGame;
  MarbleSolitaireModel armThickness1Game;

  MarbleSolitaireView textViewDefaultGame;
  MarbleSolitaireController defaultController;

  Appendable systemOut;

  public MarbleSolitaireControllerImplTest() {
    this.setDefualts();
  }

  /**
   * Tests MarbleSolitaireControllerImpl constructor.
   */
  @Test
  public void testConstructor() {
    setDefualts();
    assertThrows(IllegalArgumentException.class, () ->
            defaultController = new MarbleSolitaireControllerImpl(null,
                    null,
                    null));

    assertThrows(IllegalArgumentException.class, () ->
            defaultController = new MarbleSolitaireControllerImpl(defaultGame,
                    null,
                    null));

    assertThrows(IllegalArgumentException.class, () ->
            defaultController = new MarbleSolitaireControllerImpl(null,
                    textViewDefaultGame,
                    null));

    assertThrows(IllegalArgumentException.class, () ->
            defaultController = new MarbleSolitaireControllerImpl(null,
                    null,
                    oneMove));

    assertThrows(IllegalArgumentException.class, () ->
            defaultController = new MarbleSolitaireControllerImpl(defaultGame,
                    textViewDefaultGame,
                    null));

    assertThrows(IllegalArgumentException.class, () ->
            defaultController = new MarbleSolitaireControllerImpl(defaultGame,
                    null,
                    oneMove));

    assertThrows(IllegalArgumentException.class, () ->
            defaultController = new MarbleSolitaireControllerImpl(null,
                    textViewDefaultGame,
                    oneMove));
  }

  /**
   * Sets default variables to be used in tests.
   */
  private void setDefualts() {
    oneMoveQuitq = new StringReader("2 4 4 4 q");
    oneMoveQuitQ = new StringReader("2 4    4 4 Q");
    quitq = new StringReader("q");
    quitQ = new StringReader("Q");
    nonsenseQuitq = new StringReader("k q");
    nonsenseQuitQ = new StringReader("jkms Q");

    oneMoveNonsenseQuit = new StringReader("2 4 4 4 jkms q");
    oneMoveNonsenseQuitv2 = new StringReader("2 jk 4 ms 4 k 4 q");
    oneMoveNonsenseQuitv3 = new StringReader("jk ms k 2 4 4 4 q");

    negativeNumberQuit = new StringReader("-1 q");
    oneMoveNegativeQuit = new StringReader("2 4 4 4 -1 q");
    oneMoveNegativeQuitv2 = new StringReader("2 -1 4 -1 4 -1 4 q");
    oneMoveNegativeQuitv3 = new StringReader("-1 -1 -1 2 4 4 4 q");
    incompleteMoveQuitq = new StringReader("2 4 4 q");
    moveIncompleteMoveQuitq = new StringReader("2 4 4 4 1 4 2  q");

    oneMove = new StringReader("2 4 4 4");
    nonsense = new StringReader("jk ms s");
    negativeNumber = new StringReader("-1");
    incompleteMove = new StringReader("2 4 4");
    invalidMove = new StringReader("2 4 4 64");

    incompleteMove2q = new StringReader("2 4 q");
    incompleteMove3q = new StringReader("2 4 4 q");

    invalidMoveQuit = new StringReader("2 4 4 64 q");
    invalidMoveQuitv2 = new StringReader("1 4 4 4 q");
    moveinvalidMoveQuit = new StringReader("2 4 4 4 1 4 4 4 q");
    invalidMoveMoveQuit = new StringReader("1 4 4 4 2 4 4 4 q");

    winningSetOfMoves = new StringReader(
            "2 4 4 4 " +
                    "3 2 3 4 " +
                    "1 3 3 3 " +
                    "4 3 2 3 " +
                    "3 5 3 3 " +
                    "6 3 4 3 " +
                    "5 1 5 3 " +
                    "5 4 5 2 " +
                    "3 7 3 5 " +
                    "3 1 5 1 " +
                    "5 1 5 3 " +
                    "5 6 5 4 " +
                    "5 4 5 2 " +
                    "5 2 3 2 " +
                    "3 2 3 4 " +
                    "3 4 3 6 " +
                    "7 5 5 5 " +
                    "4 5 6 5 " +
                    "5 7 3 7 " +
                    "3 7 3 5 " +
                    "7 3 7 5 " +
                    "7 5 5 5 " +
                    "1 5 1 3 " +
                    "1 3 3 3 " +
                    "3 3 5 3 " +
                    "2 5 4 5 " +
                    "4 5 6 5 " +
                    "6 5 6 3 " +
                    "6 3 4 3 " +
                    "4 3 4 5 " +
                    "4 6 4 4");

    losingSetOfMoves = new StringReader(
            "2 4 4 4 " +
                    "3 2 3 4 " +
                    "1 3 3 3 " +
                    "4 3 2 3 " +
                    "3 5 3 3 " +
                    "6 3 4 3 " +
                    "5 1 5 3 " +
                    "5 4 5 2 " +
                    "3 7 3 5 " +
                    "3 1 5 1 " +
                    "5 1 5 3 " +
                    "5 6 5 4 " +
                    "5 4 5 2 " +
                    "5 2 3 2 " +
                    "3 2 3 4 " +
                    "3 4 3 6 " +
                    "7 5 5 5 " +
                    "4 5 6 5 " +
                    "5 7 3 7 " +
                    "3 7 3 5 " +
                    "7 3 7 5 " +
                    "7 5 5 5 " +
                    "1 5 1 3 " +
                    "1 3 3 3 " +
                    "3 3 5 3 " +
                    "3 5 1 5");

    winningSetOfMovesMoreReadable = new StringReader(
            "2 4 4 4 " +
                    "3 2 3 4 " +
                    "1 3 3 3 " +
                    "4 3 2 3 " +
                    "3 5 3 3 " +
                    "6 3 4 3 " +
                    "5 1 5 3 " +
                    "5 4 5 2 " +
                    "3 7 3 5 " +
                    "3 1 5 1 " +
                    "5 1 5 3 " +
                    "5 6 5 4 " +
                    "5 4 5 2 " +
                    "5 2 3 2 " +
                    "3 2 3 4 " +
                    "3 4 3 6 " +
                    "7 5 5 5 " +
                    "4 5 6 5 " +
                    "5 7 3 7 " +
                    "3 7 3 5 " +
                    "7 3 7 5 " +
                    "7 5 5 5 " +
                    "1 5 1 3 " +
                    "1 3 3 3 " +
                    "3 3 5 3 " +
                    "2 5 4 5 " +
                    "4 5 6 5 " +
                    "6 5 6 3 " +
                    "6 3 4 3 " +
                    "4 3 4 5 " +
                    "4 6 4 4 " +
                    "0 0 0 0 " +
                    "-1 qhms q");

    losingSetOfMovesMoreReadable = new StringReader(
            "2 4 4 4 " +
                    "3 2 3 4 " +
                    "1 3 3 3 " +
                    "4 3 2 3 " +
                    "3 5 3 3 " +
                    "6 3 4 3 " +
                    "5 1 5 3 " +
                    "5 4 5 2 " +
                    "3 7 3 5 " +
                    "3 1 5 1 " +
                    "5 1 5 3 " +
                    "5 6 5 4 " +
                    "5 4 5 2 " +
                    "5 2 3 2 " +
                    "3 2 3 4 " +
                    "3 4 3 6 " +
                    "7 5 5 5 " +
                    "4 5 6 5 " +
                    "5 7 3 7 " +
                    "3 7 3 5 " +
                    "7 3 7 5 " +
                    "7 5 5 5 " +
                    "1 5 1 3 " +
                    "1 3 3 3 " +
                    "3 3 5 3 " +
                    "3 5 1 5 0 3 4 1 -1 qms q");

    defaultGame = new EnglishSolitaireModel();
    armThickness1Game = new EnglishSolitaireModel(1);

    triangleGame = new TriangleSolitaireModel();
    europeanGame = new EuropeanSolitaireModel();

    systemOut = new StringBuilder();
    textViewDefaultGame = new MarbleSolitaireTextView(defaultGame, systemOut);

  }

  /**
   * test controller can also play game with triangle model.
   */
  @Test
  public void testPlayGame8() {
    //test playGame one move quit with "q"
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(triangleGame,
            new TriangleSolitaireTextView(triangleGame, systemOut),
            new StringReader("3 1 1 1 q"));
    assertFalse(triangleGame.isGameOver());
    defaultController.playGame();
    assertEquals("    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O\n" +
                    "Score: 14\n" +
                    "    O\n" +
                    "   _ O\n" +
                    "  _ O O\n" +
                    " O O O O\n" +
                    "O O O O O\n" +
                    "Score: 13\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O\n" +
                    "   _ O\n" +
                    "  _ O O\n" +
                    " O O O O\n" +
                    "O O O O O\n" +
                    "Score: 13",
            systemOut.toString());
    assertFalse(triangleGame.isGameOver());
    assertEquals(13, triangleGame.getScore());
  }

  /**
   * test controller can also play game with european model.
   */
  @Test
  public void testPlayGame7() {
    //test playGame one move quit with "q"
    this.setDefualts();
    textViewDefaultGame = new MarbleSolitaireTextView(europeanGame, systemOut);
    defaultController = new MarbleSolitaireControllerImpl(europeanGame,
            textViewDefaultGame, oneMoveQuitq);
    assertFalse(europeanGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O\n" +
                    "Score: 36\n" +
                    "    O O O\n" +
                    "  O O _ O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O\n" +
                    "Score: 35\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "  O O _ O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O\n" +
                    "Score: 35",
            systemOut.toString());
    assertFalse(europeanGame.isGameOver());
    assertEquals(35, europeanGame.getScore());
  }

  /**
   * tests situations where the game ends without quitting.
   */
  @Test
  public void testPlayGame6() {
    //test playGame on armThickness 1 (game won from default position)
    this.setDefualts();
    textViewDefaultGame = new MarbleSolitaireTextView(armThickness1Game, systemOut);
    defaultController = new MarbleSolitaireControllerImpl(armThickness1Game,
            textViewDefaultGame, losingSetOfMovesMoreReadable);
    assertTrue(armThickness1Game.isGameOver());
    assertEquals(0, armThickness1Game.getScore());
    defaultController.playGame();
    assertEquals(
            "Game over!\n" +
                    "_\n" +
                    "Score: 0",
            systemOut.toString());
    assertEquals(0, armThickness1Game.getScore());

    // test playGame losing set of moves with more readable
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, losingSetOfMovesMoreReadable);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertTrue(systemOut.toString().contains("Game over!\n"));
    assertTrue(defaultGame.isGameOver());
    assertEquals(6, defaultGame.getScore());

    // test playGame losing set of moves
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, losingSetOfMoves);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertTrue(systemOut.toString().contains("Game over!\n"));
    assertTrue(defaultGame.isGameOver());
    assertEquals(6, defaultGame.getScore());

    // test playGame winning set of moves
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, winningSetOfMoves);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertTrue(systemOut.toString().contains("Game over!\n"));
    assertTrue(defaultGame.isGameOver());
    assertEquals(1, defaultGame.getScore());

    // test playGame winning set of moves plus more readable
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, winningSetOfMovesMoreReadable);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertTrue(systemOut.toString().contains("Game over!\n"));
    assertTrue(defaultGame.isGameOver());
    assertEquals(1, defaultGame.getScore());

  }


  /**
   * tests combinations of invalid moves and one move.
   */
  @Test
  public void testPlayGame5() {
    // test playGame invalid move, move, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, invalidMoveMoveQuit);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Invalid move. Play again. not a valid move\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(31, defaultGame.getScore());

    // test playGame move, invalid move, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, moveinvalidMoveQuit);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Invalid move. Play again. not a valid move\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(31, defaultGame.getScore());

    // test playGame invalid move, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, invalidMoveQuitv2);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Invalid move. Play again. not a valid move\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

    // test playGame invalid move, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, invalidMoveQuit);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Invalid move. Play again. not a valid move\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

  }

  /**
   * tests moves, negative numbers, invalid moves, nonsense without quiting.
   */
  @Test
  public void testPlayGame4() {
    // test playGame invalid move
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, invalidMove);
    assertFalse(defaultGame.isGameOver());
    assertThrows(IllegalStateException.class, () ->
            defaultController.playGame());
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Invalid move. Play again. not a valid move\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

    // test playGame incomplete move
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, incompleteMove);
    assertFalse(defaultGame.isGameOver());
    assertThrows(IllegalStateException.class, () ->
            defaultController.playGame());
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

    // test playGame negative number
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, negativeNumber);
    assertFalse(defaultGame.isGameOver());
    assertThrows(IllegalStateException.class, () ->
            defaultController.playGame());
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Re-enter the next the value.\n",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

    // test playGame nonsense
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, nonsense);
    assertFalse(defaultGame.isGameOver());
    assertThrows(IllegalStateException.class, () ->
            defaultController.playGame());
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Re-enter the next the value.\n" +
                    "Re-enter the next the value.\n" +
                    "Re-enter the next the value.\n",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

    // test playGame one move
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, oneMove);
    assertFalse(defaultGame.isGameOver());
    assertThrows(IllegalStateException.class, () ->
            defaultController.playGame());
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(31, defaultGame.getScore());
  }

  /**
   * tests playGame with combinations of negative numbers and one valid move.
   */
  @Test
  public void testPlayGame3() {
    // test playGame incomplete move, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, moveIncompleteMoveQuitq);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(31, defaultGame.getScore());

    // test playGame incomplete move, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, incompleteMoveQuitq);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

    // test playGame incomplete move, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, incompleteMove2q);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

    // test playGame incomplete move, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, incompleteMove3q);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

    // test playGame negative, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, negativeNumberQuit);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Re-enter the next the value.\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

    // test playGame move inputs, then negative, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, oneMoveNegativeQuit);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Re-enter the next the value.\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(31, defaultGame.getScore());

    // test playGame negative numbers inbetween move inputs, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, oneMoveNegativeQuitv2);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Re-enter the next the value.\n" +
                    "Re-enter the next the value.\n" +
                    "Re-enter the next the value.\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(31, defaultGame.getScore());

    // test playGame negative, then move, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, oneMoveNegativeQuitv3);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Re-enter the next the value.\n" +
                    "Re-enter the next the value.\n" +
                    "Re-enter the next the value.\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(31, defaultGame.getScore());
  }

  /**
   * Tests playGame with one move, and random inputs before, between and after valid numbers.
   */
  @Test
  public void testPlayGame2() {
    // test playGame random, then move, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, oneMoveNonsenseQuitv3);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Re-enter the next the value.\n" +
                    "Re-enter the next the value.\n" +
                    "Re-enter the next the value.\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(31, defaultGame.getScore());

    // test playGame one move, random inputs inbetween numbers, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, oneMoveNonsenseQuitv2);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Re-enter the next the value.\n" +
                    "Re-enter the next the value.\n" +
                    "Re-enter the next the value.\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(31, defaultGame.getScore());

    // test playGame one move, then random, then quit
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, oneMoveNonsenseQuit);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Re-enter the next the value.\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(31, defaultGame.getScore());
  }

  /**
   * Tests playGame method with basic moves, nonsense input, and quiting with "q" and "Q".
   */
  @Test
  public void testPlayGame() {
    //test playGame one move quit with "q"
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, oneMoveQuitq);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(31, defaultGame.getScore());

    //test playGame one move quit with "Q"
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, oneMoveQuitQ);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O _ O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 31",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(31, defaultGame.getScore());

    //test playGame quit with "q"
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, quitq);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

    //test playGame quit with "Q"
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, quitq);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

    //test playGame nonsense then quit with "q"
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, nonsenseQuitq);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Re-enter the next the value.\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());

    //test playGame nonsense then quit with "Q"
    this.setDefualts();
    defaultController = new MarbleSolitaireControllerImpl(defaultGame,
            textViewDefaultGame, nonsenseQuitQ);
    assertFalse(defaultGame.isGameOver());
    defaultController.playGame();
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Re-enter the next the value.\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32",
            systemOut.toString());
    assertFalse(defaultGame.isGameOver());
    assertEquals(32, defaultGame.getScore());
  }


}
