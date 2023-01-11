package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.ControllerFeatures;
import cs3500.marblesolitaire.controller.GuiController;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.SwingGuiView;

/**
 * entry point to gui version of this program.
 */
public class MarbleSolitaireGUI {

  /**
   * reads input from command line and sets up marble solitaire game.
   *
   * @param args command line input
   */
  public static void main(String[] args) {

    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireGuiView view = new SwingGuiView(model);
    ControllerFeatures controller = new GuiController(view, model);

  }
}
