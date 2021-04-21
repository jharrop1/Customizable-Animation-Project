package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.Point2D;

public class PlayBackView extends JFrame implements IView, ActionListener {
  private AnimationModel model;
  private JButton quitButton, loopButton, startButton, pauseButton, resumeButton,
          restartButton, increaseSpeedButton, decreaseSpeedButton;
  private JPanel buttonPanel;
  private PlaybackPanel mainPanel;
  private JLabel display;
  private int speed;
  private Timer timer;

  public PlayBackView(AnimationModel model) {
    super();
    this.model = model;
    this.setTitle("Playback View.");

    //Sets the size and the canvass offset
    this.setSize(new Dimension(model.getCanvas().getWidth(), model.getCanvas().getHeight()));
    Point2D point = new Point2D(model.getCanvas().getX(), model.getCanvas().getY());
    //this.mainPanel = new PlaybackPanel(point, new Dimension(model.getCanvas().getWidth(), model.getCanvas().getHeight()))

    //Set preferred pane
    JScrollPane pane = new JScrollPane(mainPanel);
    pane.setPreferredSize(new Dimension(model.getCanvas().getWidth(),
            model.getCanvas().getWidth()));
    this.getContentPane().add(pane, BorderLayout.CENTER);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Button Panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.timer = new Timer(1000 / this.speed, this);
    this.speed = 1;
  }

  @Override
  public void run() throws IOException {

  }

  @Override
  public void setUpdatedShapes(int currentTick) {

  }

  @Override
  public ViewType getViewType() {
    return null;
  }

  @Override
  public void setSpeed(int speed) throws UnsupportedOperationException {
    //TODO can speed be less than 1?
    if (speed > 0) {
      this.speed = this.speed + 1;
    } else if (speed < 0) {
      this.speed = this.speed - 1;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
