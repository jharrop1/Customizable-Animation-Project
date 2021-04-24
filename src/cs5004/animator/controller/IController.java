package cs5004.animator.controller;

import java.io.IOException;

import cs5004.animator.model.AnimationModel;

/**
 * Interface for the controller class.
 */
public interface IController {

  /**
   * Set the animation to a given tick. May be useful for resetting animation to beginning
   * or making edits.
   * @param tick integer for the desired tick for the animation.
   */
  void setTick(int tick);

  /**
   * Gets the current tick.
   * @return The current tick.
   */
  int getTick();

  /**
   * Set whether or not the animation loops back to the start once it is done.
   */
  void toggleLooping();

  /**
   * Starts the animation again from tick 0.
   */
  void restart();

  /**
   * Gets the controller's model.
   * @return
   */
  AnimationModel getModel();

  /**
   * Alters the speed (per tick) at which the animation proceeds. If the animation hits 0
   * or less speed it will pause.
   * @param speed integer for updating speed.
   */
  void adjustSpeed(int speed);

  /**
   * Method that runs the controller.
   * @throws IOException if there is an error in the filereader
   */
  void goController() throws IOException;

}
