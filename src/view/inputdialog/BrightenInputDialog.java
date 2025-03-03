package view.inputdialog;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * This is a class that extends AbstractInputDialog for brighten. The BrightenInputDialog enables
 * the user to enter a value that they want the image brightened by.
 */
public class BrightenInputDialog extends AbstractInputDialog {

  /**
   * Creates a new BrightenInputDialog. The user can enter the value that they want to brighten the
   * image by.
   */
  public BrightenInputDialog() {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.inputs.add(new JTextField());
    this.add(new JLabel("Brighten-by:"));
    for (JTextField input : inputs) {
      this.add(input);
    }

  }
}
