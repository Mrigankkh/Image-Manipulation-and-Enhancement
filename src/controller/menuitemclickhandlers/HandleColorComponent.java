package controller.menuitemclickhandlers;

import model.ImageModel;
import view.ImageViewG;

/**
 * This class handles color component when one of the color components is selected from the menu.
 */
public class HandleColorComponent extends AbstractMenuItemClickHandler {

  private int colorNumber;

  /**
   * Constructs a new HandleColorComponent object.
   *
   * @param colorNumber the color that the component will be
   */
  public HandleColorComponent(int colorNumber) {
    super();
    this.colorNumber = colorNumber;

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
    model.colorComponent(imageName, imageName, colorNumber);
    model.histogram(imageName, (imageName + "_histogram"));
    view.changeImage(arrayToBufferedImage(model.look(imageName)),
        arrayToBufferedImage(model.get((imageName + "_histogram"))));
    this.setImageIsSaved(false);

  }
}
