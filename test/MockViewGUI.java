import controller.Features;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.SortedMap;
import view.ImageViewG;

/**
 * A mock of the ImageViewGUI.
 */
public class MockViewGUI implements ImageViewG {

  StringBuilder outLog;
  SortedMap<String, String[]> menu;
  String fileFormat;

  /**
   * Constructs an instance of a Graphical User Interface.
   *
   * @param menu menu supported by the view
   */
  public MockViewGUI(SortedMap<String, String[]> menu, StringBuilder outLog) {
    this.menu = menu;
    this.outLog = outLog;
    this.fileFormat = ".png";
  }

  /**
   * Print a message in the view.
   *
   * @param message to be printed.
   */
  @Override
  public void printMessage(String message) {
    System.out.println("PRINTING");
  }

  /**
   * Opens a fileChooser popup to select an image file.
   *
   * @return the imageFile chosen using the fileChooser
   */
  @Override
  public File openImageSelectionAndGetImageFile() {
    outLog.append("Opened File selector");
    return new File("test" + this.fileFormat);
  }

  /**
   * Opens a save file popup to select the image file to save.
   *
   * @return the image file to save
   */
  @Override
  public File openImageSelectionAndSaveImageFile() {
    outLog.append("Opened File saver");
    return new File("test" + this.fileFormat);

  }

  /**
   * Open a dialog box to take inputs.
   *
   * @param operation operation for which inputs are required
   * @param message   input prompt message
   * @return parameters that have been received as inputs
   */
  @Override
  public String[] openDialogBox(String operation, String message) {
    outLog.append("Opened Dialog Box for input of operation ").append(operation)
        .append(" with message ").append(message);
    return new String[]{"50"};
  }

  /**
   * Provide the view with an instance of Features (controller).
   *
   * @param features features supported by view
   */
  @Override
  public void addFeatures(Features features) {
    outLog.append("Adding features");
  }

  /**
   * Change the image displayed on the GUI along with its histogram.
   *
   * @param image     is the new image
   * @param histogram is the histogram of the new image
   */
  @Override
  public void changeImage(BufferedImage image, BufferedImage histogram) {
    outLog.append("Changing image and its histogram");
  }

  /**
   * Display errors using a pop-up dialog box.
   *
   * @param error the error message to be displayed
   */
  @Override
  public void showError(String error) {
    outLog.append("Error is thrown");
  }

  /**
   * Call a preview screen for an operation to look at the split view of the operation.
   *
   * @param operationName name of the operation being performed
   * @param features      an instance of the controller
   */
  @Override
  public void preview(String operationName, Features features) {
    outLog.append("Preview screen to be opened");
  }

  /**
   * Call a preview screen for an operation to look at the split view of the operation.
   *
   * @param operationName name of the operation being performed
   * @param features      an instance of the controller
   * @param parameters    parameters that the operation may require
   */
  @Override
  public void preview(String operationName, Features features, String[] parameters) {
    outLog.append("Preview screen to be opened");
  }

  /**
   * Change the preview image based on change of split percentage in the slider.
   *
   * @param image the image replacing the existing image in the preview
   */
  @Override
  public void changePreviewImage(BufferedImage image) {
    outLog.append("Preview image to be changed");
  }

  /**
   * If cancel button is clicked in the preview dialog, close the dialog box without performing any
   * operation.
   */
  @Override
  public void disposePreviewDialog() {
    outLog.append("Preview to be closed.");
  }

  /**
   * Set file format.
   *
   * @param fileFormat fileformat to set
   */
  public void setFileSaverFormat(String fileFormat) {
    this.fileFormat = fileFormat;
  }
}
