package cs5004.animator.model;

/**
 * This class represents a rectangle.  It defines all the operations mandated by
 * the Shape interface.
 */

public class Rect extends AbstractShape {
  private int width;
  private int height;
  private AvailableShapes type;

  /**
   * Constructs a rectangle object with the given location of its lower-left
   * corner and dimensions.
   *
   * @param x      x coordinate of the lower-left corner of this rectangle
   * @param y      y coordinate of the lower-left corner of this rectangle
   * @param width  width of this rectangle
   * @param height height of this rectangle
   * @param label String label for the shape
   */
  public Rect(String label, int x, int y, int width, int height) {
    super(new Point2D(x, y));
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("dimensions must be positive.");
    }
    super.setWidth(width);
    super.setHeight(height);
    super.setR(0);
    super.setG(0);
    super.setB(0);
    super.setOpacity(100);
    super.setLabel(label);
    this.type = AvailableShapes.RECTANGLE;
  }

  /**
   * Constructs a rectangle object with the given location of its lower-left
   * corner and dimensions.
   *
   * @param x      x coordinate of the lower-left corner of this rectangle
   * @param y      y coordinate of the lower-left corner of this rectangle
   * @param width  width of this rectangle
   * @param height height of this rectangle
   * @param r the r color value.
   * @param g the g color value.
   * @param b the b color value.
   * @param a the opacity value
   * @param label String label for the shape
   */
  public Rect(String label, int x, int y, int width, int height, int r, int g, int b, int a) {
    super(new Point2D(x, y));
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("dimensions must be positive.");
    }
    if (r < 0 || g < 0 || b < 0) {
      throw new IllegalArgumentException("Color values must be positive");
    }
    if (r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("Color values must be below 255");
    }
    if (a < 0 || a > 100) {
      throw new IllegalArgumentException("Opacity must be between 0 and 100");
    }
    super.setWidth(width);
    super.setHeight(height);
    super.setR(r);
    super.setG(g);
    super.setB(b);
    super.setOpacity(a);
    super.setLabel(label);
    this.type = AvailableShapes.RECTANGLE;
  }

  @Override
  public AvailableShapes getType() {
    return this.type;
  }

  @Override
  public AbstractShape cloneShape() {
    Rect rectClone = new Rect(this.getLabel(), this.reference.getX(), this.reference.getY(),
            this.getWidth(), this.getHeight(), this.getR(), this.getG(),
            this.getB(), this.getOpacity());
    return rectClone;
  }

  /**
   * Creates a string representation of a rectangle.
   * @return a string representation of a rectangle.
   */
  public String toString() {
    return "Rectangle " + this.getLabel() + " with RGB(" + this.getR() + ", "
            + this.getG() + ", " + this.getB() + "), and corner at ("
        + this.reference.getX() + ", " + this.reference.getY()
        + "), width: " + this.getWidth() + ", height: " + this.getHeight();
  }
}

