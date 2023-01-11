package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;


/**
 * represents the view for a Triangular Marble Solitaire game.
 */
public class TriangleSolitaireTextView extends AbstractSolitaireView {


  /**
   * Given a MarbleSolitaireModelState creates a TriangleSolitaireTextView.
   *
   * @param game the model of the marble solitaire game
   * @throws IllegalArgumentException if the given game or destination is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState game) throws IllegalArgumentException {
    super(game);
  }

  /**
   * Given a MarbleSolitaireModelState and Appendable creates a TriangleSolitaireTextView.
   *
   * @param game        the model of the marble solitaire game
   * @param destination destination of the output
   * @throws IllegalArgumentException if the given game or destination is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState game, Appendable destination)
          throws IllegalArgumentException {
    super(game, destination);
  }

  /**
   * Represents a triangle marble solitaire game with 'O' for marbles and '_' for empty squares.
   *
   * @return printed string representation of the game.
   */
  @Override
  public String toString() {


    String boardString = "";
    int counter = super.game.getBoardSize() - 1;
    for (int row = super.game.getBoardSize() - 1; row >= 0; row--) {
      String temp = "";
      for (int i = counter; i < super.game.getBoardSize() - 1; i++) {
        temp += " ";
      }

      for (int col = 0; col <= counter; col++) {

        if (super.game.getSlotAt(row, col).equals(MarbleSolitaireModelState.SlotState.Marble)) {
          temp = temp + "O";
        } else if (super.game.getSlotAt(row, col).equals(
                MarbleSolitaireModelState.SlotState.Empty)) {
          temp += "_";
        }
        if (col + 1 > counter
                || super.game.getSlotAt(row, col + 1).equals(
                MarbleSolitaireModelState.SlotState.Invalid)) {
          col = counter + 1;
        } else {
          temp = temp + " ";
        }

      }
      counter--;
      boardString = temp + "\n" + boardString;
    }
    return boardString.replaceAll("\\s+$", "");
  }

}
