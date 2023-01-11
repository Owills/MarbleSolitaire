package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractMarbleSolitaireModel;

/**
 * This class represents an English Solitaire version of marble solitaire
 * characterized by a plus shaped board.
 */
public class EnglishSolitaireModel extends AbstractMarbleSolitaireModel {


  /**
   * Default constructor, creates an EnglishSolitaireModel
   * with arm Thickness 3 and empty spot in the center.
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Generates an EnglishSolitaireModel with the empty spot at the given row and col position.
   *
   * @param sRow the row position of the empty square
   * @param sCol the column position of the empty square
   * @throws IllegalArgumentException if the given row and col does not represent a valid position
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
  }

  /**
   * Generates an EnglishSolitaireModel with the given armThickness.
   *
   * @param armThickness the thickness of the first row, first col, last row, or last col
   * @throws IllegalArgumentException if the armThickness is not odd number
   * @throws IllegalArgumentException if the armThickness is not a positive number
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness, (int) Math.floor(((3 * armThickness) - 2) / 2),
            (int) Math.floor(((3 * armThickness) - 2) / 2));
  }

  /**
   * Generates an EnglishSolitaireModel with the given armThickness and assigns the empty spot to
   * the given row and col position.
   *
   * @param armThickness the thickness of the first row, first col, last row, or last col
   * @param sRow         the row position of the empty square
   * @param sCol         the column position of the empty square
   * @throws IllegalArgumentException if the armThickness is not odd number
   * @throws IllegalArgumentException if the armThickness is not a positive number
   * @throws IllegalArgumentException if the given row and col does not represent a valid position
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }

  /**
   * Generates 2d array representation of the board and fills each tile with correct slot state.
   *
   * @param armThickness the thickness of the first row, first col, last row, or last col
   * @throws IllegalArgumentException if the given row and col does not represent a valid position
   *                                  or is outside the board or the given armThickness
   *                                  is not a positive off number
   */
  protected void generateBoard(int armThickness)
          throws IllegalArgumentException {

    if (armThickness % 2 != 1) {
      throw new IllegalArgumentException("Arm Thickness must be a positive odd number");
    }

    int length = 3 * armThickness - 2;

    for (int row = 0; row < super.board.length; row++) {
      for (int col = 0; col < super.board[row].length; col++) {
        // top left corner
        if (row < (length - armThickness) / 2 && col < (length - armThickness) / 2) {
          super.board[row][col] = SlotState.Invalid;
          // top right corner
        } else if (row < armThickness - 1 && col > length - armThickness) {
          super.board[row][col] = SlotState.Invalid;
          // bottom left corner
        } else if (row > length - armThickness && col < (length - armThickness) / 2) {
          super.board[row][col] = SlotState.Invalid;
          // bottom right corner
        } else if (row > length - armThickness && col > length - armThickness) {
          super.board[row][col] = SlotState.Invalid;
          // fill everything else in as a marble
        } else {
          super.board[row][col] = SlotState.Marble;
        }
      }
    }
  }

  /**
   * Gets the length of the board from the armThickness.
   *
   * @param armThickness the thickness of the first row, first col, last row, or last col
   * @return the length of the board
   */
  @Override
  protected int getLength(int armThickness) {
    return 3 * armThickness - 2;
  }


}
