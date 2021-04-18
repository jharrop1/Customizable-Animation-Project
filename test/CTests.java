import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.AbstractShape;
import cs5004.animator.model.AvailableShapes;
import cs5004.animator.model.Circle;
import cs5004.animator.model.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Testing for the circle class.
 */
public class CTests {
  private Circle circle1;
  private Circle circle2;
  private Circle circle3;
  private Circle circle4;

  @Before
  public void setup() {
    circle1 = new Circle("c1", 1, 2, 3);
    circle2 = new Circle("c2", 1, 2, 3, 4, 5, 6, 7, 8);
    circle3 = new Circle("c3", 1, 2, 3, 4);
    circle4 = new Circle("c4", 1, 2, 3, 4, 5, 6, 7);
  }

  @Test
  public void testConstructor() {
    //Testing the constructor with a radius no RGB or width and height
    Circle circleConstructorRadius = new Circle("circle1", 5, 3, 2, 15, 25, 35, 100);
    assertEquals("Ellipse circle1 with RGB(15, 25, 35), and center at: " +
                    "(5, 3), x-diameter: 2, y-diameter: 2",
            circleConstructorRadius.toString());
    // Testing the constructor with a width and a height and no radius or rgb
    Circle circleConstructorWH1 = new Circle("circle2", 2, 4, 5, 6);
    assertEquals("Ellipse circle2 with RGB(0, 0, 0), and center at: (2, 4), " +
                    "x-diameter: 5, y-diameter: 6",
            circleConstructorWH1.toString());
    Circle circleConstructorWH2 = new Circle("circle3", 15, 1, 15, 167);
    assertEquals("Ellipse circle3 with RGB(0, 0, 0), and center at: (15, 1)," +
                    " x-diameter: 15, y-diameter: 167",
            circleConstructorWH2.toString());
    //Testing constructor with width and height and rgb, no radius
    Circle circleConstructorRGB1 = new Circle("circle4",
        5, 3, 2, 2, 2, 2, 2, 20);
    assertEquals("Ellipse circle4 with RGB(2, 2, 2), and center at: (5, 3)," +
                    " x-diameter: 2, y-diameter: 2",
            circleConstructorRGB1.toString());
    Circle circleConstructorRGB2 = new Circle("circle5",
        1, 1, 1, 1, 1, 1, 1, 1);
    assertEquals("Ellipse circle5 with RGB(1, 1, 1), and center at: (1, 1)," +
                    " x-diameter: 1, y-diameter: 1",
            circleConstructorRGB2.toString());
    //Testing constructor with radius and rgb no width and height
    Circle circleConstructorRGBRadius = new Circle("circle6",
        1, 2, 3, 4, 5, 6, 7);
    assertEquals("Ellipse circle6 with RGB(4, 5, 6), and center at: (1, 2)," +
                    " x-diameter: 3, y-diameter: 3",
            circleConstructorRGBRadius.toString());
  }

  @Test
  public void testInvalidConstructor1() {
    // Testing negative radius for constructor 1
    try {
      new Circle("circle1", 5, 5, -5);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Radius must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void testInvalidConstructor2() {
    // Testing negative Width for constructor 2
    try {
      new Circle("circle4", 5, 5, -2, 3, 2, 4, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative Height for constructor 2
    try {
      new Circle("circle5", 5, 5, 2, -3, 2, 4, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative r for constructor 2
    try {
      new Circle("circle6", 5, 5, 2, 3, -2, 4, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing r too high for constructor 2
    try {
      new Circle("circle7", 5, 5, 2, 3, 2000, 4, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be below 255", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative g for constructor 2
    try {
      new Circle("circle8", 5, 5, 2, 3, 2, -4, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing g too high for constructor 2
    try {
      new Circle("circle9", 5, 5, 2, 3, 2, 400, 5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be below 255", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative b for constructor 2
    try {
      new Circle("circle10", 5, 5, 2, 3, 2, 4, -5, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing b too high for constructor 2
    try {
      new Circle("circle11", 5, 5, 2, 3, 2, 4, 578, 6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be below 255", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative a for constructor 2
    try {
      new Circle("circle12", 5, 5, 2, 3, 2, 4, 5, -6);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Opacity must be between 0 and 100", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing a too high for constructor 2
    try {
      new Circle("circle13", 5, 5, 2, 3, 2, 4, 5, 600);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Opacity must be between 0 and 100", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void testInvalidConstructor3() {
    // Testing negative Width for constructor 3
    try {
      new Circle("circle4",11, 12, -13, 14);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative Height for constructor 3
    try {
      new Circle("circle5", 11, 12, 13, -14);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void testInvalidConstructor4() {
    // Testing negative radius for constructor 4
    try {
      new Circle("circle4", 20, 30, -40, 50, 60, 70, 80);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("dimensions must be positive.", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative r for constructor 4
    try {
      new Circle("circle5", 20, 30, 40, -50, 60, 70, 80);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing r too high for constructor 4
    try {
      new Circle("circle6", 20, 30, 40, 500, 60, 70, 80);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be below 255", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative g for constructor 4
    try {
      new Circle("circle7", 20, 30, 40, 50, -60, 70, 80);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing g too high for constructor 4
    try {
      new Circle("circle8", 20, 30, 40, 50, 690, 70, 80);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be below 255", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative b for constructor 4
    try {
      new Circle("circle9", 20, 30, 40, 50, 60, -1, 80);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be positive", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing b too high for constructor 4
    try {
      new Circle("circle10", 20, 30, 40, 50, 60, 708, 80);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Color values must be below 255", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing negative a for constructor 4
    try {
      new Circle("circle11", 20, 30, 40, 50, 60, 70, -80);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Opacity must be between 0 and 100", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    // Testing a too high for constructor 4
    try {
      new Circle("circle12", 20, 30, 40, 50, 60, 70, 101);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Opacity must be between 0 and 100", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void testGetters() {
    // Testing getters for circle 1
    assertEquals(1, circle1.getLocation().getX());
    assertEquals(2, circle1.getLocation().getY());
    assertEquals(3, circle1.getWidth());
    assertEquals(3, circle1.getHeight());
    assertEquals(0, circle1.getR());
    assertEquals(0, circle1.getG());
    assertEquals(0, circle1.getB());
    assertEquals(1, circle1.getOpacity());
    assertEquals(AvailableShapes.OVAL, circle1.getType());

    // Testing getters for circle 2
    assertEquals(1, circle2.getLocation().getX());
    assertEquals(2, circle2.getLocation().getY());
    assertEquals(3, circle2.getWidth());
    assertEquals(4, circle2.getHeight());
    assertEquals(5, circle2.getR());
    assertEquals(6, circle2.getG());
    assertEquals(7, circle2.getB());
    assertEquals(8, circle2.getOpacity());
    assertEquals(AvailableShapes.OVAL, circle2.getType());

    // Testing getters for circle 3
    assertEquals(1, circle3.getLocation().getX());
    assertEquals(2, circle3.getLocation().getY());
    assertEquals(3, circle3.getWidth());
    assertEquals(4, circle3.getHeight());
    assertEquals(0, circle3.getR());
    assertEquals(0, circle3.getG());
    assertEquals(0, circle3.getB());
    assertEquals(100, circle3.getOpacity());
    assertEquals(AvailableShapes.OVAL, circle3.getType());

    // Testing getters for circle 4
    assertEquals(1, circle4.getLocation().getX());
    assertEquals(2, circle4.getLocation().getY());
    assertEquals(3, circle4.getWidth());
    assertEquals(3, circle4.getHeight());
    assertEquals(4, circle4.getR());
    assertEquals(5, circle4.getG());
    assertEquals(6, circle4.getB());
    assertEquals(7, circle4.getOpacity());
    assertEquals(AvailableShapes.OVAL, circle4.getType());
  }

  @Test
  public void testSetters() {
    // Testing Setters for circle 1
    circle1.getLocation().setX(10);
    circle1.getLocation().setY(20);
    circle1.setWidth(30);
    circle1.setHeight(40);
    circle1.setR(11);
    circle1.setG(12);
    circle1.setB(13);
    circle1.setOpacity(14);
    assertEquals(10, circle1.getLocation().getX());
    assertEquals(20, circle1.getLocation().getY());
    Point2D point = new Point2D(5,5);
    circle1.setLocation(point);
    assertEquals(5, circle1.getLocation().getX());
    assertEquals(5, circle1.getLocation().getY());
    assertEquals(30, circle1.getWidth());
    assertEquals(40, circle1.getHeight());
    assertEquals(11, circle1.getR());
    assertEquals(12, circle1.getG());
    assertEquals(13, circle1.getB());
    assertEquals(14, circle1.getOpacity());

    // Testing Setters for circle 2
    circle2.getLocation().setX(100);
    circle2.getLocation().setY(200);
    circle2.setWidth(300);
    circle2.setHeight(400);
    circle2.setR(110);
    circle2.setG(120);
    circle2.setB(130);
    circle2.setOpacity(140);
    assertEquals(100, circle2.getLocation().getX());
    assertEquals(200, circle2.getLocation().getY());
    assertEquals(300, circle2.getWidth());
    assertEquals(400, circle2.getHeight());
    assertEquals(110, circle2.getR());
    assertEquals(120, circle2.getG());
    assertEquals(130, circle2.getB());
    assertEquals(140, circle2.getOpacity());

    // Testing Setters for circle 3
    circle3.getLocation().setX(1000);
    circle3.getLocation().setY(2000);
    circle3.setWidth(3000);
    circle3.setHeight(4000);
    circle3.setR(1100);
    circle3.setG(1200);
    circle3.setB(1300);
    circle3.setOpacity(1400);
    assertEquals(1000, circle3.getLocation().getX());
    assertEquals(2000, circle3.getLocation().getY());
    assertEquals(3000, circle3.getWidth());
    assertEquals(4000, circle3.getHeight());
    assertEquals(1100, circle3.getR());
    assertEquals(1200, circle3.getG());
    assertEquals(1300, circle3.getB());
    assertEquals(1400, circle3.getOpacity());

    // Testing Setters for circle 4
    circle4.getLocation().setX(10000);
    circle4.getLocation().setY(20000);
    circle4.setWidth(30000);
    circle4.setHeight(40000);
    circle4.setR(11000);
    circle4.setG(12000);
    circle4.setB(13000);
    circle4.setOpacity(14000);
    assertEquals(10000, circle4.getLocation().getX());
    assertEquals(20000, circle4.getLocation().getY());
    assertEquals(30000, circle4.getWidth());
    assertEquals(40000, circle4.getHeight());
    assertEquals(11000, circle4.getR());
    assertEquals(12000, circle4.getG());
    assertEquals(13000, circle4.getB());
    assertEquals(14000, circle4.getOpacity());
  }

  @Test
  public void testCloneShape() {
    AbstractShape testClone = circle2.cloneShape();
    assertEquals(testClone.getLabel(), circle2.getLabel());
    assertEquals(testClone.getLocation().getX(), circle2.getLocation().getX());
    assertEquals(testClone.getLocation().getY(), circle2.getLocation().getY());
    assertEquals(testClone.getWidth(), circle2.getWidth());
    assertEquals(testClone.getHeight(), circle2.getHeight());
    assertEquals(testClone.getR(), circle2.getR());
    assertEquals(testClone.getB(), circle2.getB());
    assertEquals(testClone.getG(), circle2.getG());
    assertEquals(testClone.getOpacity(), circle2.getOpacity());
  }
}
