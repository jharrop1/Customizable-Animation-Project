package cs5004.animator.model;

/**
 * Abstract class for the change movement of the model.
 */
public abstract class AbstractChange implements Change {
  private int startTime;
  private int endTime;
  private String shapeLabel;
  private AvailableChanges type;
  private AvailableShapes shapeType;

  @Override
  public int getStartTime() {
    return startTime;
  }

  @Override
  public AvailableShapes getShapeType() {
    return this.shapeType;
  }

  @Override
  public void setShapeType(AvailableShapes shapeType) {
    this.shapeType = shapeType;
  }

  @Override
  public void setStartTime(int startTime) {
    this.startTime = startTime;
  }

  @Override
  public int getEndTime() {
    return endTime;
  }

  @Override
  public void setEndTime(int endTime) {
    this.endTime = endTime;
  }

  @Override
  public void setShapeLabel(String shapeLabel) {
    this.shapeLabel = shapeLabel;
  }

  @Override
  public void setType(AvailableChanges type) {
    this.type = type;
  }

  @Override
  public AvailableChanges getType() {
    return this.type;
  }

  @Override
  public String getShapeLabel() {
    return this.shapeLabel;
  }

}


