package controller.menuitemclickhandlers;

import model.ImageModel;
import view.ImageViewG;

/**
 * This class handles vertical flip when vertical flip is selected from the menu.
 */
public class HandleVerticalFlip extends AbstractMenuItemClickHandler {

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
    model.verticalFlip(imageName, imageName);
    model.histogram(imageName, (imageName + "_histogram"));
    view.changeImage(arrayToBufferedImage(model.look(imageName)),
        arrayToBufferedImage(model.get((imageName + "_histogram"))));
    this.setImageIsSaved(false);
  }
}
