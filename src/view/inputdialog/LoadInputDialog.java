package view.inputdialog;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

/**
 * This is a class that extends AbstractInputDialog for compress. The CompressInputDialog enables
 * the user to enter a value that they want the image compressed by.
 */
public class LoadInputDialog extends AbstractInputDialog {

  /**
   * Creates a new CompressInputDialog. The user can enter the value that they want to compress the
   * image by.
   */
  public LoadInputDialog() {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(new JLabel("The current image is not saved. "
        + "Loading a new image will cause you to "
        + "lose your changes. Do you want to continue?"));


  }


}
