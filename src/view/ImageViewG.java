package view;

import controller.Features;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * An interactive Graphical User Interface for the Image Manipulation and Enhancement Tool that
 * displays the current image being worked on.
 */
public interface ImageViewG extends ImageView {

  /**
   * Print a message in the view.
   *
   * @param message to be printed.
   */
  @Override
   void printMessage(String message);

  /**
   * Opens a fileChooser popup to select an image file.
   *
   * @return the imageFile chosen using the fileChooser
   */
  public File openImageSelectionAndGetImageFile();

  /**
   * Opens a save file popup to select the image file to save.
   *
   * @return the image file to save
   */
  public File openImageSelectionAndSaveImageFile();

  /**
   * Open a dialog box to take inputs.
   *
   * @param operation operation for which inputs are required
   * @param message   input prompt message
   * @return parameters that have been received as inputs
   */
  public String[] openDialogBox(String operation, String message);

  /**
   * Provide the view with an instance of Features (controller).
   *
   * @param features the features passed to the method
   */
  public void addFeatures(Features features);


  /**
   * Change the image displayed on the GUI along with its histogram.
   *
   * @param image     is the new image
   * @param histogram is the histogram of the new image
   */

  public void changeImage(BufferedImage image, BufferedImage histogram);


  /**
   * Display errors using a pop-up dialog box.
   *
   * @param error the error message to be displayed
   */
  public void showError(String error);

  /**
   * Call a preview screen for an operation to look at the split view of the operation.
   *
   * @param operationName name of the operation being performed
   * @param features      an instance of the controller
   */
  public void preview(String operationName, Features features);

  /**
   * Call a preview screen for an operation to look at the split view of the operation.
   *
   * @param operationName name of the operation being performed
   * @param features      an instance of the controller
   * @param parameters    parameters that the operation may require
   */
  public void preview(String operationName, Features features, String[] parameters);

  /**
   * Change the preview image based on change of split percentage in the slider.
   *
   * @param image the image replacing the existing image in the preview
   */
  public void changePreviewImage(BufferedImage image);

  /**
   * If cancel button is clicked in the preview dialog, close the dialog box without performing any
   * operation.
   */
  public void disposePreviewDialog();

}
