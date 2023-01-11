package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents a general MarbleSolitaireModel.
 */
public abstract class AbstractMarbleSolitaireModel implements MarbleSolitaireModel {
  protected final SlotState[][] board;


  /**
   * Generates an MarbleSolitaireModel with the given armThickness and assigns the empty spot to
   * the given row and col position.
   *
   * @param boardDistinguisher either armThickness:
   *                           the thickness of the first row, first col, last row, or last col or
   *                           dimensions: the number of rows in a solitaire game
   * @param sRow               the row position of the empty square
   * @param sCol               the column position of the empty square
   * @throws IllegalArgumentException if the armThickness is not odd number
   * @throws IllegalArgumentException if the armThickness is not a positive number
   * @throws IllegalArgumentException if the given row and col does not represent a valid position
   */
  public AbstractMarbleSolitaireModel(int boardDistinguisher, int sRow, int sCol)
          throws IllegalArgumentException {
    if (this.getLength(boardDistinguisher) <= 0) {
      throw new IllegalArgumentException("invalid boardDistinguisher");
    }
    this.board = new SlotState[this.getLength(boardDistinguisher)]
            [this.getLength(boardDistinguisher)];
    this.generateBoard(boardDistinguisher);
    this.placeEmptySquare(sRow, sCol);
  }

  /**
   * Generates 2d array representation of the board and fills each tile with correct slot state for
   * a MarbleSolitaireModel.
   *
   * @param boardDistinguisher either armThickness:
   *                           the thickness of the first row, first col, last row, or last col or
   *                           dimensions: the number of rows in a solitaire game
   * @throws IllegalArgumentException if given boardDistinguisher is not valid, (arm thickness
   *                                  is not positive off number, or dimensions is not positive)
   */
  protected abstract void generateBoard(int boardDistinguisher)
          throws IllegalArgumentException;

  /**
   * gets the length of the board from the boardDistinguisher.
   *
   * @param boardDistinguisher either armThickness for European and English model
   *                           or dimensions for triangle model
   * @return the length of the board
   */
  protected abstract int getLength(int boardDistinguisher);

  /**
   * Places the empty square on a board.
   *
   * @param sRow the row position of the empty square
   * @param sCol the column position of the empty square
   * @throws IllegalArgumentException is the empty square position is invalid or outside the board
   */
  protected void placeEmptySquare(int sRow, int sCol)
          throws IllegalArgumentException {
    if (sRow < 0 || sCol < 0 || sCol >= this.getBoardSize() || sRow >= this.getBoardSize()
            || this.board[sRow][sCol].equals(SlotState.Invalid)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + String.valueOf(sRow)
              + "," + String.valueOf(sCol) + ")");
    }
    this.board[sRow][sCol] = SlotState.Empty;
  }

  /**
   * Gets the length of the board.
   *
   * @return the number of rows or columns
   */
  @Override
  public int getBoardSize() {
    return this.board.length;
  }


  /**
   * Gets the SlotState information about the board at a specific index.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the SlotState and a specific index on the board.
   * @throws IllegalArgumentException if the row and col position is outside the board
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row >= this.getBoardSize() || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("the given row and column are "
              + " outside the board");
    }

    return this.board[row][col];
  }

  /**
   * Gets the score of the game as represented by the number of pegs on the board.
   *
   * @return the number of pegs still on the board
   */
  @Override
  public int getScore() {
    int temp = 0;
    for (int row = 0; row < this.board.length; row++) {
      for (int col = 0; col < this.board[row].length; col++) {
        if (this.board[row][col].equals(SlotState.Marble)) {
          temp += 1;
        }
      }
    }
    return temp;
  }

  /**
   * moves a peg from one position to another position if the move is valid and removes the
   * peg between the starting position and ending position.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is no valid based on the rules of
   *                                  english solitaire
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws
          IllegalArgumentException {
    if (isMovePossible(fromRow, fromCol, toRow, toCol)) {
      moveActionsNonDiagonal(fromRow, fromCol, toRow, toCol);
    } else {
      throw new IllegalArgumentException("not a valid move");
    }
  }

  /**
   * updates model for a valid side to side or up and down move.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   */
  protected void moveActionsNonDiagonal(int fromRow, int fromCol, int toRow, int toCol) {
    this.board[fromRow][fromCol] = SlotState.Empty;
    if (fromRow + 2 == toRow) {
      this.board[fromRow + 1][fromCol] = SlotState.Empty;

    } else if (fromRow - 2 == toRow) {
      this.board[fromRow - 1][fromCol] = SlotState.Empty;

    } else if (fromCol + 2 == toCol) {
      this.board[fromRow][fromCol + 1] = SlotState.Empty;

    } else if (fromCol - 2 == toCol) {
      this.board[fromRow][fromCol - 1] = SlotState.Empty;
    }
    this.board[toRow][toCol] = SlotState.Marble;
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
            || isSideToSideMovePossible(fromRow, fromCol, toRow, toCol);
  }

  /**
   * Determines if the move conditions are valid for a move:
   * is the move outside the board, is the from position and marble and the to position empty.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @return true is the move is conditions are valid, false otherwise
   */
  protected boolean areMoveConditionsPossible(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromCol < 0 || fromRow < 0 || fromRow >= this.getBoardSize()
            || fromCol >= this.getBoardSize()) {
      return false;
    }
    if (toCol < 0 || toRow < 0 || toRow >= this.getBoardSize() || toCol >= this.getBoardSize()) {
      return false;
    }
    if (this.getSlotAt(fromRow, fromCol) != SlotState.Marble) {
      return false;
    }
    return this.getSlotAt(toRow, toCol) == SlotState.Empty;
  }

  /**
   * Determines if a side to side move from one position to another is possible.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @return true is the move is possible, false otherwise
   */
  protected boolean isSideToSideMovePossible(int fromRow, int fromCol, int toRow, int toCol) {
    if (!areMoveConditionsPossible(fromRow, fromCol, toRow, toCol)) {
      return false;
    }
    if (fromCol + 2 == toCol && this.getSlotAt(fromRow, fromCol + 1) == SlotState.Marble
            && fromRow == toRow) {
      return true;
    } else if (fromCol - 2 == toCol && this.getSlotAt(fromRow, fromCol - 1) == SlotState.Marble
            && fromRow == toRow) {
      return true;
    }
    return false;
  }

  /**
   * Determines if an up and down move from one position to another is possible.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @return true is the move is possible, false otherwise
   */
  protected boolean isUpDownMovePossible(int fromRow, int fromCol, int toRow, int toCol) {
    if (!areMoveConditionsPossible(fromRow, fromCol, toRow, toCol)) {
      return false;
    }
    if (fromRow + 2 == toRow && this.getSlotAt(fromRow + 1, fromCol) == SlotState.Marble
            && fromCol == toCol) {
      return true;
    } else if (fromRow - 2 == toRow && this.getSlotAt(fromRow - 1, fromCol) == SlotState.Marble
            && fromCol == toCol) {
      return true;
    }
    return false;

  }

  /**
   * Determines if diagonal move from one position to another is possible.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @return true is the move is possible, false otherwise
   */
  protected boolean isDiagonalMovePossible(int fromRow, int fromCol, int toRow, int toCol) {
    if (!areMoveConditionsPossible(fromRow, fromCol, toRow, toCol)) {
      return false;
    }
    if (fromRow - 2 == toRow && fromCol - 2 == toCol &&
            this.board[fromRow - 1][fromCol - 1].equals(SlotState.Marble)) {
      return true;
    }
    return fromRow + 2 == toRow && fromCol + 2 == toCol &&
            this.board[fromRow + 1][fromCol + 1].equals(SlotState.Marble);
  }

  /**
   * Determines if the game is over.
   *
   * @return true is there are no possible moves left (regardless of if the player won
   *     or ran out of moves)
   */
  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.getSlotAt(row, col).equals(SlotState.Marble)) {
          if (isMovePossible(row, col, row - 2, col)
                  || isMovePossible(row, col, row, col - 2)
                  || isMovePossible(row, col, row + 2, col)
                  || isMovePossible(row, col, row, col + 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }


}
