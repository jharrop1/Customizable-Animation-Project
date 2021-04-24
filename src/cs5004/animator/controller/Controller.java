package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import cs5004.animator.model.AbstractChange;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.IView;
import cs5004.animator.view.PlayBackView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.ViewType;
import cs5004.animator.view.VisualView;

public class Controller implements IController, ActionListener {
  private AnimationModel model;
  private IView view;
  private int speed;
  private Timer timer;
  private int currentTick = 1;
  private boolean isLooping = false;
  private int finalTime;

  public Controller(AnimationModel model, IView view, int speed, Appendable output) throws IOException {
    if (model == null) {
      throw new IllegalArgumentException("The provided model is null");
    } else if (speed < 1) {
      throw new IllegalArgumentException("The speed must be > 0");
    }

    this.model = model;
    this.speed = speed;
    this.view = view;
    //TODO: gotta set the action listener, see old visual view maybe for inspo
    this.timer = new Timer(1000 / this.speed, this);
    this.finalTime = model.getFinalTime();
    //TODO figure out outputs for views
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
    if (this.getTick() >= model.getFinalTime()) {
      this.setTick(1);
    }
  }

  public AnimationModel getModel() {
    return model;
  }

  @Override
  public void pause() {
    this.timer.stop();
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
  public void adjustSpeed(int factor){
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
  public void go() throws IllegalStateException, IOException {
    if(this.view.equals(ViewType.TEXT) || this.view.equals(ViewType.SVG) || this.view.equals(ViewType.VISUAL)) {
      throw new IllegalStateException("Controller only run PlayBack view");
    } else {
      view.run();
      this.timer.start();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.view.setCurrentShapes(model.getShapesAtTick(currentTick));
    if (currentTick == this.finalTime) {
      this.timer.stop();
    }
    if (this.isLooping && this.currentTick == this.finalTime) {
      this.currentTick = 0;
      this.timer.restart();
    }
  }
}
