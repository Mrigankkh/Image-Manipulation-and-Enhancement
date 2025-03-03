package controller.menuitemclickhandlers;

import model.ImageModel;
import view.ImageViewG;

/**
 * Handles a click in the GUI's menu item.
 */
public interface MenuItemClickHandler {
  /**
   * Handle a click of a menu item.
   *
   * @param view the ImageViewGUI view
   * @param model the ImageModel model
   * @param imageName the name of the image
   * @param imageIsSaved whether the image is saved or not
   */
  void handle(ImageViewG view, ImageModel model, String imageName, boolean imageIsSaved)
          throws RuntimeException;

  /**
   * Get the image name sent to the model.
   *
   * @return image name
   */
  String getImageName();

  /**
   * Set the image name sent to the model.
   */
  void setImageName(String newImageName);

  /**
   * Returns if the image is saved.
   * @return if the image is saved
   */
  boolean isImageIsSaved();

  /**
   * Sets if image is saved.
   * @param imageIsSaved the boolean imageIsSaved
   */
  void setImageIsSaved(boolean imageIsSaved);

}