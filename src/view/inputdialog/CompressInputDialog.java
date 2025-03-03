package view.inputdialog;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * This is a class that extends AbstractInputDialog for compress. The CompressInputDialog enables
 * the user to enter a value that they want the image compressed by.
 */
public class CompressInputDialog extends AbstractInputDialog {

  /**
   * Creates a new CompressInputDialog. The user can enter the value that they want to compress the
   * image by.
   */
  public CompressInputDialog() {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.inputs.add(new JTextField());
    this.add(new JLabel("Compress-by:"));
    for (JTextField input : inputs) {
      this.add(input);
    }

  }
}
