import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

import cs5004.animator.model.AbstractShape;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AvailableShapes;
import cs5004.animator.model.Change;
import cs5004.animator.model.Circle;
import cs5004.animator.model.Move;
import cs5004.animator.model.Recolor;
import cs5004.animator.model.Rect;
import cs5004.animator.model.Resize;
import cs5004.animator.util.AnimationReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Testing for the Model class.
 */
public class ModelTests {
  private AnimationModelImpl model1;
  private AnimationModelImpl model2;
  private Rect rectangle1;
  private Rect rectangle2;
  private Circle circle1;

  @Before
  public void setup() {
    model1 = new AnimationModelImpl();
    model2 = new AnimationModelImpl();
    rectangle1 = new Rect("R1", 3, 6, 2, 3, 10, 20, 30, 50);
    rectangle2 = new Rect("R2", 5, 6, 7, 8);
    circle1 = new Circle("C1", 1, 2, 3, 4);
    Circle circle2 = new Circle("C2", 15, 26, 45, 0, 64, 254, 100);
  }

  @Test
  public void testAnimationModelImplConstructor() {
    AnimationModelImpl modelConstructor = new AnimationModelImpl();
    modelConstructor.addShape(AvailableShapes.RECTANGLE, "Rect1", 5, 1, 2,
            5, 0, 0, 0,0);
    assertEquals("[Rectangle Rect1 with RGB(0, 0, 0), and corner at (5, 1), " +
            "width: 2, height: 5]",
        modelConstructor.getShapes().toString());
  }

  @Test
  public void testCanvas() {
    assertEquals(0, model1.getCanvas().getHeight());
    assertEquals(0, model1.getCanvas().getWidth());
    model1.setCanvas(1,2,3,4);
    assertEquals(4, model1.getCanvas().getHeight());
    assertEquals(3, model1.getCanvas().getWidth());
    assertEquals(2, model1.getCanvas().getY());
    assertEquals(1, model1.getCanvas().getX());

  }

  @Test
  public void addingShapes() {
    // adding a circle and rect via a premade shape
    LinkedList<AbstractShape> testList = new LinkedList<>();
    assertEquals(testList, model1.getShapes());
    Circle circleConstructorRadius = new Circle("Circ",5, 3, 2);
    testList.add(circleConstructorRadius);
    model1.addShape(circleConstructorRadius);
    assertEquals(testList, model1.getShapes());
    Rect rectangle1 = new Rect("cs5004.AnimationModel.Rect", 3, 6, 4, 7);
    assertEquals(testList, model1.getShapes());

    // adding via a change
    Rect rectangle2 = new Rect("Rect2", 3, 6, 2, 3,
        10, 20, 30, 50);
    model1.addMove(rectangle2, 5, 5, 3, 4,  0, 4);
    testList.add(rectangle2);
    assertEquals(testList, model1.getShapes());

    // testing a model via shape constructor
    model2.addShape(AvailableShapes.RECTANGLE, "aRect", 2, 2, 2, 2,
            0, 0, 0,0);
    model2.addShape(AvailableShapes.RECTANGLE, "anotherRect", 3, 3, 3,
            3,0, 0, 0,0);
    model2.addShape(AvailableShapes.OVAL, "aCircle", 4, 4, 4, 4,0,
            0, 0,0);
    model2.addShape(AvailableShapes.OVAL, "anotherCircle",
        5, 5, 5, 5, 5, 5, 5, 100);
    model2.addShape(AvailableShapes.RECTANGLE, "Rect3",
        6, 6, 6, 6, 6, 6, 6, 6);
    assertEquals("[Rectangle aRect with RGB(0, 0, 0), and corner at (2, 2), width: 2, " +
            "height: 2, Rectangle anotherRect with RGB(0, 0, 0), and corner at (3, 3), " +
            "width: 3, height: 3, Ellipse aCircle with RGB(0, 0, 0), and center at: " +
            "(4, 4), x-diameter: 4, y-diameter: 4, Ellipse anotherCircle with " +
            "RGB(5, 5, 5), and center at: (5, 5), x-diameter: 5, y-diameter: 5, " +
            "Rectangle Rect3 with RGB(6, 6, 6), and corner at (6, 6), width: " +
            "6, height: 6]",
        model2.getShapes().toString());
  }

  @Test
  public void testGetHasShape() {
    assertFalse(model1.hasShape("R1"));
    model1.addShape(rectangle1);
    assertTrue(model1.hasShape("R1"));
    assertEquals(rectangle1, model1.getShape("R1"));
  }

  @Test
  public void testInvalidAddShapesWH() {
    // Testing negative width for addShape 2
    try {
      model1.addShape(AvailableShapes.OVAL, "invalid 1", 5, 5, -5, 5,
              0, 0, 0,0);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative height for addShape 2
    try {
      model1.addShape(AvailableShapes.OVAL, "invalid 1", 5, 5, -5, 5,
              0, 0, 0,0);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void testRemoveShapes() {
    //Removing by shape index
    model2.addShape(AvailableShapes.RECTANGLE, "Rect1", 2, 2, 2, 2,
        0, 0, 0,0);
    model2.addShape(AvailableShapes.RECTANGLE, "Rect2",3, 3, 3, 3,
        0, 0, 0,0);
    model2.addShape(AvailableShapes.OVAL, "Circle1",4, 4, 4, 4,0,
        0, 0,0);
    model2.addShape(AvailableShapes.OVAL,"Circle2",
        5, 5, 5, 5, 5, 5, 5, 100);
    model2.addShape(AvailableShapes.RECTANGLE,"Circle3",
        6, 6, 6, 6, 6, 6, 6, 6);
    model2.removeShape("Rect1");
    assertEquals("[Rectangle Rect2 with RGB(0, 0, 0), and corner at (3, 3), width: " +
            "3, height: 3, Ellipse Circle1 with RGB(0, 0, 0), and center at: (4, 4), " +
            "x-diameter: 4, y-diameter: 4, Ellipse Circle2 with RGB(5, 5, 5), " +
            "and center at: (5, 5), x-diameter: 5, y-diameter: 5, Rectangle Circle3 " +
            "with RGB(6, 6, 6), and corner at (6, 6), width: 6, height: 6]",
        model2.getShapes().toString());
    model2.removeShape("Rect2");
    model2.removeShape("Circle1");
    model2.removeShape("Circle2");
    assertEquals("[Rectangle Circle3 with RGB(6, 6, 6), and corner at (6, 6), " +
            "width: 6, height: 6]",
        model2.getShapes().toString());
    model2.removeShape("Circle3");
    assertEquals("[]", model2.getShapes().toString());
  }

  @Test
  public void testRemoveShape() {
    // Testing removing by label
    try {
      model1.removeShape("hi");
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Given label does not exist.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void testAddMove() {
    model1.addShape(rectangle1);
    LinkedList<Change> testList = new LinkedList<>();
    Move move1 = new Move(rectangle1, rectangle1.getLabel(),
        100, 115, 130, 100,
        100, 115);
    testList.add(move1);
    model1.addMove(rectangle1, 100, 115, 130, 100,
        100, 115);
    assertEquals(testList.get(0).getReference().getX(),
            model1.getChanges().get(0).getReference().getX());
    assertEquals(testList.get(0).getReference().getY(),
            model1.getChanges().get(0).getReference().getY());
    assertEquals(testList.get(0).getStartTime(), model1.getChanges().get(0).getStartTime());
    assertEquals(testList.get(0).getEndTime(), model1.getChanges().get(0).getEndTime());

    model2.addShape(rectangle1);
    LinkedList<Change> testList2 = new LinkedList<>();
    Move move2 = new Move(rectangle2, rectangle2.getLabel(),
        100, 115, 130, 100,
        100, 115);
    testList2.add(move1);
    model2.addMove(rectangle2, 100, 115, 130, 100,
        100, 115);
    assertEquals(testList2.get(0).getReference().getX(),
            model2.getChanges().get(0).getReference().getX());
    assertEquals(testList2.get(0).getReference().getY(),
            model2.getChanges().get(0).getReference().getY());
    assertEquals(testList2.get(0).getStartTime(), model2.getChanges().get(0).getStartTime());
    assertEquals(testList2.get(0).getEndTime(), model2.getChanges().get(0).getEndTime());
  }


  @Test
  public void testIllegalAddMove() {
    // Testing negative t1
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addMove(circle1, 15, 15, -5, 5, -10, 20);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Time value must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative t2
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addMove(circle1, 15, 15, 25, -5, 10, -20);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Time value must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing conflicting time at front
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addMove(circle1, 15, 15, 5, 20, 15, 25);
      testIllegal.addMove(circle1, 15, 15,19, 20, 1, 16);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only one move change can be made at a time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing conflicting time at the back
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addMove(circle1, 15, 15,5, 20, 15, 25);
      testIllegal.addMove(circle1, 15, 15,5, 20, 24, 45);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only one move change can be made at a time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing conflicting time, move 2 is within move 1
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addMove(circle1, 15, 15,5, 20, 15, 25);
      testIllegal.addMove(circle1, 15, 15,5, 20, 19, 21);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only one move change can be made at a time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing conflicting time, move 1 is within move 2
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addMove(circle1, 15, 15,5, 20, 15, 25);
      testIllegal.addMove(circle1, 15, 15,5, 20, 0, 40);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only one move change can be made at a time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing start time after end time
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addMove(circle1, 15, 15,5, 20, 15, 5);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Start time > end time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void testAddColor() {
    model1.addShape(rectangle1);
    LinkedList<Change> testList = new LinkedList<>();
    Recolor color1 = new Recolor(rectangle1, rectangle1.getLabel(),
        100, 115, 130, 100,
        100, 115, 130, 100, 0, 5);
    testList.add(color1);
    model1.addRecolor(rectangle1, 100, 115, 130, 100,
        100, 115, 130, 100, 0, 5);
    assertEquals(testList.get(0).getUpdatedR(), model1.getChanges().get(0).getUpdatedR());
    assertEquals(testList.get(0).getUpdatedG(), model1.getChanges().get(0).getUpdatedG());
    assertEquals(testList.get(0).getUpdatedB(), model1.getChanges().get(0).getUpdatedB());
    assertEquals(testList.get(0).getUpdatedA(), model1.getChanges().get(0).getUpdatedA());
    assertEquals(testList.get(0).getStartTime(), model1.getChanges().get(0).getStartTime());
    assertEquals(testList.get(0).getEndTime(), model1.getChanges().get(0).getEndTime());

    model2.addShape(circle1);
    LinkedList<Change> testList2 = new LinkedList<>();
    Recolor color2 = new Recolor(circle1,
        circle1.getLabel(),100, 115, 130, 0,
        100, 115, 10, 0,5, 10);
    testList2.add(color2);
    model2.addRecolor(circle1, 100, 115, 130, 0,
        100, 115, 10, 0,5, 10);
    assertEquals(testList2.get(0).getUpdatedR(), model2.getChanges().get(0).getUpdatedR());
    assertEquals(testList2.get(0).getUpdatedG(), model2.getChanges().get(0).getUpdatedG());
    assertEquals(testList2.get(0).getUpdatedB(), model2.getChanges().get(0).getUpdatedB());
    assertEquals(testList2.get(0).getUpdatedA(), model2.getChanges().get(0).getUpdatedA());
    assertEquals(testList2.get(0).getStartTime(), model2.getChanges().get(0).getStartTime());
    assertEquals(testList2.get(0).getEndTime(), model2.getChanges().get(0).getEndTime());
  }

  @Test
  public void testIllegalAddColor() {
    // Testing negative R
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, -15, 25, 30, 100,
          100, 115, 130, 0,5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative G
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, -25, 30, 100,
          100, 115, 130, 0,5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative B
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, 25, -30, 100,
          100, 115, 130, 0,5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative A
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, 25, 30, -100,
          100, 115, 130, 0,5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Opacity must be between 0 and 100", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }

    // Testing too High R
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 315, 25, 30, 100,
          100, 115, 130, 0,5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be below 255", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing too High G
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, 325, 30, 100,
          100, 115, 130, 0,5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be below 255", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing too high B
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, 25, 256, 100,
          100, 115, 130, 0,5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be below 255", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing too high A
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, 25, 30, 101,
          100, 115, 130, 0,5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Opacity must be between 0 and 100", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }

    // Testing negative t1
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, 25, 30, 100,
          100, 115, 130, 0,-5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Time value must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative t2
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, 25, 30, 100,
          100, 115, 130, 0,5, -6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Time value must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing conflicting time at front
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, 25, 30, 100,
          100, 115, 130, 0,15, 25);
      testIllegal.addRecolor(circle1, 15, 25, 30, 100,
          100, 115, 130, 0,1, 16);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only one recolor change can be made at a time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing conflicting time at the back
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, 25, 30, 100,
          100, 115, 130, 0,15, 25);
      testIllegal.addRecolor(circle1, 15, 25, 30, 100,
          100, 115, 130, 0,24, 45);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only one recolor change can be made at a time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing conflicting time, move 2 is within move 1
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, 25, 30, 100,
          100, 115, 130, 0,15, 25);
      testIllegal.addRecolor(circle1, 15, 25, 30, 100,
          100, 115, 130, 0,19, 21);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only one recolor change can be made at a time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing conflicting time, move 1 is within move 2
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, 25, 30, 100,
          100, 115, 130, 0,15, 25);
      testIllegal.addRecolor(circle1, 15, 25, 30, 100,
          100, 115, 130, 0,0, 40);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only one recolor change can be made at a time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing start time after end time
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addRecolor(circle1, 15, 25, 30, 100,
          100, 115, 130, 0,6, 5);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Start time > end time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void testAddResize() {
    model1.addShape(rectangle1);
    LinkedList<Change> testList = new LinkedList<>();
    Resize resize1 = new Resize(rectangle1, rectangle1.getLabel(),
        100, 115, 600, 100,
        100, 115);
    testList.add(resize1);
    model1.addResize(rectangle1, 100, 115, 600, 100,
        100, 115);
    assertEquals(testList.get(0).getUpdatedWidth(), model1.getChanges().get(0).getUpdatedWidth());
    assertEquals(testList.get(0).getUpdatedHeight(), model1.getChanges().get(0).getUpdatedHeight());
    assertEquals(testList.get(0).getStartTime(), model1.getChanges().get(0).getStartTime());
    assertEquals(testList.get(0).getEndTime(), model1.getChanges().get(0).getEndTime());
  }

  @Test
  public void testIllegalAddResize() {
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addResize(circle1, -15, 15, 1, 3, 5, 5);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative/0 height
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addResize(circle1, 15, -1,1, 3, 5, 5);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative t1
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addResize(circle1, 15, 15,1, 3, -5, 5);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Time value must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative t2
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addResize(circle1, 15, 15,1, 3, 25, -5);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Time value must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing conflicting time at front
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addResize(circle1, 15, 15,1, 3, 15, 25);
      testIllegal.addResize(circle1, 15, 15,1, 3, 1, 16);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only one resize change can be made at a time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing conflicting time at the back
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addResize(circle1, 15, 15,1, 3, 15, 25);
      testIllegal.addResize(circle1, 15, 15,1, 3, 24, 45);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only one resize change can be made at a time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing conflicting time, move 2 is within move 1
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addResize(circle1, 15, 15,1, 3, 15, 25);
      testIllegal.addResize(circle1, 15, 15, 1, 3,19, 21);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only one resize change can be made at a time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing conflicting time, move 1 is within move 2
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addResize(circle1, 15, 15,1, 3, 15, 25);
      testIllegal.addResize(circle1, 15, 15, 1, 3,0, 40);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only one resize change can be made at a time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing start time after end time
    try {
      AnimationModelImpl testIllegal = new AnimationModelImpl();
      testIllegal.addResize(circle1, 15, 15, 1, 3,15, 5);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Start time > end time", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void testToString() throws FileNotFoundException {
    FileReader fileIn = new FileReader("resources/testAllMotions.txt");
    AnimationModel model3 = AnimationReader.parseFile(fileIn,
        new AnimationModelImpl.Builder());
    assertEquals("Rectangle R1 with RGB(100, 100, 100), and corner at (100, 100), " +
        "width: 100, height: 100\n" +
        "Ellipse C1 with RGB(0, 222, 255), and center at: (440, 70), x-diameter: 120, " +
        "y-diameter: 60\n" +
        "\n" +
        "R1 appears at time t=1\n" +
        "C1 appears at time t=6\n" +
        "\n" +
        "Shape R1 updates its dimensions from width: 100 height: 100 to width: 50 height: " +
        "100 from t= 1 to t= 10\n" +
        "Shape R1 updates its position from x-dimension: 100, y-dimension: 100 to " +
        "x-dimension: 200, y-dimension: 200 from t= 1 to t= 10\n" +
        "Shape C1 updates its position from x-dimension: 440, y-dimension: 70 to " +
        "x-dimension: 440, y-dimension: 70 from t= 6 to t= 20\n" +
        "Shape R1 updates its color from (255, 0, 0, 100) to (255, 0, 230, 100) " +
        "from t= 10 to t= 50\n" +
        "Shape R1 updates its position from x-dimension: 200, y-dimension: 200 to " +
        "x-dimension: 400, y-dimension: 400 from t= 10 to t= 50\n" +
        "Shape R1 updates its position from x-dimension: 200, y-dimension: 200 to " +
        "x-dimension: 400, y-dimension: 400 from t= 10 to t= 50\n" +
        "Shape C1 updates its color from (222, 0, 255, 100) to (15, 25, 200, 100) " +
        "from t= 20 to t= 50\n" +
        "Shape C1 updates its dimensions from width: 120 height: 60 to width: 120 " +
        "height: 120 from t= 20 to t= 50\n" +
        "Shape C1 updates its position from x-dimension: 440, y-dimension: 70 to " +
        "x-dimension: 100, y-dimension: 120 from t= 20 to t= 50\n" +
        "Shape C1 updates its color from (15, 25, 200, 100) to (0, 170, 85, 100) " +
        "from t= 50 to t= 70\n" +
        "Shape C1 updates its dimensions from width: 120 height: 120 to width: 120 " +
        "height: 60 from t= 50 to t= 70\n" +
        "Shape C1 updates its position from x-dimension: 100, y-dimension: 120 to " +
        "x-dimension: 440, y-dimension: 370 from t= 50 to t= 70\n" +
        "Shape R1 updates its position from x-dimension: 400, y-dimension: 400 to " +
        "x-dimension: 400, y-dimension: 400 from t= 50 to t= 51\n" +
        "Shape R1 updates its color from (255, 0, 230, 100) to (255, 0, 0, 100) " +
        "from t= 51 to t= 70\n" +
        "Shape R1 updates its dimensions from width: 50 height: 100 to " +
        "width: 25 height: 100 from t= 51 to t= 70\n" +
        "Shape R1 updates its position from x-dimension: 400, y-dimension: 400 to " +
        "x-dimension: 1, y-dimension: 1 from t= 51 to t= 70\n" +
        "Shape R1 updates its position from x-dimension: 400, y-dimension: 400 to " +
        "x-dimension: 1, y-dimension: 1 from t= 51 to t= 70\n" +
        "Shape C1 updates its color from (0, 170, 85, 100) to (255, 255, 0, 100) " +
        "from t= 70 to t= 80\n" +
        "Shape C1 updates its position from x-dimension: 440, y-dimension: 370 to " +
        "x-dimension: 15, y-dimension: 15 from t= 70 to t= 80\n" +
        "Shape R1 updates its color from (255, 0, 0, 100) to (0, 0, 0, 100) from " +
        "t= 70 to t= 100\n" +
        "Shape R1 updates its position from x-dimension: 1, y-dimension: 1 to " +
        "x-dimension: 200, y-dimension: 200 from t= 70 to t= 100\n" +
        "Shape R1 updates its position from x-dimension: 1, y-dimension: 1 to " +
        "x-dimension: 200, y-dimension: 200 from t= 70 to t= 100\n" +
        "Shape C1 updates its color from (255, 255, 0, 100) to (255, 25, 20, 100) " +
        "from t= 80 to t= 100\n" +
        "Shape C1 updates its dimensions from width: 120 height: 60 to width: 300 " +
        "height: 300 from t= 80 to t= 100\n" +
        "Shape C1 updates its position from x-dimension: 15, y-dimension: 15 to " +
        "x-dimension: 440, y-dimension: 370 from t= 80 to t= 100\n", model3.toString());
  }


  @Test
  public void testGetShapesAtTick() {
    model1.addShape(rectangle1);
    model1.addShape(circle1);
    AnimationModel modelEmpty = model1.getShapesAtTick(1);
    assertEquals(model2.toString(), modelEmpty.toString());
    model1.addResize(rectangle1, 1, 3,
        5, 3,0 , 10);
    model1.addMove(rectangle1, 1, 3, 5,3,
        0, 10);
    model1.addRecolor(circle1, 18, 111, 2,3,
        2, 3, 250,3,5, 11);
    // altered attributes
    assertEquals(model1.getShapesAtTick(5).getShape(rectangle1.getLabel()).getWidth(),
        3);
    assertEquals(model1.getShapesAtTick(5)
            .getShape(rectangle1.getLabel()).getLocation().getX(),
        3);
    assertEquals(model1.getShapesAtTick(8)
            .getShape(circle1.getLabel()).getR(),
        10);
    assertEquals(model1.getShapesAtTick(8)
            .getShape(circle1.getLabel()).getG(),
        57);
    assertEquals(model1.getShapesAtTick(8)
            .getShape(circle1.getLabel()).getB(),
        126);
    assertEquals(model1.getShapesAtTick(8)
            .getShape(circle1.getLabel()).getOpacity(),
        3);
    // unaltered attributes stay the same
    assertEquals(model1.getShapesAtTick(5).getShape(rectangle1.getLabel()).getHeight(),
        3);
    assertEquals(model1.getShapesAtTick(5).getShape(circle1.getLabel()).getHeight(),
        4);
  }

  @Test
  public void testIllegalGetShapesAtTick() {
    model1.addShape(rectangle1);
    model1.addShape(circle1);
    model1.addResize(rectangle1, 1, 3,
        5, 3,0 , 10);
    // Testing negative tick
    try {
      assertEquals(model1.getShapesAtTick(-1).getShape(rectangle1.getLabel()).getWidth(),
          3);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Time value must be >= 0", iae.getMessage());
    }
  }
}
