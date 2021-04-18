package cs5004.animator.model;

/**
 * Class representing the canvas of the model.
 */
public class Canvas {
  int width;
  int height;
  int x;
  int y;

  /**
   * Constructor for the canvas class.
   */
  public Canvas() {
    this.width = 0;
    this.height = 0;
  }

  /**
   * Method to get the width of canvas.
   * @return the width of the canvas.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Method to set the width of the canvas.
   * @param width the width to set the canvas at.
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Method to get the height of canvas.
   * @return the height of the canvas.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Method to set the height of the canvas.
   * @param height the height to set the canvas at.
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Method to get the X of canvas.
   * @return the X of the canvas.
   */
  public int getX() {
    return x;
  }

  /**
   * Method to set the X of the canvas.
   * @param x the X to set the canvas at.
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Method to get the Y of canvas.
   * @return the Y of the canvas.
   */
  public int getY() {
    return y;
  }

  /**
   * Method to set the Y of the canvas.
   * @param y the Y to set the canvas at.
   */
  public void setY(int y) {
    this.y = y;
  }
}
