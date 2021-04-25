package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.Timer;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.IView;
import cs5004.animator.view.ViewType;

/**
 * Class representing the controller for the MVC model.
 */
public class Controller implements IController, ActionListener, KeyListener {
  private AnimationModel model;
  private IView view;
  private int speed;
  private Timer timer;
  private int currentTick = 1;
  private boolean isLooping = false;
  private int finalTime;

  /**
   * Constructor for the controller class.
   * @param model the model that is taken in.
   * @param view a playback view to display the model of.
   * @param speed the speed the process plays at.
   * @param output output for the model.
   * @throws IOException if the output runs into an error.
   */
  public Controller(AnimationModel model, IView view, int speed, Appendable output)
          throws IOException {
    if (model == null) {
      throw new IllegalArgumentException("The provided model is null");
    } else if (speed < 1) {
      throw new IllegalArgumentException("The speed must be > 0");
    }

    this.model = model;
    this.speed = speed;
    this.view = view;
    this.finalTime = model.getFinalTime();
    this.view.setListeners(this, this);
    this.timer = new Timer(1000 / this.speed, this);
    timer.setActionCommand("NextTick");
  }

  @Override
  public void setTick(int tick) {
    this.currentTick = tick;
  }

  @Override
  public int getTick() {
    return this.currentTick;
  }

  @Override
  public void toggleLooping() {
    if (isLooping) {
      isLooping = false;
      return;
    }
    isLooping = true;
  }

  @Override
  public AnimationModel getModel() {
    return model;
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public boolean isLooping() {
    return this.isLooping;
  }

  @Override
  public void restart() {
    this.currentTick = 1;
    if (this.timer.isRunning()) {
      this.timer.restart();
    } else {
      this.view.setCurrentShapes(model.getShapesAtTick(currentTick));
    }
  }

  @Override
  public void adjustSpeed(int factor) {
    // speed must be >= 1
    if (factor + this.speed < 1) {
      this.speed = 1;
    } else {
      this.speed += factor;
    }
    // reset timer rate
    this.timer.setDelay(1000 / this.speed);
  }

  @Override
  public void goController() throws IllegalStateException, IOException {
    if (this.view.getViewType().equals(ViewType.TEXT)
        || this.view.getViewType().equals(ViewType.SVG)
        || this.view.getViewType().equals(ViewType.VISUAL)) {
      throw new IllegalStateException("Controller only run PlayBack view");
    } else {
      view.run();
      this.timer.start();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    String command = e.getActionCommand();

    switch (command) {
      case "LoopButton":
        toggleLooping();
        break;
      case "StartButton":
        timer.start();
        break;
      case "RestartButton":
        this.restart();
        break;
      case "PauseButton":
        timer.stop();
        break;
      case "ResumeButton":
        timer.start();
        break;
      case "IncreaseSpeedButton":
        adjustSpeed(1);
        break;
      case "DecreaseSpeedButton":
        this.adjustSpeed(-1);
        break;
      case "NextTick":
        if (isLooping && currentTick >= finalTime) {
          this.currentTick = 1;
          this.timer.restart();
        } else {
          view.setCurrentShapes(model.getShapesAtTick(currentTick));
          this.currentTick++;
        }
        break;

      default:
        throw new IllegalStateException("Error: Unknown button");

    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    //Model doesn't take in any key typed event but it is required by the interface
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == KeyEvent.VK_UP || key == KeyEvent.VK_RIGHT) {
      this.adjustSpeed(1);
    } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT) {
      this.adjustSpeed(-1);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == KeyEvent.VK_P) {
      timer.stop();
    } else if (key == KeyEvent.VK_R) {
      timer.start();
    }
  }
}

