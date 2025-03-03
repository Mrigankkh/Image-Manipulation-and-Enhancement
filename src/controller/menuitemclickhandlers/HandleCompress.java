package controller.menuitemclickhandlers;

import model.ImageModel;
import view.ImageViewG;

/**
 * This class handles compress when compress is selected from the menu.
 */
public class HandleCompress extends AbstractMenuItemClickHandler {

  private int compressBy;


  /**
   * Constructs a HandleCompress object when nothing is passed to the constructor.
   */
  public HandleCompress() {
    super();
    this.compressBy = Integer.MIN_VALUE;

  }

  /**
   * Handle a click of a menu item.
   *
   * @param view         the ImageViewGUI view
   * @param model        the ImageModel model
   * @param imageName    the name of the image
   * @param imageIsSaved whether the image is saved or not
   */
  @Override
  public void handle(ImageViewG view, ImageModel model, String imageName, boolean imageIsSaved) {
    this.setImageName(imageName);

    if (imageNotLoaded()) {
      throw new RuntimeException("Load an image before performing an operation");
    }
    String[] parameters = view.openDialogBox("Compress", "Enter the compress-by value: ");

    if (parameters == null) {
      return;
    }
    try {
      compressBy = Integer.parseInt(parameters[0]);
    } catch (Exception e) {
      view.showError("Compress by can only take numerical values.");
      return;
    }

    if (compressBy > 100 || compressBy < 0) {
      view.showError("Compress by must be in range 0 - 100");
      return;
    }

    this.setImageName(imageName);
    model.compress(compressBy, imageName, imageName);
    model.histogram(imageName, (imageName + "_histogram"));
    view.changeImage(arrayToBufferedImage(model.look(imageName)),
        arrayToBufferedImage(model.get((imageName + "_histogram"))));
    this.setImageIsSaved(false);
  }
}