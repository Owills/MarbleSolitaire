package cs3500.marblesolitaire.controller;

/**
 * features for gui controller.
 */
public interface ControllerFeatures {

  /**
   * assigns inputs to from row and column or attempts to move to the inputs.
   *
   * @param row the from row or to row
   * @param col the from column or to column
   */
  void move(int row, int col);
}
