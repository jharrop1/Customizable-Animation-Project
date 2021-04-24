package cs5004.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs5004.animator.model.AnimationModel;

/**
 * Type of Iview representing the PlayBack view. It is similar to visualview in that it
 * generates a GUI, but delegates tick management to a controller and has buttons that allow
 * the user to issue commands changing animation behaviors.
 */
public class PlayBackView extends JFrame implements IView {
  JButton quitButton;
  JButton loopButton;
  JButton startButton;
  JButton pauseButton;
  JButton resumeButton;
  JButton restartButton;
  JButton increaseSpeedButton;
  JButton decreaseSpeedButton;
  JPanel buttonPanel;
  PlayBackPanel mainPanel;
  private int speed;
  private final int currentTick = 0;

  /**
   * Constructor for the playback view.
   * @param model the model the playback view is representing
   */
  public PlayBackView(AnimationModel model) {
    super();
    this.setTitle("Playback View.");
    Dimension canvasDimensions = new Dimension(model.getCanvas().getWidth()
            + model.getCanvas().getX(),
            model.getCanvas().getHeight() + model.getCanvas().getY());


    //Sets the size and the canvass offset
    this.setSize(model.getCanvas().getWidth(), model.getCanvas().getHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Set preferred pane
    this.setLayout(new BorderLayout());
    this.mainPanel = new PlayBackPanel(canvasDimensions);
    mainPanel.setPreferredSize(canvasDimensions);
    JScrollPane pane = new JScrollPane(mainPanel);
    pane.setPreferredSize(canvasDimensions);
    this.add(mainPanel, BorderLayout.CENTER);


    //Button Panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    //Loop Button
    loopButton = new JButton("Loop");
    loopButton.setActionCommand("LoopButton");
    buttonPanel.add(loopButton);

    //Start animation from beginning
    startButton = new JButton("Start");
    startButton.setActionCommand("StartButton");
    buttonPanel.add(startButton);

    //Restart animation from beginning, basically the same as start
    restartButton = new JButton("Restart");
    restartButton.setActionCommand("RestartButton");
    buttonPanel.add(restartButton);

    // Pause button
    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("PauseButton");
    buttonPanel.add(pauseButton);

    //Resume animation from pause button
    resumeButton = new JButton("Resume");
    resumeButton.setActionCommand("ResumeButton");
    buttonPanel.add(resumeButton);

    //Resume animation from pause button
    increaseSpeedButton = new JButton("Speed +");
    increaseSpeedButton.setActionCommand("IncreaseSpeedButton");
    buttonPanel.add(increaseSpeedButton);

    //Resume animation from pause button
    decreaseSpeedButton = new JButton("Speed -");
    decreaseSpeedButton.setActionCommand("DecreaseSpeedButton");
    buttonPanel.add(decreaseSpeedButton);
    //Quit Button
    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> System.exit(0));
    buttonPanel.add(quitButton);

    this.setVisible(true);
    this.pack();

  }

  @Override
  public void run() throws IOException {
    this.setVisible(true);
  }

  @Override
  public void setUpdatedShapes(int currentTick) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("PlayBack View cannot set updated shapes");
  }

  @Override
  public void setCurrentShapes(AnimationModel model) {
    this.mainPanel.setAnimatedShapes(model);
    // every child component calls paintComponent as a result
    this.repaint();
  }

  @Override
  public ViewType getViewType() {
    return null;
  }

  @Override
  public void setSpeed(int speed) throws UnsupportedOperationException {
    return;
  }

  @Override
  public void setListeners(ActionListener clicks, KeyListener keys) {
    startButton.addActionListener(clicks);
    loopButton.addActionListener(clicks);
    pauseButton.addActionListener(clicks);
    restartButton.addActionListener(clicks);
    resumeButton.addActionListener(clicks);
    increaseSpeedButton.addActionListener(clicks);
    decreaseSpeedButton.addActionListener(clicks);
    mainPanel.addKeyListener(keys);
    startButton.addKeyListener(keys);
    loopButton.addKeyListener(keys);
    pauseButton.addKeyListener(keys);
    restartButton.addKeyListener(keys);
    resumeButton.addKeyListener(keys);
    increaseSpeedButton.addKeyListener(keys);
    decreaseSpeedButton.addKeyListener(keys);
  }


}
