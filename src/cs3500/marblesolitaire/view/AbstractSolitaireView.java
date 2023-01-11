package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * general text view for a marble solitaire game.
 */
public abstract class AbstractSolitaireView implements MarbleSolitaireView {
  protected final MarbleSolitaireModelState game;
  private final Appendable destination;


  /**
   * Given a MarbleSolitaireModelState creates a AbstractSolitaireView.
   *
   * @param game the model of the marble solitaire game
   * @throws IllegalArgumentException if the given game or destination is null
   */
  public AbstractSolitaireView(MarbleSolitaireModelState game) throws IllegalArgumentException {
    if (game == null) {
      throw new IllegalArgumentException("Given model cannot be null");
    }
    this.game = game;
    this.destination = System.out;
  }

  /**
   * Given a MarbleSolitaireModelState and Appendable creates a AbstractSolitaireView.
   *
   * @param game        the model of the marble solitaire game
   * @param destination destination of the output
   * @throws IllegalArgumentException if the given game or destination is null
   */
  public AbstractSolitaireView(MarbleSolitaireModelState game, Appendable destination)
          throws IllegalArgumentException {
    if (game == null) {
      throw new IllegalArgumentException("Given model cannot be null");
    }
    if (destination == null) {
      throw new IllegalArgumentException("Given destination cannot be null");
    }
    this.game = game;
    this.destination = destination;
  }

  /**
   * Represents a marble solitaire game with 'O' for marbles and '_' for empty squares.
   *
   * @return printed string representation of the game.
   */
  public abstract String toString();

  /**
   * Transmits state of the board to the destination.
   *
   * @throws IOException if the Transmission fails
   */
  @Override
  public void renderBoard() throws IOException {
    this.renderMessage(this.toString());
  }

  /**
   * Transmits a message to the destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if the Transmission fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.destination.append(message);
    } catch (IOException e) {
      throw new IOException("Transmission to destination failed");
    }
  }
}
