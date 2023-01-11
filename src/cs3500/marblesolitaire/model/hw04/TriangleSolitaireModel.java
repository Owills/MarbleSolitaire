package cs3500.marblesolitaire.model.hw04;

/**
 * represents a marble solitaire game with triangle shaped board.
 */
public class TriangleSolitaireModel extends AbstractMarbleSolitaireModel {

  /**
   * creates a 5-row game with the empty slot at 0,0.
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  /**
   * creates a game with the specified dimensions and empty slot at 0,0.
   *
   * @param dimensions the number of rows in the game
   * @throws IllegalArgumentException if the given dimensions is non-positive
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {
    super(dimensions, 0, 0);
  }

  /**
   * creates a game with 5 rows and the empty slot at the given position.
   *
   * @param row the row position of the empty slot
   * @param col the col position of the empty slot
   * @throws IllegalArgumentException if the empty slot is not on the board
   */
  public TriangleSolitaireModel(int row, int col) throws IllegalArgumentException {
    super(5, row, col);
  }

  /**
   * creates a game with the specified dimensions and the empty slot at the given position.
   *
   * @param dimensions the number of rows
   * @param row        the row position of the empty slot
   * @param col        the col position of the empty slot
   * @throws IllegalArgumentException if the dimensions are not positive, or the empty slot
   *                                  is not on the board
   */
  public TriangleSolitaireModel(int dimensions, int row, int col) throws IllegalArgumentException {
    super(dimensions, row, col);

  }

  /**
   * determines if a move is possible and carries out move actions.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is invalid or impossible
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws
          IllegalArgumentException {
    if (areMoveConditionsPossible(fromRow, fromCol, toRow, toCol)
            && (isUpDownMovePossible(fromRow, fromCol, toRow, toCol)
            || isSideToSideMovePossible(fromRow, fromCol, toRow, toCol))) {
      moveActionsNonDiagonal(fromRow, fromCol, toRow, toCol);
    } else if (areMoveConditionsPossible(fromRow, fromCol, toRow, toCol)
            && isDiagonalMovePossible(fromRow, fromCol, toRow, toCol)) {

      super.board[fromRow][fromCol] = SlotState.Empty;
      super.board[toRow][toCol] = SlotState.Marble;

      if (fromRow - 2 == toRow && fromCol - 2 == toCol) {
        super.board[fromRow - 1][fromCol - 1] = SlotState.Empty;
      } else if (fromRow + 2 == toRow && fromCol + 2 == toCol) {
        super.board[fromRow + 1][fromCol + 1] = SlotState.Empty;
      }
    } else {
      throw new IllegalArgumentException("not a valid move");
    }

  }

  /**
   * Determines if a move from one position to another is possible.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @return true is the move is possible, false otherwise
   */
  protected boolean isMovePossible(int fromRow, int fromCol, int toRow, int toCol) {
    return isUpDownMovePossible(fromRow, fromCol, toRow, toCol)
            || isSideToSideMovePossible(fromRow, fromCol, toRow, toCol)
            || isDiagonalMovePossible(fromRow, fromCol, toRow, toCol);
  }

  /**
   * Determines if the game is over.
   *
   * @return true is there are no possible moves left (regardless of if the player won
   *     or ran out of moves)
   */
  @Override
  public boolean isGameOver() {
    return super.isGameOver() && this.anyDiagonalMoves();
  }

  /**
   * determines if any diagonal moves are possible.
   *
   * @return true if one or more diagonal moves
   */
  private boolean anyDiagonalMoves() {
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.getSlotAt(row, col).equals(SlotState.Marble)) {
          if (isMovePossible(row, col, row - 2, col - 2)
                  || isMovePossible(row, col, row + 2, col + 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }


  /**
   * Generates 2d array representation of the board and fills each tile with correct slot state for
   * a TriangleModelSolitaire game.
   *
   * @param dimensions the number of rows in a solitaire game
   * @throws IllegalArgumentException if the dimensions are not positive
   */
  @Override
  protected void generateBoard(int dimensions)
          throws IllegalArgumentException {
    if (dimensions <= 0) {
      throw new IllegalArgumentException("Dimensions must be positive");
    }
    int counter = 1;
    for (int row = 0; row < dimensions; row++) {
      for (int col = 0; col < counter; col++) {
        super.board[row][col] = SlotState.Marble;
      }
      for (int col = counter; col < dimensions; col++) {
        super.board[row][col] = SlotState.Invalid;
      }
      counter++;
    }

  }

  /**
   * gets the size of the board from the dimensions.
   *
   * @param dimensions dimensions for triangle model
   * @return the size of the board
   */
  @Override
  protected int getLength(int dimensions) {
    return dimensions;
  }


}
