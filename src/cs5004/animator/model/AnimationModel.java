package cs5004.animator.model;

import java.util.LinkedList;

/**
 * This interface contains all operations that all types of shapes
 * should support.
 */

public interface AnimationModel {


  /**
   * Determines if shape exists in the model given a label.
   * @param label String ID for possibly existent shape
   * @return boolean indicating existence of the shape
   */
  boolean hasShape(String label);

  /**
   * Gets all the shapes in the model.
   * @return list of shapes in the model
   */
  LinkedList<AbstractShape> getShapes();

  /**
   * Returns the shape with the given identifier.
   * @param label the identifier of the shape.
   * @return the shape with the identifier.
   */
  AbstractShape getShape(String label);

  /**
   * Gets all the changes altering the shapes in the model.
   * @return list of changes in the model
   */
  LinkedList<AbstractChange> getChanges();


  /**
   * Gets the canvas for the animation.
   * @return the animations canvas object
   */
  Canvas getCanvas();

  /**
   * Sets the canvas for the animation.
   * @param x the x coordinate for the animation's canvas
   * @param y y coordinate for the animation's canvas
   * @param width the width for the animation's canvas
   * @param height the height for the animation's canvas
   */
  void setCanvas(int x, int y, int width, int height);

  /**
   * Adds a shape to to the model.
   * @param shape the shape to be added to the model (list of shapes).
   * @throws IllegalArgumentException if the given shape does not have proper type.
   */
  void addShape(AbstractShape shape);

  /**
   * Overload of addShape that adds a shape to the model using parameters needed to construct
   * a shape.
   * @param shape enum shape type
   * @param label a label for the shape
   * @param x x position of the shape
   * @param y y position of the shape
   * @param w width of the shape
   * @param h height of the shape
   * @param r red fill value
   * @param g green fill value
   * @param b blue fill value
   * @param opacity fill opacity value
   * @throws IllegalArgumentException for non-positive dimensions
   * @throws IllegalArgumentException for negative color values
   * @throws IllegalArgumentException for color values outside of normal range
   * @throws IllegalArgumentException for non-unique label
   * @throws IllegalArgumentException for invalid shape type
   */
  void addShape(AvailableShapes shape, String label, int x, int y, int w, int h,
                       int r, int g, int b, int opacity);

  /**
   * Given a shape, will remove that shape.
   * @param label of the shape to be removed
   */
  void removeShape(String label);

  /**
   * Moves a shape over a time interval by creating copies, deleting the previous shape,
   * and mapping time ticks to coordinates as they shape "moves."
   * @param shape shape to be moved
   * @param startX integer indicating starting x coordinate
   * @param startY integer indicating starting y coordinate
   * @param endX integer indicating ending x coordinate
   * @param endY integer indicating ending y coordinate
   * @param t1 integer indicating start time
   * @param t2 integer indicating end time
   * @throws IllegalArgumentException for negative time
   * @throws IllegalArgumentException for conflicting changes
   * @throws IllegalArgumentException for reverse time
   */
  void addMove(AbstractShape shape, int startX, int startY, int endX, int endY, int t1, int t2);

  /**
   * Takes a shape, changes color according to time, then removes the shape.
   * @param shape shape to be recolored
   * @param startR start red value 0 - 255
   * @param startG start green value 0 - 255
   * @param startB start blue value 0 - 255
   * @param startA start opacity value 0 - 100
   * @param endR end red value 0 - 255
   * @param endG end green value 0 - 255
   * @param endB end blue value 0 - 255
   * @param endA end opacity value 0 - 100
   * @param t1 integer indicating start time
   * @param t2 integer indicating end time
   * @throws IllegalArgumentException for negative time
   * @throws IllegalArgumentException for conflicting changes
   * @throws IllegalArgumentException for reverse time
   * @throws IllegalArgumentException for negative colors
   * @throws IllegalArgumentException for out of bounds colors
   */
  void addRecolor(AbstractShape shape,
                  int startR, int startG, int startB, int startA,
                  int endR, int endG, int endB, int endA, int t1, int t2);

  /**
   * Takes a shape and resizes it over time.
   * @param shape to be resized
   * @param startH starting height
   * @param startW starting width
   * @param endH new height
   * @param endW new width
   * @param t1 start time
   * @param t2 end time
   * @throws IllegalArgumentException for negative time
   * @throws IllegalArgumentException for conflicting changes
   * @throws IllegalArgumentException for reverse time
   * @throws IllegalArgumentException for non-positive dimensions
   */
  void addResize(AbstractShape shape,
                 int startW, int startH, int endW, int endH, int t1, int t2);

  /**
   * Takes in a time interval and returns the shapes at that point.
   *
   * @param tick the time interval to get the shapes at.
   * @return the shapes that are created at that time interval.
   */
  AnimationModel getShapesAtTick(int tick);

  /**
   * Gets the final time of the last change.
   * @return the time of the last change.
   */
  int getFinalTime();

  /**
   * Sets the final time for the changes.
   * @param finalTime the final time.
   */
  void setFinalTime(int finalTime);

  /**
   * Generates a description of the model's shapes and changes to those shapes.
   * @return string describing the model
   */
  String toString();
}
