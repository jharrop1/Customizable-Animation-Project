package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;


import java.util.stream.Collectors;

import cs5004.animator.util.AnimationBuilder;

/**
 * Implementation of the IShape interface.
 */
public class AnimationModelImpl implements AnimationModel {
  private final HashMap<String, AbstractShape> shapeMap;
  //private final HashMap<AbstractShape, List<AbstractChange>> ChangeMap;
  private final LinkedList<AbstractChange> changeList;
  private final Canvas canvas;
  private int finalTime = 0;

  /**
   * Constructor for the animation model impl.
   */
  public AnimationModelImpl() {
    this.shapeMap = new LinkedHashMap<>();
    this.changeList = new LinkedList<>();
    this.canvas = new Canvas();
  }

  @Override
  public boolean hasShape(String label) {
    return this.shapeMap.containsKey(label);
  }

  @Override
  public LinkedList<AbstractShape> getShapes() {
    LinkedList<AbstractShape> shapes = new LinkedList<AbstractShape>(shapeMap.values());
    return shapes;
  }

  public AbstractShape getShape(String label) {
    return shapeMap.get(label);
  }

  @Override
  public LinkedList<AbstractChange> getChanges() {
    return changeList;
  }

  @Override
  public int getFinalTime() {
    return finalTime;
  }

  public void setFinalTime(int finalTime) {
    this.finalTime = finalTime;
  }

  @Override
  public Canvas getCanvas() {
    return this.canvas;
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.canvas.setHeight(height);
    this.canvas.setWidth(width);
    this.canvas.setX(x);
    this.canvas.setY(y);
  }

  @Override
  public void addShape(AbstractShape shape) {
    // make sure labels are unique
    if (shapeMap.containsKey(shape.getLabel())) {
      throw new IllegalArgumentException("This shape has already been added");
    }
    if (shape.getType().equals(AvailableShapes.OVAL)) {
      this.shapeMap.put(shape.getLabel(), shape);
    } else if (shape.getType().equals(AvailableShapes.RECTANGLE)) {
      this.shapeMap.put(shape.getLabel(), shape);
    } else {
      throw new IllegalArgumentException("added shape must be one of the accepted types");
    }
  }

  /**
   * Overload of addShape that adds a shape to the model using parameters needed to construct a
   * shape.
   *
   * @param shapeType enum shape type
   * @param label     a label for the shape
   * @param x         x position of the shape
   * @param y         y position of the shape
   * @param w         width of the shape
   * @param h         height of the shape
   * @param r         red fill value
   * @param g         green fill value
   * @param b         blue fill value
   * @param opacity   fill opacity value
   * @throws IllegalArgumentException for non-positive dimensions
   * @throws IllegalArgumentException for negative color values
   * @throws IllegalArgumentException for color values outside of normal range
   * @throws IllegalArgumentException for non-unique label
   * @throws IllegalArgumentException for invalid shape type
   */
  public void addShape(AvailableShapes shapeType, String label, int x, int y, int w, int h,
                       int r, int g, int b, int opacity) {
    if (w < 0 || h < 0) {
      throw new IllegalArgumentException("dimensions must be positive.");
    }
    if (r < 0 || g < 0 || b < 0 || opacity < 0) {
      throw new IllegalArgumentException("Color values must be positive");
    }
    if (r > 255 || g > 255 || b > 255 || opacity > 100) {
      throw new IllegalArgumentException("Color values must be below 255 for " +
          "rgb and 100 for opacity");
    }
    if (shapeMap.containsKey(label)) {
      throw new IllegalArgumentException("This shape has already been added");
    }
    if (shapeType == AvailableShapes.OVAL) {
      AbstractShape circ = new Circle(label, x, y, w, h, r, g, b, opacity);
      this.shapeMap.put(label, circ);
    } else if (shapeType == AvailableShapes.RECTANGLE) {
      AbstractShape rect = new Rect(label, x, y, w, h, r, g, b, opacity);
      this.shapeMap.put(label, rect);
    } else {
      throw new IllegalArgumentException("added shape must be one of the accepted types");
    }
  }


  /**
   * Overload of removeShape method that uses the unique label of a shape rather than the shape
   * itself to find and remove it from the model.
   *
   * @param label unique string label for a shape
   */
  public void removeShape(String label) {
    if (shapeMap.containsKey(label)) {
      shapeMap.remove(label);
    } else {
      throw new IllegalArgumentException("Given label does not exist.");
    }
  }

  /**
   * Helper method to check that time stamps do not overlap on conflicting changes on the same
   * shape.
   *
   * @param shape shape being checked
   * @param type  of change being checked for
   * @param t1    start time for change
   * @param t2    end time for change
   * @return boolean indicating overlap in conflicting changes
   */
  private boolean timeOverlap(AbstractShape shape, AvailableChanges type, int t1, int t2) {
    for (Change c : this.changeList) {
      //two changes of the same type cannot occur at once
      if (type == c.getType()
          // same shape?
          && c.getShapeLabel().equals(shape.getLabel())
          //overlapping time at the front of existing change
          && ((c.getStartTime() < t1 && c.getEndTime() > t1)
          // Overlapping time at the end of existing change
          || (c.getStartTime() < t2 && c.getEndTime() > t2)
          //new change inside old change time
          || (c.getStartTime() < t1 && c.getEndTime() > t2)
          // old change inside new change's time
          || (c.getStartTime() > t1 && c.getEndTime() < t2))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void addMove(AbstractShape shape,
                      int startX, int startY, int endX, int endY, int t1, int t2) {
    if (t1 < 0 || t2 < 0) {
      throw new IllegalArgumentException("Time value must be positive");
    }
    if (timeOverlap(shape, AvailableChanges.MOVE, t1, t2)) {
      throw new IllegalArgumentException("Only one move change can be made at a time");
    }
    if (t1 > t2) {
      throw new IllegalArgumentException("Start time > end time");
    }
    if (!shapeMap.containsKey(shape.getLabel())) {
      this.addShape(shape);
    }
    changeList.add(new Move(shape, shape.getLabel(),
        startX, startY, endX, endY, t1, t2));
  }

  @Override
  public void addRecolor(AbstractShape shape,
                         int startR, int startG, int startB, int startA,
                         int endR, int endG, int endB, int endA, int t1, int t2) {
    if (t1 < 0 || t2 < 0) {
      throw new IllegalArgumentException("Time value must be positive");
    }
    if (endR < 0 || endG < 0 || endB < 0 || startR < 0 || startG < 0 || startB < 0) {
      throw new IllegalArgumentException("Color values must be positive");
    }
    if (endR > 255 || endG > 255 || endB > 255
        || startR > 255 || startG > 255 || startB > 255) {
      throw new IllegalArgumentException("Color values must be below 255");
    }
    if (timeOverlap(shape, AvailableChanges.RECOLOR, t1, t2)) {
      throw new IllegalArgumentException("Only one recolor change can be made at a time");
    }
    if (t1 > t2) {
      throw new IllegalArgumentException("Start time > end time");
    }
    if (startA < 0 || startA > 100 || endA < 0 || endA > 100) {
      throw new IllegalArgumentException("Opacity must be between 0 and 100");
    }
    if (!shapeMap.containsKey(shape.getLabel())) {
      this.addShape(shape);
    }
    changeList.add(new Recolor(shape, shape.getLabel(),
        startR, startG, startB, startA, endR, endG, endB, endA, t1, t2));
  }

  @Override
  public void addResize(AbstractShape shape,
                        int startW, int startH, int endW, int endH, int t1, int t2) {
    if (startW < 0 || startH < 0 || endW < 0 || endH < 0) {
      throw new IllegalArgumentException("dimensions must be positive.");
    }
    if (t1 < 0 || t2 < 0) {
      throw new IllegalArgumentException("Time value must be positive");
    }
    if (timeOverlap(shape, AvailableChanges.RESIZE, t1, t2)) {
      throw new IllegalArgumentException("Only one resize change can be made at a time");
    }
    if (t1 > t2) {
      throw new IllegalArgumentException("Start time > end time");
    }
    if (!shapeMap.containsKey(shape.getLabel())) {
      this.addShape(shape);
    }
    changeList.add(new Resize(shape, shape.getLabel(),
        startW, startH, endW, endH, t1, t2));
  }


  /**
   * Method helps to "tween" a value when visualizing a change from tick to tick. It is used in
   * getShapesAtTick().
   * @param startTime t1 integer for the formula's start time
   * @param endTime t2 integer for the formula's end time
   * @param tick the current tick, that should be between the previous two values
   * @param startVal the initial value
   * @param endVal the end state value
   * @return an intermediate "tweened" value based on time
   * @throws IllegalArgumentException when tick is not between start/end times
   */
  private int tweener(int startTime, int endTime, int tick, int startVal, int endVal)
      throws IllegalArgumentException {
    if (tick < startTime) {
      throw new IllegalArgumentException("tick must be within start/end times for tweening");
    }
    if (tick >= endTime) {
      return endVal;
    }
    if (startVal != endVal) {
      Double endT = new Double(endTime);
      Double currTick = new Double(tick);
      Double startT = new Double(startTime);
      Double tweenOne = ((endT - currTick) /
          (endT - startT));
      Double tweenTwo = ((currTick - startT) /
          (endT - startT));
      // tween the attributes
      Double startV = new Double(startVal);
      Double endV = new Double(endVal);
      Double tweenX = (startV * tweenOne) + (endV * tweenTwo);
      int intV = tweenX.intValue();
      return intV;
    }
    // values do not change
    return startVal;
  }


  @Override
  public AnimationModel getShapesAtTick(int currentTick) {
    if (currentTick < 0) {
      throw new IllegalArgumentException("Time value must be >= 0");
    }
    AnimationModelImpl modelCopy = new AnimationModelImpl();
    for (AbstractChange change : changeList) {
      // change is in bounds
      if (change.getStartTime() <= currentTick) {
        // MOVE
        if (change.getType().equals(AvailableChanges.MOVE)) {
          // tween location
          int x = tweener(change.getStartTime(), change.getEndTime(), currentTick,
              change.getStartReference().getX(), change.getReference().getX());
          int y = tweener(change.getStartTime(), change.getEndTime(), currentTick,
              change.getStartReference().getY(), change.getReference().getY());
          Point2D location = new Point2D(x, y);
          // model has the shape
          if (modelCopy.hasShape(change.getShapeLabel())) {
            AbstractShape shape = modelCopy.getShape(change.getShapeLabel());
            shape.setLocation(location);
            // model doesn't have the shape
          } else {
            AbstractShape moveCopy = this.getShape(change.getShapeLabel()).cloneShape();
            moveCopy.setLocation(location);
            modelCopy.addShape(moveCopy);
          }
          //COLORS
        } else if (change.getType().equals(AvailableChanges.RECOLOR)) {
          // tween colors
          int r = tweener(change.getStartTime(), change.getEndTime(), currentTick,
              change.getStartR(), change.getUpdatedR());
          int g = tweener(change.getStartTime(), change.getEndTime(), currentTick,
              change.getStartG(), change.getUpdatedG());
          int b = tweener(change.getStartTime(), change.getEndTime(), currentTick,
              change.getStartB(), change.getUpdatedB());
          int a = tweener(change.getStartTime(), change.getEndTime(), currentTick,
              change.getStartA(), change.getUpdatedA());
          // model has the shape
          if (modelCopy.hasShape(change.getShapeLabel())) {
            AbstractShape shape = modelCopy.getShape(change.getShapeLabel());
            shape.setR(r);
            shape.setG(g);
            shape.setB(b);
            shape.setOpacity(a);
            // model doesn't have the shape
          } else {
            AbstractShape colorCopy = this.getShape(change.getShapeLabel()).cloneShape();
            colorCopy.setR(r);
            colorCopy.setG(g);
            colorCopy.setB(b);
            colorCopy.setOpacity(a);
            modelCopy.addShape(colorCopy);
          }
          //SIZE
        } else if (change.getType().equals(AvailableChanges.RESIZE)) {
          // tween dimensions
          int w = tweener(change.getStartTime(), change.getEndTime(), currentTick,
              change.getStartWidth(), change.getUpdatedWidth());
          int h = tweener(change.getStartTime(), change.getEndTime(), currentTick,
              change.getStartHeight(), change.getUpdatedHeight());
          // model has the shape
          if (modelCopy.hasShape(change.getShapeLabel())) {
            AbstractShape shape = modelCopy.getShape(change.getShapeLabel());
            shape.setWidth(w);
            shape.setHeight(h);
            // model doesn't have the shape
          } else {
            AbstractShape sizeCopy = this.getShape(change.getShapeLabel()).cloneShape();
            sizeCopy.setWidth(w);
            sizeCopy.setHeight(h);
            modelCopy.addShape(sizeCopy);
          }
        }
      }
    }
    return modelCopy;
  }

  @Override
  public String toString() {
    HashMap<String, AbstractChange> appearMap = new LinkedHashMap<>();
    ArrayList<AbstractChange> changeListNoAppear = new ArrayList<>();
    ChangeComparator byStartTime = new ChangeComparator();
    List<AbstractChange> sortedChanges = changeList.stream().
            sorted(byStartTime).collect(Collectors.toList());

    //Adding the different shapes to the model string
    String model = "";
    for (String label : shapeMap.keySet()) {
      model = model + shapeMap.get(label).toString() + "\n";
    }
    model = model + "\n";


    // Creating a list of when the shapes appear and a list of the changes
    for (AbstractChange change : sortedChanges) {
      if (appearMap.containsKey(change.getShapeLabel())) {
        changeListNoAppear.add(change);
      } else {
        appearMap.put(change.getShapeLabel(), change);
      }
    }

    //Adding when they appear to the model string
    for (String label : appearMap.keySet()) {
      model = model + appearMap.get(label).getShapeLabel() + " appears at time t="
          + appearMap.get(label).getStartTime() + "\n";
    }
    model = model + "\n";

    //Comparator to sort by start time, i think this is redundant
    List<AbstractChange> sortedChangeListNoAppear = changeListNoAppear
        .stream().sorted(byStartTime).collect(Collectors.toList());

    //Adding the Changes to the model string
    for (AbstractChange change : sortedChangeListNoAppear) {
      model = model + change.toString();
    }
    return model;
  }

  /**
   * Class for the Builder which is an adaptor for the view to the model.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {
    protected AnimationModel model;
    HashMap shapeList = new HashMap<>();

    public Builder() {
      this.model = new AnimationModelImpl();
    }

    @Override
    public AnimationModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      model.setCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      if (type.equalsIgnoreCase("rectangle")) {
        model.addShape(AvailableShapes.RECTANGLE, name,
                0, 0, 0, 0, 0, 0, 0, 0);
      } else if (type.equalsIgnoreCase("ellipse")) {
        model.addShape(AvailableShapes.OVAL, name,
                0, 0, 0, 0, 0, 0, 0, 0);
      } else {
        throw new IllegalArgumentException("Must be a rectangle or ellipse");
      }
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name,
                                                      int t1, int x1, int y1, int w1, int h1,
                                                      int r1, int g1, int b1,
                                                      int t2, int x2, int y2, int w2, int h2,
                                                      int r2, int g2, int b2) {
      //Add hashtable for new shapes with boolean value of if it's seen

      if (!shapeList.containsValue(name)) {
        model.getShape(name).setLocation(new Point2D(x1, y1));
        model.getShape(name).setWidth(w1);
        model.getShape(name).setHeight(h1);
        model.getShape(name).setR(r1);
        model.getShape(name).setG(g1);
        model.getShape(name).setB(b1);
        shapeList.put(name, name);
        if (t1 == 1) {
          model.getShape(name).setOpacity(100);
        } else {
          model.getShape(name).setOpacity(0);
        }
      }
      if (t2 > model.getFinalTime()) {
        model.setFinalTime(t2);
      }

      //could enclose the first 3 in a if statement to encompass so the t only adds once
      if (x2 - x1 != 0 || y2 - y1 != 0) {
        model.addMove(model.getShape(name), x1, y1, x2, y2, t1, t2);
      }
      if (w2 - w1 != 0 || h2 - h1 != 0) {
        model.addResize(model.getShape(name), w1, h1, w2, h2, t1, t2);

      }
      if (r2 - r1 != 0 || g2 - g1 != 0 || b2 - b1 != 0) {
        model.addRecolor(model.getShape(name),
                r1, g1, b1, 100, r2, g2, b2, 100, t1, t2);
      }
      if (t2 - t1 != 0
              && (x2 - x1 == 0 || y2 - y1 == 0
              && w2 - w1 == 0 || h2 - h1 == 0
              && r2 - r1 == 0 || g2 - g1 == 0 || b2 - b1 == 0)) {
        model.addMove(model.getShape(name), x1, y1, x2, y2, t1, t2);
      }
      ChangeComparatorSort byStartTime = new ChangeComparatorSort();
      Collections.sort(model.getChanges(), byStartTime);
      return this;
    }
  }
}


