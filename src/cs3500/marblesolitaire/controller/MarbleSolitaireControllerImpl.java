package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents the first implementation of a MarbleSolitaireController, allowing basic game play
 * moves with four positive numbers, and quiting with "q" or "Q".
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private Readable input;

  /**
   * Given a MarbleSolitaireModel, MarbleSolitaireView and readable creates a
   * MarbleSolitaireControllerImpl.
   *
   * @param model represents the model of a marble solitaire game
   * @param view  represents how a user will view this marble solitaire game
   * @param input reads input from the user
   * @throws IllegalArgumentException if any of the given arguments are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view,
                                       Readable input) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("given MarbleSolitaireModel must not be null");
    }
    if (view == null) {
      throw new IllegalArgumentException("given MarbleSolitaireView must not be null");
    }
    if (input == null) {
      throw new IllegalArgumentException("given input as Readable must not be null");
    }

    this.model = model;
    this.view = view;
    this.input = input;
  }

  /**
   * Renders the current state of the game to the view, transmits the score to the view,
   * obtains next user input for moves or 'q' 'Q' to quit:
   * (row number from position beginning at one,
   * col number from position beginning at one,
   * row number to position beginning at one,
   * col number to position beginning at one) seperated by spaces or new line,
   * and passes them to the model,
   * repeats until the game is over.
   *
   * @throws IllegalStateException if input can't be read or output can't be transmitted
   */
  @Override
  public void playGame() throws IllegalStateException {
    boolean hasQuit = false;
    Scanner in = new Scanner(input);

    while (!this.model.isGameOver() && !hasQuit) {
      try {
        this.view.renderBoard();
        this.view.renderMessage("\nScore: " + Integer.toString(this.model.getScore()) + "\n");
      } catch (IOException e) {
        throw new IllegalStateException("Unable to transmit output");
      }

      // handle user input, moving, quitting game, and invalid input
      int rowFrom = -1;
      int colFrom = -1;
      int rowTo = -1;
      int colTo = -1;
      //once counter is 4 we know we have all the inputted numbers
      int counter = 0;

      while (counter < 4) {
        if (!in.hasNext() && !this.model.isGameOver()) {
          throw new IllegalStateException("no more inputs");
        }

        if (in.hasNextInt()) {
          int num = in.nextInt();
          if (num <= 0) {
            this.helpRenderMessage("Re-enter the next the value.\n",
                    "Unable to transmit output");
          } else {
            counter += 1;
            if (counter == 1) {
              rowFrom = num - 1;
            } else if (counter == 2) {
              colFrom = num - 1;
            } else if (counter == 3) {
              rowTo = num - 1;
            } else if (counter == 4) { // a move can be played
              colTo = num - 1;
              try {
                this.model.move(rowFrom, colFrom, rowTo, colTo);
              } catch (IllegalArgumentException iae) {
                this.helpRenderMessage("Invalid move. Play again. " + iae.getMessage() + "\n",
                        "Unable to transmit output");
              }
            }
          }
        } else if (in.hasNext()) {
          String temp = in.next();
          if (temp.equalsIgnoreCase("q")) {
            hasQuit = true;
            counter = 4;
            this.helpRenderMessage("Game quit!\nState of game when quit:\n",
                    "Unable to transmit output");
            this.helpRenderBoard();
            this.helpRenderMessage("\nScore: " + Integer.toString(this.model.getScore()),
                    "Unable to transmit output");
          } else {
            this.helpRenderMessage("Re-enter the next the value.\n",
                    "Unable to transmit output");
          }
        }
      }
    }

    // Game ended not by quiting
    if (!hasQuit) {
      this.helpRenderMessage("Game over!\n", "Unable to transmit output");
      this.helpRenderBoard();
      this.helpRenderMessage("\nScore: " + Integer.toString(this.model.getScore()),
              "Unable to transmit output");
    }
  }

  /**
   * playMove helper, helps renderMessage with try catch.
   */
  private void helpRenderMessage(String s, String error) {
    try {
      this.view.renderMessage(s);
    } catch (IOException e) {
      throw new IllegalStateException(error);
    }
  }

  /**
   * playMove helper, helps renderBoard with try catch.
   */
  private void helpRenderBoard() {
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to transmit output");
    }
  }

}
















