import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests cases for the TextView.
 */
public class TextViewTest {
  private AnimationModelImpl model;
  private String toh;

  /**
   * Setup for the text View.
   */
  @Before
  public void setup() {
    AnimationModelImpl model = new AnimationModelImpl();
  }

  /**
   * Testing the constructor.
   * @throws IOException if there is an error with reading input.
   */
  @Test
  public void testConstructor() throws IOException {
    FileReader fileIn = new FileReader("resources/smalldemo.txt");
    AnimationModel model = AnimationReader.parseFile(fileIn,
            new AnimationModelImpl.Builder());
    Appendable viewLog = new StringBuilder();
    TextView smalldemoText = new TextView(model, viewLog);
    smalldemoText.run();
    String[] lines = viewLog.toString().split("\n");
    assertEquals(21, lines.length);

  }

  /**
   * Tests for an invalid constructor.
   */
  @Test
  public void testInvalidConstructor() {
    try {
      FileReader fileIn = new FileReader("afsdfvsd.txt");
      AnimationModel model = AnimationReader.parseFile(fileIn,
              new AnimationModelImpl.Builder());
      fail("Invalid constructor should have thrown exception");
    } catch (IOException ioe) {
      assertEquals("afsdfvsd.txt (The system cannot find the " +
              "file specified)", ioe.getMessage());
      assertTrue(ioe.getMessage().length() > 0);
    }
  }

  /**
   * Tests the output of smallDemo.
   * @throws IOException if input is invalid.
   */
  @Test
  public void testOutputSmallDemo() throws IOException {
    FileReader fileIn = new FileReader("resources/smalldemo.txt");
    AnimationModel model = AnimationReader.parseFile(fileIn,
            new AnimationModelImpl.Builder());
    Appendable viewLog = new StringBuilder();
    TextView smalldemoText = new TextView(model, viewLog);
    smalldemoText.run();
    assertEquals("Rectangle R with RGB(255, 0, 0), and corner at (200, 200), " +
            "width: 50, height: 100\n" +
            "Ellipse C with RGB(0, 0, 255), and center at: (440, 70), x-diameter: 120, " +
            "y-diameter: 60\n" +
            "\n" +
            "R appears at time t=1\n" +
            "C appears at time t=6\n" +
            "\n" +
            "Shape R updates its position from x-dimension: 200, y-dimension: 200 to " +
            "x-dimension: 300, y-dimension: 300 from t= 10 to t= 50\n" +
            "Shape R updates its position from x-dimension: 200, y-dimension: 200 to " +
            "x-dimension: 300, y-dimension: 300 from t= 10 to t= 50\n" +
            "Shape C updates its position from x-dimension: 440, y-dimension: 70 to " +
            "x-dimension: 440, y-dimension: 250 from t= 20 to t= 50\n" +
            "Shape C updates its position from x-dimension: 440, y-dimension: 70 to " +
            "x-dimension: 440, y-dimension: 250 from t= 20 to t= 50\n" +
            "Shape C updates its color from (0, 0, 255, 100) " +
            "to (0, 170, 85, 100) from t= 50 to t= 70\n" +
            "Shape C updates its position from x-dimension: 440, y-dimension: 250 to" +
            " x-dimension: 440, y-dimension: 370 from t= 50 to t= 70\n" +
            "Shape C updates its position from x-dimension: 440, y-dimension: 250 to " +
            "x-dimension: 440, y-dimension: 370 from t= 50 to t= 70\n" +
            "Shape R updates its position from x-dimension: 300, y-dimension: 300 to " +
            "x-dimension: 300, y-dimension: 300 from t= 50 to t= 51\n" +
            "Shape R updates its dimensions from width: 50 height: 100 to width: 25 " +
            "height: 100 from t= 51 to t= 70\n" +
            "Shape R updates its position from x-dimension: 300, y-dimension: 300 to" +
            " x-dimension: 300, y-dimension: 300 from t= 51 to t= 70\n" +
            "Shape C updates its color from (0, 170, 85, 100) to (0, 255, 0, 100) " +
            "from t= 70 to t= 80\n" +
            "Shape C updates its position from x-dimension: 440, y-dimension: 370 to" +
            " x-dimension: 440, y-dimension: 370 from t= 70 to t= 80\n" +
            "Shape R updates its position from x-dimension: 300, y-dimension: 300 to " +
            "x-dimension: 200, y-dimension: 200 from t= 70 to t= 100\n" +
            "Shape R updates its position from x-dimension: 300, y-dimension: 300 to" +
            " x-dimension: 200, y-dimension: 200 from t= 70 to t= 100\n" +
            "Shape C updates its position from x-dimension: 440, y-dimension: 370 " +
            "to x-dimension: 440, y-dimension: 370 from t= 80 to t= 100\n", viewLog.toString());
  }

  /**
   * Tests the AllMotions file.
   * @throws IOException if input is invalid.
   */
  @Test
  public void testAllMotions() throws IOException {
    FileReader fileIn = new FileReader("resources/testAllMotions.txt");
    AnimationModel model = AnimationReader.parseFile(fileIn,
            new AnimationModelImpl.Builder());
    Appendable viewLog = new StringBuilder();
    TextView testAllMotionsText = new TextView(model, viewLog);
    testAllMotionsText.run();
    assertEquals("Rectangle R1 with RGB(100, 100, 100), " +
            "and corner at (100, 100), width: 100, height: 100\n" +
            "Ellipse C1 with RGB(0, 222, 255), and center at: " +
            "(440, 70), x-diameter: 120, y-diameter: 60\n" +
            "\n" +
            "R1 appears at time t=1\n" +
            "C1 appears at time t=6\n" +
            "\n" +
            "Shape R1 updates its dimensions from width: 100 height: 100 to width: " +
            "50 height: 100 from t= 1 to t= 10\n" +
            "Shape R1 updates its position from x-dimension: 100, y-dimension: 100 " +
            "to x-dimension: 200, y-dimension: 200 from t= 1 to t= 10\n" +
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
            "Shape C1 updates its dimensions from width: 120 height: 120 to width: 120" +
            " height: 60 from t= 50 to t= 70\n" +
            "Shape C1 updates its position from x-dimension: 100, y-dimension: 120 to " +
            "x-dimension: 440, y-dimension: 370 from t= 50 to t= 70\n" +
            "Shape R1 updates its position from x-dimension: 400, y-dimension: 400 to " +
            "x-dimension: 400, y-dimension: 400 from t= 50 to t= 51\n" +
            "Shape R1 updates its color from (255, 0, 230, 100) to (255, 0, 0, 100) " +
            "from t= 51 to t= 70\n" +
            "Shape R1 updates its dimensions from width: 50 height: 100 to width: 25 " +
            "height: 100 from t= 51 to t= 70\n" +
            "Shape R1 updates its position from x-dimension: 400, y-dimension: 400 to" +
            " x-dimension: 1, y-dimension: 1 from t= 51 to t= 70\n" +
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
            "x-dimension: 440, y-dimension: 370 from t= 80 to t= 100\n", viewLog.toString());
  }

  /**
   * Tests the MultipleShapes file.
   * @throws IOException if input is invalid.
   */
  @Test
  public void testMultipleShapes() throws IOException {
    FileReader fileIn = new FileReader("resources/testMultipleShapes.txt");
    AnimationModel model = AnimationReader.parseFile(fileIn,
            new AnimationModelImpl.Builder());
    Appendable viewLog = new StringBuilder();
    TextView multipleShapesText = new TextView(model, viewLog);
    multipleShapesText.run();
    assertEquals("Rectangle R1 with RGB(100, 100, 100), and corner " +
            "at (100, 100), width: 100, height: 100\n" +
            "Rectangle R2 with RGB(255, 0, 230), and corner at (400, 400), " +
            "width: 50, height: 100\n" +
            "Ellipse C1 with RGB(0, 222, 255), and center at: (440, 70), " +
            "x-diameter: 120, y-diameter: 60\n" +
            "Ellipse C2 with RGB(15, 25, 200), and center at: (100, 120), " +
            "x-diameter: 120, y-diameter: 120\n" +
            "\n" +
            "C1 appears at time t=1\n" +
            "C2 appears at time t=1\n" +
            "R1 appears at time t=1\n" +
            "R2 appears at time t=1\n" +
            "\n" +
            "Shape C1 updates its dimensions from width: 120 height: 60 to width:" +
            " 60 height: 60 from t= 1 to t= 50\n" +
            "Shape C1 updates its position from x-dimension: 440, y-dimension: 70 " +
            "to x-dimension: 100, y-dimension: 70 from t= 1 to t= 50\n" +
            "Shape C1 updates its position from x-dimension: 440, y-dimension: 70 " +
            "to x-dimension: 100, y-dimension: 70 from t= 1 to t= 50\n" +
            "Shape C2 updates its dimensions from width: 120 height: 120 to width:" +
            " 120 height: 60 from t= 1 to t= 50\n" +
            "Shape C2 updates its position from x-dimension: 100, y-dimension: 120 " +
            "to x-dimension: 440, y-dimension: 370 from t= 1 to t= 50\n" +
            "Shape R1 updates its dimensions from width: 100 height: 100 to width: " +
            "50 height: 100 from t= 1 to t= 50\n" +
            "Shape R1 updates its position from x-dimension: 100, y-dimension: 100 " +
            "to x-dimension: 200, y-dimension: 200 from t= 1 to t= 50\n" +
            "Shape R2 updates its position from x-dimension: 400, y-dimension: 400" +
            " to x-dimension: 123, y-dimension: 321 from t= 1 to t= 50\n" +
            "Shape R2 updates its position from x-dimension: 400, y-dimension: 400" +
            " to x-dimension: 123, y-dimension: 321 from t= 1 to t= 50\n", viewLog.toString());
  }
}
