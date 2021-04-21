package cs5004.animator.controller;

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

  //TODO we will have to figure out how to determine the last tick so we know the
  // animation is done and can loop
  /**
   * Set whether or not the animation loops back to the start once it is done.
   */
  void toggleLooping();

  /**
   * Method pauses the animation at the current tick
   */
  void pause();

  /**
   * Starts the animation again from tick 0.
   */
  void restart();

  //TODO not sure if the entered int should be the new speed or an int to be added to speed
  /**
   * Alters the speed (per tick) at which the animation proceeds. If the animation hits 0
   * or less speed it will pause.
   * @param speed integer for updating speed.
   */
  void adjustSpeed(int speed);

}
