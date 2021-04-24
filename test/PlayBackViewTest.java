
import org.junit.Before;
import org.junit.Test;

import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import java.awt.Frame;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import cs5004.animator.easyanimator.EasyAnimator;

/**
 * This class ensures that wiring from the controller properly adjusts the behaviors of the
 * GUI as commands are issued.
 */
public class PlayBackViewTest extends AssertJSwingJUnitTestCase{
  //private JFrame frame;

  @Override
  protected void onSetUp() {
    application(EasyAnimator.class)
        .withArgs("-in resources/buildings.txt -view playback -speed 10").start();
    FrameFixture frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
      protected boolean isMatching(Frame frame) {
        return "Your application title".equals(frame.getTitle()) && frame.isShowing();
      }
    }).using(robot());

  }

  @Test
  public void shouldCopyTextInLabelWhenClickingButton() {
    /*
    window.textBox("textToCopy").enterText("Some random text");
    window.button("copyButton").click();
    window.label("copiedText").requireText("Some random text");
     */
  }

}
