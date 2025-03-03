package view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

/**
 * This class represents the JPanel for the histogram. This panel contains an image with the ability
 * to scroll.
 */
class ImagePanel extends JPanel {

  private JScrollPane imageScrollPane;

  /**
   * Constructs a new ImagePanel. Contains an area for the image with scrolling and padding.
   */
  ImagePanel() {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    Border padding = BorderFactory.createEmptyBorder(50, 50, 50, 50);
    this.setBorder(padding);
    this.setBackground(Color.WHITE);

    this.imageScrollPane = new JScrollPane();
    imageScrollPane.setViewportView(new JLabel(new ImageIcon()));
    this.setSize(new Dimension(500, 700));
    this.setAutoscrolls(true);

    this.add(this.imageScrollPane);

  }

  /**
   * Creates a viewport in the scroll pane and sets that to a JLabel which has the ImageIcon.
   *
   * @param image a BufferedImage image
   */
  void changeImage(BufferedImage image) {
    this.imageScrollPane.setViewportView(new JLabel(new ImageIcon(image)));
  }


}
