package cs5004.animator.model;

/**
 * Interface for the change part of the model.
 */
public interface Change {

  /**
   * Method to get the startHeight value of the current shape.
   * @return the startHeight value of the current shape.
   */
  int getStartHeight();

  /**
   * Method to set the startHeight value of the current shape.
   * @param startHeight the startHeight value that it will be set to.
   */
  void setStartHeight(int startHeight);

  /**
   * Method to get the shape type value of the current shape.
   * @return the shape type value of the current shape.
   */
  AvailableShapes getShapeType();

  /**
   * Sets the shapetype of the super class.
   * @param shapeType the type of shape
   */
  void setShapeType(AvailableShapes shapeType);

  /**
   * Method to get the startWidth value of the current shape.
   * @return the startWidth value of the current shape.
   */
  int getStartWidth();

  /**
   * Method to set the startWidth value of the current shape.
   * @param startWidth the startWidth value that it will be set to.
   */
  void setStartWidth(int startWidth);

  /**
   * Method to get the startR value of RGB of the current shape.
   * @return the startR value of the current shape.
   */
  int getStartR();

  /**
   * Method to set the startR value of the current shape.
   * @param startR the startR value that it will be set to.
   */
  void setStartR(int startR);

  /**
   * Method to get the starting G value of RGB of the current shape.
   * @return the starting g value of the current shape.
   */
  int getStartG();

  /**
   * Method to set the starting G value of the current shape.
   * @param startG the startG value that it will be set to.
   */
  void setStartG(int startG);

  /**
   * Method to get the startB value of RGB of the current shape.
   * @return the startB of the current shape.
   */
  int getStartB();

  /**
   * Method to set the starting B value of the current shape.
   * @param startB the startB value that it will be set to.
   */
  void setStartB(int startB);

  /**
   * Method to get the startA value of the current shape.
   * @return the startA of the current shape.
   */
  int getStartA();

  /**
   * Method to set the startA value of the current shape.
   * @param startA the startA value that it will be set to.
   */
  void setStartA(int startA);

  /**
   * Method to get the startReference value of the current shape.
   * @return the startReference of the current shape.
   */
  Point2D getStartReference();

  /**
   * Method to set the startReference value of the current shape.
   * @param startReference the starting reference value that it will be set to.
   */
  void setStartReference(Point2D startReference);

  /**
   * Method to get the updatedHeight value of the current shape.
   * @return the updatedHeight value of the current shape.
   */
  int getUpdatedHeight();

  /**
   * Method to set the updatedHeight value of the current shape.
   * @param updatedHeight the updatedHeight value that it will be set to.
   */
  void setUpdatedHeight(int updatedHeight);

  /**
   * Method to get the updatedWidth value of the current shape.
   * @return the updatedWidth value of the current shape.
   */
  int getUpdatedWidth();

  /**
   * Method to set the updatedWidth value of the current shape.
   * @param updatedWidth the updatedWidth value that it will be set to.
   */
  void setUpdatedWidth(int updatedWidth);

  /**
   * Method to get the updatedR value of RGB of the current shape.
   * @return the updatedR value of the current shape.
   */
  int getUpdatedR();

  /**
   * Method to set the updatedR value of the current shape.
   * @param updatedR the updatedR value that it will be set to.
   */
  void setUpdatedR(int updatedR);

  /**
   * Method to get the updated G value of RGB of the current shape.
   * @return the G value of the current shape.
   */
  int getUpdatedG();

  /**
   * Method to set the updated G value of the current shape.
   * @param updatedG the updatedG value that it will be set to.
   */
  void setUpdatedG(int updatedG);

  /**
   * Method to get the updatedB value of RGB of the current shape.
   * @return the updatedB of the current shape.
   */
  int getUpdatedB();

  /**
   * Method to set the updated B value of the current shape.
   * @param updatedB the updatedB value that it will be set to.
   */
  void setUpdatedB(int updatedB);

  /**
   * Method to get the updatedA value of the current shape.
   * @return the updatedA of the current shape.
   */
  int getUpdatedA();

  /**
   * Method to set the updatedA value of the current shape.
   * @param updatedA the updatedA value that it will be set to.
   */
  void setUpdatedA(int updatedA);

  /**
   * Method to get the reference value of the current shape.
   * @return the reference of the current shape.
   */
  Point2D getReference();

  /**
   * Method to set the reference value of the current shape.
   * @param reference the reference value that it will be set to.
   */
  void setReference(Point2D reference);

  /**
   * Method to get the startTime value of the current shape.
   * @return the startTime of the current shape.
   */
  int getStartTime();

  /**
   * Method to set the startTime value of the current shape.
   * @param startTime the startTime value that it will be set to.
   */
  void setStartTime(int startTime);

  /**
   * Method to get the endTime value of the current shape.
   * @return the endTime of the current shape.
   */
  int getEndTime();

  /**
   * Method to set the endTime value of the current shape.
   * @param endTime the endTime value that it will be set to.
   */
  void setEndTime(int endTime);

  /**
   * Get the shape's label for a change.
   * @return int indicating shape label
   */
  String getShapeLabel();

  /**
   * Set the shape's label for a change.
   * @param shapeLabel is an int indicating a new label.
   */
  void setShapeLabel(String shapeLabel);

  /**
   * Set the type of change.
   * @param type is an enum indicating the type of change
   */
  void setType(AvailableChanges type);

  /**
   * Get the type of change.
   * @return the change's type
   */
  AvailableChanges getType();

  /**
   * Generates a string describing the change.
   * @return string giving details of the change
   */
  String toString();
}
