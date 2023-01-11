package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

/**
 * represents a gui controller.
 */
public class GuiController implements ControllerFeatures {

  private final MarbleSolitaireGuiView view;
  private final MarbleSolitaireModel model;

  private int fromCol;
  private int fromRow;

  /**
   * given a MarbleSolitaireGuiView, and a MarbleSolitaireModel, creates a gui controller.
   *
   * @param view  the gui view
   * @param model the marble model
   * @throws IllegalArgumentException if either input si null
   */
  public GuiController(MarbleSolitaireGuiView view, MarbleSolitaireModel model)
          throws IllegalArgumentException {

    if (model == null) {
      throw new IllegalArgumentException("given MarbleSolitaireModel must not be null");
    }
    if (view == null) {
      throw new IllegalArgumentException("given MarbleSolitaireGuiView must not be null");
    }

    this.model = model;
    this.view = view;
    this.view.accept(this);
    this.view.refresh();

    this.fromCol = -1;
    this.fromRow = -1;

  }

  @Override
  public void move(int row, int col) {
    if (fromCol < 0) {
      fromRow = row;
      fromCol = col;
      view.renderMessage("From Cell Row: " + String.valueOf(row) + " Col: " + String.valueOf(col));
    } else {
      try {
        model.move(fromRow, fromCol, row, col);
        view.renderMessage("To Cell Row: " + String.valueOf(row) + " Col: " + String.valueOf(col));
        if (model.isGameOver()) {
          view.renderMessage("Game over");
        }
      } catch (IllegalArgumentException e) {
        view.renderMessage(e.getMessage());
      } finally {
        view.refresh();
        this.fromCol = -1;
        this.fromRow = -1;
      }
    }

  }
}
