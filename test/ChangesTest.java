import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.Circle;
import cs5004.animator.model.Move;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Recolor;
import cs5004.animator.model.Rect;
import cs5004.animator.model.Resize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class is an extra check to make sure that changes handle inputs/outputs properly
 * (changes are tested as well in the model tests).
 */
public class ChangesTest {
  private Point2D point;
  private Point2D pointS;
  private Point2D pointE;
  private Move move1;
  private Recolor recolor1;
  private Resize resize1;

  @Before
  public void setup() {
    point = new Point2D(1,1);
    pointS = new Point2D(3,4);
    pointE = new Point2D(5,6);
    Rect rectangle1 = new Rect("R1", 3, 6, 4, 7);
    Circle circle1 = new Circle("c1", 1, 2, 3);
    move1 = new Move(rectangle1, "R1",
        3, 4,5,6,7,8);
    recolor1 = new Recolor(circle1, "C1",
        3, 4, 5, 6, 7, 8, 9,10,
        11, 12);
    resize1 = new Resize(rectangle1, "R1", 3, 4,5,6,
        7,8);
  }

  @Test
  public void testMoveInvalid() {

    // set/get colors
    try {
      move1.setStartR(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.setStartG(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.setStartB(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.setStartA(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.setUpdatedR(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.setUpdatedG(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.setUpdatedB(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.setUpdatedA(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      move1.getStartR();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.getStartB();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.getStartG();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.getStartA();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.getUpdatedR();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.getUpdatedB();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.getUpdatedG();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.getUpdatedA();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }

    // set/get dimensions
    try {
      move1.getStartHeight();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move does not have a height value", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.getStartWidth();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot get width values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.getUpdatedHeight();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot get height value", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.getUpdatedWidth();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot get width value", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      move1.setStartHeight(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot set height values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.setStartWidth(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot set width values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.setUpdatedHeight(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot set height value", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      move1.setUpdatedWidth(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Move cannot set width value", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }


  }

  @Test
  public void testRecolorInvalid() {

    // set/get location
    try {
      recolor1.getStartReference();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Recolor cannot get position values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      recolor1.getReference();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Recolor cannot get position values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      recolor1.setStartReference(point);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Recolor cannot set position values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      recolor1.setReference(point);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Recolor cannot set position values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }

    // set/get dimensions
    try {
      recolor1.setStartHeight(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Recolor cannot set height values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      recolor1.setStartWidth(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Recolor cannot set width values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      recolor1.setUpdatedHeight(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Recolor cannot set height value", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      recolor1.setUpdatedWidth(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Recolor cannot set width value", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }


  }

  @Test
  public void testResizeInvalid() {

    // set/get colors
    try {
      resize1.setStartR(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.setStartG(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.setStartB(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.setStartA(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.setUpdatedR(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.setUpdatedG(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.setUpdatedB(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.setUpdatedA(1);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot set color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }

    try {
      resize1.getStartR();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.getStartB();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.getStartG();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.getStartA();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.getUpdatedR();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.getUpdatedB();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.getUpdatedG();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.getUpdatedA();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot get color values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }

    // set/get location
    try {
      resize1.getStartReference();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot get position values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.getReference();
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot get position values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.setStartReference(point);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot set position values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      resize1.setReference(point);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Resize cannot set position values", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }

  }

  @Test
  public void testValidMove() {

    assertEquals(pointS.getX(), move1.getStartReference().getX());
    assertEquals(pointS.getY(), move1.getStartReference().getY());
    move1.setStartReference(point);
    assertEquals(point.getX(), move1.getStartReference().getX());
    assertEquals(point.getY(), move1.getStartReference().getY());

    assertEquals(pointE.getX(), move1.getReference().getX());
    assertEquals(pointE.getY(), move1.getReference().getY());
    move1.setReference(point);
    assertEquals(point.getX(), move1.getReference().getX());
    assertEquals(point.getY(), move1.getReference().getY());

  }


  @Test
  public void testValidRecolor() {

    // set/get colors
    assertEquals(3, recolor1.getStartR());
    recolor1.setStartR(1);
    assertEquals(1, recolor1.getStartR());

    assertEquals(4, recolor1.getStartG());
    recolor1.setStartG(2);
    assertEquals(2, recolor1.getStartG());

    assertEquals(5, recolor1.getStartB());
    recolor1.setStartB(3);
    assertEquals(3, recolor1.getStartB());

    assertEquals(6, recolor1.getStartA());
    recolor1.setStartA(4);
    assertEquals(4, recolor1.getStartA());

    assertEquals(7, recolor1.getUpdatedR());
    recolor1.setUpdatedR(5);
    assertEquals(5, recolor1.getUpdatedR());

    assertEquals(8, recolor1.getUpdatedG());
    recolor1.setUpdatedG(6);
    assertEquals(6, recolor1.getUpdatedG());

    assertEquals(9, recolor1.getUpdatedB());
    recolor1.setUpdatedB(7);
    assertEquals(7, recolor1.getUpdatedB());

    assertEquals(10, recolor1.getUpdatedA());
    recolor1.setUpdatedA(8);
    assertEquals(8, recolor1.getUpdatedA());

  }

  @Test
  public void testValidResize() {

    assertEquals(4, resize1.getStartHeight());
    resize1.setStartHeight(2);
    assertEquals(2, resize1.getStartHeight());

    assertEquals(6, resize1.getUpdatedHeight());
    resize1.setUpdatedHeight(1);
    assertEquals(1, resize1.getUpdatedHeight());

    assertEquals(3, resize1.getStartWidth());
    resize1.setStartWidth(2);
    assertEquals(2, resize1.getStartWidth());

    assertEquals(5, resize1.getUpdatedWidth());
    resize1.setUpdatedWidth(2);
    assertEquals(2, resize1.getUpdatedWidth());

  }

}

