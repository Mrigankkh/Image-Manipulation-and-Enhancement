package view;


import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

/**
 * This class creates a preview slider when the split view preview is shown.
 */
class PreviewSlider extends JSlider {

  /**
   * Constructs the slider in the split view preview.
   */
  PreviewSlider() {

    super(JSlider.HORIZONTAL, 0, 100, 0);
    this.setMajorTickSpacing(25);
    this.setPaintTicks(true);
    this.setPaintLabels(true);
    this.setEnabled(false);
    this.setMajorTickSpacing(25);
    this.setPaintTicks(true);
    this.setPaintLabels(true);
    this.setEnabled(true);
    this.setBorder(new EmptyBorder(10, 0, 30, 0));//top,left,bottom,right
  }

}
