package controller.menuitemclickhandlers;

import model.ImageModel;
import view.ImageViewG;

/**
 * This class handles brighten when brighten is selected from the menu.
 */
public class HandleBrighten extends AbstractMenuItemClickHandler {

  int brightenBy;


  /**
   * Constructs HandleBrighten object when nothing is passed to the constructor.
   */
  public HandleBrighten() {
    super();
    this.brightenBy = Integer.MIN_VALUE;
  }


  /**
   * Handle a click of a menu item.
   *
   * @param view the ImageGUI view
   */
  @Override
  public void handle(ImageViewG view, ImageModel model, String imageName, boolean imageIsSaved) {
    this.setImageName(imageName);
    if (imageNotLoaded()) {
      throw new RuntimeException("Load an image before performing an operation");
    }
    String[] parameters = view.openDialogBox("Brighten", "Enter the brighten-by value: ");
    if (parameters == null) {
      return;
    }
    try {
      brightenBy = Integer.parseInt(
          parameters[0]);
    } catch (Exception e) {
      view.showError("Brighten by can only take numerical values.");
      return;
    }

    this.setImageName(imageName);
    model.brighten(imageName, imageName, brightenBy);
    model.histogram(imageName, (imageName + "_histogram"));
    view.changeImage(arrayToBufferedImage(model.look(imageName)),
        arrayToBufferedImage(model.get((imageName + "_histogram"))));
    this.setImageIsSaved(false);

  }
}