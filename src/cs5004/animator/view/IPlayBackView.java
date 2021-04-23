package cs5004.animator.view;

import cs5004.animator.controller.IController;

/**
 * The playback functionalities in PlayBackView (e.g. pause, set/stop looping, restart)
 * require a controller to handle an asynchronous set of inputs to be handled and passed
 * to the view for implementation.
 */
public interface IPlayBackView extends IView{

  /**
   * Provides the view a stream of inputs to with which to edit the animation.
   * @param inputs The controller handles inputs and sends them to this view.
   */
  void setInputs(IController inputs);
}

