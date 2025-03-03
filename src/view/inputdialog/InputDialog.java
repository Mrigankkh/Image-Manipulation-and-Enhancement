package view.inputdialog;

import java.util.List;
import javax.swing.JTextField;

/**
 * An interface for the InputDialog that is used for certain operations that require input. Has one
 * method getData().
 */
public interface InputDialog {

  /**
   * Gets the data for all input text fields in the Input Dialog box; there is one text field for
   * each input required.
   *
   * @return a list of JTextFields; one for each input required.
   */
  List<JTextField> getData();
}
