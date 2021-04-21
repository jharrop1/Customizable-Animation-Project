package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
  private int currentTick = 0;

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

    //Loop Button
    loopButton = new JButton("Loop");
    loopButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
      }
    });

    //Start animation from beginning
    startButton = new JButton("Start");
    startButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
      }
    });

    //Restart animation from beginning, basically the same as start
    restartButton = new JButton("Restart0");
    restartButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
      }
    });

    // Pause button
    pauseButton = new JButton("Pause");
    pauseButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
      }
    });

    //Resume animation from pause button
    resumeButton = new JButton("Resume");
    resumeButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
      }
    });

    //Resume animation from pause button
    increaseSpeedButton = new JButton("Speed +");
    increaseSpeedButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
      }
    });

    //Resume animation from pause button
    decreaseSpeedButton = new JButton("Speed -");
    decreaseSpeedButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
      }
    });

    //Quit Button
    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> System.exit(0));
    buttonPanel.add(quitButton);

    this.timer = new Timer(1000 / this.speed, this);
    this.speed = 1;
  }

  @Override
  public void run() throws IOException {
    this.setVisible(true);
    this.timer.start();
  }

  @Override
  public void setUpdatedShapes(int currentTick) {
    this.mainPanel.setAnimatedShapes(model.getShapesAtTick(currentTick));
    // every child component calls paintComponent as a result
    this.repaint();
  }

  @Override
  public ViewType getViewType() {
    return ViewType.PLAYBACK;
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
    this.setUpdatedShapes(this.currentTick);
    this.currentTick++;
  }
}
