package cs5004.animator.controller;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.IView;

public class Controller implements IController, ActionListener {
  private AnimationModel model;
  private IView view;
  private int speed;
  private Timer timer;
  private int currentTick = 1;
  private boolean looping = false;


  public Controller(AnimationModel model, IView view, int speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;
    //TODO: gotta set the action listener, see old visual view maybe for inspo
    this.timer = new Timer(1000 / this.speed, this);
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
  void toggleLooping() {

  }

  @Override
  public void pause() {
    this.timer.stop();
    // TODO: may have to havethe view replay the current loop on loop
    //  or just display that set of shapes statically
  }

  @Override
  void restart() {

  }


  @Override
  void adjustSpeed(int speed){

  }

}