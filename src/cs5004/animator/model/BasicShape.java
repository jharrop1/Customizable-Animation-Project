package cs5004.animator.model;

/**
 * This interface contains all operations that all types of shapes
 * should support.
 */
public interface BasicShape {

  /**
   * Gets the coordinates for the shape.
   * @return the coordinates for the shape
   */
  Point2D getLocation();

  /**
   * Method to get the height of the current shape.
   * @return the height of the current shape.
   */
  int getHeight();

  /**
   * Method to get the width of the current shape.
   * @return the width of the current shape.
   */
  int getWidth();

  /**
   * Method to get the R value of RGB of the current shape.
   * @return the R value of the current shape.
   */
  int getR();

  /**
   * Method to get the G value of RGB of the current shape.
   * @return the G value of the current shape.
   */
  int getG();

  /**
   * Method to get the B value of RGB of the current shape.
   * @return the B value of the current shape.
   */
  int getB();

  /**
   * Method to get the opacity value of RGBA of the current shape.
   * @return the opacity value of the current shape.
   */
  int getOpacity();

  /**
   * Method to get the enum type of shape.
   * @return the opacity value of the current shape.
   */
  AvailableShapes getType();

  /**
   * Sets the coordinates for the shape.
   * @param point that sets the new coordinates for a shape.
   */
  void setLocation(Point2D point);

  /**
   * Method to set the height of the current shape.
   * @param height the height that it will be set to.
   */
  void setHeight(int height);

  /**
   * Method to set the width of the current shape.
   * @param width the width that it will be set to.
   */
  void setWidth(int width);

  /**
   * Method to set the R value of the current shape.
   * @param r the r value that it will be set to.
   */
  void setR(int r);

  /**
   * Method to set the G value of the current shape.
   * @param g the g value that it will be set to.
   */
  void setG(int g);

  /**
   * Method to set the B value of the current shape.
   * @param b the b value that it will be set to.
   */
  void setB(int b);

  /**
   * Method to set the opacity value of the current shape.
   * @param opacity the opacity value that it will be set to.
   */
  void setOpacity(int opacity);

  /**
   * Method to set the label of the current shape.
   * @param label the unique label that it will be set to.
   */
  void setLabel(String label);

  /**
   * Method to get the label of the current shape.
   * @return label the unique label that it will be set to.
   */
  String getLabel();

  /**
   * Method to return the time value of the current shape.
   * @return the time value of the current shape.
   */
  int getTick();

  /**
   * Method to set the tick value of the current shape.
   * @param tick the tick value to set it to.
   */
  void setTick(int tick);

  /**
   * Generates a string describing the shape.
   * @return string giving details of the shape
   */
  String toString();

  /**
   * Method to clone the shape.
   * @return a deep copy of the shape.
   */
  AbstractShape cloneShape();
}

