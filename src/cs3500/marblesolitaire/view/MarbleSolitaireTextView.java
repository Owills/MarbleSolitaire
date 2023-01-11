package cs3500.marblesolitaire.view;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * View a EnglishMarbleSolitaire or EuropeanMarbleSolitaireGame as a printed string.
 */
public class MarbleSolitaireTextView extends AbstractSolitaireView {

  /**
   * Given a MarbleSolitaireModelState creates a MarbleSolitaireTextView.
   *
   * @param game the model of the marble solitaire game
   * @throws IllegalArgumentException if the given game or destination is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game) throws IllegalArgumentException {
    super(game);
  }

  /**
   * Given a MarbleSolitaireModelState and Appendable creates a MarbleSolitaireTextView.
   *
   * @param game        the model of the marble solitaire game
   * @param destination destination of the output
   * @throws IllegalArgumentException if the given game or destination is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game, Appendable destination)
          throws IllegalArgumentException {
    super(game, destination);
  }

  /**
   * Represents a marble solitaire game with 'O' for marbles and '_' for empty squares.
   *
   * @return printed string representation of the game.
   */
  @Override
  public String toString() {
    String boardString = "";
    for (int row = 0; row < game.getBoardSize(); row++) {
      String temp = "";
      for (int col = 0; col < game.getBoardSize(); col++) {
        if (game.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty) {
          temp += "_";
        } else if (game.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Invalid) {
          temp += " ";
        } else if (game.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble) {
          temp += "O";
        }
        temp += " ";
      }
      temp = temp.replaceAll("\\s+$", "");
      boardString = boardString + temp + "\n";
    }
    return boardString.replaceAll("\\s+$", "");
  }


}
