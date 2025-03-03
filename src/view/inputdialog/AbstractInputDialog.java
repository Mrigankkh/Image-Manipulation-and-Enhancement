package view.inputdialog;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * An abstract class that implements the interface InputDialog.
 */
class AbstractInputDialog extends JPanel implements InputDialog {

  List<JTextField> inputs;

  /**
   * Constructs a new AbstractInputDialog object. Initializes inputs to a new ArrayList.
   */
  AbstractInputDialog() {
    super();
    this.inputs = new ArrayList<>();
  }

  /**
   * Gets the data for all input text fields in the Input Dialog box; there is one text field for
   * each input required.
   *
   * @return a list of JTextFields; one for each input required.
   */
  public List<JTextField> getData() {
    return inputs;
  }
}
