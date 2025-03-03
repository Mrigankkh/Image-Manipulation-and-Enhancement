package view.inputdialog;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * This is a class that extends AbstractInputDialog for level adjust. The LevelAdjustInputDialog
 * enables the user to enter the values for black, middle, and white.
 */
public class LevelAdjustInputDialog extends AbstractInputDialog {

  /**
   * Creates a new LevelAdjustInputDialog. The user can enter the values for black, middle, and
   * white.
   */
  public LevelAdjustInputDialog() {
    super();
    this.inputs.add(new JTextField());
    this.inputs.add(new JTextField());
    this.inputs.add(new JTextField());
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    String[] text = {"Black: ", "Middle", "White"};
    int i = 0;
    for (JTextField input : inputs) {
      this.add(new JLabel(text[i++]));
      this.add(input);
    }

  }
}
