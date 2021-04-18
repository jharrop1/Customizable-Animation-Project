package cs5004.animator.easyanimator;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.ViewMaker;
import cs5004.animator.view.ViewType;
import cs5004.animator.view.IView;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Main method for the EasyAnimator project.
 */
public class EasyAnimator {

  /**
   * Method to run the program.
   * @param args arguments for running the program such as -view, -speed, -in, -out.
   */
  public static void main(String[] args) {
    // placeholder view parameters
    String fileNameIn = "";
    ViewType viewType = null;
    String fileNameOut = "";
    int speed = 1;

    // parse commandline
    for (int i = 0; i < args.length; i++) {
      if (args[i].charAt(0) == '-') {
        switch (args[i]) {
          case "-in":
            i++;
            fileNameIn = args[i];
            break;
          case "-view":
            i++;
            viewType = ViewType.getType(args[i]);
            break;
          case "-out":
            i++;
            fileNameOut = args[i];
            break;
          case "-speed":
            i++;
            speed = Integer.parseInt(args[i]);
            break;
          default:
            JFrame badArgs = new JFrame();
            JOptionPane.showMessageDialog(badArgs, "Warning",
                "Precede arguments with -speed, -out, -in, or -view.",
                JOptionPane.WARNING_MESSAGE);
        }
      }
    }

    if (fileNameIn.equals("") || fileNameOut.equals("")) {
      JFrame noFiles = new JFrame();
      JOptionPane.showMessageDialog(noFiles, "Warning",
          "Both file names must be provided.", JOptionPane.WARNING_MESSAGE);
    } else if (viewType == null) {
      JFrame noType = new JFrame();
      JOptionPane.showMessageDialog(noType, "Warning",
          "A view type must be provided.", JOptionPane.WARNING_MESSAGE);
    } else if (speed <= 0) {
      JFrame badSpeed = new JFrame();
      JOptionPane.showMessageDialog(badSpeed, "Warning",
          "Provided speed must be positive.", JOptionPane.WARNING_MESSAGE);
    }
    try {
      FileWriter fileOut = new FileWriter(fileNameOut);
      // build model with file reader
      FileReader fileIn = new FileReader(fileNameIn);
      AnimationModel model = AnimationReader.parseFile(fileIn,
          new AnimationModelImpl.Builder());
      IView view = ViewMaker.makeView(viewType, model, fileOut, speed);
      view.run();
      fileOut.close();
    } catch (IOException e) {
      // fileNameOut not a file
      try {
        // create outfile
        File fileOutFile = new File(fileNameOut);
        FileWriter fileOut = new FileWriter(fileOutFile);
        // build model with file reader
        FileReader fileIn = new FileReader(fileNameIn);
        AnimationModel model = AnimationReader.parseFile(fileIn,
            new AnimationModelImpl.Builder());
        IView view = ViewMaker.makeView(viewType, model, fileOut, speed);
        view.run();
        fileOut.close();
        //TODO: controller
      } catch (IOException ee) {
        // could not create output file writer
        // or could not find file in
        e.printStackTrace();
      } catch (IllegalArgumentException eee) {
        JFrame noFiles = new JFrame();
        JOptionPane.showMessageDialog(noFiles, "Warning",
            eee.toString(),
            JOptionPane.WARNING_MESSAGE);
      }
    }
  }
}
