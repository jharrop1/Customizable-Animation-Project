package cs5004.animator.model;

/**
 * A class representing an oval.
 */
public class Circle extends AbstractShape {
  private AvailableShapes type;

  /**
   * Construct a circle object using the given center and radius.
   * @param x x coordinate of the center of this circle
   * @param y y coordinate of the center of this circle
   * @param radius the radius of this circle
   * @param label String label for the shape
   */
  public Circle(String label, int x, int y, int radius) {
    super(new Point2D(x,y));
    if (radius < 0) {
      throw new IllegalArgumentException("Radius must be positive.");
    }
    super.setWidth(radius);
    super.setHeight(radius);
    super.setR(0);
    super.setG(0);
    super.setB(0);
    super.setOpacity(1);
    super.setLabel(label);
    this.type = AvailableShapes.OVAL;
  }

  /**
   * Constructs a cs5004.AnimationModel.Circle object with the given center and dimensions
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
  public Circle(String label, int x, int y, int width, int height, int r, int g, int b, int a) {
    super(new Point2D(x,y));
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
    this.type = AvailableShapes.OVAL;
  }

  /**
   * Constructs an oval given the center and the width and height of the oval.
   * @param x the x coordinate of the center of the oval.
   * @param y the y coordinate of the center of the oval.
   * @param width the full width of the oval.
   * @param height the full height of the oval.
   * @param label String label for the shape
   */
  public Circle(String label, int x, int y, int width, int height) {
    super(new Point2D(x,y));
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
    this.type = AvailableShapes.OVAL;
  }

  /**
   * Constructs a cs5004.AnimationModel.Circle object with the given center and dimensions
   *
   * @param x      x coordinate of the lower-left corner of this rectangle
   * @param y      y coordinate of the lower-left corner of this rectangle
   * @param radius the radius of the circle
   * @param r the r color value.
   * @param g the g color value.
   * @param b the b color value.
   * @param a the opacity value
   * @param label String label for the shape
   */
  public Circle(String label, int x, int y, int radius, int r, int g, int b, int a) {
    super(new Point2D(x,y));
    if (radius < 0) {
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
    super.setWidth(radius);
    super.setHeight(radius);
    super.setR(r);
    super.setG(g);
    super.setB(b);
    super.setOpacity(a);
    super.setLabel(label);
    this.type = AvailableShapes.OVAL;
  }

  @Override
  public AvailableShapes getType() {
    return this.type;
  }

  /**
   * Creates a string representation of the circle class.
   * @return a string representation of a circle.
   */
  public String toString() {
    return "Ellipse " + this.getLabel() + " with RGB(" + this.getR() + ", " + this.getG()
            + ", " + this.getB() + "), and center at: ("
        + this.reference.getX() + ", " + this.reference.getY()
        + "), x-diameter: " + this.getWidth() + ", y-diameter: " + this.getHeight();
  }

  @Override
  public AbstractShape cloneShape() {
    Circle circleClone = new Circle(this.getLabel(), this.reference.getX(),
            this.reference.getY(), this.getWidth(), this.getHeight(),
            this.getR(), this.getG(), this.getB(), this.getOpacity());
    return circleClone;
  }
}

