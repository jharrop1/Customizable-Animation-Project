package cs5004.animator.view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.util.LinkedList;


import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AbstractShape;
import cs5004.animator.model.AvailableShapes;
import cs5004.animator.model.Point2D;

/**
 * This is the main panel in the frame that will have shapes from the model,
 * represented as graphics, painted on it.
 */
public class CanvasPanel extends JPanel {
  private Point2D reference;
  private LinkedList<AbstractShape> shapes;

  /**
   * Constructs the Canvas Panel where shapes can be drawn.
   * @param reference is the point representing the top-left corner.
   * @param dimension The dimensions for the panel.
   */
  CanvasPanel(Point2D reference, Dimension dimension) {
    this.setBackground(Color.WHITE);
    this.setSize(dimension);
  }

  /**
   * Sets the model's listOfShapes that are to be displayed.
   * @param model the model for the current state.
   */
  void setAnimatedShapes(AnimationModel model) {
    this.shapes = model.getShapes();
  }

  @Override
  protected void paintComponent(Graphics g) {
    // taking model data, converting to graphics2D objects with shapes
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    if (this.shapes != null) {
      for (AbstractShape s : this.shapes) {
        Color c = new Color(s.getR(), s.getG(), s.getB());
        if (s.getType().equals(AvailableShapes.RECTANGLE)) {
          Shape rect = new Rectangle2D.Double(s.getLocation().getX(),
              s.getLocation().getY(),
              s.getWidth(),s.getHeight());
          g2d.setColor(c);
          g2d.fill(rect);
        } else if (s.getType().equals(AvailableShapes.OVAL)) {
          Shape oval = new Ellipse2D.Double(s.getLocation().getX(),
              s.getLocation().getY(),
              s.getWidth(),s.getHeight());
          g2d.setColor(c);
          g2d.fill(oval);
        } else {
          throw new IllegalStateException("Shape must be RECT or OVAL");
        }
      }
    }
  }
}
