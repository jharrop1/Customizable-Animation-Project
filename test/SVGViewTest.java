import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.SVGView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests cases for the SVGView.
 */
public class SVGViewTest {

  /**
   * Setup for the testing files.
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
    SVGView smalldemoSVG = new SVGView(model, viewLog, 10);
    smalldemoSVG.run();
    String[] lines = viewLog.toString().split("\n");
    assertEquals(42, lines.length);

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
  public void testSmallDemo() throws IOException {
    FileReader fileIn = new FileReader("resources/smalldemo.txt");
    AnimationModel model = AnimationReader.parseFile(fileIn,
            new AnimationModelImpl.Builder());
    Appendable viewLog = new StringBuilder();
    SVGView smalldemoSVG = new SVGView(model, viewLog, 10);
    smalldemoSVG.run();
    assertEquals("<svg width=\"360\" height=\"360\"" +
            " version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "<rect id=\"R\" x=\"0\" y=\"130\" width=\"50\" height=\"100\"" +
            " fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"100ms\" dur=\"900ms\" " +
            "attributeName=\"x\" from=\"0\" to=\"0\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"100ms\" dur=\"900ms\" " +
            "attributeName=\"y\" from=\"130\" to=\"130\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\"" +
            " attributeName=\"x\" from=\"0\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" " +
            "attributeName=\"y\" from=\"130\" to=\"230\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\"" +
            " attributeName=\"x\" from=\"0\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\"" +
            " attributeName=\"y\" from=\"130\" to=\"230\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"100ms\" " +
            "attributeName=\"x\" from=\"100\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"100ms\"" +
            " attributeName=\"y\" from=\"230\" to=\"230\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900ms\" " +
            "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900ms\"" +
            " attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900ms\"" +
            " attributeName=\"x\" from=\"100\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900ms\" " +
            "attributeName=\"y\" from=\"230\" to=\"230\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000ms\" " +
            "attributeName=\"x\" from=\"100\" to=\"0\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000ms\" " +
            "attributeName=\"y\" from=\"230\" to=\"130\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000ms\"" +
            " attributeName=\"x\" from=\"100\" to=\"0\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000ms\"" +
            " attributeName=\"y\" from=\"230\" to=\"130\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "\n" +
            "<ellipse id=\"C\" cx=\"240\" cy=\"0\" rx=\"120\" ry=\"60\" " +
            "fill=\"rgb(0,0,255)\" visibility=\"hidden\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"600ms\" dur=\"1400ms\" " +
            "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"600ms\" dur=\"1400ms\" " +
            "attributeName=\"cx\" from=\"240\" to=\"240\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"600ms\" dur=\"1400ms\"" +
            " attributeName=\"cy\" from=\"0\" to=\"0\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" " +
            "attributeName=\"cx\" from=\"240\" to=\"240\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\"" +
            " attributeName=\"cy\" from=\"0\" to=\"180\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" " +
            "attributeName=\"cx\" from=\"240\" to=\"240\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" " +
            "attributeName=\"cy\" from=\"0\" to=\"180\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\"" +
            " fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\"" +
            " attributeName=\"cx\" from=\"240\" to=\"240\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\"" +
            " attributeName=\"cy\" from=\"180\" to=\"300\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" " +
            "attributeName=\"cx\" from=\"240\" to=\"240\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" " +
            "attributeName=\"cy\" from=\"180\" to=\"300\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"7000ms\" dur=\"1000ms\"" +
            " attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" " +
            "fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"7000ms\" dur=\"1000ms\"" +
            " attributeName=\"cx\" from=\"240\" to=\"240\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"7000ms\" dur=\"1000ms\"" +
            " attributeName=\"cy\" from=\"300\" to=\"300\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\" " +
            "attributeName=\"cx\" from=\"240\" to=\"240\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"8000ms\" dur=\"2000ms\"" +
            " attributeName=\"cy\" from=\"300\" to=\"300\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "\n" +
            "</svg>", viewLog.toString());
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
    SVGView testAllMotionsSVG = new SVGView(model, viewLog, 15);
    testAllMotionsSVG.run();
    assertEquals("<svg width=\"500\" height=\"500\"" +
            " version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "<rect id=\"R1\" x=\"0\" y=\"0\" width=\"100\" height=\"100\" " +
            "fill=\"rgb(100,100,100)\" visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"66ms\" dur=\"600ms\"" +
            " attributeName=\"fill\" from=\"rgb(100,100,100)\" to=\"rgb(255,0,0)\" " +
            "fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"66ms\" dur=\"600ms\" " +
            "attributeName=\"width\" from=\"100\" to=\"50\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"66ms\" dur=\"600ms\"" +
            " attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"66ms\" dur=\"600ms\"" +
            " attributeName=\"x\" from=\"0\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"66ms\" dur=\"600ms\" " +
            "attributeName=\"y\" from=\"0\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"666ms\" dur=\"2666ms\" " +
            "attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(255,0,230)\"" +
            " fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"666ms\" dur=\"2666ms\" " +
            "attributeName=\"x\" from=\"100\" to=\"300\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"666ms\" dur=\"2666ms\" " +
            "attributeName=\"y\" from=\"100\" to=\"300\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"666ms\" dur=\"2666ms\" " +
            "attributeName=\"x\" from=\"100\" to=\"300\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"666ms\" dur=\"2666ms\" " +
            "attributeName=\"y\" from=\"100\" to=\"300\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3333ms\" dur=\"66ms\" " +
            "attributeName=\"x\" from=\"300\" to=\"300\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3333ms\" dur=\"66ms\" " +
            "attributeName=\"y\" from=\"300\" to=\"300\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3400ms\" dur=\"1266ms\" " +
            "attributeName=\"fill\" from=\"rgb(255,0,230)\" to=\"rgb(255,0,0)\"" +
            " fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3400ms\" dur=\"1266ms\" " +
            "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3400ms\" dur=\"1266ms\"" +
            " attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3400ms\" dur=\"1266ms\" " +
            "attributeName=\"x\" from=\"300\" to=\"-99\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3400ms\" dur=\"1266ms\" " +
            "attributeName=\"y\" from=\"300\" to=\"-99\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3400ms\" dur=\"1266ms\" " +
            "attributeName=\"x\" from=\"300\" to=\"-99\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3400ms\" dur=\"1266ms\" " +
            "attributeName=\"y\" from=\"300\" to=\"-99\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"4666ms\" dur=\"2000ms\" " +
            "attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(0,0,0)\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"4666ms\" dur=\"2000ms\" " +
            "attributeName=\"x\" from=\"-99\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"4666ms\" dur=\"2000ms\" " +
            "attributeName=\"y\" from=\"-99\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"4666ms\" dur=\"2000ms\"" +
            " attributeName=\"x\" from=\"-99\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"4666ms\" dur=\"2000ms\"" +
            " attributeName=\"y\" from=\"-99\" to=\"100\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "\n" +
            "<ellipse id=\"C1\" cx=\"340\" cy=\"-30\" rx=\"120\" ry=\"60\" " +
            "fill=\"rgb(0,222,255)\" visibility=\"hidden\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"400ms\" dur=\"933ms\" " +
            "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"400ms\" dur=\"933ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,222,255)\" to=\"rgb(222,0,255)\" " +
            "fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"400ms\" dur=\"933ms\" " +
            "attributeName=\"cx\" from=\"340\" to=\"340\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"400ms\" dur=\"933ms\"" +
            " attributeName=\"cy\" from=\"-30\" to=\"-30\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"1333ms\" dur=\"2000ms\"" +
            " attributeName=\"fill\" from=\"rgb(222,0,255)\" to=\"rgb(15,25,200)\" " +
            "fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"1333ms\" dur=\"2000ms\" " +
            "attributeName=\"rx\" from=\"120\" to=\"120\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"1333ms\" dur=\"2000ms\" " +
            "attributeName=\"ry\" from=\"60\" to=\"120\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"1333ms\" dur=\"2000ms\" " +
            "attributeName=\"cx\" from=\"340\" to=\"0\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"1333ms\" dur=\"2000ms\" " +
            "attributeName=\"cy\" from=\"-30\" to=\"20\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3333ms\" dur=\"1333ms\" " +
            "attributeName=\"fill\" from=\"rgb(15,25,200)\" to=\"rgb(0,170,85)\" " +
            "fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3333ms\" dur=\"1333ms\"" +
            " attributeName=\"rx\" from=\"120\" to=\"120\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3333ms\" dur=\"1333ms\"" +
            " attributeName=\"ry\" from=\"120\" to=\"60\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3333ms\" dur=\"1333ms\" " +
            "attributeName=\"cx\" from=\"0\" to=\"340\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3333ms\" dur=\"1333ms\" " +
            "attributeName=\"cy\" from=\"20\" to=\"270\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"4666ms\" dur=\"666ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(255,255,0)\"" +
            " fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"4666ms\" dur=\"666ms\" " +
            "attributeName=\"cx\" from=\"340\" to=\"-85\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"4666ms\" dur=\"666ms\" " +
            "attributeName=\"cy\" from=\"270\" to=\"-85\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5333ms\" dur=\"1333ms\" " +
            "attributeName=\"fill\" from=\"rgb(255,255,0)\" to=\"rgb(255,25,20)\" " +
            "fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5333ms\" dur=\"1333ms\"" +
            " attributeName=\"rx\" from=\"120\" to=\"300\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5333ms\" dur=\"1333ms\"" +
            " attributeName=\"ry\" from=\"60\" to=\"300\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5333ms\" dur=\"1333ms\"" +
            " attributeName=\"cx\" from=\"-85\" to=\"340\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5333ms\" dur=\"1333ms\" " +
            "attributeName=\"cy\" from=\"-85\" to=\"270\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "\n" +
            "</svg>", viewLog.toString());
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
    SVGView multipleShapes = new SVGView(model, viewLog, 5);
    multipleShapes.run();
    assertEquals("<svg width=\"500\" height=\"500\" " +
            "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "<rect id=\"R1\" x=\"100\" y=\"100\" width=\"100\" height=\"100\" " +
            "fill=\"rgb(100,100,100)\" visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"fill\" from=\"rgb(100,100,100)\" to=\"rgb(255,0,0)\" " +
            "fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"width\" from=\"100\" to=\"50\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"x\" from=\"100\" to=\"200\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"y\" from=\"100\" to=\"200\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "\n" +
            "<rect id=\"R2\" x=\"400\" y=\"400\" width=\"50\" height=\"100\" " +
            "fill=\"rgb(255,0,230)\" visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"width\" from=\"50\" to=\"15\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"height\" from=\"100\" to=\"15\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\"" +
            " attributeName=\"x\" from=\"400\" to=\"123\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"y\" from=\"400\" to=\"321\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"x\" from=\"400\" to=\"123\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"y\" from=\"400\" to=\"321\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "\n" +
            "<ellipse id=\"C1\" cx=\"440\" cy=\"70\" rx=\"120\" ry=\"60\" " +
            "fill=\"rgb(0,222,255)\" visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,222,255)\" to=\"rgb(222,0,255)\" " +
            "fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"rx\" from=\"120\" to=\"60\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\"" +
            " attributeName=\"ry\" from=\"60\" to=\"60\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\"" +
            " attributeName=\"cx\" from=\"440\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"cy\" from=\"70\" to=\"70\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"cx\" from=\"440\" to=\"100\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"cy\" from=\"70\" to=\"70\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "\n" +
            "<ellipse id=\"C2\" cx=\"100\" cy=\"120\" rx=\"120\" ry=\"120\" " +
            "fill=\"rgb(15,25,200)\" visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"fill\" from=\"rgb(15,25,200)\" to=\"rgb(0,170,85)\"" +
            " fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"rx\" from=\"120\" to=\"120\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"ry\" from=\"120\" to=\"60\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"cx\" from=\"100\" to=\"440\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"200ms\" dur=\"9800ms\" " +
            "attributeName=\"cy\" from=\"120\" to=\"370\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "\n" +
            "</svg>", viewLog.toString());
  }
}


