package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

import cs5004.animator.model.AnimationModel;

/**
 * Interface for the view classes.
 */
public interface IView {

  /**
   * Has the animation run (outputs vary depending on view type).
   */
  void run() throws IOException;

  /**
   * Has (visual) view update its shapes for its canvas panel for the given tick.
   * @param currentTick  The current tick at which to re paint the shapes.
   * @throws UnsupportedOperationException If the view is an SVG/Text view.
   */
  void setUpdatedShapes(int currentTick);

  /**
   * Has (PlayBackView) view update its shapes for its canvas panel for the given tick.
   * @param model The model holding the shapes at the current tick (from controller)
   * @throws UnsupportedOperationException If the view is an SVG/Text/Visual view.
   */
  void setCurrentShapes(AnimationModel model);

  /**
   * Get the view's type (one of 3 types - visual, textual, SVG enum).
   * @return The type of view (enum)
   */
  ViewType getViewType();

  /**
   * Set the speed of the view given a speed (per tick).
   * @param speed updated speed of the view.
   * @throws UnsupportedOperationException if the view type is a TextualView (speed does not impact
   *                                      TextualView output).
   */
  void setSpeed(int speed) throws UnsupportedOperationException;

  /**
   * Sets the listeners for keyclicks.
   * @param clicks the mouse clicks.
   * @param keys the keyclicks.
   */
  void setListeners(ActionListener clicks, KeyListener keys);
}
