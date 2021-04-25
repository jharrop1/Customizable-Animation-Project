package cs5004.animator.controller;

import java.io.IOException;

import cs5004.animator.model.AnimationModel;

/**
 * Interface for the controller class. The controller manages ticks, holds the model, and handles
 * inputs from the view, changing animation behaviors for the user.
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
   * Determines whether or not an animation is looping (for testing).
   * @return boolean indicating looping state.
   */
  boolean isLooping();

  /**
   * Determines the speed set for an animation.
   * @return int indicating speed set.
   */
  int getSpeed();

  /**
   * Starts the animation again from tick 0.
   */
  void restart();

  /**
   * Gets the controller's model.
   * @return the model in the controller.
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
