package cs5004.animator.model;

/**
 * This class represents a 2D point. This point is denoted in Cartesian.
 * coordinates as (x,y).
 */

public class Point2D {
  private int x;
  private int y;

  /**
   * Construct a 2d point with the given coordinates.
   *
   * @param x the x-coordinate of this point
   * @param y the y-coordinate of this point
   */
  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Return the x-coordinate of this point.
   *
   * @return x-coordinate of this point
   */
  public int getX() {
    return x;
  }

  /**
   * Return the y-coordinate of this point.
   *
   * @return y-coordinate of this point
   */
  public int getY() {
    return y;
  }

  /**
   * Method to set the X value of the origin point to a new value.
   *
   * @param x is the new value of x.
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Method to set the Y value of the origin point to a new value.
   *
   * @param y is the new value of y.
   */
  public void setY(int y) {
    this.y = y;
  }
}