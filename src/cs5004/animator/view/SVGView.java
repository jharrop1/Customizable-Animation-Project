package cs5004.animator.view;

import cs5004.animator.model.AbstractChange;
import cs5004.animator.model.AbstractShape;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AvailableChanges;
import cs5004.animator.model.AvailableShapes;
import cs5004.animator.model.ChangeComparator;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Model that outputs a SVG file.
 */
public class SVGView implements IView {
  AnimationModel model;
  Appendable fileOutput;
  int speed;
  File file;
  FileWriter writer;

  /**
   * Constructor for the SVG model.
   * @param model the animationModelImpl that contains the data.
   * @param fileOutput the name of the file to export to.
   * @param speed the speed of the file playback.
   * @throws IOException if there is an error with the IO.
   */
  public SVGView(AnimationModel model, Appendable fileOutput, int speed) throws IOException {
    this.model = model;
    this.fileOutput = fileOutput;
    this.speed = speed;

  }

  // TODO add in IO exception/trycatch handling like in the last lab

  /**
   * Creates a canvass with the specified dimensions for the SVG.
   * @throws IOException if there is an error with the IO.
   */
  public void createCanvas() throws IOException {
    fileOutput.append("<svg width=\"" + model.getCanvas().getWidth() + "\" height=\""
            + model.getCanvas().getHeight() + "\" version=\"1.1\"" +
            " xmlns=\"http://www.w3.org/2000/svg\">\n"); //Not sure if this line is right
    fileOutput.append("\n");
  }

  /**
   * Adds all the shapes to the SVG output file.
   * @param listOfShapes the list of shapes to be added.
   * @param speed the speed at which it plays.
   * @throws IOException if there is an error with the IO.
   */
  public void createShapes(LinkedList<AbstractShape> listOfShapes, int speed) throws IOException {

    for (AbstractShape shape : model.getShapes()) {
      //Attemped to set visiblity off the start, doesn't work when shapes come in later
      try {
        String visible = "";
        if (shape.getOpacity() == 100) {
          visible += "visible";
        } else {
          visible += "hidden";
        }
        //Setup for each Oval
        if (shape.getType().equals(AvailableShapes.OVAL)) {
          fileOutput.append("<ellipse id=\"" + shape.getLabel() + "\" cx=\""
                  + (shape.getLocation().getX() - model.getCanvas().getX()) + "\" cy=\""
                  + (shape.getLocation().getY() - model.getCanvas().getY())
                  + "\" rx=\"" + shape.getWidth() + "\" ry=\"" + shape.getHeight()
                  + "\" fill=\"rgb(" + shape.getR() + ","
                  + shape.getG() + "," + shape.getB() + ")\" visibility=\"" + visible + "\" >\n");
          //Setup for each rectangle
        } else if (shape.getType().equals(AvailableShapes.RECTANGLE)) {
          fileOutput.append("<rect id=\"" + shape.getLabel() + "\" x=\""
                  + (shape.getLocation().getX() - model.getCanvas().getX())
                  + "\" y=\"" + (shape.getLocation().getY() - model.getCanvas().getY())
                  + "\" width=\"" + shape.getWidth() + "\" "
                  + "height=\"" + shape.getHeight() + "\" fill=\"rgb(" + shape.getR() + ","
                  + shape.getG()
                  + "," + shape.getB() + ")\" visibility=\"" + visible + "\" >\n");
        }
        ChangeComparator byStartTime = new ChangeComparator();
        List<AbstractChange> sortedChangeListNoAppear = model.getChanges()
                .stream().sorted(byStartTime).collect(Collectors.toList());

        //This changes the setting to if it was hidden before any motions
        for (AbstractChange change : sortedChangeListNoAppear) {
          if (change.getShapeLabel().equals(shape.getLabel())) {
            if (visible.equals("hidden")) {
              fileOutput.append("    <animate attributeType=\"xml\" begin=\""
                      + change.getStartTime()
                      * 1000 / this.speed + "ms\" dur=\"" + (change.getEndTime()
                      - change.getStartTime()) * 1000 / this.speed
                      + "ms\" attributeName=\"visibility\" from=\"hidden\" to=\"visible\" " +
                      "fill=\"freeze\" />\n");
              visible += "visible";
            }
            //Finds changes for Moving a rectangle
            if (change.getType().equals(AvailableChanges.MOVE)
                    && shape.getType().equals(AvailableShapes.RECTANGLE)) {
              //Moves X coordinate
              fileOutput.append("    <animate attributeType=\"xml\" begin=\""
                      + change.getStartTime()
                      * 1000 / this.speed + "ms\" dur=\"" + (change.getEndTime()
                      - change.getStartTime()) * 1000 / this.speed
                      + "ms\" attributeName=\"x\" from=\""
                      + (change.getStartReference().getX() - model.getCanvas().getX()) + "\" to=\""
                      + (change.getReference().getX() - model.getCanvas().getX())
                      + "\" fill=\"freeze\" />\n");
              // Moves Y coordinate
              fileOutput.append("    <animate attributeType=\"xml\" begin=\""
                      + change.getStartTime() * 1000 / this.speed + "ms\" dur=\""
                      + (change.getEndTime() - change.getStartTime()) * 1000 / this.speed
                      + "ms\" attributeName=\"y\" from=\"" + (change.getStartReference().getY()
                      - model.getCanvas().getY())
                      + "\" to=\"" + (change.getReference().getY() - model.getCanvas().getY())
                      + "\" fill=\"freeze\" />\n");
              //Find changes for moving an oval
            } else if (change.getType().equals(AvailableChanges.MOVE)
                    && shape.getType().equals(AvailableShapes.OVAL)) {
              //Moves X coordinate
              fileOutput.append("    <animate attributeType=\"xml\" begin=\""
                      + change.getStartTime()
                      * 1000 / this.speed + "ms\" dur=\"" + (change.getEndTime()
                      - change.getStartTime()) * 1000 / this.speed
                      + "ms\" attributeName=\"cx\" from=\""
                      + (change.getStartReference().getX()
                      - model.getCanvas().getX()) + "\" to=\""
                      + (change.getReference().getX() - model.getCanvas().getX())
                      + "\" fill=\"freeze\" />\n");
              //Moves Y coordinate
              fileOutput.append("    <animate attributeType=\"xml\" begin=\""
                      + change.getStartTime() * 1000 / this.speed + "ms\" dur=\""
                      + (change.getEndTime() - change.getStartTime()) * 1000 / this.speed
                      + "ms\" attributeName=\"cy\" from=\"" + (change.getStartReference().getY()
                      - model.getCanvas().getY())
                      + "\" to=\"" + (change.getReference().getY() - model.getCanvas().getY())
                      + "\" fill=\"freeze\" />\n");
              // Resizes a rectangle
            } else if (change.getType().equals(AvailableChanges.RESIZE)
                    && shape.getType().equals(AvailableShapes.RECTANGLE)) {
              //Resizes the width
              fileOutput.append("    <animate attributeType=\"xml\" begin=\""
                      + change.getStartTime()
                      * 1000 / this.speed + "ms\" dur=\"" + (change.getEndTime()
                      - change.getStartTime()) * 1000 / this.speed
                      + "ms\" attributeName=\"width\" from=\"" + change.getStartWidth() + "\" to=\""
                      + change.getUpdatedWidth() + "\" fill=\"freeze\" />\n");
              //Resizes the height
              fileOutput.append("    <animate attributeType=\"xml\" begin=\""
                      + change.getStartTime() * 1000 / this.speed + "ms\" dur=\""
                      + (change.getEndTime() - change.getStartTime()) * 1000 / this.speed
                      + "ms\" attributeName=\"height\" from=\"" + change.getStartHeight()
                      + "\" to=\"" + change.getUpdatedHeight() + "\" fill=\"freeze\" />\n");
            } else if (change.getType().equals(AvailableChanges.RESIZE)
                    && shape.getType().equals(AvailableShapes.OVAL)) {
              //Changes x radius
              fileOutput.append("    <animate attributeType=\"xml\" begin=\""
                      + change.getStartTime()
                      * 1000 / this.speed + "ms\" dur=\"" + (change.getEndTime()
                      - change.getStartTime()) * 1000 / this.speed
                      + "ms\" attributeName=\"rx\" from=\"" + change.getStartWidth() + "\" to=\""
                      + change.getUpdatedWidth() + "\" fill=\"freeze\" />\n");
              //Changes y radius
              fileOutput.append("    <animate attributeType=\"xml\" begin=\""
                      + change.getStartTime() * 1000 / this.speed + "ms\" dur=\""
                      + (change.getEndTime() - change.getStartTime()) * 1000 / this.speed
                      + "ms\" attributeName=\"ry\" from=\"" + change.getStartHeight()
                      + "\" to=\"" + change.getUpdatedHeight() + "\" fill=\"freeze\" />\n");
              //Recolors the shape
            } else if (change.getType().equals(AvailableChanges.RECOLOR)) {
              fileOutput.append("    <animate attributeType=\"xml\" begin=\""
                      + change.getStartTime() * 1000 / this.speed + "ms\" dur=\""
                      + (change.getEndTime() - change.getStartTime()) * 1000 / this.speed
                      + "ms\" attributeName=\"fill\" from=\"rgb("
                      + change.getStartR() + ","
                      + change.getStartG() + ","
                      + change.getStartB() + ")\" to=\"rgb("
                      + change.getUpdatedR() + ","
                      + change.getUpdatedG() + ","
                      + change.getUpdatedB() + ")\" fill=\"freeze\" />\n");
            }
          }
        }
        //Adds closing box to the shape
        if (shape.getType().equals(AvailableShapes.RECTANGLE)) {
          fileOutput.append("</rect>\n");
        } else if (shape.getType().equals(AvailableShapes.OVAL)) {
          fileOutput.append("</ellipse>\n");
        }
        fileOutput.append("\n");
      } catch (IOException e) {
        System.out.println("Error while writing to file");
      }
    }
  }


  /**
   * Runs the SVG file.
   * @throws IOException if there is an error with the IO.
   */
  @Override
  public void run() throws IOException {
    try {
      createCanvas();
      createShapes(model.getShapes(), speed);
      fileOutput.append("</svg>");
    } catch (IOException e) {
      System.out.println("Error with the file writer.");
    }
  }

  @Override
  public void setUpdatedShapes(int currentTick) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("SVG View cannot set updated shapes");
  }

  @Override
  public void setCurrentShapes(AnimationModel model) {
    throw new UnsupportedOperationException("SVG View cannot set updated shapes");
  }

  /**
   * Returns the type of view.
   * @return the type of view.
   */
  @Override
  public ViewType getViewType() {
    return ViewType.SVG;
  }

  /**
   * Sets the speed of the view.
   * @param speed updated speed of the view.
   * @throws UnsupportedOperationException if its not supported.
   */
  @Override
  public void setSpeed(int speed) throws UnsupportedOperationException {
    this.speed = speed;
  }

  @Override
  public void setListeners(ActionListener clicks, KeyListener keys) {
    //SVG view does not have listeners, it is a method from the interface.
  }
}
