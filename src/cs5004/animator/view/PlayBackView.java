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

  public PlayBackView(AnimationModel model, int speed) {
    super();
    this.model = model;
    this.speed = speed;
    this.setTitle("Playback View.");
    Dimension canvasDimensions = new Dimension(model.getCanvas().getWidth() + model.getCanvas().getX(),
            model.getCanvas().getHeight() + model.getCanvas().getY());


    //Sets the size and the canvass offset
    this.setSize(model.getCanvas().getWidth(), model.getCanvas().getHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Point2D point = new Point2D(model.getCanvas().getX(), model.getCanvas().getY()); //is this needed?

    //Set preferred pane
    this.setLayout(new BorderLayout());
    this.mainPanel = new PlaybackPanel(canvasDimensions);
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
    loopButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
      }
    });
    buttonPanel.add(loopButton);

    //Start animation from beginning
    startButton = new JButton("Start");
    startButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
      }
    });
    buttonPanel.add(startButton);

    //Restart animation from beginning, basically the same as start
    restartButton = new JButton("Restart");
    restartButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        timer.restart();
        currentTick = 0;
        super.mousePressed(e);
      }
    });
    buttonPanel.add(restartButton);

    // Pause button
    pauseButton = new JButton("Pause");
    pauseButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        timer.stop();
        super.mousePressed(e);
      }
    });
    buttonPanel.add(pauseButton);

    //Resume animation from pause button
    resumeButton = new JButton("Resume");
    resumeButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        timer.start();
        super.mousePressed(e);
      }
    });
    buttonPanel.add(resumeButton);

    //Resume animation from pause button
    increaseSpeedButton = new JButton("Speed +");
    increaseSpeedButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
      }
    });
    buttonPanel.add(increaseSpeedButton);

    //Resume animation from pause button
    decreaseSpeedButton = new JButton("Speed -");
    decreaseSpeedButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        setSpeed(-1);
        super.mousePressed(e);
      }
    });
    buttonPanel.add(decreaseSpeedButton);

    //Quit Button
    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> System.exit(0));
    buttonPanel.add(quitButton);

    this.timer = new Timer(1000 / this.speed, this);

    this.pack();
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
