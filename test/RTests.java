import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.AbstractShape;
import cs5004.animator.model.AvailableShapes;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Testing for the rectangle class.
 */
public class RTests {
  private Rect rectangle1;
  private Rect rectangle2;

  @Before
  public void setup() {
    rectangle1 = new Rect("R1", 3, 6, 4, 7);
    rectangle2 = new Rect("R2", 3, 6, 2, 3, 10, 20, 30, 50);
  }

  @Test
  public void testConstructor() {
    //Testing the constructor 1 with X/Y width and Height
    Rect rectConstructorWH = new Rect("rect1",1, 1, 2, 9, 30, 25, 50, 100);
    assertEquals("Rectangle rect1 with RGB(30, 25, 50), and corner at " +
                    "(1, 1), width: 2, height: 9",
            rectConstructorWH.toString());
    //Testing the constructor 2 with X/Y, width, height, RGB, and opacity
    Rect rectConstructorRGB = new Rect("rect2",
        10, 20, 30, 40, 15, 15, 15, 100);
    assertEquals("Rectangle rect2 with RGB(15, 15, 15), and corner at " +
                    "(10, 20), width: 30, height: 40",
            rectConstructorRGB.toString());
  }

  @Test
  public void testInvalidConstructor1() {
    // Testing negative Width for constructor 1
    try {
      new Rect("rect4", 5, 5, -2, 3, 2, 4, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative Height for constructor 1
    try {
      new Rect("rect5", 5, 5, 2, -3, 2, 4, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void testInvalidConstructor2() {
    // Testing negative Width for constructor 2
    try {
      new Rect("rect4", 5, 5, -2, 3, 2, 4, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative Height for constructor 2
    try {
      new Rect("rect5", 5, 5, 2, -3, 2, 4, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative r for constructor 2
    try {
      new Rect("rect6", 5, 5, 2, 3, -2, 4, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing r too high for constructor 2
    try {
      new Rect("rect7", 5, 5, 2, 3, 2000, 4, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be below 255", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative g for constructor 2
    try {
      new Rect("rect8", 5, 5, 2, 3, 2, -4, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing g too high for constructor 2
    try {
      new Rect("rect9", 5, 5, 2, 3, 2, 400, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be below 255", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative b for constructor 2
    try {
      new Rect("rect10", 5, 5, 2, 3, 2, 4, -5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing b too high for constructor 2
    try {
      new Rect("rect11", 5, 5, 2, 3, 2, 4, 578, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be below 255", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative a for constructor 2
    try {
      new Rect("rect12", 5, 5, 2, 3, 2, 4, 5, -6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Opacity must be between 0 and 100", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing a too high for constructor 2
    try {
      new Rect("rect13", 5, 5, 2, 3, 2, 4, 5, 600);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Opacity must be between 0 and 100", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void testGetters() {
    assertEquals(3, rectangle1.getLocation().getX());
    assertEquals(6, rectangle1.getLocation().getY());
    assertEquals(4, rectangle1.getWidth());
    assertEquals(7, rectangle1.getHeight());
    assertEquals(0, rectangle1.getR());
    assertEquals(0, rectangle1.getG());
    assertEquals(0, rectangle1.getB());
    assertEquals(100, rectangle1.getOpacity());
    assertEquals(AvailableShapes.RECTANGLE, rectangle1.getType());

    assertEquals(3, rectangle2.getLocation().getX());
    assertEquals(6, rectangle2.getLocation().getY());
    assertEquals(2, rectangle2.getWidth());
    assertEquals(3, rectangle2.getHeight());
    assertEquals(10, rectangle2.getR());
    assertEquals(20, rectangle2.getG());
    assertEquals(30, rectangle2.getB());
    assertEquals(50, rectangle2.getOpacity());
    assertEquals(AvailableShapes.RECTANGLE, rectangle2.getType());
  }

  @Test
  public void testSetters() {
    rectangle1.getLocation().setX(10);
    rectangle1.getLocation().setY(20);
    rectangle1.setWidth(30);
    rectangle1.setHeight(40);
    rectangle1.setR(11);
    rectangle1.setG(12);
    rectangle1.setB(13);
    rectangle1.setOpacity(14);
    assertEquals(10, rectangle1.getLocation().getX());
    assertEquals(20, rectangle1.getLocation().getY());
    Point2D point = new Point2D(5,5);
    rectangle1.setLocation(point);
    assertEquals(5, rectangle1.getLocation().getX());
    assertEquals(5, rectangle1.getLocation().getY());
    assertEquals(30, rectangle1.getWidth());
    assertEquals(40, rectangle1.getHeight());
    assertEquals(11, rectangle1.getR());
    assertEquals(12, rectangle1.getG());
    assertEquals(13, rectangle1.getB());
    assertEquals(14, rectangle1.getOpacity());

    rectangle2.getLocation().setX(30);
    rectangle2.getLocation().setY(60);
    rectangle2.setWidth(20);
    rectangle2.setHeight(30);
    rectangle2.setR(100);
    rectangle2.setG(200);
    rectangle2.setB(130);
    rectangle2.setOpacity(99);
    assertEquals(30, rectangle2.getLocation().getX());
    assertEquals(60, rectangle2.getLocation().getY());
    assertEquals(20, rectangle2.getWidth());
    assertEquals(30, rectangle2.getHeight());
    assertEquals(100, rectangle2.getR());
    assertEquals(200, rectangle2.getG());
    assertEquals(130, rectangle2.getB());
    assertEquals(99, rectangle2.getOpacity());
  }

  @Test
  public void testCloneShape() {
    AbstractShape testClone = rectangle2.cloneShape();
    assertEquals(testClone.getLabel(), rectangle2.getLabel());
    assertEquals(testClone.getLocation().getX(), rectangle2.getLocation().getX());
    assertEquals(testClone.getLocation().getY(), rectangle2.getLocation().getY());
    assertEquals(testClone.getWidth(), rectangle2.getWidth());
    assertEquals(testClone.getHeight(), rectangle2.getHeight());
    assertEquals(testClone.getR(), rectangle2.getR());
    assertEquals(testClone.getB(), rectangle2.getB());
    assertEquals(testClone.getG(), rectangle2.getG());
    assertEquals(testClone.getOpacity(), rectangle2.getOpacity());
  }
}
