package cs5004.animator.model;

/**
 * Abstract class for the basic shape interface.
 */
public abstract class AbstractShape implements BasicShape {
  protected Point2D reference;
  private int height;
  private int width;
  private int r;
  private int g;
  private int b;
  private int a;
  private String label;
  private int tick;

  public AbstractShape(Point2D reference) {
    this.reference = reference;
  }

  @Override
  public Point2D getLocation() {
    return this.reference;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getR() {
    return r;
  }

  @Override
  public int getG() {
    return g;
  }

  @Override
  public int getB() {
    return b;
  }


  @Override
  public int getOpacity() {
    return a;
  }

  @Override
  public void setLocation(Point2D point) {
    this.reference = point;
  }

  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public void setWidth(int width) {
    this.width = width;
  }

  @Override
  public void setR(int r) {
    this.r = r;
  }

  @Override
  public void setG(int g) {
    this.g = g;
  }

  @Override
  public void setB(int b) {
    this.b = b;
  }

  @Override
  public void setOpacity(int opacity) {
    this.a = opacity;
  }

  @Override
  public void setLabel(String label) {
    this.label = label;
  }

  @Override
  public String getLabel() {
    return this.label;
  }

  @Override
  public int getTick() {
    return tick;
  }

  @Override
  public void setTick(int tick) {
    this.tick = tick;
  }

  @Override
  public AbstractShape cloneShape() {
    return null;
  }
}
