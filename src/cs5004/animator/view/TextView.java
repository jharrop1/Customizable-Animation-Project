package cs5004.animator.view;

import java.io.IOException;

import cs5004.animator.model.AnimationModel;

/**
 * Creates the textual representation.
 */
public class TextView implements IView {
  AnimationModel model;
  Appendable fileOutput;

  public TextView(AnimationModel model, Appendable fileOutput) {
    this.model = model;
    this.fileOutput = fileOutput;
  }

  @Override
  public void run() throws IOException {
    try {
      fileOutput.append(model.toString());
    } catch (IOException e) {
      System.out.println("Error with the file writer.");
    }
  }

  @Override
  public void setUpdatedShapes(int currentTick) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("SVG View cannot set updated shapes");
  }

  @Override
  public ViewType getViewType() {
    return ViewType.TEXT;
  }

  @Override
  public void setSpeed(int speed) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("SVG View cannot set speed");
  }
}
