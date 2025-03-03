package controller.menuitemclickhandlers;

import controller.Features;
import model.ImageModel;
import view.ImageViewG;

/**
 * Handler for a menuItem that opens a preview screen.
 */
public abstract class AbstractMenuItemClickHandlersWithPreview extends
    AbstractMenuItemClickHandler {

  protected Features features;

  /**
   * Sets image name to the image name sent to by the model.
   */
  AbstractMenuItemClickHandlersWithPreview(Features features) {
    super();
    this.features = features;
  }

  /**
   * Opens the preview screen.
   *
   * @param view the ImageViewGUI view
   */
  void openPreview(ImageViewG view) {
    view.preview(this.operationName, this.features);

  }

  /**
   * Opens the preview screen.
   *
   * @param view the ImageViewGUI view
   */
  void openPreview(ImageViewG view, String[] parameters) {
    view.preview(this.operationName, this.features, parameters);

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
  public void handle(ImageViewG view, ImageModel model, String imageName, boolean imageIsSaved)

      throws RuntimeException {
    this.setImageName(imageName);
    if (imageNotLoaded()) {
      throw new RuntimeException("Load an image before performing an operation");
    }
    this.setImageName(imageName);
    this.openPreview(view);
    view.changePreviewImage(arrayToBufferedImage(model.look(imageName)));

  }
}
