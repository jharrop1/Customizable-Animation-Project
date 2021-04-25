import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.controller.Controller;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.PlayBackView;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Ensures that controller methods correctly modify the state of the animation controller.
 */
public class ControllerTest {


  @Test
  public void testConstructor() throws IOException {
    FileReader fileIn = new FileReader("resources/smalldemo.txt");
    AnimationModel model = AnimationReader.parseFile(fileIn,
            new AnimationModelImpl.Builder());
    PlayBackView smalldemoPB = new PlayBackView(model);
    Controller controller = new Controller(model, smalldemoPB, 5, System.out);
    assertEquals(controller.getModel(), model);
  }

  @Test
  public void testInvalidConstructor() {
    //Invalid filereader
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
    //Negative Speed
    try {
      FileReader fileIn = new FileReader("resources/smalldemo.txt");
      AnimationModel model = AnimationReader.parseFile(fileIn,
              new AnimationModelImpl.Builder());
      PlayBackView smalldemoPB = new PlayBackView(model);
      Controller controller = new Controller(model, smalldemoPB, -5, System.out);
      assertEquals(controller.getModel(), model);
      fail("Invalid constructor should have thrown exception");
    } catch (IllegalArgumentException | IOException ioe) {
      assertEquals("The speed must be > 0", ioe.getMessage());
      assertTrue(ioe.getMessage().length() > 0);
    }
  }

  @Test
  public void testTicks() throws IOException {
    FileReader fileIn = new FileReader("resources/smalldemo.txt");
    AnimationModel model = AnimationReader.parseFile(fileIn,
            new AnimationModelImpl.Builder());
    PlayBackView smalldemoPB = new PlayBackView(model);
    Controller controller = new Controller(model, smalldemoPB, 5, System.out);
    controller.setTick(10);
    assertEquals(10, controller.getTick());
    controller.setTick(45);
    assertEquals(45, controller.getTick());
  }

  @Test
  public void testRestart() throws IOException {
    FileReader fileIn = new FileReader("resources/smalldemo.txt");
    AnimationModel model = AnimationReader.parseFile(fileIn,
            new AnimationModelImpl.Builder());
    PlayBackView smalldemoPB = new PlayBackView(model);
    Controller controller = new Controller(model, smalldemoPB, 5, System.out);
    controller.setTick(35);
    controller.restart();
    assertEquals(1, controller.getTick());
    controller.setTick(70);
    controller.restart();
    assertEquals(1, controller.getTick());
  }

  @Test
  public void testLoop() throws IOException {
    FileReader fileIn = new FileReader("resources/smalldemo.txt");
    AnimationModel model = AnimationReader.parseFile(fileIn,
        new AnimationModelImpl.Builder());
    PlayBackView smalldemoPB = new PlayBackView(model);
    Controller controller = new Controller(model, smalldemoPB, 5, System.out);
    controller.setTick(35);
    controller.restart();
    assertFalse(controller.isLooping());
    controller.toggleLooping();
    assertTrue(controller.isLooping());
  }

  @Test
  public void testSpeed() throws IOException {
    FileReader fileIn = new FileReader("resources/smalldemo.txt");
    AnimationModel model = AnimationReader.parseFile(fileIn,
        new AnimationModelImpl.Builder());
    PlayBackView smalldemoPB = new PlayBackView(model);
    Controller controller = new Controller(model, smalldemoPB, 5, System.out);
    controller.setTick(35);
    controller.restart();
    assertEquals(controller.getSpeed(), 5);
    controller.adjustSpeed(5);
    assertEquals(controller.getSpeed(), 10);
  }
}
