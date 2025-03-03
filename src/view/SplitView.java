package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

/**
 * This class makes a split view panel that allows the user to preview an operation.
 */
class SplitView extends JPanel {


  private JScrollPane imageScrollPane;
  private final JSlider slider;
  private final JButton confirmButton;
  private final JButton cancelButton;

  /**
   * Creates a new JPanel with a double buffer and a flow layout.
   */
  SplitView() {
    super();

    //Layout
    this.setSize(new Dimension(500, 700));
    this.setAutoscrolls(true);
    this.setBackground(Color.WHITE);
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

    this.confirmButton = new JButton("Confirm");
    this.cancelButton = new JButton("Cancel");

    JLabel heading = new JLabel("PREVIEW");
    heading.setFont(new Font("Dialog", Font.PLAIN, 30));
    heading.setBorder(new EmptyBorder(10, 0, 30, 0));
    heading.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    this.add(heading);

    this.slider = new PreviewSlider();
    this.add(slider);

    this.imageScrollPane = new JScrollPane();
    imageScrollPane.setViewportView(new JLabel(new ImageIcon()));
    this.add(this.imageScrollPane);

    JPanel buttons = new JPanel();
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
    buttons.setBorder(new EmptyBorder(30, 50, 10, 0));
    buttons.setBackground(Color.WHITE);
    buttons.add(this.cancelButton);
    buttons.add(this.confirmButton);
    this.add(buttons);

  }

  /**
   * Get the image scroll pane that displays image.
   *
   * @return the image scroll pane
   */
  JScrollPane getImageScrollPane() {
    return imageScrollPane;
  }

  /**
   * Get the preview slider component.
   *
   * @return preview slider component
   */
  JSlider getSlider() {
    return slider;
  }

  /**
   * Get the confirm button component.
   *
   * @return the confirm button component
   */
  JButton getConfirmButton() {
    return confirmButton;
  }

  /**
   * Get the cancel button component.
   *
   * @return the cancel button component
   */
  JButton getCancelButton() {
    return cancelButton;
  }
}
