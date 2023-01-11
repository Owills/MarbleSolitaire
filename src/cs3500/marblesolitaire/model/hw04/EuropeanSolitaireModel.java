package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * This class represents an English Solitaire version of marble solitaire
 * characterized by a plus shaped board.
 */
public class EuropeanSolitaireModel extends AbstractMarbleSolitaireModel {


  /**
   * Default constructor, creates an EnglishSolitaireModel
   * with arm Thickness 3 and empty spot in the center.
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Generates an EuropeanSolitaireModel with the empty spot at the given row and col position.
   *
   * @param sRow the row position of the empty square
   * @param sCol the column position of the empty square
   * @throws IllegalArgumentException if the given row and col does not represent a valid position
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
  }

  /**
   * Generates an EuropeanSolitaireModel with the given armThickness.
   *
   * @param armThickness the thickness of the first row, first col, last row, or last col
   * @throws IllegalArgumentException if the armThickness is not odd number
   * @throws IllegalArgumentException if the armThickness is not a positive number
   */
  public EuropeanSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness, (int) Math.floor(((3 * armThickness) - 2) / 2),
            (int) Math.floor(((3 * armThickness) - 2) / 2));
  }

  /**
   * Generates an EuropeanSolitaireModel with the given armThickness and assigns the empty spot to
   * the given row and col position.
   *
   * @param armThickness the thickness of the first row, first col, last row, or last col
   * @param sRow         the row position of the empty square
   * @param sCol         the column position of the empty square
   * @throws IllegalArgumentException if the armThickness is not odd number
   * @throws IllegalArgumentException if the armThickness is not a positive number
   * @throws IllegalArgumentException if the given row and col does not represent a valid position
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }

  /**
   * Generates 2d array representation of the board and fills each tile with correct slot state for
   * a EuropeanSolitaireModel.
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

    EnglishSolitaireModel startingPoint = new EnglishSolitaireModel(armThickness);
    int length = 3 * armThickness - 2;

    // make the board the same as the english solitaire model
    for (int row = 0; row < super.board.length; row++) {
      for (int col = 0; col < super.board[row].length; col++) {
        super.board[row][col] = startingPoint.getSlotAt(row, col);
        if (startingPoint.getSlotAt(row, col).equals(SlotState.Empty)) {
          super.board[row][col] = SlotState.Marble;
        }
      }
    }

    // fill in the corners to make an octagon
    // top left
    for (int row = 1; row < armThickness - 1; row++) {
      for (int col = armThickness - 1; col >= armThickness - 1 - row; col--) {
        super.board[row][col] = SlotState.Marble;
      }
    }

    // top right
    for (int row = 1; row < armThickness - 1; row++) {
      for (int col = length - armThickness; col < length - armThickness + 1 + row; col++) {
        super.board[row][col] = SlotState.Marble;
      }
    }

    // bottom left
    for (int row = length - armThickness + 1; row < length - 1; row++) {
      for (int col = armThickness - 1; col >= armThickness - length + row; col--) {
        super.board[row][col] = SlotState.Marble;
      }
    }

    int num = 0;
    // bottom right
    for (int row = length - armThickness + 1; row < length - 1; row++) {
      num = num + 1;
      for (int col = length - armThickness; col < length - num; col++) {
        super.board[row][col] = SlotState.Marble;
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