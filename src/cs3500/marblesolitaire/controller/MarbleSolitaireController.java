package cs3500.marblesolitaire.controller;

/**
 * Represents a controller for a marble solitaire game.
 */
public interface MarbleSolitaireController {

  /**
   * Plays a new game of Marble Solitaire.
   *
   * @throws IllegalStateException if the controller is unable to read input or transmit output
   */
  void playGame() throws IllegalStateException;
}
