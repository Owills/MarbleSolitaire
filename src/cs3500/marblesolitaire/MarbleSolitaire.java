package cs3500.marblesolitaire;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * entry point to this program.
 */
public final class MarbleSolitaire {
  /**
   * reads input from command line and sets up marble solitaire game.
   *
   * @param args command line input
   */
  public static void main(String[] args) {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireController controller;
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    int size = 0;
    int row = 0;
    int col = 0;

    //set defualts
    if (args[0].equalsIgnoreCase("english")) {
      size = 3;
      row = 3;
      col = 3;
    } else if (args[0].equalsIgnoreCase("european")) {
      size = 3;
      row = 3;
      col = 3;
    } else if (args[0].equalsIgnoreCase("triangular")) {
      size = 5;
      row = 0;
      col = 0;
    }

    int i = 1;
    while (i < args.length) {
      if (args[i].equals("-size")) {
        size = Integer.parseInt(args[i + 1]);
        i += 2;
      } else if (args[i].equals("-hole")) {
        row = Integer.parseInt(args[i + 1]);
        col = Integer.parseInt(args[i + 2]);
        i += 3;
      }
    }

    if (args[0].equalsIgnoreCase("english")) {

      model = new EnglishSolitaireModel(size, row, col);
      view = new MarbleSolitaireTextView(model);
    } else if (args[0].equalsIgnoreCase("european")) {

      model = new EuropeanSolitaireModel(size, row, col);
      view = new MarbleSolitaireTextView(model);
    } else if (args[0].equalsIgnoreCase("triangular")) {

      model = new TriangleSolitaireModel(size, row, col);
      view = new TriangleSolitaireTextView(model);
    }


    controller = new MarbleSolitaireControllerImpl(model, view, new StringReader(""));

    System.out.println(view.toString());
  }
}
