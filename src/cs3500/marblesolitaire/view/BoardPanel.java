package cs3500.marblesolitaire.view;

import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import cs3500.marblesolitaire.controller.ControllerFeatures;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * represent the gui board.
 */
public class BoardPanel extends JPanel implements BoardPanelInterface {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot;
  private Image marbleSlot;
  private Image blankSlot;
  private final int cellDimension;
  private int originX;
  private int originY;

  /**
   * creates a board panel from a MarbleSolitaireModelState.
   * @param state the MarbleSolitaireModelState
   * @throws IllegalStateException if the image icons are not found
   */
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
              new Dimension((this.modelState.getBoardSize() + 4) * cellDimension
                      , (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    originX = (int) (this.getPreferredSize().getWidth() /
            2 - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() /
            2 - this.modelState.getBoardSize() * cellDimension / 2);


    for (int i = 0; i < modelState.getBoardSize(); i++) {
      for (int j = 0; j < modelState.getBoardSize(); j++) {
        if (modelState.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Empty)) {
          g.drawImage(emptySlot, originX, originY, this);
        } else if (modelState.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Marble)) {
          g.drawImage(marbleSlot, originX, originY, this);
        } else if (modelState.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Invalid)) {
          g.drawImage(blankSlot, originX, originY, this);
        }
        originX += cellDimension;
      }
      originY += cellDimension;
      originX = (int) (this.getPreferredSize().getWidth() /
              2 - this.modelState.getBoardSize() * cellDimension / 2);
    }

    originX = (int) (this.getPreferredSize().getWidth() /
            2 - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() /
            2 - this.modelState.getBoardSize() * cellDimension / 2);
  }


  @Override
  public void accept(ControllerFeatures c) {
    MouseListener m = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int x = (e.getX() - originX) / cellDimension;
        int y = (e.getY() - originY) / cellDimension;
        c.move(y, x);
      }
    };
    this.addMouseListener(m);
  }
}
